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

<%@ include file="/alloy_mvc/jsp/watson/indexers/incident_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends WatsonAlloyControllerImpl {

	public AlloyControllerImpl() throws Exception {
		setAlloyServiceInvokerClass(WatsonIncident.getBaseModelClass());
		setPermissioned(false);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonIncident watsonIncident = WatsonIncident.create(request);

		if (!WatsonPermission.check(user, watsonIncident, Constants.ADD)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		long[] natureWatsonListTypeIdLongs = ParamUtil.getLongValues(request, "natureWatsonListType");

		List<Long> natureWatsonListTypeIds = new ArrayList<>();

		for (long natureWatsonListTypeId : natureWatsonListTypeIdLongs) {
			natureWatsonListTypeIds.add(natureWatsonListTypeId);
		}

		Object[] generatedWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonIncident, watsonIncident.getModelClassName().concat(".nature"), natureWatsonListTypeIds);

		List<WatsonListTypeRel> natureWatsonListTypeRels = (List<WatsonListTypeRel>)generatedWatsonListTypeRelChanges[0];

		WatsonAddress watsonAddress = WatsonAddress.createFromJSON(ParamUtil.getString(request, "address"), request, true);

		if (!watsonIncident.isValid(natureWatsonListTypeRels, null) || !watsonAddress.isValidWithoutWatsonIncidentId()) {
			if (isRespondingTo("json")) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				JSONObject incidentObject = WatsonIncident.getAsJSONObject(watsonIncident, natureWatsonListTypeRels, null);

				incidentObject.put("address", WatsonAddress.getAsJSONObject(watsonAddress));

				jsonObject.put("model", incidentObject);

				JSONObject errorsObject = JSONFactoryUtil.createJSONObject();

				errorsObject.put("incidentErrors", watsonIncident.getIncidentFormErrors());

				errorsObject.put("addressErrors", watsonAddress.getAddressFormErrors());

				jsonObject.put("errors", errorsObject);

				respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("incident-was-not-saved"), jsonObject);
			}

			WatsonListTypeRel.clear(watsonIncident);

			return;
		}

		watsonIncident.add();

		WatsonListTypeRel.batchAdd(natureWatsonListTypeRels, watsonIncident);

		WatsonActivity.add(watsonIncident.getWatsonIncidentId(), request);

		watsonAddress.add(watsonIncident.getWatsonIncidentId(), request);

		WatsonHistory.add(watsonIncident.getWatsonIncidentId(), watsonIncident, request, WatsonHistory.HISTORY_TYPE_CREATED);

		respondWith(translate("incident-saved-successfully"), WatsonIncident.getAsJSONObject(watsonIncident));
	}

	public void create() throws Exception {
		if (isRespondingTo("json")) {
			return;
		}
	}

	public void delete() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonIncidentId = ParamUtil.getLong(request, "id");

		WatsonIncident watsonIncident = WatsonIncident.fetch(watsonIncidentId);

		if (!WatsonPermission.check(user, watsonIncident, Constants.DELETE)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		WatsonIncidentRel.clear(watsonIncident);

		WatsonListTypeRel.clear(watsonIncident);

		WatsonRelationship.deleteAllWatsonIncidentRelationships(watsonIncidentId);

		WatsonIncident.clearWatsonIncidentChildren(watsonIncidentId);

		watsonIncident.delete();

		WatsonHistory.add(watsonIncident.getWatsonIncidentId(), watsonIncident, request, WatsonHistory.HISTORY_TYPE_DELETED);

		respondWith(StringPool.BLANK);
	}

	public void edit() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonIncidentId = ParamUtil.getLong(request, "id");

		WatsonIncident watsonIncident = WatsonIncident.fetch(watsonIncidentId);

		if (!WatsonPermission.check(user, watsonIncident, Constants.VIEW)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		respondWith(WatsonIncident.getAsJSONObject(watsonIncident));
	}

	public void fetchAffiliations() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (!WatsonPermission.check(user, RoleConstants.INCIDENT_STAFF)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		List<WatsonIncident> affiliatedIncidents = new ArrayList<>();

		String model = ParamUtil.getString(request, "model");
		long watsonId = ParamUtil.getLong(request, "id");

		if (model.equals("addresses")) {
			WatsonAddress watsonAddress = WatsonAddress.fetch(watsonId);

			affiliatedIncidents = WatsonAddress.getAffiliatedIncidents(watsonAddress);
		}
		else if (model.equals("people")) {
			WatsonPerson watsonPerson = WatsonPerson.fetch(watsonId);

			affiliatedIncidents = WatsonPerson.getAffiliatedIncidents(watsonPerson);
		}
		else if (model.equals("resources")) {
			WatsonResource watsonResource = WatsonResource.fetch(watsonId);

			affiliatedIncidents = WatsonResource.getAffiliatedIncidents(watsonResource);
		}
		else if (model.equals("vehicles")) {
			WatsonVehicle watsonVehicle = WatsonVehicle.fetch(watsonId);

			affiliatedIncidents = WatsonVehicle.getAffiliatedIncidents(watsonVehicle);
		}

		String[] fields = ParamUtil.getStringValues(request, "fields");
		String[] keywords = ParamUtil.getStringValues(request, "keywords");

		if ((fields.length > 0) && (keywords.length > 0)) {
			SearchContext searchContext = getPopulatedSearchContext(WatsonIncident.baseModelClass);

			List<Long> watsonIncidentIds = _doSearchForClassPKs(searchContext);

			List<WatsonIncident> matchedAffiliatedIncidents = new ArrayList<>();

			if (!watsonIncidentIds.isEmpty()) {
				for (WatsonIncident affiliatedIncident : affiliatedIncidents) {
					if (watsonIncidentIds.contains(affiliatedIncident.getWatsonIncidentId())) {
						matchedAffiliatedIncidents.add(affiliatedIncident);
					}
				}
			}

			affiliatedIncidents = matchedAffiliatedIncidents;
		}

		respondWith(WatsonIncident.getAsJSONDataArray(affiliatedIncidents, affiliatedIncidents.size()));
	}

	public void fetchMetrics() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.INCIDENT_MANAGER) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			String actionType = ParamUtil.getString(request, "actionType");

			if (actionType.equals("report")) {
				String dateRangeString = ParamUtil.getString(request, "dateRangeString");

				respondWith(WatsonMetricsUtil.getMetrics(dateRangeString));

				return;
			}
			else {
				List<WatsonAddress> watsonAddresses = new ArrayList<>();

				String[] fields = ParamUtil.getStringValues(request, "fields");
				String[] keywords = ParamUtil.getStringValues(request, "keywords");
				long typeWatsonListTypeId = ParamUtil.getLong(request, "type");

				if ((fields.length > 0) && (keywords.length > 0)) {
					SearchContext searchContext = getPopulatedSearchContext(WatsonIncident.baseModelClass);

					List<Long> watsonIncidentIds = _doSearchForClassPKs(searchContext);

					if (!watsonIncidentIds.isEmpty()) {
						List<WatsonAddress> typeWatsonAddresses = WatsonAddress.query("typeWatsonListTypeId", typeWatsonListTypeId);

						for (WatsonAddress watsonAddress : typeWatsonAddresses) {
							if (watsonIncidentIds.contains(watsonAddress.getWatsonIncidentId())) {
								watsonAddresses.add(watsonAddress);
							}
						}
					}
				}
				else {
					watsonAddresses = WatsonAddress.query("typeWatsonListTypeId", typeWatsonListTypeId);
				}

				respondWith(WatsonMetricsUtil.getAddressMetricsArray(watsonAddresses));

				return;
			}
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void fetchTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.WATSON_TRANSLATOR) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			long watsonIncidentId = ParamUtil.getLong(request, "id");

			WatsonIncident watsonIncident = WatsonIncident.fetch(watsonIncidentId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			respondWith(WatsonIncident.getAsJSONObject(watsonIncident, locale));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void importIncidentsFile() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.ADMINISTRATOR)) {
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);

			File file = uploadPortletRequest.getFile("file");

			String name = ParamUtil.getString(request, "name");

			name = StringUtil.toLowerCase(name);

			boolean success = WatsonDataMigrationUtil.importIncidentCSV(file, name, locale, request);

			if (success) {
				respondWith(translate("incident-saved-successfully"), JSONFactoryUtil.createJSONArray());

				return;
			}
		}

		respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("incident-was-not-saved"), null);
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (!WatsonPermission.check(user, RoleConstants.INCIDENT_STAFF)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		String sort = ParamUtil.getString(request, "sortBy", null);
		int start = ParamUtil.getInteger(request, "start", QueryUtil.ALL_POS);
		int end = ParamUtil.getInteger(request, "end", QueryUtil.ALL_POS);

		List<WatsonIncident> watsonIncidents = null;

		watsonIncidents = WatsonIncident.queryRange(sort, start, end, "status", WorkflowConstants.STATUS_APPROVED);

		String actionType = ParamUtil.getString(request, "actionType");

		if ((start == 0) && actionType.equals("relate")) {
			long watsonIncidentId = ParamUtil.getLong(request, "id", 0);

			watsonIncidents.add(WatsonIncident.fetch(watsonIncidentId));
		}

		respondWith(WatsonIncident.getAsJSONDataArray(watsonIncidents, WatsonIncident.count("status", WorkflowConstants.STATUS_APPROVED)));
	}

	public void refreshSubModel() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (!WatsonPermission.check(user, RoleConstants.INCIDENT_STAFF)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		String model = ParamUtil.getString(request, "model");
		long watsonIncidentId = ParamUtil.getLong(request, "id");

		JSONArray idJSONArray = JSONFactoryUtil.createJSONArray();

		if (model.equals("activities")) {
			idJSONArray = WatsonActivity.getIdJSONArray(watsonIncidentId);
		}
		else if (model.equals("addresses")) {
			idJSONArray = WatsonAddress.getIdJSONArray(watsonIncidentId);
		}
		else if (model.equals("people")) {
			idJSONArray = WatsonPerson.getIdJSONArray(watsonIncidentId);
		}
		else if (model.equals("resources")) {
			idJSONArray = WatsonResource.getIdJSONArray(watsonIncidentId);
		}
		else if (model.equals("vehicles")) {
			idJSONArray = WatsonVehicle.getIdJSONArray(watsonIncidentId);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("id", watsonIncidentId);
		jsonObject.put("idArray", idJSONArray);
		jsonObject.put("model", model);

		respondWith(jsonObject);
	}

	public void requestTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonIncidentId = ParamUtil.getLong(request, "watsonPrimaryKey");

		WatsonIncident watsonIncident = WatsonIncident.fetch(watsonIncidentId);

		String translationURL = ParamUtil.getString(request, "translationURL");

		WatsonWorkflowUtil.sendTranslationEmail(user, getModelProperName(WatsonIncident.baseModelClass, locale), watsonIncident.getName(), translationURL);

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

		SearchContext searchContext = getPopulatedSearchContext(WatsonIncident.baseModelClass);

		respondWith(WatsonIncident.getAsJSONDataArray(_doSearch(searchContext), getTotalHits(searchContext)));
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonIncidentId = ParamUtil.getLong(request, "id");

		WatsonIncident watsonIncident = WatsonIncident.fetch(watsonIncidentId);

		if (!WatsonPermission.check(user, watsonIncident, Constants.UPDATE) || WatsonIncident.hasDisabled(user.getUserId(), watsonIncident.getIncidentStatus())) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), WatsonIncident.getAsJSONObject(watsonIncident));

			return;
		}

		watsonIncident.setFields(request);

		long[] natureWatsonListTypeIdLongs = ParamUtil.getLongValues(request, "natureWatsonListType");

		List<Long> natureWatsonListTypeIds = new ArrayList<>();

		for (long natureWatsonListTypeId : natureWatsonListTypeIdLongs) {
			natureWatsonListTypeIds.add(natureWatsonListTypeId);
		}

		Object[] generatedWatsonListTypeRelChanges = WatsonListTypeRel.generateValidationData(request, watsonIncident, watsonIncident.getModelClassName().concat(".nature"), natureWatsonListTypeIds);

		List<WatsonListTypeRel> natureWatsonListTypeRels = (List<WatsonListTypeRel>)generatedWatsonListTypeRelChanges[0];

		List<WatsonListTypeRel> removableWatsonListTypeRels = (List<WatsonListTypeRel>)generatedWatsonListTypeRelChanges[1];

		long[] watsonIncidentRelatedIncidentIds = ParamUtil.getLongValues(request, "watsonIncidentRelIds");

		Object[] generatedWatsonRels = WatsonIncidentRel.generateValidationData(request, watsonIncident, watsonIncidentRelatedIncidentIds);

		List<WatsonIncidentRel> watsonIncidentRels = (List<WatsonIncidentRel>)generatedWatsonRels[0];

		List<WatsonIncidentRel> removableWatsonIncidentRels = (List<WatsonIncidentRel>)generatedWatsonRels[1];

		if (!watsonIncident.isValid(natureWatsonListTypeRels, watsonIncidentRels)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("model", WatsonIncident.getAsJSONObject(watsonIncident, natureWatsonListTypeRels, watsonIncidentRels));

			jsonObject.put("errors", watsonIncident.getIncidentFormErrors());

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("incident-was-not-saved"), jsonObject);

			return;
		}

		watsonIncident.update();

		WatsonListTypeRel.batchUpdate(natureWatsonListTypeRels);

		WatsonListTypeRel.batchDelete(removableWatsonListTypeRels);

		WatsonIncidentRel.batchUpdate(watsonIncidentRels);

		WatsonIncidentRel.batchDelete(removableWatsonIncidentRels);

		WatsonHistory.add(watsonIncident.getWatsonIncidentId(), watsonIncident, request, WatsonHistory.HISTORY_TYPE_UPDATED);

		respondWith(translate("incident-saved-successfully"), WatsonIncident.getAsJSONObject(watsonIncident));
	}

	public void updateTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.WATSON_TRANSLATOR) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			long watsonIncidentId = ParamUtil.getLong(request, "id");

			WatsonIncident watsonIncident = WatsonIncident.fetch(watsonIncidentId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			watsonIncident.updateTranslations(request, locale);

			respondWith(translate("incident-saved-successfully"), WatsonIncident.getAsJSONObject(watsonIncident));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonIncidentId = ParamUtil.getLong(request, "id");

		WatsonIncident watsonIncident = WatsonIncident.fetch(watsonIncidentId);

		if (!WatsonPermission.check(user, watsonIncident, Constants.VIEW)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		respondWith(WatsonIncident.getAsJSONObject(watsonIncident));
	}

	@Override
	protected Indexer buildIndexer() {
		return WatsonIncidentIndexer.getInstance();
	}

	private List<WatsonIncident> _doSearch(SearchContext searchContext) throws Exception {
		List<WatsonIncident> watsonIncidents = new ArrayList<>();

		Document[] documents = getDocuments(searchContext);

		for (Document document : documents) {
			long classPK = GetterUtil.getLong(document.get("entryClassPK"));

			watsonIncidents.add(WatsonIncident.fetch(classPK));
		}

		return watsonIncidents;
	}

	private List<Long> _doSearchForClassPKs(SearchContext searchContext) throws Exception {
		List<Long> watsonIncidentsIds = new ArrayList<>();

		Document[] documents = getDocuments(searchContext);

		for (Document document : documents) {
			watsonIncidentsIds.add(GetterUtil.getLong(document.get("entryClassPK")));
		}

		return watsonIncidentsIds;
	}

}
%>