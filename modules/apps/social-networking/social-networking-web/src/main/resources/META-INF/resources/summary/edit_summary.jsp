<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<portlet:renderURL var="redirectURL" windowState="<%= WindowState.NORMAL.toString() %>" />

<portlet:actionURL name="updateSummary" var="updateSummaryURL" />

<aui:form action="<%= updateSummaryURL %>" method="post" name="<portlet:namespace />fm">
	<aui:input name="redirect" type="hidden" value="<%= redirectURL %>" />

	<aui:model-context bean="<%= user2.getContact() %>" model="<%= Contact.class %>" />

	<div class="alert alert-info">
		<liferay-ui:message arguments="<%= themeDisplay.getURLMyAccount() %>" key="use-my-account-to-change-regular-account-settings" translateArguments="<%= false %>" />
	</div>

	<aui:input name="jobTitle" />

	<aui:input label="about-me" name="aboutMe" type="textarea" value='<%= HtmlUtil.escape(ExpandoValueLocalServiceUtil.getData(themeDisplay.getCompanyId(), User.class.getName(), "SN", "aboutMe", user2.getUserId(), StringPool.BLANK)) %>' />

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirectURL %>" value="cancel" />
	</aui:button-row>
</aui:form>