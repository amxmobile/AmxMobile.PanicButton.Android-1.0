package com.amxmobile.PanicButton.Services;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.util.Log;

import com.amxmobile.PanicButton.Runtime.Helpers;

public abstract class ServiceBase 
{
	private String _serviceName;

	protected ServiceBase(String serviceName)
	{
		_serviceName = serviceName;
	}
	
	private String getServiceName()
	{
		return _serviceName;
	}
	
	public String getServiceUrl()
	{
		return "http://192.168.1.105/amxpanic/webservices/" + getServiceName();
	}
	
	public Document SendRequest(RestRequestArgs args) throws Exception
	{
		String xml = args.ToXml();
		Log.v(getClass().getName(), "OUTPUT: " + xml);
		
		// send it...
		HttpPost post = new HttpPost(getServiceUrl());
		post.setEntity(new RestArgsHttpEntity(xml));
		
		// send...
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = client.execute(post);
		
		// what happened?
		int status = response.getStatusLine().getStatusCode(); 
		if(status != 200)
			throw new Exception(String.format("Invalid status '%d' received.", status));
		
		// read the value back...
		String answer = Helpers.GetHttpEntityContent(response.getEntity());
		Log.v(getClass().getName(), "RESULT: " + answer);
		Document answerDoc = Helpers.LoadXml(answer);
		
		// did we get an error?
		NodeList hasErrorElements = (NodeList)answerDoc.getElementsByTagName("HasError");
		if(hasErrorElements.getLength() > 0)
		{
			String value = Helpers.GetStringValueForNode(hasErrorElements.item(0));
			if(value.compareTo("1") == 0)
			{
				NodeList errorElements = (NodeList)answerDoc.getElementsByTagName("Error");
				if(errorElements.getLength() == 0)
					throw new Exception("An error was returned, but no further information was sent.");
				
				// throw...
				String error = Helpers.GetStringValueForNode(errorElements.item(0));
				throw new Exception(error);
			}
		}
		
		// ok...
		return answerDoc;
	}
}
