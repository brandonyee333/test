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

<aui:form name="fm">
	<div class="unit">
		<h2 class="section-heading">
			<liferay-ui:message key="resolution" />
		</h2>

		<div>
			<aui:select name="resolution">
				<aui:option value="" />

				<%
				List<ListType> resolutionTypes = ListTypeServiceUtil.getListTypes(TicketEntryConstants.LIST_TYPE_RESOLUTION);

				for (ListType resolutionType : resolutionTypes) {
				%>

					<aui:option label="<%= resolutionType.getName() %>" value="<%= resolutionType.getListTypeId() %>" />

				<%
				}
				%>

			</aui:select>
		</div>

		<h2 class="section-heading">
			<liferay-ui:message key="comment" />
		</h2>

		<div>
			<aui:input name="addCommentBody" style="height: 250px; width: 700px;" type="textarea" wrap="soft" />
		</div>

		<div>
			<aui:button cssClass="aui-button-input" onClick='<%= renderResponse.getNamespace() + "closeTicket();" %>' value="close-ticket" />

			<aui:button cssClass="aui-button-input" onClick="window.close();" type="button" value="cancel" />
		</div>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />closeTicket() {
		opener.<portlet:namespace />closeTicket(document.<portlet:namespace />fm.<portlet:namespace />resolution.value, document.<portlet:namespace />fm.<portlet:namespace />addCommentBody.value);

		window.close();
	}
</aui:script>
