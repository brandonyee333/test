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

<%@ include file="/init.jsp" %>

<%
AccountEntry accountEntry = (AccountEntry)request.getAttribute("summary.jsp-accountEntry");
%>

<h3>
	<liferay-ui:message key="project-summary" />
</h3>

<strong><liferay-ui:message key="code" />:</strong> <%= accountEntry.getCode() %>

<br />

<strong><liferay-ui:message key="industry" />:</strong> <%= LanguageUtil.get(locale, accountEntry.getIndustryLabel()) %>

<br />

<c:if test="<%= accountEntry.getPartnerEntryId() > 0 %>">

	<%
	PartnerEntry partnerEntry = accountEntry.getPartnerEntry();
	%>

	<strong><liferay-ui:message key="partner" /></strong> <%= HtmlUtil.escape(partnerEntry.getCode()) %>

	<br />

	<strong><liferay-ui:message key="partner-first-line-support" />:</strong> <%= LanguageUtil.get(locale, String.valueOf(accountEntry.getPartnerManagedSupport())) %>

	<br />
</c:if>

<strong><liferay-ui:message key="support-regions" />:</strong> <%= HtmlUtil.escape(ListUtil.toString(accountEntry.getSupportRegions(), "name", StringPool.COMMA_AND_SPACE)) %>

<br />

<strong><liferay-ui:message key="support-languages" />:</strong>

<%
String[] languageIds = accountEntry.getLanguageIds();

for (int i = 0; i < languageIds.length; i++) {
%>

	<%= LanguageUtil.get(locale, AccountEntryConstants.getLanguageLabel(languageIds[i])) %><%= (i < (languageIds.length - 1)) ? StringPool.COMMA_AND_SPACE : "" %>

<%
}
%>