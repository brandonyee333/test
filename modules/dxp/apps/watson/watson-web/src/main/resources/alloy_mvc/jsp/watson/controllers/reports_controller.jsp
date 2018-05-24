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

<%@ include file="/alloy_mvc/jsp/watson/indexers/report_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends WatsonAlloyControllerImpl {

	public AlloyControllerImpl() throws Exception {
		setAlloyServiceInvokerClass(WatsonReport.getBaseModelClass());
		setPermissioned(false);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonReport watsonReport = WatsonReport.create(request);

		if (!WatsonPermission.check(user, WatsonChild.fetch(watsonReport.getWatsonChildId()), Constants.ADD)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		if (!watsonReport.isValid()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonReport.getReportFormErrors());
			jsonObject.put("model", WatsonReport.getAsJSONObject(watsonReport));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("report-was-not-saved"), jsonObject);

			return;
		}

		watsonReport.add();

		WatsonHistory.add(watsonReport.getWatsonChildId(), watsonReport, request, WatsonHistory.HISTORY_TYPE_CREATED);

		respondWith(translate("report-saved-successfully"), WatsonReport.getAsJSONObject(watsonReport));
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

		long watsonReportId = ParamUtil.getLong(request, "id");

		WatsonReport watsonReport = WatsonReport.fetch(watsonReportId);

		if (!WatsonPermission.check(user, WatsonChild.fetch(watsonReport.getWatsonChildId()), Constants.DELETE)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		watsonReport.delete();

		WatsonRelationship.clearWatsonRelationships(watsonReport.getWatsonChildId(), watsonReportId);

		WatsonHistory.add(watsonReport.getWatsonChildId(), watsonReport, request, WatsonHistory.HISTORY_TYPE_DELETED);

		respondWith(StringPool.BLANK);
	}

	public void edit() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonReportId = ParamUtil.getLong(request, "id");

		WatsonReport watsonReport = WatsonReport.fetch(watsonReportId);

		if (!WatsonPermission.check(user, WatsonChild.fetch(watsonReport.getWatsonChildId()), Constants.VIEW)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		respondWith(WatsonReport.getAsJSONObject(watsonReport));
	}

	public void fetchTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.WATSON_TRANSLATOR) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			long watsonReportId = ParamUtil.getLong(request, "id");

			WatsonReport watsonReport = WatsonReport.fetch(watsonReportId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			respondWith(WatsonReport.getAsJSONObject(watsonReport, locale));

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

		String key = ParamUtil.getString(request, "key");

		String[] fields = {"key"};
		String[] keywords = {key};

		long watsonChildId = ParamUtil.getLong(request, "id");

		if (watsonChildId > 0) {
			fields = new String[] {"watsonChildId", "key"};
			keywords = new String[] {String.valueOf(watsonChildId), String.valueOf(key)};
		}

		boolean includeInactive = ParamUtil.getBoolean(request, "includeInactive", false);

		SearchContext searchContext = getPopulatedSearchContext(WatsonReport.baseModelClass, fields, keywords, includeInactive);

		List<WatsonReport> searchResultWatsonReports = _doSearch(searchContext);

		respondWith(WatsonReport.getAsJSONDataArray(searchResultWatsonReports, getTotalHits(searchContext)));
	}

	public void requestTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonReportId = ParamUtil.getLong(request, "watsonPrimaryKey");

		WatsonReport watsonReport = WatsonReport.fetch(watsonReportId);

		String translationURL = ParamUtil.getString(request, "translationURL");

		WatsonEmailUtil.sendTranslationEmail(user, getModelProperName(WatsonReport.baseModelClass, locale), StringPool.BLANK, translationURL);

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

		String key = ParamUtil.getString(request, "key");

		fields = ArrayUtil.append(fields, "key");
		keywords = ArrayUtil.append(keywords, key);

		SearchContext searchContext = getPopulatedSearchContext(WatsonReport.baseModelClass, fields, keywords, false);

		List<WatsonReport> searchResultWatsonReports = _doSearch(searchContext);

		respondWith(WatsonReport.getAsJSONDataArray(searchResultWatsonReports, getTotalHits(searchContext)));
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonReportId = ParamUtil.getLong(request, "id");

		if (watsonReportId == 0) {
			this.add();

			return;
		}

		WatsonReport watsonReport = WatsonReport.fetch(watsonReportId);

		if (!WatsonPermission.check(user, WatsonChild.fetch(watsonReport.getWatsonChildId()), Constants.UPDATE)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), WatsonReport.getAsJSONObject(watsonReport));

			return;
		}

		watsonReport.setFields(request);

		if (!watsonReport.isValid()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonReport.getReportFormErrors());

			jsonObject.put("model", WatsonReport.getAsJSONObject(watsonReport));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("report-was-not-saved"), jsonObject);

			return;
		}

		watsonReport.update();

		WatsonHistory.add(watsonReport.getWatsonChildId(), watsonReport, request, WatsonHistory.HISTORY_TYPE_UPDATED);

		respondWith(translate("report-saved-successfully"), WatsonReport.getAsJSONObject(watsonReport));
	}

	public void updateTranslation() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (WatsonPermission.check(user, RoleConstants.WATSON_TRANSLATOR) || WatsonPermission.check(user, RoleConstants.WATSON_ADMINISTRATOR)) {
			long watsonReportId = ParamUtil.getLong(request, "id");

			WatsonReport watsonReport = WatsonReport.fetch(watsonReportId);

			String translatingTo = ParamUtil.getString(request, "translatingTo");

			Locale locale = LocaleUtil.fromLanguageId(translatingTo);

			watsonReport.updateTranslations(request, locale);

			respondWith(translate("report-saved-successfully"), WatsonReport.getAsJSONObject(watsonReport));

			return;
		}

		respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
	}

	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonReportId = ParamUtil.getLong(request, "id");

		WatsonReport watsonReport = WatsonReport.fetch(watsonReportId);

		if (!WatsonPermission.check(user, WatsonChild.fetch(watsonReport.getWatsonChildId()), Constants.VIEW)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, translate("you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());
		}

		respondWith(WatsonReport.getAsJSONObject(watsonReport));
	}

	@Override
	protected Indexer buildIndexer() {
		return WatsonReportIndexer.getInstance();
	}

	private List<WatsonReport> _doSearch(SearchContext searchContext) throws Exception {
		List<WatsonReport> watsonReports = new ArrayList<>();

		Document[] documents = getDocuments(searchContext);

		for (Document document : documents) {
			long classPK = GetterUtil.getLong(document.get("entryClassPK"));

			watsonReports.add(WatsonReport.fetch(classPK));
		}

		return watsonReports;
	}

}
%>