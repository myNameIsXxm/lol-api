package com.es.dao.face;
import java.util.List;
import com.es.entity.Legend;
/**
* Legend Dao 
*/ 
public interface LegendDaoFace{
	public int save(Legend bean);

	public int delete(Long id);

	public int update(Legend bean);

	public Legend selectById(Long id);

	public List<Legend> lists(Integer num,Integer size);

}

