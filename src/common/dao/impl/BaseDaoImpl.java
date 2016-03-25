package common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.Session;

import common.dao.BaseDao;

public class BaseDaoImpl<T> implements BaseDao<T>{

	@Override
	public T get(Session sess, Class<T> clazz, Serializable id) {
		return (T)sess.get(clazz , id);
	}

	@Override
	public Serializable save(Session sess, T entity) throws Exception {
		sess.save(entity);
		Method getId = entity.getClass().getMethod("getId");
		return (Serializable)getId.invoke(entity);
	}

	@Override
	public void delete(Session sess, T entity) {
		sess.delete(entity);
	}

	@Override
	public void delete(Session sess, Class<T> clazz, Serializable id) {
		sess.delete(get(sess , clazz , id));
		
	}

	@Override
	public void update(Session sess, T entity) {
		sess.saveOrUpdate(entity);
		
	}

	@Override
	public List<T> findAll(Session sess, Class<T> clazz) {
		return sess.createQuery("select en from "
				+ clazz.getSimpleName() + " en")
				.list();
	}
}
