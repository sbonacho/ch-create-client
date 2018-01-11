package com.soprasteria.seda.examples.insurance.connector.kafka.service;

import com.soprasteria.connector.domain.Message;
import com.soprasteria.connector.domain.Response;
import com.soprasteria.connector.producer.Sender;
import com.soprasteria.connector.service.MultipleRace;
import com.soprasteria.seda.examples.insurance.connector.domain.Message;
import com.soprasteria.seda.examples.insurance.connector.domain.Response;
import com.soprasteria.seda.examples.insurance.connector.producer.Sender;
import com.soprasteria.seda.examples.insurance.connector.service.MultipleRace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MultipleRaceImpl implements MultipleRace<List> {

    @Autowired
    private Sender sender;

    private static final Logger LOGGER = LoggerFactory.getLogger(MultipleRaceImpl.class);

    @Override
    public Response<List> run(List list) {
        List<Message> message = new ArrayList<>();
        message.add(new Message<List>(list));
        sender.send(message);
        Response<List> response = new Response<>(list);
        return response;
    }

    @KafkaListener(topics = "${connector.topics.in}")
    public void receive(List<Message> messages) {
        LOGGER.info("Initial: received type - {}, {}", messages.getClass().getName(), messages);
        for (Object m: messages) {
            LOGGER.info("message: {}, {}", m.getClass().getName(), m);
        }
    }

}
