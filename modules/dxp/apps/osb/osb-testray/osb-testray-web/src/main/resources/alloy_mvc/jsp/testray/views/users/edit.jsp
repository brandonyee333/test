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

<portlet:actionURL var="updateUserURL">
	<portlet:param name="controller" value="users" />
	<portlet:param name="action" value="update" />
</portlet:actionURL>

<div class="testray-card testray-card-medium">
	<aui:form action="${updateUserURL}" cssClass="testray-form" enctype="multipart/form-data" method="post" name="editUserForm" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
		<aui:input name="redirect" type="hidden" value="${portletURL}" />

		<aui:input name="id" type="hidden" value="${selUser.userId}" />

		<aui:row cssClass="testray-form-section">
			<aui:col cssClass="testray-form-description" md="3">
				<h2>
					<liferay-ui:message key="user-information" />
				</h2>
			</aui:col>

			<aui:col cssClass="testray-form-content" md="9">
				<aui:input autoFocus="${true}" name="firstName" placeholder="first-name" required="${true}" />

				<aui:input name="lastName" placeholder="last-name" required="${true}" />

				<aui:input name="emailAddress" placeholder="email-address" required="${true}" />

				<aui:input name="screenName" placeholder="screen-name" required="${true}" />
			</aui:col>
		</aui:row>

		<aui:row cssClass="testray-form-section">
			<aui:col cssClass="testray-form-description" md="3">
				<h2>
					<liferay-ui:message key="avatar" />
				</h2>
			</aui:col>

			<aui:col cssClass="testray-form-content" md="9">
				<div class="testray-user-icon user-icon user-icon-xl" id="testrayUserAvatar">
					<img alt="avatar" src="${selUser.getPortraitURL(themeDisplay)}" />
				</div>

				<aui:input label="" name="avatar" type="file" wrapperCssClass="testray-user-icon-input" />
			</aui:col>
		</aui:row>

		<aui:row cssClass="testray-form-section">
			<aui:col cssClass="testray-form-description" md="3">
				<h2>
					<liferay-ui:message key="change-password" />
				</h2>
			</aui:col>

			<aui:col cssClass="testray-form-content" md="9">
				<portlet:renderURL var="editUserPasswordURL">
					<portlet:param name="controller" value="users" />
					<portlet:param name="action" value="editPassword" />
					<portlet:param name="id" value="${selUser.userId}" />
				</portlet:renderURL>

				<aui:button href="${editUserPasswordURL}" value="change-password" />
			</aui:col>
		</aui:row>

		<aui:row cssClass="testray-form-section">
			<aui:col cssClass="testray-form-description" md="3">
				<h2>
					<liferay-ui:message key="roles" />
				</h2>
			</aui:col>

			<aui:col cssClass="testray-form-content" md="9">
				<aui:fieldset id="roles">
					<aui:field-wrapper cssClass="testray-user-role-wrapper">
						<c:forEach items="${roles}" var="role">
							<div class="testray-checkbox-wrapper testray-user-role-checkbox-wrapper">
								<c:set var="checkboxLabel">
									${role.name}

									<div class="testray-checkbox-description">
										${role.description}
									</div>
								</c:set>

								<aui:input checked="${selUserRoleIds.contains(role.roleId)}" cssClass="testray-user-role" disabled='${not testrayPermission:containsModelAction(themeDisplay, selUser, "permissions")}' label="${checkboxLabel}" name="role${role.roleId}" type="checkbox" />
							</div>
						</c:forEach>
					</aui:field-wrapper>
				</aui:fieldset>

				<aui:input name="selUserRoleIds" type="hidden" value="${selUserRoleIds}" />
			</aui:col>
		</aui:row>

		<c:if test="${selUser.userId != themeDisplay.userId}">
			<aui:row cssClass="testray-form-section">
				<aui:col cssClass="testray-form-description" md="3">
					<h2>
						<liferay-ui:message key="active" />
					</h2>
				</aui:col>

				<aui:col cssClass="testray-form-content" md="9">
					<aui:input name="active" onChange="Liferay.Util.toggleDisabled(${htmlNamespace}deleteUserButton, this.checked);" type="checkbox" value="${selUser.status == 0}" />
				</aui:col>
			</aui:row>

			<aui:row cssClass="testray-form-section">
				<aui:col cssClass="testray-form-description" md="3">
					<h2>
						<liferay-ui:message key="delete-user" />
					</h2>
				</aui:col>

				<aui:col cssClass="testray-form-content" md="9">
					<portlet:renderURL var="viewUsersURL">
						<portlet:param name="controller" value="users" />
						<portlet:param name="action" value="index" />
					</portlet:renderURL>

					<portlet:actionURL var="deleteUserURL">
						<portlet:param name="controller" value="users" />
						<portlet:param name="action" value="delete" />
						<portlet:param name="id" value="${selUser.userId}" />
						<portlet:param name="redirect" value="${viewUsersURL}" />
					</portlet:actionURL>

					<c:set value="javascript:Liferay.Testray.confirmDelete('${deleteUserURL}')" var="deleteUserURL" />

					<aui:button cssClass="btn-danger" disabled="${selUser.status == 0}" name="deleteUserButton" onClick="${deleteUserURL}" value="delete-user" />
				</aui:col>
			</aui:row>
		</c:if>

		<aui:button-row>
			<aui:button type="submit" value="save" />

			<aui:button href="${viewUsersURL}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	Liferay.provide(
		window,
		'${htmlNamespace}submit',
		function() {
			Liferay.Testray.combineCheckboxValues('${htmlNamespace}roles', 'role', 'testray-user-role', 'selUserRoleIds');

			submitForm(document.${htmlNamespace}editUserForm);
		},
		['testray-base']
	);
</aui:script>