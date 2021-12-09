package cn.toseektech.provider;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.TimeZone;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.toseektech.common.configuration.annotations.EnableSecurityPermitAll;
import cn.toseektech.common.configuration.annotations.EnableToSeekTechDataSource;
import cn.toseektech.common.configuration.annotations.EnableToSeekTechExceptionHandler;
import cn.toseektech.common.configuration.annotations.EnableToSeekTechJedis;
import cn.toseektech.common.configuration.annotations.EnableToSeekTechRedission;
import cn.toseektech.common.utils.SpringUtils;
import cn.toseektech.provider.service.mq.send.DemoSender;
import cn.toseektech.translation.annotation.EnableToSeekTechTranslation;

@EnableToSeekTechJedis
@EnableDiscoveryClient
@EnableSecurityPermitAll
@EnableToSeekTechRedission
@EnableToSeekTechDataSource
@EnableToSeekTechTranslation
@EnableToSeekTechExceptionHandler
@MapperScan(basePackages = { "cn.toseektech.provider.dao"})
@EnableFeignClients(basePackages = { "cn.toseektech.remote.**" })
@SpringBootApplication(scanBasePackages = {"cn.toseektech.provider.**","cn.toseektech.remote.**"})
public class ProviderApplication {

	private static final Logger logger = LoggerFactory.getLogger(ProviderApplication.class);

	public static void main(String[] args) {
		logger.info("=====provider 启动开始！======");
		ConfigurableApplicationContext context = SpringApplication.run(ProviderApplication.class, args);
		SpringUtils.setApplicationContext(context);
		logger.info("=====provider 启动结束！======");
		
		DemoSender demoSender=context.getBean(DemoSender.class);
		demoSender.sendNormalMessage();
		demoSender.sendReplyMessage();
	}


	/**
	 * jackson统一处理规则
	 * 
	 * @param env
	 * @return
	 */
	@Bean
	public ObjectMapper objectMapper(Environment env) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat());
		objectMapper.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Asia/Shanghai")));
		return objectMapper;
	}
    
	/**
	 * 开启负载均衡的注解
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate  restTemplate() {
	    return new RestTemplate();
	}
	


}
