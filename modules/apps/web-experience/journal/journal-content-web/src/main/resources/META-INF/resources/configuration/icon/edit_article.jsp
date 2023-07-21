<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:if test="<%= journalContentDisplayContext.isShowEditArticleIcon() %>">

	<%
	Map<String, Object> data = new HashMap<String, Object>();

	data.put("destroyOnHide", true);
	data.put("id", HtmlUtil.escape(portletDisplay.getNamespace()) + "editAsset");

	JournalArticle latestArticle = journalContentDisplayContext.getLatestArticle();

	data.put("title", HtmlUtil.escape(latestArticle.getTitle(locale)));
	%>

	<liferay-ui:icon
		data="<%= data %>"
		id="editWebContentIcon"
		message="edit-web-content"
		url="<%= journalContentDisplayContext.getURLEdit() %>"
		useDialog="<%= true %>"
	/>
</c:if>