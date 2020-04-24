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

<c:set value='${requestScope["testray:filter-element:items"]}' var="items" />
<c:set value='${requestScope["testray:filter-element:label"]}' var="label" />
<c:set value='${requestScope["testray:filter-element:labelHelper"]}' var="labelHelper" />
<c:set value='${requestScope["testray:filter-element:name"]}' var="name" />
<c:set value='${requestScope["testray:filter-element:nullable"]}' var="nullable" />
<c:set value='${requestScope["testray:filter-element:nullableLabel"]}' var="nullableLabel" />
<c:set value='${requestScope["testray:filter-element:optionLabelProperty"]}' var="optionLabelProperty" />
<c:set value='${requestScope["testray:filter-element:optionValueProperty"]}' var="optionValueProperty" />
<c:set value='${requestScope["testray:filter-element:selectedValue"]}' var="selectedValue" />
<c:set value='${requestScope["testray:filter-element:showEmptyOption"]}' var="showEmptyOption" />
<c:set value='${requestScope["testray:filter-element:type"]}' var="type" />

<li class="testray-filter-item">
	<c:choose>
		<c:when test='${type == "boolean"}'>
			<aui:field-wrapper>
				<aui:select label="${label}" name="${name}">
					<c:if test="${showEmptyOption}">
						<aui:option label="" value="" />
					</c:if>

					<aui:option label="true" selected='${param[name] == "true"}' value="true" />
					<aui:option label="false" selected='${param[name] == "false"}' value="false" />
				</aui:select>
			</aui:field-wrapper>
		</c:when>
		<c:when test='${type == "checkboxes"}'>
			<aui:fieldset id="${name}Fieldset">
				<aui:field-wrapper cssClass="filter-${name}-wrapper" label="${label}" name="${label}">
					<c:if test="${nullable}">
						<div class="testray-checkbox-wrapper">
							<aui:input checked="${selectedValue.contains(Integer.valueOf(0))}" cssClass="filter-${name}" ignoreRequestValue="${true}" label="${nullableLabel}" name="${name}0" type="checkbox" />
						</div>
					</c:if>

					<c:forEach items="${items}" var="element">
						<div class="testray-checkbox-wrapper">
							<aui:input checked="${selectedValue.contains(element)}" cssClass="filter-${name}" ignoreRequestValue="${true}" label="${not empty labelHelper ? labelHelper.getStatusLabel(element) : element}" name="${name}${element}" type="checkbox" />
						</div>
					</c:forEach>
				</aui:field-wrapper>
			</aui:fieldset>

			<aui:input name="${name}" type="hidden" value="${param[name]}" />

			<aui:script use="testray-base">
				Liferay.on(
					'submitTestrayFilterForm',
					function() {
						Liferay.Testray.combineCheckboxValues('${htmlNamespace}${name}Fieldset', '${name}', 'filter-${name}', '${name}');
					}
				);
			</aui:script>
		</c:when>
		<c:when test='${type == "select-model"}'>
			<aui:field-wrapper>
				<aui:select label="${label}" name="${name}">
					<c:if test="${showEmptyOption}">
						<aui:option label="" value="${-1}" />
					</c:if>

					<c:if test="${nullable}">
						<aui:option label="${nullableLabel}" value="${0}" />
					</c:if>

					<c:forEach items="${items}" var="element">
						<aui:option label="${fn:escapeXml(element[optionLabelProperty])}" selected="${param[name] == element[optionValueProperty]}" value="${element[optionValueProperty]}" />
					</c:forEach>
				</aui:select>
			</aui:field-wrapper>
		</c:when>
		<c:when test='${type == "select-models"}'>
			<aui:field-wrapper>
				<aui:select cssClass="choices-select" label="${label}" multiple="${true}" name="${name}">
					<c:if test="${nullable}">
						<aui:option label="${nullableLabel}" selected="${selectedValue.contains(0)}" value="${0}" />
					</c:if>

					<c:forEach items="${items}" var="element">
						<aui:option label="${fn:escapeXml(element[optionLabelProperty])}" selected="${selectedValue.contains(element[optionValueProperty])}" useModelValue="${false}" value="${element[optionValueProperty]}" />
					</c:forEach>
				</aui:select>
			</aui:field-wrapper>

			<aui:script use="choices,testray-base">
				if (!Liferay.Testray.Choices) {
					Liferay.Testray.Choices = new Choices(
						'.choices-select',
						{
							itemSelectText: '',
							loadingText: '<liferay-ui:message key="loading" />',
							noResultsText: '<liferay-ui:message key="no-results-were-found" />',
							noChoicesText: '<liferay-ui:message key="no-other-selections" />',
							position: 'bottom',
							removeItemButton: true
						}
					);
				}
			</aui:script>
		</c:when>
		<c:when test='${type == "text"}'>
			<aui:field-wrapper>
				<aui:input label="${label}" name="${name}" />
			</aui:field-wrapper>
		</c:when>
		<c:when test='${type == "textarea"}'>
			<aui:field-wrapper>
				<c:set value="${name}BlankOnly" var="nullableName" />

				<aui:input disabled="${nullable ? param[nullableName] : false}" label="${label}" name="${name}" type="textarea" />

				<c:if test="${nullable}">
					<aui:input label="${nullableLabel}" name="${nullableName}" onChange="Liferay.Util.toggleDisabled(${htmlNamespace}${name}, this.checked);" type="checkbox" value="${param[nullableName]}" />
				</c:if>
			</aui:field-wrapper>
		</c:when>
	</c:choose>