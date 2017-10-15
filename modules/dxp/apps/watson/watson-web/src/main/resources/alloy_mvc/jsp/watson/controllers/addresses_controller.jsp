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

<%@ include file="/alloy_mvc/jsp/watson/indexers/address_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends WatsonAlloyControllerImpl {

	public AlloyControllerImpl() throws Exception {
		setAlloyServiceInvokerClass(WatsonAddress.getBaseModelClass());
		setPermissioned(true);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonAddress watsonAddress = WatsonAddress.create(request);

		if (!watsonAddress.isValid(null)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonAddress.getAddressFormErrors());

			jsonObject.put("model", WatsonAddress.getAsJSONObject(watsonAddress));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, LanguageUtil.get(request, "address-was-not-saved"), jsonObject);

			return;
		}

		watsonAddress.add();

		WatsonHistory.add(watsonAddress.getWatsonIncidentId(), watsonAddress, request, WatsonHistory.HISTORY_TYPE_CREATED);

		respondWith(LanguageUtil.get(request, "address-saved-successfully"), WatsonAddress.getAsJSONObject(watsonAddress));
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

		long watsonAddressId = ParamUtil.getLong(request, "id");

		WatsonAddress watsonAddress = WatsonAddress.fetch(watsonAddressId);

		watsonAddress.delete();

		WatsonRelationship.clearWatsonRelationships(watsonAddress.getWatsonIncidentId(), watsonAddressId);

		WatsonHistory.add(watsonAddress.getWatsonIncidentId(), watsonAddress, request, WatsonHistory.HISTORY_TYPE_DELETED);

		respondWith(StringPool.BLANK);
	}

	public void edit() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonAddressId = ParamUtil.getLong(request, "id");

		WatsonAddress watsonAddress = WatsonAddress.fetch(watsonAddressId);

		respondWith(WatsonAddress.getAsJSONObject(watsonAddress));
	}

	public void executeImport() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		List<WatsonAddress> addedAddresses = new ArrayList<>();

		long watsonIncidentId = ParamUtil.getLong(request, "id");

		long[] watsonAddressIds = ParamUtil.getLongValues(request, "selectedIds");

		for (long watsonAddressId : watsonAddressIds) {
			WatsonAddress originalWatsonAddress = WatsonAddress.fetch(watsonAddressId);

			WatsonAddress newWatsonAddress = new WatsonAddress((com.liferay.watson.model.WatsonAddress)originalWatsonAddress.clone());

			if (originalWatsonAddress.getOriginalWatsonAddressId() == 0) {
				newWatsonAddress.setOriginalWatsonAddressId(originalWatsonAddress.getPrimaryKey());
			}
			else {
				newWatsonAddress.setOriginalWatsonAddressId(originalWatsonAddress.getOriginalWatsonAddressId());
			}

			newWatsonAddress.setPrimaryKey(CounterLocalServiceUtil.increment());
			newWatsonAddress.setWatsonIncidentId(watsonIncidentId);

			newWatsonAddress = WatsonAddress.add(newWatsonAddress);

			WatsonHistory.add(newWatsonAddress.getWatsonIncidentId(), newWatsonAddress, request, WatsonHistory.HISTORY_TYPE_CREATED);

			addedAddresses.add(newWatsonAddress);
		}

		respondWith(LanguageUtil.get(request, "address-saved-successfully"), WatsonAddress.getAsJSONDataArray(addedAddresses, WatsonIncident.getWatsonAddressesCount(watsonIncidentId)));
	}

	public void fetchTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.hasPermission(user, RoleConstants.TRANSLATOR)) {
			long watsonAddressId = ParamUtil.getLong(request, "id");

			WatsonAddress watsonAddress = WatsonAddress.fetch(watsonAddressId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			respondWith(WatsonAddress.getAsJSONObject(watsonAddress, locale));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		List<WatsonAddress> watsonAddresses = null;
		long watsonAddressCount = 0;

		long watsonIncidentId = ParamUtil.getLong(request, "id", 0);
		int start = ParamUtil.getInteger(request, "start", QueryUtil.ALL_POS);
		int end = ParamUtil.getInteger(request, "end", QueryUtil.ALL_POS);

		String actionType = ParamUtil.getString(request, "actionType");

		if (actionType.equals("import")) {
			watsonAddresses = WatsonAddress.getUnrelatedWatsonAddresses(watsonIncidentId, start, end);

			List<WatsonAddress> tempWatsonAddresses = WatsonAddress.getUnrelatedWatsonAddresses(watsonIncidentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			watsonAddressCount = tempWatsonAddresses.size();
		}
		else {
			boolean includeInactive = ParamUtil.getBoolean(request, "includeInactive", false);
			String sort = ParamUtil.getString(request, "sortBy", null);

			watsonAddresses = WatsonIncident.getWatsonAddresses(watsonIncidentId, includeInactive, sort, start, end);

			watsonAddressCount = WatsonIncident.getWatsonAddressesCount(watsonIncidentId);
		}

		respondWith(WatsonAddress.getAsJSONDataArray(watsonAddresses, watsonAddressCount));
	}

	public void requestTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonAddressId = ParamUtil.getLong(request, "watsonPrimaryKey");

		WatsonAddress watsonAddress = WatsonAddress.fetch(watsonAddressId);

		String translationURL = ParamUtil.getString(request, "translationURL");

		WatsonWorkflowUtil.sendTranslationEmail(user, getModelProperName(WatsonAddress.baseModelClass, locale), watsonAddress.getName(locale), translationURL);

		respondWith(StringPool.BLANK);
	}

	public void search() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		SearchContext searchContext = getPopulatedSearchContext(WatsonAddress.baseModelClass);

		List<WatsonAddress> searchResultWatsonAddresses = _doSearch(searchContext);

		String actionType = ParamUtil.getString(request, "actionType");

		if (actionType.equals("import")) {
			long watsonIncidentId = ParamUtil.getLong(request, "watsonIncidentId");

			List<WatsonAddress> unrelatedWatsonAddresses = WatsonAddress.getUnrelatedWatsonAddresses(watsonIncidentId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (WatsonAddress watsonAddress : searchResultWatsonAddresses) {
				if (!unrelatedWatsonAddresses.contains(watsonAddress)) {
					unrelatedWatsonAddresses.remove(watsonAddress);
				}
			}
		}

		respondWith(WatsonAddress.getAsJSONDataArray(searchResultWatsonAddresses, getTotalHits(searchContext)));
	}

	public void sendCoordinates() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonAddressId = ParamUtil.getLong(request, "id");

		WatsonAddress watsonAddress = WatsonAddress.fetch(watsonAddressId);

		boolean sendFullAddress = ParamUtil.getBoolean(request, "address");
		boolean sendCoordinates = ParamUtil.getBoolean(request, "coordinates");
		String description = ParamUtil.getString(request, "description");
		String toEmailAddress = ParamUtil.getString(request, "emailAddress");

		WatsonWorkflowUtil.sendAddressEmail(user, watsonAddress, sendFullAddress, sendCoordinates, description, toEmailAddress);

		respondWith(StringPool.BLANK);
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonAddressId = ParamUtil.getLong(request, "id");

		if (watsonAddressId == 0) {
			this.add();

			return;
		}

		WatsonAddress watsonAddress = WatsonAddress.fetch(watsonAddressId);

		if (WatsonIncident.hasDisabled(user.getUserId(), WatsonIncident.getIncidentStatus(watsonAddress.getWatsonIncidentId()))) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), WatsonAddress.getAsJSONObject(watsonAddress));

			return;
		}

		watsonAddress.setFields(request);

		long watsonIncidentId = watsonAddress.getWatsonIncidentId();

		JSONArray watsonRelationshipJSONArray = JSONFactoryUtil.createJSONArray(ParamUtil.getString(request, "watsonRelationships"));

		Object[] generatedWatsonRelationships = WatsonRelationship.generateValidationData(request, watsonAddress, watsonRelationshipJSONArray, watsonIncidentId);

		List<WatsonRelationship> watsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[0];

		List<WatsonRelationship> removableWatsonRelationships = (List<WatsonRelationship>)generatedWatsonRelationships[1];

		if (!watsonAddress.isValid(watsonRelationships)) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonAddress.getAddressFormErrors());

			jsonObject.put("model", WatsonAddress.getAsJSONObject(watsonAddress, watsonRelationships));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, LanguageUtil.get(request, "address-was-not-saved"), jsonObject);

			return;
		}

		watsonAddress.update();

		WatsonRelationship.batchUpdate(watsonRelationships);

		WatsonRelationship.batchDelete(removableWatsonRelationships);

		WatsonHistory.add(watsonAddress.getWatsonIncidentId(), watsonAddress, request, WatsonHistory.HISTORY_TYPE_UPDATED);

		respondWith(LanguageUtil.get(request, "address-saved-successfully"), WatsonAddress.getAsJSONObject(watsonAddress));
	}

	public void updateTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.hasPermission(user, RoleConstants.TRANSLATOR)) {
			long watsonAddressId = ParamUtil.getLong(request, "id");

			WatsonAddress watsonAddress = WatsonAddress.fetch(watsonAddressId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			watsonAddress.updateTranslations(request, locale);

			respondWith(LanguageUtil.get(request, "address-saved-successfully"), WatsonAddress.getAsJSONObject(watsonAddress));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonAddressId = ParamUtil.getLong(request, "id");

		WatsonAddress watsonAddress = WatsonAddress.fetch(watsonAddressId);

		respondWith(WatsonAddress.getAsJSONObject(watsonAddress));
	}

	@Override
	protected Indexer buildIndexer() {
		return WatsonAddressIndexer.getInstance();
	}

	private List<WatsonAddress> _doSearch(SearchContext searchContext) throws Exception {
		List<WatsonAddress> watsonAddresses = new ArrayList<>();

		Document[] documents = getDocuments(searchContext);

		for (Document document : documents) {
			long classPK = GetterUtil.getLong(document.get("entryClassPK"));

			watsonAddresses.add(WatsonAddress.fetch(classPK));
		}

		return watsonAddresses;
	}

}
%>