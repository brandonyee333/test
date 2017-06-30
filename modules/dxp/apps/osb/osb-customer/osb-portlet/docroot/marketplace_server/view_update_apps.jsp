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

<%@ include file="/marketplace_server/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1");

int compatibility = ParamUtil.getInteger(request, "compatibility");

long[] appEntryIds = ParamUtil.getLongValues(request, "appEntryIds");
String[] appVersions = ParamUtil.getParameterValues(request, "appVersions");

List<AppEntry> appEntries = new ArrayList<AppEntry>(appEntryIds.length);

for (int i = 0; i < appEntryIds.length; i++) {
	AppEntry appEntry = AppEntryLocalServiceUtil.fetchAppEntry(appEntryIds[i]);

	if ((appEntry == null) || appEntry.isPortalRequired()) {
		continue;
	}

	AppPackage appPackage = AppPackageLocalServiceUtil.fetchCompatibleAppPackage(appEntry.getAppEntryId(), compatibility, WorkflowConstants.STATUS_APPROVED);

	if (appPackage == null) {
		continue;
	}

	String version = appEntry.getVersion();

	if (!version.equals(appVersions[i])) {
		appEntries.add(appEntry);
	}
}

String ownerClassName = ParamUtil.get(request, "ownerClassName", StringPool.BLANK);
long ownerClassPK = ParamUtil.getLong(request, "ownerClassPK");

boolean supportsHotDeploy = ParamUtil.getBoolean(request, "supportsHotDeploy");

request.setAttribute("view_purchased.jsp-tabs1", tabs1);

request.setAttribute("view_purchased.jsp-compatibility", compatibility);
request.setAttribute("view_purchased.jsp-ownerClassName", ownerClassName);
request.setAttribute("view_purchased.jsp-ownerClassPK", ownerClassPK);
request.setAttribute("view_purchased.jsp-supportsHotDeploy", supportsHotDeploy);
%>

<div class="view-update-apps">
	<liferay-ui:header
		title="update"
	/>

	<liferay-ui:search-container
		curParam="curUpdate"
		delta="<%= appEntries.size() %>"
	>
		<liferay-ui:search-container-results
			results="<%= appEntries %>"
			total="<%= appEntries.size() %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.AppEntry"
			escapedModel="<%= true %>"
			keyProperty="appEntryId"
			modelVar="appEntry"
		>
			<liferay-ui:search-container-column-text
				name="product-information"
			>
				<liferay-util:include page="/marketplace_server/app_entry_display.jsp" servletContext="<%= application %>">
					<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
				</liferay-util:include>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/marketplace_server/app_entry_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator paginate="<%= false %>" />
	</liferay-ui:search-container>
</div>