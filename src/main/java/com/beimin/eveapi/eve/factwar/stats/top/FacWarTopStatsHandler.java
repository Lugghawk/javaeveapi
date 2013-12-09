package com.beimin.eveapi.eve.factwar.stats.top;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.beimin.eveapi.core.AbstractContentHandler;

public class FacWarTopStatsHandler extends AbstractContentHandler<FacWarTopStatsResponse> {
	private boolean characters;
	private boolean corporations;
	private boolean factions;
	private boolean killsYesterday;
	private boolean killsLastWeek;
	private boolean killsTotal;
	private boolean victoryPointsYesterday;
	private boolean victoryPointsLastWeek;
	private boolean victoryPointsTotal;

	@Override
	public void startDocument() throws SAXException {
		setResponse(new FacWarTopStatsResponse());
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs)
			throws SAXException {
		if (qName.equals("characters"))
			characters = true;
		else if (qName.equals("corporations"))
			corporations = true;
		else if (qName.equals("factions"))
			factions = true;
		else if (qName.equals("rowset")) {
			String name = getString(attrs, "name");
			killsYesterday = name.equals("KillsYesterday");
			killsLastWeek = name.equals("KillsLastWeek");
			killsTotal = name.equals("KillsTotal");
			victoryPointsYesterday = name.equals("VictoryPointsYesterday");
			victoryPointsLastWeek = name.equals("VictoryPointsLastWeek");
			victoryPointsTotal = name.equals("VictoryPointsTotal");
		} else if (qName.equals("row")) {
			if (characters) {
				if (killsYesterday)
					getResponse().addYesterday(getCharKill(attrs));
				else if (killsLastWeek)
					getResponse().addLastWeek(getCharKill(attrs));
				else if (killsTotal)
					getResponse().addTotal(getCharKill(attrs));
				else if (victoryPointsYesterday)
					getResponse().addYesterday(getCharVP(attrs));
				else if (victoryPointsLastWeek)
					getResponse().addLastWeek(getCharVP(attrs));
				else if (victoryPointsTotal)
					getResponse().addTotal(getCharVP(attrs));
			} else if (corporations) {
				if (killsYesterday)
					getResponse().addYesterday(getCorpKill(attrs));
				else if (killsLastWeek)
					getResponse().addLastWeek(getCorpKill(attrs));
				else if (killsTotal)
					getResponse().addTotal(getCorpKill(attrs));
				else if (victoryPointsYesterday)
					getResponse().addYesterday(getCorpVP(attrs));
				else if (victoryPointsLastWeek)
					getResponse().addLastWeek(getCorpVP(attrs));
				else if (victoryPointsTotal)
					getResponse().addTotal(getCorpVP(attrs));
			} else if (factions) {
				if (killsYesterday)
					getResponse().addYesterday(getFactionKill(attrs));
				else if (killsLastWeek)
					getResponse().addLastWeek(getFactionKill(attrs));
				else if (killsTotal)
					getResponse().addTotal(getFactionKill(attrs));
				else if (victoryPointsYesterday)
					getResponse().addYesterday(getFactionVP(attrs));
				else if (victoryPointsLastWeek)
					getResponse().addLastWeek(getFactionVP(attrs));
				else if (victoryPointsTotal)
					getResponse().addTotal(getFactionVP(attrs));
			}
		} else
			super.startElement(uri, localName, qName, attrs);
	}

	private void setCharacterDetails(Attributes attrs, CharacterStat stat) {
		stat.setCharacterID(getLong(attrs, "characterID"));
		stat.setCharacterName(getString(attrs, "characterName"));
	}

	private void setCorporationDetails(Attributes attrs, CorporationStat stat) {
		stat.setCorporationID(getLong(attrs, "corporationID"));
		stat.setCorporationName(getString(attrs, "corporationName"));
	}

	private void setFactionDetails(Attributes attrs, FactionStat stat) {
		stat.setFactionID(getInt(attrs, "factionID"));
		stat.setFactionName(getString(attrs, "factionName"));
	}

	private void setKills(Attributes attrs, KillStat stat) {
		stat.setKills(getInt(attrs, "kills"));
	}

	private void setVictoryPoint(Attributes attrs, VictoryPointsStat stat) {
		stat.setVictoryPoints(getInt(attrs, "victoryPoints"));
	}

	private CharacterKills getCharKill(Attributes attrs) {
		CharacterKills stat = new CharacterKills();
		setCharacterDetails(attrs, stat);
		setKills(attrs, stat);
		return stat;
	}

	private CharacterVictoryPoints getCharVP(Attributes attrs) {
		CharacterVictoryPoints stat = new CharacterVictoryPoints();
		setCharacterDetails(attrs, stat);
		setVictoryPoint(attrs, stat);
		return stat;
	}

	private CorporationKills getCorpKill(Attributes attrs) {
		CorporationKills stat = new CorporationKills();
		setCorporationDetails(attrs, stat);
		setKills(attrs, stat);
		return stat;
	}

	private CorporationVictoryPoints getCorpVP(Attributes attrs) {
		CorporationVictoryPoints stat = new CorporationVictoryPoints();
		setCorporationDetails(attrs, stat);
		setVictoryPoint(attrs, stat);
		return stat;
	}

	private FactionKills getFactionKill(Attributes attrs) {
		FactionKills stat = new FactionKills();
		setFactionDetails(attrs, stat);
		setKills(attrs, stat);
		return stat;
	}

	private FactionVictoryPoints getFactionVP(Attributes attrs) {
		FactionVictoryPoints stat = new FactionVictoryPoints();
		setFactionDetails(attrs, stat);
		setVictoryPoint(attrs, stat);
		return stat;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("characters"))
			characters = false;
		else if (qName.equals("corporations"))
			corporations = false;
		else if (qName.equals("factions"))
			corporations = false;
		else
			super.endElement(uri, localName, qName);
	}

}