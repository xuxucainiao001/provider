package cn.toseektech.provider.service.mq.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import cn.toseektech.provider.dto.UserDto;
import cn.toseektech.provider.service.constants.RocketmqTags;
import cn.toseektech.provider.service.constants.RocketmqTopics;

@Component
@RocketMQMessageListener(consumerGroup = "consumer-provider-normol", 
topic = RocketmqTopics.TOPIC_NORMAL, selectorType = SelectorType.TAG, selectorExpression = RocketmqTags.TAG_NORMAL)
public class DemoListener implements RocketMQListener<UserDto> {

	@Override
	public void onMessage(UserDto message) {

		System.out.println("DemoListener接受到消息：" + message);

	}

}
