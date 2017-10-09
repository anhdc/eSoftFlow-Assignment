package org.esoftflow.assignment.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.esoftflow.assignment.rest.errorhandling.CustomReasonPhraseException;
import org.esoftflow.assignment.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/mocked-custom-reason-phrase-exception")
public class CustomReasonPhraseExceptionMockResource {
	
	@Autowired
	private UserService userService;
	
	@GET
	public void testReasonChangedInResponse() throws CustomReasonPhraseException{
		userService.generateCustomReasonPhraseException();
	}
}
