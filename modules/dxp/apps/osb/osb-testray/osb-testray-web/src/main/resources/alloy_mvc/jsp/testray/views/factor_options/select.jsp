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
	<liferay-util:param name="title" value="select-options" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:actionURL var="selectTestrayFactorsURL">
	<portlet:param name="controller" value="factors" />
	<portlet:param name="action" value="select" />
</portlet:actionURL>

<portlet:actionURL var="addTestrayFactorsURL">
	<portlet:param name="controller" value="factors" />
	<portlet:param name="action" value="addTestrayFactors" />
	<portlet:param name="testrayBuildId" value="${param.testrayBuildId}" />
</portlet:actionURL>

<aui:form action="${param.multiselect ? selectTestrayFactorsURL : addTestrayFactorsURL}" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="${param.multiselect ? portletURL : StringPool.BLANK}" />

	<aui:input name="className" type="hidden" value="${param.className}" />
	<aui:input name="classPK" type="hidden" value="${param.classPK}" />
	<aui:input name="testrayRoutineId" type="hidden" value="${param.testrayRoutineId}" />

	<div class="${popup ? "spacing-footer testray-modal-content" : StringPool.BLANK}">
		<c:forEach items="${testrayFactorCategoryMap}" var="testrayFactorCategoryEntry" varStatus="i">
			<c:set value="${testrayFactorCategoryEntry.key}" var="testrayFactorCategory" />

			<aui:field-wrapper label="${testrayFactorCategory.name}">
				<aui:input name="testrayFactorCategoryId${i.index}" type="hidden" value="${testrayFactorCategory.testrayFactorCategoryId}" />
				<aui:input name="testrayFactorCategoryName${i.index}" type="hidden" value="${testrayFactorCategory.name}" />

				<aui:select label="" multiple="${param.multiselect}" name="testrayFactorOptionId${i.index}" required="<%= true %>" showEmptyOption="${param.multiselect ? false : true}" size="${param.multiselect ? 8 : 0}">
					<c:forEach items="${testrayFactorCategoryEntry.value}" var="testrayFactorOption">
						<aui:option label="${testrayFactorOption.name}" selected="${selectedTestrayFactorOptionIds.contains(testrayFactorOption.testrayFactorOptionId)}" value="${testrayFactorOption.testrayFactorOptionId}" />
					</c:forEach>
				</aui:select>
			</aui:field-wrapper>
		</c:forEach>
	</div>

	<aui:input name="testrayFactorCategoriesCount" type="hidden" value="${testrayFactorCategoryMap.size()}" />

	<aui:button-row cssClass='${popup ? "testray-modal-footer" : StringPool.BLANK}'>
		<aui:button type="submit" value='${param.multiselect ? "next" : "save"}' />

		<c:choose>
			<c:when test="${not empty param.redirect}">
				<aui:button href="${param.redirect}" value="back" />
			</c:when>
			<c:otherwise>
				<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
			</c:otherwise>
		</c:choose>
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>