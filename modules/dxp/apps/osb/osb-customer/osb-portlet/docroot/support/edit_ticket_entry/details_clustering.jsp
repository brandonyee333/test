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
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

boolean edit = (Boolean)request.getAttribute("edit_ticket_entry.jsp-edit");
boolean hasUpdateAdvanced = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateAdvanced");
OfferingEntry offeringEntry = (OfferingEntry)request.getAttribute("edit_ticket_entry.jsp-offeringEntry");

Map<Long, String> ticketInformationFieldsMap = ticketEntry.getTicketInformationFieldsMap();

int numberOfNodes = ParamUtil.getInteger(request, "numberOfNodes", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_CLUSTER_NUMBER_OF_NODES)));
int serverCommunicationType = ParamUtil.getInteger(request, "serverCommunicationType", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_CLUSTER_SERVER_COMMUNICATION_TYPE)));
String serverConfigurations = ParamUtil.getString(request, "serverConfigurations", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_SERVER_CONFIGURATIONS)));
%>

<h2 class="section-heading">
	<liferay-ui:message key="clustered-environment" />
</h2>

<liferay-util:include page="/support/common/details_environment.jsp" servletContext="<%= application %>">
	<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
	<liferay-util:param name="offeringEntryId" value="<%= String.valueOf(offeringEntry.getOfferingEntryId()) %>" />
</liferay-util:include>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-w50 content-column">
			<div class="content-column-content">
				<span class="txt-b txt-up"><%= edit ? "*" : "" %><liferay-ui:message key="server-communication-type" />:</span>

				<c:choose>
					<c:when test="<%= edit && hasUpdateAdvanced %>">
						<select id="<portlet:namespace />serverCommunicationType" name="<portlet:namespace />serverCommunicationType">
							<option value=""></option>

							<%
							for (int curServerCommunicationType : TicketEntryConstants.CLUSTER_SERVER_COMMUNICATION_TYPES) {
							%>

								<option <%= (serverCommunicationType == curServerCommunicationType) ? "selected" : StringPool.BLANK %> value="<%= curServerCommunicationType %>"><liferay-ui:message key="<%= TicketEntryConstants.getClusterServerCommunicationTypeLabel(curServerCommunicationType) %>" /></option>

							<%
							}
							%>

						</select>
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="<%= TicketEntryConstants.getClusterServerCommunicationTypeLabel(serverCommunicationType) %>" />

						<input name="<portlet:namespace />serverCommunicationType" type="hidden" value="<%= serverCommunicationType %>" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>

		<div class="aui-w50 content-column">
			<div class="content-column-content">
				<span class="txt-b txt-up"><liferay-ui:message key="number-of-nodes" />:</span>

				<c:choose>
					<c:when test="<%= edit && hasUpdateAdvanced %>">
						<select id="<portlet:namespace />numberOfNodes" name="<portlet:namespace />numberOfNodes">
							<option value="0"></option>
							<option <%= (numberOfNodes == 1) ? "selected" : StringPool.BLANK %> value="1">1</option>
							<option <%= (numberOfNodes == 2) ? "selected" : StringPool.BLANK %> value="2">2</option>
							<option <%= (numberOfNodes == 3) ? "selected" : StringPool.BLANK %> value="3">3</option>
							<option <%= (numberOfNodes == 4) ? "selected" : StringPool.BLANK %> value="4">4</option>
							<option <%= (numberOfNodes == 5) ? "selected" : StringPool.BLANK %> value="5">5</option>
							<option <%= (numberOfNodes == 6) ? "selected" : StringPool.BLANK %> value="6">6</option>
							<option <%= (numberOfNodes == 7) ? "selected" : StringPool.BLANK %> value="7">7</option>
							<option <%= (numberOfNodes >= 8) ? "selected" : StringPool.BLANK %> value="8">8+</option>
						</select>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="<%= numberOfNodes < 8 %>">
								<%= (numberOfNodes > 1) ? numberOfNodes : StringPool.BLANK %>
							</c:when>
							<c:otherwise>
								<%= numberOfNodes %>+
							</c:otherwise>
						</c:choose>

						<input name="<portlet:namespace />numberOfNodes" type="hidden" value="<%= numberOfNodes %>" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<span class="fl txt-b"><liferay-ui:message key="please-provide-any-jvm-arguments-or-settings-used-to-set-up-each-node" />:</span>

		<br />

		<c:choose>
			<c:when test="<%= edit && hasUpdateAdvanced %>">
				<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
					<liferay-util:param name="content" value="<%= serverConfigurations %>" />
					<liferay-util:param name="editorId" value="serverConfigurations" />
					<liferay-util:param name="height" value="300" />
					<liferay-util:param name="name" value="serverConfigurations" />
					<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
				</liferay-util:include>
			</c:when>
			<c:otherwise>
				<pre><%= SupportUtil.getHTML(serverConfigurations) %></pre>

				<input name="<portlet:namespace />serverConfigurations" type="hidden" value="<%= HtmlUtil.escape(serverConfigurations) %>" />
			</c:otherwise>
		</c:choose>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
			<liferay-util:param name="kBaseArticleId" value="33142855" />
			<liferay-util:param name="label" value="portal-ext-files" />
			<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PORTAL_EXT) %>" />
		</liferay-util:include>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
			<liferay-util:param name="kBaseArticleId" value="33142925" />
			<liferay-util:param name="label" value="patching-tool-info-files" />
			<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PATCH_LEVEL) %>" />
		</liferay-util:include>
	</div>
</div>