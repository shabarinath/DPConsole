package com.dpconsole.dao;


public interface DefaultDao {

	public Object get(@SuppressWarnings("rawtypes") Class clazz, long id) throws Exception;

}
