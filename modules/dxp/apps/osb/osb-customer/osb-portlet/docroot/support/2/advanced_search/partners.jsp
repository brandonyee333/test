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
PartnerEntryDisplayTerms displayTerms = new PartnerEntryDisplayTerms(renderRequest);
%>

<div class="advanced-search partner">
	<div>
		<span class="first-line-support search-param" onclick="<portlet:namespace />toggleSelected(this);" searchParam="managingSupport">
			<liferay-ui:message key="first-line-support" />

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
		<span class="search-param support-region" onclick="<portlet:namespace />toggleSelected(this);" searchParam="supportRegion">
			<liferay-ui:message key="support-region" />

			<svg height="8" viewBox="0 0 8 8" width="8">
				<path d="M 0, 0 4, 4 8, 0" />
			</svg>
		</span>
	</div>

	<div class="aui-helper-clearfix search-param-config">
		<div class="first-line-support search-param-dropdown" id="<portlet:namespace />managingSupport">
			<select id="<portlet:namespace /><%= displayTerms.MANAGING_SUPPORT %>" name="<portlet:namespace /><%= displayTerms.MANAGING_SUPPORT %>">
				<option <%= !displayTerms.hasManagingSupport() ? "selected" : StringPool.BLANK %> value=""></option>
				<option <%= (displayTerms.hasManagingSupport() && displayTerms.isManagingSupport()) ? "selected" : StringPool.BLANK %> value="1"><liferay-ui:message key="yes" /></option>
				<option <%= (displayTerms.hasManagingSupport() && !displayTerms.isManagingSupport()) ? "selected" : StringPool.BLANK %> value="0"><liferay-ui:message key="no" /></option>
			</select>
		</div>

		<div class="search-param-dropdown status" id="<portlet:namespace />status">

			<%
			String[] statusLabels = new String[] {WorkflowConstants.getStatusLabel(WorkflowConstants.STATUS_APPROVED), WorkflowConstants.getStatusLabel(WorkflowConstants.STATUS_INACTIVE)};
			int[] statusValues = new int[] {WorkflowConstants.STATUS_APPROVED, WorkflowConstants.STATUS_INACTIVE};
			%>

			<liferay-util:include page="/support/2/advanced_search/multiple_select_checkboxes.jsp" servletContext="<%= application %>">
				<liferay-util:param name="curValues" value="<%= StringUtil.merge(displayTerms.getStatuses()) %>" />
				<liferay-util:param name="labels" value="<%= StringUtil.merge(statusLabels) %>" />
				<liferay-util:param name="name" value="<%= displayTerms.STATUSES %>" />
				<liferay-util:param name="values" value="<%= StringUtil.merge(statusValues) %>" />
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