package demo.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import demo.dao.PersonDao;
import demo.dao.SonDao;
import demo.domain.Person;
import demo.domain.Son;
import demo.exception.PersonException;
import demo.factory.ComponentFactory;
import demo.service.PersonService;
import demo.tools.HibernateUtil;

public class PersonServiceImpl implements PersonService{

	public void createPersonAndSon(Person person
			, List<Son> sons)throws PersonException
		{
			try
			{
				// 业务逻辑组件依赖于DAO组件，从组件工厂中取出两个DAO组件
				PersonDao pd = (PersonDao)ComponentFactory
					.instance().getComponent("personDao");
				SonDao sd = (SonDao)ComponentFactory
					.instance().getComponent("sonDao");
				// 获取Hibernate Session
				Session s = HibernateUtil.sf.getCurrentSession();
				Transaction tx = s.beginTransaction();
				// 以面向对象方式开始持久化操作
				pd.save(s , person);
				// 增加Person实体关联的Son实体
				for (int i = 0 ; i < sons.size()  ; i++ )
				{
					sons.get(i).setParent(person);
					sd.save(s, sons.get(i));
				}
				// 提交事务
				tx.commit();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw new PersonException("业务异常");
			}
		}

}
