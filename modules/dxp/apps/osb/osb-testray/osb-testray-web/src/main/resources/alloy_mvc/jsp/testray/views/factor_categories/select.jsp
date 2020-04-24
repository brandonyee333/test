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
	<liferay-util:param name="title" value="select-categories" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:actionURL var="selectTestrayFactorOptionsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="controller" value="factor_options" />
	<portlet:param name="action" value="select" />
</portlet:actionURL>

<aui:form action="${selectTestrayFactorOptionsURL}" method="post" name="fm" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
	<aui:input name="redirect" type="hidden" value="${portletURL}" />

	<aui:input name="className" type="hidden" value="<%= TestrayRoutine.class.getName() %>" />
	<aui:input name="classPK" type="hidden" value="${testrayRoutineId}" />
	<aui:input name="testrayFactorCategoryIds" type="hidden" />

	<div class="${popup ? "spacing-footer testray-modal-content" : StringPool.BLANK}">
		<liferay-ui:input-move-boxes
			cssClass="testray-move-boxes"
			leftBoxName="availableTestrayFactorCategoryFields"
			leftList="${availableTestrayFactorCategories}"
			leftReorder="<%= Boolean.FALSE.toString() %>"
			leftTitle="available"
			rightBoxName="currentTestrayFactorCategoryFields"
			rightList="${currentTestrayFactorCategories}"
			rightTitle="selected"
		/>
	</div>

	<aui:button-row cssClass='${popup ? "testray-modal-footer" : StringPool.BLANK}'>
		<aui:button type="submit" value="next" />

		<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	Liferay.provide(
		window,
		'${htmlNamespace}submit',
		function() {
			document.${htmlNamespace}fm.${htmlNamespace}testrayFactorCategoryIds.value = Liferay.Util.listSelect(document.${htmlNamespace}fm.${htmlNamespace}currentTestrayFactorCategoryFields);

			var dialog = Liferay.Util.getWindow();

			if (dialog) {
				dialog.set('title', '${AlloyLanguageUtil.getUnicode("select-options")}');
			}

			submitForm(document.${htmlNamespace}fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>