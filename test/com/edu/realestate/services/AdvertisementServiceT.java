package com.edu.realestate.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.edu.realestate.config.TestConfig;
import com.edu.realestate.model.AdStatus;
import com.edu.realestate.model.Advertisement;
import com.edu.realestate.model.Advertiser;
import com.edu.realestate.model.Apartment;
import com.edu.realestate.model.City;
import com.edu.realestate.model.CommercialProperty;
import com.edu.realestate.model.OtherProperty;
import com.edu.realestate.model.TransactionType;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class AdvertisementServiceT extends AbstractT {

	// SUT
	@Autowired
	private AdvertisementService service;

//	private static ClassPathXmlApplicationContext ctx;
//	
//	@BeforeClass
//	public static void setupClass() throws Exception {
//		ctx = new ClassPathXmlApplicationContext("app-context-test.xml");
//	}
//	
//	@AfterClass
//	public static void tearDown() {
//		if (ctx!=null) ctx.close();
//	}

	@Before
	public void setup() throws Exception {
//		ApplicationContext ctx= new
//		ClassPathXmlApplicationContext("app-context-test.xml");
//		service = (UserService) ctx.getBean("userService");
//		service = ctx.getBean(AdvertisementService.class);

		cleanInsert();
	}

	@Test
	public void it_should_find_advertisement_by_city() throws Exception {

		// arrange
		int cityId = 30473;

		// act
		List<Advertisement> advertisement = service.findAdvertisementByCity(cityId);

		// assert
		assertThat(advertisement).isNotNull().hasSize(3).doesNotContainNull();

		Advertisement ad1 = advertisement.get(0);
		assertThat(ad1).extracting(Advertisement::getId, Advertisement::getAdNumber, Advertisement::getRefusedComment,
				Advertisement::getReleaseDate, Advertisement::getStatus, Advertisement::getTitle,
				Advertisement::getTransactionType).contains(20249, "19-00012", null, LocalDate.of(2019, 02, 28),
						AdStatus.Validated, "Vente Local commercial 195  m², Paris 14 ème (75014)",
						TransactionType.Sale);
		assertThat(ad1.getDescription()).isNotNull().hasSize(112).startsWith("Sur 2 niveaux, EVOLIS")
				.endsWith("Loi Carrez et affectation juridique en cours de détermination.");

		assertThat(ad1.getAdvertiser()).isNotNull().isExactlyInstanceOf(Advertiser.class)
				.extracting(Advertiser::getUsername).isEqualTo("jmd@yahoo.fr");

		assertThat(ad1.getRealEstate()).isNotNull().isInstanceOf(CommercialProperty.class)
				.extracting("id", "area", "price", "available").containsExactly(20355, 195, 995000, true);

		Advertisement ad2 = advertisement.get(1);
		assertThat(ad2).extracting(Advertisement::getId, Advertisement::getAdNumber, Advertisement::getRefusedComment,
				Advertisement::getReleaseDate, Advertisement::getStatus, Advertisement::getTitle,
				Advertisement::getTransactionType).contains(20479, "19-00242", null, LocalDate.of(2019, 02, 28),
						AdStatus.Validated, "Vente Appartement 1  pièce 12  m², Paris 14 ème (75014)",
						TransactionType.Sale);
		assertThat(ad2.getDescription()).isNotNull().hasSize(123).startsWith("Dans le quartier du « Montparnasse »")
				.endsWith("veuillez consulter notre site internet immonotairesencheres.");

		assertThat(ad2.getAdvertiser()).isNotNull().isExactlyInstanceOf(Advertiser.class)
				.extracting(Advertiser::getUsername).isEqualTo("jmd@yahoo.fr");

		assertThat(ad2.getRealEstate()).isNotNull().isInstanceOf(Apartment.class)
				.extracting("id", "area", "price", "available").containsExactly(20585, 12, 73500, true);

		Advertisement ad3 = advertisement.get(2);
		assertThat(ad3).extracting(Advertisement::getId, Advertisement::getAdNumber, Advertisement::getRefusedComment,
				Advertisement::getReleaseDate, Advertisement::getStatus, Advertisement::getTitle,
				Advertisement::getTransactionType).contains(20807, "19-00570", null, LocalDate.of(2019, 02, 28),
						AdStatus.Validated, "Vente Appartement 2  pièces 40  m², Paris 14 ème (75014)",
						TransactionType.Sale);
		assertThat(ad3.getDescription()).isNotNull().hasSize(104).startsWith("Au sein d'un bel immeuble ancien, ce")
				.endsWith("Une cave complète le lot.");

		assertThat(ad3.getAdvertiser()).isNotNull().isExactlyInstanceOf(Advertiser.class)
				.extracting(Advertiser::getUsername).isEqualTo("jmd@yahoo.fr");

		assertThat(ad3.getRealEstate()).isNotNull().isInstanceOf(Apartment.class)
				.extracting("id", "area", "price", "available").containsExactly(20913, 40, 410000, true);

	}

	@Test
	public void it_should_place_advertisement() throws Exception {
		// Arrange
		Advertisement ad = new Advertisement();
		ad.setTitle("Vend Elysée");
		ad.setTransactionType(TransactionType.Sale);
		ad.setDescription("Vend Elysée pour comblé la dette de l'état");

		Advertiser advertiser = new Advertiser();
		advertiser.setUsername("jmd@yahoo.fr");
		ad.setAdvertiser(advertiser);

		OtherProperty re = new OtherProperty();
		re.setArea((short)11179);
		re.setAvailable(true);
		re.setPrice(2000000000);

		City city = new City();
		city.setId(1);

		re.setCity(city);

		ad.setRealEstate(re);

		service.placeAdvertisement(ad);

		IDataSet actualDataset = getConnection().createDataSet();
		IDataSet expectedDataset = getReplacement(
				loadXmlDataSet("src/test/resources/datasets/user_service/create_ad.xml"));

		// Advertisement
		ITable actualTableAd = actualDataset.getTable("advertisement");
		ITable expectedTableAd = expectedDataset.getTable("advertisement");

		String[] ignoredColsAd = { "id", "release_date", "ad_number", "real_estate_id", "refused_comment" };
		Assertion.assertEqualsIgnoreCols(actualTableAd, expectedTableAd, ignoredColsAd);

		// RealEstate
		ITable actualTableRe = actualDataset.getTable("real_estate");
		ITable expectedTableRe = expectedDataset.getTable("real_estate");

		String[] ignoredColsRe = { "id" };
		Assertion.assertEqualsIgnoreCols(actualTableRe, expectedTableRe, ignoredColsRe);

		// OtherProperty
		ITable actualTableOp = actualDataset.getTable("other_property");
		ITable expectedTableOp = expectedDataset.getTable("other_property");

		String[] ignoredColsOp = { "id" };
		Assertion.assertEqualsIgnoreCols(actualTableOp, expectedTableOp, ignoredColsOp);

	}

}
