<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="users" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<c:if test="${not viewOnly}">
	<portlet:renderURL var="viewTestrayUsersURL">
		<portlet:param name="controller" value="users" />
		<portlet:param name="action" value="select" />
		<portlet:param name="submitAction" value="${submitAction}" />
		<portlet:param name="submitController" value="${submitController}" />
		<portlet:param name="submitIdName" value="${submitIdName}" />
		<portlet:param name="submitIdValue" value="${submitIdValue}" />
	</portlet:renderURL>
</c:if>

<div class="hide" id="${htmlNamespace}userSearchAlertContainer">
	<div class="alert alert-error">
		<liferay-ui:message key="your-request-failed-to-complete" />
	</div>
</div>

<aui:form action="${viewTestrayUsersURL}" method="get" name="fm" onSubmit="event.preventDefault(); ${htmlNamespace}searchUsers();">
	<c:if test="${not viewOnly}">
		<div class="${popup ? "testray-modal-toolbar" : StringPool.BLANK}">
			<aui:fieldset>
				<aui:input inlineField="${true}" label="" name="keywords" size="30" title="search-users" type="text" />

				<aui:button type="submit" value="search" />
			</aui:fieldset>
		</div>
	</c:if>

	<div class="${popup ? "spacing-toolbar testray-modal-content" : StringPool.BLANK}">
		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-available-users"
			id="usersSearchContainer"
			iteratorURL="${portletURL}"
			total="${usersCount}"
		>
			<liferay-ui:search-container-results
				results="${users}"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.model.User"
				escapedModel="${true}"
				keyProperty="userId"
				modelVar="curUser"
			>
				<c:if test="${not viewOnly}">
					<portlet:actionURL var="setUserURL">
						<portlet:param name="controller" value="${submitController}" />
						<portlet:param name="action" value="${submitAction}" />
						<portlet:param name="redirect" value="${portletURL}" />
						<portlet:param name="assignedUserId" value="${curUser.userId}" />
						<portlet:param name="${submitIdName}" value="${submitIdValue}" />
					</portlet:actionURL>
				</c:if>

				<liferay-ui:search-container-column-text
					cssClass="user"
					href="${setUserURL}"
					name="name"
					property="fullName"
				/>

				<liferay-ui:search-container-column-text
					cssClass="user"
					href="${setUserURL}"
					name="screen-name"
					property="screenName"
				/>

				<liferay-ui:search-container-column-text
					cssClass="user"
					href="${setUserURL}"
					name="email-address"
					property="emailAddress"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</div>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	function ${htmlNamespace}searchUsers() {
		var keywords = document.${htmlNamespace}fm.${htmlNamespace}keywords.value;

		window.location.href = '${viewTestrayUsersURL}&${htmlNamespace}keywords=' + escape(keywords);
	}
</aui:script>

<c:if test="${not viewOnly}">
	<aui:script use="aui-base,aui-io-request,testray-base">
		function ${htmlNamespace}setUser(url) {
			A.io.request(
				url,
				{
					on: {
						failure: function(event, id, obj) {
							var alertContainer = A.one('#${htmlNamespace}userSearchAlertContainer');

							if (alertContainer) {
								alertContainer.show();
							}
						},
						success: function(event, id, obj) {
							var opener = Liferay.Util.getOpener();

							if (opener) {
								if (opener.${htmlNamespace}refreshAssignedUserSection) {
									opener.${htmlNamespace}refreshAssignedUserSection();

									Liferay.Testray.closeWindow();
								}
								else {
									opener.location.reload();
								}
							}
						}
					}
				}
			);
		}

		var userSearchContainerNode = A.one('#${htmlNamespace}usersSearchContainerSearchContainer');

		if (userSearchContainerNode) {
			userSearchContainerNode.delegate(
				'click',
				function(event) {
					event.preventDefault();

					var userLink = event.currentTarget.attr('href');

					${htmlNamespace}setUser(userLink);
				},
				'.user a'
			);
		}
	</aui:script>
</c:if>