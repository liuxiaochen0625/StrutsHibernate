package demo.factory;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ComponentFactory {
	// 用于缓存组件实例的Map对象
	private Map<String , Object> componentMap
		= new HashMap<String , Object>();
	// 使用单例模式来设置组件工厂
	private static ComponentFactory cf;
	// 加载多个配置文件
	private ComponentFactory(String[] files)throws Exception
	{
		// 遍历每个配置文件
		for (String f : files )
		{
			// 使用SAXReader来负责解析配置文件
			Document doc = new SAXReader().read(new File(f));
			// 获取文档的根元素
			Element root = doc.getRootElement();
			// 遍历根元素下的每个子元素
			List el =  root.elements();
			for (Iterator it = el.iterator();it.hasNext() ; )
			{
				// 每个子元素对应一个组件
				Element em = (Element)it.next();
				String id = em.attributeValue("id");
				// 获取实现类
				String impl = em.attributeValue("class");
				// 使用反射创建组件
				Class implClazz = Class.forName(impl);
				Object d = implClazz.newInstance();
				// 将创建的组件放入缓存池中
				componentMap.put(id , d);
			}
			}
		}
		// 指定配置文件创建组件工厂的方法
		public static ComponentFactory instance(String[] files)
			throws Exception
		{
			if (cf == null)
			{
				cf = new ComponentFactory(files);
			}
			return cf;
		}
		// 不指定配置文件直接获取组件工厂的方法
		public static ComponentFactory instance()
			throws Exception
		{
			return cf;
		}
		// 获取组件的方法
		public Object getComponent(String id)
		{
			return componentMap.get(id);
		}
}
