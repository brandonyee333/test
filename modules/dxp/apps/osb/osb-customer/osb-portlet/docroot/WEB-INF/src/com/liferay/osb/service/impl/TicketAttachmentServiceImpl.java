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

import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.service.base.TicketAttachmentServiceBaseImpl;
import com.liferay.osb.service.permission.OSBTicketAttachmentPermission;
import com.liferay.osb.service.permission.OSBTicketEntryPermission;
import com.liferay.osb.support.util.FileRepositoryUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.File;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class TicketAttachmentServiceImpl
	extends TicketAttachmentServiceBaseImpl {

	public TicketAttachment addTicketAttachment(
			long userId, long ticketEntryId, long ticketSolutionId,
			String fileName, long fileSize, int type, int visibility,
			String fileRepositoryId, int status)
		throws PortalException, SystemException {

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntryId, ActionKeys.ADD_ATTACHMENT);

		checkVisibility(getUserId(), ticketEntryId, visibility);

		return ticketAttachmentLocalService.addTicketAttachment(
			userId, ticketEntryId, ticketSolutionId, fileName, fileSize, type,
			visibility, fileRepositoryId, status);
	}

	public List<TicketAttachment> addTicketAttachments(
			long userId, long ticketEntryId, long ticketSolutionId,
			List<ObjectValuePair<String, File>> files, List<Integer> types,
			int visibility, int status, int[] pendingTypes,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		List<TicketAttachment> ticketAttachments = addTicketAttachments(
			userId, ticketEntryId, ticketSolutionId, files, types, visibility,
			status, serviceContext);

		if ((pendingTypes != null) && (pendingTypes.length > 0)) {
			ticketEntryLocalService.updatePendingTypes(
				userId, ticketEntryId, pendingTypes);
		}

		return ticketAttachments;
	}

	public List<TicketAttachment> addTicketAttachments(
			long userId, long ticketEntryId, long ticketSolutionId,
			List<ObjectValuePair<String, File>> files, List<Integer> types,
			int visibility, int status, ServiceContext serviceContext)
		throws PortalException, SystemException {

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntryId, ActionKeys.ADD_ATTACHMENT);

		checkVisibility(getUserId(), ticketEntryId, visibility);

		return ticketAttachmentLocalService.addTicketAttachments(
			userId, ticketEntryId, ticketSolutionId, files, types, visibility,
			status, serviceContext);
	}

	public boolean checkAvailability(
			long ticketAttachmentId, String fileRepositoryId)
		throws PortalException, SystemException {

		TicketAttachment ticketAttachment =
			ticketAttachmentLocalService.getTicketAttachment(
				ticketAttachmentId);

		OSBTicketAttachmentPermission.check(
			getPermissionChecker(), ticketAttachment, ActionKeys.VIEW);

		return ticketAttachmentLocalService.checkAvailability(
			ticketAttachmentId, fileRepositoryId);
	}

	public TicketAttachment deleteTicketAttachment(long ticketAttachmentId)
		throws PortalException, SystemException {

		TicketAttachment ticketAttachment =
			ticketAttachmentLocalService.getTicketAttachment(
				ticketAttachmentId);

		OSBTicketAttachmentPermission.check(
			getPermissionChecker(), ticketAttachment, OSBActionKeys.DELETE);

		checkVisibility(
			getUserId(), ticketAttachment.getTicketEntryId(),
			ticketAttachment.getVisibility());

		return ticketAttachmentLocalService.deleteTicketAttachment(
			getUserId(), ticketAttachment);
	}

	public TicketAttachment getTicketAttachment(long ticketAttachmentId)
		throws PortalException, SystemException {

		TicketAttachment ticketAttachment =
			ticketAttachmentLocalService.getTicketAttachment(
				ticketAttachmentId);

		OSBTicketAttachmentPermission.check(
			getPermissionChecker(), ticketAttachment, ActionKeys.VIEW);

		checkVisibility(
			getUserId(), ticketAttachment.getTicketEntryId(),
			ticketAttachment.getVisibility());

		return ticketAttachment;
	}

	public String getUploadToken(long ticketEntryId, String fileRepositoryId)
		throws PortalException, SystemException {

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntryId, ActionKeys.ADD_ATTACHMENT);

		return FileRepositoryUtil.getToken(fileRepositoryId, ticketEntryId);
	}

	public TicketAttachment replicateTicketAttachment(long ticketAttachmentId)
		throws PortalException, SystemException {

		TicketAttachment ticketAttachment =
			ticketAttachmentLocalService.getTicketAttachment(
				ticketAttachmentId);

		OSBTicketAttachmentPermission.check(
			getPermissionChecker(), ticketAttachment, OSBActionKeys.UPDATE);

		checkVisibility(
			getUserId(), ticketAttachment.getTicketEntryId(),
			ticketAttachment.getVisibility());

		return ticketAttachmentLocalService.replicateTicketAttachment(
			getUserId(), ticketAttachmentId);
	}

	public TicketAttachment updateDeleteDate(
			long ticketAttachmentId, Date deleteDate)
		throws PortalException, SystemException {

		TicketAttachment ticketAttachment =
			ticketAttachmentLocalService.getTicketAttachment(
				ticketAttachmentId);

		OSBTicketAttachmentPermission.check(
			getPermissionChecker(), ticketAttachment, OSBActionKeys.DELETE);

		checkVisibility(
			getUserId(), ticketAttachment.getTicketEntryId(),
			ticketAttachment.getVisibility());

		return ticketAttachmentLocalService.updateDeleteDate(
			getUserId(), ticketAttachmentId, deleteDate);
	}

	public TicketAttachment updateTicketAttachment(
			long ticketAttachmentId, long ticketEntryId, int type,
			int visibility, int[] pendingTypes)
		throws PortalException, SystemException {

		TicketAttachment ticketAttachment =
			ticketAttachmentLocalService.getTicketAttachment(
				ticketAttachmentId);

		if ((ticketAttachment.getTicketEntryId() > 0) &&
			(ticketAttachment.getTicketEntryId() != ticketEntryId)) {

			throw new PrincipalException();
		}

		OSBTicketAttachmentPermission.check(
			getPermissionChecker(), ticketAttachment, OSBActionKeys.UPDATE);

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntryId, ActionKeys.ADD_ATTACHMENT);

		checkVisibility(
			getUserId(), ticketEntryId, ticketAttachment.getVisibility());

		if ((pendingTypes != null) && (pendingTypes.length > 0)) {
			ticketEntryLocalService.updatePendingTypes(
				getUserId(), ticketEntryId, pendingTypes);
		}

		return ticketAttachmentLocalService.updateTicketAttachment(
			ticketAttachmentId, ticketEntryId, type, visibility);
	}

	public List<TicketAttachment> updateTicketAttachments(
			List<Long> ticketAttachmentIds, long ticketEntryId,
			List<Integer> types, List<Integer> visibilities, int[] pendingTypes)
		throws PortalException, SystemException {

		for (long ticketAttachmentId : ticketAttachmentIds) {
			TicketAttachment ticketAttachment =
				ticketAttachmentLocalService.getTicketAttachment(
					ticketAttachmentId);

			if ((ticketAttachment.getTicketEntryId() > 0) &&
				(ticketAttachment.getTicketEntryId() != ticketEntryId)) {

				throw new PrincipalException();
			}

			OSBTicketAttachmentPermission.check(
				getPermissionChecker(), ticketAttachment, OSBActionKeys.UPDATE);

			OSBTicketEntryPermission.check(
				getPermissionChecker(), ticketEntryId,
				ActionKeys.ADD_ATTACHMENT);

			checkVisibility(
				getUserId(), ticketEntryId, ticketAttachment.getVisibility());
		}

		if ((pendingTypes != null) && (pendingTypes.length > 0)) {
			ticketEntryLocalService.updatePendingTypes(
				getUserId(), ticketEntryId, pendingTypes);
		}

		return ticketAttachmentLocalService.updateTicketAttachments(
			ticketAttachmentIds, ticketEntryId, types, visibilities);
	}

	protected void checkVisibility(
			long userId, long ticketEntryId, int visibility)
		throws PortalException, SystemException {

		if (!ticketEntryLocalService.hasVisibility(
				userId, ticketEntryId, visibility)) {

			throw new PrincipalException();
		}
	}

}