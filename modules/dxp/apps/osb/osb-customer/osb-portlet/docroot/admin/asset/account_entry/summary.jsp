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

<%@ include file="/init.jsp" %>

<%
AccountEntry accountEntry = (AccountEntry)request.getAttribute("summary.jsp-accountEntry");
%>

<h3>
	<liferay-ui:message key="project-summary" />
</h3>

<strong><liferay-ui:message key="code" />:</strong> <%= accountEntry.getCode() %>

<br />

<strong><liferay-ui:message key="industry" />:</strong> <%= LanguageUtil.get(request, accountEntry.getIndustryLabel()) %>

<br />

<c:if test="<%= accountEntry.getPartnerEntryId() > 0 %>">

	<%
	PartnerEntry partnerEntry = accountEntry.getPartnerEntry();
	%>

	<strong><liferay-ui:message key="partner" /></strong> <%= HtmlUtil.escape(partnerEntry.getCode()) %>

	<br />

	<strong><liferay-ui:message key="partner-first-line-support" />:</strong> <%= LanguageUtil.get(request, String.valueOf(accountEntry.getPartnerManagedSupport())) %>

	<br />
</c:if>

<strong><liferay-ui:message key="support-regions" />:</strong> <%= HtmlUtil.escape(ListUtil.toString(accountEntry.getSupportRegions(), "name", StringPool.COMMA_AND_SPACE)) %>

<br />

<strong><liferay-ui:message key="support-languages" />:</strong>

<%
String[] languageIds = accountEntry.getLanguageIds();

for (int i = 0; i < languageIds.length; i++) {
%>

	<%= LanguageUtil.get(request, AccountEntryConstants.getLanguageLabel(languageIds[i])) %><%= (i < (languageIds.length - 1)) ? StringPool.COMMA_AND_SPACE : "" %>

<%
}
%>