package com.beimin.eveapi.eve.character;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.beimin.eveapi.core.AbstractContentHandler;
import com.beimin.eveapi.shared.character.EveBloodline;
import com.beimin.eveapi.shared.character.EveRace;

public class CharacterInfoHandler extends AbstractContentHandler<CharacterInfoResponse> {

	@Override
	public void startDocument() throws SAXException {
		setResponse(new CharacterInfoResponse());
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs)
			throws SAXException {
		if (qName.equals("row")) {
			CharacterEmployment employ = new CharacterEmployment();
			employ.setCorporationID(getLong(attrs, "corporationID"));
			employ.setStartDate(getDate(attrs, "startDate"));
			getResponse().addEmployment(employ);
		}
		super.startElement(uri, localName, qName, attrs);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("characterID"))
			getResponse().setCharacterID(getLong());
		else if (qName.equals("characterName"))
			getResponse().setCharacterName(getString());
		else if (qName.equals("race"))
			getResponse().setRace(getRace());
		else if (qName.equals("bloodline"))
			getResponse().setBloodline(getBloodline());
		else if (qName.equals("accountBalance"))
			getResponse().setAccountBalance(getDouble());
		else if (qName.equals("skillPoints"))
			getResponse().setSkillPoints(getInt());
		else if (qName.equals("shipName"))
			getResponse().setShipName(getString());
		else if (qName.equals("shipTypeID"))
			getResponse().setShipTypeID(getInt());
		else if (qName.equals("shipTypeName"))
			getResponse().setShipTypeName(getString());
		else if (qName.equals("corporationID"))
			getResponse().setCorporationID(getLong());
		else if (qName.equals("corporation"))
			getResponse().setCorporation(getString());
		else if (qName.equals("corporationDate"))
			getResponse().setCorporationDate(getDate());
		else if (qName.equals("allianceID"))
			getResponse().setAllianceID(getLong());
		else if (qName.equals("alliance"))
			getResponse().setAlliance(getString());
		else if (qName.equals("allianceDate"))
			getResponse().setAllianceDate(getDate());
		else if (qName.equals("lastKnownLocation"))
			getResponse().setLastKnownLocation(getString());
		else if (qName.equals("securityStatus"))
			getResponse().setSecurityStatus(getDouble());
	}

	private EveBloodline getBloodline() {
		return EveBloodline.valueOf(getString().toUpperCase().replaceAll("[-\\s]", "_"));
	}

	private EveRace getRace() {
		return EveRace.valueOf(getString().toUpperCase().replaceAll("[-\\s]", "_"));
	}

}