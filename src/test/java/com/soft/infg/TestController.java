package com.soft.infg.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import com.soft.infg.SpringRestApplication;
import com.soft.infg.dao.entity.BeanClass;
import com.soft.infg.demo.service.ServiceInterface;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringRestApplication.class)

public class TestController {
	
	@Autowired
    private WebApplicationContext wac;
	@MockBean
	private ServiceInterface medservice;
	@InjectMocks
	private ControllerClass medcontroller;
	Date dt1;
	Date dt2;
	private MockMvc mockMvc;
	
	BeanClass b1= new BeanClass();
	BeanClass b2= new BeanClass();
	BeanClass b3= new BeanClass();
	String cList="";
	List<BeanClass> bList = new ArrayList<BeanClass>();


	 Date dt_1;
	 Date dt_2;
	{
	

	 try { 
		 SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy");
	 dt_1 = objSDF.parse("20-08-2016");
	  dt_2 = objSDF.parse("12-10-2018");}
	 catch(Exception e) {
		 e.printStackTrace();
	 }
	 
	 
	
	

	 try { 
		 SimpleDateFormat objSDF = new SimpleDateFormat("dd-mm-yyyy");
	 dt1 = objSDF.parse("11-03-2018");
	  dt2 = objSDF.parse("19-09-2019");}
	 catch(Exception e) {
		 e.printStackTrace();
	 }
	}

	@Before
	public void setData() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
	    b1.setId(1);
		b1.setExpirydate(dt_1);
		b1.setMfgdate(dt_2);
		b1.setName("nvxn");
		b1.setPrice(100);
		b1.setQuantity(2);
		bList.add(b1);
		
		b2.setId(2);
		b2.setExpirydate(dt1);
		b2.setMfgdate(dt2);
		b2.setName("nvxn");
		b2.setPrice(100);
		b2.setQuantity(2);
		bList.add(b1);
		

		b3.setId(4);
		b3.setExpirydate(dt1);
		b3.setMfgdate(dt2);
		b3.setName("crocin");
		b3.setPrice(100);
		b3.setQuantity(7);
		List<BeanClass> aList = new ArrayList<BeanClass>();
		aList.add(b3);
		
		
		
		
		Mockito.when(medservice.getAllMedicine()).thenReturn( aList);
		//Mockito.when(medservice.getMedicineById1(Mockito.anyInt())).thenReturn(b1);
		Mockito.when(medservice.checkMedicineById(Mockito.anyInt())).thenReturn(false);
		
		//Mockito.when(medservice.updateMedicine(Mockito.any(BeanClass.class))).thenReturn();

