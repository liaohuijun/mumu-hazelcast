package com.lovecws.mumu.hazelcast.topic;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.lovecws.mumu.hazelcast.HazelcastConfiguration;

import java.util.concurrent.TimeUnit;

/**
 * @author babymm
 * @version 1.0-SNAPSHOT
 * @Description: 分布式主题
 * @date 2017-09-15 15:30
 */
public class HazelcastDistributedTopic implements MessageListener<String> {

    /**
     * 发布主题消息
     *
     * @param topicName 主题名称
     */
    public void topic(String topicName) throws InterruptedException {
        HazelcastInstance hazelcastInstance = HazelcastConfiguration.instance();
        ITopic<String> topic = hazelcastInstance.getTopic(topicName);
        topic.addMessageListener(new HazelcastDistributedTopic());
        topic.publish("Hello to distributed world");
        TimeUnit.SECONDS.sleep(10);
        hazelcastInstance.shutdown();
    }

    @Override
    public void onMessage(Message<String> message) {
        System.out.println("Got message " + message.getMessageObject());
    }
}