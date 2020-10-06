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

<%@ include file="/alloy_mvc/jsp/watson/indexers/history_indexer.jspf" %>

<%!
public static class AlloyControllerImpl extends WatsonAlloyControllerImpl {

	public AlloyControllerImpl() throws Exception {
		setAlloyServiceInvokerClass(WatsonHistory.getBaseModelClass());
		setPermissioned(false);
	}

	public void add() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		WatsonHistory watsonHistory = WatsonHistory.create(request);

		if (!watsonHistory.isValid()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonHistory.getHistoryFormErrors());

			jsonObject.put("model", WatsonHistory.getAsJSONObject(watsonHistory));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("history-was-not-saved"), jsonObject);

			return;
		}

		watsonHistory.add();

		respondWith(translate("history-saved-successfully"), WatsonHistory.getAsJSONObject(watsonHistory));
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

		long watsonHistoryId = ParamUtil.getLong(request, "id");

		WatsonHistory watsonHistory = WatsonHistory.fetch(watsonHistoryId);

		watsonHistory.delete();

		respondWith(StringPool.BLANK);
	}

	public void edit() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}
	}

	public void index() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		if (!WatsonPermission.check(user, RoleConstants.CHILDRENS_HOME_STAFF) && !WatsonPermission.check(user, RoleConstants.INCIDENT_STAFF)) {
			respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

			return;
		}

		JSONObject watsonHistoriesJSONObject = JSONFactoryUtil.createJSONObject();

		List<WatsonHistory> watsonHistories = null;

		long watsonParentId = ParamUtil.getLong(request, "watsonParentId");
		boolean includeInactive = ParamUtil.getBoolean(request, "includeInactive");
		int start = ParamUtil.getInteger(request, "start", QueryUtil.ALL_POS);
		int end = ParamUtil.getInteger(request, "end", QueryUtil.ALL_POS);

		String model = ParamUtil.getString(request, "model");

		if (model.equals("incidents")) {
			watsonHistories = WatsonIncident.getWatsonHistories(watsonParentId, includeInactive, null, start, end);
		}
		else {
			watsonHistories = WatsonChild.getWatsonHistories(watsonParentId, includeInactive, null, start, end);
		}

		Collections.reverse(watsonHistories);

		watsonHistoriesJSONObject.put("watsonHistories", WatsonHistory.getAsJSONArray(watsonHistories));
		watsonHistoriesJSONObject.put("watsonParentId", watsonParentId);

		respondWith(watsonHistoriesJSONObject);
	}

	public void update() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonHistoryId = ParamUtil.getLong(request, "id");

		if (watsonHistoryId == 0) {
			this.add();

			return;
		}

		WatsonHistory watsonHistory = WatsonHistory.fetch(watsonHistoryId);

		watsonHistory.setFields(request);

		if (!watsonHistory.isValid()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("errors", watsonHistory.getHistoryFormErrors());

			jsonObject.put("model", WatsonHistory.getAsJSONObject(watsonHistory));

			respondWith(HttpServletResponse.SC_BAD_REQUEST, translate("history-was-not-saved"), jsonObject);

			return;
		}

		watsonHistory.update();

		respondWith(translate("history-saved-successfully"), WatsonHistory.getAsJSONObject(watsonHistory));
	}

	public void view() throws Exception {
		if (!isRespondingTo("json")) {
			return;
		}

		long watsonHistoryId = ParamUtil.getLong(request, "id");

		WatsonHistory watsonHistory = WatsonHistory.fetch(watsonHistoryId);

		respondWith(WatsonHistory.getAsJSONObject(watsonHistory));
	}

	@Override
	protected Indexer buildIndexer() {
		return WatsonHistoryIndexer.getInstance();
	}

}
%>