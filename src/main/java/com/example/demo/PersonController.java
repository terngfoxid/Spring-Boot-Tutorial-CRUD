package com.example.demo;

import java.util.*; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@Autowired
	TestObjectRepository testObjectRepository;


	@GetMapping("/person")
	public String personHome() {
      return String.format("<h1>Hello Person Controller Home</h1><br><p>/allperson to get example res</p>");
    }

	@GetMapping("/person/allperson")
	public List<Person> allPerson() {
		Person p1 = new Person(1,"p1");
		Person p2 = new Person(2,"p2");
		System.out.println("ok for created record");

		Person[] allPerson = new Person[2];
		System.out.println("ok for created array");

		allPerson[0] = p1;
		allPerson[1] = p2;
		System.out.println(allPerson.toString());
		System.out.println(allPerson[0]);
		System.out.println(allPerson[1]);
		System.out.println("ok for add record to array");

		List<Person> list = Arrays.asList(allPerson);

      return list;
    }

	//C
	@PostMapping("test_object")
	public ResponseEntity<TestObject> createTutorial(@RequestBody TestObject item) {
		try {
			TestObject _new = testObjectRepository.save(new TestObject(item.getName()));
			return new ResponseEntity<>(_new, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//R
	@GetMapping("/test_object/{id}")
	public ResponseEntity<TestObject> getTutorialById(@PathVariable("id") long id) {
		Optional<TestObject> data = testObjectRepository.findById(id);

		if (data.isPresent()) {
			return new ResponseEntity<>(data.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//U
	@PutMapping("/test_object")
	public ResponseEntity<TestObject> updateTutorial(@RequestBody TestObject item) {
		Optional<TestObject> oldData = testObjectRepository.findById((long) item.getId());

		if (oldData.isPresent()) {
			TestObject _newObject = oldData.get();
			_newObject.setName(item.getName());
			return new ResponseEntity<>(testObjectRepository.save(_newObject), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	//D
	@DeleteMapping("/test_object/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			testObjectRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}