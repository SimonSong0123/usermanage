package cn.itcast.usermanage.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import cn.itcast.usermanage.pojo.User;
import cn.itcast.usermanage.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/applicationContext*.xml")
public class UserMapperTest {
	
	@Autowired
	private UserService userService;

	@Test
	public void testQueryById() {
		User user = userService.queryById(3L);
		System.out.println(user);
	}

}
