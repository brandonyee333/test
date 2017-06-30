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
String tabs1 = ParamUtil.getString(request, "tabs1", "current");

String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

String linkStep = ParamUtil.getString(request, "linkStep", "assignment");

long trainingCustomerId = ParamUtil.getLong(request, "trainingCustomerId");

TrainingCustomer trainingCustomer = TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(trainingCustomerId);

long userId = ParamUtil.getLong(request, "userId");

if (trainingCustomer != null) {
	userId = trainingCustomer.getUserId();
}

User curUser = UserLocalServiceUtil.getUser(userId);

TrainingLinkedUser trainingLinkedUser = TrainingLinkedUserLocalServiceUtil.fetchUserTrainingLinkedUser(userId);

long oldPrimaryUserId = userId;

if (trainingLinkedUser != null) {
	oldPrimaryUserId = trainingLinkedUser.getPrimaryUserId();
}

String firstName = StringPool.BLANK;
String lastName = StringPool.BLANK;
String emailAddress = StringPool.BLANK;
String type = "regular-user";

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_training_linked_user.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("backURL", backURL);

if (trainingCustomer != null) {
	portletURL.setParameter("trainingCustomerId", String.valueOf(trainingCustomerId));

	firstName = trainingCustomer.getFirstName();
	lastName = trainingCustomer.getLastName();
	emailAddress = trainingCustomer.getEmailAddress();
	type = trainingCustomer.getType();
}
else {
	portletURL.setParameter("userId", String.valueOf(userId));

	firstName = curUser.getFirstName();
	lastName = curUser.getLastName();
	emailAddress = curUser.getEmailAddress();
}
%>

