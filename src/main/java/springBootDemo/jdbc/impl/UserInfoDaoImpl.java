package springBootDemo.jdbc.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import springBootDemo.entity.UserInfo;
import springBootDemo.jdbc.UserInfoDao;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insert(UserInfo userInfo) {
		String sql = "insert into user_info(id,username,password,birthday) values(?,?,?,?)";
		return this.jdbcTemplate.update(sql, userInfo.getId(), userInfo.getUsername(), userInfo.getPassword(),
				userInfo.getBirthday());
	}

	@Override
	public int deleteById(Integer id) {
		String sql = "delete from user_info where id = ?";
		return this.jdbcTemplate.update(sql, id);
	}

	@Override
	public int update(UserInfo userInfo) {
		String sql = "update user_info set password = ? where id = ?";
		return this.jdbcTemplate.update(sql, userInfo.getPassword(), userInfo.getId());
	}

	@Override
	public UserInfo getById(Integer id) {
		String sql = "select * from user_info where id = ?";
		return this.jdbcTemplate.queryForObject(sql, new RowMapper<UserInfo>() {
			@Override
			public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserInfo userInfo = new UserInfo();
				userInfo.setId(rs.getLong("id"));
				userInfo.setUsername(rs.getString("username"));
				userInfo.setPassword(rs.getString("password"));
				userInfo.setBirthday(rs.getDate("birthday"));
				return userInfo;
			}
		}, id);
	}

}
