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

<%@ include file="/init.jsp" %>

<%
String backURL = ParamUtil.getString(request, "backURL");

long partnerEntryId = ParamUtil.getLong(request, "partnerEntryId");

PartnerEntry partnerEntry = PartnerEntryLocalServiceUtil.getPartnerEntry(partnerEntryId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_partner_entry_workers.jsp");
portletURL.setParameter("backURL", backURL);
portletURL.setParameter("partnerEntryId", String.valueOf(partnerEntryId));

request.setAttribute("edit_partner_entry_workers.jsp-portletURL", portletURL);
%>

<portlet:actionURL name="updatePartnerWorker" var="updatePartnerWorkerURL">
	<portlet:param name="mvcPath" value="/admin/edit_partner_entry_workers.jsp" />
	<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
	<portlet:param name="backURL" value="<%= backURL %>" />
</portlet:actionURL>

<aui:form action="<%= updatePartnerWorkerURL %>" method="post">
	<aui:input name="partnerEntryId" type="hidden" value="<%= partnerEntryId %>" />
	<aui:input name="partnerWorkerId" type="hidden" />

	<liferay-util:include page="/common/exception.jsp" servletContext="<%= application %>" />

	<liferay-ui:error exception="<%= NoSuchUserException.class %>" message="the-user-could-not-be-found" />
	<liferay-ui:error exception="<%= PartnerEntryDossieraAccountKeyException.class %>" message="dossiera-account-key-is-required-to-assign-workers" />

	<liferay-ui:message arguments="<%= partnerEntry.getCode() %>" key="edit-workers-for-partner-x" />

	<br /><br />

	<liferay-ui:error exception="<%= RemoteServiceException.class %>">

		<%
		RemoteServiceException rse = (RemoteServiceException)errorException;
		%>

		<liferay-ui:message key="unable-to-sync-to-web.liferay.com" />

		<br />

		<%= rse.getMessage() %>
	</liferay-ui:error>

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="users"
	/>

	<liferay-ui:search-container>

		<%
		List<PartnerWorker> partnerWorkers = ListUtil.copy(PartnerWorkerLocalServiceUtil.getPartnerWorkers(partnerEntryId));

		partnerWorkers.add(0, new PartnerWorkerImpl());
		%>

		<liferay-ui:search-container-results
			results="<%= partnerWorkers %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.PartnerWorker"
			escapedModel="<%= true %>"
			keyProperty="partnerWorkerId"
			modelVar="partnerWorker"
		>

			<%
			User curUser = UserLocalServiceUtil.fetchUser(partnerWorker.getUserId());

			if ((partnerWorker.getPartnerWorkerId() > 0) && ((curUser == null) || !curUser.isActive())) {
				row.setClassName("inactive");
			}
			%>

			<liferay-ui:search-container-column-text
				name="name"
			>
				<c:choose>
					<c:when test="<%= curUser != null %>">
						<%= HtmlUtil.escape(curUser.getFullName()) %>
					</c:when>
					<c:when test="<%= partnerWorker.getUserId() > 0 %>">
						<%= partnerWorker.getUserId() %>
					</c:when>
					<c:otherwise>
						<strong><liferay-ui:message key="new-user" /></strong>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="screen-name"
				value='<%= (curUser != null) ? curUser.getScreenName() : "" %>'
			/>

			<liferay-ui:search-container-column-text
				name="email-address"
			>
				<c:choose>
					<c:when test="<%= curUser != null %>">
						<%= HtmlUtil.escape(curUser.getEmailAddress()) %>
					</c:when>
					<c:when test="<%= partnerWorker.getPartnerWorkerId() <= 0 %>">
						<aui:input label="" name="emailAddress" />
					</c:when>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="role"
			>
				<aui:select label="" name='<%= "role_" + partnerWorker.getPartnerWorkerId() %>'>
					<aui:option />

					<%
					for (int i = 1; i <= 3; i++) {
					%>

						<aui:option label="<%= PartnerWorkerConstants.getRoleLabel(i) %>" selected="<%= partnerWorker.getRole() == i %>" value="<%= i %>" />

					<%
					}
					%>

				</aui:select>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="user-status"
			>
				<c:choose>
					<c:when test="<%= partnerWorker.getPartnerWorkerId() <= 0 %>">
					</c:when>
					<c:when test="<%= curUser == null %>">
						<liferay-ui:message key="deleted" />
					</c:when>
					<c:when test="<%= !curUser.isActive() %>">
						<liferay-ui:message key="inactive" />
					</c:when>
					<c:when test="<%= RoleLocalServiceUtil.hasUserRole(partnerWorker.getUserId(), OSBConstants.ROLE_VERIFIED_USER_ID) %>">
						<liferay-ui:message key="verified" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="unverified" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/admin/partner_worker_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script>
	function <portlet:namespace />updatePartnerWorker(partnerWorkerId) {
		var form = AUI.$(document.<portlet:namespace />fm);

		form.fm('partnerWorkerId').val(partnerWorkerId);

		submitForm(form);
	}
</aui:script>