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

int[] userVisibilities = null;

if (screenShareMode) {
	userVisibilities = new int[] {VisibilityConstants.PUBLIC};
}
else {
	userVisibilities = TicketEntryLocalServiceUtil.getUserVisibilities(user.getUserId(), ticketEntry.getTicketEntryId());
}

List<TicketAttachment> ticketAttachments = TicketAttachmentLocalServiceUtil.getTicketAttachments(ticketEntry.getTicketEntryId(), TicketAttachmentConstants.TYPES, userVisibilities);
%>

<c:if test="<%= !ticketAttachments.isEmpty() %>">
	<table class="lfr-table">
		<tr>
			<td>
				<strong><liferay-ui:message key="attachments" />:</strong>
			</td>
			<td class="stretch">

				<%
				for (int i = 0; i < ticketAttachments.size(); i++) {
					TicketAttachment ticketAttachment = ticketAttachments.get(i);

					String fileName = ticketAttachment.getFileName();
					long fileSize = ticketAttachment.getFileSize();
				%>

					<div class="attachment cleared <%= (ticketAttachment.getVisibility() != VisibilityConstants.PUBLIC) ? "visibility-" + ticketAttachment.getVisibilityLabel() : "" %>">
						<div class="fl">

							<%
							LiferayPortletURL ticketAttachmentURL = null;

							if (OSBTicketAttachmentPermission.contains(permissionChecker, ticketAttachment, OSBActionKeys.VIEW)) {
								ticketAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

								ticketAttachmentURL.setCopyCurrentRenderParameters(false);
								ticketAttachmentURL.setParameter("ticketAttachmentId", String.valueOf(ticketAttachment.getTicketAttachmentId()));
								ticketAttachmentURL.setResourceID("ticketAttachment");
							}
							%>

							<c:if test="<%= ticketAttachmentURL != null %>">
								<a href="<%= ticketAttachmentURL.toString() %>" target="_blank">
							</c:if>

							<%= fileName %>

							<c:if test="<%= ticketAttachmentURL != null %>">
								</a>
							</c:if>

							(<%= TextFormatter.formatKB((double)fileSize, locale) %>k)

							<c:if test="<%= (ticketAttachmentURL != null) && SupportUtil.hasAttachmentPreview(fileName) %>">

								<%
								ticketAttachmentURL.setParameter("preview", String.valueOf(Boolean.TRUE));
								%>

								<span>
									<liferay-ui:icon
										image="preview"
										target="_blank"
										url="<%= ticketAttachmentURL.toString() %>"
									/>
								</span>
							</c:if>

							<c:if test="<%= (ticketAttachment.getType() == TicketAttachmentConstants.TYPE_HOTFIX) && (ticketAttachment.getReleaseNotesId() > 0) %>">

								<%
								ReleaseNotes releaseNotes = ReleaseNotesLocalServiceUtil.fetchReleaseNotes(ticketAttachment.getReleaseNotesId());
								%>

								<c:if test="<%= releaseNotes != null %>">

									<%
									LiferayPortletURL viewReleaseNotesURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.RN_RELEASE_NOTES, themeDisplay.getPlid(), PortletRequest.RESOURCE_PHASE);

									viewReleaseNotesURL.setCopyCurrentRenderParameters(false);
									viewReleaseNotesURL.setParameter("uuid", releaseNotes.getUuid());
									%>

									<liferay-ui:icon
										image="view_articles"
										message="view-release-notes"
										target="_blank"
										url="<%= viewReleaseNotesURL.toString() %>"
									/>
								</c:if>
							</c:if>
						</div>

						<div class="fr">
							<%= HtmlUtil.escape(ticketAttachment.getUserName()) %> on <%= fullDateFormatDateTime.format(ticketAttachment.getCreateDate()) %>

							<c:if test="<%= ticketAttachment.getVisibility() != VisibilityConstants.PUBLIC %>">
								<span class="footnote"><%= LanguageUtil.format(pageContext, "visible-to-x", ticketAttachment.getVisibilityLabel()) %></span>
							</c:if>
						</div>

						<%= (i < (ticketAttachments.size() - 1)) ? "<br />" : "" %>
					</div>

				<%
				}
				%>

				<c:if test="<%= !screenShareMode && liferayIncOrg %>">
					<portlet:resourceURL id="ticketAttachmentsZipFile" var="ticketAttachmentsZipFileURL">
						<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
					</portlet:resourceURL>

					<div class="fr download-all">
						<a href="<%= ticketAttachmentsZipFileURL %>"><liferay-ui:message key="download-all" /></a>
					</div>
				</c:if>
			</td>
		</tr>
	</table>
