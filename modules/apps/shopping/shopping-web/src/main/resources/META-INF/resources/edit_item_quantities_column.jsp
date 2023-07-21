<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
int start = GetterUtil.getInteger(request.getAttribute("start"));

int rowNumber = start + row.getPos();
%>

<aui:input label="" name='<%= "fieldsQuantity" + rowNumber %>' size="4" title="quantity" type="text" value="0" />