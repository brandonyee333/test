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

import com.liferay.osb.exception.TicketLinkTypeException;
import com.liferay.osb.exception.TicketLinkURLException;
import com.liferay.osb.exception.TicketLinkVisibilityException;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketLink;
import com.liferay.osb.service.base.TicketLinkLocalServiceBaseImpl;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class TicketLinkLocalServiceImpl extends TicketLinkLocalServiceBaseImpl {

	public TicketLink addTicketLink(
			long userId, long ticketEntryId, long ticketSolutionId,
			String[] urls, Integer[] types, int visibility,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = serviceContext.getCreateDate(new Date());

		validate(ticketEntryId, urls, types, visibility);

		TicketLink ticketLink = null;

		long auditSetId = GetterUtil.getInteger(
			serviceContext.getAttribute("auditSetId"));

		if (auditSetId <= 0) {
			auditSetId = auditEntryLocalService.getNextAuditSetId(
				TicketEntry.class.getName(), ticketEntryId);
		}

		int auditAction = GetterUtil.getInteger(
			serviceContext.getAttribute("auditAction"));

		if (auditAction <= 0) {
			auditAction = AuditEntryConstants.ACTION_ADD;
		}

		for (int i = 0; i < urls.length; i++) {
			long ticketLinkId = counterLocalService.increment();

			ticketLink = ticketLinkPersistence.create(ticketLinkId);

			ticketLink.setUserId(user.getUserId());
			ticketLink.setUserName(user.getFullName());
			ticketLink.setCreateDate(now);
			ticketLink.setTicketEntryId(ticketEntryId);
			ticketLink.setTicketSolutionId(ticketSolutionId);
			ticketLink.setUrl(urls[i]);
			ticketLink.setType(types[i]);
			ticketLink.setVisibility(visibility);

			ticketLinkPersistence.update(ticketLink);

			long classNameId = classNameLocalService.getClassNameId(
				TicketEntry.class.getName());
			long fieldClassNameId = classNameLocalService.getClassNameId(
				TicketLink.class.getName());

			auditEntryLocalService.addAuditEntry(
				userId, user.getFullName(), now, classNameId, ticketEntryId,
				auditSetId, fieldClassNameId, ticketLink.getTicketLinkId(),
				auditAction, AuditEntryConstants.FIELD_URL, visibility,
				StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, urls[i]);
		}

		return ticketLink;
	}

	public void deleteTicketLink(long userId, long ticketLinkId)
		throws PortalException {

		TicketLink ticketLink = ticketLinkPersistence.findByPrimaryKey(
			ticketLinkId);

		deleteTicketLink(userId, ticketLink);
	}

	public void deleteTicketLink(long userId, TicketLink ticketLink)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);

		ticketLinkPersistence.remove(ticketLink);

		auditEntryLocalService.addAuditEntry(
			userId, user.getFullName(), new Date(),
			classNameLocalService.getClassNameId(TicketEntry.class.getName()),
			ticketLink.getTicketEntryId(), 0,
			classNameLocalService.getClassNameId(TicketLink.class.getName()),
			ticketLink.getTicketLinkId(), AuditEntryConstants.ACTION_DELETE,
			AuditEntryConstants.FIELD_URL, ticketLink.getVisibility(),
			StringPool.BLANK, ticketLink.getUrl(), StringPool.BLANK,
			StringPool.BLANK);
	}

	public List<TicketLink> getTicketLinks(
		long ticketEntryId, int[] visibilities) {

		return ticketLinkPersistence.findByTEI_V(ticketEntryId, visibilities);
	}

	public List<TicketLink> getTicketLinks(
		long ticketEntryId, long ticketSolutionId) {

		return ticketLinkPersistence.findByTEI_TSI(
			ticketEntryId, ticketSolutionId);
	}

	public int getTicketLinksCount(long ticketEntryId, int[] visibilities) {
		return ticketLinkPersistence.countByTEI_V(ticketEntryId, visibilities);
	}

	protected void validate(
			long ticketEntryId, String[] urls, Integer[] types, int visibility)
		throws PortalException {

		for (int i = 0; i < urls.length; i++) {
			if (!Validator.isUrl(urls[i])) {
				throw new TicketLinkURLException();
			}

			if ((types[i] < 0) || (types[i] > 4)) {
				throw new TicketLinkTypeException();
			}
		}

		if (Validator.isNull(VisibilityConstants.toLabel(visibility))) {
			throw new TicketLinkVisibilityException();
		}

		ticketEntryPersistence.findByPrimaryKey(ticketEntryId);
	}

}