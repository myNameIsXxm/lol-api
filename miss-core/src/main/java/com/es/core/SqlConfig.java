package com.es.core;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:app.properties")
@EnableTransactionManagement
public class SqlConfig{

	private static final Logger logger = LoggerFactory.getLogger(SqlConfig.class);
	
	@Value("${sql.conn.driver}")
	private String driverClassName;

	@Value("${sql.conn.url}")
	private String url;

	@Value("${sql.conn.username}")
	private String username;
 
	@Value("${sql.conn.password}")
	private String password;
	
	@Value("${hibernate.dialect}")
	private String dialect;
	
	@Value("${hibernate.format_sql}")
	private String formatSql;
	
	@Value("${hibernate.show_sql}")
	private String showSql;
	
	@Value("${c3p0.pool_size}")
	private Integer poolSize;
	
	@Bean(name = "dataSource")
    public DataSource getDataSource() {
		logger.info("dataSource c3p0");
	    ComboPooledDataSource bean = new ComboPooledDataSource();
	    bean.setJdbcUrl(url);
	    try {
			bean.setDriverClass(driverClassName);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	    bean.setUser(username);
	    bean.setPassword(password);
	    
	    // 连接池中保留的最小连接数
	    bean.setMinPoolSize(poolSize);
	    // 连接池中保留的最大连接数
	    bean.setMaxPoolSize(poolSize*4);
	    // 初始化时获取的连接数
	    bean.setInitialPoolSize((int)(poolSize*1.4));
	    // 当连接池中的连接耗尽的时候c3p0一次同时获取的连接数
	    bean.setAcquireIncrement((int)(poolSize*0.4));
	    // 最大空闲时间,60秒内未使用则连接被丢弃
	    bean.setMaxIdleTime(60); 
	    // 每60秒检查所有连接池中的空闲连接
	    bean.setIdleConnectionTestPeriod(60);
	    // 如果maxStatements与maxStatementsPerConnection均为0，则缓存被关闭
	    bean.setMaxStatements(0);
	    // 定义了连接池内单个连接所拥有的最大缓存statements数
	    bean.setMaxStatementsPerConnection(0);
	    // 定义在从数据库获取新连接失败后重复尝试的次数
	    bean.setAcquireRetryAttempts(30); 
	    // 每个connection提交的时候都将校验其有效性,消耗较大,谨慎使用
	    bean.setTestConnectionOnCheckout(false);
	    // 两次连接中间隔时间，单位毫秒
	    bean.setAcquireRetryDelay(1000);
	    // 连接关闭时默认将所有未提交的操作回滚
	    bean.setAutoCommitOnClose(false);
	    // 获取连接失败但是数据源仍有效保留,如果设置为true,那么在尝试获取连接失败后该数据源将申明已断开并永久关闭
	    bean.setBreakAfterAcquireFailure(false);
	    // 当连接池用完时客户端调用getConnection()后等待获取新连接的时间，超时后将抛出SQLException,单位毫秒,默认为0,永不超时
	    bean.setCheckoutTimeout(0);
	    // 每60秒检查所有连接池中的空闲连接
	    bean.setIdleConnectionTestPeriod(600);
	    // 如果设为true那么在取得连接的同时将校验连接的有效性
	    bean.setTestConnectionOnCheckin(true);
	    return bean;
    }
	
	/*@Bean(name = "dataSource")
    public DataSource getDataSource() {
		logger.info("dataSource baseData");
		BasicDataSource bean= new BasicDataSource();
		bean.setDriverClassName(driverClassName);
		bean.setUrl(url);
		bean.setUsername(username);
		bean.setPassword(password);
		bean.setMaxActive(255);
		bean.setMaxIdle(2);
		bean.setMaxWait(120000L);
	    return bean;
    }*/
	
	@Bean(name = "sessionFactory")
	public AnnotationSessionFactoryBean getSessionFactory(){
		logger.info("sessionFactory");
		AnnotationSessionFactoryBean bean = new AnnotationSessionFactoryBean();
		bean.setDataSource(getDataSource());
		bean.setPackagesToScan("com.es.entity");
		Properties hiberProper = new Properties(); 
		hiberProper.setProperty("hibernate.dialect", dialect);
		hiberProper.setProperty("connection.autoReconnect", "true");
		hiberProper.setProperty("connection.autoReconnectForPools", "true");
		hiberProper.setProperty("connection.is-connection-validation-required", "true");
		hiberProper.setProperty("hibernate.show_sql", showSql);
		hiberProper.setProperty("hibernate.format_sql", formatSql);
		hiberProper.setProperty("hibernate.hbm2ddl.auto", "update");
		bean.setHibernateProperties(hiberProper);
		return bean;
	}
	
	@Bean(name="hibernateTemplate")
	public HibernateTemplate getHibernateTemplate(){
		logger.info("hibernateTemplate");
		HibernateTemplate bean = new HibernateTemplate();
		bean.setSessionFactory(getSessionFactory().getObject());
		return bean;
	}
	
	@Bean(name="jdbcTemplate")
	public JdbcTemplate getJdbcTemplate(){
		logger.info("jdbcTemplate");
		JdbcTemplate bean= new JdbcTemplate();
		bean.setDataSource(getDataSource());
		return bean;
	}
	
	/**
	 * 声明一个事务管理器
	 */
	@Bean(name="transactionManager")
	public HibernateTransactionManager transactionManager(){
		logger.info("transactionManager");
		HibernateTransactionManager bean = new HibernateTransactionManager();
		bean.setSessionFactory(getSessionFactory().getObject());
		bean.setDataSource(getDataSource());
		return bean;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer loadProperties() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		return configurer;
	}

}
