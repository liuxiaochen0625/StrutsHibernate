package demo.service;

import java.util.List;

import demo.domain.Person;
import demo.domain.Son;
import demo.exception.PersonException;

public interface PersonService {
	void createPersonAndSon(Person person
			, List<Son> sons)throws PersonException;
}