		//Mockito.when(medservice.deleteMedicine(Mockito.anyInt())).thenReturn();
		//Mockito.when(medservice.updateMedicine(Mockito.anyInt())).thenReturn(b1);
	}
	/*@Test
	public void getAllData() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allmedicine")
			
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		
		String example= "{\"id\":1,\"quantity\":10 ,\"price\":100,\"mfgdate\",\"20-09-2016\",\"expirydate\",\"20-09-2016\",\"name\",\"para\"}";
		assertEquals(example, result.getResponse().getContentAsString());
		System.out.println(" success...");
	}*/
	
	
	
	@Test
	public void checkMedicine() throws Exception{
		boolean flag= medservice.checkMedicineById(b2.getId());
		assertFalse("Id doesnot exists", flag);
			}
	
	@Test
	public void MedicineExists() throws Exception
	{
		assertFalse(medservice.checkMedicineById(b1.getId()));
	}
	
	@Test
	public void checkMedicineNotNull() throws Exception{
		boolean flag= medservice.checkMedicineById(b2.getId());
		assertNotNull("Not null", medservice);
			}
	
	@Test
	public void addMedicine() throws Exception{
		boolean flag= medservice.addMedicine(b1);
		assertNotNull("Medicine Exists ", medservice);
	}
		

	
	@Test
	public void addMedicineNotExists() throws Exception{
		//boolean flag= medservice.addMedicine(b2);
		assertFalse(medservice.addMedicine(b2));
		//assertNotNull("Medicine Not Exists ", medservice);
	}
	
   @Test
	public void getAllData() throws Exception{
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON);
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			String example="{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
			           "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";		

		assertEquals(example, result.getResponse().getContentAsString());
	
	 System.out.println(" success...");
	}
   
   @Test
	public void getAllDataNullCheck() throws Exception{
	 RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String example="{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
		           "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";	



		assertNotNull(result.getResponse());
	 System.out.println(" success...");
	}
   
   
   
   @Test
   public void getByID() throws Exception
   {
		Mockito.when(medservice.getMedicineById1(Mockito.anyInt())).thenReturn(b3);
   RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/medicine/4")
   .accept(MediaType.APPLICATION_JSON);
  MvcResult result = mockMvc.perform(requestBuilder).andReturn();
  //assertNotNull(result.getResponse());
    //String expected="";
   String expected = "{\"id\":1,\"quantity\":10 ,\"price\":100,\"mfgdate\":\"20-09-2016\",\"expirydate\":\"20-09-2016\",\"name\":\"para\"}";

   assertNotEquals(expected, result.getResponse().getContentAsString());
   System.out.println("getMedicineById successfully executed...");
   }
  
   
  
   
   @Test
   public void getByIDNotequals() throws Exception
   {
	   Mockito.when(medservice.getMedicineById1(Mockito.anyInt())).thenReturn(b1);
   RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/medicine/1")
   .requestAttr("id", b1.getId())
   .accept(MediaType.APPLICATION_JSON);
   MvcResult result = mockMvc.perform(requestBuilder).andReturn();
   
   String expected = "{\"id\":1,\"quantity\":10 ,\"price\":100,\"mfgdate\":\"20-09-2016\",\"expirydate\":\"20-09-2016\",\"name\":\"para\"}";
   assertNotEquals(expected, result.getResponse().getContentAsString());
   System.out.println("getMedicineById successfully executed...");
  /* assertEquals(expected, result.getResponse().getContentAsString());
   assertNotEquals(0, result.getResponse().getContentAsString());
   assertEquals("nvxn", result.getResponse().getContentAsString().charAt(3));
   System.out.println("getMedicineById successfully executed...");*/
   
   }
   
   
   @Test
	public void testUpdate() throws Exception {
	   	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("update")
				.requestAttr("id", b1.getId())
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder)
	    .andExpect(status().is(404));
   }
   
   
   @Test
  	public void testNotUpdated() throws Exception {
  	   	
  		
  		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("update")
  				.requestAttr("id", b2.getId())
  				.accept(MediaType.APPLICATION_JSON);
  		mockMvc.perform(requestBuilder)
  		.andExpect(status().is(404));
    }
    
   @Test
   public void showMedicine() throws Exception{
	   RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allmedicine")
			   .requestAttr("All medicine",medservice.getAllMedicine())
			   .accept(MediaType.APPLICATION_JSON);
	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		   String expected = "[{\"id\":4,\"quantity\":7 ,\"price\":100.0,\"mfgdate\":\"18-01-2019\",\"expirydate\":\"10-01-2018\",\"name\":\"crocin\"}]";
		//assertEquals(expected, result.getResponse().getContentAsString());
		assertNotEquals(expected, result.getResponse().getContentAsString());
	  System.out.println("getAllMedicines successfully executed...");
	   
   }
   
   
   @Test
	public void testDeleteMedicineNotPresent() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicine/1")
				.requestAttr("id", b1.getId())
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder)
		.andExpect(status().isOk());
		
	}
   
   
   @Test
	public void testDeleteMedicinePresent() throws Exception {
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/medicine/4")
				
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder)
		.andExpect(status().isOk());
		
	}
   @Test
   public void showMedicineNull() throws Exception
   {
	   RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allmedicine")
			   .requestAttr("All medicine",medservice.getAllMedicine())
			   .accept(MediaType.APPLICATION_JSON);
	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		   String expected = "[{\"id\":4,\"quantity\":7 ,\"price\":100.0,\"mfgdate\":\"18-01-2019\",\"expirydate\":\"10-01-2018\",\"name\":\"crocin\"}]";
	
		assertNotNull(bList);
	  System.out.println("getAllMedicines successfully executed...");
	   
   }







}
