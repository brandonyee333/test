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

<%@ include file="/alloy_mvc/jsp/watson/indexers/vehicle_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends WatsonAlloyControllerImpl {

	public AlloyControllerImpl() throws Exception {
		setAlloyServiceInvokerClass(WatsonVehicle.getBaseModelClass());
		setPermissioned(false);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonVehicle watsonVehicle = WatsonVehicle.create(request);

		if (!WatsonPermission.check(user, watsonVehicle.getWatsonIncidentId(), Constants.ADD)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		if (!watsonVehicle.isValid(null)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonVehicle.getVehicleFormErrors());

			jsonObject.put("model", WatsonVehicle.getAsJSONObject(watsonVehicle));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("vehicle-was-not-saved"), jsonObject);

			return;
		}

		watsonVehicle.add();

		WatsonHistory.add(watsonVehicle.getWatsonIncidentId(), watsonVehicle, request, WatsonHistory.HISTORY_TYPE_CREATED);

		respondWith(translate("vehicle-saved-successfully"), WatsonVehicle.getAsJSONObject(watsonVehicle));
	}

	public void autoComplete() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (!WatsonPermission.check(user, RoleConstants.INCIDENT_STAFF)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		SearchContext searchContext = getSearchContext(WatsonVehicle.baseModelClass, false);

		String keywords = ParamUtil.getString(request, "keywordQueryString");

		searchContext.setKeywords(keywords);

		respondWith(WatsonVehicle.getAsJSONDataArray(_doSearch(searchContext), getTotalHits(searchContext)));
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

		long watsonVehicleId = ParamUtil.getLong(request, "id");

		WatsonVehicle watsonVehicle = WatsonVehicle.fetch(watsonVehicleId);

		if (!WatsonPermission.check(user, watsonVehicle.getWatsonIncidentId(), Constants.DELETE)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		watsonVehicle.delete();

		WatsonRelationship.clearWatsonRelationships(watsonVehicle.getWatsonIncidentId(), watsonVehicleId);

		WatsonHistory.add(watsonVehicle.getWatsonIncidentId(), watsonVehicle, request, WatsonHistory.HISTORY_TYPE_DELETED);

		respondWith(StringPool.BLANK);
	}

	public void edit() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonVehicleId = ParamUtil.getLong(request, "id");

		WatsonVehicle watsonVehicle = WatsonVehicle.fetch(watsonVehicleId);

		if (!WatsonPermission.check(user, watsonVehicle.getWatsonIncidentId(), Constants.VIEW)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		respondWith(WatsonVehicle.getAsJSONObject(watsonVehicle));
	}

	public void executeImport() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonIncidentId = ParamUtil.getLong(request, "id");

		long[] watsonVehicleIds = ParamUtil.getLongValues(request, "selectedIds");

		List<WatsonVehicle> addedVehicles = new ArrayList<>();

		for (long watsonVehicleId : watsonVehicleIds) {
			WatsonVehicle originalWatsonVehicle = WatsonVehicle.fetch(watsonVehicleId);

			WatsonVehicle newWatsonVehicle = new WatsonVehicle((com.liferay.watson.model.WatsonVehicle)originalWatsonVehicle.clone());

			if (originalWatsonVehicle.getOriginalWatsonVehicleId() == 0) {
				newWatsonVehicle.setOriginalWatsonVehicleId(originalWatsonVehicle.getPrimaryKey());
			}
			else {
				newWatsonVehicle.setOriginalWatsonVehicleId(originalWatsonVehicle.getOriginalWatsonVehicleId());
			}

			newWatsonVehicle.setPrimaryKey(CounterLocalServiceUtil.increment());
			newWatsonVehicle.setWatsonIncidentId(watsonIncidentId);

			newWatsonVehicle = WatsonVehicle.add(newWatsonVehicle);

			WatsonHistory.add(newWatsonVehicle.getWatsonIncidentId(), newWatsonVehicle, request, WatsonHistory.HISTORY_TYPE_CREATED);

			addedVehicles.add(newWatsonVehicle);
		}

		respondWith(translate("vehicle-saved-successfully"), WatsonVehicle.getAsJSONDataArray(addedVehicles, WatsonIncident.getWatsonVehiclesCount(watsonIncidentId)));
	}

	public void fetchTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.WATSON_TRANSLATOR) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			long watsonVehicleId = ParamUtil.getLong(request, "id");

			WatsonVehicle watsonVehicle = WatsonVehicle.fetch(watsonVehicleId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			respondWith(WatsonVehicle.getAsJSONObject(watsonVehicle, locale));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (!WatsonPermission.check(user, RoleConstants.INCIDENT_STAFF)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		List<WatsonVehicle> returnWatsonVehicles = null;
		long watsonVehicleCount = 0;

		String actionType = ParamUtil.getString(request, "actionType");
		long watsonIncidentId = ParamUtil.getLong(request, "id");

		if (actionType.equals("import")) {
			int start = ParamUtil.getInteger(request, "start");
			int end = ParamUtil.getInteger(request, "end");

			returnWatsonVehicles = WatsonVehicle.getUnrelatedWatsonVehicles(watsonIncidentId, start, end);

			List<WatsonVehicle> watsonVehiclesCountList = WatsonVehicle.getUnrelatedWatsonVehicles(watsonIncidentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			watsonVehicleCount = watsonVehiclesCountList.size();
		}
		else {
			String[] fields = new String[0];
			String[] keywords = new String[0];

			if (watsonIncidentId > 0) {
				fields = new String[] {"watsonIncidentId"};
				keywords = new String[] {String.valueOf(watsonIncidentId)};
			}

			boolean includeInactive = ParamUtil.getBoolean(request, "includeInactive", false);

			SearchContext searchContext = getPopulatedSearchContext(WatsonVehicle.baseModelClass, fields, keywords, includeInactive);

			returnWatsonVehicles = _doSearch(searchContext);
			watsonVehicleCount = getTotalHits(searchContext);
		}

		respondWith(WatsonVehicle.getAsJSONDataArray(returnWatsonVehicles, watsonVehicleCount));
	}

	public void requestTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonVehicleId = ParamUtil.getLong(request, "watsonPrimaryKey");

		WatsonVehicle watsonVehicle = WatsonVehicle.fetch(watsonVehicleId);

		String translationURL = ParamUtil.getString(request, "translationURL");

		WatsonEmailUtil.sendTranslationEmail(user, getModelProperName(WatsonVehicle.baseModelClass, locale), watsonVehicle.getName(locale), translationURL);

		respondWith(StringPool.BLANK);
	}

	public void search() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (!WatsonPermission.check(user, RoleConstants.INCIDENT_STAFF)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		String[] fields = ParamUtil.getStringValues(request, "fields");
		String[] keywords = ParamUtil.getStringValues(request, "keywords");

		SearchContext searchContext = getPopulatedSearchContext(WatsonVehicle.baseModelClass, fields, keywords, false);

		List<WatsonVehicle> searchResultWatsonVehicles = _doSearch(searchContext);

		String actionType = ParamUtil.getString(request, "actionType");

		if (actionType.equals("import")) {
			long watsonIncidentId = ParamUtil.getLong(request, "watsonIncidentId");

			List<WatsonVehicle> unrelatedWatsonVehicles = WatsonVehicle.getUnrelatedWatsonVehicles(watsonIncidentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			List<WatsonVehicle> importableWatsonVehicles = ListUtil.copy(searchResultWatsonVehicles);

			for (WatsonVehicle searchResultVehicle : searchResultWatsonVehicles) {
				if (!unrelatedWatsonVehicles.contains(searchResultVehicle)) {
					importableWatsonVehicles.remove(searchResultVehicle);
				}
			}

			searchResultWatsonVehicles = importableWatsonVehicles;
		}

		respondWith(WatsonVehicle.getAsJSONDataArray(searchResultWatsonVehicles, getTotalHits(searchContext)));
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonVehicleId = ParamUtil.getLong(request, "id");

		if (watsonVehicleId == 0) {
			this.add();

			return;
		}

		WatsonVehicle watsonVehicle = WatsonVehicle.fetch(watsonVehicleId);

		if (!WatsonPermission.check(user, watsonVehicle.getWatsonIncidentId(), Constants.UPDATE) || WatsonIncident.hasDisabled(user.getUserId(), WatsonIncident.getIncidentStatus(watsonVehicle.getWatsonIncidentId()))) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), WatsonVehicle.getAsJSONObject(watsonVehicle));

			return;
		}

		watsonVehicle.setFields(request);

		long watsonIncidentId = watsonVehicle.getWatsonIncidentId();

		JSONArray watsonRelationshipJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "watsonRelationships"));

		Object[] generatedWatsonRelationships = WatsonRelationship.generateValidationData(request, watsonVehicle, watsonRelationshipJSONArray, watsonIncidentId);

		List<WatsonRelationship> watsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[0];

		List<WatsonRelationship> removableWatsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[1];

		if (!watsonVehicle.isValid(watsonRelationships)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonVehicle.getVehicleFormErrors());

			jsonObject.put("model", WatsonVehicle.getAsJSONObject(watsonVehicle, watsonRelationships));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("vehicle-was-not-saved"), jsonObject);

			return;
		}

		watsonVehicle.update();

		WatsonRelationship.batchUpdate(watsonRelationships);

		WatsonRelationship.batchDelete(removableWatsonRelationships);

		respondWith(translate("vehicle-saved-successfully"), WatsonVehicle.getAsJSONObject(watsonVehicle));
	}

	public void updateTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.WATSON_TRANSLATOR) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			long watsonVehicleId = ParamUtil.getLong(request, "id");

			WatsonVehicle watsonVehicle = WatsonVehicle.fetch(watsonVehicleId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			watsonVehicle.updateTranslations(request, locale);

			respondWith(translate("vehicle-saved-successfully"), WatsonVehicle.getAsJSONObject(watsonVehicle));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonVehicleId = ParamUtil.getLong(request, "id");

		WatsonVehicle watsonVehicle = WatsonVehicle.fetch(watsonVehicleId);

		if (!WatsonPermission.check(user, watsonVehicle.getWatsonIncidentId(), Constants.VIEW)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		respondWith(WatsonVehicle.getAsJSONObject(watsonVehicle));
	}

	@Override
	protected Indexer buildIndexer() {
		return WatsonVehicleIndexer.getInstance();
	}

	private List<WatsonVehicle> _doSearch(SearchContext searchContext) throws Exception {
		List<WatsonVehicle> watsonVehicles = new ArrayList<>();

		Document[] documents = getDocuments(searchContext);

		for (Document document : documents) {
			long classPK = GetterUtil.getLong(document.get("entryClassPK"));

			watsonVehicles.add(WatsonVehicle.fetch(classPK));
		}

		return watsonVehicles;
	}

}
%>