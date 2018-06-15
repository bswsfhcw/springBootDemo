package springBootDemo.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER_INFO", schema = "BSWSF_HCW")
public class UserInfo implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="USER_INFO_SEQ")
	 @SequenceGenerator(name="USER_INFO_SEQ",sequenceName="USERT_INFO_ID_SEQ",allocationSize=1,initialValue=1)
//	@GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID 我觉得，数据库不大的时候，使用UUID比较合适
//    @GeneratedValue(generator="idGenerator")
	@Column(name = "ID")
	private Long id;// null

	@Column(name = "USERNAME")
	private String username;// null

	@Column(name = "PASSWORD")
	private String password;// null

	@Column(name = "BIRTHDAY")
	private Date birthday;// null
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", password=" + password + ", birthday=" + birthday
				+ "]";
	}
}
