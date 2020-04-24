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
	<liferay-util:param name="title" value="new-option" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="alert alert-error hide" id="${htmlNamespace}factorOptionsAlertContainer"></div>

<aui:model-context bean="${testrayFactorOption}" model="<%= TestrayFactorOption.class %>" />

<portlet:actionURL var="addTestrayFactorOptionURL">
	<portlet:param name="controller" value="factor_options" />
	<portlet:param name="action" value="add" />
</portlet:actionURL>

<aui:form action="${addTestrayFactorOptionURL}" method="post" name="addTestrayFactorOptionForm" onSubmit='${(not empty param.closeOnSave) ? "event.preventDefault(); ${htmlNamespace}submitTestrayFactorOption();" : StringPool.BLANK}'>
	<portlet:renderURL var="viewTestrayFactorOptionsURL">
		<portlet:param name="controller" value="factor_options" />
		<portlet:param name="action" value="index" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="${viewTestrayFactorOptionsURL}" />

	<div class="${popup ? "spacing-footer testray-modal-content" : StringPool.BLANK}">
		<aui:input autoFocus="${true}" name="name" required="${true}" />

		<aui:select label="type" name="testrayFactorCategoryId" showEmptyOption="${true}">
			<c:forEach items="${testrayFactorCategories}" var="testrayFactorCategory">
				<aui:option label="${testrayFactorCategory.name}" selected="${testrayFactorOption.testrayFactorCategoryId == testrayFactorCategory.testrayFactorCategoryId}" value="${testrayFactorCategory.testrayFactorCategoryId}" />
			</c:forEach>
		</aui:select>
	</div>

	<aui:button-row cssClass='${popup ? "testray-modal-footer" : StringPool.BLANK}'>
		<aui:button type="submit" />

		<aui:button onClick='${(not empty param.closeOnSave) ? "Liferay.Testray.closeWindow();" : viewTestrayFactorOptionsURL}' value="cancel" />
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	Liferay.provide(
		window,
		'${htmlNamespace}submitTestrayFactorOption',
		function() {
			var A = AUI();

			Liferay.Testray.request(
				{
					controller: 'factor_options',
					controllerMethod: 'add.json',
					form: {
						id: '${htmlNamespace}addTestrayFactorOptionForm'
					}
				}
			).then(
				function(response) {
					var ${htmlNamespace}opener = Liferay.Util.getOpener();

					if (${htmlNamespace}opener && ${htmlNamespace}opener.${htmlNamespace}updateAvailableTestrayFactorOptions) {
						${htmlNamespace}opener.${htmlNamespace}updateAvailableTestrayFactorOptions();

						${htmlNamespace}opener.Liferay.Testray.addAlert(
							{
								containerId: '#${htmlNamespace}testrayAlertContainer',
								message: '<liferay-ui:message key="the-new-option-was-created-successfully" />',
								type: 'success'
							}
						);

						Liferay.Testray.closeWindow();
					}
				}
			).catch(
				function(response) {
					var alertContainer = A.one('#${htmlNamespace}factorOptionsAlertContainer');

					if (alertContainer) {
						alertContainer.text(response.message);

						alertContainer.show();
					}
				}
			);
		},
		['testray-base']
	);
</aui:script>