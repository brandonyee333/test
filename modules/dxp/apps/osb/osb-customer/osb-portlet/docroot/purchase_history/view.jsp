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

<%@ include file="/purchase_history/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();
%>

<div class="purchase-history view">
	<liferay-ui:header
		title="purchase-history"
	/>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-results"
		iteratorURL="<%= portletURL %>"
	>

		<%
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		params.put("storeName", ECommerceConstants.STORE_NAME_MARKETPLACE);
		params.put("userId", themeDisplay.getUserId());
		%>

		<liferay-ui:search-container-results
			results="<%= ECDocumentEntryLocalServiceUtil.getECDocumentEntries(OSBConstants.GROUP_GUEST_ID, params, searchContainer.getStart(), searchContainer.getEnd(), new ECDocumentEntryCreateDateComparator(false)) %>"
			total="<%= ECDocumentEntryLocalServiceUtil.getECDocumentEntriesCount(OSBConstants.GROUP_GUEST_ID, params) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.ecommerce.model.ECDocumentEntry"
			escapedModel="<%= true %>"
			keyProperty="ecDocumentEntryId"
			modelVar="ecDocumentEntry"
		>
			<liferay-portlet:renderURL var="rowURL">
				<portlet:param name="mvcPath" value="/purchase_history/view_ec_document_entry.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntry.getEcDocumentEntryId()) %>" />
			</liferay-portlet:renderURL>

			<%
			ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings(ecDocumentEntry);

			String productType = ecDocumentEntryExtraSettings.getProductType();
			%>

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
				<c:choose>
					<c:when test='<%= productType.equals("developer-subscription") %>'>
						<liferay-ui:message key="developer-subscription" />
					</c:when>
					<c:otherwise>

						<%
						AppEntry appEntry = AppEntryLocalServiceUtil.fetchAppEntry(ecDocumentEntryExtraSettings.getAppEntryId());
						%>

						<c:if test="<%= appEntry != null %>">
							<div class="icon">
								<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveIcon" var="iconURL">
									<portlet:param name="assetAttachmentId" value="<%= String.valueOf(appEntry.getIconImageId()) %>" />
								</liferay-portlet:resourceURL>

								<img src="<%= iconURL %>" />
							</div>

							<%= HtmlUtil.escape(appEntry.getTitle()) %>
						</c:if>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="purchased-from"
			>
				<c:choose>
					<c:when test='<%= productType.equals("developer-subscription") %>'>
						<liferay-ui:message key="liferay" />
					</c:when>
					<c:otherwise>

						<%
						AppEntry appEntry = AppEntryLocalServiceUtil.fetchAppEntry(ecDocumentEntryExtraSettings.getAppEntryId());
						%>

						<c:if test="<%= appEntry != null %>">
							<%= HtmlUtil.escape(appEntry.getDeveloperName()) %>
						</c:if>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="project"
			>

				<%
				String ownerClassName = ecDocumentEntryExtraSettings.getOwnerClassName();
				%>

				<c:if test="<%= ownerClassName.equals(CorpProject.class.getName()) %>">

					<%
					CorpProject corpProject = CorpProjectLocalServiceUtil.getCorpProject(ecDocumentEntryExtraSettings.getOwnerClassPK());
					%>

					<%= HtmlUtil.escape(corpProject.getName()) %>
				</c:if>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="order-total"
				value="<%= ecDocumentEntry.getFormattedTotal() %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>