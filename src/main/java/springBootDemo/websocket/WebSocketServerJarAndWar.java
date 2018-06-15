package springBootDemo.websocket;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
/**
 * 该方式适用于 jar 包方式运行和 war 方式运行。
 * @author bswsfhcw
 *
 */
public class WebSocketServerJarAndWar extends TextWebSocketHandler {
	private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketServerJarAndWar.class);
    private static final Map<WebSocketSession, String> connections = new ConcurrentHashMap<>();

    private static String getDatetime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
    
    /**
     * 建立连接
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String uri = session.getUri().toString();
        String userName = uri.substring(uri.lastIndexOf("/") + 1);
        String nickname = URLDecoder.decode(userName, "utf-8");
    	LOGGER.info("WebSocketServerJarAndWar afterConnectionEstablished:"+userName+","+session.getId());
        connections.put(session, nickname);
        String message = String.format("* %s %s", nickname, "加入聊天！");
        broadcast(new TextMessage(message));
    }

    /**
     * 断开连接
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String nickname = connections.remove(session);
        String message = String.format("* %s %s", nickname, "退出聊天！");
        broadcast(new TextMessage(message));
    }

    /**
     * 处理消息
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	LOGGER.info("WebSocketServerJarAndWar handleTextMessage:"+message);
        String msg = "【" + connections.get(session) + "】" + getDatetime(new Date()) + " : " + message.getPayload();
        broadcast(new TextMessage(msg));
    }

    private static void broadcast(TextMessage msg) {
        // 广播形式发送消息
        for (WebSocketSession session : connections.keySet()) {
            try {
                synchronized (session) {
                    session.sendMessage(msg);
                }
            } catch (Exception e) {
            	LOGGER.error("WebSocketServerJarAndWar broadcast error:"+e.getMessage());
                connections.remove(session);
                try {
                    session.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                String message = String.format("* %s %s", connections.get(session), "断开连接");
                broadcast(new TextMessage(message));
            }
        }
    }
}