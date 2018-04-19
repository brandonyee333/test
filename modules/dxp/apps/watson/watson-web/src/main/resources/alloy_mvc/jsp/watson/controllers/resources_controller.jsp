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

<%@ include file="/alloy_mvc/jsp/watson/indexers/resource_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends WatsonAlloyControllerImpl {

	public AlloyControllerImpl() throws Exception {
		setAlloyServiceInvokerClass(WatsonResource.getBaseModelClass());
		setPermissioned(false);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonResource watsonResource = WatsonResource.create(request);

		if (!WatsonPermission.check(user, watsonResource.getWatsonIncidentId(), Constants.ADD)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		if (!watsonResource.isValid(null)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonResource.getResourceFormErrors());

			jsonObject.put("model", WatsonResource.getAsJSONObject(watsonResource));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("resource-was-not-saved"), jsonObject);

			return;
		}

		watsonResource.add();

		WatsonHistory.add(watsonResource.getWatsonIncidentId(), watsonResource, request, WatsonHistory.HISTORY_TYPE_CREATED);

		respondWith(translate("resource-saved-successfully"), WatsonResource.getAsJSONObject(watsonResource));
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

		long watsonResourceId = ParamUtil.getLong(request, "id");

		WatsonResource watsonResource = WatsonResource.fetch(watsonResourceId);

		if (!WatsonPermission.check(user, watsonResource.getWatsonIncidentId(), Constants.DELETE)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		watsonResource.delete();

		WatsonRelationship.clearWatsonRelationships(watsonResource.getWatsonIncidentId(), watsonResourceId);

		WatsonHistory.add(watsonResource.getWatsonIncidentId(), watsonResource, request, WatsonHistory.HISTORY_TYPE_DELETED);

		respondWith(StringPool.BLANK);
	}

	public void edit() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonResourceId = ParamUtil.getLong(request, "id");

		WatsonResource watsonResource = WatsonResource.fetch(watsonResourceId);

		if (!WatsonPermission.check(user, watsonResource.getWatsonIncidentId(), Constants.EDIT)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		respondWith(WatsonResource.getAsJSONObject(watsonResource));
	}

	public void executeImport() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonIncidentId = ParamUtil.getLong(request, "id");

		long[] watsonResourceIds = ParamUtil.getLongValues(request, "selectedIds");

		List<WatsonResource> addedResources = new ArrayList<>();

		for (long watsonResourceId : watsonResourceIds) {
			WatsonResource originalWatsonResource = WatsonResource.fetch(watsonResourceId);

			WatsonResource newWatsonResource = new WatsonResource((com.liferay.watson.model.WatsonResource)originalWatsonResource.clone());

			if (originalWatsonResource.getOriginalWatsonResourceId() == 0) {
				newWatsonResource.setOriginalWatsonResourceId(originalWatsonResource.getPrimaryKey());
			}
			else {
				newWatsonResource.setOriginalWatsonResourceId(originalWatsonResource.getOriginalWatsonResourceId());
			}

			newWatsonResource.setPrimaryKey(CounterLocalServiceUtil.increment());

			newWatsonResource.setWatsonIncidentId(watsonIncidentId);

			newWatsonResource = WatsonResource.add(newWatsonResource);

			WatsonHistory.add(newWatsonResource.getWatsonIncidentId(), newWatsonResource, request, WatsonHistory.HISTORY_TYPE_CREATED);

			addedResources.add(newWatsonResource);
		}

		respondWith(translate("resource-saved-successfully"), WatsonResource.getAsJSONDataArray(addedResources, WatsonIncident.getWatsonResourcesCount(watsonIncidentId)));
	}

	public void fetchTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.WATSON_TRANSLATOR) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			long watsonResourceId = ParamUtil.getLong(request, "id");

			WatsonResource watsonResource = WatsonResource.fetch(watsonResourceId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			respondWith(WatsonResource.getAsJSONObject(watsonResource, locale));

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

		SearchContext searchContext = getPopulatedSearchContext(WatsonResource.baseModelClass, fields, keywords, includeInactive);

		List<WatsonResource> searchResultWatsonResources = _doSearch(searchContext);

		String actionType = ParamUtil.getString(request, "actionType");

		if (actionType.equals("import")) {
			List<WatsonResource> unrelatedWatsonResources = WatsonResource.getUnrelatedWatsonResources(watsonIncidentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (WatsonResource watsonResources : searchResultWatsonResources) {
				if (!unrelatedWatsonResources.contains(watsonResources)) {
					searchResultWatsonResources.remove(watsonResources);
				}
			}
		}

		respondWith(WatsonResource.getAsJSONDataArray(searchResultWatsonResources, getTotalHits(searchContext)));
	}

	public void requestTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonResourceId = ParamUtil.getLong(request, "watsonPrimaryKey");

		WatsonResource watsonResource = WatsonResource.fetch(watsonResourceId);

		String translationURL = ParamUtil.getString(request, "translationURL");

		WatsonEmailUtil.sendTranslationEmail(user, getModelProperName(WatsonResource.baseModelClass, locale), watsonResource.getName(locale), translationURL);

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

		SearchContext searchContext = getPopulatedSearchContext(WatsonResource.baseModelClass, fields, keywords, false);

		List<WatsonResource> searchResultWatsonResources = _doSearch(searchContext);

		String actionType = ParamUtil.getString(request, "actionType");

		if (actionType.equals("import")) {
			long watsonIncidentId = ParamUtil.getLong(request, "watsonIncidentId");

			List<WatsonResource> unrelatedWatsonResources = WatsonResource.getUnrelatedWatsonResources(watsonIncidentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (WatsonResource watsonResources : searchResultWatsonResources) {
				if (!unrelatedWatsonResources.contains(watsonResources)) {
					searchResultWatsonResources.remove(watsonResources);
				}
			}
		}

		respondWith(WatsonResource.getAsJSONDataArray(searchResultWatsonResources, getTotalHits(searchContext)));
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonResourceId = ParamUtil.getLong(request, "id");

		if (watsonResourceId == 0) {
			this.add();

			return;
		}

		WatsonResource watsonResource = WatsonResource.fetch(watsonResourceId);

		if (!WatsonPermission.check(user, watsonResource.getWatsonIncidentId(), Constants.UPDATE) || WatsonIncident.hasDisabled(user.getUserId(), WatsonIncident.getIncidentStatus(watsonResource.getWatsonIncidentId()))) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), WatsonResource.getAsJSONObject(watsonResource));

			return;
		}

		watsonResource.setFields(request);

		long watsonIncidentId = watsonResource.getWatsonIncidentId();

		JSONArray watsonRelationshipJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "watsonRelationships"));

		Object[] generatedWatsonRelationships = WatsonRelationship.generateValidationData(request, watsonResource, watsonRelationshipJSONArray, watsonIncidentId);

		List<WatsonRelationship> watsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[0];

		List<WatsonRelationship> removableWatsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[1];

		if (!watsonResource.isValid(watsonRelationships)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonResource.getResourceFormErrors());

			jsonObject.put("model", WatsonResource.getAsJSONObject(watsonResource, watsonRelationships));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("resource-was-not-saved"), jsonObject);

			return;
		}

		watsonResource.update();

		WatsonRelationship.batchUpdate(watsonRelationships);

		WatsonRelationship.batchDelete(removableWatsonRelationships);

		WatsonHistory.add(watsonResource.getWatsonIncidentId(), watsonResource, request, WatsonHistory.HISTORY_TYPE_UPDATED);

		respondWith(translate("resource-saved-successfully"), WatsonResource.getAsJSONObject(watsonResource));
	}

	public void updateTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.WATSON_TRANSLATOR) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			long watsonResourceId = ParamUtil.getLong(request, "id");

			WatsonResource watsonResource = WatsonResource.fetch(watsonResourceId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			watsonResource.updateTranslations(request, locale);

			respondWith(translate("resource-saved-successfully"), WatsonResource.getAsJSONObject(watsonResource));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void upload() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.INCIDENT_STAFF) || WatsonPermission.check(user, RoleConstants.CHILDRENS_HOME_STAFF) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			UploadPortletRequest uploadPortletRequest = PortalUtil.getUploadPortletRequest(actionRequest);

			File file = uploadPortletRequest.getFile("file");

			if (Validator.isNotNull(file) && (file.length() > 0)) {
				JSONObject cropDataJSONObject = JSONFactoryUtil.createJSONObject(ParamUtil.getString(request, "cropData"));

				try {
					WatsonFileUploaderUtil.rotateImage(file);

					file = WatsonFileUploaderUtil.cropImage(cropDataJSONObject, file);
				}
				catch (Exception e) {
				}

				long classPK = ParamUtil.getLong(request, "classPK");

				String name = ParamUtil.getString(request, "name");

				respondWith(WatsonFileUploaderUtil.addDLFileEntry(user.getUserId(), user.getGroupId(), PortalUtil.getClassNameId("com.liferay.watson.model.WatsonResource"), classPK, WatsonPortletKeys.WATSON, file, name));

				return;
			}
		}

		respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("resource-was-not-saved"), null);
	}

	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonResourceId = ParamUtil.getLong(request, "id");

		WatsonResource watsonResource = WatsonResource.fetch(watsonResourceId);

		if (!WatsonPermission.check(user, watsonResource.getWatsonIncidentId(), Constants.VIEW)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
		}

		respondWith(WatsonResource.getAsJSONObject(watsonResource));
	}

	@Override
	protected Indexer buildIndexer() {
		return WatsonResourceIndexer.getInstance();
	}

	private List<WatsonResource> _doSearch(SearchContext searchContext) throws Exception {
		List<WatsonResource> watsonResources = new ArrayList<>();

		Document[] documents = getDocuments(searchContext);

		for (Document document : documents) {
			long classPK = GetterUtil.getLong(document.get("entryClassPK"));

			watsonResources.add(WatsonResource.fetch(classPK));
		}

		return watsonResources;
	}

}
%>