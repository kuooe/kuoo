package kr.kuooe.comm.comp;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ServiceStop implements ApplicationListener<ContextClosedEvent> {
	
	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		log.info("================================================================================");
		log.info("============================= E-Mobile Service Stop ============================");
		log.info("================================================================================");
	}
}
