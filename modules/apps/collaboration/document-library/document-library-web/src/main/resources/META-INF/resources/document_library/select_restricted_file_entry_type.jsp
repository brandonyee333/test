<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectFileEntryType");
%>

<liferay-util:include page="/document_library/file_entry_type_toolbar.jsp" servletContext="<%= application %>">
	<liferay-util:param name="mvcPath" value="/document_library/select_restricted_file_entry_type.jsp" />
	<liferay-util:param name="includeBasicFileEntryType" value="<%= Boolean.TRUE.toString() %>" />
</liferay-util:include>

<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="mvcPath" value="/document_library/select_restricted_file_entry_type.jsp" />
	<portlet:param name="includeBasicFileEntryType" value="<%= Boolean.TRUE.toString() %>" />
</liferay-portlet:renderURL>

<aui:form action="<%= portletURL %>" method="post" name="selectFileEntryTypeFm">
	<liferay-ui:search-container
		searchContainer='<%= new SearchContainer(renderRequest, new DisplayTerms(request), new DisplayTerms(request), SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, null, LanguageUtil.get(request, "there-are-no-results")) %>'
	>
		<liferay-ui:search-container-results>
			<%@ include file="/document_library/file_entry_type_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.document.library.kernel.model.DLFileEntryType"
			keyProperty="fileEntryTypeId"
			modelVar="fileEntryType"
		>
			<liferay-ui:search-container-column-text
				name="name"
				value="<%= HtmlUtil.escape(fileEntryType.getName(locale)) %>"
			/>

			<liferay-ui:search-container-column-text>

				<%
				Map<String, Object> data = new HashMap<String, Object>();

				data.put("entityid", fileEntryType.getFileEntryTypeId());
				data.put("entityname", fileEntryType.getName(locale));
				data.put("fileentrytypeid", fileEntryType.getFileEntryTypeId());
				data.put("fileentrytypename", fileEntryType.getName(locale));
				%>

				<aui:button cssClass="selector-button" data="<%= data %>" value="choose" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	Liferay.Util.selectEntityHandler('#<portlet:namespace />selectFileEntryTypeFm', '<%= HtmlUtil.escapeJS(eventName) %>');
</aui:script>