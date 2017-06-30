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

<%@ include file="/marketplace_admin/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String emailAddress = ParamUtil.getString(request, "emailAddress");

User assetReceiptsUser = UserLocalServiceUtil.fetchUserByEmailAddress(themeDisplay.getCompanyId(), emailAddress);
%>

<div class="user-asset-entries">
	<c:choose>
		<c:when test="<%= assetReceiptsUser == null %>">
			<liferay-ui:header
				backURL="<%= redirect %>"
				title="customer-lookup"
			/>

			<liferay-portlet:renderURL varImpl="lookUpURL">
				<portlet:param name="mvcPath" value="/marketplace_admin/user_asset_receipts.jsp" />
				<portlet:param name="tabs1" value="orders" />
			</liferay-portlet:renderURL>

			<aui:form action="<%= lookUpURL.toString() %>" method="get" name="fm1">
				<liferay-portlet:renderURLParams varImpl="lookUpURL" />
				<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

				<aui:input name="emailAddress" size="50" type="text" />

				<aui:button type="submit" value="lookup" />
			</aui:form>
		</c:when>
		<c:otherwise>
			<liferay-ui:header
				backURL="<%= redirect %>"
				title='<%= LanguageUtil.format(pageContext, "customer-x", assetReceiptsUser.getFullName()) %>'
			/>

			<div class="actions-container">
				<portlet:renderURL var="editAssetReceiptURL">
					<portlet:param name="mvcPath" value="/marketplace_admin/edit_asset_receipt.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="purchasedByUserId" value="<%= String.valueOf(assetReceiptsUser.getUserId()) %>" />
				</portlet:renderURL>

				<aui:button onClick="<%= editAssetReceiptURL %>" value="add-order" />
			</div>

			<div class="field-header">
				<h3>
					<liferay-ui:message key="projects" />
				</h3>
			</div>

			<div class="projects">

				<%
				List<CorpProject> corpProjects = CorpProjectLocalServiceUtil.getUserCorpProjects(assetReceiptsUser.getUserId());
				%>

				<liferay-ui:search-container
					delta="<%= corpProjects.size() %>"
					emptyResultsMessage="no-projects-were-found"
					headerNames="name,apps,users"
				>
					<liferay-ui:search-container-results
						results="<%= corpProjects %>"
						total="<%= corpProjects.size() %>"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.osb.model.CorpProject"
						escapedModel="<%= true %>"
						keyProperty="corpProjectId"
						modelVar="corpProject"
					>
						<liferay-ui:search-container-column-text
							name="name"
						>
							<a href="#corpProject<%= corpProject.getCorpProjectId() %>">
								<%= corpProject.getName() %>
							</a>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="apps"
						>

							<%
							LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

							params.put("ownerClassNameId", PortalUtil.getClassNameId(CorpProject.class));
							params.put("ownerClassPK", corpProject.getCorpProjectId());
							%>

							<%= AssetReceiptLocalServiceUtil.getAssetReceiptsCount(PortalUtil.getClassNameId(AppEntry.class), params) %>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="users"
						>
							<%= CorpMembersUtil.getCorpProjectUsersCount(corpProject.getCorpProjectId(), StringPool.BLANK, 0) %>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text>
							<liferay-ui:icon-menu>
								<liferay-portlet:renderURL portletName="<%= OSBPortletKeys.OSB_CORP_PROJECT_ADMIN %>" var="viewCorpProjectURL">
									<portlet:param name="mvcPath" value="/corp_project_admin/view_corp_project.jsp" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="corpProjectId" value="<%= String.valueOf(corpProject.getCorpProjectId()) %>" />
								</liferay-portlet:renderURL>

								<liferay-ui:icon image="assign" message="assign-members" url="<%= viewCorpProjectURL %>" />

								<liferay-portlet:renderURL portletName="<%= OSBPortletKeys.OSB_CORP_PROJECT_ADMIN %>" var="editCorpProjectURL">
									<portlet:param name="mvcPath" value="/corp_project_admin/edit_corp_project.jsp" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="corpProjectId" value="<%= String.valueOf(corpProject.getCorpProjectId()) %>" />
								</liferay-portlet:renderURL>

								<liferay-ui:icon image="edit" url="<%= editCorpProjectURL %>" />
							</liferay-ui:icon-menu>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator />
				</liferay-ui:search-container>
			</div>

			<div class="field-header">
				<h3>
					<liferay-ui:message key="apps" />
				</h3>
			</div>

			<div class="purchased-apps">
				<h4>
					<liferay-ui:message key="individual" />
				</h4>

				<%
				LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

				params.put("ownerClassNameId", PortalUtil.getClassNameId(User.class));
				params.put("ownerClassPK", assetReceiptsUser.getUserId());

				List<AssetReceipt> assetReceipts = AssetReceiptLocalServiceUtil.getAssetReceipts(PortalUtil.getClassNameId(AppEntry.class), params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AssetReceiptCreateDateComparator(false));
				%>

				<%@ include file="/marketplace_admin/user_asset_receipt_results.jspf" %>
			</div>

			<%
			for (CorpProject corpProject : corpProjects) {
			%>

				<div class="purchased-apps">
					<a name="corpProject<%= corpProject.getCorpProjectId() %>"></a>

					<h4>
						<liferay-portlet:renderURL portletName="<%= OSBPortletKeys.OSB_CORP_PROJECT_ADMIN %>" var="corpProjectURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
							<portlet:param name="mvcPath" value="/corp_project_admin/view_corp_project.jsp" />
							<portlet:param name="backURL" value="<%= currentURL %>" />
							<portlet:param name="corpProjectId" value="<%= String.valueOf(corpProject.getCorpProjectId()) %>" />
						</liferay-portlet:renderURL>

						<a href="<%= corpProjectURL.toString() %>" target="_blank"><%= HtmlUtil.escape(corpProject.getName()) %></a>
					</h4>

					<%
					params.clear();

					params.put("ownerClassNameId", PortalUtil.getClassNameId(CorpProject.class));
					params.put("ownerClassPK", corpProject.getCorpProjectId());

					assetReceipts = AssetReceiptLocalServiceUtil.getAssetReceipts(PortalUtil.getClassNameId(AppEntry.class), params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AssetReceiptCreateDateComparator(false));
					%>

					<%@ include file="/marketplace_admin/user_asset_receipt_results.jspf" %>
				</div>

			<%
			}
			%>

		</c:otherwise>
	</c:choose>
</div>