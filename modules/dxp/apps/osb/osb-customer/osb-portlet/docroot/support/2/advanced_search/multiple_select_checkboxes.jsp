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

<div class="checkboxes">

	<%
	int[] curValues = ParamUtil.getIntegerValues(request, "curValues");
	String[] labels = StringUtil.split(ParamUtil.getString(request, "labels"));
	String name = ParamUtil.getString(request, "name");
	int[] values = ParamUtil.getIntegerValues(request, "values");

	for (int i = 0; i < values.length; i++) {
	%>

		<div class="checkbox">
			<label>
				<input <%= ArrayUtil.contains(curValues, values[i]) ? "checked" : "" %> class="aui-field-input-choice" name="<portlet:namespace /><%= HtmlUtil.escapeAttribute(name) %>" onchange="<portlet:namespace />toggleCheckbox(this);" type="checkbox" value="<%= values[i] %>" />

				<span class="checkbox-text"><%= HtmlUtil.escape(LanguageUtil.get(request, labels[i])) %></span>
			</label>
		</div>

	<%
	}
	%>

</div>