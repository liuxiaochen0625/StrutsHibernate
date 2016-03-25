package common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

public interface BaseDao<T> {
	// 根据ID获取实体的方法
	T get(Session sess , Class<T> clazz , Serializable id);
	// 添加实体的方法
	Serializable save(Session sess , T entity)throws Exception;
	// 删除实体的方法
	void delete(Session sess , T entity);
	// 根据ID删除实体的方法
	void delete(Session sess ,Class<T> clazz , Serializable id);
	// 更新实体的方法
	void update(Session sess , T entity);
	// 查询所有实体的方法
	List<T> findAll(Session sess , Class<T> clazz);
}
