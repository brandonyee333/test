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
String cssClass = ParamUtil.getString(request, "cssClass");
String hourParam = ParamUtil.getString(request, "hourParam");
int hourValue = ParamUtil.getInteger(request, "hourValue", -1);
boolean hourNullable = ParamUtil.getBoolean(request, "hourNullable");
String minuteParam = ParamUtil.getString(request, "minuteParam");
int minuteValue = ParamUtil.getInteger(request, "minuteValue", -1);
int minuteInterval = ParamUtil.getInteger(request, "minuteInterval");
boolean minuteNullable = ParamUtil.getBoolean(request, "minuteNullable");
String amPmParam = ParamUtil.getString(request, "amPmParam");
int amPmValue = ParamUtil.getInteger(request, "amPmValue", -1);
boolean amPmNullable = ParamUtil.getBoolean(request, "amPmNullable");
boolean disabled = ParamUtil.getBoolean(request, "disabled");

NumberFormat inputTimeNumberFormat = NumberFormat.getNumberInstance(locale);

inputTimeNumberFormat.setMinimumIntegerDigits(2);

SimpleDateFormat simpleDateFormat = (SimpleDateFormat)DateFormat.getTimeInstance(DateFormat.SHORT, locale);

String pattern = simpleDateFormat.toPattern();

boolean formatAmPm = pattern.contains("a");
%>

<div class="lfr-input-time <%= HtmlUtil.escapeAttribute(cssClass) %>">
	<select <%= disabled ? "disabled=\"disabled\"" : "" %> name="<%= HtmlUtil.escapeAttribute(hourParam) %>">
		<c:if test="<%= hourNullable %>">
			<option <%= (hourValue == -1) ? "selected" : "" %> value=""></option>
		</c:if>

		<%
		for (int i = 0; i < (formatAmPm ? 12 : 24); i++) {
			String hourString = String.valueOf(i);

			if (formatAmPm && (i == 0)) {
				hourString = "12";
			}
		%>

			<option <%= (hourValue == i) ? "selected" : "" %> value="<%= i %>"><%= hourString %></option>

		<%
		}
		%>

	</select>

	<%= StringPool.COLON %>

	<select <%= disabled ? "disabled=\"disabled\"" : "" %> name="<%= HtmlUtil.escapeAttribute(minuteParam) %>">
		<c:if test="<%= minuteNullable %>">
			<option <%= (minuteValue == -1) ? "selected" : "" %> value=""></option>
		</c:if>

		<%
		for (int i = 0; i < 60; i++) {
			String minute = inputTimeNumberFormat.format(i);
		%>

			<c:if test="<%= (minuteInterval == 0) || ((i % minuteInterval) == 0) %>">
				<option <%= (minuteValue == i) ? "selected" : "" %> value="<%= i %>"><%= minute %></option>
			</c:if>

		<%
		}
		%>

	</select>

	<c:choose>
		<c:when test="<%= !formatAmPm %>">
			<input name="<%= HtmlUtil.escapeAttribute(amPmParam) %>" type="hidden" value="<%= Calendar.AM %>" />
		</c:when>
		<c:otherwise>
			<select <%= disabled ? "disabled=\"disabled\"" : "" %> name="<%= HtmlUtil.escapeAttribute(amPmParam) %>">
				<c:if test="<%= amPmNullable %>">
					<option <%= (amPmValue == -1) ? "selected" : "" %> value=""></option>
				</c:if>

				<option <%= (amPmValue == Calendar.AM) ? "selected" : "" %> value="<%= Calendar.AM %>"><liferay-ui:message key="am" /></option>
				<option <%= (amPmValue == Calendar.PM) ? "selected" : "" %> value="<%= Calendar.PM %>"><liferay-ui:message key="pm" /></option>
			</select>
		</c:otherwise>
	</c:choose>
</div>