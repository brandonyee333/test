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

<%@ include file="/filter-element/start.jsp" %>

<c:set value='${requestScope["testray:filter-date-element:dayParam"]}' var="dayParam" />
<c:set value='${requestScope["testray:filter-date-element:monthParam"]}' var="monthParam" />
<c:set value='${requestScope["testray:filter-date-element:yearParam"]}' var="yearParam" />

<aui:field-wrapper cssClass="testray-input-date" label="${label}">
	<liferay-ui:input-date
		dayParam="${dayParam}"
		disableNamespace="${true}"
		monthParam="${monthParam}"
		name="${name}"
		nullable="${showEmptyOption}"
		yearParam="${yearParam}"
	/>
</aui:field-wrapper>