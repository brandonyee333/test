<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="${testrayFactorOption.name}" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayFactorOption}" model="<%= TestrayFactorOption.class %>" />

<portlet:actionURL var="updateTestrayFactorOptionURL">
	<portlet:param name="controller" value="factor_options" />
	<portlet:param name="action" value="update" />
</portlet:actionURL>

<aui:form action="${updateTestrayFactorOptionURL}" method="post">
	<portlet:renderURL var="viewTestrayFactorOptionsURL">
		<portlet:param name="controller" value="factor_options" />
		<portlet:param name="action" value="index" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="${viewTestrayFactorOptionsURL}" />

	<aui:input name="id" type="hidden" value="${testrayFactorOption.testrayFactorOptionId}" />

	<aui:input autoFocus="${true}" name="name" />

	<aui:select label="type" name="testrayFactorCategoryId" showEmptyOption="${true}">
		<c:forEach items="${testrayFactorCategories}" var="testrayFactorCategory">
			<aui:option label="${testrayFactorCategory.name}" selected="${testrayFactorOption.testrayFactorCategoryId == testrayFactorCategory.testrayFactorCategoryId}" value="${testrayFactorCategory.testrayFactorCategoryId}" />
		</c:forEach>
	</aui:select>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="${viewTestrayFactorOptionsURL}" value="cancel" />
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>