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

<%@ include file="/account_entry_details/init.jsp" %>

<%
AccountEntry accountEntry = accountEntryViewDisplayContext.getAccountEntry();

JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

List<CorpProjectMessage> corpProjectMessages = CorpProjectMessageLocalServiceUtil.getCorpProjectMessages(accountEntry.getCorpProjectId());

if (!corpProjectMessage.isEmpty()) {
	for (CorpProjectMessage corpProjectMessage: corpProjectMessages) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("content", corpProjectMessage.getContent());
		jsonObject.put("severity", CorpProjectMessageConstants.getSeverityLevelLabel(corpProjectMessage.getSeverityLevel()));
		jsonObject.put("title", corpProjectMessage.getTitle());

		jsonArray.put(jsonObject);
	}
}
%>

<aui:row>
	<aui:col width="<%= 100 %>">
		<liferay-util:include page="/account_entry_details/account_environments.jsp" servletContext="<%= application %>" />
	</aui:col>
</aui:row>