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
	<liferay-util:param name="title" value="new-product-version" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayProductVersion}" model="<%= TestrayProductVersion.class %>" />

<portlet:actionURL var="addTestrayProductVersionURL">
	<portlet:param name="controller" value="product_versions" />
	<portlet:param name="action" value="add" />
</portlet:actionURL>

<aui:form action="${addTestrayProductVersionURL}" method="post" name="addTestrayProductVersionForm" onSubmit="${(not empty param.closeOnSave) ? 'event.preventDefault(); submitTestrayProductVersion();' : StringPool.BLANK}">
	<portlet:renderURL var="viewTestrayProductVersionsURL">
		<portlet:param name="controller" value="product_versions" />
		<portlet:param name="action" value="index" />
		<portlet:param name="testrayProjectId" value="${testrayProductVersion.testrayProjectId}" />
	</portlet:renderURL>

	<aui:input name="redirect" type="hidden" value="${viewTestrayProductVersionsURL}" />

	<aui:input name="testrayProjectId" type="hidden" />

	<div class="${popup ? "spacing-footer testray-modal-content" : StringPool.BLANK}">
		<aui:input autoFocus="${true}" name="name" required="${true}" />
	</div>

	<aui:button-row cssClass='${popup ? "testray-modal-footer" : StringPool.BLANK}'>
		<aui:button type="submit" />

		<aui:button onClick='${(not empty param.closeOnSave) ? "Liferay.Testray.closeWindow();" : viewTestrayProductVersionsURL}' value="cancel" />
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	Liferay.provide(
		window,
		'submitTestrayProductVersion',
		function() {
			var A = AUI();

			Liferay.Testray.request(
				{
					controller: 'product_versions',
					controllerMethod: 'add.json',
					form: {
						id: '${htmlNamespace}addTestrayProductVersionForm'
					}
				}
			).then(
				function(response) {
					var ${htmlNamespace}opener = Liferay.Util.getOpener();

					if (${htmlNamespace}opener && ${htmlNamespace}opener.${htmlNamespace}updateAvailableTestrayProductVersions) {
						${htmlNamespace}opener.${htmlNamespace}updateAvailableTestrayProductVersions();

						var data = response.data;

						if (data) {
							var name = data.name;

							var message = A.Lang.sub(
								'<liferay-ui:message key="the-new-product-version-x-was-created-successfully" />',
								[
									name
								]
							);

							${htmlNamespace}opener.Liferay.Testray.addAlert(
								{
									containerId: '#${htmlNamespace}testrayAlertContainer',
									message: message,
									type: 'success'
								}
							);
						}

						Liferay.Testray.closeWindow();
					}
				}
			).catch(
				function(response) {
					Liferay.Testray.addAlert(
						{
							containerId: '#${htmlNamespace}testrayAlertContainer',
							message: response.message,
							type: 'danger'
						}
					);
				}
			);
		},
		['testray-base']
	);
</aui:script>