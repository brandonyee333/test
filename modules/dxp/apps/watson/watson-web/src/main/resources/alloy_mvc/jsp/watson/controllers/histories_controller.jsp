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

<%@ include file="/alloy_mvc/jsp/watson/indexers/history_indexer.jspf" %>

<%!
	public static class AlloyControllerImpl extends WatsonAlloyControllerImpl {

		public AlloyControllerImpl() throws Exception {
			setAlloyServiceInvokerClass(WatsonHistory.getBaseModelClass());
			setPermissioned(true);
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

			long watsonIncidentId = ParamUtil.getLong(request, "id");

			if (!WatsonPermission.check(user, watsonIncidentId)) {
				respondWith(HttpServletResponse.SC_FORBIDDEN, LanguageUtil.get(request, "you-do-not-have-the-required-permissions-to-access-this-content"), JSONFactoryUtil.createJSONObject());

				return;
			}

			boolean includeInactive = ParamUtil.getBoolean(request, "includeInactive", false);
			int start = ParamUtil.getInteger(request, "start", QueryUtil.ALL_POS);
			int end = ParamUtil.getInteger(request, "end", QueryUtil.ALL_POS);

			List<WatsonHistory> watsonHistories = WatsonIncident.getWatsonHistories(watsonIncidentId, includeInactive, null, start, end);

			Collections.reverse(watsonHistories);

			JSONObject watsonHistoriesJSONObject = JSONFactoryUtil.createJSONObject();

			watsonHistoriesJSONObject.put("watsonHistories", WatsonHistory.getAsJSONArray(watsonHistories));
			watsonHistoriesJSONObject.put("watsonIncidentId", watsonIncidentId);

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