package com.epsilon.aem.citi.service.core;


import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.util.SystemOutLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

public class HeaderServiceComponent extends WCMUsePojo{

	private HeaderBean headerBean = null;
	private String headerResult;
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String args[]) {
		System.out.println("Header :: "+getHeaderServiceData());
	}
	@Override
	public void activate() throws Exception {
		// TODO Auto-generated method stub
		headerBean = new HeaderBean();
		headerBean.setHeaderData(getHeaderServiceData());
		setHeaderResult(getHeaderServiceData());
		
	}
	
	private static String getHeaderServiceData() {
		try
        {
    
   
            DefaultHttpClient httpClient = new DefaultHttpClient();
   
            HttpGet getRequest = new HttpGet("https://qa.cbgrus.website.epsilon.com/v2/header.htm?partnerCode=FV_GRB2S&sessionState=A&languageCode=ENG&countryCode=MY&mbrTiercode=MYMASS&jquery=Y&responsive=Y&styling=Y");
            getRequest.addHeader("accept", "application/json");
   
            HttpResponse response = httpClient.execute(getRequest);
   
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
   
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
   
            String output;
            String headerResult="" ;
            while ((output = br.readLine()) != null) {
                //System.out.println(output);
            	headerResult = headerResult + output;
            }
   
   
            httpClient.getConnectionManager().shutdown();
            return headerResult ;
        }
   
        catch (Exception e)
        {
            e.printStackTrace() ;
        }
   
        return null;
    }

	public String getHeaderResult() {
		return headerResult;
	}

	public void setHeaderResult(String headerResult) {
		this.headerResult = headerResult;
	}
	

}
