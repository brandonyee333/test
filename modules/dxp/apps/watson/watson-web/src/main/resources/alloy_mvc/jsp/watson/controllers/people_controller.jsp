<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/alloy_mvc/jsp/watson/controllers/init.jspf" %>

<%@ include file="/alloy_mvc/jsp/watson/indexers/people_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends WatsonAlloyControllerImpl {

	public AlloyControllerImpl() throws Exception {
		setAlloyServiceInvokerClass(WatsonPerson.getBaseModelClass());
		setPermissioned(true);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonPerson watsonPerson = WatsonPerson.create(request);

		if (!WatsonPermission.check(user, watsonPerson.getWatsonIncidentId())) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		JSONArray countryIDWatsonListTypeRelJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "countryIDWatsonListTypeRels"));

		Object[] generatedCountryIDWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonPerson, watsonPerson.getModelClassName() + ".countryIDType", countryIDWatsonListTypeRelJSONArray);

		List<WatsonListTypeRel> countryIDWatsonListTypeRels = (List<WatsonListTypeRel>)generatedCountryIDWatsonListTypeRelChanges[0];

		JSONArray nameTypeWatsonListTypeRelJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "nameWatsonListTypeRels"));

		Object[] generatedNameWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonPerson, watsonPerson.getModelClassName() + ".nameType", nameTypeWatsonListTypeRelJSONArray);

		List<WatsonListTypeRel> nameWatsonListTypeRels = (List<WatsonListTypeRel>)generatedNameWatsonListTypeRelChanges[0];

		JSONArray phoneNumberWatsonListTypeRelJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "phoneNumberWatsonListTypeRels"));

		Object[] generatedPhoneNumberWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonPerson, watsonPerson.getModelClassName() + ".phoneNumberType", phoneNumberWatsonListTypeRelJSONArray);

		List<WatsonListTypeRel> phoneNumberWatsonListTypeRels = (List<WatsonListTypeRel>)generatedPhoneNumberWatsonListTypeRelChanges[0];

		JSONArray socialMediaAccountWatsonListTypeRelJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "socialMediaAccountWatsonListTypeRels"));

		Object[] generatedSocialMediaAccountWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonPerson, watsonPerson.getModelClassName() + ".socialMediaAccountType", socialMediaAccountWatsonListTypeRelJSONArray);

		List<WatsonListTypeRel> socialMediaAccountWatsonListTypeRels = (List<WatsonListTypeRel>)generatedSocialMediaAccountWatsonListTypeRelChanges[0];

		if (!watsonPerson.isValid(countryIDWatsonListTypeRels, nameWatsonListTypeRels, phoneNumberWatsonListTypeRels, socialMediaAccountWatsonListTypeRels, null)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("model", WatsonPerson.getAsJSONObject(watsonPerson, countryIDWatsonListTypeRels, nameWatsonListTypeRels, phoneNumberWatsonListTypeRels, socialMediaAccountWatsonListTypeRels, null));

			jsonObject.put("errors", watsonPerson.getPersonFormErrors());

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("person-was-not-saved"), jsonObject);

			return;
		}

		watsonPerson.add();

		WatsonListTypeRel.batchAdd(countryIDWatsonListTypeRels);

		WatsonListTypeRel.batchAdd(nameWatsonListTypeRels);

		WatsonListTypeRel.batchAdd(phoneNumberWatsonListTypeRels);

		WatsonListTypeRel.batchAdd(socialMediaAccountWatsonListTypeRels);

		WatsonHistory.add(watsonPerson.getWatsonIncidentId(), watsonPerson, request, WatsonHistory.HISTORY_TYPE_CREATED);

		respondWith(translate("person-saved-successfully"), WatsonPerson.getAsJSONObject(watsonPerson));
	}

	public void create() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}
	}

	public void delete() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonPersonId = ParamUtil.getLong(request, "id");

		WatsonPerson watsonPerson = WatsonPerson.fetch(watsonPersonId);

		if (!WatsonPermission.check(user, watsonPerson.getWatsonIncidentId())) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		WatsonListTypeRel.clear(watsonPerson);

		WatsonRelationship.clearWatsonRelationships(watsonPerson.getWatsonIncidentId(), watsonPersonId);

		WatsonHistory.add(watsonPerson.getWatsonIncidentId(), watsonPerson, request, WatsonHistory.HISTORY_TYPE_DELETED);

		watsonPerson.delete();

		respondWith(StringPool.BLANK);
	}

	public void edit() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonPersonId = ParamUtil.getLong(request, "id");

		WatsonPerson watsonPerson = WatsonPerson.fetch(watsonPersonId);

		if (!WatsonPermission.check(user, watsonPerson.getWatsonIncidentId())) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		respondWith(WatsonPerson.getAsJSONObject(watsonPerson));
	}

	public void executeImport() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonIncidentId = ParamUtil.getLong(request, "id");

		long[] watsonPeopleIds = ParamUtil.getLongValues(request, "selectedIds");

		List<WatsonPerson> addedPeople = new ArrayList<>();

		for (long watsonPersonId : watsonPeopleIds) {
			WatsonPerson originalWatsonPerson = WatsonPerson.fetch(watsonPersonId);

			WatsonPerson newWatsonPerson = new WatsonPerson((com.liferay.watson.model.WatsonPerson)originalWatsonPerson.clone());

			if (originalWatsonPerson.getOriginalWatsonPersonId() == 0) {
				newWatsonPerson.setOriginalWatsonPersonId(originalWatsonPerson.getPrimaryKey());
			}
			else {
				newWatsonPerson.setOriginalWatsonPersonId(originalWatsonPerson.getOriginalWatsonPersonId());
			}

			newWatsonPerson.setPrimaryKey(CounterLocalServiceUtil.increment());
			newWatsonPerson.setWatsonIncidentId(watsonIncidentId);

			WatsonListTypeRel.copy(request, watsonPersonId, newWatsonPerson.getWatsonPersonId());

			newWatsonPerson = WatsonPerson.add(newWatsonPerson);

			WatsonHistory.add(newWatsonPerson.getWatsonIncidentId(), newWatsonPerson, request, WatsonHistory.HISTORY_TYPE_CREATED);

			addedPeople.add(newWatsonPerson);
		}

		respondWith(translate("people-saved-successfully"), WatsonPerson.getAsJSONDataArray(addedPeople, WatsonIncident.getWatsonPeopleCount(watsonIncidentId)));
	}

	public void fetchTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.TRANSLATOR)) {
			long watsonPersonId = ParamUtil.getLong(request, "id");

			WatsonPerson watsonPerson = WatsonPerson.fetch(watsonPersonId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			respondWith(WatsonPerson.getAsJSONObject(watsonPerson, locale));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		List<WatsonPerson> watsonPeople = null;
		long watsonPeopleCount = 0;

		long watsonIncidentId = ParamUtil.getLong(request, "id", 0);
		int start = ParamUtil.getInteger(request, "start", QueryUtil.ALL_POS);
		int end = ParamUtil.getInteger(request, "end", QueryUtil.ALL_POS);

		String actionType = ParamUtil.getString(request, "actionType");

		if (actionType.equals("import")) {
			watsonPeople = WatsonPerson.filterPeople(WatsonPerson.getUnrelatedWatsonPeople(watsonIncidentId, start, end), user);

			List<WatsonPerson> tempWatsonPeople = WatsonPerson.getUnrelatedWatsonPeople(watsonIncidentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			watsonPeopleCount = tempWatsonPeople.size();
		}
		else {
			boolean includeInactive = ParamUtil.getBoolean(request, "includeInactive", false);
			String sort = ParamUtil.getString(request, "sortBy", null);

			watsonPeople = WatsonIncident.getWatsonPeople(watsonIncidentId, includeInactive, sort, start, end, user);

			watsonPeopleCount = WatsonIncident.getWatsonPeopleCount(watsonIncidentId);
		}

		respondWith(WatsonPerson.getAsJSONDataArray(watsonPeople, watsonPeopleCount));
	}

	public void requestTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonPersonId = ParamUtil.getLong(request, "watsonPrimaryKey");

		WatsonPerson watsonPerson = WatsonPerson.fetch(watsonPersonId);

		String translationURL = ParamUtil.getString(request, "translationURL");

		WatsonWorkflowUtil.sendTranslationEmail(user, getModelProperName(WatsonPerson.baseModelClass, locale), watsonPerson.getPrimaryName(), translationURL);

		respondWith(StringPool.BLANK);
	}

	public void search() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		SearchContext searchContext = getPopulatedSearchContext(WatsonPerson.baseModelClass);

		List<WatsonPerson> searchResultWatsonPeople = _doSearch(searchContext);

		String actionType = ParamUtil.getString(request, "actionType");

		if (actionType.equals("import")) {
			long watsonIncidentId = ParamUtil.getLong(request, "watsonIncidentId");

			List<WatsonPerson> unrelatedWatsonPeople = WatsonPerson.getUnrelatedWatsonPeople(watsonIncidentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (WatsonPerson watsonPerson : searchResultWatsonPeople) {
				if (!unrelatedWatsonPeople.contains(watsonPerson)) {
					unrelatedWatsonPeople.remove(watsonPerson);
				}
			}
		}

		respondWith(WatsonPerson.getAsJSONDataArray(WatsonPerson.filterPeople(searchResultWatsonPeople, user), getTotalHits(searchContext)));
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonPersonId = ParamUtil.getLong(request, "id");

		if (watsonPersonId == 0) {
			this.add();

			return;
		}

		WatsonPerson watsonPerson = WatsonPerson.fetch(watsonPersonId);

		if (!WatsonPermission.check(user, watsonPerson.getWatsonIncidentId()) || WatsonIncident.hasDisabled(user.getUserId(), WatsonIncident.getIncidentStatus(watsonPerson.getWatsonIncidentId()))) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), WatsonPerson.getAsJSONObject(watsonPerson));

			return;
		}

		watsonPerson.setFields(request);

		long watsonIncidentId = watsonPerson.getWatsonIncidentId();

		JSONArray countryIDWatsonListTypeRelJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "countryIDWatsonListTypeRels"));

		Object[] generatedCountryIDWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonPerson, watsonPerson.getModelClassName() + ".countryIDType", countryIDWatsonListTypeRelJSONArray);

		List<WatsonListTypeRel> countryIDWatsonListTypeRels = (List<WatsonListTypeRel>)generatedCountryIDWatsonListTypeRelChanges[0];

		List<WatsonListTypeRel> removableCountryIDWatsonListTypeRels = (List<WatsonListTypeRel>)generatedCountryIDWatsonListTypeRelChanges[1];

		JSONArray nameTypeWatsonListTypeRelJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "nameWatsonListTypeRels"));

		Object[] generatedNameWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonPerson, watsonPerson.getModelClassName() + ".nameType", nameTypeWatsonListTypeRelJSONArray);

		List<WatsonListTypeRel> nameWatsonListTypeRels = (List<WatsonListTypeRel>)generatedNameWatsonListTypeRelChanges[0];

		List<WatsonListTypeRel> removableNameWatsonListTypeRels = (List<WatsonListTypeRel>)generatedNameWatsonListTypeRelChanges[1];

		JSONArray phoneNumberWatsonListTypeRelJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "phoneNumberWatsonListTypeRels"));

		Object[] generatedPhoneNumberWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonPerson, watsonPerson.getModelClassName() + ".phoneNumberType", phoneNumberWatsonListTypeRelJSONArray);

		List<WatsonListTypeRel> phoneNumberWatsonListTypeRels = (List<WatsonListTypeRel>)generatedPhoneNumberWatsonListTypeRelChanges[0];

		List<WatsonListTypeRel> removablePhoneNumberWatsonListTypeRels = (List<WatsonListTypeRel>)generatedPhoneNumberWatsonListTypeRelChanges[1];

		JSONArray socialMediaAccountWatsonListTypeRelJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "socialMediaAccountWatsonListTypeRels"));

		Object[] generatedSocialMediaAccountWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonPerson, watsonPerson.getModelClassName() + ".socialMediaAccountType", socialMediaAccountWatsonListTypeRelJSONArray);

		List<WatsonListTypeRel> socialMediaAccountWatsonListTypeRels = (List<WatsonListTypeRel>)generatedSocialMediaAccountWatsonListTypeRelChanges[0];

		List<WatsonListTypeRel> removableSocialMediaAccountWatsonListTypeRels = (List<WatsonListTypeRel>)generatedSocialMediaAccountWatsonListTypeRelChanges[1];

		JSONArray watsonRelationshipJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "watsonRelationships"));

		Object[] generatedWatsonRelationships = WatsonRelationship.generateValidationData(request, watsonPerson, watsonRelationshipJSONArray, watsonIncidentId);

		List<WatsonRelationship> watsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[0];

		List<WatsonRelationship> removableWatsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[1];

		if (!watsonPerson.isValid(countryIDWatsonListTypeRels, nameWatsonListTypeRels, phoneNumberWatsonListTypeRels, socialMediaAccountWatsonListTypeRels, watsonRelationships)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonPerson.getPersonFormErrors());

			jsonObject.put("model", WatsonPerson.getAsJSONObject(watsonPerson, countryIDWatsonListTypeRels, nameWatsonListTypeRels, phoneNumberWatsonListTypeRels, socialMediaAccountWatsonListTypeRels, watsonRelationships));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("person-was-not-saved"), jsonObject);

			return;
		}

		watsonPerson.update();

		WatsonListTypeRel.batchUpdate(countryIDWatsonListTypeRels);

		WatsonListTypeRel.batchDelete(removableCountryIDWatsonListTypeRels);

		WatsonListTypeRel.batchUpdate(nameWatsonListTypeRels);

		WatsonListTypeRel.batchDelete(removableNameWatsonListTypeRels);

		WatsonListTypeRel.batchUpdate(phoneNumberWatsonListTypeRels);

		WatsonListTypeRel.batchDelete(removablePhoneNumberWatsonListTypeRels);

		WatsonListTypeRel.batchUpdate(socialMediaAccountWatsonListTypeRels);

		WatsonListTypeRel.batchDelete(removableSocialMediaAccountWatsonListTypeRels);

		WatsonRelationship.batchUpdate(watsonRelationships);

		WatsonRelationship.batchDelete(removableWatsonRelationships);

		WatsonHistory.add(watsonPerson.getWatsonIncidentId(), watsonPerson, request, WatsonHistory.HISTORY_TYPE_UPDATED);

		respondWith(translate("person-saved-successfully"), WatsonPerson.getAsJSONObject(watsonPerson));
	}

	public void updateTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.TRANSLATOR)) {
			long watsonPersonId = ParamUtil.getLong(request, "id");

			WatsonPerson watsonPerson = WatsonPerson.fetch(watsonPersonId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			watsonPerson.updateTranslations(request, locale);

			respondWith(translate("person-saved-successfully"), WatsonPerson.getAsJSONObject(watsonPerson));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonPersonId = ParamUtil.getLong(request, "id");

		WatsonPerson watsonPerson = WatsonPerson.fetch(watsonPersonId);

		if (!WatsonPermission.check(user, watsonPerson.getWatsonIncidentId())) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		respondWith(WatsonPerson.getAsJSONObject(watsonPerson));
	}

	@Override
	protected Indexer buildIndexer() {
		return WatsonPeopleIndexer.getInstance();
	}

	private List<WatsonPerson> _doSearch(SearchContext searchContext) throws Exception {
		List<WatsonPerson> watsonPeople = new ArrayList<>();

		Document[] documents = getDocuments(searchContext);

		for (Document document : documents) {
			long classPK = GetterUtil.getLong(document.get("entryClassPK"));

			watsonPeople.add(WatsonPerson.fetch(classPK));
		}

		return watsonPeople;
	}

}
%>