<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<aui:nav-bar cssClass="collapse-basic-search" id="toolbar" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="forms" selected="<%= true %>" />
	</aui:nav>

	<c:if test="<%= ddlFormAdminDisplayContext.isShowSearch() %>">
		<aui:nav-bar-search>
			<aui:form action="<%= ddlFormAdminDisplayContext.getPortletURL() %>" method="post" name="fm1">
				<liferay-ui:input-search
					markupView="lexicon"
				/>
			</aui:form>
		</aui:nav-bar-search>
	</c:if>
</aui:nav-bar>