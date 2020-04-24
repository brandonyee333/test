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

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="${popup ? "testray-modal-toolbar" : StringPool.BLANK}">
	<testray:table-toolbar
		title="requirements"
	>
		<%@ include file="/alloy_mvc/jsp/testray/views/requirements/index_filter.jspf" %>
	</testray:table-toolbar>
</div>

<portlet:actionURL var="updateTestrayCaseTestrayRequirementsURL">
	<portlet:param name="controller" value="cases" />
	<portlet:param name="action" value="updateRequirements" />
	<portlet:param name="id" value="${param.testrayCaseId}" />
</portlet:actionURL>

<aui:form action="${updateTestrayCaseTestrayRequirementsURL}" method="post" name="fm" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
	<aui:input name="redirect" type="hidden" value="" />

	<aui:input name="addTestrayRequirementIds" type="hidden" value="" />

	<aui:button-row cssClass='${popup ? "testray-modal-footer" : StringPool.BLANK}'>
		<aui:button type="submit" value="select-requirements" />

		<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
	</aui:button-row>

	<div class="${popup ? "spacing-footer spacing-toolbar testray-modal-content" : StringPool.BLANK}">
		<%@ include file="/alloy_mvc/jsp/testray/views/requirements/index_table.jspf" %>
	</div>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	Liferay.provide(
		window,
		'${htmlNamespace}submit',
		function() {
			var A = AUI();

			var checkedRowIdsString = Liferay.Util.listCheckedExcept(document.${htmlNamespace}fm, '${htmlNamespace}allRowIds');

			document.${htmlNamespace}fm.${htmlNamespace}addTestrayRequirementIds.value = checkedRowIdsString;

			submitForm(document.${htmlNamespace}fm);
		},
		['liferay-util-list-fields', 'testray-base']
	);
</aui:script>