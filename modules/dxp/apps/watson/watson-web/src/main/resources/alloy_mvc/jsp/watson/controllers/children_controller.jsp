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

<%@ include file="/alloy_mvc/jsp/watson/indexers/children_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends WatsonAlloyControllerImpl {

	public AlloyControllerImpl() throws Exception {
		setAlloyServiceInvokerClass(WatsonChild.getBaseModelClass());
		setPermissioned(true);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonChild watsonChild = WatsonChild.create(request);

		if (!WatsonPermission.check(user, watsonChild)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
		}

		long[] activitiesInvolvedWatsonListTypeLongs = ParamUtil.getLongValues(request, "activitiesInvolvedWatsonListTypeRels");

		List<Long> activitiesInvolvedListTypeIds = new ArrayList<>();

		for (long activitiesInvolvedWatsonListTypeLong : activitiesInvolvedWatsonListTypeLongs) {
			activitiesInvolvedListTypeIds.add(activitiesInvolvedWatsonListTypeLong);
		}

		Object[] generatedActivitiesInvolvedWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonChild, watsonChild.getModelClassName().concat(".activitiesInvolved"), activitiesInvolvedListTypeIds);

		List<WatsonListTypeRel> activitiesInvolvedWatsonListTypeRels = (List<WatsonListTypeRel>)generatedActivitiesInvolvedWatsonListTypeRelChanges[0];

		JSONArray countryIDWatsonListTypeRelJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "countryIDWatsonListTypeRels"));

		Object[] generatedCountryIDWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonChild, watsonChild.getModelClassName() + ".countryIDType", countryIDWatsonListTypeRelJSONArray);

		List<WatsonListTypeRel> countryIDWatsonListTypeRels = (List<WatsonListTypeRel>)generatedCountryIDWatsonListTypeRelChanges[0];

		JSONArray guardianNameWatsonListTypeRelsJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "guardianNameWatsonListTypeRels"));

		Object[] generatedGuardianNameWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonChild, watsonChild.getModelClassName() + ".guardianNameType", guardianNameWatsonListTypeRelsJSONArray);

		List<WatsonListTypeRel> guardianNameWatsonListTypeRels = (List<WatsonListTypeRel>)generatedGuardianNameWatsonListTypeRelChanges[0];

		JSONArray nameTypeWatsonListTypeRelJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "nameWatsonListTypeRels"));

		Object[] generatedNameWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonChild, watsonChild.getModelClassName() + ".nameType", nameTypeWatsonListTypeRelJSONArray);

		List<WatsonListTypeRel> nameWatsonListTypeRels = (List<WatsonListTypeRel>)generatedNameWatsonListTypeRelChanges[0];

		JSONArray parentNameWatsonListTypeRelsJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "parentNameWatsonListTypeRels"));

		Object[] generatedParentNameWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonChild, watsonChild.getModelClassName() + ".parentNameType", parentNameWatsonListTypeRelsJSONArray);

		List<WatsonListTypeRel> parentNameWatsonListTypeRels = (List<WatsonListTypeRel>)generatedParentNameWatsonListTypeRelChanges[0];

		long[] vocationalTrainingWatsonListTypeLongs = ParamUtil.getLongValues(request, "vocationalTrainingWatsonListTypeRels");

		List<Long> vocationalTrainingListTypeIds = new ArrayList<>();

		for (long vocationalTrainingWatsonListTypeLong : vocationalTrainingWatsonListTypeLongs) {
			vocationalTrainingListTypeIds.add(vocationalTrainingWatsonListTypeLong);
		}

		Object[] generatedVocationalTrainingWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonChild, watsonChild.getModelClassName().concat(".vocationalTraining"), vocationalTrainingListTypeIds);

		List<WatsonListTypeRel> vocationalTrainingWatsonListTypeRels = (List<WatsonListTypeRel>)generatedVocationalTrainingWatsonListTypeRelChanges[0];

		if (!watsonChild.isValid(activitiesInvolvedWatsonListTypeRels, countryIDWatsonListTypeRels, guardianNameWatsonListTypeRels, nameWatsonListTypeRels, parentNameWatsonListTypeRels, vocationalTrainingWatsonListTypeRels, null)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("model", WatsonChild.getAsJSONObject(watsonChild, activitiesInvolvedWatsonListTypeRels, countryIDWatsonListTypeRels, guardianNameWatsonListTypeRels, nameWatsonListTypeRels, parentNameWatsonListTypeRels, vocationalTrainingWatsonListTypeRels, null));

			jsonObject.put("errors", watsonChild.getChildFormErrors());

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("child-was-not-saved"), jsonObject);

			return;
		}

		watsonChild.add();

		WatsonListTypeRel.batchAdd(activitiesInvolvedWatsonListTypeRels, watsonChild);

		WatsonListTypeRel.batchAdd(countryIDWatsonListTypeRels);

		WatsonListTypeRel.batchAdd(guardianNameWatsonListTypeRels);

		WatsonListTypeRel.batchAdd(nameWatsonListTypeRels);

		WatsonListTypeRel.batchAdd(parentNameWatsonListTypeRels);

		WatsonListTypeRel.batchAdd(vocationalTrainingWatsonListTypeRels, watsonChild);

		WatsonHistory.add(watsonChild.getWatsonChildId(), watsonChild, request, WatsonHistory.HISTORY_TYPE_CREATED);

		respondWith(translate("child-saved-successfully"), WatsonChild.getAsJSONObject(watsonChild));
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

		long watsonChildId = ParamUtil.getLong(request, "id");

		WatsonChild watsonChild = WatsonChild.fetch(watsonChildId);

		if (!WatsonPermission.check(user, watsonChild)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
		}

		WatsonListTypeRel.clear(watsonChild);

		WatsonRelationship.clearWatsonRelationships(watsonChild.getWatsonChildId(), watsonChildId);

		WatsonHistory.add(watsonChild.getWatsonChildId(), watsonChild, request, WatsonHistory.HISTORY_TYPE_DELETED);

		watsonChild.delete();

		respondWith(StringPool.BLANK);
	}

	public void edit() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonChildId = ParamUtil.getLong(request, "id");

		WatsonChild watsonChild = WatsonChild.fetch(watsonChildId);

		if (!WatsonPermission.check(user, watsonChild)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
		}

		respondWith(WatsonChild.getAsJSONObject(watsonChild));
	}

	public void fetchTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.TRANSLATOR)) {
			long watsonChildId = ParamUtil.getLong(request, "id");

			WatsonChild watsonChild = WatsonChild.fetch(watsonChildId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			respondWith(WatsonChild.getAsJSONObject(watsonChild, locale));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		int start = ParamUtil.getInteger(request, "start", QueryUtil.ALL_POS);
		int end = ParamUtil.getInteger(request, "end", QueryUtil.ALL_POS);

		String sort = ParamUtil.getString(request, "sortBy", null);

		List<WatsonChild> watsonChildren = WatsonChild.queryRange(sort, start, end, "status", WorkflowConstants.STATUS_APPROVED);

		respondWith(WatsonChild.getAsJSONDataArray(watsonChildren, WatsonChild.count("status", WorkflowConstants.STATUS_APPROVED)));
	}

	public void refreshSubModel() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		String model = ParamUtil.getString(request, "model");
		long watsonChildId = ParamUtil.getLong(request, "id");

		JSONArray idJSONArray = JSONFactoryUtil.createJSONArray();

		if (model.equals("activities")) {
			idJSONArray = WatsonActivity.getIdJSONArray(watsonChildId);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("id", watsonChildId);
		jsonObject.put("idArray", idJSONArray);
		jsonObject.put("model", model);

		respondWith(jsonObject);
	}

	public void requestTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonChildId = ParamUtil.getLong(request, "watsonPrimaryKey");

		WatsonChild watsonChild = WatsonChild.fetch(watsonChildId);

		String translationURL = ParamUtil.getString(request, "translationURL");

		WatsonWorkflowUtil.sendTranslationEmail(user, getModelProperName(WatsonChild.baseModelClass, locale), watsonChild.getPrimaryName(), translationURL);

		respondWith(StringPool.BLANK);
	}

	public void search() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		SearchContext searchContext = getPopulatedSearchContext(WatsonChild.baseModelClass);

		List<WatsonChild> searchResultWatsonChildren = _doSearch(searchContext);

		respondWith(WatsonChild.getAsJSONDataArray(searchResultWatsonChildren, getTotalHits(searchContext)));
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonChildId = ParamUtil.getLong(request, "id");

		if (watsonChildId == 0) {
			this.add();

			return;
		}

		WatsonChild watsonChild = WatsonChild.fetch(watsonChildId);

		if (!WatsonPermission.check(user, watsonChild)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), WatsonChild.getAsJSONObject(watsonChild));

			return;
		}

		watsonChild.setFields(request);

		long[] activitiesInvolvedWatsonListTypeLongs = ParamUtil.getLongValues(request, "activitiesInvolvedWatsonListTypeRels");

		List<Long> activitiesInvolvedListTypeIds = new ArrayList<>();

		for (long activitiesInvolvedWatsonListTypeLong : activitiesInvolvedWatsonListTypeLongs) {
			activitiesInvolvedListTypeIds.add(activitiesInvolvedWatsonListTypeLong);
		}

		Object[] generatedActivitiesInvolvedWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonChild, watsonChild.getModelClassName().concat(".activitiesInvolved"), activitiesInvolvedListTypeIds);

		List<WatsonListTypeRel> activitiesInvolvedWatsonListTypeRels = (List<WatsonListTypeRel>)generatedActivitiesInvolvedWatsonListTypeRelChanges[0];

		List<WatsonListTypeRel> removableActivitiesInvolvedWatsonListTypeRels = (List<WatsonListTypeRel>)generatedActivitiesInvolvedWatsonListTypeRelChanges[1];

		JSONArray countryIDWatsonListTypeRelJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "countryIDWatsonListTypeRels"));

		Object[] generatedCountryIDWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonChild, watsonChild.getModelClassName() + ".countryIDType", countryIDWatsonListTypeRelJSONArray);

		List<WatsonListTypeRel> countryIDWatsonListTypeRels = (List<WatsonListTypeRel>)generatedCountryIDWatsonListTypeRelChanges[0];

		List<WatsonListTypeRel> removableCountryIDWatsonListTypeRels = (List<WatsonListTypeRel>)generatedCountryIDWatsonListTypeRelChanges[1];

		JSONArray guardianNameWatsonListTypeRelsJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "guardianNameWatsonListTypeRels"));

		Object[] generatedGuardianNameWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonChild, watsonChild.getModelClassName() + ".guardianNameType", guardianNameWatsonListTypeRelsJSONArray);

		List<WatsonListTypeRel> guardianNameWatsonListTypeRels = (List<WatsonListTypeRel>)generatedGuardianNameWatsonListTypeRelChanges[0];

		List<WatsonListTypeRel> removableGuardianNameWatsonListTypeRels = (List<WatsonListTypeRel>)generatedGuardianNameWatsonListTypeRelChanges[1];

		JSONArray nameTypeWatsonListTypeRelJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "nameWatsonListTypeRels"));

		Object[] generatedNameWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonChild, watsonChild.getModelClassName() + ".nameType", nameTypeWatsonListTypeRelJSONArray);

		List<WatsonListTypeRel> nameWatsonListTypeRels = (List<WatsonListTypeRel>)generatedNameWatsonListTypeRelChanges[0];

		List<WatsonListTypeRel> removableNameWatsonListTypeRels = (List<WatsonListTypeRel>)generatedNameWatsonListTypeRelChanges[1];

		JSONArray parentNameWatsonListTypeRelsJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "parentNameWatsonListTypeRels"));

		Object[] generatedParentNameWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonChild, watsonChild.getModelClassName() + ".parentNameType", parentNameWatsonListTypeRelsJSONArray);

		List<WatsonListTypeRel> parentNameWatsonListTypeRels = (List<WatsonListTypeRel>)generatedParentNameWatsonListTypeRelChanges[0];

		List<WatsonListTypeRel> removableParentNameWatsonListTypeRels = (List<WatsonListTypeRel>)generatedParentNameWatsonListTypeRelChanges[1];

		long[] vocationalTrainingWatsonListTypeLongs = ParamUtil.getLongValues(request, "vocationalTrainingWatsonListTypeRels");

		List<Long> vocationalTrainingListTypeIds = new ArrayList<>();

		for (long vocationalTrainingWatsonListTypeLong : vocationalTrainingWatsonListTypeLongs) {
			vocationalTrainingListTypeIds.add(vocationalTrainingWatsonListTypeLong);
		}

		Object[] generatedVocationalTrainingWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonChild, watsonChild.getModelClassName().concat(".vocationalTraining"), vocationalTrainingListTypeIds);

		List<WatsonListTypeRel> vocationalTrainingWatsonListTypeRels = (List<WatsonListTypeRel>)generatedVocationalTrainingWatsonListTypeRelChanges[0];

		List<WatsonListTypeRel> removableVocationalTrainingWatsonListTypeRels = (List<WatsonListTypeRel>)generatedVocationalTrainingWatsonListTypeRelChanges[1];

		JSONArray watsonRelationshipJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "watsonRelationships"));

		Object[] generatedWatsonRelationships = WatsonRelationship.generateValidationData(request, watsonChild, watsonRelationshipJSONArray, watsonChild.getWatsonChildId());

		List<WatsonRelationship> watsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[0];

		List<WatsonRelationship> removableWatsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[1];

		if (!watsonChild.isValid(activitiesInvolvedWatsonListTypeRels, countryIDWatsonListTypeRels, guardianNameWatsonListTypeRels, nameWatsonListTypeRels, parentNameWatsonListTypeRels, vocationalTrainingWatsonListTypeRels, watsonRelationships)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("model", WatsonChild.getAsJSONObject(watsonChild, activitiesInvolvedWatsonListTypeRels, countryIDWatsonListTypeRels, guardianNameWatsonListTypeRels, nameWatsonListTypeRels, parentNameWatsonListTypeRels, vocationalTrainingWatsonListTypeRels, null));

			jsonObject.put("errors", watsonChild.getChildFormErrors());

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("child-was-not-saved"), jsonObject);

			return;
		}

		watsonChild.update();

		WatsonListTypeRel.batchUpdate(activitiesInvolvedWatsonListTypeRels);

		WatsonListTypeRel.batchDelete(removableActivitiesInvolvedWatsonListTypeRels);

		WatsonListTypeRel.batchUpdate(countryIDWatsonListTypeRels);

		WatsonListTypeRel.batchDelete(removableCountryIDWatsonListTypeRels);

		WatsonListTypeRel.batchUpdate(guardianNameWatsonListTypeRels);

		WatsonListTypeRel.batchDelete(removableGuardianNameWatsonListTypeRels);

		WatsonListTypeRel.batchUpdate(nameWatsonListTypeRels);

		WatsonListTypeRel.batchDelete(removableNameWatsonListTypeRels);

		WatsonListTypeRel.batchUpdate(parentNameWatsonListTypeRels);

		WatsonListTypeRel.batchDelete(removableParentNameWatsonListTypeRels);

		WatsonListTypeRel.batchUpdate(vocationalTrainingWatsonListTypeRels);

		WatsonListTypeRel.batchDelete(removableVocationalTrainingWatsonListTypeRels);

		WatsonRelationship.batchUpdate(watsonRelationships);

		WatsonRelationship.batchDelete(removableWatsonRelationships);

		WatsonHistory.add(watsonChild.getWatsonChildId(), watsonChild, request, WatsonHistory.HISTORY_TYPE_UPDATED);

		respondWith(translate("child-saved-successfully"), WatsonChild.getAsJSONObject(watsonChild));
	}

	public void updateTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.TRANSLATOR)) {
			long watsonChildId = ParamUtil.getLong(request, "id");

			WatsonChild watsonChild = WatsonChild.fetch(watsonChildId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			watsonChild.updateTranslations(request, locale);

			respondWith(translate("child-saved-successfully"), WatsonChild.getAsJSONObject(watsonChild));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonChildId = ParamUtil.getLong(request, "id");

		WatsonChild watsonChild = WatsonChild.fetch(watsonChildId);

		if (!WatsonPermission.check(user, watsonChild)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
		}

		respondWith(WatsonChild.getAsJSONObject(watsonChild));
	}

	@Override
	protected Indexer buildIndexer() {
		return WatsonChildrenIndexer.getInstance();
	}

	private List<WatsonChild> _doSearch(SearchContext searchContext) throws Exception {
		List<WatsonChild> watsonChildren = new ArrayList<>();

		Document[] documents = getDocuments(searchContext);

		for (Document document : documents) {
			long classPK = GetterUtil.getLong(document.get("entryClassPK"));

			watsonChildren.add(WatsonChild.fetch(classPK));
		}

		return watsonChildren;
	}

}
%>