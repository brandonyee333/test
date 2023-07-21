<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/document_library/view_file_entry_types.jsp");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(LanguageUtil.get(request, "document-types"));
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<liferay-portlet:renderURL varImpl="searchURL">
		<portlet:param name="mvcPath" value="/document_library/view_file_entry_types.jsp" />
	</liferay-portlet:renderURL>

	<aui:nav-bar-search>
		<aui:form action="<%= searchURL %>" method="post" name="fm">
			<liferay-ui:input-search
				markupView="lexicon"
			/>
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<div class="container-fluid-1280 main-content-body">
	<liferay-ui:error exception="<%= RequiredFileEntryTypeException.class %>" message="cannot-delete-a-document-type-that-is-presently-used-by-one-or-more-documents" />

	<liferay-ui:search-container
		searchContainer='<%= new SearchContainer(renderRequest, new DisplayTerms(request), new DisplayTerms(request), SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, null, LanguageUtil.get(request, "there-are-no-results")) %>'
	>
		<liferay-ui:search-container-results>
			<%@ include file="/document_library/file_entry_type_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.document.library.kernel.model.DLFileEntryType"
			escapedModel="<%= true %>"
			keyProperty="fileEntryTypeId"
			modelVar="fileEntryType"
		>

			<%
			PortletURL rowURL = liferayPortletResponse.createRenderURL();

			rowURL.setParameter("mvcRenderCommandName", "/document_library/edit_file_entry_type");
			rowURL.setParameter("redirect", currentURL);
			rowURL.setParameter("fileEntryTypeId", String.valueOf(fileEntryType.getFileEntryTypeId()));
			%>

			<liferay-ui:search-container-column-text
				cssClass="text-strong"
				href="<%= rowURL %>"
				name="name"
				value="<%= fileEntryType.getName(locale) %>"
			/>

			<%
			Group group = GroupLocalServiceUtil.getGroup(fileEntryType.getGroupId());
			%>

			<liferay-ui:search-container-column-text
				name="scope"
				value="<%= LanguageUtil.get(request, group.getScopeLabel(themeDisplay)) %>"
			/>

			<liferay-ui:search-container-column-date
				name="modified-date"
				value="<%= fileEntryType.getModifiedDate() %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				cssClass="entry-action"
				path="/document_library/file_entry_type_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>

	<c:if test="<%= DLPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_DOCUMENT_TYPE) %>">
		<portlet:renderURL var="addFileEntryTypeURL">
			<portlet:param name="mvcPath" value="/document_library/edit_file_entry_type.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:renderURL>

		<liferay-frontend:add-menu>
			<liferay-frontend:add-menu-item
				title='<%= LanguageUtil.get(request, "add") %>'
				url="<%= addFileEntryTypeURL %>"
			/>
		</liferay-frontend:add-menu>
	</c:if>
</div>