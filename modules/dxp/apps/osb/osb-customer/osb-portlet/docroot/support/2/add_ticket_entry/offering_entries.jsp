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

<aui:field-wrapper label="product">
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

			<aui:select disabled="<%= true %>" label="" name="productEntryLESADisplayName">
				<aui:option label="<%= productEntryLESADisplayName %>" value="<%= productEntryLESADisplayName %>" />
			</aui:select>

			<aui:input label="" name="productEntryLESADisplayName" type="hidden" value="<%= productEntryLESADisplayName %>" />
		</c:when>
		<c:when test="<%= productEntryLESADisplayNames.size() > 1 %>">
			<c:if test="<%= productEntryLESADisplayNames.contains(ProductEntryConstants.DISPLAY_NAME_DIGITAL_ENTERPRISE) %>">
				<div class="portlet-msg-info">
					<liferay-ui:message key="if-you-require-support-for-a-potential-upgrade-to-dxp-please-choose-digital-enterprise-as-your-product-and-upgrade-as-your-component" />
				</div>
			</c:if>

			<aui:select label="" name="productEntryLESADisplayName" onChange='<%= renderResponse.getNamespace() + "selectProductEntry();" %>'>
				<aui:option value="" />

				<%
				for (String curProductEntryLESADisplayName : productEntryLESADisplayNames) {
				%>

					<aui:option label="<%= curProductEntryLESADisplayName %>" selected="<%= productEntryLESADisplayName.equals(curProductEntryLESADisplayName) %>" value="<%= curProductEntryLESADisplayName %>" />

				<%
				}
				%>

			</aui:select>
		</c:when>
	</c:choose>
</aui:field-wrapper>

<%
PortletPreferences preferences = SupportUtil.getPortletPreferences();
%>

<c:if test="<%= productEntryLESADisplayName.equals(ProductEntryConstants.DISPLAY_NAME_PORTAL) && !productEntryLESADisplayNames.contains(ProductEntryConstants.DISPLAY_NAME_DIGITAL_ENTERPRISE) %>">

	<%
	String dxpTitle = GetterUtil.getString(preferences.getValue("dxpTitle", StringPool.BLANK));
	String dxpMessage = GetterUtil.getString(preferences.getValue("dxpMessage", StringPool.BLANK));
	%>

	<div class="dxp-message" id="<portlet:namespace />dxpTitle">
		<%= HtmlUtil.escape(dxpTitle) %>
	</div>

	<aui:script use="aui-base,liferay-util-window">
		var DOC = A.getDoc();

		var dialog = Liferay.Util.Window.getWindow(
			{
				dialog: {
					bodyContent: '<%= HtmlUtil.escape(dxpMessage) %>',
					centered: true,
					hideOn: [
						{
							eventName: 'key',
							keyCode: 'esc',
							node: DOC
						},
						{
							eventName: 'clickoutside',
							node: DOC
						}
					],
					modal: true,
					id: '<portlet:namespace />dxpMessage',
					visible: false
				}
			}
		).render();

		var dxpTitle = A.one('#<portlet:namespace />dxpTitle');

		if (dxpTitle) {
			dxpTitle.on(
				'click',
				function(event) {
					dialog.show();
				}
			);
		}
	</aui:script>
</c:if>

<%
String productLink = GetterUtil.getString(preferences.getValue("productLink_" + productEntryLESADisplayName, StringPool.BLANK));
%>

<c:if test="<%= Validator.isNotNull(productLink) %>">
	<div class="portlet-msg-info">
		<div class="txt-sb">
			<liferay-ui:message arguments="<%= productEntryLESADisplayName %>" key="known-issues" translateArguments="<%= true %>" />
		</div>

		<%
		String[] arguments = {LanguageUtil.get(request, productEntryLESADisplayName), "<a href=\"" + productLink + "\" target=\"_blank\">", "</a>"};
		%>

		<liferay-ui:message arguments="<%= arguments %>" key="known-issues-message" />
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

					<aui:select disabled="<%= true %>" label="server" name="offeringEntryId">
						<aui:option label="<%= entry.getKey() %>" value="<%= entry.getValue() %>" />
					</aui:select>

					<aui:input label="" name="offeringEntryId" type="hidden" value="<%= entry.getValue() %>" />
				</c:when>
				<c:when test="<%= productEntryEnvironments.size() > 1 %>">
					<aui:select data-field-required-status="<%= false %>" field-required-message='<%= LanguageUtil.get(request, "please-select-a-valid-server") %>' label="server" name="offeringEntryId" onChange='<%= renderResponse.getNamespace() + "selectServerComponent();" %>'>
						<aui:option value="0" />

						<%
						for (Map.Entry<String, Long> entry : productEntryEnvironments.entrySet()) {
						%>

							<aui:option label="<%= entry.getKey() %>" selected="<%= (offeringEntry != null) && (entry.getValue() == offeringEntry.getOfferingEntryId()) %>" value="<%= entry.getValue() %>" />

						<%
						}
						%>

					</aui:select>
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

			<aui:input name="offeringEntryId" type="hidden" value="<%= offeringEntry.getOfferingEntryId() %>" />
		</c:otherwise>
	</c:choose>
</c:if>

<aui:script use="aui-base">
	A.all('.close-announcement').on(
		'click',
		function(event) {
			var announcementContainer = event.currentTarget.ancestor();

			announcementContainer.hide();
		}
	);
</aui:script>