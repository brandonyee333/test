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

<%@ include file="/support/2/init.jsp" %>

<%
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

Map<Long, String> ticketInformationFieldsMap = ticketEntry.getTicketInformationFieldsMap();

int docLibPersistence = ParamUtil.getInteger(request, "docLibPersistence", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_DOC_LIB_PERSISTENCE)));
String stepsToUpgrade = ParamUtil.getString(request, "stepsToUpgrade", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_STEPS_TO_UPGRADE)));
%>

<div class="aui-helper-clearfix single-line">
	<span class="txt-sb"><liferay-ui:message key="how-is-the-document-library-server-persisting-documents" /></span>

	<%= LanguageUtil.get(request, TicketEntryConstants.getDocLibPersistenceLabel(docLibPersistence)) %>
</div>

<div class="single-line">
	<span class="txt-sb"><liferay-ui:message key="steps-used-to-perform-the-upgrade" /></span>

	<pre><%= SupportUtil.getHTML(stepsToUpgrade) %></pre>
</div>