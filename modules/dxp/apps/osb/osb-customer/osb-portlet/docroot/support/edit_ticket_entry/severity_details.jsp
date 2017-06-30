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
int severity = ParamUtil.getInteger(request, "severity");
int newSeverity = ParamUtil.getInteger(request, "newSeverity");
%>

<c:if test="<%= liferayIncOrg %>">
	<script type="text/javascript">
		function <portlet:namespace />updateSeverityDetails() {
			var severityReason = document.getElementById("<portlet:namespace />severityReason");
			var severityReasonLabel = severityReason.options[severityReason.selectedIndex].text;
			var severityReasonComments = document.getElementById("<portlet:namespace />severityReasonComments");

			opener.<portlet:namespace />updateSeverityDetails(severityReason.value, severityReasonLabel, severityReasonComments.value);

			window.close();
		}
	</script>

	<div class="unit">
		<div class="unit-content">
			<h1 class="section-heading">
				<liferay-ui:message key="severity-details" />
			</h1>

			<div class="callout-a">
				<div class="callout-content">
					<span class="txt-b"><liferay-ui:message key="what-is-the-reason-for-changing-the-ticket-severity" /></span>

					<div>
						<select id="<portlet:namespace />severityReason">
							<c:choose>
								<c:when test="<%= severity < newSeverity %>">
									<option value="customer-requesting-severity-escalation"><liferay-ui:message key="customer-requesting-severity-escalation" /></option>
									<option value="other"><liferay-ui:message key="other" /></option>
								</c:when>
								<c:otherwise>
									<option value="customer-did-not-understand-the-meaning-of-critical"><liferay-ui:message key="customer-did-not-understand-the-meaning-of-critical" /></option>
									<option value="customer-wanted-more-urgent-action"><liferay-ui:message key="customer-wanted-more-urgent-action" /></option>

									<c:choose>
										<c:when test="<%= newSeverity == SupportResponseConstants.SEVERITY_MAJOR %>">
											<option value="critical-production-down-that-was-stabilized-to-major"><liferay-ui:message key="critical-production-down-that-was-stabilized-to-major" /></option>
										</c:when>
										<c:otherwise>
											<option value="other"><liferay-ui:message key="other" /></option>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</select>
					</div>

					<br />

					<span class="txt-b"><liferay-ui:message key="please-explain-optional" /></span>

					<div>
						<textarea id="<portlet:namespace />severityReasonComments" style="height: 250px; width: 750px;" wrap="soft"></textarea>
					</div>
				</div>
			</div>

			<div>
				<input class="aui-button-input" onClick="<portlet:namespace />updateSeverityDetails();" type="button" value="<liferay-ui:message key="save" />" />

				<input class="aui-button-input" onClick="opener.<portlet:namespace />resetSeverityDetails(); window.close();" type="button" value="<liferay-ui:message key="cancel" />" />
			</div>
		</div>
	</div>
</c:if>