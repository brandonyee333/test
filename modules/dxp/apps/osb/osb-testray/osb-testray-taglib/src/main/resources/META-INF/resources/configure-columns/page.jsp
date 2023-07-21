<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:set value='${requestScope["testray:configure-columns:columnLabels"]}' var="columnLabels" />
<c:set value='${requestScope["testray:configure-columns:columnLabelsSelected"]}' var="columnLabelsSelected" />
<c:set value='${requestScope["testray:configure-columns:sessionKey"]}' var="sessionKey" />

<div class="dropdown dropdown-md testray-dropdown-form">
	<aui:a cssClass="btn btn-default dropdown-toggle" data-toggle="dropdown" href="javascript:;">
		<aui:icon image="pause" />

		<liferay-ui:message key="columns" />

		<aui:icon image="caret-down" />
	</aui:a>

	<div class="dropdown-menu dropdown-menu-right">
		<aui:form action="${portletURL}" method="post" name="configureColumnsForm" onSubmit="event.preventDefault(); ${htmlNamespace}submitConfigureColumnsForm();">
			<aui:input name="redirect" type="hidden" value="${portletURL}" />

			<div class="inline-scroller inline-scroller-md testray-dropdown-form-body">
				<aui:fieldset id="columnsFieldset">
					<aui:field-wrapper cssClass="columns-wrapper" label="columns" name="columns">
						<c:forEach items="${columnLabels}" var="columnLabel">
							<div class="testray-checkbox-wrapper">
								<aui:input checked="${columnLabelsSelected.contains(columnLabel)}" cssClass="column-label" ignoreRequestValue="${true}" label="${columnLabel}" name="column${columnLabel}" onClick="${htmlNamespace}setApplyDisabled()" type="checkbox" />
							</div>
						</c:forEach>
					</aui:field-wrapper>
				</aui:fieldset>

				<aui:input name="columnsSelected" type="hidden" />
			</div>

			<aui:button-row cssClass="testray-dropdown-form-buttons">
				<aui:button name="applyButton" type="submit" value="apply" />
			</aui:button-row>
		</aui:form>
	</div>
</div>

<aui:script>
	Liferay.provide(
		window,
		'${htmlNamespace}submitConfigureColumnsForm',
		function() {
			var A = AUI();

			Liferay.Testray.combineCheckboxValues('${htmlNamespace}columnsFieldset', 'column', 'column-label', 'columnsSelected');

			var columnsSelectedField = A.one('#${htmlNamespace}columnsSelected');

			if (columnsSelectedField) {
				Liferay.Store('${sessionKey}', columnsSelectedField.val());
			}

			submitForm(document.${htmlNamespace}configureColumnsForm);
		},
		['liferay-store', 'testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}setApplyDisabled',
		function() {
			var A = AUI();

			Liferay.Testray.combineCheckboxValues('${htmlNamespace}columnsFieldset', 'column', 'column-label', 'columnsSelected');

			var columnsSelectedField = A.one('#${htmlNamespace}columnsSelected');

			var form = Liferay.Form.get('${htmlNamespace}configureColumnsForm');

			if (form) {
				var formNode = form.formNode;

				var applyButtonNode = formNode.one('#${htmlNamespace}applyButton');

				if (applyButtonNode) {
					Liferay.Util.toggleDisabled(applyButtonNode, columnsSelectedField.val() === '');
				}
			}

			var checkboxFieldset = A.one('#${htmlNamespace}columnsFieldset');

			if (checkboxFieldset) {
				checkboxFieldset.attr('disabled', false);

				var checkboxes = checkboxFieldset.all('.column-label');

				checkboxes.set('disabled', false);
			}
		},
		['liferay-form', 'liferay-store', 'testray-base']
	);
</aui:script>