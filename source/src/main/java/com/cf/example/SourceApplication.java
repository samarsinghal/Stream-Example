package com.cf.example;

// import lombok.AllArgsConstructor;
// import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cloud.stream.annotation.EnableBinding;
// import org.springframework.cloud.stream.messaging.Source;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.messaging.support.MessageBuilder;
// import org.springframework.scheduling.annotation.EnableScheduling;
// import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import javax.annotation.PostConstruct;

// import java.util.Arrays;
// import java.util.List;
import java.util.Random;
import java.util.UUID;
// import java.util.function.Supplier;

@SpringBootApplication
public class SourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SourceApplication.class, args);
    }
}

// Legacy Spring Cloud Stream API
// @EnableBinding(Source.class)
// @EnableScheduling
// @AllArgsConstructor
// class CoffeeGrower {

//     private final Source source;
//     private final CoffeeGenerator generator;

//     @Scheduled(fixedRate = 1000)
//     private void send() {
//         final Coffee coffee = generator.generate();

//         System.out.println(coffee);
//         source.output().send(MessageBuilder.withPayload(coffee).build());
//     }
// }

// @Configuration
// @EnableScheduling
// @AllArgsConstructor
// class CoffeeGrower {
// private final CoffeeGenerator generator;

// @Scheduled(fixedRate = 1000)
// @Bean
// Supplier<WholesaleCoffee> sendCoffee() {
// return () -> generator.generate();
// }
// }

@Component
class CoffeeGenerator {
    @Value("${names:Expresso,Latte,Cappuccino,Cortado,Ristretto,Irish,Americano,Flat White}")
    private String[] names;

    private final Random rnd = new Random();

    @PostConstruct
    private void showConfig() {
        System.out.println("List of Available Coffees: " + String.join(",", names));
    }

    Coffee generate() {
        return new Coffee(UUID.randomUUID().toString(), names[rnd.nextInt(names.length)]);
    }
}

// @Data
// @AllArgsConstructor
// class Coffee {
//     private final String id, name;
// }
