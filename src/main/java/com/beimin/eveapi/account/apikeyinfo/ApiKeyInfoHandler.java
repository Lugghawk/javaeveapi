package com.beimin.eveapi.account.apikeyinfo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.beimin.eveapi.account.characters.EveCharacter;
import com.beimin.eveapi.core.AbstractContentHandler;
import com.beimin.eveapi.shared.KeyType;

public class ApiKeyInfoHandler extends AbstractContentHandler<ApiKeyInfoResponse> {

	@Override
	public void startDocument() throws SAXException {
		setResponse(new ApiKeyInfoResponse());
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs)
			throws SAXException {
		if (qName.equals("key")) {
			getResponse().setAccessMask(getLong(attrs, "accessMask"));
			getResponse().setType(KeyType.valueOf(getString(attrs, "type")));
			String expires = attrs.getValue("expires").trim();
			if (expires.length() > 0)
				getResponse().setExpires(getDate(expires));
		} else if (qName.equals("row")) {
			EveCharacter character = new EveCharacter();
			character.setCharacterID(getLong(attrs, "characterID"));
			character.setName(getString(attrs, "characterName"));
			character.setCorporationID(getLong(attrs, "corporationID"));
			character.setCorporationName(getString(attrs, "corporationName"));
			getResponse().addEveCharacter(character);
		}
		super.startElement(uri, localName, qName, attrs);
		accumulator.setLength(0);
	}

}
