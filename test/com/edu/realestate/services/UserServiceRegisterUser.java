package com.edu.realestate.services;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.edu.realestate.config.TestConfig;
import com.edu.realestate.model.Advertiser;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes=TestConfig.class)
public class UserServiceRegisterUser extends AbstractT {

	@Autowired
	private UserService service;

//	private static ClassPathXmlApplicationContext ctx;

	@BeforeClass
	public static void setupClass() throws Exception {
//		ctx = new ClassPathXmlApplicationContext("app-context-test.xml");
	}

	@AfterClass
	public static void tearDown() {
//		if (ctx != null)
//			ctx.close();
	}

	@Before
	public void setup() throws Exception {
//		service = ctx.getBean(UserService.class);
		cleanInsert();
	}

	@Test
	public void it_should_register_roger_rabbit() throws Exception {

		// Arrange
		Advertiser adv = new Advertiser();
		adv.setUsername("roger@gmail.com");
		adv.setPassword("secret");
		adv.setFirstName("Roger");
		adv.setLastName("Rabbit");
		adv.setTitle("M");
		adv.setPhone("0677889966");

		// Act
		service.register(adv);

		// Assert
		IDataSet actualDataset = getConnection().createDataSet();
		ITable actualTable = actualDataset.getTable("user_data");

		IDataSet expectedDataset = getReplacement(
				loadXmlDataSet("src/test/resources/datasets/user_service/register_roger_rabbit.xml"));
		ITable expectedTable = expectedDataset.getTable("user_data");

//		ITable actualFilter = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());

		Assertion.assertEquals(expectedTable, actualTable);

	}

}
