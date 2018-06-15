package springBootDemo.websocket;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.OnClose;  
import javax.websocket.OnError;  
import javax.websocket.OnMessage;  
import javax.websocket.OnOpen;  
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;  
/**
 * 该方式只适用于通过 jar 包直接运行项目的情况。
 * @author bswsfhcw
 *
 */
@ServerEndpoint(value = "/webSocketServer/{userName}")
@Component
public class WebSocketServer {
	private final static Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);
    private static final Set<WebSocketServer> connections = new CopyOnWriteArraySet<>();

    private String nickname;
    private Session session;

    private static String getDatetime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    @OnOpen
    public void start(@PathParam("userName") String userName, Session session) {
    	LOGGER.info("WebSocketServer start:"+userName+","+session.getId());
        this.nickname = userName;
        this.session = session;
        connections.add(this);
        String message = String.format("* %s %s", nickname, "加入聊天！");
        broadcast(message);
    }

    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("* %s %s", nickname, "退出聊天！");
        broadcast(message);
    }

    @OnMessage
    public void pushMsg(String message) {
    	LOGGER.info("WebSocketServer pushMsg:"+message);
        broadcast("【" + this.nickname + "】" + getDatetime(new Date()) + " : " + message);
    }

    @OnError
    public void onError(Throwable t) throws Throwable {

    }

    private static void broadcast(String msg) {
        // 广播形式发送消息
        for (WebSocketServer client : connections) {
            try {
                synchronized (client) {
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
            	LOGGER.error("WebSocketServer broadcast error:"+e.getMessage());
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    e.printStackTrace();
                }
                String message = String.format("* %s %s", client.nickname, "断开连接");
                broadcast(message);
            }
        }
    }
}