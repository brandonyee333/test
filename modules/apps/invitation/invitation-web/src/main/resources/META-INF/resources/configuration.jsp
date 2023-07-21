<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<div class="portlet-configuration-body-content">
		<div class="container-fluid-1280">
			<liferay-ui:error key="emailMessageBody" message="please-enter-a-valid-body" />
			<liferay-ui:error key="emailMessageSubject" message="please-enter-a-valid-subject" />

			<liferay-frontend:email-notification-settings
				emailBody='<%= InvitationUtil.getEmailMessageBodyXml(portletPreferences, renderRequest, "preferences") %>'
				emailDefinitionTerms="<%= InvitationUtil.getEmailDefinitionTerms(renderRequest) %>"
				emailParam="emailMessage"
				emailSubject='<%= InvitationUtil.getEmailMessageSubjectXml(portletPreferences, renderRequest, "preferences") %>'
				showEmailEnabled="<%= false %>"
			/>
		</div>
	</div>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>