<div class="admin edit-training-linked-user">
	<div class="field-container">
		<span class="field">
			<span class="field-content">
				<label class="field-label">
					<liferay-ui:message key="first-name" />
				</label>

				<span class="field-element">
					<%= HtmlUtil.escape(firstName) %>
				</span>
			</span>
		</span>

		<span class="field">
			<span class="field-content">
				<label class="field-label">
					<liferay-ui:message key="last-name" />
				</label>

				<span class="field-element">
					<%= HtmlUtil.escape(lastName) %>
				</span>
			</span>
		</span>

		<span class="field">
			<span class="field-content">
				<label class="field-label">
					<liferay-ui:message key="email-address" />
				</label>

				<span class="field-element">
					<%= emailAddress %>
				</span>
			</span>
		</span>

		<span class="field">
			<span class="field-content">
				<label class="field-label">
					<liferay-ui:message key="type" />
				</label>

				<span class="field-element">
					<liferay-ui:message key="<%= type %>" />
				</span>
			</span>
		</span>
	</div>

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="current,available"
		param="tabs1"
		url="<%= portletURL.toString() %>"
	/>

	<aui:form action="<%= portletURL.toString() %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value='<%= tabs1.equals("available") ? Constants.ADD : Constants.UPDATE %>' />
		<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="linkStep" type="hidden" value="<%= linkStep %>" />
		<aui:input name="trainingCustomerId" type="hidden" value="<%= trainingCustomerId %>" />
		<aui:input name="userId" type="hidden" value="<%= userId %>" />
		<aui:input name="oldPrimaryUserId" type="hidden" value="<%= oldPrimaryUserId %>" />

		<%
		String addUserIds = ParamUtil.getString(request, "addUserIds");
		String removeUserIds = ParamUtil.getString(request, "removeUserIds");
		%>

		<aui:input name="addUserIds" type="hidden" value="<%= addUserIds %>" />
		<aui:input name="removeUserIds" type="hidden" value="<%= removeUserIds %>" />

		<%
		UserTrainingLinkedUserChecker rowChecker = new UserTrainingLinkedUserChecker(renderResponse, curUser);
		%>

		<liferay-ui:search-container
			rowChecker='<%= (tabs1.equals("available") && linkStep.equals("selectPrimary")) ? null : rowChecker %>'
			searchContainer="<%= new TrainingLinkedUserSearch(renderRequest, portletURL) %>"
		>
			<c:choose>
				<c:when test='<%= tabs1.equals("available") && linkStep.equals("assignment") %>'>

					<%
					TrainingLinkedUserDisplayTerms displayTerms = (TrainingLinkedUserDisplayTerms)searchContainer.getDisplayTerms();
					%>

					<%@ include file="/admin/training_linked_user_search.jspf" %>

					<liferay-ui:search-container-results>

						<%
						LinkedHashMap userParams = new LinkedHashMap();

						results = UserLocalServiceUtil.search(company.getCompanyId(), displayTerms.getFirstName(), null, displayTerms.getLastName(), null, displayTerms.getEmailAddress(), WorkflowConstants.STATUS_ANY, userParams, displayTerms.isAndOperator(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator());
						total = UserLocalServiceUtil.searchCount(company.getCompanyId(), displayTerms.getFirstName(), null, displayTerms.getLastName(), null, displayTerms.getEmailAddress(), WorkflowConstants.STATUS_ANY, userParams, displayTerms.isAndOperator());

						pageContext.setAttribute("results", results);
						pageContext.setAttribute("total", total);
						%>

					</liferay-ui:search-container-results>
				</c:when>
				<c:when test='<%= tabs1.equals("available") && linkStep.equals("selectPrimary") %>'>
					<liferay-ui:search-container-results>

						<%
						long[] userIds = StringUtil.split(ParamUtil.getString(request, "addUserIds"), 0L);

						List<User> users = new ArrayList<User>(userIds.length);

						for (long user2Id : userIds) {
							User user2 = UserLocalServiceUtil.getUser(user2Id);

							users.add(user2);
						}

						pageContext.setAttribute("results", users);
						pageContext.setAttribute("total", users.size());
						%>

					</liferay-ui:search-container-results>
				</c:when>
				<c:otherwise>
					<liferay-ui:search-container-results>

						<%
						List<User> users = AdminUtil.getTrainingLinkedUserUsers(userId);

						pageContext.setAttribute("results", users);
						pageContext.setAttribute("total", users.size());
						%>

					</liferay-ui:search-container-results>
				</c:otherwise>
			</c:choose>

			<liferay-ui:search-container-row
				className="com.liferay.portal.model.User"
				escapedModel="<%= true %>"
				keyProperty="userId"
				modelVar="linkedUser"
			>
				<c:if test='<%= tabs1.equals("current") || (tabs1.equals("available") && linkStep.equals("selectPrimary")) %>'>
					<liferay-ui:search-container-column-text
						align="center"
						name="primary"
					>
						<aui:input checked="<%= oldPrimaryUserId == linkedUser.getUserId() %>" label="" name="primaryUserId" type="radio" value="<%= linkedUser.getUserId() %>" />
					</liferay-ui:search-container-column-text>
				</c:if>

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
					property="emailAddress"
				/>

				<liferay-ui:search-container-column-text
					name="legal-entity-name"
				>
					<liferay-ui:custom-attribute
						className="com.liferay.portal.model.User"
						classPK="<%= linkedUser.getUserId() %>"
						editable="<%= false %>"
						label="<%= false %>"
						name="osbCompany"
					/>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="type"
					value="<%= LanguageUtil.get(pageContext, AdminUtil.getTrainingCustomerType(linkedUser)) %>"
				/>
			</liferay-ui:search-container-row>

			<%
			String taglibOnClick = renderResponse.getNamespace() + "updateTrainingLinkedUsers();";

			String updateLabel = "update-associations";

			if (tabs1.equals("available") && linkStep.equals("selectPrimary")) {
				updateLabel = "select-primary-user";
			}
			%>

			<aui:button cssClass="update-btn" onClick="<%= taglibOnClick %>" value="<%= updateLabel %>" />

			<liferay-ui:search-iterator paginate='<%= tabs1.equals("available") %>' />
		</liferay-ui:search-container>
	</aui:form>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />updateTrainingLinkedUsers',
		function() {
			var linkStep = document.<portlet:namespace />fm.<portlet:namespace />linkStep.value;

			if (linkStep == 'assignment') {
				var addUserIdsArray = [];

				var addUserIds = Liferay.Util.listCheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");

				if (addUserIds != '') {
					addUserIdsArray = addUserIds.split(',');
				}

				var oldPrimaryUserId = document.<portlet:namespace />fm.<portlet:namespace />oldPrimaryUserId.value;

				if (addUserIdsArray.indexOf(oldPrimaryUserId) == -1) {
					addUserIdsArray.push(oldPrimaryUserId);

					addUserIds = addUserIdsArray.join();
				}

				document.<portlet:namespace />fm.<portlet:namespace />addUserIds.value = addUserIds;

				var removeUserIdsArray = [];

				var removeUserIds = Liferay.Util.listUncheckedExcept(document.<portlet:namespace />fm, "<portlet:namespace />allRowIds");

				if (removeUserIds != '') {
					removeUserIdsArray = removeUserIds.split(',');
				}

				document.<portlet:namespace />fm.<portlet:namespace />removeUserIds.value = removeUserIds;
			}

			var tabs1 = document.<portlet:namespace />fm.<portlet:namespace />tabs1.value;

			if ((tabs1 == 'available') && (linkStep == 'assignment')) {
				document.<portlet:namespace />fm.<portlet:namespace />linkStep.value = 'selectPrimary';

				submitForm(document.<portlet:namespace />fm);
			}

			document.<portlet:namespace />fm.<portlet:namespace />linkStep.value = 'assignment';

			submitForm(document.<portlet:namespace />fm, "<portlet:actionURL name="updateTrainingLinkedUser" />");
		},
		['liferay-util-list-fields']
	);
</aui:script>