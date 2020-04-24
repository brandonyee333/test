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
	<liferay-util:param name="title" value="${testrayCaseResultComposite.testrayCaseName}" />
</liferay-util:include>

<aui:model-context bean="${testrayCaseResultComposite}" model="<%= TestrayCaseResult.class %>" />

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:actionURL var="updateTestrayCaseResult">
	<portlet:param name="controller" value="case_results" />
	<portlet:param name="action" value="update" />
</portlet:actionURL>

<div class="testray-card">
	<aui:form action="${updateTestrayCaseResult}" name="fm">
		<aui:input name="redirect" type="hidden" value="${redirect}" />

		<aui:input name="id" type="hidden" value="${testrayCaseResultComposite.testrayCaseResultId}" />

		<div class="${popup ? "spacing-footer testray-modal-content" : StringPool.BLANK}">
			<c:if test="${testrayCaseResultComposite.getStatus() != TestrayCaseResultConstants.STATUS_IN_PROGRESS}">
				<div class="alert alert-info">
					<liferay-ui:message key="clicking-save-will-assign-you-to-this-case-result" />
				</div>
			</c:if>

			<aui:select name="status" showEmptyOption="${false}">
				<aui:option label="${TestrayCaseResultConstants.LABEL_PASSED}" value="${TestrayCaseResultConstants.STATUS_PASSED}" />
				<aui:option label="${TestrayCaseResultConstants.LABEL_FAILED}" value="${TestrayCaseResultConstants.STATUS_FAILED}" />
				<aui:option label="${TestrayCaseResultConstants.LABEL_BLOCKED}" value="${TestrayCaseResultConstants.STATUS_BLOCKED}" />
				<aui:option label="${TestrayCaseResultConstants.LABEL_TEST_FIX}" value="${TestrayCaseResultConstants.STATUS_TEST_FIX}" />
			</aui:select>

			<aui:input name="issues" type="text" />

			<aui:input name="comment" type="textarea" value="${testrayCaseResultComposite.comment}" />
		</div>

		<aui:button-row cssClass='${popup ? "testray-modal-footer" : StringPool.BLANK}'>
			<aui:button type="submit" value="save" />

			<aui:button href="${redirect}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>