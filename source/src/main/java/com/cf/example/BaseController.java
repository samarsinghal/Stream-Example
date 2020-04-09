package com.cf.example;

import org.springframework.cloud.stream.messaging.Source;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;

@RestController
@EnableBinding(Source.class)
@AllArgsConstructor
public class BaseController {

    private final Source source;

    @GetMapping(value = "/greet/{name}")
    public String elements(@PathVariable final String name) {
        return "Hello " + name + " from Cloud Foundry!";
    }

    @GetMapping(value = "/rabbitmq/orderCoffee")
    public void testRabbitTopic(@RequestParam("coffeeName") final String coffeeName, @RequestParam("coffeeId") final String coffeeId) {

        final Coffee coffee = new Coffee(coffeeId.toString(), coffeeName.toString());

        System.out.println(coffee);
        this.source.output().send(MessageBuilder.withPayload(coffee).build());

    }

}
