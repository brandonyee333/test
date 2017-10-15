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
		setPermissioned(true);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonResource watsonResource = WatsonResource.create(request);

		if (!watsonResource.isValid(null)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonResource.getResourceFormErrors());

			jsonObject.put("model", WatsonResource.getAsJSONObject(watsonResource));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, LanguageUtil.get(request, "resource-was-not-saved"), jsonObject);

			return;
		}

		watsonResource.add();

		WatsonHistory.add(watsonResource.getWatsonIncidentId(), watsonResource, request, WatsonHistory.HISTORY_TYPE_CREATED);

		respondWith(LanguageUtil.get(request, "resource-saved-successfully"), WatsonResource.getAsJSONObject(watsonResource));
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

		respondWith(LanguageUtil.get(request, "resource-saved-successfully"), WatsonResource.getAsJSONDataArray(addedResources, WatsonIncident.getWatsonResourcesCount(watsonIncidentId)));
	}

	public void fetchTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.hasPermission(user, RoleConstants.TRANSLATOR)) {
			long watsonResourceId = ParamUtil.getLong(request, "id");

			WatsonResource watsonResource = WatsonResource.fetch(watsonResourceId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			respondWith(WatsonResource.getAsJSONObject(watsonResource, locale));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		List<WatsonResource> watsonResources = null;
		long watsonResourcesCount = 0;

		long watsonIncidentId = ParamUtil.getLong(request, "id", 0);
		int start = ParamUtil.getInteger(request, "start", QueryUtil.ALL_POS);
		int end = ParamUtil.getInteger(request, "end", QueryUtil.ALL_POS);

		String actionType = ParamUtil.getString(request, "actionType");

		if (actionType.equals("import")) {
			watsonResources = WatsonResource.getUnrelatedWatsonResources(watsonIncidentId, start, end);

			List<WatsonResource> tempWatsonResources = WatsonResource.getUnrelatedWatsonResources(watsonIncidentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			watsonResourcesCount = tempWatsonResources.size();
		}
		else {
			boolean includeInactive = ParamUtil.getBoolean(request, "includeInactive", false);
			String sort = ParamUtil.getString(request, "sortBy", null);

			watsonResources = WatsonIncident.getWatsonResources(watsonIncidentId, includeInactive, sort, start, end);

			watsonResourcesCount = WatsonIncident.getWatsonResourcesCount(watsonIncidentId);
		}

		respondWith(WatsonResource.getAsJSONDataArray(watsonResources, watsonResourcesCount));
	}

	public void requestTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonResourceId = ParamUtil.getLong(request, "watsonPrimaryKey");

		WatsonResource watsonResource = WatsonResource.fetch(watsonResourceId);

		String translationURL = ParamUtil.getString(request, "translationURL");

		WatsonWorkflowUtil.sendTranslationEmail(user, getModelProperName(WatsonResource.baseModelClass, locale), watsonResource.getName(locale), translationURL);

		respondWith(StringPool.BLANK);
	}

	public void search() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		SearchContext searchContext = getPopulatedSearchContext(WatsonResource.baseModelClass);

		List<WatsonResource> searchResultWatsonResources = _doSearch(searchContext);

		String actionType = ParamUtil.getString(request, "actionType");

		if (actionType.equals("import")) {
			long watsonIncidentId = ParamUtil.getLong(request, "watsonIncidentId");

			List<WatsonResource> unrelatedWatsonResources = WatsonResource.getUnrelatedWatsonResources(watsonIncidentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (WatsonResource watsonResources : searchResultWatsonResources) {
				if (!unrelatedWatsonResources.contains(watsonResources)) {
					unrelatedWatsonResources.remove(watsonResources);
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

		if (WatsonIncident.hasDisabled(user.getUserId(), WatsonIncident.getIncidentStatus(watsonResource.getWatsonIncidentId()))) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), WatsonResource.getAsJSONObject(watsonResource));

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

			respondWith(HttpServletResponse.SC_BAD_REQUEST, LanguageUtil.get(request, "resource-was-not-saved"), jsonObject);

			return;
		}

		watsonResource.update();

		WatsonRelationship.batchUpdate(watsonRelationships);

		WatsonRelationship.batchDelete(removableWatsonRelationships);

		WatsonHistory.add(watsonResource.getWatsonIncidentId(), watsonResource, request, WatsonHistory.HISTORY_TYPE_UPDATED);

		respondWith(LanguageUtil.get(request, "resource-saved-successfully"), WatsonResource.getAsJSONObject(watsonResource));
	}

	public void updateTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.hasPermission(user, RoleConstants.TRANSLATOR)) {
			long watsonResourceId = ParamUtil.getLong(request, "id");

			WatsonResource watsonResource = WatsonResource.fetch(watsonResourceId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			watsonResource.updateTranslations(request, locale);

			respondWith(LanguageUtil.get(request, "resource-saved-successfully"), WatsonResource.getAsJSONObject(watsonResource));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void upload() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

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

			respondWith(WatsonFileUploaderUtil.addDLFileEntry(user.getUserId(), user.getGroupId(), PortalUtil.getClassNameId("com.liferay.watson.model.WatsonResource"), classPK, PortletKeys.WATSON, file, name));

			return;
		}

		respondWith(HttpServletResponse.SC_BAD_REQUEST, LanguageUtil.get(request, "resource-was-not-saved"), null);
	}

	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonResourceId = ParamUtil.getLong(request, "id");

		WatsonResource watsonResource = WatsonResource.fetch(watsonResourceId);

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