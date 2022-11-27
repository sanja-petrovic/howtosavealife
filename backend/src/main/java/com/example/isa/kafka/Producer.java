package com.example.isa.kafka;

import com.example.isa.dto.BloodRequestResponseDto;
import com.example.isa.dto.NewsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public Producer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void send(NewsDto newsDto) throws JsonProcessingException {
        String topic = "news.topic";
        String newsAsMessage = objectMapper.writeValueAsString(newsDto);
        kafkaTemplate.send(topic, newsAsMessage);
        log.info("news sent: {}", newsAsMessage);
    }

    public void send(BloodRequestResponseDto bloodRequestResponseDto) throws JsonProcessingException {
        String topic = "blood.request.responses.topic";
        String responseAsMessage = objectMapper.writeValueAsString(bloodRequestResponseDto);
        kafkaTemplate.send(topic, responseAsMessage);
        log.info("response to blood request {} sent: {}", bloodRequestResponseDto.getRequestId(), responseAsMessage);
    }


}