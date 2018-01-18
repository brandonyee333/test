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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

if (Validator.isNull(backURL)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/support/2/advanced_search.jsp");
	portletURL.setParameter("searchTab", "accounts");
	portletURL.setWindowState(LiferayWindowState.NORMAL);

	backURL = portletURL.toString();
}

long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

AccountEntry accountEntry = AccountEntryServiceUtil.getAccountEntry(accountEntryId);

boolean ticketWorker = false;

if (liferayIncOrg || (accountEntry.isPartnerManagedSupport() && PartnerWorkerLocalServiceUtil.hasPartnerWorker(user.getUserId(), accountEntry.getPartnerEntryId()))) {
	ticketWorker = true;
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/2/edit_account_entry.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
%>

<portlet:renderURL var="editAccountEntryURL">
	<portlet:param name="mvcPath" value="/support/2/edit_account_entry.jsp" />
</portlet:renderURL>

<aui:form action="<%= editAccountEntryURL %>" class="uni-form" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />
	<aui:input name="key" type="hidden" />
	<aui:input name="offeringEntryIds" type="hidden" />

	<%
	request.setAttribute("edit_account_entry.jsp-accountEntry", accountEntry);
	request.setAttribute("edit_account_entry.jsp-backURL", backURL);
	request.setAttribute("edit_account_entry.jsp-portletURL", portletURL);
	request.setAttribute("edit_account_entry.jsp-redirect", redirect);
	request.setAttribute("edit_account_entry.jsp-ticketWorker", ticketWorker);
	%>

	<div class="detail-view-filter" id="<portlet:namespace />detailViewFilter">
		<div class="detail-view-fade" id="<portlet:namespace />detailViewFade"></div>
	</div>

	<liferay-util:include page="/support/2/edit_account_entry/header.jsp" servletContext="<%= application %>" />

	<liferay-util:include page="/support/2/edit_account_entry/details_tabs.jsp" servletContext="<%= application %>" />
</aui:form>

<c:if test="<%= liferayIncOrg %>">
	<portlet:actionURL name="updateAccountInformation" var="updateAccountInformationURL">
		<portlet:param name="mvcPath" value="/support/2/edit_account_entry.jsp" />
	</portlet:actionURL>

	<aui:form action="<%= updateAccountInformationURL %>" class="uni-form" method="post" name="fm1">
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />
		<aui:input name="additionalInfoTab" type="hidden" />
		<aui:input name="section" type="hidden" />

		<liferay-util:include page="/support/2/edit_account_entry/additional_info_tabs.jsp" servletContext="<%= application %>" />
	</aui:form>
</c:if>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />reveal',
		function(selector, id) {
			var A = AUI();

			A.all(selector + ' .tab-content-tab').hide();

			var contentId = id.charAt(0).toUpperCase() + id.substr(1);

			var tabContent = A.one(selector + ' .tab-content #<portlet:namespace />content' + contentId);

			if (tabContent) {
				tabContent.show();
			}

			A.all(selector + ' .tabs span').removeClass('selected');

			var tab = A.one(selector + ' .tabs #<portlet:namespace />' + id);

			if (tab) {
				tab.addClass('selected');
			}

			window.scroll(0, 0);
		},
		['aui-base']
	);
</aui:script>

<aui:script use="aui-base">
	function <portlet:namespace />setUpThreeDotMenus() {
		A.all('.three-dot-icon').each(
			function(icon) {
				var event = A.Event.getListeners(icon, 'click');

				var parent = icon.get('parentNode');

				if (!event) {
					icon.on(
						'click',
						function() {
							parent.toggleClass('open-drop-down');
						}
					);

					icon.on(
						'clickoutside',
						function() {
							parent.removeClass('open-drop-down');
						}
					);
				}
			}
		);
	}

	<portlet:namespace />setUpThreeDotMenus();
</aui:script>