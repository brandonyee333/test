<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long wsrpConsumerId = ParamUtil.getLong(request, "wsrpConsumerId");

WSRPConsumer wsrpConsumer = WSRPConsumerLocalServiceUtil.getWSRPConsumer(wsrpConsumerId);

WSRPConsumerManager wsrpConsumerManager = WSRPConsumerManagerFactoryUtil.getWSRPConsumerManager(wsrpConsumer);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/view_consumer_portlets.jsp");
portletURL.setParameter("wsrpConsumerId", String.valueOf(wsrpConsumerId));

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(LanguageUtil.get(request, "manage-portlets"));
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="portlets" selected="<%= true %>" />
	</aui:nav>
</aui:nav-bar>

<liferay-frontend:management-bar>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= PortletURLUtil.clone(portletURL, renderResponse) %>"
			selectedDisplayStyle="list"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= PortletURLUtil.clone(portletURL, renderResponse) %>"
		/>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-portlets"
		headerNames="name,remote-portlet"
		iteratorURL="<%= portletURL %>"
		total="<%= WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortletsCount(wsrpConsumerId) %>"
	>
		<liferay-ui:search-container-results
			results="<%= WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlets(wsrpConsumerId, searchContainer.getStart(), searchContainer.getEnd()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.wsrp.model.WSRPConsumerPortlet"
			keyProperty="wsrpConsumerPortletId"
			modelVar="wsrpConsumerPortlet"
		>
			<liferay-ui:search-container-column-text
				cssClass="table-cell-content"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				buffer="buffer"
				cssClass="table-cell-content"
				name="remote-portlet"
			>

				<%
				PortletDescription portletDescription = wsrpConsumerManager.getPortletDescription(wsrpConsumerPortlet.getPortletHandle());

				if (portletDescription != null) {
					buffer.append(wsrpConsumerManager.getDisplayName(portletDescription));
				}
				else {
					buffer.append(LanguageUtil.format(locale, "is-temporarily-unavailable", "remote-portlet"));
				}
				%>

			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				path="/admin/consumer_portlet_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<portlet:renderURL var="addPortletURL">
	<portlet:param name="mvcPath" value="/admin/edit_consumer_portlet.jsp" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
</portlet:renderURL>

<liferay-frontend:add-menu>
	<liferay-frontend:add-menu-item
		title='<%= LanguageUtil.get(request, "add-portlet") %>'
		url="<%= addPortletURL.toString() %>"
	/>
</liferay-frontend:add-menu>

<%
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "manage-portlets"), currentURL);
%>