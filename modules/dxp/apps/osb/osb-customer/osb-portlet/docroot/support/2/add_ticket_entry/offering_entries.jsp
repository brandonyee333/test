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
AccountEntry accountEntry = (AccountEntry)request.getAttribute("add_ticket_entry.jsp-accountEntry");
OfferingEntry offeringEntry = (OfferingEntry)request.getAttribute("add_ticket_entry.jsp-offeringEntry");

String productEntryLESADisplayName = ParamUtil.getString(request, "productEntryLESADisplayName");

TreeSet<String> productEntryLESADisplayNames = new TreeSet<String>();

LinkedHashMap params = new LinkedHashMap();

params.put("validTicket", StringPool.BLANK);

List<OfferingEntryGroup> offeringEntryGroups = SupportUtil.getAvailableOfferingEntryGroups(0, accountEntry.getAccountEntryId(), new int[0], new int[] {OfferingEntryConstants.STATUS_ACTIVE}, 0, 0, 0, 0, 0, 0, params, true);

for (OfferingEntryGroup offeringEntryGroup : offeringEntryGroups) {
	ProductEntry productEntry = offeringEntryGroup.getProductEntry();

	productEntryLESADisplayNames.add(productEntry.getLESADisplayName());
}

request.setAttribute("add_ticket_entry.jsp-productEntryLESADisplayNames", productEntryLESADisplayNames);
%>

<h2 class="section-heading">
	<liferay-ui:message key="product" />:
</h2>

<c:choose>
	<c:when test="<%= productEntryLESADisplayNames.isEmpty() %>">
		<div class="portlet-msg-error">
			<liferay-ui:message key="the-requested-resource-was-not-found" />
		</div>
	</c:when>
	<c:when test="<%= productEntryLESADisplayNames.size() == 1 %>">

		<%
		productEntryLESADisplayName = productEntryLESADisplayNames.first();
		%>

		<span><%= LanguageUtil.get(pageContext, productEntryLESADisplayName) %></span>

		<input id="<portlet:namespace />productEntryLESADisplayName" name="<portlet:namespace />productEntryLESADisplayName" type="hidden" value="<%= HtmlUtil.escapeAttribute(productEntryLESADisplayName) %>">
	</c:when>
	<c:when test="<%= productEntryLESADisplayNames.size() > 1 %>">
		<c:if test='<%= productEntryLESADisplayNames.contains("digital-enterprise") %>'>
			<div class="portlet-msg-info">
				<liferay-ui:message key="if-you-require-support-for-a-potential-upgrade-to-dxp-please-choose-digital-enterprise-as-your-product-and-upgrade-as-your-component" />
			</div>
		</c:if>

		<select id="<portlet:namespace />productEntryLESADisplayName" name="<portlet:namespace />productEntryLESADisplayName" onChange="<portlet:namespace />selectProductEntry();">
			<option value=""></option>

			<%
			for (String curProductEntryLESADisplayName : productEntryLESADisplayNames) {
			%>

				<option <%= productEntryLESADisplayName.equals(curProductEntryLESADisplayName) ? "selected" : "" %> value="<%= curProductEntryLESADisplayName %>"><%= LanguageUtil.get(pageContext, curProductEntryLESADisplayName) %></option>

			<%
			}
			%>

		</select>
	</c:when>
</c:choose>

<%
PortletPreferences preferences = SupportUtil.getPortletPreferences();
%>

