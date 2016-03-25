package demo.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import demo.domain.Person;
import demo.domain.Son;
import demo.factory.ComponentFactory;
import demo.service.PersonService;

public class AddPersonAndSon extends ActionSupport{
	private Person person;
	private List<Son> sons;

	// 处理用户请求的execute方法
	public String execute()throws Exception
	{
		// 通过业务逻辑工厂取得业务逻辑组件
		PersonService ps = (PersonService)ComponentFactory
			.instance().getComponent ("personService");
		// 调用业务逻辑方法处理用户请求
		ps.createPersonAndSon(person , sons);
		return SUCCESS;
	}

	// person的setter和getter方法
	public void setPerson(Person person)
	{
		this.person = person;
	}
	public Person getPerson()
	{
		return this.person;
	}

	// sons的setter和getter方法
	public void setSons(List<Son> sons)
	{
		this.sons = sons;
	}
	public List<Son> getSons()
	{
		return this.sons;
	}
}
