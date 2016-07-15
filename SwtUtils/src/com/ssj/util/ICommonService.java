package com.ssj.util;

import java.io.Serializable;
import java.util.List;

public interface ICommonService {
	public void save(Object object);
	public void delete(Object object);
	public void update(Object object);
	public List findByHql(String hql);
	public Object loadEntity(Class cla, Serializable ser);
}
