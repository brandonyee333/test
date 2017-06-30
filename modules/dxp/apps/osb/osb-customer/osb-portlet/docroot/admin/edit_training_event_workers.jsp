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
String tabs2 = ParamUtil.getString(request, "tabs2", "current");

int cur = ParamUtil.getInteger(request, "cur");

String redirect = ParamUtil.getString(request, "redirect");

long trainingEventId = ParamUtil.getLong(request, "trainingEventId");

TrainingEvent trainingEvent = TrainingEventLocalServiceUtil.getTrainingEvent(trainingEventId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_training_event_workers.jsp");
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("trainingEventId", String.valueOf(trainingEventId));
%>

<%@ include file="/admin/training_event_details.jspf" %>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
	<input name="<portlet:namespace />assignmentsRedirect" type="hidden" value="" />
	<input name="<portlet:namespace />trainingEventId" type="hidden" value="<%= trainingEventId %>" />

	<liferay-ui:tabs
		backURL="<%= redirect %>"
		names="users"
	/>

	<input name="<portlet:namespace />addUserIds" type="hidden" value="" />
	<input name="<portlet:namespace />removeUserIds" type="hidden" value="" />
	<input name="<portlet:namespace />userProfileIdsJSON" type="hidden" value="" />

	<liferay-ui:tabs
		names="current,available"
		param="tabs2"
		url="<%= portletURL.toString() %>"
	/>

	<%
	UserTrainingWorkerChecker rowChecker = new UserTrainingWorkerChecker(renderResponse, trainingEvent);

	LinkedHashMap userParams = new LinkedHashMap();

	if (tabs2.equals("current")) {
		userParams.put("usersTrainingWorkers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.service.persistence.UserFinder.joinByTrainingWorker"), new Long[] {new Long(PortalUtil.getClassNameId(TrainingEvent.class)), new Long(trainingEvent.getTrainingEventId())}));
	}
	%>

	<liferay-ui:user-search
		portletURL="<%= portletURL %>"
		rowChecker="<%= rowChecker %>"
		userParams="<%= userParams %>"
	>

		<%
		SearchContainer userSearchContainer = (SearchContainer)request.getAttribute(WebKeys.SEARCH_CONTAINER);
		%>

		<liferay-ui:search-container
			headerNames="name,screen-name,email-address,registered-profile"
			rowChecker="<%= rowChecker %>"
			searchContainer="<%= userSearchContainer %>"
		>
			<liferay-ui:search-container-results
				results="<%= userSearchContainer.getResults() %>"
				total="<%= userSearchContainer.getTotal() %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.model.User"
				escapedModel="<%= true %>"
				keyProperty="userId"
				modelVar="curUser"
			>
				<liferay-ui:search-container-column-text
					name="name"
					property="fullName"
				/>

				<liferay-ui:search-container-column-text
					name="screen-name"
					property="screenName"
				/>

				<liferay-ui:search-container-column-text
					name="email-address"
					property="emailAddress"
				/>

				<liferay-ui:search-container-column-text
					name="registered-profile"
				>
					<div id="<portlet:namespace />userProfile_<%= curUser.getUserId() %>">

						<%
						TrainingWorker trainingWorker = TrainingWorkerLocalServiceUtil.fetchTrainingWorker(curUser.getUserId(), PortalUtil.getClassNameId(TrainingEvent.class), trainingEventId);

						if (trainingWorker != null) {
							UserProfileHistory userProfileHistory = UserProfileHistoryLocalServiceUtil.fetchUserProfileHistory(trainingWorker.getUserProfileHistoryId());

							if (userProfileHistory != null) {
						%>

							<%= AdminUtil.getUserProfileDisplayHTML(userProfileHistory) %>

						<%
							}
						}
						%>

					</div>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/admin/training_event_worker_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<div class="separator"><!-- --></div>

			<input onClick="<portlet:namespace />updateTrainingWorkers('<%= portletURL.toString() %>&<portlet:namespace />cur=<%= cur %>');" type="button" value="<liferay-ui:message key="update-associations" />" />

			<br /><br />

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</liferay-ui:user-search>
</aui:form>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />selectUserProfile',
		function(userId, userProfileId, userProfile) {
			var userProfileIdsJSON = document.<portlet:namespace />fm.<portlet:namespace />userProfileIdsJSON.value;

			if (userProfileIdsJSON) {
				userProfileIdsJSON = JSON.parse(userProfileIdsJSON);
			}
			else {
				userProfileIdsJSON = {};
			}

			userProfileIdsJSON[userId] = userProfileId;

			document.<portlet:namespace />fm.<portlet:namespace />userProfileIdsJSON.value = JSON.stringify(userProfileIdsJSON);

			var userProfileEl = document.getElementById('<portlet:namespace />userProfile_' + userId);

			userProfileEl.innerHTML = userProfile;

			var userCheckBoxes = document.getElementsByName('<portlet:namespace />rowIds');

			for (index = 0; index < userCheckBoxes.length; ++index) {
				if (userCheckBoxes[index].value == userId) {
					userCheckBoxes[index].checked = 'selected';
				}
			}
		},
		['liferay-search-container']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />updateTrainingWorkers',
		function(assignmentsRedirect) {
			document.<portlet:namespace />fm.<portlet:namespace />assignmentsRedirect.value = assignmentsRedirect;
			document.<portlet:namespace />fm.<portlet:namespace />addUserIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			document.<portlet:namespace />fm.<portlet:namespace />removeUserIds.value = Liferay.Util.listUncheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="updateTrainingWorkers" />");
		},
		['liferay-util-list-fields']
	);
</aui:script>