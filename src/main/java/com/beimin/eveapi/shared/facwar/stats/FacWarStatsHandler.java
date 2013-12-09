package com.beimin.eveapi.shared.facwar.stats;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.beimin.eveapi.core.AbstractContentHandler;

public class FacWarStatsHandler extends AbstractContentHandler<FacWarStatsResponse> {

	@Override
	public void startDocument() throws SAXException {
		setResponse(new FacWarStatsResponse());
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs)
			throws SAXException {
		super.startElement(uri, localName, qName, attrs);
		accumulator.setLength(0);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("factionID"))
			getResponse().setFactionID(getInt());
		if (qName.equals("factionName"))
			getResponse().setFactionName(getString());
		if (qName.equals("enlisted"))
			getResponse().setEnlisted(getDate());
		if (qName.equals("currentRank"))
			getResponse().setCurrentRank(getInt());
		if (qName.equals("highestRank"))
			getResponse().setHighestRank(getInt());
		if (qName.equals("killsYesterday"))
			getResponse().setKillsYesterday(getInt());
		if (qName.equals("killsLastWeek"))
			getResponse().setKillsLastWeek(getInt());
		if (qName.equals("killsTotal"))
			getResponse().setKillsTotal(getInt());
		if (qName.equals("victoryPointsYesterday"))
			getResponse().setVictoryPointsYesterday(getInt());
		if (qName.equals("killsTotal"))
			getResponse().setKillsTotal(getInt());
		if (qName.equals("victoryPointsYesterday"))
			getResponse().setVictoryPointsYesterday(getInt());
		if (qName.equals("victoryPointsLastWeek"))
			getResponse().setVictoryPointsLastWeek(getInt());
		if (qName.equals("victoryPointsTotal"))
			getResponse().setVictoryPointsTotal(getInt());
		if (qName.equals("pilots"))
			getResponse().setPilots(getInt());
		super.endElement(uri, localName, qName);
	}

}