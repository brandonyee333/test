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

<%@ include file="/alloy_mvc/jsp/watson/indexers/activity_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends WatsonAlloyControllerImpl {

	public AlloyControllerImpl() throws Exception {
		setAlloyServiceInvokerClass(WatsonActivity.getBaseModelClass());
		setPermissioned(false);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonActivity watsonActivity = WatsonActivity.create(request);

		if (!WatsonPermission.check(user, watsonActivity.getWatsonIncidentId(), Constants.ADD)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		if (!watsonActivity.isValid(null)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonActivity.getActivityFormErrors());

			jsonObject.put("model", WatsonActivity.getAsJSONObject(watsonActivity));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("activity-was-not-saved"), jsonObject);

			return;
		}

		JSONArray watsonResourcesJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "activityResource"));

		for (int i = 0; i < watsonResourcesJSONArray.length(); i++) {
			WatsonResource watsonResource = WatsonResource.create(request, watsonResourcesJSONArray.getJSONObject(i));

			if (watsonResource.isValid(null)) {
				WatsonRelationship.create(request, watsonActivity, watsonResource, "watsonIncidentId", watsonActivity.getWatsonIncidentId());
			}
		}

		if (watsonActivity.getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			watsonActivity.setStatus(WorkflowConstants.STATUS_APPROVED);
		}

		watsonActivity.add();

		WatsonHistory.add(watsonActivity.getWatsonIncidentId(), watsonActivity, request, WatsonHistory.HISTORY_TYPE_CREATED);

		respondWith(translate("activity-saved-successfully"), WatsonActivity.getAsJSONObject(watsonActivity));
	}

	public void autoCreate() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonActivity watsonActivity = null;

		long watsonActivityId = ParamUtil.getLong(request, "id");

		if (watsonActivityId > 0) {
			watsonActivity = WatsonActivity.fetch(watsonActivityId);
		}
		else {
			long watsonIncidentId = ParamUtil.getLong(request, "watsonIncidentId");

			List<WatsonActivity> watsonActivities = WatsonActivity.queryAll("watsonIncidentId", watsonIncidentId, "status", WorkflowConstants.STATUS_INCOMPLETE, "userId", user.getUserId());

			if (!watsonActivities.isEmpty()) {
				watsonActivity = watsonActivities.get(0);
			}
			else {
				watsonActivity = WatsonActivity.create();
			}
		}

		watsonActivity.setFields(request);

		if (watsonActivity.getWatsonActivityId() == 0) {
			watsonActivity.setWatsonActivityId(CounterLocalServiceUtil.increment());
		}

		watsonActivity.update();

		respondWith(translate("activity-automatically-saved"), WatsonActivity.getAsJSONObject(watsonActivity));
	}

	public void autoSave() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonActivity watsonActivity = null;

		long watsonActivityId = ParamUtil.getLong(request, "id");

		if (watsonActivityId > 0) {
			watsonActivity = WatsonActivity.fetch(watsonActivityId);

			watsonActivity.setFields(request);

			if (watsonActivity.isValid(null)) {
				watsonActivity.update();

				respondWith(translate("activity-automatically-saved"), WatsonActivity.getAsJSONObject(watsonActivity));

				return;
			}
		}

		respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("activity-was-not-saved"), WatsonActivity.getAsJSONObject(watsonActivity));
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

		long watsonActivityId = ParamUtil.getLong(request, "id");

		WatsonActivity watsonActivity = WatsonActivity.fetch(watsonActivityId);

		if (!WatsonPermission.check(user, watsonActivity.getWatsonIncidentId(), Constants.DELETE)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		watsonActivity.delete();

		WatsonRelationship.clearWatsonRelationships(watsonActivity.getWatsonIncidentId(), watsonActivityId);

		WatsonHistory.add(watsonActivity.getWatsonIncidentId(), watsonActivity, request, WatsonHistory.HISTORY_TYPE_DELETED);

		respondWith(StringPool.BLANK);
	}

	public void edit() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonActivityId = ParamUtil.getLong(request, "id");

		WatsonActivity watsonActivity = WatsonActivity.fetch(watsonActivityId);

		if (!WatsonPermission.check(user, watsonActivity.getWatsonIncidentId(), Constants.VIEW)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		respondWith(WatsonActivity.getAsJSONObject(watsonActivity));
	}

	public void fetchDraft() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonIncidentId = ParamUtil.getLong(request, "watsonIncidentId");

		List<WatsonActivity> watsonActivities = WatsonActivity.queryAll("watsonIncidentId", watsonIncidentId, "status", WorkflowConstants.STATUS_INCOMPLETE, "userId", user.getUserId());

		if (!watsonActivities.isEmpty()) {
			respondWith(WatsonActivity.getAsJSONObject(watsonActivities.get(0)));

			return;
		}

		respondWith(StringPool.BLANK);
	}

	public void fetchTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.WATSON_TRANSLATOR) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			long watsonActivityId = ParamUtil.getLong(request, "id");

			WatsonActivity watsonActivity = WatsonActivity.fetch(watsonActivityId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			respondWith(WatsonActivity.getAsJSONObject(watsonActivity, locale));

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

		String[] fields = new String[0];
		String[] keywords = new String[0];

		long watsonIncidentId = ParamUtil.getLong(request, "id");

		if (watsonIncidentId > 0) {
			fields = new String[] {"watsonIncidentId"};
			keywords = new String[] {String.valueOf(watsonIncidentId)};
		}

		boolean includeInactive = ParamUtil.getBoolean(request, "includeInactive", false);

		SearchContext searchContext = getPopulatedSearchContext(WatsonActivity.baseModelClass, fields, keywords, includeInactive);

		respondWith(WatsonActivity.getAsJSONDataArray(_doSearch(searchContext), getTotalHits(searchContext)));
	}

	public void requestTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonActivityId = ParamUtil.getLong(request, "watsonPrimaryKey");

		WatsonActivity watsonActivity = WatsonActivity.fetch(watsonActivityId);

		String translationURL = ParamUtil.getString(request, "translationURL");

		WatsonWorkflowUtil.sendTranslationEmail(user, getModelProperName(WatsonActivity.baseModelClass, locale), watsonActivity.getName(locale), translationURL);

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

		SearchContext searchContext = getPopulatedSearchContext(WatsonActivity.baseModelClass, fields, keywords, false);

		respondWith(WatsonActivity.getAsJSONDataArray(_doSearch(searchContext), getTotalHits(searchContext)));
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonActivityId = ParamUtil.getLong(request, "id");

		if (watsonActivityId == 0) {
			this.add();

			return;
		}

		WatsonActivity watsonActivity = WatsonActivity.fetch(watsonActivityId);

		if (!WatsonPermission.check(user, watsonActivity.getWatsonIncidentId(), Constants.UPDATE) || !WatsonActivity.hasEditableStatus(user.getUserId(), watsonActivity.getUserId())) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		watsonActivity.setFields(request);

		if (watsonActivity.getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			watsonActivity.setStatus(WorkflowConstants.STATUS_APPROVED);
		}

		long watsonIncidentId = watsonActivity.getWatsonIncidentId();

		JSONArray watsonRelationshipJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "watsonRelationships"));

		Object[] generatedWatsonRelationships = WatsonRelationship.generateValidationData(request, watsonActivity, watsonRelationshipJSONArray, watsonIncidentId);

		List<WatsonRelationship> watsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[0];

		List<WatsonRelationship> removableWatsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[1];

		if (!watsonActivity.isValid(watsonRelationships)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonActivity.getActivityFormErrors());

			jsonObject.put("model", WatsonActivity.getAsJSONObject(watsonActivity, watsonRelationships));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("activity-was-not-saved"), jsonObject);

			return;
		}

		JSONArray watsonResourcesJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "activityResource"));

		for (int i = 0; i < watsonResourcesJSONArray.length(); i++) {
			WatsonResource watsonResource = WatsonResource.create(request, watsonResourcesJSONArray.getJSONObject(i));

			if (watsonResource.isValid(null)) {
				watsonRelationships.add(WatsonRelationship.create(request, watsonActivity, watsonResource, "watsonIncidentId", watsonIncidentId));
			}
		}

		watsonActivity.update();

		WatsonRelationship.batchUpdate(watsonRelationships);

		WatsonRelationship.batchDelete(removableWatsonRelationships);

		WatsonHistory.add(watsonActivity.getWatsonIncidentId(), watsonActivity, request, WatsonHistory.HISTORY_TYPE_UPDATED);

		respondWith(translate("activity-saved-successfully"), WatsonActivity.getAsJSONObject(watsonActivity));
	}

	public void updateTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.WATSON_TRANSLATOR) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			long watsonActivityId = ParamUtil.getLong(request, "id");

			WatsonActivity watsonActivity = WatsonActivity.fetch(watsonActivityId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			watsonActivity.updateTranslations(request, locale);

			boolean returnTranslatedValue = ParamUtil.getBoolean(request, "return", false);

			JSONObject watsonActivityJSONObject = null;

			if (returnTranslatedValue) {
				watsonActivityJSONObject = WatsonActivity.getAsJSONObject(watsonActivity, locale);
			}
			else {
				watsonActivityJSONObject = WatsonActivity.getAsJSONObject(watsonActivity);
			}

			respondWith(translate("activity-saved-successfully"), watsonActivityJSONObject);

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonActivityId = ParamUtil.getLong(request, "id");

		WatsonActivity watsonActivity = WatsonActivity.fetch(watsonActivityId);

		if (!WatsonPermission.check(user, watsonActivity.getWatsonIncidentId(), Constants.VIEW)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		respondWith(WatsonActivity.getAsJSONObject(watsonActivity));
	}

	@Override
	protected Indexer buildIndexer() {
		return WatsonActivityIndexer.getInstance();
	}

	private List<WatsonActivity> _doSearch(SearchContext searchContext) throws Exception {
		List<WatsonActivity> watsonActivities = new ArrayList<>();

		Document[] documents = getDocuments(searchContext);

		for (Document document : documents) {
			long classPK = GetterUtil.getLong(document.get("entryClassPK"));

			watsonActivities.add(WatsonActivity.fetch(classPK));
		}

		return watsonActivities;
	}

}
%>