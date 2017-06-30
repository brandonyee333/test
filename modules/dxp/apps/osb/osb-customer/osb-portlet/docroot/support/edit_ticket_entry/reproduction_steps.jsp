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

<%@ include file="/support/init.jsp" %>

<%
long ticketEntryId = ParamUtil.getLong(request, "ticketEntryId");

TicketEntry ticketEntry = TicketEntryServiceUtil.getTicketEntry(ticketEntryId);
%>

<c:if test="<%= liferayIncOrg %>">
	<script type="text/javascript">
		function <portlet:namespace />updateReproductionSteps() {
			var reproductionSteps = document.getElementById("<portlet:namespace />reproductionSteps");

			opener.<portlet:namespace />updateReproductionSteps(reproductionSteps.value);

			window.close();
		}
	</script>

	<div class="unit">
		<div class="unit-content">
			<h1 class="section-heading">
				<liferay-ui:message key="reproduction-steps" />
			</h1>

			<div class="callout-a">
				<div class="callout-content">
					<textarea id="<portlet:namespace />reproductionSteps" style="height: 250px; width: 750px;" wrap="soft"><%= HtmlUtil.escape(ticketEntry.getReproductionSteps()) %></textarea>
				</div>
			</div>

			<div>
				<input class="aui-button-input" onClick="<portlet:namespace />updateReproductionSteps();" type="button" value="<liferay-ui:message key="save" />" />

				<input class="aui-button-input" onClick="opener.<portlet:namespace />resetStatus(); window.close();" type="button" value="<liferay-ui:message key="cancel" />" />
			</div>
		</div>
	</div>
</c:if>