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
boolean accountCustomer = false;

List<AccountCustomer> accountCustomers = AccountCustomerLocalServiceUtil.getUserAccountCustomers(user.getUserId());

if (!accountCustomers.isEmpty()) {
	accountCustomer = true;
}

boolean osbAdmin = RoleLocalServiceUtil.hasUserRole(user.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID);
%>

<portlet:actionURL name="updateUserGuides" var="updateUserGuidesURL">
	<portlet:param name="mvcPath" value="/support/2/help.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateUserGuidesURL %>" enctype="multipart/form-data" method="post" name="fm">
	<div class="user-guide">
		<div id="<portlet:namespace />helpDisplay">
			<br />

			<c:if test="<%= osbAdmin %>">
				<div class="fr">
					<input onClick="<portlet:namespace />toggleSection('<portlet:namespace />helpDisplay', '<portlet:namespace />helpEditDisplay');" type="button" value="<liferay-ui:message key="edit" />" />
				</div>
			</c:if>

			<%
			PortletPreferences supportPortletPreferences = SupportUtil.getPortletPreferences();

			boolean customerUserGuide = false;
			boolean liferayUserGuide = false;
			boolean partnerUserGuide = false;

			if (liferayIncOrg) {
				liferayUserGuide = true;
			}
			else if (supportPartnerWorker) {
				partnerUserGuide = true;
			}
			else if (accountCustomer) {
				customerUserGuide = true;
			}
			%>

			<c:if test="<%= osbAdmin || customerUserGuide %>">
				<div>
					<%= GetterUtil.getString(supportPortletPreferences.getValue("customerUserGuide", StringPool.BLANK)) %>
				</div>
			</c:if>

			<c:if test="<%= osbAdmin || partnerUserGuide %>">
				<div>
					<%= GetterUtil.getString(supportPortletPreferences.getValue("partnerUserGuide", StringPool.BLANK)) %>
				</div>
			</c:if>

			<c:if test="<%= liferayUserGuide %>">
				<div>
					<%= GetterUtil.getString(supportPortletPreferences.getValue("liferayUserGuide", StringPool.BLANK)) %>
				</div>
			</c:if>
		</div>

		<c:if test="<%= osbAdmin %>">
			<div class="aui-helper-hidden help-edit-display" id="<portlet:namespace />helpEditDisplay">
				<div class="aui-helper-clearfix callout-content">
					<br />

					<div class="aui-w30 content-column">
						<div>
							<liferay-ui:message key="customer-user-guide" />:
						</div>

						<div>
							<input id="<portlet:namespace />customerUserGuide" name="<portlet:namespace />customerUserGuide" type="file" />
						</div>
					</div>

					<div class="aui-w30 content-column">
						<div>
							<liferay-ui:message key="partner-user-guide" />:
						</div>

						<div>
							<input id="<portlet:namespace />partnerUserGuide" name="<portlet:namespace />partnerUserGuide" type="file" />
						</div>
					</div>

					<div class="aui-w30 content-column">
						<div>
							<liferay-ui:message key="liferay-user-guide" />:
						</div>

						<div>
							<input id="<portlet:namespace />liferayUserGuide" name="<portlet:namespace />liferayUserGuide" type="file" />
						</div>
					</div>

					<div class="aui-w10 content-column">
						<input type="submit" value="<liferay-ui:message key="save" />" />

						<input onClick="<portlet:namespace />toggleSection('<portlet:namespace />helpEditDisplay', '<portlet:namespace />helpDisplay');" type="button" value="<liferay-ui:message key="cancel" />" />
					</div>
				</div>
			</div>
		</c:if>
	</div>
</aui:form>

<aui:script>
	<portlet:namespace />navSelect('help');

	function <portlet:namespace />toggleSection(hideId, showId) {
		var A = AUI();

		A.one('#' + hideId).hide();
		A.one('#' + showId).show();
	}
</aui:script>