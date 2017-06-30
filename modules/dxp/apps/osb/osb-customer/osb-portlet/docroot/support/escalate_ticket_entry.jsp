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
int visibility = VisibilityConstants.WORKERS;

if (liferayIncOrg) {
	visibility = VisibilityConstants.LIFERAY_INC;
}
%>

<aui:form name="fm">
	<div class="unit">
		<div class="unit-content">
			<h1 class="section-heading">
				<liferay-ui:message key="escalation-details" />
			</h1>

			<div class="callout-a">
				<div class="callout-content">
					<textarea id="<portlet:namespace />addCommentBody" name="<portlet:namespace />addCommentBody" style="height: 250px; width: 750px;" wrap="soft">
<%= SupportUtil.getEscalationDetails() %></textarea>
				</div>
			</div>

			<div>
				<input class="aui-button-input" onClick="<portlet:namespace />escalateTicket();" type="button" value="<liferay-ui:message key="escalate" />" />

				<input class="aui-button-input" onClick="window.close();" type="button" value="<liferay-ui:message key="cancel" />" />
			</div>
		</div>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />escalateTicket() {
		var A = AUI();

		var addCommentBodyElement = A.one('#<portlet:namespace />addCommentBody');

		opener.<portlet:namespace />escalateTicket(addCommentBodyElement.val(), '<%= visibility %>');

		window.close();
	}
</aui:script>