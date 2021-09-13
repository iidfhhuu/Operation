package com.engineer.test.Controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.engineer.test.Entity.Currency;
import com.engineer.test.Service.CurrencyService;

@RestController
@RequestMapping("/Currency")
public class CurrencyController {

	@Value("${url}")
	private String url;

	@Autowired
	private CurrencyService currencyService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Currency> getAll(){
		return currencyService.findAll();
	}
	
	@RequestMapping(value = "/{code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Currency> getCode(@PathVariable String code) {
		return currencyService.findByCode(code);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Currency create(@RequestBody Currency value) {
		return currencyService.update(value);
	}

	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Currency update(@RequestBody Currency value) {
		return currencyService.create(value);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public List<Currency> delete() {
		currencyService.delete();
		return currencyService.findAll();
	}

	@RequestMapping(value = "getApi", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getApi() {
		return getUrl();
	}

	@RequestMapping(value = "getApiTransform", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Currency> getApiTransform() {
		String apiString = getUrl();
		JSONObject jsonObject = new JSONObject(apiString);
		JSONObject JSONTime = jsonObject.getJSONObject("time");
		String dateS = JSONTime.getString("updatedISO");
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+00:00").parse(dateS);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject jsonObject2 = jsonObject.getJSONObject("bpi");
		for (String key : jsonObject2.keySet()) {
			JSONObject jsonObject3 = jsonObject2.getJSONObject(key);
			Currency value = new Currency();

			value.setCode(jsonObject3.getString("code"));
			value.setDescription(jsonObject3.getString("description"));
			value.setRate(jsonObject3.getString("rate"));
			value.setRateFloat(jsonObject3.getInt("rate_float"));
			value.setSymbol(jsonObject3.getString("symbol"));
			value.setUpdateDate(date);

			currencyService.create(value);
		}

		return currencyService.findAll();
	}

	public String getUrl() {
		StringBuffer sb = new StringBuffer();

		try {
			String urlStr = null;
			JSONObject json = new JSONObject();
			urlStr = url;

			System.out.println(json);

			URL url = new URL(urlStr);
			HttpsURLConnection con;
			con = (HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setUseCaches(false);
			con.setAllowUserInteraction(false);
			con.setConnectTimeout(30000);
			con.setReadTimeout(30000);
			con.connect();
			InputStream is;

			if (con.getResponseCode() == 200) {
				is = con.getInputStream();
			} else {
				is = con.getErrorStream();
			}

			String temp = null;

			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			System.out.println(sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
