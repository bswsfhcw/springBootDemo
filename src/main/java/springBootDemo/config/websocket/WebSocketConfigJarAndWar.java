package springBootDemo.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import springBootDemo.websocket.WebSocketServerJarAndWar;
/**
 * 该方式适用于 jar 包方式运行和 war 方式运行。
 * @author bswsfhcw
 *
 */
@Configuration  
@EnableWebSocket  
public class WebSocketConfigJarAndWar implements WebSocketConfigurer {  
	
    @Override  
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {  
        registry.addHandler(webSocketServerJarAndWar(), "/webSocketServerJarANdWar/*"); 
    }  
  
    @Bean
    public WebSocketHandler webSocketServerJarAndWar() {  
        return new WebSocketServerJarAndWar();  
    }  
} 