</c:if>

<%
List<TicketAttachment> largeTicketAttachments = TicketAttachmentLocalServiceUtil.getTicketAttachments(ticketEntry.getTicketEntryId(), TicketAttachmentConstants.TYPES_LARGE, userVisibilities);
%>

<c:if test="<%= !largeTicketAttachments.isEmpty() %>">
	<table class="lfr-table">
	<tr>
		<td>
			<strong><liferay-ui:message key="large-attachments" />:</strong>
		</td>
		<td class="stretch">

			<%
			for (int i = 0; i < largeTicketAttachments.size(); i++) {
				TicketAttachment ticketAttachment = largeTicketAttachments.get(i);

				String fileName = ticketAttachment.getFileName();
				long fileSize = ticketAttachment.getFileSize();

				boolean hasViewPermission = OSBTicketAttachmentPermission.contains(permissionChecker, ticketAttachment, OSBActionKeys.VIEW);
			%>

				<div class="attachment cleared <%= (ticketAttachment.getVisibility() != VisibilityConstants.PUBLIC) ? "visibility-" + ticketAttachment.getVisibilityLabel() : "" %>">
					<div class="fl">

						<%
						LiferayPortletURL ticketAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

						ticketAttachmentURL.setCopyCurrentRenderParameters(false);
						ticketAttachmentURL.setParameter("ticketAttachmentId", String.valueOf(ticketAttachment.getTicketAttachmentId()));
						ticketAttachmentURL.setResourceID("ticketAttachment");
						%>

						<c:if test="<%= hasViewPermission %>">
							<a href="<%= ticketAttachmentURL.toString() %>" target="_blank">
						</c:if>

						<%= fileName %>

						<c:if test="<%= hasViewPermission %>">
							</a>
						</c:if>

						(<%= TextFormatter.formatKB((double)fileSize, locale) %>k)
					</div>

					<div class="fr">
						<%= HtmlUtil.escape(ticketAttachment.getUserName()) %> on <%= fullDateFormatDateTime.format(ticketAttachment.getCreateDate()) %>

						<c:if test="<%= ticketAttachment.getVisibility() != VisibilityConstants.PUBLIC %>">
							<span class="footnote"><%= LanguageUtil.format(pageContext, "visible-to-x", ticketAttachment.getVisibilityLabel()) %></span>
						</c:if>
					</div>

					<%= (i < (ticketAttachments.size() - 1)) ? "<br />" : "" %>
				</div>

			<%
			}
			%>

		</td>
	</tr>
	</table>
</c:if>

<%
List<TicketLink> ticketLinks = TicketLinkLocalServiceUtil.getTicketLinks(ticketEntry.getTicketEntryId(), userVisibilities);
%>

<c:if test="<%= !ticketLinks.isEmpty() %>">
	<table class="lfr-table">
		<tr>
			<td>
				<strong><liferay-ui:message key="links" />:</strong>
			</td>
			<td class="stretch">

				<%
				for (TicketLink ticketLink : ticketLinks) {
				%>

					<div class="<%= (ticketLink.getVisibility() != VisibilityConstants.PUBLIC) ? "visibility-" + ticketLink.getVisibilityLabel() : "" %>">
						<a href="<%= ticketLink.getUrl() %>" target="_blank"><%= StringUtil.shorten(ticketLink.getUrl(), 115) %></a>

						<c:if test="<%= ticketLink.getVisibility() != VisibilityConstants.PUBLIC %>">
							<span class="footnote"><%= LanguageUtil.format(pageContext, "visible-to-x", ticketLink.getVisibilityLabel()) %></span>
						</c:if>
					</div>

				<%
				}
				%>

			</td>
		</tr>
	</table>

	<br />
</c:if>