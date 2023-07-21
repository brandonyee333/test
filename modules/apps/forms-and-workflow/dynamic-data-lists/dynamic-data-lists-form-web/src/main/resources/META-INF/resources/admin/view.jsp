<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<%
String displayStyle = ddlFormAdminDisplayContext.getDisplayStyle();

PortletURL portletURL = ddlFormAdminDisplayContext.getPortletURL();

portletURL.setParameter("displayStyle", displayStyle);
%>

<liferay-util:include page="/admin/search_bar.jsp" servletContext="<%= application %>" />

<liferay-util:include page="/admin/toolbar.jsp" servletContext="<%= application %>" />

<div class="container-fluid-1280" id="<portlet:namespace />formContainer">
	<aui:form action="<%= portletURL %>" method="post" name="searchContainerForm">
		<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
		<aui:input name="deleteRecordSetIds" type="hidden" />

		<liferay-ui:search-container
			id="ddlRecordSet"
			rowChecker="<%= new EmptyOnClickRowChecker(renderResponse) %>"
			searchContainer="<%= ddlFormAdminDisplayContext.getRecordSetSearch() %>"
		>
			<liferay-ui:search-container-row
				className="com.liferay.dynamic.data.lists.model.DDLRecordSet"
				cssClass="entry-display-style"
				keyProperty="recordSetId"
				modelVar="recordSet"
			>
				<portlet:renderURL var="rowURL">
					<portlet:param name="mvcPath" value="/admin/edit_record_set.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="recordSetId" value="<%= String.valueOf(recordSet.getRecordSetId()) %>" />
					<portlet:param name="displayStyle" value="<%= displayStyle %>" />
				</portlet:renderURL>

				<%
				if (!ddlFormAdminDisplayContext.isShowEditRecordSetIcon(recordSet)) {
					rowURL = null;
				}
				%>

				<c:choose>
					<c:when test='<%= displayStyle.equals("descriptive") %>'>
						<liferay-ui:search-container-column-icon
							cssClass="asset-icon"
							icon="forms"
						/>

						<liferay-ui:search-container-column-jsp
							colspan="<%= 2 %>"
							href="<%= rowURL %>"
							path="/admin/view_record_set_descriptive.jsp"
						/>

						<liferay-ui:search-container-column-jsp
							path="/admin/record_set_action.jsp"
						/>
					</c:when>
					<c:otherwise>
						<liferay-ui:search-container-column-text
							cssClass="table-cell-content"
							href="<%= rowURL %>"
							name="name"
							value="<%= HtmlUtil.escape(recordSet.getName(locale)) %>"
						/>

						<liferay-ui:search-container-column-text
							cssClass="table-cell-content"
							name="description"
							value="<%= HtmlUtil.escape(recordSet.getDescription(locale)) %>"
						/>

						<liferay-ui:search-container-column-date
							name="modified-date"
							value="<%= recordSet.getModifiedDate() %>"
						/>

						<liferay-ui:search-container-column-jsp
							path="/admin/record_set_action.jsp"
						/>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				displayStyle="<%= displayStyle %>"
				markupView="lexicon"
			/>
		</liferay-ui:search-container>
	</aui:form>
</div>

<c:if test="<%= ddlFormAdminDisplayContext.isShowAddRecordSetButton() %>">
	<portlet:renderURL var="addRecordSetURL">
		<portlet:param name="mvcPath" value="/admin/edit_record_set.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
	</portlet:renderURL>

	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item
			title='<%= LanguageUtil.get(request, "new-form") %>'
			url="<%= addRecordSetURL.toString() %>"
		/>
	</liferay-frontend:add-menu>
</c:if>

<%@ include file="/admin/export_record_set.jspf" %>

<aui:script use="liferay-ddl-portlet"></aui:script>