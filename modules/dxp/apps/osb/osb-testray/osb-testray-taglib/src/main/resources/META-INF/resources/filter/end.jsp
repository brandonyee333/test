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

<c:set value='${requestScope["testray:filter:filterElements"]}' var="filterElements" />
			</ul>
		</div>

		<div class="testray-filter-footer">
			<aui:button-row cssClass="filter-form-buttons">
				<aui:button type="submit" value="apply" />
				<aui:button cssClass="button-action" onClick="Liferay.Testray.clearForm('${htmlNamespace}filterForm');" value="clear" />
			</aui:button-row>
		</div>
	</form>
</div>

<aui:script use="aui-popover,event-outside">
	var filterField = A.one('#testrayFilterField');

	var trigger = A.one('#filterToggle');

	if (trigger) {
		var popover = new A.Popover(
			{
				after: {
					render: function(event) {
						var instance = this;

						instance.get('contentBox').show();
					}
				},
				align: {
					node: trigger,
					points: [A.WidgetPositionAlign.TR, A.WidgetPositionAlign.BR]
				},
				constrain: '.testray-content-wrapper',
				contentBox: '.filter-popover',
				cssClass: 'filter-popover',
				headerContent: '<liferay-ui:message key="filter-results" unicode="${true}" />',
				position: 'bottom',
				visible: false,
				zIndex: Liferay.zIndex.POPOVER - 1
			}
		).render();

		trigger.on(
			'click',
			function(event) {
				event.stopPropagation();

				popover.toggle();

				filterField.focus();

				var boundingBox = popover.get('boundingBox');

				boundingBox.on(
					'clickoutside',
					function(event) {
						var target = event.target;

						if (!target.ancestor('.filter-popover-content')) {
							popover.hide();

							boundingBox.detach();
						}
					}
				);
			}
		);
	}
</aui:script>

<aui:script>
	function ${htmlNamespace}updateFilterPreferences() {
		var A = AUI();

		var filterPinButton = A.one('#${htmlNamespace}filterPinButton');

		if (!filterPinButton.hasClass('filter-pin-set')) {
			var filterPreferencesJSONObject = {
				<c:forEach items="${filterElements}" var="filterElement">
					<c:if test="${not empty param[filterElement.name]}">
						'${filterElement.name}': '${testray:escapeJS(param[filterElement.name])}',
					</c:if>
				</c:forEach>
			};

			if (Object.keys(filterPreferencesJSONObject).length > 0) {
				${htmlNamespace}setFilterPreferences(filterPreferencesJSONObject, 'filters-pinned-successfully', 'failed-to-pin-filters');

				filterPinButton.addClass('filter-pin-set');
			}
			else {
				Liferay.Testray.addAlert(
					{
						message: '<liferay-ui:message key="you-must-select-one-or-more-filters-before-pinning" />',
						type: 'danger'
					}
				);
			}
		}
		else {
			${htmlNamespace}setFilterPreferences({}, 'filters-unpinned-successfully', 'failed-to-unpin-filters');

			filterPinButton.removeClass('filter-pin-set');
		}
	};

	Liferay.provide(
		window,
		'${htmlNamespace}filterFormFields',
		function(input) {
			Liferay.Testray.filterList(input, '#${htmlNamespace}testrayFilterList .testray-filter-item', '.control-label', '#${htmlNamespace}noResultsFilterMessage');
		},
		['testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}setFilterPreferences',
		function(jsonObject, successMessage, failureMessage) {
			try {
				Liferay.Store('${TestrayFilterUtil.getFilterPreferencesKey(param.controller, param.action, testrayProjectId)}', JSON.stringify(jsonObject));

				Liferay.Testray.addAlert(
					{
						message: Liferay.Language.get(successMessage),
						type: 'success'
					}
				);
			}
			catch (err) {
				Liferay.Testray.addAlert(
					{
						message: Liferay.Language.get(failureMessage),
						type: 'danger'
					}
				);
			}
		},
		['liferay-store', 'testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}submitFilter',
		function() {
			var A = AUI();

			Liferay.fire('submitTestrayFilterForm');

			var filterForm = A.one('#${htmlNamespace}filterForm');

			if (filterForm) {
				var inputsToDisable = filterForm.all('button[type="submit"], select[value=-1], :checkbox:not(:checked), :input[value=""], #minDayParam, #minMonthParam, #minYearParam, #maxDayParam, #maxMonthParam, #maxYearParam');

				Liferay.Util.disableFormButtons(inputsToDisable, filterForm);

				var checkedCheckboxes = filterForm.all(':checkbox:checked');

				checkedCheckboxes.set('value', true);
			}

			submitForm(document.${htmlNamespace}filterForm);
		},
		['testray-base']
	);
</aui:script>