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
long eulaFileEntryId = GetterUtil.getLong(portletPreferences.getValue("eulaFileEntryId", null));

String eulaVersion = portletPreferences.getValue("eulaVersion", null);
%>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<div class="training-certification-agreement configuration">
	<aui:form action="<%= configurationURL %>" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<aui:input name="eulaFileEntryId" type="text" value="<%= String.valueOf(eulaFileEntryId) %>" />

		<aui:input name="eulaVersion" type="text" value="<%= eulaVersion %>" />

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:form>
</div>