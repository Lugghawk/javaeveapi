package com.beimin.eveapi.character.sheet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.beimin.eveapi.core.AbstractContentHandler;
import com.beimin.eveapi.shared.character.EveAncestry;
import com.beimin.eveapi.shared.character.EveBloodline;
import com.beimin.eveapi.shared.character.EveRace;

public class CharacterSheetHandler extends AbstractContentHandler<CharacterSheetResponse> {
	private ApiAttributeEnhancer attributeEnhancer;
	private String rowsetName;

	@Override
	public void startDocument() throws SAXException {
		setResponse(new CharacterSheetResponse());
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs)
			throws SAXException {
		if (qName.equals("intelligenceBonus"))
			attributeEnhancer = new IntelligenceBonus();
		else if (qName.equals("memoryBonus"))
			attributeEnhancer = new MemoryBonus();
		else if (qName.equals("charismaBonus"))
			attributeEnhancer = new CharismaBonus();
		else if (qName.equals("perceptionBonus"))
			attributeEnhancer = new PerceptionBonus();
		else if (qName.equals("willpowerBonus"))
			attributeEnhancer = new WillpowerBonus();
		else if (qName.equals("rowset"))
			rowsetName = getString(attrs, "name");
		else if (qName.equals("row")) {
			if ("skills".equals(rowsetName)) {
				ApiSkill skill = new ApiSkill();
				skill.setTypeID(getInt(attrs, "typeID"));
				Integer level = getInt(attrs, "level");
				if (level != null)
					skill.setLevel(level);
				skill.setSkillpoints(getInt(attrs, "skillpoints"));
				skill.setUnpublished(getBoolean(attrs, "unpublished"));
				getResponse().addSkill(skill);
			} else if ("certificates".equals(rowsetName)) {
				ApiCertificate certificate = new ApiCertificate();
				certificate.setCertificateID(getInt(attrs, "certificateID"));
				getResponse().addCertificate(certificate);
			} else if ("corporationRoles".equals(rowsetName)) {
				ApiCorporationRole corporationRole = new ApiCorporationRole();
				corporationRole.setRoleID(getLong(attrs, "roleID"));
				corporationRole.setRoleName(getString(attrs, "roleName"));
				getResponse().addCorporationRole(corporationRole);
			} else if ("corporationRolesAtHQ".equals(rowsetName)) {
				ApiCorporationRole corporationRole = new ApiCorporationRole();
				corporationRole.setRoleID(getLong(attrs, "roleID"));
				corporationRole.setRoleName(getString(attrs, "roleName"));
				getResponse().addCorporationRoleAtHQ(corporationRole);
			} else if ("corporationRolesAtBase".equals(rowsetName)) {
				ApiCorporationRole corporationRole = new ApiCorporationRole();
				corporationRole.setRoleID(getLong(attrs, "roleID"));
				corporationRole.setRoleName(getString(attrs, "roleName"));
				getResponse().addCorporationRoleAtBase(corporationRole);
			} else if ("corporationRolesAtOther".equals(rowsetName)) {
				ApiCorporationRole corporationRole = new ApiCorporationRole();
				corporationRole.setRoleID(getLong(attrs, "roleID"));
				corporationRole.setRoleName(getString(attrs, "roleName"));
				getResponse().addCorporationRoleAtOther(corporationRole);
			} else if ("corporationTitles".equals(rowsetName)) {
				ApiCorporationTitle corporationTitle = new ApiCorporationTitle();
				corporationTitle.setTitleID(getLong(attrs, "titleID"));
				corporationTitle.setTitleName(getString(attrs, "titleName"));
				getResponse().addCorporationTitle(corporationTitle);
			}
		}

		super.startElement(uri, localName, qName, attrs);
		accumulator.setLength(0);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("characterID"))
			getResponse().setCharacterID(getLong());
		else if (qName.equals("name"))
			getResponse().setName(getString());
		else if (qName.equals("race"))
			getResponse().setRace(getRace());
		else if (qName.equals("DoB"))
			getResponse().setDateOfBirth(getDate());
		else if (qName.equals("bloodLine"))
			getResponse().setBloodLine(getBloodline());
		else if (qName.equals("ancestry"))
			getResponse().setAncestry(getAncestry());
		else if (qName.equals("gender"))
			getResponse().setGender(getString());
		else if (qName.equals("corporationID"))
			getResponse().setCorporationID(getLong());
		else if (qName.equals("corporationName"))
			getResponse().setCorporationName(getString());
		else if (qName.equals("allianceID"))
			getResponse().setAllianceID(getLong());
		else if (qName.equals("allianceName")) {
			if (getString() != null && !getString().equals(""))
				getResponse().setAllianceName(getString());
		} else if (qName.equals("balance"))
			getResponse().setBalance(getDouble());
		else if (qName.equals("augmentatorName"))
			attributeEnhancer.setAugmentatorName(getString());
		else if (qName.equals("augmentatorValue"))
			attributeEnhancer.setAugmentatorValue(getInt());
		else if (qName.endsWith("Bonus")) {
			getResponse().addAttributeEnhancer(attributeEnhancer);
			attributeEnhancer = null;
		} else if (qName.equals("intelligence"))
			getResponse().setIntelligence(getInt());
		else if (qName.equals("memory"))
			getResponse().setMemory(getInt());
		else if (qName.equals("charisma"))
			getResponse().setCharisma(getInt());
		else if (qName.equals("perception"))
			getResponse().setPerception(getInt());
		else if (qName.equals("willpower"))
			getResponse().setWillpower(getInt());
		else if (qName.equals("rowset"))
			rowsetName = null;
		super.endElement(uri, localName, qName);
	}

	private EveAncestry getAncestry() {
		return EveAncestry.valueOf(getString().toUpperCase().replaceAll("[-\\s]", "_"));
	}

	private EveBloodline getBloodline() {
		return EveBloodline.valueOf(getString().toUpperCase().replaceAll("[-\\s]", "_"));
	}

	private EveRace getRace() {
		return EveRace.valueOf(getString().toUpperCase().replaceAll("[-\\s]", "_"));
	}

}