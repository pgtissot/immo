package com.edu.realestate.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	UserServiceAuthenticateUser.class,
	ReferenceServiceT.class,
	AdvertisementServiceT.class,
	UserServiceRegisterUser.class
})
public class AllServiceT {

}
