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
	    
	    // ���ӳ��б�������С������
	    bean.setMinPoolSize(poolSize);
	    // ���ӳ��б��������������
	    bean.setMaxPoolSize(poolSize*4);
	    // ��ʼ��ʱ��ȡ��������
	    bean.setInitialPoolSize((int)(poolSize*1.4));
	    // �����ӳ��е����Ӻľ���ʱ��c3p0һ��ͬʱ��ȡ��������
	    bean.setAcquireIncrement((int)(poolSize*0.4));
	    // ������ʱ��,60����δʹ�������ӱ�����
	    bean.setMaxIdleTime(60); 
	    // ÿ60�����������ӳ��еĿ�������
	    bean.setIdleConnectionTestPeriod(60);
	    // ���maxStatements��maxStatementsPerConnection��Ϊ0���򻺴汻�ر�
	    bean.setMaxStatements(0);
	    // ���������ӳ��ڵ���������ӵ�е���󻺴�statements��
	    bean.setMaxStatementsPerConnection(0);
	    // �����ڴ����ݿ��ȡ������ʧ�ܺ��ظ����ԵĴ���
	    bean.setAcquireRetryAttempts(30); 
	    // ÿ��connection�ύ��ʱ�򶼽�У������Ч��,���Ľϴ�,����ʹ��
	    bean.setTestConnectionOnCheckout(false);
	    // ���������м��ʱ�䣬��λ����
	    bean.setAcquireRetryDelay(1000);
	    // ���ӹر�ʱĬ�Ͻ�����δ�ύ�Ĳ����ع�
	    bean.setAutoCommitOnClose(false);
	    // ��ȡ����ʧ�ܵ�������Դ����Ч����,�������Ϊtrue,��ô�ڳ��Ի�ȡ����ʧ�ܺ������Դ�������ѶϿ������ùر�
	    bean.setBreakAfterAcquireFailure(false);
	    // �����ӳ�����ʱ�ͻ��˵���getConnection()��ȴ���ȡ�����ӵ�ʱ�䣬��ʱ���׳�SQLException,��λ����,Ĭ��Ϊ0,������ʱ
	    bean.setCheckoutTimeout(0);
	    // ÿ60�����������ӳ��еĿ�������
	    bean.setIdleConnectionTestPeriod(600);
	    // �����Ϊtrue��ô��ȡ�����ӵ�ͬʱ��У�����ӵ���Ч��
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
	 * ����һ�����������
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
