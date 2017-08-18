<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
int[] curValues = ParamUtil.getIntegerValues(request, "curValues");
int itemsPerColumn = ParamUtil.getInteger(request, "itemsPerColumn");
String[] labels = StringUtil.split(ParamUtil.getString(request, "labels"));
String name = ParamUtil.getString(request, "name");
int[] values = ParamUtil.getIntegerValues(request, "values");

for (int i = 0; i < values.length; i++) {
%>

	<c:if test="<%= (i == 0) || ((itemsPerColumn > 0) && (i > 0) && ((i % itemsPerColumn) == 0)) %>">
		<div class="checkboxes-input-column">
	</c:if>

	<div class="checkboxes-checkbox">
		<label>
			<input <%= ArrayUtil.contains(curValues, values[i]) ? "checked" : "" %> class="aui-field-input-choice" name="<portlet:namespace /><%= HtmlUtil.escapeAttribute(name) %>" type="checkbox" value="<%= values[i] %>" />

			<span class="checkbox-text"><%= HtmlUtil.escape(LanguageUtil.get(request, labels[i])) %></span>
		</label>
	</div>

	<c:if test="<%= (i == (values.length - 1)) || ((itemsPerColumn > 0) && (((i + 1) % itemsPerColumn) == 0)) %>">
		</div>
	</c:if>

<%
}
%>