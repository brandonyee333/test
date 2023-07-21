<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
long repositoryId = GetterUtil.getLong((String)request.getAttribute("view.jsp-repositoryId"));

long folderId = GetterUtil.getLong((String)request.getAttribute("view.jsp-folderId"));

DLPortletInstanceSettingsHelper dlPortletInstanceSettingsHelper = new DLPortletInstanceSettingsHelper(dlRequestHelper);
%>

<c:if test="<%= dlPortletInstanceSettingsHelper.isShowTabs() || dlPortletInstanceSettingsHelper.isShowSearch() %>">
	<aui:nav-bar cssClass='<%= dlPortletInstanceSettingsHelper.isShowSearch() ? "collapse-basic-search" : StringPool.BLANK %>' markupView="lexicon">
		<c:if test="<%= dlPortletInstanceSettingsHelper.isShowTabs() %>">
			<aui:nav cssClass="navbar-nav">
				<aui:nav-item label="documents-and-media" selected="<%= true %>" />
			</aui:nav>
		</c:if>

		<c:if test="<%= dlPortletInstanceSettingsHelper.isShowSearch() %>">
			<aui:nav-bar-search>
				<liferay-portlet:renderURL varImpl="searchURL">
					<portlet:param name="mvcRenderCommandName" value="/document_library/search" />
					<portlet:param name="repositoryId" value="<%= String.valueOf(repositoryId) %>" />
					<portlet:param name="searchRepositoryId" value="<%= String.valueOf(repositoryId) %>" />
					<portlet:param name="folderId" value="<%= String.valueOf(folderId) %>" />
					<portlet:param name="searchFolderId" value="<%= String.valueOf(folderId) %>" />
					<portlet:param name="showRepositoryTabs" value="<%= (folderId == 0) ? Boolean.TRUE.toString() : Boolean.FALSE.toString() %>" />
					<portlet:param name="showSearchInfo" value="<%= Boolean.TRUE.toString() %>" />
				</liferay-portlet:renderURL>

				<aui:form action="<%= searchURL %>" name="searchFm">
					<liferay-portlet:renderURLParams varImpl="searchURL" />
					<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

					<liferay-ui:input-search
						markupView="lexicon"
					/>
				</aui:form>
			</aui:nav-bar-search>
		</c:if>
	</aui:nav-bar>
</c:if>