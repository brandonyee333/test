<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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