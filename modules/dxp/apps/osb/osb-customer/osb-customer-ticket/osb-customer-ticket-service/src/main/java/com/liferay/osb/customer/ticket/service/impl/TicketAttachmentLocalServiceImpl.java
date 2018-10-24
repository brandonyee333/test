/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.ticket.service.impl;

import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.osb.customer.ticket.repository.FileRepositoryWebService;
import com.liferay.osb.customer.ticket.service.base.TicketAttachmentLocalServiceBaseImpl;
import com.liferay.osb.customer.zendesk.model.ZendeskTicket;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketCommentWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskTicketWebService;
import com.liferay.osb.customer.zendesk.web.service.exception.NoSuchZendeskOrganizationException;
import com.liferay.osb.customer.zendesk.web.service.exception.NoSuchZendeskTicketException;
import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Date;
import java.util.List;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class TicketAttachmentLocalServiceImpl
	extends TicketAttachmentLocalServiceBaseImpl {

	public TicketAttachment addTicketAttachment(
			long userId, long accountEntryId, long zendeskTicketId,
			String fileRepositoryId, String fileName, long fileSize, int type,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(accountEntryId, zendeskTicketId);

		long ticketAttachmentId = counterLocalService.increment();

		TicketAttachment ticketAttachment = ticketAttachmentPersistence.create(
			ticketAttachmentId);

		ticketAttachment.setUserId(user.getUserId());
		ticketAttachment.setUserName(user.getFullName());
		ticketAttachment.setCreateDate(new Date());
		ticketAttachment.setAccountEntryId(accountEntryId);
		ticketAttachment.setZendeskTicketId(zendeskTicketId);
		ticketAttachment.setFileRepositoryId(fileRepositoryId);
		ticketAttachment.setFileName(fileName);
		ticketAttachment.setFileSize(fileSize);
		ticketAttachment.setType(type);

		ticketAttachmentPersistence.update(ticketAttachment);

		_fileRepositoryWebService.updateFile(
			fileRepositoryId, zendeskTicketId, fileName,
			ticketAttachment.getFilePath());

		long zendeskUserId = _zendeskMapperUtil.getZendeskUserId(
			user.getUserId());

		String zendeskTicketCommentBody = buildZendeskTicketCommentBody(
			ticketAttachment, serviceContext);

		_zendeskTicketCommentWebService.addZendeskTicketComment(
			zendeskTicketId, zendeskUserId, zendeskTicketCommentBody);

		return ticketAttachment;
	}

	public void deleteTicketAttachments(long zendeskTicketId, int[] types)
		throws PortalException {

		List<TicketAttachment> ticketAttachments =
			ticketAttachmentPersistence.findByZTI_T(zendeskTicketId, types);

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			_fileRepositoryWebService.deleteFile(
				ticketAttachment.getFileRepositoryId(),
				ticketAttachment.getFilePath());

			ticketAttachmentPersistence.remove(ticketAttachment);
		}
	}

	protected String buildZendeskTicketCommentBody(
			TicketAttachment ticketAttachment, ServiceContext serviceContext)
		throws PortalException {

		StringBundler sb = new StringBundler(7);

		String comment = (String)serviceContext.getAttribute("comment");

		if (Validator.isNotNull(comment)) {
			sb.append(comment.replace(StringPool.NEW_LINE, "<br />"));
			sb.append("<br /><br />");
		}

		sb.append("<a href=\"");

		HttpServletRequest request = serviceContext.getRequest();

		if (request == null) {
			return StringPool.BLANK;
		}

		String portletId =
			"com_liferay_osb_customer_account_entry_details_web_" +
				"AccountEntryDetailsPortlet";

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long plid = PortalUtil.getPlidFromPortletId(
			OSBConstants.GROUP_CUSTOMER_ID, portletId);

		if (themeDisplay == null) {
			themeDisplay = new ThemeDisplay();

			Layout layout = _layoutLocalService.getLayout(plid);

			themeDisplay.setLayout(layout);
			themeDisplay.setLayoutSet(layout.getLayoutSet());
			themeDisplay.setSiteGroupId(layout.getGroupId());

			request.setAttribute(WebKeys.THEME_DISPLAY, themeDisplay);
		}

		LiferayPortletURL resourceURL = PortletURLFactoryUtil.create(
			request, portletId, plid, PortletRequest.RESOURCE_PHASE);

		resourceURL.setCopyCurrentRenderParameters(false);
		resourceURL.setParameter(
			"ticketAttachmentId",
			String.valueOf(ticketAttachment.getTicketAttachmentId()));
		resourceURL.setResourceID("/ticket_attachment");

		sb.append(resourceURL.toString());

		sb.append("\">");
		sb.append(ticketAttachment.getFileName());
		sb.append("</a>");

		return sb.toString();
	}

	protected void validate(long accountEntryId, long zendeskTicketId)
		throws PortalException {

		if (accountEntryId <= 0) {
			throw new NoSuchAccountEntryException();
		}

		long zendeskOrganizationId =
			_zendeskMapperUtil.fetchZendeskOrganizationId(accountEntryId);

		if (zendeskOrganizationId == 0) {
			throw new NoSuchZendeskOrganizationException();
		}

		ZendeskTicket zendeskTicket = _zendeskTicketWebService.getZendeskTicket(
			zendeskTicketId);

		if ((zendeskTicketId <= 0) ||
			(zendeskOrganizationId !=
				zendeskTicket.getZendeskOrganizationId())) {

			throw new NoSuchZendeskTicketException();
		}
	}

	@ServiceReference(type = FileRepositoryWebService.class)
	private FileRepositoryWebService _fileRepositoryWebService;

	@ServiceReference(type = LayoutLocalService.class)
	private LayoutLocalService _layoutLocalService;

	@ServiceReference(type = ZendeskMapperUtil.class)
	private ZendeskMapperUtil _zendeskMapperUtil;

	@ServiceReference(type = ZendeskTicketCommentWebService.class)
	private ZendeskTicketCommentWebService _zendeskTicketCommentWebService;

	@ServiceReference(type = ZendeskTicketWebService.class)
	private ZendeskTicketWebService _zendeskTicketWebService;

}