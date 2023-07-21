<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long wsrpConsumerId = ParamUtil.getLong(request, "wsrpConsumerId");

WSRPConsumer wsrpConsumer = WSRPConsumerLocalServiceUtil.fetchWSRPConsumer(wsrpConsumerId);

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle((wsrpConsumer == null) ? LanguageUtil.get(request, "new-consumer") : wsrpConsumer.getName());
%>

<portlet:actionURL name="updateWSRPConsumer" var="updateWSRPConsumerURL" />

<aui:form action="<%= updateWSRPConsumerURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<aui:input name="mvcPath" type="hidden" value="/admin/edit_consumer.jsp" />
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="wsrpConsumerId" type="hidden" value="<%= wsrpConsumerId %>" />

	<liferay-ui:error exception="<%= WSRPConsumerNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= WSRPConsumerWSDLException.class %>" message="url-does-not-point-to-a-valid-wsrp-producer" />

	<aui:model-context bean="<%= wsrpConsumer %>" model="<%= WSRPConsumer.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input autoFocus="<%= true %>" name="name" />

			<aui:input name="url" type="textarea" />

			<aui:input name="forwardCookies" />

			<aui:input name="forwardHeaders" />

			<aui:input helpMessage="markup-character-sets-help" name="markupCharacterSets" />
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<%
if (wsrpConsumer != null) {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "edit"), currentURL);
}
else {
	PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "add-consumer"), currentURL);
}
%>