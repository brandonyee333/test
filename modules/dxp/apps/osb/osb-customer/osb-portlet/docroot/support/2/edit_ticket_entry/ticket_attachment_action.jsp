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

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

TicketAttachment ticketAttachment = (TicketAttachment)row.getObject();

TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

PortletURL portletURL = (PortletURL)request.getAttribute("edit_ticket_entry.jsp-portletURL");
%>

<c:if test="<%= OSBTicketAttachmentPermission.contains(permissionChecker, ticketAttachment, OSBActionKeys.VIEW) %>">

	<%
	LiferayPortletURL ticketAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

	ticketAttachmentURL.setCopyCurrentRenderParameters(false);
	ticketAttachmentURL.setParameter("ticketAttachmentId", String.valueOf(ticketAttachment.getTicketAttachmentId()));
	ticketAttachmentURL.setResourceID("ticketAttachment");
	%>

	<liferay-ui:icon-menu>
		<c:choose>
			<c:when test="<%= ArrayUtil.contains(TicketAttachmentConstants.TYPES_LARGE, ticketAttachment.getType()) %>">
				<c:if test="<%= (ticketEntry.getStatus() == TicketEntryConstants.STATUS_CLOSED) && OSBTicketAttachmentPermission.contains(permissionChecker, ticketAttachment, OSBActionKeys.DELETE) %>">
					<portlet:actionURL name="extendTicketAttachmentDeleteDate" var="extendDeleteDateURL">
						<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
						<portlet:param name="ticketAttachmentId" value="<%= String.valueOf(ticketAttachment.getTicketAttachmentId()) %>" />
					</portlet:actionURL>

					<liferay-ui:icon
						message="extend-delete-date"
						url="<%= extendDeleteDateURL.toString() %>"
					/>
				</c:if>

				<%
				FileRepository fileRepository = SupportUtil.getFirstActiveFileRepository(ticketAttachment.getAvailableFileRepositoryIdsSet());

				String taglibOnClick = "alert('" + UnicodeLanguageUtil.get(request, "file-servers-not-available-please-contact-your-support-manager") + "');";
				%>

				<c:if test="<%= OSBTicketAttachmentPermission.contains(permissionChecker, ticketAttachment, OSBActionKeys.DELETE) %>">
					<portlet:actionURL name="deleteTicketAttachment" var="deleteURL">
						<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
						<portlet:param name="ticketAttachmentId" value="<%= String.valueOf(ticketAttachment.getTicketAttachmentId()) %>" />
					</portlet:actionURL>

					<c:choose>
						<c:when test="<%= fileRepository == null %>">
							<liferay-ui:icon
								message="delete"
								onClick="<%= taglibOnClick %>"
								url="javascript:;"
							/>
						</c:when>
						<c:otherwise>
							<liferay-ui:icon-delete
								label="<%= true %>"
								url="<%= deleteURL %>"
							/>
						</c:otherwise>
					</c:choose>
				</c:if>

				<c:choose>
					<c:when test="<%= !ticketAttachment.getReplicate() %>">
						<c:choose>
							<c:when test="<%= fileRepository == null %>">
								<liferay-ui:icon
									message="download"
									onClick="<%= taglibOnClick %>"
									url="javascript:;"
								/>
							</c:when>
							<c:otherwise>
								<liferay-ui:icon
									message="download"
									url="<%= ticketAttachmentURL.toString() %>"
								/>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<portlet:renderURL var="downloadTicketAttachmentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
							<portlet:param name="mvcPath" value="/support/2/download_ticket_attachment.jsp" />
							<portlet:param name="ticketAttachmentId" value="<%= String.valueOf(ticketAttachment.getTicketAttachmentId()) %>" />
						</portlet:renderURL>

						<%
						StringBundler sb = new StringBundler(5);

						sb.append("javascript:");
						sb.append("var downloadWindow = window.open('");
						sb.append(downloadTicketAttachmentURL);
						sb.append("', 'download', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=800'); ");
						sb.append("downloadWindow.focus();");
						%>

						<liferay-ui:icon
							message="download"
							url="<%= sb.toString() %>"
						/>
					</c:otherwise>
				</c:choose>

				<c:if test="<%= (ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) && !ticketAttachment.getReplicate() && OSBTicketAttachmentPermission.contains(permissionChecker, ticketAttachment, OSBActionKeys.UPDATE) %>">
					<portlet:actionURL name="replicateTicketAttachment" var="replicateTicketAttachmentURL">
						<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
						<portlet:param name="ticketAttachmentId" value="<%= String.valueOf(ticketAttachment.getTicketAttachmentId()) %>" />
					</portlet:actionURL>

					<c:choose>
						<c:when test="<%= fileRepository == null %>">
							<liferay-ui:icon
								message="copy"
								onClick="<%= taglibOnClick %>"
								url="javascript:;"
							/>
						</c:when>
						<c:otherwise>
							<liferay-ui:icon
								message="replicate"
								url="<%= replicateTicketAttachmentURL.toString() %>"
							/>
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:when>
			<c:otherwise>
				<c:if test="<%= OSBTicketAttachmentPermission.contains(permissionChecker, ticketAttachment, OSBActionKeys.DELETE) %>">
					<portlet:actionURL name="deleteTicketAttachment" var="deleteURL">
						<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
						<portlet:param name="ticketAttachmentId" value="<%= String.valueOf(ticketAttachment.getTicketAttachmentId()) %>" />
					</portlet:actionURL>

					<liferay-ui:icon-delete
						label="<%= true %>"
						url="<%= deleteURL %>"
					/>
				</c:if>

				<liferay-ui:icon
					message="download"
					url="<%= ticketAttachmentURL.toString() %>"
				/>

				<c:if test="<%= SupportUtil.hasAttachmentPreview(ticketAttachment.getFileName()) %>">

					<%
					ticketAttachmentURL.setParameter("preview", Boolean.TRUE.toString());
					%>

					<liferay-ui:icon
						message="preview"
						target="_blank"
						url="<%= ticketAttachmentURL.toString() %>"
					/>
				</c:if>
			</c:otherwise>
		</c:choose>
	</liferay-ui:icon-menu>
</c:if>