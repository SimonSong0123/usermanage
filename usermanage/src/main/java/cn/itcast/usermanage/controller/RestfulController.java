package cn.itcast.usermanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.itcast.usermanage.pojo.User;
import cn.itcast.usermanage.service.CommonUserService;

@RestController
@RequestMapping("rest")
public class RestfulController {
	
	@Autowired
	private CommonUserService userService;

	@GetMapping("{id}")
	public ResponseEntity<User> queryById(@PathVariable("id") Long id) {
		User user = userService.queryById(id);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Void> save(User user) {
		try {
			int count = userService.save(user);
			if (count == 1) {
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping
	public ResponseEntity<Void> update(User user) {
		try {
			userService.update(user);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestParam("ids")List<Long> ids) {
		try {
			userService.delete(ids);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
