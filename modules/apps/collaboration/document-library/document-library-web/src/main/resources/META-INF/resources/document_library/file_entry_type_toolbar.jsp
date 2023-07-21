<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath", "/document_library/view_file_entry_types.jsp");

String toolbarItem = ParamUtil.getString(request, "toolbarItem");

boolean includeBasicFileEntryType = ParamUtil.getBoolean(request, "includeBasicFileEntryType");
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<portlet:renderURL var="viewFileEntryTypesURL">
			<portlet:param name="mvcPath" value="<%= mvcPath %>" />
			<portlet:param name="includeBasicFileEntryType" value="<%= String.valueOf(includeBasicFileEntryType) %>" />
		</portlet:renderURL>

		<c:if test="<%= DLPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_DOCUMENT_TYPE) %>">
			<portlet:renderURL var="addFileEntryTypeURL">
				<portlet:param name="mvcPath" value="/document_library/edit_file_entry_type.jsp" />
				<portlet:param name="redirect" value="<%= viewFileEntryTypesURL %>" />
			</portlet:renderURL>

			<aui:nav-item href="<%= addFileEntryTypeURL %>" iconCssClass="icon-plus" label="add" selected='<%= toolbarItem.equals("add") %>' />
		</c:if>
	</aui:nav>

	<aui:nav-bar-search>
		<div class="form-search">
			<liferay-portlet:renderURL varImpl="searchURL">
				<portlet:param name="mvcPath" value="<%= mvcPath %>" />
			</liferay-portlet:renderURL>

			<aui:form action="<%= searchURL %>" method="post" name="fm">
				<liferay-ui:input-search
					markupView="lexicon"
				/>
			</aui:form>
		</div>
	</aui:nav-bar-search>
</aui:nav-bar>