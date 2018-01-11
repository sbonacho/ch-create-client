package com.soprasteria.seda.examples.insurance.peproxy.api;

import com.soprasteria.seda.examples.insurance.peproxy.cms.domain.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/client")
@ExposesResourceFor(Client.class)
public class ClientController {


	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	private final EntityLinks entityLinks;

	public ClientController(EntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(method = POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Response> createExecution(@RequestBody @Valid CreateExecutionCommand command) {
		logger.info("Create execution request recived");
		return dispatch(command);
	}

}
