package com.codehunter.activemq.sender.config;

import com.codehunter.activemq.sdo.ICheckingAccountService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.remoting.JmsInvokerProxyFactoryBean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * @author codehunter
 */
@Configuration
@EnableJms
public class ActiveMQConfig {
    public static final String QUEUE_NAME = "order.queue";

    @Value("${spring.activemq.broker-url}")
    String brokerUrl;

    @Bean
    public JmsListenerContainerFactory<?> queueListenerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setMessageConverter(messageConverter());
        return factory;
    }

    @Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public ActiveMQQueue getQueue() {
        return new ActiveMQQueue("queue.remote");
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        factory.setTrustAllPackages(true);
        return factory;
    }

    @Bean
    public JmsInvokerProxyFactoryBean checkingAccountService() {
        JmsInvokerProxyFactoryBean jmsInvokerProxyFactoryBean = new JmsInvokerProxyFactoryBean();
        jmsInvokerProxyFactoryBean.setServiceInterface(ICheckingAccountService.class);
        jmsInvokerProxyFactoryBean.setConnectionFactory(connectionFactory());
        jmsInvokerProxyFactoryBean.setQueue(getQueue());
        return jmsInvokerProxyFactoryBean;
    }
}
