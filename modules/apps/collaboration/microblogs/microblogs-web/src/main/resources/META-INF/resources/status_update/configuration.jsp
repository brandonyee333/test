<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="microblogs-status-update">
	<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

	<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<div class="portlet-configuration-body-content">
			<div class="container-fluid-1280">
				<liferay-ui:panel
					collapsible="<%= true %>"
					extended="<%= true %>"
					persistState="<%= true %>"
					title="display-settings"
				>
					<aui:input label="display-most-recent-status" name="preferences--showStatus--" type="checkbox" value="<%= showStatus %>" />
				</liferay-ui:panel>
			</div>
		</div>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" />
		</aui:button-row>
	</aui:form>
</div>