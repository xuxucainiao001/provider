package cn.toseektech.provider.configuration;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 * @author xuxu
 *
 */
@Configuration
public class ThreadPoolTaskExecutorConfiguration {
	
	@Bean(name = "provider-common-excutor")
	public ThreadPoolTaskExecutor  commenThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor  executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(10);
		executor.setKeepAliveSeconds(600);
		executor.setQueueCapacity(1024);
		executor.setThreadFactory(new DefaultThreadFactory("provider-common-thread"));
		executor.setWaitForTasksToCompleteOnShutdown(true);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}
		
}

/**
 * 默认的线程工厂
 * @author xuxu
 *
 */
class DefaultThreadFactory implements ThreadFactory{
	
	private String threadName;
	
	public DefaultThreadFactory(String threadName) {
		this.threadName=threadName;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t=new Thread(r);
		t.setName(this.threadName);
		return t;
	}
	
}
