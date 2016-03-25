package demo.tools;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	//执行持久化访问的SessionFactory
		public static final SessionFactory sf;
		static
		{
			try
			{
				// 采用默认的hibernate.cfg.xml来创建Configuration的实例
				Configuration cfg = new Configuration().configure();
				org.hibernate.service.ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
				// 由Configuration的实例创建SessionFactory实例
				sf = cfg.buildSessionFactory(sr);
			}
			catch (Throwable ex)
			{
				System.err.println("初始化SessionFactory出现异常." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}
}
