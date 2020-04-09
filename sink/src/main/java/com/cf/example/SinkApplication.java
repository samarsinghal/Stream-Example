package com.cf.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;


@SpringBootApplication
public class SinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SinkApplication.class, args);
    }

}

// Legacy Spring Cloud Stream API
@EnableBinding(Sink.class)
@MessageEndpoint
class CoffeeBill {

    @StreamListener(value = Sink.INPUT)
    private void drink(Bill bill) {
        System.out.println(bill);
    }

}


// @Configuration
// class CoffeeDrinker {
//     @Bean
//     Consumer<RetailCoffee> drinkIt() {
//         return System.out::println;
//     }
// }


@Data
@AllArgsConstructor
class Bill {

    private String id, name;
    private String price;
}