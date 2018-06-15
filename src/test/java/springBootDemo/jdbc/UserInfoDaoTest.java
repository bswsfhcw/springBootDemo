package springBootDemo.jdbc;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springBootDemo.SpringbootApplication;
import springBootDemo.entity.UserInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootApplication.class)
public class UserInfoDaoTest {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserInfoDao userInfoDao;
	@Test
    public void testInsert() {
		LOGGER.info("testInsert");
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setUsername("张三");
        userInfo.setPassword("zhangsan");
        userInfo.setBirthday(new Date());
        int result = this.userInfoDao.insert(userInfo);
        System.out.println(result);
    }
    
    @Test
    public void testGetById() {
        UserInfo userInfo = this.userInfoDao.getById(1);
        System.out.println(userInfo.getUsername());
    }
    
    @Test
    public void testUpdate() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setPassword("zhangsan123");
        this.userInfoDao.update(userInfo);
    }
    
    @Test
    public void testDeleteById() {
        int result = this.userInfoDao.deleteById(1);
        System.out.println(result);
    }
}
