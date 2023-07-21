<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long zendeskCategoryId = ParamUtil.getLong(request, "zendeskCategoryId");

ZendeskCategory zendeskCategory = ZendeskCategoryLocalServiceUtil.getZendeskCategory(zendeskCategoryId);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);
%>

<liferay-frontend:info-bar>
	<%= HtmlUtil.escape(zendeskCategory.getDocumentationKey()) %>
</liferay-frontend:info-bar>

<portlet:actionURL name="importDocumentationArchive" var="importDocumentationArchiveURL">
	<portlet:param name="mvcPath" value="/admin/documentation_import.jsp" />
</portlet:actionURL>

<aui:form action="<%= importDocumentationArchiveURL %>" class="lfr-dynamic-form" cssClass="container-fluid-1280" enctype="multipart/form-data" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="zendeskCategoryId" type="hidden" value="<%= String.valueOf(zendeskCategoryId) %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input label="upload-an-updated-zip-file" name="file" type="file" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>