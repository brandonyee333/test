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
	<liferay-util:param name="title" value="create-user" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${selUser}" model="<%= User.class %>" />

<portlet:actionURL var="addUserURL">
	<portlet:param name="controller" value="users" />
	<portlet:param name="action" value="add" />
</portlet:actionURL>

<div class="testray-card testray-card-medium">
	<aui:form action="${addUserURL}" cssClass="testray-form" method="post" name="createUserForm" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
		<portlet:renderURL var="viewUsersURL">
			<portlet:param name="controller" value="users" />
			<portlet:param name="action" value="index" />
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="${viewUsersURL}" />

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
					<liferay-ui:message key="password" />
				</h2>
			</aui:col>

			<aui:col cssClass="testray-form-content" md="9">
				<aui:input label="password" name="password1" placeholder="password" required="${true}" type="password" />

				<aui:input label="confirm-password" name="password2" placeholder="confirm-password" required="${true}" type="password">
					<aui:validator errorMessage="passwords-do-not-match" name="equalTo">
						'#${htmlNamespace}password1'
					</aui:validator>
				</aui:input>
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

								<aui:input cssClass="testray-user-role" label="${checkboxLabel}" name="role${role.roleId}" type="checkbox" />
							</div>
						</c:forEach>
					</aui:field-wrapper>
				</aui:fieldset>

				<aui:input name="selUserRoleIds" type="hidden" />
			</aui:col>
		</aui:row>

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

			submitForm(document.${htmlNamespace}createUserForm);
		},
		['testray-base']
	);
</aui:script>