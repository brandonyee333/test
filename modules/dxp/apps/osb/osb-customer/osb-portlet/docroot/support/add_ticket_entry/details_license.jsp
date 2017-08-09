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
long offeringEntryId = ParamUtil.getLong(request, "offeringEntryId");

OfferingEntry offeringEntry = OfferingEntryLocalServiceUtil.getOfferingEntry(offeringEntryId);

int envLFR = ParamUtil.getInteger(request, "envLFR");
int type = ParamUtil.getInteger(request, "type");
int purpose = ParamUtil.getInteger(request, "purpose");
String serverIds = ParamUtil.getString(request, "serverIds");
String ipAddresses = ParamUtil.getString(request, "ipAddresses");
String hostNames = ParamUtil.getString(request, "hostNames");
String additionalComments = ParamUtil.getString(request, "additionalComments");
%>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<span class="txt-b">
			<liferay-ui:message key="please-attach-the-server-log-generated-during-activation-key-deployment-that-can-be-located-within-your-liferay-bundle" />
		</span>

		<br /><br />

		<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="label" value="server-log" />
			<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_SERVER_LOG) %>" />
		</liferay-util:include>
	</div>
</div>

<h2 class="section-heading steps">
	3. <liferay-ui:message key="verify-your-activation-key-details" />
</h2>

<div>
	<liferay-ui:message key="please-provide-accurate-activation-key-details-these-details-will-help-us-reproduce-your-issue-and-come-to-a-faster-resolution" />
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<span class="txt-b">
			<liferay-ui:message key="please-provide-a-screenshot-of-your-activation-key-management-page" /> <liferay-ui:icon-help message="where's-your-activation-key-management-page" />
		</span>

		<br /><br />

		<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="label" value="screenshot" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_SCREEN_SHOT) %>" />
		</liferay-util:include>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<liferay-util:include page="/support/common/eosl_environment_liferay.jsp" servletContext="<%= application %>">
			<portlet:param name="envLFR" value="<%= String.valueOf(envLFR) %>" />
		</liferay-util:include>

		<div class="aui-w33 content-column">
			<div class="content-column-content left-column">
				<span class="txt-b">* <liferay-ui:message key="type-of-key" />:</span>

				<select name="<portlet:namespace />type">
					<option value="0"></option>

					<%
					int[] types = TicketEntryConstants.getLicenseTypes();

					for (int curType : types) {
					%>

						<option <%= (curType == type) ? "selected" : "" %> value="<%= curType %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getLicenseTypeLabel(curType)) %></option>

					<%
					}
					%>

				</select>
			</div>
		</div>

		<div class="aui-w33 content-column">
			<div class="content-column-content middle-column">
				<span class="txt-b">* <liferay-ui:message key="purpose" />:</span>

				<select name="<portlet:namespace />purpose">
					<option value="0"></option>

					<%
					int[] purposes = TicketEntryConstants.getLicensePurposes();

					for (int curPurpose : purposes) {
					%>

						<option <%= (curPurpose == purpose) ? "selected" : "" %> value="<%= curPurpose %>"><%= LanguageUtil.get(pageContext, TicketEntryConstants.getLicensePurposeLabel(curPurpose)) %></option>

					<%
					}
					%>

				</select>
			</div>
		</div>

		<div class="aui-w33 content-column">
			<div class="content-column-content right-column">
				<span class="txt-b">* <liferay-ui:message key="liferay-version" />:</span>

				<select id="<portlet:namespace />envLFR" name="<portlet:namespace />envLFR" onChange="<portlet:namespace />updateSupportMessage(this.value);">
					<option value="0"></option>

					<%
					List<ListType> envLFRTypes = ListTypeServiceUtil.getListTypes(ProductEntryConstants.LIST_TYPE_PORTAL_ALL_VERSIONS);

					String previousNamePrefix = StringPool.BLANK;

					for (ListType envLFRType : envLFRTypes) {
						if ((envLFRType.getListTypeId() == ProductEntryConstants.PORTAL_VERSION_OTHER) || ArrayUtil.contains(ProductEntryConstants.LIST_TYPES_DEPRECATED, envLFRType.getListTypeId())) {
							continue;
						}

						String name = envLFRType.getName();

						String namePrefix = name.substring(0, 3);
					%>

						<c:if test="<%= Validator.isNotNull(previousNamePrefix) && !previousNamePrefix.equals(namePrefix) %>">
							<option disabled>--------</option>
						</c:if>

						<option <%= (envLFRType.getListTypeId() == envLFR) ? "selected" : "" %> value="<%= envLFRType.getListTypeId() %>"><%= LanguageUtil.get(pageContext, envLFRType.getName()) %></option>

					<%
						previousNamePrefix = namePrefix;
					}
					%>

				</select>
			</div>
		</div>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div>
			<span class="fl txt-b"><liferay-ui:message key="server-ids-mac-addresses" />:</span>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= serverIds %>" />
				<liferay-util:param name="editorId" value="serverIds" />
				<liferay-util:param name="name" value="serverIds" />
				<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
			</liferay-util:include>
		</div>

		<div>
			<span class="fl txt-b"><liferay-ui:message key="ip-addresses" />:</span>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= ipAddresses %>" />
				<liferay-util:param name="editorId" value="ipAddresses" />
				<liferay-util:param name="name" value="ipAddresses" />
				<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
			</liferay-util:include>
		</div>

		<div>
			<span class="fl txt-b"><liferay-ui:message key="host-names" />:</span>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= hostNames %>" />
				<liferay-util:param name="editorId" value="hostNames" />
				<liferay-util:param name="name" value="hostNames" />
				<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
			</liferay-util:include>
		</div>

		<div>
			<span class="fl txt-b"><liferay-ui:message key="additional-comments-unstable-server-details-multiple-jvms-etc" />:</span>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= additionalComments %>" />
				<liferay-util:param name="editorId" value="additionalComments" />
				<liferay-util:param name="height" value="300" />
				<liferay-util:param name="name" value="additionalComments" />
				<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
			</liferay-util:include>
		</div>
	</div>
</div>