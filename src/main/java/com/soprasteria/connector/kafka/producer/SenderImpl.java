package com.soprasteria.connector.kafka.producer;

import com.soprasteria.connector.producer.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

@Service
public class SenderImpl implements Sender<List> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SenderImpl.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${connector.topics.in}")
    private String topic;

    public ListenableFuture send(List message) {
        LOGGER.info("sending message='{}' to topic='{}'", message, topic);
        return kafkaTemplate.send(topic, message);
    }
}