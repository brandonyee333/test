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

long accountEnvironmentId = ParamUtil.getLong(request, "accountEnvironmentId");
int accountEnvironmentAttachmentType = ParamUtil.getInteger(request, "accountEnvironmentAttachmentType");
boolean confirm = ParamUtil.getBoolean(request, "confirm");
boolean edit = ParamUtil.getBoolean(request, "edit");
String fieldName = ParamUtil.getString(request, "fieldName");
long kBaseArticleId = ParamUtil.getLong(request, "kBaseArticleId");
String label = ParamUtil.getString(request, "label");
boolean required = ParamUtil.getBoolean(request, "required");
long ticketAttachmentId = ParamUtil.getLong(request, "ticketAttachmentId");
int ticketAttachmentType = ParamUtil.getInteger(request, "ticketAttachmentType");
boolean translate = ParamUtil.getBoolean(request, "translate", true);

if (Validator.isNull(fieldName)) {
	fieldName = TicketAttachmentConstants.getTypeString(ticketAttachmentType);
}

AccountEnvironmentAttachment accountEnvironmentAttachment = null;
TicketAttachment ticketAttachment = null;

LiferayPortletURL attachmentResourceURL = null;
String attachmentFileName = StringPool.BLANK;
long attachmentFileSize = 0;

if (ticketAttachmentId > 0) {
	ticketAttachment = TicketAttachmentServiceUtil.getTicketAttachment(ticketAttachmentId);

	attachmentFileName = ticketAttachment.getFileName();
	attachmentFileSize = ticketAttachment.getFileSize();

	if (ticketAttachment.fileExists()) {
		attachmentResourceURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

		attachmentResourceURL.setCopyCurrentRenderParameters(false);
		attachmentResourceURL.setParameter("ticketAttachmentId", String.valueOf(ticketAttachmentId));
		attachmentResourceURL.setResourceID("ticketAttachment");
	}
}
else if ((ticketEntry != null) && (ticketAttachmentType > 0)) {
	ticketAttachment = TicketAttachmentLocalServiceUtil.fetchTicketAttachment(ticketEntry.getTicketEntryId(), ticketAttachmentType);

	if (ticketAttachment != null) {
		attachmentFileName = ticketAttachment.getFileName();
		attachmentFileSize = ticketAttachment.getFileSize();

		if (ticketAttachment.fileExists()) {
			attachmentResourceURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

			attachmentResourceURL.setCopyCurrentRenderParameters(false);
			attachmentResourceURL.setParameter("ticketAttachmentId", String.valueOf(ticketAttachment.getTicketAttachmentId()));
			attachmentResourceURL.setResourceID("ticketAttachment");
		}
	}
}
else if ((accountEnvironmentId > 0) && (accountEnvironmentAttachmentType > 0)) {
	accountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironmentId, accountEnvironmentAttachmentType);

	if (accountEnvironmentAttachment != null) {
		attachmentFileName = accountEnvironmentAttachment.getFileName();
		attachmentFileSize = accountEnvironmentAttachment.getFileSize();

		if (accountEnvironmentAttachment.fileExists()) {
			attachmentResourceURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

			attachmentResourceURL.setCopyCurrentRenderParameters(false);
			attachmentResourceURL.setParameter("accountEnvironmentAttachmentId", String.valueOf(accountEnvironmentAttachment.getAccountEnvironmentAttachmentId()));
			attachmentResourceURL.setResourceID("accountEnvironmentAttachment");
		}
	}
}
%>

