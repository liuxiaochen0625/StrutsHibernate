package demo.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import demo.factory.ComponentFactory;

public class ContextLoaderServlet extends HttpServlet{
	// 指定配置文件所谓位置的参数名
	public static final String CONFIG_LOCATION_PARAM
		= "contextConfig";
	public void init(ServletConfig config)
		throws ServletException
	{
		super.init(config);
		String configLocation = config
			.getInitParameter(CONFIG_LOCATION_PARAM);
		// 以逗号和空白作为分隔符
		String[] configs = configLocation.split("(\\s*,\\s*)|\\s+");
		for (int i = 0; i < configs.length ; i++ )
		{
			configs[i] = getServletContext().getRealPath("/")
				+ configs[i];
		}
		try
		{
			// 初始化ComponentFactory对象
			ComponentFactory.instance(configs);
		}
		catch (Exception ex)
		{
			System.out.println("初始化组件工厂出现异常" + ex);
		}
	}
}
