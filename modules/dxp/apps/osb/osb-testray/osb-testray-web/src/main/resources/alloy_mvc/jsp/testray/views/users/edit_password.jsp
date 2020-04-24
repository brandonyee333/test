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
	<liferay-util:param name="title" value="${selUser.fullName}" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${selUser}" model="<%= User.class %>" />

<portlet:actionURL var="updateUserPasswordURL">
	<portlet:param name="controller" value="users" />
	<portlet:param name="action" value="updatePassword" />
</portlet:actionURL>

<div class="testray-card testray-card-medium">
	<aui:form action="${updateUserPasswordURL}" cssClass="testray-form" method="post" name="editUserPasswordForm">
		<portlet:renderURL var="editUserURL">
			<portlet:param name="controller" value="users" />
			<portlet:param name="action" value="edit" />
			<portlet:param name="id" value="${selUser.userId}" />
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="${editUserURL}" />

		<aui:input name="id" type="hidden" value="${selUser.userId}" />

		<aui:input label="new-password" name="password1" placeholder="password" required="${true}" type="password" />

		<aui:input label="confirm-password" name="password2" placeholder="confirm-password" required="${true}" type="password">
			<aui:validator errorMessage="passwords-do-not-match" name="equalTo">
				'#${htmlNamespace}password1'
			</aui:validator>
		</aui:input>

		<aui:button-row>
			<aui:button type="submit" value="change-password" />

			<aui:button href="${editUserURL}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>