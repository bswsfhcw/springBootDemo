package springBootDemo.jdbc;

import springBootDemo.entity.UserInfo;

public interface UserInfoDao {
	
	public int insert(UserInfo userInfo);

	public int deleteById(Integer id);

	public int update(UserInfo userInfo);

	public UserInfo getById(Integer id);
}
