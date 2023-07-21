<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
JournalArticle article = journalDisplayContext.getArticle();

boolean neverExpire = ParamUtil.getBoolean(request, "neverExpire", true);

if ((article != null) && (article.getExpirationDate() != null)) {
	neverExpire = false;
}

boolean neverReview = ParamUtil.getBoolean(request, "neverReview", true);

if ((article != null) && (article.getReviewDate() != null)) {
	neverReview = false;
}
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="schedule"
/>

<aui:model-context bean="<%= article %>" model="<%= JournalArticle.class %>" />

<liferay-ui:error exception="<%= ArticleDisplayDateException.class %>" message="please-enter-a-valid-display-date" />
<liferay-ui:error exception="<%= ArticleExpirationDateException.class %>" message="please-enter-a-valid-expiration-date" />

<aui:input formName="fm1" name="displayDate" />

<aui:input dateTogglerCheckboxLabel="never-expire" disabled="<%= neverExpire %>" formName="fm1" name="expirationDate" />

<aui:input dateTogglerCheckboxLabel="never-review" disabled="<%= neverReview %>" formName="fm1" name="reviewDate" />