<c:if test="<%= productEntryLESADisplayName.equals(ProductEntryConstants.DISPLAY_NAME_PORTAL) && !productEntryLESADisplayNames.contains(ProductEntryConstants.DISPLAY_NAME_DIGITAL_ENTERPRISE) %>">

	<%
	String dxpTitle = GetterUtil.getString(preferences.getValue("dxpTitle", StringPool.BLANK));
	String dxpMessage = GetterUtil.getString(preferences.getValue("dxpMessage", StringPool.BLANK));
	%>

	<div>
		<div class="dxp-message" data-pop-display="#<portlet:namespace />dxpMessage" id="<portlet:namespace />dxpTitle">
			<%= HtmlUtil.escape(dxpTitle) %>
		</div>
	</div>

	<div class="aui-helper-hidden page-pop-up" data-overlay="true" id="<portlet:namespace />dxpMessage">
		<div class="close-pop-up">&nbsp;</div>

		<div class="pop-up-content">
			<%= HtmlUtil.escape(dxpMessage) %>
		</div>
	</div>
</c:if>

<%
String productLink = GetterUtil.getString(preferences.getValue("productLink_" + productEntryLESADisplayName, StringPool.BLANK));
%>

<c:if test="<%= Validator.isNotNull(productLink) %>">
	<div class="portlet-msg-info">
		<span class="txt-b"><liferay-ui:message arguments="<%= productEntryLESADisplayName %>" key="known-issues" translateArguments="true" /></span>

		<br />

		<span><liferay-ui:message arguments='<%= new String[] {LanguageUtil.get(pageContext, productEntryLESADisplayName), "<a href=\"" + productLink + "\" target=\"_blank\">", "</a>"} %>' key="known-issues-message" /></span>
	</div>
</c:if>

<c:if test="<%= Validator.isNotNull(productEntryLESADisplayName) %>">

	<%
	Map<String, Long> productEntryEnvironments = new HashMap<String, Long>();

	for (OfferingEntryGroup offeringEntryGroup : offeringEntryGroups) {
		ProductEntry productEntry = offeringEntryGroup.getProductEntry();

		if (productEntryLESADisplayName.equals(productEntry.getLESADisplayName())) {
			if (!productEntryEnvironments.containsKey(productEntry.getEnvironment())) {
				OfferingEntry curOfferingEntry = offeringEntryGroup.getAvailableSupportOfferingEntry();

				productEntryEnvironments.put(productEntry.getEnvironmentLabel(), curOfferingEntry.getOfferingEntryId());
			}
		}
	}
	%>

	<c:choose>
		<c:when test="<%= productEntryLESADisplayName.equals(ProductEntryConstants.DISPLAY_NAME_MOBILE_DEVICE_DETECTION) || productEntryLESADisplayName.equals(ProductEntryConstants.DISPLAY_NAME_ENTERPRISE_SEARCH_PREMIUM) || productEntryLESADisplayName.equals(ProductEntryConstants.DISPLAY_NAME_ENTERPRISE_SEARCH_STANDARD) || productEntryLESADisplayName.equals(ProductEntryConstants.DISPLAY_NAME_PORTAL) || productEntryLESADisplayName.equals(ProductEntryConstants.DISPLAY_NAME_DIGITAL_ENTERPRISE) %>">
			<h2 class="section-heading">
				<liferay-ui:message key="server" />:
			</h2>

			<c:choose>
				<c:when test="<%= productEntryEnvironments.size() == 1 %>">

					<%
					Set<Map.Entry<String, Long>> keySet = productEntryEnvironments.entrySet();

					Iterator<Map.Entry<String, Long>> iterator = keySet.iterator();

					Map.Entry<String, Long> entry = iterator.next();

					offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(entry.getValue());

					request.setAttribute("add_ticket_entry.jsp-offeringEntry", offeringEntry);
					request.setAttribute("add_ticket_entry.jsp-productEntry", offeringEntry.getProductEntry());
					%>

					<liferay-ui:message key="<%= entry.getKey() %>" />

					<input id="<portlet:namespace />offeringEntryId" name="<portlet:namespace />offeringEntryId" type="hidden" value="<%= entry.getValue() %>" />
				</c:when>
				<c:when test="<%= productEntryEnvironments.size() > 1 %>">
					<div>
						<select data-field-required-status="<%= false %>" field-required-message="<%= LanguageUtil.get(pageContext, "please-select-a-valid-server") %>" id="<portlet:namespace />offeringEntryId" name="<portlet:namespace />offeringEntryId" onChange="<portlet:namespace />selectServerComponent();">
							<option value="0"></option>

							<%
							for (Map.Entry<String, Long> entry : productEntryEnvironments.entrySet()) {
							%>

								<option <%= ((offeringEntry != null) && (entry.getValue() == offeringEntry.getOfferingEntryId())) ? "selected" : "" %> value="<%= entry.getValue() %>"><%= LanguageUtil.get(pageContext, entry.getKey()) %></option>

							<%
							}
							%>

						</select>
					</div>
				</c:when>
			</c:choose>
		</c:when>
		<c:otherwise>

			<%
			Set<Map.Entry<String, Long>> keySet = productEntryEnvironments.entrySet();

			Iterator<Map.Entry<String, Long>> iterator = keySet.iterator();

			Map.Entry<String, Long> entry = iterator.next();

			offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(entry.getValue());

			request.setAttribute("add_ticket_entry.jsp-offeringEntry", offeringEntry);
			request.setAttribute("add_ticket_entry.jsp-productEntry", offeringEntry.getProductEntry());
			%>

			<input id="<portlet:namespace />offeringEntryId" name="<portlet:namespace />offeringEntryId" type="hidden" value="<%= offeringEntry.getOfferingEntryId() %>" />
		</c:otherwise>
	</c:choose>
