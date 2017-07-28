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

<%@ include file="/support/2/init.jsp" %>

<%
AccountEntryDisplayTerms displayTerms = new AccountEntryDisplayTerms(renderRequest);
%>

<div class="account advanced-search">
	<div>
		<span class="search-param tier" onclick="<portlet:namespace />toggleSelected(this);" searchParam="tier">
			<liferay-ui:message key="tier" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>
		<span class="search-param status" onclick="<portlet:namespace />toggleSelected(this);" searchParam="status">
			<liferay-ui:message key="status" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>
		<span class="industry search-param" onclick="<portlet:namespace />toggleSelected(this);" searchParam="industry">
			<liferay-ui:message key="industry" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>
		<span class="search-param support-region" onclick="<portlet:namespace />toggleSelected(this);" searchParam="supportRegion">
			<liferay-ui:message key="support-region" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>
	</div>

	<div class="aui-helper-clearfix search-param-config">
		<div class="search-param-dropdown tiers" id="<portlet:namespace />tier">

			<%
			String[] tierLabels = new String[4];
			int[] tierValues = new int[4];

			for (int i = 0; i < 4; i++) {
				tierLabels[i] = AccountEntryConstants.getTierLabel(i + 1);
				tierValues[i] = i + 1;
			}
			%>

			<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
				<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getTiers()) %>" />
				<liferay-util:param name="labels" value="<%= StringUtil.merge(tierLabels) %>" />
				<liferay-util:param name="name" value="<%= displayTerms.TIERS %>" />
				<liferay-util:param name="values" value="<%= StringUtil.merge(tierValues) %>" />
			</liferay-util:include>
		</div>

		<div class="search-param-dropdown statuses" id="<portlet:namespace />status">

			<%
			int[] statusValues = AccountEntryConstants.STATUSES;

			String[] statusLabels = new String[statusValues.length];

			for (int i = 0; i < statusValues.length; i++) {
				statusLabels[i] = WorkflowConstants.getStatusLabel(statusValues[i]);
			}
			%>

			<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
				<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getStatuses()) %>" />
				<liferay-util:param name="labels" value="<%= StringUtil.merge(statusLabels) %>" />
				<liferay-util:param name="name" value="<%= displayTerms.STATUSES %>" />
				<liferay-util:param name="values" value="<%= StringUtil.merge(statusValues) %>" />
			</liferay-util:include>
		</div>

		<div class="industries search-param-dropdown" id="<portlet:namespace />industry">
			<div class="check-all">
				<input onClick="<portlet:namespace />checkAll('industry', true);" type="button" value="<liferay-ui:message key="check-all" />" />

				<input onClick="<portlet:namespace />checkAll('industry', false);" type="button" value="<liferay-ui:message key="uncheck-all" />" />
			</div>

			<%
			List<ListType> industryTypes = ListTypeServiceUtil.getListTypes(AccountEntryConstants.LIST_TYPE_INDUSTRY);
			%>

			<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
				<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getIndustries()) %>" />
				<liferay-util:param name="itemsPerColumn" value="6" />
				<liferay-util:param name="labels" value='<%= ListUtil.toString(industryTypes, "name") %>' />
				<liferay-util:param name="name" value="<%= displayTerms.INDUSTRIES %>" />
				<liferay-util:param name="values" value='<%= ListUtil.toString(industryTypes, "listTypeId") %>' />
			</liferay-util:include>
		</div>

		<div class="search-param-dropdown support-region" id="<portlet:namespace />supportRegion">
			<div class="check-all">
				<input onClick="<portlet:namespace />checkAll('supportRegion', true);" type="button" value="<liferay-ui:message key="check-all" />" />

				<input onClick="<portlet:namespace />checkAll('supportRegion', false);" type="button" value="<liferay-ui:message key="uncheck-all" />" />
			</div>

			<%
			List<SupportRegion> supportRegions = SupportRegionLocalServiceUtil.getSupportRegions(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
			%>

			<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
				<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getSupportRegionIds()) %>" />
				<liferay-util:param name="labels" value='<%= ListUtil.toString(supportRegions, "name") %>' />
				<liferay-util:param name="name" value="<%= displayTerms.SUPPORT_REGION_IDS %>" />
				<liferay-util:param name="values" value='<%= ListUtil.toString(supportRegions, "supportRegionId") %>' />
			</liferay-util:include>
		</div>
	</div>
</div>

<div class="search-results">
	<div id="<portlet:namespace />advancedSearchResultsContent">
	</div>
</div>