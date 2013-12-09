package com.beimin.eveapi.corporation.sheet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.beimin.eveapi.core.AbstractContentHandler;

public class CorpSheetHandler extends AbstractContentHandler<CorpSheetResponse> {
	private ApiCorpLogo logo;
	private boolean divisions;
	private boolean walletDivisions;

	@Override
	public void startDocument() throws SAXException {
		setResponse(new CorpSheetResponse());
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs)
			throws SAXException {
		if (qName.equals("logo")) {
			logo = new ApiCorpLogo();
		} else if (qName.equals("rowset")) {
			String name = getString(attrs, "name");
			divisions = name.equals("divisions");
			walletDivisions = name.equals("walletDivisions");
		} else if (qName.equals("row")) {
			Division division = new Division();
			division.setAccountKey(getInt(attrs, "accountKey"));
			division.setDescription(getString(attrs, "description"));
			if (divisions)
				getResponse().addDivision(division);
			else if (walletDivisions)
				getResponse().addWalletDivision(division);
		}
		super.startElement(uri, localName, qName, attrs);
		accumulator.setLength(0);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("corporationID")) {
			getResponse().setCorporationID(getLong());
		} else if (qName.equals("corporationName")) {
			getResponse().setCorporationName(getString());
		} else if (qName.equals("ticker")) {
			getResponse().setTicker(getString());
		} else if (qName.equals("ceoID")) {
			getResponse().setCeoID(getLong());
		} else if (qName.equals("ceoName")) {
			getResponse().setCeoName(getString());
		} else if (qName.equals("stationID")) {
			getResponse().setStationID(getLong());
		} else if (qName.equals("stationName")) {
			getResponse().setStationName(getString());
		} else if (qName.equals("description")) {
			getResponse().setDescription(getString());
		} else if (qName.equals("url")) {
			getResponse().setUrl(getString());
		} else if (qName.equals("allianceID")) {
			getResponse().setAllianceID(getLong());
		} else if (qName.equals("allianceName")) {
			getResponse().setAllianceName(getString());
		} else if (qName.equals("factionID")) {
			getResponse().setFactionID(getLong());
		} else if (qName.equals("taxRate")) {
			getResponse().setTaxRate(getDouble());
		} else if (qName.equals("memberCount")) {
			getResponse().setMemberCount(getInt());
		} else if (qName.equals("memberLimit")) {
			getResponse().setMemberLimit(getInt());
		} else if (qName.equals("shares")) {
			getResponse().setShares(getLong());
		} else if (qName.equals("graphicsID")) {
			logo.setGraphicID(getInt());
		} else if (qName.equals("shape1")) {
			logo.setShape1(getInt());
		} else if (qName.equals("shape2")) {
			logo.setShape2(getInt());
		} else if (qName.equals("shape3")) {
			logo.setShape3(getInt());
		} else if (qName.equals("color1")) {
			logo.setColor1(getInt());
		} else if (qName.equals("color2")) {
			logo.setColor2(getInt());
		} else if (qName.equals("color3")) {
			logo.setColor3(getInt());
		} else if (qName.equals("logo")) {
			getResponse().setLogo(logo);
		} else if (qName.equals("rowset")) {
			if (divisions || walletDivisions) {
				divisions = false;
				walletDivisions = false;
			}
		}

		super.endElement(uri, localName, qName);
	}

}