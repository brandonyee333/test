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

import java.util.Date;
import java.util.List;

import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.model.SecurityPatch;
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.service.base.SecurityPatchLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Amos Fong
 */
public class SecurityPatchLocalServiceImpl
	extends SecurityPatchLocalServiceBaseImpl {

	public SecurityPatch addSecurityPatch(
			long userId, long accountEntryId, long ticketAttachmentId,
			String portletId, int envLFR, String name, String fileName)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		validate(envLFR);

		long securityPatchId = counterLocalService.increment();

		SecurityPatch securityPatch = securityPatchPersistence.create(
			securityPatchId);

		securityPatch.setUserId(user.getUserId());
		securityPatch.setUserName(user.getFullName());
		securityPatch.setCreateDate(new Date());
		securityPatch.setAccountEntryId(accountEntryId);
		securityPatch.setTicketAttachmentId(ticketAttachmentId);
		securityPatch.setPortletId(portletId);
		securityPatch.setEnvLFR(envLFR);
		securityPatch.setName(name);
		securityPatch.setFileName(fileName);

		securityPatchPersistence.update(securityPatch, false);

		return securityPatch;
	}

	public SecurityPatch addSecurityPatch(
			long userId, long ticketAttachmentId, String portletId, int envLFR,
			String fileName)
		throws PortalException, SystemException {

		TicketAttachment ticketAttachment =
			ticketAttachmentPersistence.findByPrimaryKey(ticketAttachmentId);

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketAttachment.getTicketEntryId());

		String name = getSecurityPatchName(envLFR, ticketAttachment);

		return addSecurityPatch(
			userId, ticketEntry.getAccountEntryId(), ticketAttachmentId,
			portletId, envLFR, name, fileName);
	}

	public void deleteSecurityPatches(String portletId) throws SystemException {
		securityPatchPersistence.removeByPortletId(portletId);
	}

	public List<SecurityPatch> getSecurityPatches(
			long accountEntryId, String portletId)
		throws SystemException {

		return securityPatchPersistence.findByAEI_PI(accountEntryId, portletId);
	}

	public String getSecurityPatchName(
		int envLFR, TicketAttachment ticketAttachment) {

		StringBundler sb = new StringBundler(3);

		sb.append(TicketEntryConstants.getEnvLabel(envLFR));
		sb.append(" - ");
		sb.append(ticketAttachment.getFileName());

		return sb.toString();
	}

	public SecurityPatch updateSecurityPatch(long securityPatchId, String name)
		throws PortalException, SystemException {

		SecurityPatch securityPatch = securityPatchPersistence.findByPrimaryKey(
			securityPatchId);

		securityPatch.setName(name);

		return securityPatchPersistence.update(securityPatch, false);
	}

	protected void validate(int envLFR)
		throws PortalException, SystemException {

		ListType listType = listTypeService.getListType(envLFR);

		String type = listType.getType();

		if (!type.equals(ProductEntryConstants.LIST_TYPE_PORTAL_ALL_VERSIONS) &&
			!type.equals(
				ProductEntryConstants.
					LIST_TYPE_DIGITAL_ENTERPRISE_ALL_VERSIONS)) {

			throw new NoSuchListTypeException();
		}
	}

} 