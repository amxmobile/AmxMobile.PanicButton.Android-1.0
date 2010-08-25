package com.amxmobile.PanicButton.Services;

import com.amxmobile.PanicButton.Runtime.PanicRuntime;

public class PanicService extends ServiceBase
{
	public PanicService()
	{
		super("PanicRest.aspx");
	}

	public void StartPanicking() throws Exception
	{
		RestRequestArgs args = new RestRequestArgs("StartPanicking");
		args.put("ResourceId", PanicRuntime.ResourceId);
		
		// send...
		this.SendRequest(args);
	}
}
