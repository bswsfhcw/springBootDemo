package springBootDemo.jpa;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import springBootDemo.SpringbootApplication;
import springBootDemo.config.websocket.WebSocketConfig;
import springBootDemo.entity.UserInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
@ContextConfiguration(classes=WebSocketConfig.class) //加载Configuration配置类
public class UserInfoJpaTest {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Test
	public void testInsert() {
		LOGGER.info("testInsert");
		for (int i = 0; i < 10; i++) {
			UserInfo userInfo = new UserInfo();
			// userInfo.setId(1L);
			userInfo.setUsername("张三");
			userInfo.setPassword("zhangsan");
			userInfo.setBirthday(new Date());
			UserInfo result = this.userInfoRepository.save(userInfo);
			System.out.println(result);
		}
	}

	@Test
	public void testGetById() {
		UserInfo userInfo = this.userInfoRepository.findUserInfoById(1L);
		System.out.println(userInfo.toString());
	}

	@Test
	public void testUpdate() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(1L);
		userInfo.setPassword("zhangsan123");
		UserInfo result = this.userInfoRepository.save(userInfo);
		System.out.println(result);
	}

	@Test
	public void testDeleteById() {
		this.userInfoRepository.deleteById(1L);
	}
}
