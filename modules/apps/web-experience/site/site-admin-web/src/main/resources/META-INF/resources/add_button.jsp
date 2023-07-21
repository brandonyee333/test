<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:if test="<%= PortalPermissionUtil.contains(permissionChecker, ActionKeys.ADD_COMMUNITY) %>">

	<%
	Group group = siteAdminDisplayContext.getGroup();
	%>

	<liferay-portlet:renderURL varImpl="addSiteURL">
		<portlet:param name="mvcPath" value="/edit_site.jsp" />

		<c:if test="<%= (group != null) && siteAdminDisplayContext.hasAddChildSitePermission(group) %>">
			<portlet:param name="parentGroupSearchContainerPrimaryKeys" value="<%= String.valueOf(group.getGroupId()) %>" />
		</c:if>
	</liferay-portlet:renderURL>

	<%
	List<LayoutSetPrototype> layoutSetPrototypes = LayoutSetPrototypeServiceUtil.search(company.getCompanyId(), Boolean.TRUE, null);
	%>

	<liferay-frontend:add-menu>
		<c:choose>
			<c:when test="<%= layoutSetPrototypes.isEmpty() %>">
				<liferay-frontend:add-menu-item
					title='<%= LanguageUtil.get(request, "add") %>'
					url="<%= addSiteURL.toString() %>"
				/>
			</c:when>
			<c:otherwise>
				<liferay-frontend:add-menu-item
					title='<%= LanguageUtil.get(request, "blank-site") %>'
					url="<%= addSiteURL.toString() %>"
				/>

				<%
				for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
					addSiteURL.setParameter("layoutSetPrototypeId", String.valueOf(layoutSetPrototype.getLayoutSetPrototypeId()));
				%>

					<liferay-frontend:add-menu-item
						title="<%= layoutSetPrototype.getName(locale) %>"
						url="<%= addSiteURL.toString() %>"
					/>

				<%
				}
				%>

			</c:otherwise>
		</c:choose>
	</liferay-frontend:add-menu>
</c:if>