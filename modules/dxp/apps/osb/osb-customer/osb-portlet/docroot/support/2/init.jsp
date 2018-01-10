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
boolean liferayIncOrg = OrganizationLocalServiceUtil.hasUserOrganization(user.getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID);

boolean clockedIn = true;

if (liferayIncOrg && SupportWorkerLocalServiceUtil.hasSupportWorker(user.getUserId(), SupportWorkerConstants.ROLE_WATCHER)) {
	clockedIn = SupportWorkerLocalServiceUtil.isClockedIn(user.getUserId());
}

boolean supportPartnerWorker = false;

LinkedHashMap<String, Object> partnerWorkerParams = new LinkedHashMap<String, Object>();

partnerWorkerParams.put("partnerWorker", Long.valueOf(user.getUserId()));
partnerWorkerParams.put("status", AccountEntryConstants.STATUSES_ACTIVE);

if (AccountEntryLocalServiceUtil.searchCount(null, partnerWorkerParams) > 0) {
	supportPartnerWorker = true;
}

boolean reverseCommentOrder = SupportUtil.getUserPreferenceValue(user.getUserId(), "reverseCommentOrder");

boolean screenShareMode = false;

if (liferayIncOrg) {
	screenShareMode = SupportUtil.getUserPreferenceValue(user.getUserId(), "screenShareMode");
}
%>

<c:if test='<%= !GetterUtil.getBoolean(request.getAttribute("init.jsp-cssIncluded")) && !themeDisplay.isStateExclusive() %>'>

	<%
	request.setAttribute("init.jsp-cssIncluded", Boolean.TRUE.toString());

	Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);
	%>

	<liferay-util:html-top>
		<link href="<%= PortalUtil.getStaticResourceURL(request, request.getContextPath() + "/support/2/css/main.css", portlet.getTimestamp()) %>" rel="stylesheet" type="text/css" />
	</liferay-util:html-top>
</c:if>

<c:if test='<%= !GetterUtil.getBoolean(request.getAttribute("init.jsp-navIncluded")) && !themeDisplay.isStateExclusive() && !themeDisplay.isStatePopUp() %>'>

	<%
	request.setAttribute("init.jsp-navIncluded", Boolean.TRUE.toString());

	List<SearchFilter> searchFilters = SearchFilterLocalServiceUtil.getSearchFilters(user.getUserId(), PortalUtil.getClassNameId(TicketEntry.class.getName()));

	boolean lesaAccountCustomer = false;

	List<Long> developerAccountEntryIds = new ArrayList<Long>();

	List<AccountCustomer> accountCustomers = AccountCustomerLocalServiceUtil.getUserAccountCustomers(user.getUserId());

	for (AccountCustomer accountCustomer : accountCustomers) {
		if (!lesaAccountCustomer) {
			AccountEntry accountEntry = accountCustomer.getAccountEntry();

			if (accountEntry.getType() != AccountEntryConstants.TYPE_TRIAL) {
				lesaAccountCustomer = true;
			}
		}

		if (accountCustomer.getRole() == AccountCustomerConstants.ROLE_DEVELOPER) {
			developerAccountEntryIds.add(accountCustomer.getAccountEntryId());
		}
	}

	boolean partnerManager = PartnerWorkerLocalServiceUtil.hasPartnerWorkerRole(user.getUserId(), PartnerWorkerConstants.ROLE_MANAGER);
	%>

	<c:choose>
		<c:when test='<%= BrowserSnifferUtil.isMobile(request) %>'>
			<%@ include file="/support/2/nav_mobile.jspf" %>
		</c:when>
		<c:otherwise>
			<%@ include file="/support/2/nav.jspf" %>
		</c:otherwise>
	</c:choose>

	<c:if test="<%= !developerAccountEntryIds.isEmpty() || !searchFilters.isEmpty() || supportPartnerWorker %>">
		<aui:script use="aui-base">
			var homePage = A.all('[data-page-id="homePage"]');
			var navSubmenu = A.all('.nav-submenu');

			homePage.on(
				'click',
				function() {
					navSubmenu.toggleClass('show');
				}
			);
		</aui:script>
	</c:if>

	<aui:script>
		Liferay.provide(
			window,
			'<portlet:namespace />navSelect',
			function(pageId) {
				var A = AUI();

				var activePage = A.one('[data-page-id=' + pageId + 'Page]');

				activePage.addClass('active');
			},
			['aui-node']
		);
	</aui:script>

	<%@ include file="/support/2/banner.jspf" %>
</c:if>