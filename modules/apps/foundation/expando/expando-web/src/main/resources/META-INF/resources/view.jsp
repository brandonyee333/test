<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String displayStyle = ParamUtil.getString(request, "displayStyle", "list");

PortletURL portletURL = renderResponse.createRenderURL();

List<String> headerNames = new ArrayList<String>();

headerNames.add("resource");
headerNames.add("custom-fields");

List<CustomAttributesDisplay> customAttributesDisplays = PortletLocalServiceUtil.getCustomAttributesDisplays();

Collections.sort(customAttributesDisplays, new CustomAttributesDisplayComparator(locale));
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="custom-fields" selected="<%= true %>" />
	</aui:nav>
</aui:nav-bar>

<liferay-frontend:management-bar>
	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= renderResponse.createRenderURL() %>"
		/>
	</liferay-frontend:management-bar-filters>

	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= renderResponse.createRenderURL() %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>
</liferay-frontend:management-bar>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		emptyResultsMessage='<%= LanguageUtil.get(request, "custom-fields-are-not-enabled-for-any-resource") %>'
		iteratorURL="<%= portletURL %>"
		total="<%= customAttributesDisplays.size() %>"
	>
		<liferay-ui:search-container-results
			results="<%= customAttributesDisplays %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.expando.kernel.model.CustomAttributesDisplay"
			modelVar="customAttributesDisplay"
			stringKey="<%= true %>"
		>
			<liferay-ui:search-container-row-parameter
				name="customAttributesDisplay"
				value="<%= customAttributesDisplay %>"
			/>

			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcPath" value="/view_attributes.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="modelResource" value="<%= customAttributesDisplay.getClassName() %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="resource"
			>
				<liferay-ui:icon
					icon="<%= customAttributesDisplay.getIconCssClass() %>"
					label="<%= true %>"
					markupView="lexicon"
					message="<%= ResourceActionsUtil.getModelResource(locale, customAttributesDisplay.getClassName()) %>"
				/>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>
</div>