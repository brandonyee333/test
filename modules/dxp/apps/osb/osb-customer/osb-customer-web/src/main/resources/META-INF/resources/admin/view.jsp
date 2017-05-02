<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/admin/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "import");

long kbFolderClassNameId = PortalUtil.getClassNameId(KBFolderConstants.getClassName());

long parentResourceClassNameId = ParamUtil.getLong(request, "parentResourceClassNameId", kbFolderClassNameId);
long parentResourcePrimKey = ParamUtil.getLong(request, "parentResourcePrimKey", KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/view.jsp");
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("parentResourcePrimKey", String.valueOf(parentResourcePrimKey));
%>

<liferay-ui:tabs
	names="general"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<%
AdminUtil.addPortletBreadcrumbEntries(parentResourceClassNameId, parentResourcePrimKey, "/admin/view.jsp", request, renderResponse);
%>

<liferay-ui:breadcrumb
	showCurrentGroup="<%= false %>"
	showGuestGroup="<%= false %>"
	showLayout="<%= false %>"
	showParentGroups="<%= false %>"
/>

<portlet:actionURL name="importFile" var="importFileURL">
	<portlet:param name="redirect" value="<%= currentURL %>" />
</portlet:actionURL>

<aui:form action="<%= importFileURL %>" class="lfr-dynamic-form" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="kbFolderId" type="hidden" value="<%= parentResourcePrimKey %>" />

	<liferay-ui:error exception="<%= KBArticleImportException.class %>">

		<%
		KBArticleImportException kbaie = (KBArticleImportException)errorException;
		%>

		<%= kbaie.getMessage() %>
	</liferay-ui:error>

	<aui:fieldset>
		<liferay-ui:search-container
			curParam="cur1"
			id="kbFoldersAdminSearchContainer"
		>

			<%
			searchContainer.setResults(KBFolderServiceUtil.getKBFolders(themeDisplay.getSiteGroupId(), parentResourcePrimKey, searchContainer.getStart(), searchContainer.getEnd()));
			searchContainer.setTotal(KBFolderServiceUtil.getKBFoldersCount(themeDisplay.getSiteGroupId(), parentResourcePrimKey));
			%>

			<liferay-ui:search-container-row
				className="com.liferay.knowledge.base.model.KBFolder"
				escapedModel="<%= true %>"
				keyProperty="kbFolderId"
				modelVar="kbFolder"
			>
				<liferay-portlet:renderURL varImpl="rowURL">
					<portlet:param name="mvcPath" value="/admin/view.jsp" />
					<portlet:param name="parentResourceClassNameId" value="<%= String.valueOf(kbFolder.getClassNameId()) %>" />
					<portlet:param name="parentResourcePrimKey" value="<%= String.valueOf(kbFolder.getKbFolderId()) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text
					name="folder"
				>
					<a class="icon-folder-open" href="<%= rowURL %>">
						<%= kbFolder.getName() %>
					</a>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="author"
					property="userName"
				/>

				<liferay-ui:search-container-column-date
					href="<%= rowURL %>"
					name="create-date"
					property="createDate"
				/>

				<liferay-ui:search-container-column-date
					href="<%= rowURL %>"
					name="modified-date"
					property="modifiedDate"
				/>
			</liferay-ui:search-container-row>

			<c:if test="<%= searchContainer.getTotal() == 0 %>">
				<liferay-ui:tabs
					names="import,export"
					param="tabs2"
					url="<%= portletURL.toString() %>"
				/>

				<c:choose>
					<c:when test='<%= tabs2.equals("export") %>'>
						<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="exportCSV" var="exportCSVURL">
							<portlet:param name="kbFolderId" value="<%= String.valueOf(parentResourcePrimKey) %>" />
						</liferay-portlet:resourceURL>

						<a class="btn" href="<%= exportCSVURL %>"><liferay-ui:message key="export-csv" /></a>

						<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="exportXML" var="exportXMLURL">
							<portlet:param name="kbFolderId" value="<%= String.valueOf(parentResourcePrimKey) %>" />
						</liferay-portlet:resourceURL>

						<a class="btn" href="<%= exportXMLURL %>"><liferay-ui:message key="export-xml" /></a>
					</c:when>
					<c:when test='<%= tabs2.equals("import") %>'>
						<aui:fieldset class="kb-block-labels">
							<aui:input id="file" label="upload-your-csv-file" name="file" type="file" />
						</aui:fieldset>

						<aui:button-row>
							<aui:button name="submit" type="submit" />

							<liferay-portlet:renderURL var="cancelURL">
								<portlet:param name="mvcPath" value="/admin/view.jsp" />
							</liferay-portlet:renderURL>

							<aui:button href="<%= cancelURL %>" type="cancel" />
						</aui:button-row>
					</c:when>
				</c:choose>
			</c:if>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>