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

<div class="cleared section">
	<div class="fr">
		<a class="btn" href="javascript:history.go(-1);">
			&lt; <liferay-ui:message key="back-to-previous-page" />
		</a>
	</div>
</div>

<liferay-ui:error exception="<%= NoSuchAccountEntryException.class %>" message="the-project-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchTicketEntryException.class %>" message="the-support-ticket-could-not-be-found" />