</c:if>

<aui:script use="aui-overlay-mask,node">
	var centerOnPage = function(node) {
		var WIN = A.getWin();

		var winHeight = WIN.get('innerHeight');

		if (winHeight == undefined) {
			winHeight = document.documentElement.clientHeight;
		}

		var winWidth = WIN.get('innerWidth');

		if (winWidth == undefined) {
			winWidth = document.documentElement.clientWidth;
		}

		var nodeHeight = node.get('clientHeight');
		var nodeWidth = node.get('clientWidth');

		var xCenter = (winWidth / 2) - (nodeWidth / 2);
		var yCenter = (winHeight / 2) - (nodeHeight / 2);

		node.setStyle('left', xCenter);
		node.setStyle('top', yCenter / 2);
	};

	var createOverlayMask = function() {
		var bindUI = function() {
			var overlayMask = A.one('.aui-overlaymask');

			if (overlayMask) {
				overlayMask.on(
					'click',
					function() {
						overlayMask.remove(true);
					}
				);
			}
		};

		var init = function() {
			var overlay = new A.OverlayMask().render();

			overlay.set('z-index', 20);
			overlay.show();

			bindUI();
		};

		return init();
	};

	var displayPopUp = function(node) {
		node.removeClass('aui-helper-hidden');

		centerOnPage(node);

		var popUpContent = node.one('.pop-up-content');

		if (node.getAttribute('data-overlay')) {
			createOverlayMask();
		}

		popUpContent.on(
			'clickoutside',
			function(event) {
				var overlayMask = A.one('.aui-overlaymask');

				if (overlayMask) {
					overlayMask.remove();
				}

				node.addClass('aui-helper-hidden');

				popUpContent.detach('clickoutside');
			}
		);
	};

	A.all('.close-announcement').on(
		'click',
		function(event) {
			var announcementContainer = event.currentTarget.ancestor();

			announcementContainer.hide();
		}
	);

	var iOS = /iPad/i.test(navigator.userAgent) || /iPhone/i.test(navigator.userAgent);
	var mouseEvent = 'click';

	if (iOS) {
		mouseEvent = 'mousemove';
	}

	var dxpTitle = A.one('#<portlet:namespace />dxpTitle');

	if (dxpTitle) {
		dxpTitle.on(
			mouseEvent,
			function(event) {
				event.stopPropagation();

				var popDisplay = event.currentTarget.getAttribute("data-pop-display");

				var popUp = A.one(popDisplay);

				displayPopUp(popUp);
			}
		);
	}
</aui:script>