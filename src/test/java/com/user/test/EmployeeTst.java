package com.user.test;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.pojos.EmpDetails;
import com.pojos.Searchpage;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class EmployeeTst{
	

	@Test
	public void generatePojo() {
		
		EmpDetails emp = new EmpDetails();
		emp.setFirstname("ranjan");
		emp.setEmail("abc@email.com");
		emp.setId(34);
		emp.setAvater("profile pic1");
		emp.setLastnmae("dash");
		
		EmpDetails emp1 = new EmpDetails();
		emp1.setFirstname("manranjan");
		emp1.setEmail("ecabc@email.com");
		emp1.setId(24);
		emp1.setAvater("profile pic2");
		emp1.setLastnmae("rath");
		
		Searchpage ser= new Searchpage();
		ser.setTotal(2);
		ser.setName("nakul");
		ser.setPer_page(45);
		ser.setPage(3);
		ser.setData(Arrays.asList(emp,emp1));
		
		System.out.println(ser);
		
		
	}
}
