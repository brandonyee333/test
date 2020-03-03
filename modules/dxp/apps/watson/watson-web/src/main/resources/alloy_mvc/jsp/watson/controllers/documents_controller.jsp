<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/alloy_mvc/jsp/watson/controllers/init.jspf" %>

<%@ include file="/alloy_mvc/jsp/watson/indexers/document_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends WatsonAlloyControllerImpl {

	public AlloyControllerImpl() throws Exception {
		setAlloyServiceInvokerClass(WatsonDocument.getBaseModelClass());
		setPermissioned(false);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonDocument watsonDocument = WatsonDocument.create(request);

		if (!WatsonPermission.check(user, WatsonChild.fetch(watsonDocument.getWatsonChildId()), Constants.ADD)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		if (!watsonDocument.isValid()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonDocument.getDocumentFormErrors());

			jsonObject.put("model", WatsonDocument.getAsJSONObject(watsonDocument));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("document-was-not-saved"), jsonObject);

			return;
		}

		watsonDocument.add();

		WatsonHistory.add(watsonDocument.getWatsonChildId(), watsonDocument, request, WatsonHistory.HISTORY_TYPE_CREATED);

		respondWith(translate("document-saved-successfully"), WatsonDocument.getAsJSONObject(watsonDocument));
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

		long watsonDocumentId = ParamUtil.getLong(request, "id");

		WatsonDocument watsonDocument = WatsonDocument.fetch(watsonDocumentId);

		if (!WatsonPermission.check(user, WatsonChild.fetch(watsonDocument.getWatsonChildId()), Constants.DELETE)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		watsonDocument.delete();

		WatsonRelationship.clearWatsonRelationships(watsonDocument.getWatsonChildId(), watsonDocumentId);

		WatsonHistory.add(watsonDocument.getWatsonChildId(), watsonDocument, request, WatsonHistory.HISTORY_TYPE_DELETED);

		respondWith(StringPool.BLANK);
	}

	public void edit() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonDocumentId = ParamUtil.getLong(request, "id");

		WatsonDocument watsonDocument = WatsonDocument.fetch(watsonDocumentId);

		if (!WatsonPermission.check(user, WatsonChild.fetch(watsonDocument.getWatsonChildId()), Constants.VIEW)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		respondWith(WatsonDocument.getAsJSONObject(watsonDocument));
	}

	public void fetchTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.WATSON_TRANSLATOR) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			long watsonDocumentId = ParamUtil.getLong(request, "id");

			WatsonDocument watsonDocument = WatsonDocument.fetch(watsonDocumentId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			respondWith(WatsonDocument.getAsJSONObject(watsonDocument, locale));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (!WatsonPermission.check(user, RoleConstants.CHILDRENS_HOME_STAFF)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		String[] fields = new String[0];
		String[] keywords = new String[0];

		long watsonChildId = ParamUtil.getLong(request, "id");

		if (watsonChildId > 0) {
			fields = new String[] {"watsonChildId"};
			keywords = new String[] {String.valueOf(watsonChildId)};
		}

		boolean includeInactive = ParamUtil.getBoolean(request, "includeInactive");

		SearchContext searchContext = getPopulatedSearchContext(WatsonDocument.baseModelClass, fields, keywords, includeInactive);

		List<WatsonDocument> searchResultWatsonDocuments = _doSearch(searchContext);

		respondWith(WatsonDocument.getAsJSONDataArray(searchResultWatsonDocuments, getTotalHits(searchContext)));
	}

	public void requestTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonDocumentId = ParamUtil.getLong(request, "watsonPrimaryKey");

		WatsonDocument watsonDocument = WatsonDocument.fetch(watsonDocumentId);

		String translationURL = ParamUtil.getString(request, "translationURL");

		WatsonEmailUtil.sendTranslationEmail(user, getModelProperName(WatsonDocument.baseModelClass, locale), StringPool.BLANK, translationURL);

		respondWith(StringPool.BLANK);
	}

	public void search() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (!WatsonPermission.check(user, RoleConstants.CHILDRENS_HOME_STAFF)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		String[] fields = ParamUtil.getStringValues(request, "fields");
		String[] keywords = ParamUtil.getStringValues(request, "keywords");

		SearchContext searchContext = getPopulatedSearchContext(WatsonDocument.baseModelClass, fields, keywords, false);

		List<WatsonDocument> searchResultWatsonDocuments = _doSearch(searchContext);

		respondWith(WatsonDocument.getAsJSONDataArray(searchResultWatsonDocuments, getTotalHits(searchContext)));
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonDocumentId = ParamUtil.getLong(request, "id");

		if (watsonDocumentId == 0) {
			this.add();

			return;
		}

		WatsonDocument watsonDocument = WatsonDocument.fetch(watsonDocumentId);

		if (!WatsonPermission.check(user, WatsonChild.fetch(watsonDocument.getWatsonChildId()), Constants.UPDATE)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), WatsonDocument.getAsJSONObject(watsonDocument));

			return;
		}

		watsonDocument.setFields(request);

		if (!watsonDocument.isValid()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonDocument.getDocumentFormErrors());

			jsonObject.put("model", WatsonDocument.getAsJSONObject(watsonDocument));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("document-was-not-saved"), jsonObject);

			return;
		}

		watsonDocument.update();

		WatsonHistory.add(watsonDocument.getWatsonChildId(), watsonDocument, request, WatsonHistory.HISTORY_TYPE_UPDATED);

		respondWith(translate("document-saved-successfully"), WatsonDocument.getAsJSONObject(watsonDocument));
	}

	public void updateTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.WATSON_TRANSLATOR) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			long watsonDocumentId = ParamUtil.getLong(request, "id");

			WatsonDocument watsonDocument = WatsonDocument.fetch(watsonDocumentId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			watsonDocument.updateTranslations(request, locale);

			respondWith(translate("document-saved-successfully"), WatsonDocument.getAsJSONObject(watsonDocument));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonDocumentId = ParamUtil.getLong(request, "id");

		WatsonDocument watsonDocument = WatsonDocument.fetch(watsonDocumentId);

		if (!WatsonPermission.check(user, WatsonChild.fetch(watsonDocument.getWatsonChildId()), Constants.VIEW)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
		}

		respondWith(WatsonDocument.getAsJSONObject(watsonDocument));
	}

	@Override
	protected Indexer buildIndexer() {
		return WatsonDocumentIndexer.getInstance();
	}

	private List<WatsonDocument> _doSearch(SearchContext searchContext) throws Exception {
		List<WatsonDocument> watsonDocuments = new ArrayList<>();

		Document[] documents = getDocuments(searchContext);

		for (Document document : documents) {
			long classPK = GetterUtil.getLong(document.get("entryClassPK"));

			watsonDocuments.add(WatsonDocument.fetch(classPK));
		}

		return watsonDocuments;
	}

}
%>