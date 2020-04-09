package com.cf.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.Random;
import java.util.function.Function;
import java.util.HashMap;

@SpringBootApplication
public class ProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessorApplication.class, args);
    }

}

//  Legacy Spring Cloud Stream API
@EnableBinding(Processor.class)
//@MessageEndpoint
class BillGenerator {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    Bill transform(Coffee wCoffee) {

        System.out.println(wCoffee);

        Bill bill = new Bill(wCoffee.getId(),
                wCoffee.getName(), getPrice(wCoffee.getName()));
                
        System.out.println(bill);

        return bill;
    }


     String getPrice(String name){
        HashMap<String, String> priceList = new HashMap<String, String>();

        /*Adding elements to HashMap*/
        priceList.put("Expresso", "5.0");
        priceList.put("Latte", "5.25");
        priceList.put("Cappuccino", "6");
        priceList.put("Ristretto", "3");
        priceList.put("Irish", "2.5");
        priceList.put("Americano", "6.5");
        priceList.put("Flat White", "3.4");

        if (priceList.containsKey(name)) {
            return priceList.get(name);
        }
        return "0.0";
     }

}






// @Configuration
// class CoffeeRoaster {
//     private final Random rnd = new Random();

//     @Bean
//     Function<WholesaleCoffee, RetailCoffee> processIt() {
//         return wCoffee -> {
//             RetailCoffee rCoffee = new RetailCoffee(wCoffee.getId(),
//                     wCoffee.getName(),
//                     rnd.nextInt(2) == 0
//                             ? RetailCoffee.State.WHOLE_BEAN
//                             : RetailCoffee.State.GROUND);

//             System.out.println(rCoffee);
//             return rCoffee;
//         };
//     }

//     @Bean
//     Function<RetailCoffee, RetailCoffee> fixIt() {
//         return coffee -> new RetailCoffee(coffee.getId(),
//                 coffee.getName(),
//                 RetailCoffee.State.WHOLE_BEAN);
//     }
// }

@Data
@AllArgsConstructor
class Bill {

    private String id, name;
    private String price;
}

@Data
@AllArgsConstructor
class Coffee {
    private final String id, name;
}