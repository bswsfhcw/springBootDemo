package springBootDemo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import springBootDemo.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{

	@Query("FROM UserInfo u WHERE u.id=:id")
	UserInfo findUserInfoById(@Param("id")long id);
}
