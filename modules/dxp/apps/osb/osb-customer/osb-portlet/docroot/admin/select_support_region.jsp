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

portletURL.setParameter("mvcPath", "/admin/select_support_region.jsp");
%>

<aui:form method="post" name="fm">
	<liferay-ui:tabs
		names="support-regions"
	/>

	<liferay-ui:search-container
		headerNames="name,description"
		iteratorURL="<%= portletURL %>"
		total="<%= SupportRegionLocalServiceUtil.getSupportRegionsCount() %>"
	>
		<liferay-ui:search-container-results
			results="<%= SupportRegionLocalServiceUtil.getSupportRegions(searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.SupportRegion"
			escapedModel="<%= true %>"
			keyProperty="supportRegionId"
			modelVar="supportRegion"
		>

			<%
			StringBuilder sb = new StringBuilder(7);

			sb.append("javascript:opener.");
			sb.append(renderResponse.getNamespace());
			sb.append("selectSupportRegion('");
			sb.append(supportRegion.getSupportRegionId());
			sb.append("', '");
			sb.append(supportRegion.getName());
			sb.append("'); window.close();");

			String rowHREF = sb.toString();
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="description"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>