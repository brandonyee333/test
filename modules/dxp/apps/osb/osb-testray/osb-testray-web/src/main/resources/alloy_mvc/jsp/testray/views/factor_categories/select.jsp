<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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