package com.beimin.eveapi.character.skill.intraining;

import org.xml.sax.SAXException;

import com.beimin.eveapi.core.AbstractContentHandler;

public class SkillInTrainingHandler extends AbstractContentHandler<SkillInTrainingResponse> {
	@Override
	public void startDocument() throws SAXException {
		setResponse(new SkillInTrainingResponse());
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("currentTQTime"))
			getResponse().setCurrentTQTime(getDate());
		else if (qName.equals("trainingEndTime"))
			getResponse().setTrainingEndTime(getDate());
		else if (qName.equals("trainingStartTime"))
			getResponse().setTrainingStartTime(getDate());
		else if (qName.equals("trainingTypeID"))
			getResponse().setTrainingTypeID(getInt());
		else if (qName.equals("trainingStartSP"))
			getResponse().setTrainingStartSP(getInt());
		else if (qName.equals("trainingDestinationSP"))
			getResponse().setTrainingDestinationSP(getInt());
		else if (qName.equals("trainingToLevel"))
			getResponse().setTrainingToLevel(getInt());
		else if (qName.equals("skillInTraining"))
			getResponse().setSkillInTraining(getBoolean());
		super.endElement(uri, localName, qName);
	}

}