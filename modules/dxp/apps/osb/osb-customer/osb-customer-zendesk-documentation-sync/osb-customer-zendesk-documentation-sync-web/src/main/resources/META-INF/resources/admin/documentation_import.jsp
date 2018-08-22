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