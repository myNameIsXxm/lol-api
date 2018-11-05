package com.mysi.service.face;
import java.util.List;
import com.es.entity.Legend;
/**
* Legend Service 
*/ 
public interface LegendServiceFace{
	public int save(Legend bean);

	public int delete(Long id);

	public int update(Legend bean);

	public Legend selectById(Long id);

	public List<Legend> lists(Integer num,Integer size);

	public Legend getById(Long id);

}

