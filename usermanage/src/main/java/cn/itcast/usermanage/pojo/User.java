package cn.itcast.usermanage.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name="tb_user")
public class User{

	@Field
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    // 用户名
	@Field()
	@Column(name="user_name")
    private String userName;

    // 密码
    private String password;

    // 姓名
	@Field()
    private String name;

    // 年龄
	@Field()
    private Integer age;

    // 性别，1男性，2女性
	@Field()
    private Integer sex;

    // 出生日期
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Field()
    private Date birthday;

    // 创建时间
    @Field()
    private Date created;

    // 更新时间
    @Field()
    private Date updated;
	
	// 备注
    @Field()
	private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", name=" + name + ", age=" + age + ", sex=" + sex
				+ ", birthday=" + birthday + ", created=" + created
				+ ", updated=" + updated + ", note=" + note + "]";
	}
}
