package com.example.kafkaconsumer.controllers

import org.apache.kafka.clients.KafkaClient
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class order {
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello!"
    }

    @PostMapping("/send-message")
    fun sendMessageToKafka() {
        val props = Properties()
        props["bootstrap.servers"] = "localhost:9092"
        props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        props["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        val producer = KafkaProducer<String, String>(props)
        producer.send(ProducerRecord<String, String>("test-topic","""{"id": 1, "name" : "John", }"""))
        producer.send(ProducerRecord<String, String>("test-topic","""{"id": 1, "name" : "Jim", }"""))
        producer.send(ProducerRecord<String, String>("product-queries","""{"name" : "John", "inventory": 10, "id": 1}"""))
        producer.send(ProducerRecord<String, String>("product-queries","""{"name" : "Jim", "inventory": 20, "id": 2}"""))
        producer.close()
    }
}