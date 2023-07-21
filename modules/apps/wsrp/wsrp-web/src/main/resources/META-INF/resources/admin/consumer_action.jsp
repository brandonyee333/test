<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

WSRPConsumer wsrpConsumer = (WSRPConsumer)row.getObject();

ServiceDescription serviceDescription = null;

try {
	WSRPConsumerManager wsrpConsumerManager = WSRPConsumerManagerFactoryUtil.getWSRPConsumerManager(wsrpConsumer);

	serviceDescription = wsrpConsumerManager.getServiceDescription();
}
catch (Exception e) {
}
%>

<c:choose>
	<c:when test="<%= serviceDescription == null %>">
		<liferay-ui:icon-menu
			direction="left-side"
			icon="<%= StringPool.BLANK %>"
			markupView="lexicon"
			message="<%= StringPool.BLANK %>"
			showWhenSingleIcon="<%= true %>"
		>
			<portlet:actionURL name="restartConsumer" var="restartConsumerURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				message="restart-consumer"
				url="<%= restartConsumerURL %>"
			/>

			<portlet:actionURL name="deleteWSRPConsumer" var="deleteURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon-delete
				url="<%= deleteURL %>"
			/>
		</liferay-ui:icon-menu>
	</c:when>
	<c:otherwise>
		<liferay-ui:icon-menu
			direction="left-side"
			icon="<%= StringPool.BLANK %>"
			markupView="lexicon"
			message="<%= StringPool.BLANK %>"
			showWhenSingleIcon="<%= true %>"
		>
			<portlet:renderURL var="editURL">
				<portlet:param name="mvcPath" value="/admin/edit_consumer.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
			</portlet:renderURL>

			<liferay-ui:icon
				message="edit"
				url="<%= editURL %>"
			/>

			<c:if test="<%= serviceDescription.isRequiresRegistration() %>">
				<portlet:renderURL var="editRegistrationURL">
					<portlet:param name="mvcPath" value="/admin/edit_consumer_registration.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon
					message="edit-registration"
					url="<%= editRegistrationURL %>"
				/>
			</c:if>

			<c:if test="<%= !serviceDescription.isRequiresRegistration() || (wsrpConsumer.getRegistrationContext() != null) %>">
				<portlet:renderURL var="managePortletsURL">
					<portlet:param name="mvcPath" value="/admin/view_consumer_portlets.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
				</portlet:renderURL>

				<liferay-ui:icon
					message="manage-portlets"
					url="<%= managePortletsURL %>"
				/>
			</c:if>

			<portlet:actionURL name="updateServiceDescription" var="updateServiceDescriptionURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				message="update-service-description"
				url="<%= updateServiceDescriptionURL %>"
			/>

			<portlet:actionURL name="restartConsumer" var="restartConsumerURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				message="restart-consumer"
				url="<%= restartConsumerURL %>"
			/>

			<portlet:actionURL name="deleteWSRPConsumer" var="deleteURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="wsrpConsumerId" value="<%= String.valueOf(wsrpConsumer.getWsrpConsumerId()) %>" />
			</portlet:actionURL>

			<liferay-ui:icon-delete
				url="<%= deleteURL %>"
			/>
		</liferay-ui:icon-menu>
	</c:otherwise>
</c:choose>