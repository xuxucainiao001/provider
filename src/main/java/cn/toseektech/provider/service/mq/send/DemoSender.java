package cn.toseektech.provider.service.mq.send;

import javax.annotation.Resource;

import org.apache.rocketmq.spring.core.RocketMQLocalRequestCallback;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.toseektech.provider.dto.UserDto;
import cn.toseektech.provider.service.constants.RocketmqTags;
import cn.toseektech.provider.service.constants.RocketmqTopics;

@Component
public class DemoSender {
	
	
	private Logger logger = LoggerFactory.getLogger(DemoSender.class);
	
	@Resource
	private RocketMQTemplate rocketMQTemplate;
	
	
	public void sendNormalMessage() {
		UserDto user = new UserDto();
		user.setId(1L);
		user.setUsername("zhangsan");
		rocketMQTemplate.convertAndSend(RocketmqTopics.TOPIC_NORMAL+":"+RocketmqTags.TAG_NORMAL, user);
	}
	
	
	public void sendReplyMessage() {
		UserDto user = new UserDto();
		user.setId(2L);
		user.setUsername("lisi");
		rocketMQTemplate.sendAndReceive(RocketmqTopics.TOPIC_REPLY+":"+RocketmqTags.TAG_REPLY, user,new RocketMQLocalRequestCallback<UserDto>() {

			@Override
			public void onSuccess(UserDto message) {
				System.out.println(message);
				
			}

			@Override
			public void onException(Throwable e) {
				logger.error("等待消息返回发生异常：",e);
			}
			
		});
	}
	

}
