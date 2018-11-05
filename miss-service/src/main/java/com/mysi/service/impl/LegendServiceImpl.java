package com.mysi.service.impl;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import com.es.dao.face.LegendDaoFace;
import com.mysi.service.face.LegendServiceFace;
import com.es.entity.Legend;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
* Legend Service Impl 
*/ 
@Component("legendService")
@Transactional
public class LegendServiceImpl implements LegendServiceFace{

	private static final Logger log = LoggerFactory.getLogger(LegendServiceImpl.class);

	@Inject
	private LegendDaoFace legendDao;

	@Override
	public int save(Legend bean){
		return legendDao.save(bean);
	}

	@Override
	public int delete(Long id){
		return legendDao.delete(id);
	}

	@Override
	public int update(Legend bean){
		return legendDao.update(bean);
	}

	@Override
	public Legend selectById(Long id){
		return legendDao.selectById(id);
	}

	@Override
	public List<Legend> lists(Integer num,Integer size) {
		return legendDao.lists( num, size);
	}

	@Override
	public Legend getById(Long id) {
		return legendDao.selectById(id);
	}

}

