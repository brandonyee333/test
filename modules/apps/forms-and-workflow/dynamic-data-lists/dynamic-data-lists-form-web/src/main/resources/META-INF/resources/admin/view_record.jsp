<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

DDLRecordVersion recordVersion = ddlFormAdminDisplayContext.getRecordVersion();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(LanguageUtil.get(request, "view-form"));
%>

<div class="container-fluid-1280">
	<c:if test="<%= recordVersion != null %>">
		<aui:model-context bean="<%= recordVersion %>" model="<%= DDLRecordVersion.class %>" />

		<div class="panel text-center">
			<aui:workflow-status markupView="lexicon" model="<%= DDLRecord.class %>" showHelpMessage="<%= false %>" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= recordVersion.getStatus() %>" version="<%= recordVersion.getVersion() %>" />
		</div>
	</c:if>

	<%= ddlFormAdminDisplayContext.getDDMFormHTML(renderRequest) %>
</div>