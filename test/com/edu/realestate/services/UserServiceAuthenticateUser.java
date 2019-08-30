package com.edu.realestate.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.edu.realestate.config.TestConfig;
import com.edu.realestate.exceptions.AuthenticationException;
import com.edu.realestate.model.Advertiser;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=TestConfig.class)
public class UserServiceAuthenticateUser extends AbstractT {

	// SUT (System Under Test)
	@Autowired
	private UserService service;

//	private static ClassPathXmlApplicationContext ctx;
//
//	@BeforeClass
//	public static void setupClass() throws Exception {
//		ctx = new ClassPathXmlApplicationContext("app-context-test.xml");
//	}
//
//	@AfterClass
//	public static void tearDown() {
//		if (ctx != null)
//			ctx.close();
//	}

	@Before
	public void setup() throws Exception {
		// ApplicationContext ctx= new
		// ClassPathXmlApplicationContext("app-context-test.xml");
//		service = (UserService) ctx.getBean("userService");
//		service = ctx.getBean(UserService.class);

		cleanInsert();
	}

	@Test
	public void it_should_authenticate_jean_marc_dupuis() throws Exception {

		// ACT
		Advertiser ad = (Advertiser)service.authenticate("jmd@yahoo.fr", "pomme");

		// Assert
		assertThat(ad).isNotNull()
				.extracting(Advertiser::getUsername, Advertiser::getFirstName, Advertiser::getLastName,
						Advertiser::getTitle, Advertiser::getPhone, Advertiser::getPassword)
				.containsExactly("jmd@yahoo.fr", "Jean-Marc", "Dupuis", "M", "0677889912", "pomme");
	}

	@Test
	public void it_should_not_autenticate_when_user_is_unknown() throws Exception {

		// ACT
		ThrowingCallable callable = () -> service.authenticate("roger@gmail.com", "secret");

		// Assert
		assertThatExceptionOfType(AuthenticationException.class).isThrownBy(callable);

	}

	@Test
	public void it_should_not_autenticate_when_password_is_bad() throws Exception {

		// ACT
		ThrowingCallable callable = () -> service.authenticate("jmd@yahoo.fr", "secret");

		// Assert
		assertThatExceptionOfType(AuthenticationException.class).isThrownBy(callable);

	}

	@Test
	public void it_should_not_autenticate_when_password_is_null() throws Exception {

		// ACT
		ThrowingCallable callable = () -> service.authenticate(null, null);

		// Assert
		assertThatExceptionOfType(AuthenticationException.class).isThrownBy(callable);

	}

	@Test
	public void it_should_not_autenticate_when_password_is_empty() throws Exception {

		// ACT
		ThrowingCallable callable = () -> service.authenticate("", "");

		// Assert
		assertThatExceptionOfType(AuthenticationException.class).isThrownBy(callable);
	}

	@Test
	public void it_should_not_autenticate_when_user_exist_and_password_is_null() throws Exception {

		// ACT
		ThrowingCallable callable = () -> service.authenticate("jmd@yahoo.fr", null);

		// Assert
		assertThatExceptionOfType(AuthenticationException.class).isThrownBy(callable);
	}

	@Test
	public void it_should_not_autenticate_when_user_exist_and_password_is_empty() throws Exception {

		// ACT
		ThrowingCallable callable = () -> service.authenticate("jmd@yahoo.fr", "");

		// Assert
		assertThatExceptionOfType(AuthenticationException.class).isThrownBy(callable);
	}

}
