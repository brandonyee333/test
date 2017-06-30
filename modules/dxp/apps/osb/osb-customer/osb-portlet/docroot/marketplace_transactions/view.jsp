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
PortletURL portletURL = renderResponse.createRenderURL();
%>

<div class="marketplace-transactions view">
	<div class="fr ha-r">
		<a class="btn" onClick="<portlet:namespace />viewExportECDocumentEntryPopup();"><liferay-ui:message key="export" /></a>
	</div>

	<liferay-ui:header
		title="transactions"
	/>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-results"
		iteratorURL="<%= portletURL %>"
	>
		<liferay-ui:search-container-results>

			<%
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("paid", Boolean.TRUE);
			params.put("type", ECDocumentEntryConstants.TYPE_INVOICE);
			params.put("storeName", ECommerceConstants.STORE_NAME_MARKETPLACE);

			DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntryByGroupId(themeDisplay.getScopeGroupId());

			params.put("vendorClassNameId", PortalUtil.getClassNameId(DeveloperEntry.class.getName()));
			params.put("vendorClassPK", developerEntry.getDeveloperEntryId());

			results = ECDocumentEntryLocalServiceUtil.getECDocumentEntries(OSBConstants.GROUP_GUEST_ID, params, searchContainer.getStart(), searchContainer.getEnd(), null);
			total = ECDocumentEntryLocalServiceUtil.getECDocumentEntriesCount(OSBConstants.GROUP_GUEST_ID, params);

			pageContext.setAttribute("results", results);
			pageContext.setAttribute("total", total);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.ecommerce.model.ECDocumentEntry"
			escapedModel="<%= true %>"
			keyProperty="ecDocumentEntryId"
			modelVar="ecDocumentEntry"
		>

			<%
			ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings(ecDocumentEntry);
			%>

			<liferay-portlet:renderURL var="rowURL">
				<portlet:param name="mvcPath" value="/marketplace_transactions/view_ec_document_entry.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntry.getEcDocumentEntryId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="purchased"
				value="<%= shortDateFormatDate.format(ecDocumentEntry.getModifiedDate()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="transaction-id"
				value="<%= String.valueOf(ecDocumentEntry.getEcDocumentEntryId()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="app"
			>

				<%
				AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(ecDocumentEntryExtraSettings.getAppEntryId());
				%>

				<div class="icon">
					<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveIcon" var="iconURL">
						<portlet:param name="assetAttachmentId" value="<%= String.valueOf(appEntry.getIconImageId()) %>" />
					</liferay-portlet:resourceURL>

					<img src="<%= iconURL %>" />
				</div>

				<%= HtmlUtil.escape(appEntry.getTitle()) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="purchased-by"
				value="<%= PortalUtil.getUserName(ecDocumentEntry) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="project"
			>

				<%
				String projectName = StringPool.BLANK;

				String ownerClassName = ecDocumentEntryExtraSettings.getOwnerClassName();

				if (ownerClassName.equals(CorpProject.class.getName())) {
					CorpProject corpProject = CorpProjectLocalServiceUtil.fetchCorpProject(ecDocumentEntryExtraSettings.getOwnerClassPK());

					if (corpProject != null) {
						projectName = corpProject.getName();
					}
				}
				%>

				<%= HtmlUtil.escape(projectName) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="order-total"
			>
				<%= ecDocumentEntry.getFormattedTotal() %>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />viewExportECDocumentEntryPopup',
		function() {
			var A = AUI();

			window.popup = new A.Dialog(
				{
					centered: true,
					destroyOnClose: true,
					modal: true,
					resizable: false,
					title: '<liferay-ui:message key="export-transactions" unicode="<%= true %>" />',
					width: 435
				}
			).plug(
				A.Plugin.IO,
				{
					uri: '<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/marketplace_transactions/export_ec_document_entry.jsp" /></liferay-portlet:renderURL>'
				}
			).render();
		},
		['aui-dialog', 'aui-io']
	);
</aui:script>