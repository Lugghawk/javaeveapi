package com.beimin.eveapi.corporation.starbase.detail;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.beimin.eveapi.core.AbstractContentHandler;

public class StarbaseDetailHandler extends AbstractContentHandler<StarbaseDetailResponse> {

	@Override
	public void startDocument() throws SAXException {
		setResponse(new StarbaseDetailResponse());
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs)
			throws SAXException {
		if (qName.equals("onStandingDrop")) {
			getResponse().setOnStandingDrop(getCombatSetting(attrs));
		} else if (qName.equals("onStatusDrop")) {
			getResponse().setOnStatusDrop(getCombatSetting(attrs));
		} else if (qName.equals("onAggression")) {
			getResponse().setOnAggression(getCombatSetting(attrs));
		} else if (qName.equals("onCorporationWar")) {
			getResponse().setOnCorporationWar(getCombatSetting(attrs));
		} else if (qName.equals("row")) {
			getResponse().addFuelLevel(getInt(attrs, "typeID"), getInt(attrs, "quantity"));
		} else
			super.startElement(uri, localName, qName, attrs);
	}

	private ApiCombatSetting getCombatSetting(Attributes attrs) {
		ApiCombatSetting onStandingDrop = new ApiCombatSetting();
		Integer standing = getInt(attrs, "standing");
		if (standing != null)
			onStandingDrop.setStanding(standing);
		onStandingDrop.setEnabled(getBoolean(attrs, "enabled"));
		return onStandingDrop;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("usageFlags")) {
			getResponse().setUsageFlags(getInt());
		} else if (qName.equals("deployFlags")) {
			getResponse().setDeployFlags(getInt());
		} else if (qName.equals("allowCorporationMembers")) {
			getResponse().setAllowCorporationMembers(getBoolean());
		} else if (qName.equals("allowAllianceMembers")) {
			getResponse().setAllowAllianceMembers(getBoolean());
		} else if (qName.equals("claimSovereignty")) {
			getResponse().setClaimSovereignty(getBoolean());
		}

		super.endElement(uri, localName, qName);
	}
}