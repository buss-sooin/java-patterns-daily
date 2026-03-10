package designpatterns.structural.proxy.composite;

public class MessageQueueSender {

    public void sendAsync(String topic, String message) {
        System.out.println("메시지 큐 전송: " + topic + " - " + message);
        // 실제: KafkaTemplate.send(topic, message)
    }

}
