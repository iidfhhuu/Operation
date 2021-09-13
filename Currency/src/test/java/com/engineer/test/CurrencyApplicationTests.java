package com.engineer.test;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.engineer.test.Controller.CurrencyTest;
import com.engineer.test.Repository.CurrencyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CurrencyApplicationTests {
	@Autowired
	private CurrencyRepository currencyRepository; // 加入MemberRepository
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void contextLoads6() throws Exception {
		Test6();
	}
	
	@Test
	void contextLoads5() throws Exception {
		Test5();
	}
	
	@Test
	void contextLoads4() throws Exception {
		Test4();
	}
	
	@Test
	void contextLoads3() throws Exception {
		Test3();
	}
	
	@Test
	void contextLoads2() throws Exception {
		Test2();
	}


	@Test
	void contextLoads1() throws Exception {
		Test1();
	}


	// 查詢幣別對應表資料
	public void Test1() throws Exception {
		System.out.println("==============單元測試開始=============");
		System.out.println("==============測試呼叫查詢幣別對應表資料 Start=============");
		String uri = "/Currency";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = result.getResponse().getStatus();
		String conte = result.getResponse().getContentAsString();

		Assert.assertEquals("錯誤", 200, status);
		System.out.println("==============測試呼叫查詢幣別對應表資料 結果=============");
		System.out.println("狀態:" + status + ",內容:" + conte);
		System.out.println("==============測試呼叫查詢幣別對應表資料 End=============");
	}

	// 測試呼叫新增幣別對應表資料
	public void Test2() throws Exception {
		CurrencyTest currency = new CurrencyTest();
		currency.setCode("WWW");
		currency.setSymbol("QM");
		currency.setRate("99,000.9438");
		currency.setDescription("British Pound SGWW");
		currency.setRateFloat(99000);
		currency.setUpdateDate("2021-09-14 08:52:00.000");

		System.out.println("==============單元測試開始=============");
		System.out.println("==============測試呼叫新增幣別對應表資料 Start=============");
		String uri = "/Currency";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.content(objectMapper.writeValueAsString(currency))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("UTF-8"))
				.andReturn();
		
		int status = result.getResponse().getStatus();
		String conte = result.getResponse().getContentAsString();

		Assert.assertEquals("錯誤", 200, status);
		System.out.println("==============測試呼叫新增幣別對應表資料 結果=============");
		System.out.println("狀態:" + status + ",內容:" + conte);
		System.out.println("==============測試呼叫新增幣別對應表資料 End=============");
	}

	//測試呼叫更新幣別對應表資料
	public void Test3() throws Exception {
		CurrencyTest currency = new CurrencyTest();
		currency.setRate("99,123.9438");
		currency.setDescription("British Pound QQQQ");
		currency.setRateFloat(99123);
		currency.setId(1);
		
		System.out.println("==============單元測試開始=============");
		System.out.println("==============測試呼叫更新幣別對應表資料Start=============");
		String uri = "/Currency";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(objectMapper.writeValueAsString(currency))
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("UTF-8"))
				.andReturn();
		
		int status = result.getResponse().getStatus();
		String conte = result.getResponse().getContentAsString();

		Assert.assertEquals("錯誤", 200, status);
		System.out.println("==============測試呼叫更新幣別對應表資料 結果=============");
		System.out.println("狀態:" + status + ",內容:" + conte);
		System.out.println("==============測試呼叫更新幣別對應表資料 End=============");
	}

	//測試呼叫刪除幣別對應表資料
	public void Test4() throws Exception {
		System.out.println("==============單元測試開始=============");
		System.out.println("==============測試呼叫刪除幣別對應表資料 Start=============");
		String uri = "/Currency";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = result.getResponse().getStatus();
		String conte = result.getResponse().getContentAsString();

		Assert.assertEquals("錯誤", 200, status);
		System.out.println("==============測試呼叫刪除幣別對應表資料 結果=============");
		System.out.println("狀態:" + status + ",內容:" + conte);
		System.out.println("==============測試呼叫刪除幣別對應表資料 End=============");
	}

	//測試呼叫 coindesk
	public void Test5() throws Exception {
		System.out.println("==============單元測試開始=============");
		System.out.println("==============測試呼叫 coindesk Start=============");
		String uri = "/Currency/getApi/";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("UTF-8")).andReturn();
		int status = result.getResponse().getStatus();
		String conte = result.getResponse().getContentAsString();

		Assert.assertEquals("錯誤", 200, status);
		System.out.println("==============測試呼叫 coindesk 結果=============");
		System.out.println("狀態:" + status + ",內容:" + conte);
		System.out.println("==============測試呼叫 coindesk End=============");
	}

	//測試呼叫資料轉換的
	public void Test6() throws Exception {
		System.out.println("==============單元測試開始=============");
		System.out.println("==============測試呼叫資料轉換的 Start=============");
		String uri = "/Currency/getApiTransform/";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("UTF-8")).andReturn();
		int status = result.getResponse().getStatus();
		String conte = result.getResponse().getContentAsString();

		Assert.assertEquals("錯誤", 200, status);
		System.out.println("==============測試呼叫資料轉換的 結果=============");
		System.out.println("狀態:" + status + ",內容:" + conte);
		System.out.println("==============測試呼叫資料轉換的 End=============");
	}

}
