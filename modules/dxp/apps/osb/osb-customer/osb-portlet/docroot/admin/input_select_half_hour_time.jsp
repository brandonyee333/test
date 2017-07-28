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
String name = ParamUtil.getString(request, "name");
int value = ParamUtil.getInteger(request, "value");

String am = LanguageUtil.get(request, "am");
String pm = LanguageUtil.get(request, "pm");
%>

<aui:select label="" name="<%= name %>">
	<aui:option label='<%= "12:00 " + am %>' selected="<%= value == 0 %>" value="0" />
	<aui:option label='<%= "12:30 " + am %>' selected="<%= value == 30 %>" value="30" />
	<aui:option label='<%= "1:00 " + am %>' selected="<%= value == 60 %>" value="60" />
	<aui:option label='<%= "1:30 " + am %>' selected="<%= value == 90 %>" value="90" />
	<aui:option label='<%= "2:00 " + am %>' selected="<%= value == 120 %>" value="120" />
	<aui:option label='<%= "2:30 " + am %>' selected="<%= value == 150 %>" value="150" />
	<aui:option label='<%= "3:00 " + am %>' selected="<%= value == 180 %>" value="180" />
	<aui:option label='<%= "3:30 " + am %>' selected="<%= value == 210 %>" value="210" />
	<aui:option label='<%= "4:00 " + am %>' selected="<%= value == 240 %>" value="240" />
	<aui:option label='<%= "4:30 " + am %>' selected="<%= value == 270 %>" value="270" />
	<aui:option label='<%= "5:00 " + am %>' selected="<%= value == 300 %>" value="300" />
	<aui:option label='<%= "5:30 " + am %>' selected="<%= value == 330 %>" value="330" />
	<aui:option label='<%= "6:00 " + am %>' selected="<%= value == 360 %>" value="360" />
	<aui:option label='<%= "6:30 " + am %>' selected="<%= value == 390 %>" value="390" />
	<aui:option label='<%= "7:00 " + am %>' selected="<%= value == 420 %>" value="420" />
	<aui:option label='<%= "7:30 " + am %>' selected="<%= value == 450 %>" value="450" />
	<aui:option label='<%= "8:00 " + am %>' selected="<%= value == 480 %>" value="480" />
	<aui:option label='<%= "8:30 " + am %>' selected="<%= value == 510 %>" value="510" />
	<aui:option label='<%= "9:00 " + am %>' selected="<%= value == 540 %>" value="540" />
	<aui:option label='<%= "9:30 " + am %>' selected="<%= value == 570 %>" value="570" />
	<aui:option label='<%= "10:00 " + am %>' selected="<%= value == 600 %>" value="600" />
	<aui:option label='<%= "10:30 " + am %>' selected="<%= value == 630 %>" value="630" />
	<aui:option label='<%= "11:00 " + am %>' selected="<%= value == 660 %>" value="660" />
	<aui:option label='<%= "11:30 " + am %>' selected="<%= value == 690 %>" value="690" />
	<aui:option label='<%= "12:00 " + pm %>' selected="<%= value == 720 %>" value="720" />
	<aui:option label='<%= "12:30 " + pm %>' selected="<%= value == 750 %>" value="750" />
	<aui:option label='<%= "1:00 " + pm %>' selected="<%= value == 780 %>" value="780" />
	<aui:option label='<%= "1:30 " + pm %>' selected="<%= value == 810 %>" value="810" />
	<aui:option label='<%= "2:00 " + pm %>' selected="<%= value == 840 %>" value="840" />
	<aui:option label='<%= "2:30 " + pm %>' selected="<%= value == 870 %>" value="870" />
	<aui:option label='<%= "3:00 " + pm %>' selected="<%= value == 900 %>" value="900" />
	<aui:option label='<%= "3:30 " + pm %>' selected="<%= value == 930 %>" value="930" />
	<aui:option label='<%= "4:00 " + pm %>' selected="<%= value == 960 %>" value="960" />
	<aui:option label='<%= "4:30 " + pm %>' selected="<%= value == 990 %>" value="990" />
	<aui:option label='<%= "5:00 " + pm %>' selected="<%= value == 1020 %>" value="1020" />
	<aui:option label='<%= "5:30 " + pm %>' selected="<%= value == 1050 %>" value="1050" />
	<aui:option label='<%= "6:00 " + pm %>' selected="<%= value == 1080 %>" value="1080" />
	<aui:option label='<%= "6:30 " + pm %>' selected="<%= value == 1110 %>" value="1110" />
	<aui:option label='<%= "7:00 " + pm %>' selected="<%= value == 1140 %>" value="1140" />
	<aui:option label='<%= "7:30 " + pm %>' selected="<%= value == 1170 %>" value="1170" />
	<aui:option label='<%= "8:00 " + pm %>' selected="<%= value == 1200 %>" value="1200" />
	<aui:option label='<%= "8:30 " + pm %>' selected="<%= value == 1230 %>" value="1230" />
	<aui:option label='<%= "9:00 " + pm %>' selected="<%= value == 1260 %>" value="1260" />
	<aui:option label='<%= "9:30 " + pm %>' selected="<%= value == 1290 %>" value="1290" />
	<aui:option label='<%= "10:00 " + pm %>' selected="<%= value == 1320 %>" value="1320" />
	<aui:option label='<%= "10:30 " + pm %>' selected="<%= value == 1350 %>" value="1350" />
	<aui:option label='<%= "11:00 " + pm %>' selected="<%= value == 1380 %>" value="1380" />
	<aui:option label='<%= "11:30 " + pm %>' selected="<%= value == 1410 %>" value="1410" />
	<aui:option label='<%= "11:59 " + pm %>' selected="<%= value == 1439 %>" value="1439" />
</aui:select>