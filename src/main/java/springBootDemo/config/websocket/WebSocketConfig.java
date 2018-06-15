package springBootDemo.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 该方式只适用于通过 jar 包直接运行项目的情况。
 * 
 * @author bswsfhcw
 */
@Configuration
public class WebSocketConfig {

	/**
	 * 影响junit测试，暂未找出原因
	 * 
	 * @return
	 */
	 /*@Bean
	 public ServerEndpointExporter serverEndpointExporter() {
		 return new ServerEndpointExporter();
	 }*/
}
