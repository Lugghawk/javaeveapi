package com.beimin.eveapi.server;

import org.xml.sax.SAXException;

import com.beimin.eveapi.core.AbstractContentHandler;

public class ServerStatusHandler extends AbstractContentHandler<ServerStatusResponse> {

	@Override
	public void startDocument() throws SAXException {
		setResponse(new ServerStatusResponse());
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("serverOpen"))
			getResponse().setServerOpen(getBoolean());
		else if (qName.equals("onlinePlayers"))
			getResponse().setOnlinePlayers(getInt());
		super.endElement(uri, localName, qName);
	}

}