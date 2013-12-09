package com.beimin.eveapi.eve.factwar.stats;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.beimin.eveapi.core.AbstractContentHandler;

public class FacWarStatsHandler extends AbstractContentHandler<FacWarStatsResponse> {
	private boolean factions;
	private boolean factionWars;

	@Override
	public void startDocument() throws SAXException {
		setResponse(new FacWarStatsResponse());
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs)
			throws SAXException {
		if (qName.equals("rowset")) {
			String name = getString(attrs, "name");
			factions = name.equals("factions");
			factionWars = name.equals("factionWars");
		} else if (qName.equals("row")) {
			if (factions) {
				ApiFactionStats item = new ApiFactionStats();
				item.setFactionID(getInt(attrs, "factionID"));
				item.setFactionName(getString(attrs, "factionName"));
				item.setPilots(getInt(attrs, "pilots"));
				item.setSystemsControlled(getInt(attrs, "systemsControlled"));
				item.setKillsYesterday(getInt(attrs, "killsYesterday"));
				item.setKillsLastWeek(getInt(attrs, "killsLastWeek"));
				item.setKillsTotal(getInt(attrs, "killsTotal"));
				item.setVictoryPointsYesterday(getInt(attrs, "victoryPointsYesterday"));
				item.setVictoryPointsLastWeek(getInt(attrs, "victoryPointsLastWeek"));
				item.setVictoryPointsTotal(getInt(attrs, "victoryPointsTotal"));
				getResponse().addStat(item);
			} else if (factionWars) {
				ApiFactionWar item = new ApiFactionWar();
				item.setFactionID(getInt(attrs, "factionID"));
				item.setFactionName(getString(attrs, "factionName"));
				item.setAgainstID(getInt(attrs, "againstID"));
				item.setAgainstName(getString(attrs, "againstName"));
				getResponse().addStat(item);
			}
		}
		super.startElement(uri, localName, qName, attrs);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("killsYesterday"))
			getResponse().setKillsYesterday(getInt());
		else if (qName.equals("killsLastWeek"))
			getResponse().setKillsLastWeek(getInt());
		else if (qName.equals("killsTotal"))
			getResponse().setKillsTotal(getInt());
		else if (qName.equals("victoryPointsYesterday"))
			getResponse().setVictoryPointsYesterday(getInt());
		else if (qName.equals("victoryPointsLastWeek"))
			getResponse().setVictoryPointsLastWeek(getInt());
		else if (qName.equals("victoryPointsTotal"))
			getResponse().setVictoryPointsTotal(getInt());
		else if (qName.equals("rowset")) {
			factions = false;
			factionWars = false;
		}
		super.endElement(uri, localName, qName);
	}

}