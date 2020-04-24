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
	<liferay-util:param name="title" value="select-stacks" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:actionURL var="addTestrayRunsURL">
	<portlet:param name="controller" value="factors" />
	<portlet:param name="action" value="addTestrayRuns" />
</portlet:actionURL>

<aui:form action="${addTestrayRunsURL}" method="post" name="fm" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
	<aui:input name="redirect" type="hidden" value="" />

	<aui:input name="testrayBuildId" type="hidden" value="${testrayBuildId}" />

	<c:forEach items="${testrayFactorCategoryMap}" var="testrayFactorCategoryEntry" varStatus="i">
		<c:set value="${testrayFactorCategoryEntry.key}" var="testrayFactorCategory" />

		<aui:input name="testrayFactorCategoryId_column${i.index}" type="hidden" value="${testrayFactorCategory.testrayFactorCategoryId}" />
		<aui:input name="testrayFactorCategoryName_column${i.index}" type="hidden" value="${testrayFactorCategory.name}" />
	</c:forEach>

	<div class="${popup ? "spacing-footer testray-modal-content" : StringPool.BLANK}">
		<aui:fieldset id="testrayFactors">
			<c:forEach items="${testrayFactorCombinations}" var="testrayFactors" varStatus="j">
				<aui:field-wrapper cssClass="lfr-form-row">
					<c:forEach items="${testrayFactors}" var="testrayFactor" varStatus="k">
						<aui:col span="${testrayFactors.size() / 12}">
							<aui:input cssClass="lfr-form-row-input" name="testrayFactorOptionId_column${k.index}_auto${j.index}" type="hidden" value="${testrayFactor.testrayFactorOptionId}" />

							<aui:input label="${testrayFactor.testrayFactorCategoryName}" name="testrayFactorOptionName_column${k.index}_auto${j.index}" readonly="true" type="text" value="${testrayFactor.testrayFactorOptionName}" />
						</aui:col>
					</c:forEach>

					<span class="aui-toolbar lfr-autorow-controls">
						<span class="aui-toolbar-content">
							<button class="btn btn-content btn-icon-only delete-row toolbar-item toolbar-last" title="" type="button"><span class="btn-icon icon icon-minus"></span></button>
						</span>
					</span>
				</aui:field-wrapper>

				<c:set value="${testrayFactors.size()}" var="testrayFactorCategoriesCount" />
			</c:forEach>

			<aui:input name="autoTestrayRunsCount" type="hidden" value="${testrayFactorCombinations.size()}" />
			<aui:input name="testrayFactorCategoriesCount" type="hidden" value="${testrayFactorCategoriesCount}" />

			<aui:field-wrapper cssClass="duplicable lfr-form-row">
				<c:forEach items="${testrayFactorCategoryMap}" var="testrayFactorCategoryEntry" varStatus="l">
					<c:set value="${testrayFactorCategoryEntry.key}" var="testrayFactorCategory" />

					<aui:col span="${testrayFactorCategoryMap.size() / 12}">
						<aui:select cssClass="lfr-form-row-select" label="${testrayFactorCategory.name}" name="testrayFactorOptionId_column${l.index}_custom1" showEmptyOption="${true}" style="width: 220px;">
							<c:forEach items="${testrayFactorCategoryEntry.value}" var="testrayFactorOption">
								<aui:option label="${testrayFactorOption.name}" value="${testrayFactorOption.testrayFactorOptionId}" />
							</c:forEach>
						</aui:select>
					</aui:col>
				</c:forEach>
			</aui:field-wrapper>

			<aui:input name="testrayFactorIndexes" type="hidden" />
		</aui:fieldset>
	</div>

	<aui:button-row cssClass='${popup ? "testray-modal-footer" : StringPool.BLANK}'>
		<aui:button type="submit" value="add" />

		<aui:button onClick="Liferay.Testray.closeWindow();" value="close" />
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	var autoFields;

	AUI().use(
		'liferay-auto-fields',
		function(A) {
			autoFields = new Liferay.AutoFields(
				{
					baseRows: '.duplicable',
					contentBox: '#${htmlNamespace}testrayFactors',
					fieldIndexes: '${htmlNamespace}testrayFactorIndexes'
				}
			).render();
		}
	);

	Liferay.provide(
		window,
		'${htmlNamespace}submit',
		function() {
			var A = AUI();

			var opener = Liferay.Util.getOpener();

			if (opener && opener.${htmlNamespace}addAutoFieldRows) {
				var autoFieldsContainer = A.one('#${htmlNamespace}testrayFactors');

				if (autoFieldsContainer) {
					if (autoFields) {
						autoFields.save(autoFieldsContainer);
					}

					var autoTestrayFactors = autoFieldsContainer.all('.lfr-form-row-input');
					var customTestrayFactors = autoFieldsContainer.all('.lfr-form-row-select');

					var testrayFactors = autoTestrayFactors.concat(customTestrayFactors);

					opener.${htmlNamespace}addAutoFieldRows(testrayFactors);

					var autoTestrayFactorRows = autoFieldsContainer.all('.lfr-form-row');

					if (autoTestrayFactorRows) {
						var rowCount = autoTestrayFactorRows.size();

						var message = A.Lang.sub(
							'<liferay-ui:message key="x-stacks-were-added-successfully" />',
							[
								rowCount
							]
						);

						opener.Liferay.Testray.addAlert(
							{
								containerId: '#${htmlNamespace}testrayAlertContainer',
								message: message,
								type: 'success'
							}
						);
					}
				}
			}
			else {
				submitForm(document.${htmlNamespace}fm);
			}

			Liferay.Testray.closeWindow();
		},
		['testray-base']
	);
</aui:script>