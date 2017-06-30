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

<%@ include file="/marketplace_apps/init.jsp" %>

<%
Group group = themeDisplay.getScopeGroup();

String ownerClassName = ParamUtil.getString(request, "ownerClassName");
long ownerClassPK = ParamUtil.getLong(request, "ownerClassPK");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/marketplace_apps/view_app_entries.jsp");
portletURL.setParameter("ownerClassName", ownerClassName);
portletURL.setParameter("ownerClassPK", String.valueOf(ownerClassPK));
%>

<div class="marketplace-apps view-app-entries">

	<%
	long marketplacePlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);
	%>

	<liferay-portlet:renderURL plid="<%= marketplacePlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE %>" var="marketplaceURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
		<portlet:param name="mvcPath" value="/marketplace/view.jsp" />
	</liferay-portlet:renderURL>

	<liferay-ui:header
		backLabel="marketplace"
		backURL="<%= marketplaceURL %>"
		title="purchased-apps"
	/>

	<p>
		<liferay-ui:message key="we-recommend-that-you-log-into-your-portal-instance" />
	</p>

	<div class="drop-down projects">

		<%
		List<CorpProject> corpProjects = CorpProjectLocalServiceUtil.getUserCorpProjects(themeDisplay.getUserId());
		%>

		<c:if test="<%= !corpProjects.isEmpty() %>">
			<div class="label">
				<liferay-ui:message key="select-a-project" />
			</div>
		</c:if>

		<h2 class="drop-down-title project-name">
			<span><liferay-ui:message arguments="<%= HtmlUtil.escape(AssetReceiptConstants.getOwnerName(ownerClassName, ownerClassPK, StringPool.BLANK)) %>" key="x's-apps" /></span>
		</h2>

		<ul class="drop-down-list">
			<c:if test="<%= !ownerClassName.equals(User.class.getName()) %>">
				<liferay-portlet:renderURL var="viewUserAppEntriesURL">
					<portlet:param name="mvcPath" value="/marketplace_apps/view_app_entries.jsp" />
					<portlet:param name="ownerClassName" value="<%= User.class.getName() %>" />
					<portlet:param name="ownerClassPK" value="<%= String.valueOf(themeDisplay.getUserId()) %>" />
				</liferay-portlet:renderURL>

				<li>
					<a href="<%= viewUserAppEntriesURL %>"><%= HtmlUtil.escape(user.getFullName()) %></a>
				</li>
			</c:if>

			<%
			for (CorpProject corpProject : corpProjects) {
				if (ownerClassName.equals(CorpProject.class.getName()) && (corpProject.getCorpProjectId() == ownerClassPK)) {
					continue;
				}
			%>

				<liferay-portlet:renderURL var="viewProjectAppEntriesURL">
					<portlet:param name="mvcPath" value="/marketplace_apps/view_app_entries.jsp" />
					<portlet:param name="ownerClassName" value="<%= CorpProject.class.getName() %>" />
					<portlet:param name="ownerClassPK" value="<%= String.valueOf(corpProject.getCorpProjectId()) %>" />
				</liferay-portlet:renderURL>

				<li>
					<a href="<%= viewProjectAppEntriesURL %>"><%= HtmlUtil.escape(corpProject.getName()) %></a>
				</li>

			<%
			}
			%>

		</ul>
	</div>

	<div class="taglib-search-iterator-page-iterator-bottom">
		<liferay-ui:search-container
			iteratorURL="<%= portletURL %>"
		>

			<%
			long productClassNameId = PortalUtil.getClassNameId(AppEntry.class);

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("ownerClassNameId", PortalUtil.getClassNameId(ownerClassName));
			params.put("ownerClassPK", ownerClassPK);

			List<AssetReceipt> assetReceipts = AssetReceiptLocalServiceUtil.getAssetReceipts(productClassNameId, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
			int assetReceiptsCount = AssetReceiptLocalServiceUtil.getAssetReceiptsCount(productClassNameId, params);
			%>

			<c:if test="<%= assetReceiptsCount == 0 %>">
				<div class="portlet-msg-info">
					<liferay-ui:message key="there-are-no-apps" />
				</div>
			</c:if>

			<liferay-ui:search-container-results
				results="<%= assetReceipts %>"
				total="<%= assetReceiptsCount %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.AssetReceipt"
				escapedModel="<%= true %>"
				keyProperty="assetReceiptId"
				modelVar="assetReceipt"
			>

				<%
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(assetReceipt.getAssetEntryId());

				AppVersion appVersion = AppVersionLocalServiceUtil.fetchAppVersion(assetEntry.getClassPK(), AppVersionConstants.STATUSES_APPROVED, null);
				%>

				<liferay-portlet:renderURL var="viewAppEntryURL">
					<portlet:param name="mvcPath" value="/marketplace_apps/view_app_entry.jsp" />
					<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
				</liferay-portlet:renderURL>

				<div class="asset container">
					<liferay-util:include page="/marketplace/asset_entry_display.jsp" servletContext="<%= application %>">
						<liferay-util:param name="assetEntryURL" value="<%= viewAppEntryURL %>" />
						<liferay-util:param name="className" value="<%= PortalUtil.getClassName(assetEntry.getClassNameId()) %>" />
						<liferay-util:param name="classPK" value="<%= String.valueOf(assetEntry.getClassPK()) %>" />
						<liferay-util:param name="displayStyle" value="5" />
						<liferay-util:param name="title" value="<%= assetEntry.getTitle() %>" />
						<liferay-util:param name="titleLength" value="34" />
					</liferay-util:include>
				</div>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="false" />
		</liferay-ui:search-container>
	</div>

	<div class="cleared"><!-- --></div>
</div>

<aui:script use="aui-base">
	var dropDown = A.one('.marketplace-apps .drop-down');

	var dropDownListItems = dropDown.all('.drop-down-list li');

	if (dropDownListItems.size() > 0) {
		dropDown.addClass('drop-down-capable');

		dropDown.one('.drop-down-title span').on(
			'mouseenter',
			function(event) {
				dropDown.addClass('drop-down-active');
			}
		);

		dropDown.on(
			'mouseleave',
			function(event) {
				dropDown.removeClass('drop-down-active');
			}
		);
	}
</aui:script>