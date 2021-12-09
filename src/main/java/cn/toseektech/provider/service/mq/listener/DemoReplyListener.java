package cn.toseektech.provider.service.mq.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQReplyListener;
import org.springframework.stereotype.Component;

import cn.toseektech.provider.dto.UserDto;
import cn.toseektech.provider.service.constants.RocketmqTags;
import cn.toseektech.provider.service.constants.RocketmqTopics;

@Component
@RocketMQMessageListener(consumerGroup = "consumer-provider-reply", 
topic = RocketmqTopics.TOPIC_REPLY, selectorType = SelectorType.TAG, selectorExpression = RocketmqTags.TAG_REPLY)
public class DemoReplyListener implements RocketMQReplyListener<UserDto,UserDto> {

	@Override
	public UserDto onMessage(UserDto message) {
		System.out.println("DemoReplyListener收到消息："+message);
		return message;
	}

	
}
