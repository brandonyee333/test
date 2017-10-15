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
		setPermissioned(true);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonVehicle watsonVehicle = WatsonVehicle.create(request);

		if (!watsonVehicle.isValid(null)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonVehicle.getVehicleFormErrors());

			jsonObject.put("model", WatsonVehicle.getAsJSONObject(watsonVehicle));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, LanguageUtil.get(request, "vehicle-was-not-saved"), jsonObject);

			return;
		}

		watsonVehicle.add();

		WatsonHistory.add(watsonVehicle.getWatsonIncidentId(), watsonVehicle, request, WatsonHistory.HISTORY_TYPE_CREATED);

		respondWith(LanguageUtil.get(request, "vehicle-saved-successfully"), WatsonVehicle.getAsJSONObject(watsonVehicle));
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

		respondWith(LanguageUtil.get(request, "vehicle-saved-successfully"), WatsonVehicle.getAsJSONDataArray(addedVehicles, WatsonIncident.getWatsonVehiclesCount(watsonIncidentId)));
	}

	public void fetchTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.hasPermission(user, RoleConstants.TRANSLATOR)) {
			long watsonVehicleId = ParamUtil.getLong(request, "id");

			WatsonVehicle watsonVehicle = WatsonVehicle.fetch(watsonVehicleId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			respondWith(WatsonVehicle.getAsJSONObject(watsonVehicle, locale));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		List<WatsonVehicle> watsonVehicles = null;
		long watsonVehiclesCount = 0;

		long watsonIncidentId = ParamUtil.getLong(request, "id", 0);
		int start = ParamUtil.getInteger(request, "start", QueryUtil.ALL_POS);
		int end = ParamUtil.getInteger(request, "end", QueryUtil.ALL_POS);

		String actionType = ParamUtil.getString(request, "actionType");

		if (actionType.equals("import")) {
			watsonVehicles = WatsonVehicle.getUnrelatedWatsonVehicles(watsonIncidentId, start, end);

			List<WatsonVehicle> tempWatsonVehicles = WatsonVehicle.getUnrelatedWatsonVehicles(watsonIncidentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			watsonVehiclesCount = tempWatsonVehicles.size();
		}
		else {
			boolean includeInactive = ParamUtil.getBoolean(request, "includeInactive", false);
			String sort = ParamUtil.getString(request, "sortBy", null);

			watsonVehicles = WatsonIncident.getWatsonVehicles(watsonIncidentId, includeInactive, sort, start, end);

			watsonVehiclesCount = WatsonIncident.getWatsonVehiclesCount(watsonIncidentId);
		}

		respondWith(WatsonVehicle.getAsJSONDataArray(watsonVehicles, watsonVehiclesCount));
	}

	public void requestTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonVehicleId = ParamUtil.getLong(request, "watsonPrimaryKey");

		WatsonVehicle watsonVehicle = WatsonVehicle.fetch(watsonVehicleId);

		String translationURL = ParamUtil.getString(request, "translationURL");

		WatsonWorkflowUtil.sendTranslationEmail(user, getModelProperName(WatsonVehicle.baseModelClass, locale), watsonVehicle.getName(locale), translationURL);

		respondWith(StringPool.BLANK);
	}

	public void search() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		SearchContext searchContext = getPopulatedSearchContext(WatsonVehicle.baseModelClass);

		List<WatsonVehicle> searchResultWatsonVehicles = _doSearch(searchContext);

		String actionType = ParamUtil.getString(request, "actionType");

		if (actionType.equals("import")) {
			long watsonIncidentId = ParamUtil.getLong(request, "watsonIncidentId");

			List<WatsonVehicle> unrelatedWatsonVehicles = WatsonVehicle.getUnrelatedWatsonVehicles(watsonIncidentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (WatsonVehicle watsonVehicle : searchResultWatsonVehicles) {
				if (!unrelatedWatsonVehicles.contains(watsonVehicle)) {
					unrelatedWatsonVehicles.remove(watsonVehicle);
				}
			}
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

		if (WatsonIncident.hasDisabled(user.getUserId(), WatsonIncident.getIncidentStatus(watsonVehicle.getWatsonIncidentId()))) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), WatsonVehicle.getAsJSONObject(watsonVehicle));

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

			respondWith(HttpServletResponse.SC_BAD_REQUEST, LanguageUtil.get(request, "vehicle-was-not-saved"), jsonObject);

			return;
		}

		watsonVehicle.update();

		WatsonRelationship.batchUpdate(watsonRelationships);

		WatsonRelationship.batchDelete(removableWatsonRelationships);

		respondWith(LanguageUtil.get(request, "vehicle-saved-successfully"), WatsonVehicle.getAsJSONObject(watsonVehicle));
	}

	public void updateTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.hasPermission(user, RoleConstants.TRANSLATOR)) {
			long watsonVehicleId = ParamUtil.getLong(request, "id");

			WatsonVehicle watsonVehicle = WatsonVehicle.fetch(watsonVehicleId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			watsonVehicle.updateTranslations(request, locale);

			respondWith(LanguageUtil.get(request, "vehicle-saved-successfully"), WatsonVehicle.getAsJSONObject(watsonVehicle));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonVehicleId = ParamUtil.getLong(request, "id");

		WatsonVehicle watsonVehicle = WatsonVehicle.fetch(watsonVehicleId);

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