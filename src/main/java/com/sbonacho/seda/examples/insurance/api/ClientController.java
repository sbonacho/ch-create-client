package com.sbonacho.seda.examples.insurance.api;

import com.sbonacho.seda.examples.insurance.api.model.CreateClient;
import com.sbonacho.seda.examples.insurance.adapters.Command2Event;
import com.sbonacho.seda.examples.insurance.bus.producer.Sender;
import com.sbonacho.seda.examples.insurance.events.ClientCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
@RequestMapping("/client")
public class ClientController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private Sender<ClientCreated> sender;

	@Value("${response.synchronous}")
	private Boolean synchronous;


	@RequestMapping(method = PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity createClient(@RequestBody @Valid CreateClient command) {
		LOGGER.info("CreateClient API command received -> {}", command);
		try {
			sender.send(Command2Event.CreateClient2Event(command));
			if (synchronous) {
                LOGGER.info("Waiting for the final Event...");
                return new ResponseEntity(HttpStatus.OK);
			} else {
                return new ResponseEntity(HttpStatus.OK);
            }
		} catch(Exception e) {
			sender.send(Command2Event.CreateClient2Event(command));
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
