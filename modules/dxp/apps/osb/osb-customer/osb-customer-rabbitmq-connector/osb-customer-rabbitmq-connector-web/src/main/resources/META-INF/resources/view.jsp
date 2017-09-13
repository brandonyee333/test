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

<%@ include file="/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1");

String tabsNames = "admin";

if (PortletPropsValues.RABBITMQ_DEBUG_MODE_ENABLED) {
	tabsNames += ",debug";
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/view.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setWindowState(WindowState.MAXIMIZED);
%>

<liferay-ui:tabs
	names="<%= tabsNames %>"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("debug") %>'>
		<liferay-portlet:actionURL name="consumeMessage" var="actionURL" />

		<aui:form action="<%= actionURL %>" method="post" name="fm">
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

			<aui:fieldset>
				<aui:button-row>
					<aui:button type="submit" value="consume-message" />
				</aui:button-row>
			</aui:fieldset>
		</aui:form>
	</c:when>
	<c:otherwise>
		<liferay-portlet:actionURL name="restart" var="actionURL" />

		<aui:form action="<%= actionURL %>" method="post" name="fm">
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

			<aui:fieldset>
				<liferay-ui:message key="restarting-will-close-all-channels-reopen-a-new-connection-and-then-reregister-all-consumers" />

				<aui:button-row>
					<aui:button type="submit" value="restart" />
				</aui:button-row>
			</aui:fieldset>
		</aui:form>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-consumers"
			headerNames="class,queue,channel,active"
		>

			<%
			Map<String, ConsumerBag> consumers = ConsumerManagerLocalServiceUtil.getConsumersMap();
			%>

			<liferay-ui:search-container-results
				results="<%= ListUtil.fromCollection(consumers.entrySet()) %>"
				total="<%= consumers.size() %>"
			/>

			<liferay-ui:search-container-row
				className="java.util.Map.Entry"
				modelVar="consumerEntry"
			>

				<%
				String rabbitMQConsumerKey = (String)consumerEntry.getKey();
				ConsumerBag consumerBag = (ConsumerBag)consumerEntry.getValue();

				Object rabbitMQConsumer = consumerBag.getRabbitMQConsumer();
				%>

				<liferay-ui:search-container-column-text
					name="class"
					value="<%= rabbitMQConsumer.getClass().getName() %>"
				/>

				<liferay-ui:search-container-column-text
					name="queue"
					value="<%= consumerBag.getQueue() %>"
				/>

				<liferay-ui:search-container-column-text
					name="channel"
				>

					<%
					Channel channel = consumerBag.getChannel();
					%>

					<c:if test="<%= channel != null %>">
						<%= channel.getChannelNumber() %>
					</c:if>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="active"
					value="<%= String.valueOf(consumerBag.isActive()) %>"
				/>

				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu>
						<c:choose>
							<c:when test="<%= consumerBag.isActive() %>">
								<portlet:actionURL name="deactivateConsumer" var="deactivateURL">
									<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
									<portlet:param name="rabbitMQConsumerKey" value="<%= rabbitMQConsumerKey %>" />
								</portlet:actionURL>

								<liferay-ui:icon-deactivate
									url="<%= deactivateURL %>"
								/>
							</c:when>
							<c:otherwise>
								<portlet:actionURL name="activateConsumer" var="activateURL">
									<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
									<portlet:param name="rabbitMQConsumerKey" value="<%= rabbitMQConsumerKey %>" />
								</portlet:actionURL>

								<liferay-ui:icon
									image="activate"
									url="<%= activateURL %>"
								/>
							</c:otherwise>
						</c:choose>
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="<%= false %>" />
		</liferay-ui:search-container>
	</c:otherwise>
</c:choose>