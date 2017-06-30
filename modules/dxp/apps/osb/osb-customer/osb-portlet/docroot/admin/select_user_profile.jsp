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

<%@ include file="/init.jsp" %>

<%
long userId = ParamUtil.getLong(request, "userId");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/select_user_profile.jsp");
portletURL.setParameter("userId", String.valueOf(userId));
%>

<aui:form method="post" name="fm">
	<liferay-ui:tabs
		names="profiles"
	/>

	<div>
		<input onClick="location.href = '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/edit_user_profile.jsp" /><portlet:param name="userId" value="<%= String.valueOf(userId) %>" /><portlet:param name="redirect" value="<%= currentURL %>" /></portlet:renderURL>';" type="button" value="<liferay-ui:message key="add-profile" />" />
	</div>

	<br />

	<liferay-ui:search-container
		emptyResultsMessage="no-profiles-were-found"
		headerNames="first-name,last-name,email-address,legal-entity-name"
		iteratorURL="<%= portletURL %>"
	>
		<liferay-ui:search-container-results
			results="<%= UserProfileLocalServiceUtil.getUserProfiles(userId) %>"
			total="<%= UserProfileLocalServiceUtil.getUserProfilesCount(userId) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.UserProfile"
			escapedModel="<%= true %>"
			keyProperty="userProfileId"
			modelVar="userProfile"
		>

			<%
			StringBundler sb = new StringBundler(9);

			sb.append("javascript:opener.");
			sb.append(renderResponse.getNamespace());
			sb.append("selectUserProfile('");
			sb.append(userId);
			sb.append("', '");
			sb.append(userProfile.getUserProfileId());
			sb.append("', '");
			sb.append(AdminUtil.getUserProfileDisplayHTML(userProfile));
			sb.append("'); window.close();");

			String rowHREF = sb.toString();
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="first-name"
				property="firstName"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="last-name"
				property="lastName"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="email-address"
				property="emailAddress"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="legal-entity-name"
				property="legalEntityName"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>