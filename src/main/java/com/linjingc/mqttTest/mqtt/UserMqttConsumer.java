package com.linjingc.mqttTest.mqtt;

import com.alibaba.fastjson.JSON;
import com.linjingc.mqttTest.converter.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * 自定义编码消息
 */
@Component
@ConditionalOnProperty(value = "spring.mqtt.enable", havingValue = "true")
@Slf4j
public class UserMqttConsumer implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String topic = String.valueOf(message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC));
        User user = (User) message.getPayload();
        log.info("User接收到 mqtt消息，主题:{} 消息:{}", topic, JSON.toJSONString(user));
    }
}