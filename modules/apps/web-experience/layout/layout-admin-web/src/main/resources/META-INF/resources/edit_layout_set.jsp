<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Group selGroup = (Group)request.getAttribute(WebKeys.GROUP);

long liveGroupId = layoutsAdminDisplayContext.getLiveGroupId();
boolean privateLayout = layoutsAdminDisplayContext.isPrivateLayout();
LayoutSet selLayoutSet = layoutsAdminDisplayContext.getSelLayoutSet();

String backURL = ParamUtil.getString(request, "backURL");

if (Validator.isNull(backURL) && (LayoutLocalServiceUtil.getLayoutsCount(selGroup, privateLayout) > 0)) {
	backURL = PortalUtil.getGroupFriendlyURL(layoutsAdminDisplayContext.getSelLayoutSet(), themeDisplay);
}

PortletURL redirectURL = layoutsAdminDisplayContext.getRedirectURL();

if (selGroup.isLayoutSetPrototype()) {
	privateLayout = true;
}

renderResponse.setTitle(selGroup.getLayoutRootNodeName(privateLayout, locale));
%>

<portlet:actionURL name="editLayoutSet" var="editLayoutSetURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
</portlet:actionURL>

<aui:form action="<%= editLayoutSetURL %>" cssClass="edit-layoutset-form" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirectURL.toString() %>" />
	<aui:input name="groupId" type="hidden" value="<%= selGroup.getGroupId() %>" />
	<aui:input name="liveGroupId" type="hidden" value="<%= liveGroupId %>" />
	<aui:input name="stagingGroupId" type="hidden" value="<%= layoutsAdminDisplayContext.getStagingGroupId() %>" />
	<aui:input name="selPlid" type="hidden" value="<%= layoutsAdminDisplayContext.getSelPlid() %>" />
	<aui:input name="privateLayout" type="hidden" value="<%= privateLayout %>" />
	<aui:input name="layoutSetId" type="hidden" value="<%= selLayoutSet.getLayoutSetId() %>" />
	<aui:input name="<%= PortletDataHandlerKeys.SELECTED_LAYOUTS %>" type="hidden" />

	<liferay-ui:form-navigator
		backURL="<%= backURL %>"
		formModelBean="<%= selLayoutSet %>"
		id="<%= FormNavigatorConstants.FORM_NAVIGATOR_ID_LAYOUT_SET %>"
		markupView="lexicon"
		showButtons="<%= GroupPermissionUtil.contains(permissionChecker, selGroup, ActionKeys.MANAGE_LAYOUTS) && SitesUtil.isLayoutSetPrototypeUpdateable(selLayoutSet) %>"
	/>
</aui:form>