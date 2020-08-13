package com.openshift.coursecatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openshift.coursecatalogue.model.Users;
import com.openshift.coursecatalogue.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
public class UserController {

	@Autowired
	private UserService usersService;
	
	@Autowired
	private ObjectMapper mapper;

	@RequestMapping("/getUser")
	public Users getUserName(@RequestParam String name) {
		Users users = usersService.getByName(name);
		return users;
	}

	@PostMapping(value = "/sendTemporaryPassword")
	public ResponseEntity<JsonNode> sendTemporaryPassword(@RequestBody JsonNode userNode) {
		JsonNode responceNode = mapper.createObjectNode();
		try {
			responceNode = usersService.sendTemporaryPassword(userNode.get("email").asText(),
					userNode.get("userName").asText());
			return new ResponseEntity<JsonNode>(responceNode, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			((ObjectNode)responceNode).put("status", false);
			((ObjectNode)responceNode).put("data",  "Email Not send. Exception accured");
			e.printStackTrace();
			return new ResponseEntity<JsonNode>(responceNode, HttpStatus.NOT_FOUND);

		}

	}

	@PostMapping(path = "/insetUser")
	public ResponseEntity<JsonNode> insetUser(@RequestBody JsonNode userNode) {
		JsonNode responceNode = mapper.createObjectNode();
		try {
			System.out.println("==>>> "+userNode);
			Users user = new Users();
			user.setEmail(userNode.get("email").asText());
			user.setName(userNode.get("name").asText());
			user.setPassword(userNode.get("password").asText());
			user.setAdmin_access(userNode.get("admin_access").asBoolean());
			user.setRole(userNode.get("role").asText());
			responceNode = usersService.insertUsers(user);
			return new ResponseEntity<JsonNode>(responceNode, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			((ObjectNode)responceNode).put("status", false);
			((ObjectNode)responceNode).put("data", "Exception is accured");
			return new ResponseEntity<JsonNode>(responceNode, HttpStatus.NOT_FOUND);

		}
	}
}
