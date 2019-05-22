package com.newcoder.myWenda;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.newcoder.myWenda.dao.UserDAO;
import com.newcoder.myWenda.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Sql("/init-schema.sql")
public class InitDataBaseTests {
	@Autowired
	UserDAO userDAO;

	@Test
	public void initDatabase() {
		Random random = new Random();
		for(int i=0;i<11;++i) {
			User user = new User();
			user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
			user.setName(String.format("USER%d", i));
			user.setPassword("");
			user.setSalt("");
//			userDAO.addUser(user);
			user.setId(i+20);
			user.setPassword("xx");
			userDAO.updatePassword(user);
		}
		Assert.assertEquals("xx", userDAO.selectById(21).getPassword());
		userDAO.deleteById(21);
		Assert.assertNull(userDAO.selectById(21));
	}

}
