package com.amxmobile.PanicButton.Services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

public class RestArgsHttpEntity implements HttpEntity
{
	private static final String Encoding = "UTF-8"; 
	
	private byte[] _bytes;
	
	public RestArgsHttpEntity(String xml) throws Exception
	{
		_bytes = xml.getBytes(Encoding);
	}

	public void consumeContent() throws IOException
	{
	}

	public InputStream getContent() throws IOException, IllegalStateException
	{
		return null;
	}

	public Header getContentEncoding()
	{
		return new BasicHeader("content-encoding", Encoding);
	}

	public long getContentLength()
	{
		return _bytes.length;
	}

	public Header getContentType()
	{
		return new BasicHeader("content-type", "text/xml");
	}

	public boolean isChunked()
	{
		return false;
	}

	public boolean isRepeatable()
	{
		return false;
	}

	public boolean isStreaming()
	{
		return false;
	}

	public void writeTo(OutputStream outstream) throws IOException
	{
		outstream.write(_bytes);
	}
}
