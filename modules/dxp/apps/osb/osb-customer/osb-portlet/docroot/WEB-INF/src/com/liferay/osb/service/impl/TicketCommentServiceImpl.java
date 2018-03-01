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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.TicketComment;
import com.liferay.osb.model.TicketSolutionConstants;
import com.liferay.osb.service.base.TicketCommentServiceBaseImpl;
import com.liferay.osb.service.permission.OSBTicketCommentPermission;
import com.liferay.osb.service.permission.OSBTicketEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.File;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class TicketCommentServiceImpl extends TicketCommentServiceBaseImpl {

	public TicketComment addTicketComment(
			long userId, long ticketEntryId, String body, int type,
			int visibility, int status, int[] pendingTypes,
			List<ObjectValuePair<String, File>> files, List<Integer> types,
			ServiceContext serviceContext)
		throws PortalException {

		if ((files != null) && !files.isEmpty()) {
			OSBTicketEntryPermission.check(
				getPermissionChecker(), ticketEntryId,
				ActionKeys.ADD_ATTACHMENT);
		}

		serviceContext.setCreateDate(new Date());

		if ((files != null) && !files.isEmpty()) {
			ticketAttachmentLocalService.addTicketAttachments(
				userId, ticketEntryId,
				TicketSolutionConstants.DEFAULT_SOLUTION_ID, files, types,
				visibility, status, serviceContext);
		}

		return addTicketComment(
			userId, ticketEntryId, body, type, visibility, status, pendingTypes,
			serviceContext);
	}

	public TicketComment addTicketComment(
			long userId, long ticketEntryId, String body, int type,
			int visibility, int status, int[] pendingTypes,
			ServiceContext serviceContext)
		throws PortalException {

		validateAddTicketComment(ticketEntryId, status, visibility);

		return ticketCommentLocalService.addTicketComment(
			userId, ticketEntryId, body, type, visibility, status, pendingTypes,
			serviceContext);
	}

	public TicketComment deleteTicketComment(long ticketCommentId)
		throws PortalException {

		TicketComment ticketComment =
			ticketCommentLocalService.getTicketComment(ticketCommentId);

		OSBTicketCommentPermission.check(
			getPermissionChecker(), ticketComment, ActionKeys.DELETE);

		return ticketCommentLocalService.deleteTicketComment(
			getUserId(), ticketComment);
	}

	public TicketComment updateTicketComment(
			long userId, long ticketCommentId, long ticketEntryId, String body,
			int visibility, int status, int[] pendingTypes,
			List<ObjectValuePair<String, File>> files, List<Integer> types)
		throws PortalException {

		TicketComment ticketComment =
			ticketCommentLocalService.getTicketComment(ticketCommentId);

		if (ticketComment.getVisibility() != visibility) {
			throw new PrincipalException();
		}

		if ((ticketComment.getStatus() == WorkflowConstants.STATUS_DRAFT) &&
			(status == WorkflowConstants.STATUS_APPROVED)) {

			validateAddTicketComment(ticketEntryId, status, visibility);

			if ((files != null) && !files.isEmpty()) {
				OSBTicketEntryPermission.check(
					getPermissionChecker(), ticketEntryId,
					ActionKeys.ADD_ATTACHMENT);
			}
		}

		OSBTicketCommentPermission.check(
			getPermissionChecker(), ticketComment, ActionKeys.UPDATE);

		ServiceContext serviceContext = new ServiceContext();

		if (status == WorkflowConstants.STATUS_DRAFT) {
			serviceContext.setCreateDate(ticketComment.getCreateDate());
		}
		else {
			serviceContext.setCreateDate(new Date());
		}

		if ((files != null) && !files.isEmpty()) {
			ticketAttachmentLocalService.addTicketAttachments(
				userId, ticketEntryId,
				TicketSolutionConstants.DEFAULT_SOLUTION_ID, files, types,
				visibility, status, serviceContext);
		}

		return ticketCommentLocalService.updateTicketComment(
			userId, ticketCommentId, ticketEntryId, body, visibility, status,
			pendingTypes, serviceContext);
	}

	public TicketComment updateTicketCommentType(long ticketCommentId, int type)
		throws PortalException {

		OSBTicketCommentPermission.check(
			getPermissionChecker(), null, OSBActionKeys.MARK_AS_SOLUTION);

		return ticketCommentLocalService.updateTicketCommentType(
			ticketCommentId, type);
	}

	protected void validateAddTicketComment(
			long ticketEntryId, int status, int visibility)
		throws PortalException {

		if (status != WorkflowConstants.STATUS_DRAFT) {
			if (visibility == VisibilityConstants.LIFERAY_INC) {
				OSBTicketEntryPermission.check(
					getPermissionChecker(), ticketEntryId,
					OSBActionKeys.ADD_COMMENT_LIFERAY_INC);
			}
			else if (visibility == VisibilityConstants.PUBLIC) {
				OSBTicketEntryPermission.check(
					getPermissionChecker(), ticketEntryId,
					OSBActionKeys.ADD_COMMENT_PUBLIC);
			}
			else if (visibility == VisibilityConstants.WORKERS) {
				OSBTicketEntryPermission.check(
					getPermissionChecker(), ticketEntryId,
					OSBActionKeys.ADD_COMMENT_WORKERS);
			}
		}
	}

}