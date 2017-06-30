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

<%@ include file="/marketplace_apps/init.jsp" %>

<%
long contractEntryId = ParamUtil.getLong(request, "contractEntryId");

ContractEntry tosContractEntry = ContractEntryLocalServiceUtil.getLatestContractEntry(ContractEntryConstants.DEFAULT_CLASS_NAME_ID, ContractEntryConstants.DEFAULT_CLASS_PK, ContractEntryConstants.TYPE_TERMS_OF_SERVICE);
%>

<c:choose>
	<c:when test="<%= contractEntryId > 0 %>">
		<liferay-util:include page="/marketplace/view_contract_entry.jsp" servletContext="<%= application %>">
			<liferay-util:param name="contractEntryId" value="<%= String.valueOf(contractEntryId) %>" />
		</liferay-util:include>
	</c:when>
	<c:when test="<%= !ContractAuditLocalServiceUtil.hasContractAudit(tosContractEntry.getContractEntryId(), User.class.getName(), themeDisplay.getUserId()) %>">
		<liferay-util:include page="/marketplace/view_contract_entry.jsp" servletContext="<%= application %>">
			<liferay-util:param name="redirect" value="<%= currentURL %>" />
			<liferay-util:param name="contractEntryId" value="<%= String.valueOf(tosContractEntry.getContractEntryId()) %>" />
			<liferay-util:param name="signatoryClassName" value="<%= User.class.getName() %>" />
			<liferay-util:param name="signatoryClassPK" value="<%= String.valueOf(themeDisplay.getUserId()) %>" />
			<liferay-util:param name="title" value="<%= ContractEntryConstants.getTypeLabel(ContractEntryConstants.TYPE_TERMS_OF_SERVICE) %>" />
		</liferay-util:include>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/marketplace/error.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>