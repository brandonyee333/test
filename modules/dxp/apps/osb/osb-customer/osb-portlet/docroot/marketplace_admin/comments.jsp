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

<%@ include file="/marketplace_admin/init.jsp" %>

<%
String mvcPath = ParamUtil.getString(request, "mvcPath");

String className = ParamUtil.getString(request, "className");
long classPK = ParamUtil.getLong(request, "classPK");

PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");
%>

<liferay-portlet:actionURL name="invokeTaglibDiscussion" varImpl="discussionURL">
	<portlet:param name="mvcPath" value="<%= mvcPath %>" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="className" value="<%= className %>" />
	<portlet:param name="classPK" value="<%= String.valueOf(classPK) %>" />
</liferay-portlet:actionURL>

<div class="comments">
	<div class="lfr-portlet-toolbar">
		<span class="lfr-toolbar-button add-button current">
			<a href="javascript:;"><liferay-ui:message key="post" /></a>
		</span>

		<c:choose>
			<c:when test="<%= MarketplaceAdminDiscussionUtil.isSubscribed(user.getUserId(), className, classPK) %>">

				<%
				discussionURL.setParameter(Constants.CMD, Constants.UNSUBSCRIBE_FROM_COMMENTS);
				%>

				<span class="lfr-toolbar-button unsubscribe-button">
					<a href="<%= discussionURL %>"><liferay-ui:message key="unsubscribe" /></a>
				</span>
			</c:when>
			<c:otherwise>

				<%
				discussionURL.setParameter(Constants.CMD, Constants.SUBSCRIBE_TO_COMMENTS);
				%>

				<span class="lfr-toolbar-button subscribe-button">
					<a href="<%= discussionURL %>"><liferay-ui:message key="subscribe" /></a>
				</span>
			</c:otherwise>
		</c:choose>
	</div>

	<%
	discussionURL.setParameter(Constants.CMD, Constants.ADD);
	%>

	<aui:form action="<%= discussionURL.toString() %>" cssClass="discussion-form aui-helper-hidden" method="post" name="fm2">
		<aui:input label="comment" name="body" type="textarea" />

		<aui:button type="submit" value="post" />

		<hr />
	</aui:form>

	<div class="discussion">
		<liferay-ui:search-container
			iteratorURL="<%= portletURL %>"
		>

			<%
			List<MBMessage> mbMessages = MarketplaceAdminDiscussionUtil.getDiscussionMBMessages(className, classPK, searchContainer.getStart(), searchContainer.getEnd());
			int count = MarketplaceAdminDiscussionUtil.getDiscussionMBMessagesCount(className, classPK);
			%>

			<liferay-ui:search-container-results
				results="<%= mbMessages %>"
				total="<%= count %>"
			/>

			<%
			for (MBMessage mbMessage : mbMessages) {
			%>

				<c:choose>
					<c:when test="<%= mbMessage.getUserId() == OSBConstants.USER_DEFAULT_USER_ID %>">

						<%
						JSONObject jsonObject = JSONFactoryUtil.createJSONObject(mbMessage.getBody());

						String userName = HtmlUtil.escape(PortalUtil.getUserName(jsonObject.getLong("userId"), jsonObject.getString("userName")));

						if (Validator.isNull(userName)) {
							userName = "System";
						}
						%>

						<div class="message event">
							<div class="meta-data">
								<span class="user">
									<%= userName %>
								</span>

								<span class="time">
									<%= longDateFormatDateTime.format(mbMessage.getCreateDate()) %>
								</span>
							</div>

							<div class="body">
								<div>
									<liferay-ui:message key="status" />: <liferay-ui:message key='<%= WorkflowConstants.toLabel(jsonObject.getInt("status")) %>' />
								</div>

								<div>
									<%= MarketplaceMarkupUtil.getHTML(jsonObject.getString("statusMessage")) %>
								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="message">
							<div class="meta-data">
								<span class="user">
									<%= HtmlUtil.escape(PortalUtil.getUserName(mbMessage)) %>
								</span>

								<span class="time">
									<%= longDateFormatDateTime.format(mbMessage.getCreateDate()) %>
								</span>
							</div>

							<div class="body">
								<%= MarketplaceMarkupUtil.getHTML(mbMessage.getBody()) %>
							</div>
						</div>
					</c:otherwise>
				</c:choose>

			<%
			}
			%>

			<liferay-ui:search-paginator searchContainer="<%= searchContainer %>" />
		</liferay-ui:search-container>
	</div>
</div>

<aui:script use="aui-base">
	var form = A.one('.osb-portlet-marketplace-admin .discussion-form');

	A.one('.osb-portlet-marketplace-admin .lfr-toolbar-button.add-button').on(
		'click',
		function(event) {
			form.show();
		}
	)
</aui:script>