<div class="<%= ((ticketEntry == null) || edit) ? "attachment-text" : "" %> fl">
	<span class="fl txt-b txt-up">
		<c:if test="<%= required && edit %>">
			*
		</c:if>

		<c:choose>
			<c:when test="<%= translate %>">
				<liferay-ui:message key="<%= HtmlUtil.escape(label) %>" />
			</c:when>
			<c:otherwise>
				<%= HtmlUtil.escape(label) %>
			</c:otherwise>
		</c:choose>

		<c:if test="<%= kBaseArticleId > 0 %>">
			<a class="help-link" href="/group/customer/kbase/-/knowledge_base/article/<%= kBaseArticleId %>" target="_blank"><img src="<%= themeDisplay.getPathThemeImages() + "/common/help.png" %>" /></a>
		</c:if>

		:
	</span>

	<input id="<portlet:namespace /><%= HtmlUtil.escapeAttribute(fieldName) %>TicketAttachmentId" name="<portlet:namespace /><%= HtmlUtil.escapeAttribute(fieldName) %>TicketAttachmentId" type="hidden" value="<%= ticketAttachmentId %>" />

	<ul class="file-list" id="<portlet:namespace /><%= HtmlUtil.escapeAttribute(fieldName) %>FileList">
		<li class="file upload-file" id="<portlet:namespace /><%= HtmlUtil.escapeAttribute(fieldName) %>File">
			<c:if test="<%= (accountEnvironmentAttachment != null) || (ticketAttachment != null) %>">
				<c:choose>
					<c:when test="<%= attachmentResourceURL != null %>">
						<span class="title"><a href="<%= attachmentResourceURL.toString() %>" target="_blank"><%= HtmlUtil.escape(attachmentFileName) %></a> (<%= TextFormatter.formatKB((double)attachmentFileSize, locale) %>k)</span>
					</c:when>
					<c:otherwise>
						<span class="portlet-msg-error" style="display: inline;"><liferay-ui:message arguments="<%= HtmlUtil.escape(attachmentFileName) %>" key='<%= (edit && (accountEnvironmentAttachment != null) && (ticketEntry == null)) ? "file-x-is-missing-and-will-not-be-added-to-the-ticket-please-replace-this-file" : "file-x-is-missing-please-replace-it" %>' /></span>
					</c:otherwise>
				</c:choose>
			</c:if>
		</li>
	</ul>
</div>

<div class="fr">
	<c:choose>
		<c:when test="<%= ticketEntry == null %>">
			<c:if test="<%= confirm %>">
				<div class="attachment-text fl">
					*<liferay-ui:message arguments="<%= LanguageUtil.get(locale, TicketAttachmentConstants.getTypeLabel(ticketAttachmentType)) %>" key="this-x-is-current" />

					<input <%= ((accountEnvironmentAttachment == null) && (ticketAttachment == null)) ? "disabled" : "" %> id="<portlet:namespace /><%= HtmlUtil.escapeAttribute(fieldName) %>Checkbox" name="<portlet:namespace /><%= HtmlUtil.escapeAttribute(fieldName) %>Checkbox" type="checkbox" />
				</div>
			</c:if>

			<div class="buttons fr">
				<input class="upload" id="<portlet:namespace /><%= HtmlUtil.escapeAttribute(fieldName) %>" name="<portlet:namespace /><%= HtmlUtil.escapeAttribute(fieldName) %>" onChange="<portlet:namespace />uploadUpdate('<%= HtmlUtil.escapeAttribute(fieldName) %>');" type="file" />

				<a class="btn" href="javascript:;" id="<portlet:namespace />select<%= HtmlUtil.escapeAttribute(fieldName) %>File"><liferay-ui:message key="upload" /></a>
			</div>
		</c:when>
		<c:when test="<%= edit %>">
			<input id="<portlet:namespace /><%= HtmlUtil.escapeAttribute(fieldName) %>" name="<portlet:namespace /><%= HtmlUtil.escapeAttribute(fieldName) %>" onChange="<portlet:namespace />uploadUpdate('<%= HtmlUtil.escapeAttribute(fieldName) %>');" style="display: none;" type="file" />

			<input class="btn" onClick="javascript:document.getElementById('<portlet:namespace /><%= HtmlUtil.escapeAttribute(fieldName) %>').click(); return;" type="button" value="<liferay-ui:message key="upload-new" />" />
		</c:when>
		<c:when test="<%= (ticketAttachment != null) && confirm %>">
			<liferay-ui:message arguments="<%= longDateFormatDateTime.format(ticketAttachment.getCreateDate()) %>" key="confirmed-on-x" />
		</c:when>
	</c:choose>
</div>

<c:if test='<%= !GetterUtil.getBoolean(request.getAttribute("ticket_attachment_upload.jsp-javaScriptIncluded")) %>'>

	<%
	request.setAttribute("ticket_attachment_upload.jsp-javaScriptIncluded", Boolean.TRUE.toString());
	%>

	<%@ include file="/support/common/javascript/ticket_attachment_upload_js.jspf" %>
</c:if>