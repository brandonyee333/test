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
String backURL = ParamUtil.getString(request, "backURL", redirect);

long trainingEventId = ParamUtil.getLong(request, "trainingEventId");

TrainingEvent trainingEvent = TrainingEventLocalServiceUtil.getTrainingEvent(trainingEventId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_training_event_customers.jsp");
portletURL.setParameter("tabs2", tabs2);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("backURL", backURL);
portletURL.setParameter("trainingEventId", String.valueOf(trainingEventId));
%>

<%@ include file="/admin/training_event_details.jspf" %>

<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
	<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escapeAttribute(backURL) %>" />
	<input name="<portlet:namespace />assignmentsRedirect" type="hidden" value="" />
	<input name="<portlet:namespace />trainingEventId" type="hidden" value="<%= trainingEventId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
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

	<liferay-ui:error exception="<%= TrainingCustomerCertificateException.class %>" message="you-cannot-remove-students-that-have-generated-certificate-keys" />
	<liferay-ui:error exception="<%= TrainingCustomerStatusException.class %>" message="you-cannot-remove-students-that-have-sent-out-surveys" />

	<%
	UserTrainingCustomerChecker rowChecker = new UserTrainingCustomerChecker(renderResponse, trainingEvent);
	%>

	<liferay-ui:search-container
		headerNames="first-name,last-name,email-address,type,registered-profile"
		rowChecker="<%= rowChecker %>"
		searchContainer="<%= new OSBUserSearch(renderRequest, portletURL) %>"
	>

		<%
		OSBUserDisplayTerms displayTerms = (OSBUserDisplayTerms)searchContainer.getDisplayTerms();

		LinkedHashMap userParams = new LinkedHashMap();

		if (tabs2.equals("current")) {
			userParams.put("usersTrainingCustomers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.service.persistence.UserFinder.joinByTrainingCustomer"), new Long[] {new Long(PortalUtil.getClassNameId(TrainingEvent.class)), new Long(trainingEvent.getTrainingEventId()), new Long(WorkflowConstants.STATUS_APPROVED), new Long(WorkflowConstants.STATUS_INCOMPLETE_TRAINING_REGISTRATION)}));
		}
		else {
			userParams.put("usersTrainingCustomers", new CustomSQLParam(CustomSQLUtil.get("com.liferay.portal.service.persistence.UserFinder.filterByStatusId"), new Long[] {new Long(WorkflowConstants.STATUS_APPROVED), new Long(WorkflowConstants.STATUS_INCOMPLETE_TRAINING_REGISTRATION)}));
		}
		%>

		<%@ include file="/admin/training_customer_search.jspf" %>

		<liferay-ui:search-container-results>
			<%@ include file="/admin/training_event_customer_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.portal.model.User"
			escapedModel="<%= true %>"
			keyProperty="userId"
			modelVar="curUser"
		>

			<%
			TrainingCustomer trainingCustomer = TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(curUser.getUserId(), PortalUtil.getClassNameId(TrainingEvent.class), TrainingEventConstants.DEFAULT_TRAINING_EVENT_ID);
			%>

			<liferay-ui:search-container-column-text
				name="first-name"
				property="firstName"
			/>

			<liferay-ui:search-container-column-text
				name="last-name"
				property="lastName"
			/>

			<liferay-ui:search-container-column-text
				name="email-address"
				value="<%= (trainingCustomer != null) ? trainingCustomer.getEmailAddress() : curUser.getEmailAddress() %>"
			/>

			<liferay-ui:search-container-column-text
				name="type"
				value="<%= (trainingCustomer != null) ? LanguageUtil.get(pageContext, trainingCustomer.getType()) : LanguageUtil.get(pageContext, TrainingCustomerConstants.TYPE_REGULAR_USER) %>"
			/>

			<liferay-ui:search-container-column-text
				name="registered-profile"
			>
				<div id="<portlet:namespace />userProfile_<%= curUser.getUserId() %>">

					<%
					trainingCustomer = TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(curUser.getUserId(), PortalUtil.getClassNameId(TrainingEvent.class), trainingEventId);

					if (trainingCustomer != null) {
						UserProfileHistory userProfileHistory = UserProfileHistoryLocalServiceUtil.fetchUserProfileHistory(trainingCustomer.getUserProfileHistoryId());

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
				path="/admin/training_event_customer_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<div class="separator"><!-- --></div>

		<input onClick="<portlet:namespace />updateTrainingCustomers('<%= portletURL.toString() %>&<portlet:namespace />cur=<%= cur %>');" type="button" value="<liferay-ui:message key="update-associations" />" />

		<input onClick="location.href = '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/edit_training_customer.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /><portlet:param name="trainingEventId" value="<%= String.valueOf(trainingEventId) %>" /></portlet:renderURL>';" type="button" value="<liferay-ui:message key="add-student" />" />

		<br /><br />

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
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
		'<portlet:namespace />updateTrainingCustomers',
		function(assignmentsRedirect) {
			document.<portlet:namespace />fm.<portlet:namespace />assignmentsRedirect.value = assignmentsRedirect;
			document.<portlet:namespace />fm.<portlet:namespace />addUserIds.value = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			document.<portlet:namespace />fm.<portlet:namespace />removeUserIds.value = Liferay.Util.listUncheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");
			submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="updateTrainingCustomers"><portlet:param name="mvcPath" value="/admin/edit_training_event_customers.jsp" /></portlet:actionURL>");
		},
		['liferay-util-list-fields']
	);
</aui:script>