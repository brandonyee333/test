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
String tabs1 = ParamUtil.getString(request, "tabs1", "admin");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/view.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setWindowState(WindowState.MAXIMIZED);
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<portlet:renderURL var="adminURL">
		<portlet:param name="mvcPath" value="/view.jsp" />
		<portlet:param name="tabs1" value="admin" />
	</portlet:renderURL>

	<aui:nav cssClass="navbar-nav">
		<aui:nav-item
			href="<%= adminURL %>"
			label="admin"
			selected='<%= tabs1.equals("admin") %>'
		/>
	</aui:nav>

	<c:if test="<%= RabbitMQConnectorConfigurationValues.RABBITMQ_DEBUG_MODE_ENABLED %>">
		<portlet:renderURL var="debugURL">
			<portlet:param name="mvcPath" value="/view.jsp" />
			<portlet:param name="tabs1" value="debug" />
		</portlet:renderURL>

		<aui:nav cssClass="navbar-nav">
			<aui:nav-item
				href="<%= debugURL %>"
				label="debug"
				selected='<%= tabs1.equals("debug") %>'
			/>
		</aui:nav>
	</c:if>
</aui:nav-bar>

<div class="container-fluid-1280">
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

			<liferay-frontend:management-bar>
				<liferay-frontend:management-bar-buttons>
					<liferay-frontend:management-bar-filters>
						<liferay-frontend:management-bar-navigation
							navigationKeys='<%= new String[] {"all"} %>'
							portletURL="<%= renderResponse.createRenderURL() %>"
						/>
					</liferay-frontend:management-bar-filters>

					<liferay-frontend:management-bar-display-buttons
						displayViews='<%= new String[] {"list"} %>'
						portletURL="<%= renderResponse.createRenderURL() %>"
						selectedDisplayStyle="list"
					/>
				</liferay-frontend:management-bar-buttons>
			</liferay-frontend:management-bar>

			<%
			Map<String, RabbitMQProcessor> rabbitMQProcessors = RabbitMQProcessorRegistryUtil.getRabbitMQProcessors();
			%>

			<liferay-ui:search-container
				emptyResultsMessage="there-are-no-processors"
				emptyResultsMessageCssClass="taglib-empty-result-message-header"
				headerNames="class,queue,channel,active"
				total="<%= rabbitMQProcessors.size() %>"
			>
				<liferay-ui:search-container-results
					results="<%= ListUtil.fromCollection(rabbitMQProcessors.entrySet()) %>"
				/>

				<liferay-ui:search-container-row
					className="java.util.Map.Entry"
					modelVar="processorEntry"
				>

					<%
					RabbitMQProcessor rabbitMQProcessor = (RabbitMQProcessor)processorEntry.getValue();

					Channel channel = null;

					Consumer consumer = ConsumerManagerUtil.getConsumer(rabbitMQProcessor);

					if (consumer != null) {
						channel = consumer.getChannel();
					}

					Class<?> rabbitMQProcessorClass = rabbitMQProcessor.getClass();
					%>

					<liferay-ui:search-container-column-text
						name="class"
						value="<%= rabbitMQProcessorClass.getName() %>"
					/>

					<liferay-ui:search-container-column-text
						name="queue"
						value="<%= rabbitMQProcessor.getQueue() %>"
					/>

					<liferay-ui:search-container-column-text
						name="channel"
					>
						<c:if test="<%= (channel != null) && channel.isOpen() %>">
							<%= channel.getChannelNumber() %>
						</c:if>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="active"
						value="<%= String.valueOf((channel != null) && channel.isOpen()) %>"
					/>

					<liferay-ui:search-container-column-text>
						<liferay-ui:icon-menu>
							<c:choose>
								<c:when test="<%= (channel != null) && channel.isOpen() %>">
									<portlet:actionURL name="deactivateMessageProcessor" var="deactivateMessageProcessorURL">
										<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
										<portlet:param name="rabbitMQProcessorKey" value="<%= (String)processorEntry.getKey() %>" />
									</portlet:actionURL>

									<liferay-ui:icon-deactivate
										url="<%= deactivateMessageProcessorURL %>"
									/>
								</c:when>
								<c:otherwise>
									<portlet:actionURL name="activateMessageProcessor" var="activateMessageProcessorURL">
										<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
										<portlet:param name="rabbitMQProcessorKey" value="<%= (String)processorEntry.getKey() %>" />
									</portlet:actionURL>

									<liferay-ui:icon
										image="activate"
										url="<%= activateMessageProcessorURL %>"
									/>
								</c:otherwise>
							</c:choose>
						</liferay-ui:icon-menu>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator markupView="lexicon" paginate="<%= false %>" />
			</liferay-ui:search-container>
		</c:otherwise>
	</c:choose>
</div>