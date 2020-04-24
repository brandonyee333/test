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