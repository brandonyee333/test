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
PartnerEntry partnerEntry = (PartnerEntry)request.getAttribute("edit_partner_entry.jsp-partnerEntry");
PortletURL portletURL = (PortletURL)request.getAttribute("edit_partner_entry.jsp-portletURL");

PortletURL partnerEntryTicketSearchURL = renderResponse.createRenderURL();

partnerEntryTicketSearchURL.setParameter("mvcPath", "/support/2/advanced_search.jsp");
partnerEntryTicketSearchURL.setParameter("assignedPartnerEntryIds", String.valueOf(partnerEntry.getPartnerEntryId()));
%>

<aui:button cssClass="aui-button-input" href="<%= partnerEntryTicketSearchURL.toString() %>" value="view-tickets" />

<%
Map<String, String> dropDownList = new TreeMap<String, String>();

if (liferayIncOrg) {
	PortletURL buttonURL = renderResponse.createRenderURL();

	buttonURL.setParameter("mvcPath", "/support/2/add_ticket_entry.jsp");
	buttonURL.setParameter("partnerEntryId", String.valueOf(partnerEntry.getPartnerEntryId()));
	buttonURL.setWindowState(LiferayWindowState.POP_UP);

	dropDownList.put("add-ticket", "location.href='" + buttonURL.toString() + "';");
}
%>

<c:if test="<%= !dropDownList.isEmpty() %>">
	<span class="three-dot" id="<portlet:namespace />threeDotMenu">
		<span class="three-dot-icon" id="<portlet:namespace />threeDotIcon">
			<span style="top: 6px;"></span>
			<span style="top: 14px;"></span>
			<span style="top: 22px;"></span>
		</span>

		<div class="drop-down-menu">
			<div class="drop-down-arrow">
				<div></div>
			</div>

			<ul>

				<%
				for (Map.Entry<String, String> entry : dropDownList.entrySet()) {
					String curLabel = entry.getKey();
					String curOnClick = entry.getValue();

					StringBundler sb = new StringBundler(5);

					sb.append("<a href=\"javascript:;\" onClick=\"");
					sb.append(curOnClick);
					sb.append("\">");
					sb.append(LanguageUtil.get(request, curLabel));
					sb.append("</a>");
				%>

					<li>
						<%= sb.toString() %>
					</li>

				<%
				}
				%>

			</ul>
		</div>
	</span>

	<aui:script use="aui-base">
		var threeDotIcon = AUI().one('#<portlet:namespace />threeDotIcon');
		var threeDotMenu = AUI().one('#<portlet:namespace />threeDotMenu');

		if (threeDotIcon && threeDotMenu) {
			threeDotIcon.on(
				'click',
				function() {
					threeDotMenu.toggleClass('open-drop-down');
				}
			);

			threeDotIcon.on(
				'clickoutside',
				function() {
					threeDotMenu.removeClass('open-drop-down');
				}
			);
		}
	</aui:script>
</c:if>