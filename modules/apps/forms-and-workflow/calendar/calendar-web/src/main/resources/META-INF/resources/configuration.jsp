<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<liferay-ui:tabs
				names="user-settings,display-settings,rss"
				param="tabs2"
				refresh="<%= false %>"
				type="tabs nav-tabs-default"
			>
				<liferay-ui:section>
					<%@ include file="/configuration/user_settings.jspf" %>
				</liferay-ui:section>

				<liferay-ui:section>
					<%@ include file="/configuration/display_settings.jspf" %>
				</liferay-ui:section>

				<liferay-ui:section>
					<%@ include file="/configuration/rss.jspf" %>
				</liferay-ui:section>
			</liferay-ui:tabs>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>