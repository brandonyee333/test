<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

LayoutPrototype layoutPrototype = (LayoutPrototype)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= LayoutPrototypePermissionUtil.contains(permissionChecker, layoutPrototype.getLayoutPrototypeId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="editURL">
			<portlet:param name="mvcPath" value="/edit_layout_prototype.jsp" />
			<portlet:param name="layoutPrototypeId" value="<%= String.valueOf(layoutPrototype.getLayoutPrototypeId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="edit"
			url="<%= editURL %>"
		/>
	</c:if>

	<c:if test="<%= LayoutPrototypePermissionUtil.contains(permissionChecker, layoutPrototype.getLayoutPrototypeId(), ActionKeys.PERMISSIONS) %>">
		<liferay-security:permissionsURL
			modelResource="<%= LayoutPrototype.class.getName() %>"
			modelResourceDescription="<%= layoutPrototype.getName(locale) %>"
			resourcePrimKey="<%= String.valueOf(layoutPrototype.getLayoutPrototypeId()) %>"
			var="permissionsURL"
			windowState="<%= LiferayWindowState.POP_UP.toString() %>"
		/>

		<liferay-ui:icon
			message="permissions"
			method="get"
			url="<%= permissionsURL %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= GroupPermissionUtil.contains(permissionChecker, layoutPrototype.getGroup(), ActionKeys.EXPORT_IMPORT_LAYOUTS) %>">

		<%
		PortletURL exportURL = PortalUtil.getControlPanelPortletURL(request, ExportImportPortletKeys.EXPORT, PortletRequest.RENDER_PHASE);

		exportURL.setParameter("mvcRenderCommandName", "exportLayouts");
		exportURL.setParameter(Constants.CMD, Constants.EXPORT);
		exportURL.setParameter("groupId", String.valueOf(layoutPrototype.getGroupId()));
		exportURL.setParameter("privateLayout", Boolean.TRUE.toString());
		exportURL.setParameter("rootNodeName", layoutPrototype.getName(locale));
		exportURL.setParameter("showHeader", Boolean.FALSE.toString());
		exportURL.setWindowState(LiferayWindowState.POP_UP);
		%>

		<liferay-ui:icon
			cssClass="export-layout-prototype layout-prototype-action"
			message="export"
			method="get"
			url="<%= exportURL.toString() %>"
			useDialog="<%= true %>"
		/>

		<%
		PortletURL importURL = PortalUtil.getControlPanelPortletURL(request, ExportImportPortletKeys.IMPORT, PortletRequest.RENDER_PHASE);

		importURL.setParameter("mvcRenderCommandName", "importLayouts");
		importURL.setParameter(Constants.CMD, Constants.IMPORT);
		importURL.setParameter("groupId", String.valueOf(layoutPrototype.getGroupId()));
		importURL.setParameter("privateLayout", Boolean.TRUE.toString());
		importURL.setParameter("rootNodeName", layoutPrototype.getName(locale));
		importURL.setParameter("showHeader", Boolean.FALSE.toString());
		importURL.setWindowState(LiferayWindowState.POP_UP);
		%>

		<liferay-ui:icon
			cssClass="import-layout-prototype layout-prototype-action"
			message="import"
			method="get"
			url="<%= importURL.toString() %>"
			useDialog="<%= true %>"
		/>
	</c:if>

	<c:if test="<%= LayoutPrototypePermissionUtil.contains(permissionChecker, layoutPrototype.getLayoutPrototypeId(), ActionKeys.DELETE) %>">
		<portlet:actionURL name="deleteLayoutPrototypes" var="deleteLayoutPrototypesURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="layoutPrototypeId" value="<%= String.valueOf(layoutPrototype.getLayoutPrototypeId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteLayoutPrototypesURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>