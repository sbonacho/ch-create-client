package com.soprasteria.seda.examples.insurance.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = { CreateClientBootTests.topic })
public class CreateClientBootTests {

	@Autowired
	private MockMvc mockMvc;

    @Autowired
    private KafkaEmbedded embeddedKafka;

	protected static final String topic = "createClient";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	@Test
	public void clientCreateEventIsEqualsToPayload() throws Exception {

	    String payload = "{\"name\": \"John Doe\", \"address\": \"Bendford st 10\", \"interest\": \"Microservices\"}";
	    String eventName = "ClientCreated";

		mockMvc.perform(post("/client")
				.content(payload)
				.contentType(contentType))
				.andExpect(status().is2xxSuccessful());

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("testGroup", "true", this.embeddedKafka);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        ConsumerFactory<Integer, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
        Consumer<Integer, String> consumer = cf.createConsumer();
        this.embeddedKafka.consumeFromAnEmbeddedTopic(consumer, topic);
        ConsumerRecords<Integer, String> replies = KafkaTestUtils.getRecords(consumer);

        assertThat(replies.count()).isEqualTo(1);

        ObjectMapper mapper = new ObjectMapper();
        HashMap sent = mapper.readValue(payload, HashMap.class);
        HashMap received = null;
        for (ConsumerRecord<Integer, String> record : replies) {
            received = mapper.readValue(record.value(), HashMap.class);
        }

        for (Object key : sent.keySet()) {
            assertThat(sent.get(key)).isEqualTo(received.get(key));
        }

        assertThat(received.get("type")).isEqualTo(eventName);
	}

}
