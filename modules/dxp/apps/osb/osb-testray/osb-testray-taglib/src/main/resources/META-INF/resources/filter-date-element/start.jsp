<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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