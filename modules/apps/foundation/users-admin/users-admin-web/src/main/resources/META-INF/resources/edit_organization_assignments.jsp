<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1");
String tabs2 = ParamUtil.getString(request, "tabs2", "current");

String redirect = ParamUtil.getString(request, "redirect");

long organizationId = ParamUtil.getLong(request, "organizationId");

Organization organization = OrganizationServiceUtil.fetchOrganization(organizationId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/users_admin/edit_organization_assignments");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("organizationId", String.valueOf(organization.getOrganizationId()));

UsersAdminUtil.addPortletBreadcrumbEntries(organization, request, renderResponse);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "assign-members"), currentURL);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(organization.getName());
%>

<liferay-ui:membership-policy-error />

<aui:form action="<%= portletURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
	<aui:input name="tabs2" type="hidden" value="<%= tabs2 %>" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="assignmentsRedirect" type="hidden" />
	<aui:input name="organizationId" type="hidden" value="<%= organization.getOrganizationId() %>" />

	<liferay-ui:tabs
		names="current,available"
		param="tabs2"
		url="<%= portletURL.toString() %>"
	/>

	<aui:input name="addUserIds" type="hidden" />
	<aui:input name="removeUserIds" type="hidden" />

	<liferay-ui:search-container
		rowChecker="<%= new UserOrganizationChecker(renderResponse, organization) %>"
		searchContainer="<%= new UserSearch(renderRequest, portletURL) %>"
		var="userSearchContainer"
	>
		<liferay-ui:user-search-form />

		<%
		LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

		if (tabs2.equals("current")) {
			userParams.put("usersOrgs", Long.valueOf(organization.getOrganizationId()));
		}
		else if (PropsValues.ORGANIZATIONS_ASSIGNMENT_STRICT && !permissionChecker.isCompanyAdmin() && !permissionChecker.hasPermission(scopeGroupId, User.class.getName(), User.class.getName(), ActionKeys.VIEW)) {
			userParams.put("usersOrgsTree", user.getOrganizations(true));
		}
		%>

		<liferay-ui:user-search-container-results
			userParams="<%= userParams %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.User"
			escapedModel="<%= true %>"
			keyProperty="userId"
			modelVar="user2"
			rowIdProperty="screenName"
		>
			<liferay-ui:search-container-column-text
				name="name"
				property="fullName"
				truncate="<%= true %>"
			/>

			<liferay-ui:search-container-column-text
				name="screen-name"
				property="screenName"
				truncate="<%= true %>"
			/>
		</liferay-ui:search-container-row>

		<div class="separator"><!-- --></div>

		<aui:button onClick='<%= renderResponse.getNamespace() + "updateOrganizationUsers();" %>' value="update-associations" />

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	function <portlet:namespace />updateOrganizationUsers() {
		var form = AUI.$(document.<portlet:namespace />fm);

		form.fm('addUserIds').val(Liferay.Util.listCheckedExcept(form, '<portlet:namespace />allRowIds'));
		form.fm('removeUserIds').val(Liferay.Util.listUncheckedExcept(form, '<portlet:namespace />allRowIds'));

		submitForm(form, '<portlet:actionURL name="/users_admin/edit_organization_assignments" />');
	}
</aui:script>