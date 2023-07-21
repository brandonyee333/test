<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ViewUADApplicationsSummaryDisplay viewUADApplicationsSummaryDisplay = (ViewUADApplicationsSummaryDisplay)request.getAttribute(UADWebKeys.VIEW_UAD_APPLICATIONS_SUMMARY_DISPLAY);

SearchContainer<UADApplicationSummaryDisplay> uadApplicationsSummaryDisplaySearchContainer = viewUADApplicationsSummaryDisplay.getSearchContainer();

portletDisplay.setShowBackIcon(true);

PortletURL backURL = renderResponse.createRenderURL();

backURL.setParameter("mvcRenderCommandName", "/view_uad_summary");
backURL.setParameter("p_u_i_d", String.valueOf(selectedUser.getUserId()));

portletDisplay.setURLBack(backURL.toString());

renderResponse.setTitle(StringBundler.concat(selectedUser.getFullName(), " - ", LanguageUtil.get(request, "personal-data-erasure")));
%>

<div class="container-fluid-1280 uad-application-summary-wrapper">
	<div class="application-summary-section panel panel-default">
		<div class="panel-heading">
			<div class="panel-title">
				<liferay-ui:message key="status-summary" />
			</div>
		</div>

		<div class="panel-body">
			<p class="remaining-items-wrapper">
				<strong><liferay-ui:message key="remaining-items" />: </strong><%= viewUADApplicationsSummaryDisplay.getTotalCount() %>
			</p>

			<div class="complete-step-button-wrapper">
				<aui:button cssClass="btn-lg" disabled="<%= viewUADApplicationsSummaryDisplay.getTotalCount() > 0 %>" href="<%= backURL.toString() %>" primary="true" value="complete-step" />
			</div>
		</div>
	</div>

	<div class="application-summary-section panel panel-default">
		<div class="panel-heading">
			<div class="panel-title">
				<liferay-ui:message key="applications" />
			</div>
		</div>

		<div class="panel-body">
			<liferay-frontend:management-bar>
				<liferay-frontend:management-bar-filters>
					<liferay-frontend:management-bar-navigation
						navigationKeys='<%= new String[] {"all", "pending", "done"} %>'
						portletURL="<%= PortletURLUtil.clone(currentURLObj, renderResponse) %>"
					/>

					<liferay-frontend:management-bar-sort
						orderByCol="<%= uadApplicationsSummaryDisplaySearchContainer.getOrderByCol() %>"
						orderByType="<%= uadApplicationsSummaryDisplaySearchContainer.getOrderByType() %>"
						orderColumns='<%= new String[] {"name", "items", "status"} %>'
						portletURL="<%= PortletURLUtil.clone(currentURLObj, renderResponse) %>"
					/>
				</liferay-frontend:management-bar-filters>
			</liferay-frontend:management-bar>

			<liferay-ui:search-container
				searchContainer="<%= uadApplicationsSummaryDisplaySearchContainer %>"
			>
				<liferay-ui:search-container-row
					className="com.liferay.user.associated.data.web.internal.display.UADApplicationSummaryDisplay"
					escapedModel="<%= true %>"
					keyProperty="key"
					modelVar="uadApplicationSummaryDisplay"
				>
					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						href="<%= uadApplicationSummaryDisplay.getViewURL() %>"
						name="name"
						value="<%= UADLanguageUtil.getApplicationName(uadApplicationSummaryDisplay.getApplicationKey(), locale) %>"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						href="<%= uadApplicationSummaryDisplay.getViewURL() %>"
						name="items"
						property="count"
					/>

					<liferay-ui:search-container-column-text
						cssClass="table-cell-content"
						name="status"
					>
						<span class="text-<%= uadApplicationSummaryDisplay.hasItems() ? "warning" : "success" %>">
							<liferay-ui:message key='<%= uadApplicationSummaryDisplay.hasItems() ? "pending" : "done" %>' />
						</span>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-jsp
						cssClass="entry-action-column"
						path="/applications_summary_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator
					markupView="lexicon"
				/>
			</liferay-ui:search-container>
		</div>
	</div>
</div>