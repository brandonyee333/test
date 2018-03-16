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

<c:choose>
	<c:when test="<%= SessionMessages.contains(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_REFRESH_PORTLET) %>">
		<aui:script>
			parent.location.reload();
		</aui:script>
	</c:when>
	<c:otherwise>

		<%
		String tabs1 = ParamUtil.getString(request, "tabs1", "public");

		String redirect = ParamUtil.getString(request, "redirect");

		String backURL = ParamUtil.getString(request, "backURL", redirect);

		if (Validator.isNull(backURL)) {
			PortletURL portletURL = renderResponse.createRenderURL();

			portletURL.setParameter("mvcPath", "/support/2/view.jsp");
			portletURL.setWindowState(LiferayWindowState.NORMAL);

			backURL = portletURL.toString();
		}

		TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

		long accountEntryId = BeanParamUtil.getLong(ticketEntry, request, "accountEntryId");

		AccountEntry accountEntry = null;

		if (accountEntryId > 0) {
			accountEntry = AccountEntryServiceUtil.getAccountEntry(accountEntryId);
		}
		else {
			accountEntry = ticketEntry.getAccountEntry();
		}

		long offeringEntryId = BeanParamUtil.getLong(ticketEntry, request, "offeringEntryId");

		OfferingEntry offeringEntry = null;

		if (offeringEntryId > 0) {
			offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);
		}
		else {
			offeringEntry = ticketEntry.getOfferingEntry();
		}

		ProductEntry productEntry = offeringEntry.getProductEntry();
		SupportResponse supportResponse = ticketEntry.getSupportResponse();

		int component = BeanParamUtil.getInteger(ticketEntry, request, "component");

		boolean hasUpdateAdmin = OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_ADMIN);

		boolean hasUpdateAdvanced = hasUpdateAdmin || OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_ADVANCED);

		boolean hasUpdateBasic = hasUpdateAdvanced || OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_BASIC);

		boolean closed = false;

		if (ticketEntry.getStatus() == TicketEntryConstants.STATUS_CLOSED) {
			closed = true;
		}

		boolean accountCustomer = false;

		if (AccountCustomerLocalServiceUtil.hasAccountCustomer(user.getUserId(), accountEntry.getAccountEntryId())) {
			accountCustomer = true;
		}

		boolean partnerWorker = false;

		if (accountEntry.isPartnerManagedSupport() && PartnerWorkerLocalServiceUtil.hasPartnerWorker(user.getUserId(), accountEntry.getPartnerEntryId())) {
			partnerWorker = true;
		}

		boolean ticketWorker = false;

		if (liferayIncOrg || partnerWorker) {
			ticketWorker = true;
		}

		boolean canForward = false;

		if ((ticketEntry.getSeverity() == SupportResponseConstants.SEVERITY_CRITICAL) && supportResponse.isPlatinumLevel() && OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.FORWARD)) {
			canForward = true;
		}

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("mvcPath", "/support/2/edit_ticket_entry.jsp");
		portletURL.setParameter("tabs1", tabs1);
		portletURL.setParameter("redirect", redirect);
		portletURL.setParameter("ticketEntryId", String.valueOf(ticketEntry.getTicketEntryId()));
		%>

		<%
		request.setAttribute("edit_ticket_entry.jsp-accountCustomer", accountCustomer);
		request.setAttribute("edit_ticket_entry.jsp-accountEntry", accountEntry);
		request.setAttribute("edit_ticket_entry.jsp-backURL", backURL);
		request.setAttribute("edit_ticket_entry.jsp-canForward", canForward);
		request.setAttribute("edit_ticket_entry.jsp-closed", closed);
		request.setAttribute("edit_ticket_entry.jsp-hasUpdateAdmin", hasUpdateAdmin);
		request.setAttribute("edit_ticket_entry.jsp-hasUpdateAdvanced", hasUpdateAdvanced);
		request.setAttribute("edit_ticket_entry.jsp-hasUpdateBasic", hasUpdateBasic);
		request.setAttribute("edit_ticket_entry.jsp-offeringEntry", offeringEntry);
		request.setAttribute("edit_ticket_entry.jsp-partnerWorker", partnerWorker);
		request.setAttribute("edit_ticket_entry.jsp-portletURL", portletURL);
		request.setAttribute("edit_ticket_entry.jsp-productEntry", productEntry);
		request.setAttribute("edit_ticket_entry.jsp-supportResponse", supportResponse);
		request.setAttribute("edit_ticket_entry.jsp-ticketWorker", ticketWorker);
		%>

		<div class="detail-view-filter" id="<portlet:namespace />detailViewFilter">
			<div class="detail-view-fade" id="<portlet:namespace />detailViewFade"></div>
		</div>

		<liferay-util:include page="/support/2/edit_ticket_entry/exceptions.jsp" servletContext="<%= application %>" />

		<liferay-util:include page="/support/2/edit_ticket_entry/header.jsp" servletContext="<%= application %>" />

		<liferay-util:include page="/common/dialog.jsp" servletContext="<%= application %>">
			<liferay-util:param name="centered" value="<%= Boolean.FALSE.toString() %>" />
			<liferay-util:param name="close" value="<%= Boolean.FALSE.toString() %>" />
			<liferay-util:param name="cssClass" value="login-info-dialog" />
			<liferay-util:param name="dialogMVCPath" value="/support/2/edit_ticket_entry/login_info_dialog.jsp" />
			<liferay-util:param name="draggable" value="<%= Boolean.FALSE.toString() %>" />
			<liferay-util:param name="id" value="3" />
			<liferay-util:param name="modal" value="<%= Boolean.FALSE.toString() %>" />
			<liferay-util:param name="width" value="715" />
		</liferay-util:include>

		<liferay-util:include page="/support/2/edit_ticket_entry/sidebar.jsp" servletContext="<%= application %>" />

		<liferay-util:include page="/support/2/edit_ticket_entry/details_tabs.jsp" servletContext="<%= application %>" />

		<c:if test="<%= canForward && clockedIn %>">
			<liferay-util:include page="/support/2/edit_ticket_entry/forward.jsp" servletContext="<%= application %>" />
		</c:if>

		<liferay-util:include page="/support/2/edit_ticket_entry/discussion.jsp" servletContext="<%= application %>" />

		<%
		StringBundler sb = new StringBundler(5);

		sb.append(StringPool.OPEN_BRACKET);
		sb.append(ticketEntry.getDisplayId());
		sb.append(StringPool.CLOSE_BRACKET);
		sb.append(StringPool.SPACE);
		sb.append(ticketEntry.getSubject());

		PortalUtil.setPageSubtitle(sb.toString(), request);
		%>

		<aui:script>
			function <portlet:namespace />pinElement(id, pinOffset) {
				var element = document.getElementById(id);

				element.classList.remove('pinned');

				if (window.pageYOffset >= pinOffset) {
					element.classList.add('pinned');
				}
			}

			function <portlet:namespace />pinElements(pinElementIds, offsetElementIds, pinOffset) {
				var totalPinOffset = pinOffset;

				for (var i = 0; i < offsetElementIds.length; i++) {
					var offsetElement = document.getElementById(offsetElementIds[i]);

					totalPinOffset += offsetElement.offsetHeight;
				}

				for (var j = 0; j < pinElementIds.length; j++) {
					<portlet:namespace />pinElement(pinElementIds[j], totalPinOffset);
				}
			}

			function <portlet:namespace />submitCloseTicketValues(resolution, addCommentBody) {
				document.<portlet:namespace />ticketStatusFm.<portlet:namespace /><%= CMDConstants.CMD %>.value = '<%= CMDConstants.CLOSE %>';
				document.<portlet:namespace />ticketStatusFm.<portlet:namespace />redirect.value = '<%= portletURL.toString() %>';
				document.<portlet:namespace />ticketStatusFm.<portlet:namespace />resolution.value = resolution;
				document.<portlet:namespace />ticketStatusFm.<portlet:namespace />body.value = addCommentBody;

				submitForm(document.<portlet:namespace />ticketStatusFm);
			}

			<c:if test="<%= clockedIn %>">
				Liferay.provide(
					window,
					'<portlet:namespace />setUpThreeDotMenus',
					function() {
						var A = AUI();

						A.all('.three-dot').each(
							function(icon) {
								var clickEvent = A.Event.getListeners(icon, 'click');
								var clickOutsideEvent = A.Event.getListeners(icon, 'clickoutside');

								if (!clickEvent) {
									icon.on(
										'click',
										function() {
											icon.toggleClass('open-drop-down');
										}
									);
								}

								if (!clickOutsideEvent) {
									icon.on(
										'clickoutside',
										function() {
											icon.removeClass('open-drop-down');
										}
									);
								}
							}
						);
					},
					['aui-base']
				);

				<portlet:namespace />setUpThreeDotMenus();
			</c:if>

			if (window.parent.document.getElementById('<portlet:namespace />loginDialog')) {
				window.parent.<portlet:namespace />openDialog(3);
			}

			window.addEventListener(
				'resize',
				function() {
					<portlet:namespace />updateShowMoreButton();
				},
				false
			);

			var offsetElementIds = ['<portlet:namespace />showMoreButtonContainer', '<portlet:namespace />ticketTabContent'];

			var pinElementIds = ['<portlet:namespace />discussionTabs', '<portlet:namespace />detailViewFade', '<portlet:namespace />detailViewFilter'];

			window.addEventListener(
				'scroll',
				function() {
					<portlet:namespace />pinElements(pinElementIds, offsetElementIds, 60);
				},
				false
			);
		</aui:script>

		<aui:script use="aui-base">
			var advSearchIcon = A.one('#<portlet:namespace />advSearchIcon');

			if (advSearchIcon) {
				advSearchIcon.addClass('active');
			}
		</aui:script>
	</c:otherwise>
</c:choose>