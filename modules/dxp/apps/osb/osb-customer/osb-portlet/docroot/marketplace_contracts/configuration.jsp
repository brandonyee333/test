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

<%@ include file="/marketplace_contracts/init.jsp" %>

<%
if (appEntryId > 0) {
	defaultEula = true;
}
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<div class="marketplace-contracts view-configuration">
	<aui:form action="<%= configurationURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<liferay-ui:error key="appEntryId" message="no-app-was-found-with-the-app-entry-id-provided" />

		<aui:fieldset cssClass="eula-type">
			<aui:input checked="<%= defaultEula %>" cssClass="eula" label="default-app-eula" name="defaultEula" type="radio" value="<%= true %>" />
			<aui:input checked="<%= !defaultEula %>" cssClass="eula" label="app-eula" name="defaultEula" type="radio" value="<%= false %>" />
		</aui:fieldset>

		<aui:fieldset>
			<aui:input cssClass="app-entry-id" disabled="<%= defaultEula %>" name="appEntryId" type="text" value="<%= (appEntryId <= 0) ? StringPool.BLANK : appEntryId %>" />
		</aui:fieldset>

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script use="aui-dialog">
	var eulaType = A.one('.marketplace-contracts .eula-type');

	if (eulaType != null) {
		eulaType.delegate(
			'change',
			function(event) {
				var targetRadio = event.currentTarget;

				var appEntryId = A.one('#<portlet:namespace />appEntryId');

				appEntryId.attr('value', '');

				if (targetRadio.val() == 'true') {
					appEntryId.attr('disabled', true);
				}
				else if (targetRadio.val() == 'false') {
					appEntryId.removeAttribute('disabled');
				}
			},
			'input[type=radio]'
		);
	}
</aui:script>