package com.codehunter.activemq.sender;

import com.codehunter.activemq.sdo.ICheckingAccountService;
import com.codehunter.activemq.sdo.Order;
import com.codehunter.activemq.sender.service.OrderSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootApplication
public class ActivemqDemoSenderServerApplication implements ApplicationRunner {

    private static Logger log = LoggerFactory.getLogger(ActivemqDemoSenderServerApplication.class);

    @Autowired
    private OrderSender orderSender;
    @Autowired
    private ICheckingAccountService checkingAccountService;

    public static void main(String[] args) {
        SpringApplication.run(ActivemqDemoSenderServerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Spring Boot Embedded ActiveMQ Configuration Example");

        for (int i = 0; i < 7; i++) {
//            Order myMessage = new Order(i + " - Sending JMS Message using Embedded activeMQ", new Date());
//            orderSender.send(myMessage);
//          String data = checkingAccountService.getData();
//          System.out.println("data receive" + data);
        }

//        String data = checkingAccountService.getData();
//        System.out.println("data receive" + data);
        log.info("Waiting for all ActiveMQ JMS Messages to be consumed");

//        checkingAccountService.cancelAccount(999L);

//        Thread newThread = new Thread(() -> {
//            log.info("send long processing started ");
//            checkingAccountService.longProcessing();
//            log.info("send long processing done");
//        });
//        newThread.start();
//        TimeUnit.SECONDS.sleep(1);
//        LongStream.range(1,5).forEach(i ->asyncServiceCall(i));
//
//        TimeUnit.SECONDS.sleep(10);
//        LongStream.range(10,12).forEach(i ->asyncServiceCall(i));
//        TimeUnit.SECONDS.sleep(30);
//        System.exit(-1);
    }

    private void asyncServiceCall(long value) {
        Thread newThread = new Thread(() -> {
            log.info("send value " + value + "started");
            checkingAccountService.cancelAccount(value);
            log.info("send value " + value + "done");
        });
        newThread.start();
    }
}
