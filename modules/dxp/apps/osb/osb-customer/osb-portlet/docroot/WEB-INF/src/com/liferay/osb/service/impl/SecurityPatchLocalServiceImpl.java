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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.model.SecurityPatch;
import com.liferay.osb.service.base.SecurityPatchLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class SecurityPatchLocalServiceImpl
	extends SecurityPatchLocalServiceBaseImpl {

	public SecurityPatch addSecurityPatch(
			long userId, long accountEntryId, String portletId, int envLFR,
			String name, String fileName)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		validate(envLFR);

		long securityPatchId = counterLocalService.increment();

		SecurityPatch securityPatch = securityPatchPersistence.create(
			securityPatchId);

		securityPatch.setUserId(user.getUserId());
		securityPatch.setUserName(user.getFullName());
		securityPatch.setCreateDate(new Date());
		securityPatch.setAccountEntryId(accountEntryId);
		securityPatch.setPortletId(portletId);
		securityPatch.setEnvLFR(envLFR);
		securityPatch.setName(name);
		securityPatch.setFileName(fileName);

		return securityPatchPersistence.update(securityPatch);
	}

	public SecurityPatch addSecurityPatch(
			long userId, String portletId, int envLFR, String fileName)
		throws PortalException {

		/* Need to refactor, removing TicketAttachment from osb-portlet
		 *
		 * TicketAttachment ticketAttachment =
			ticketAttachmentPersistence.findByPrimaryKey(ticketAttachmentId);

		TicketEntry ticketEntry = ticketEntryPersistence.findByPrimaryKey(
			ticketAttachment.getTicketEntryId());

		String name = getSecurityPatchName(envLFR, ticketAttachment);

		return addSecurityPatch(
			userId, ticketEntry.getAccountEntryId(), ticketAttachmentId,
			portletId, envLFR, name, fileName);*/

		return null;
	}

	public void deleteSecurityPatches(String portletId) {
		securityPatchPersistence.removeByPortletId(portletId);
	}

	public List<SecurityPatch> getSecurityPatches(
		long accountEntryId, String portletId) {

		return securityPatchPersistence.findByAEI_PI(accountEntryId, portletId);
	}

	public String getSecurityPatchName(int envLFR) {
		StringBundler sb = new StringBundler(3);

		/*Need to refactor, removing TicketAttachment from osb-portlet
		 *
		 * sb.append(TicketEntryConstants.getEnvLabel(envLFR));
		sb.append(" - ");
		sb.append(ticketAttachment.getFileName());*/

		return sb.toString();
	}

	public SecurityPatch updateSecurityPatch(long securityPatchId, String name)
		throws PortalException {

		SecurityPatch securityPatch = securityPatchPersistence.findByPrimaryKey(
			securityPatchId);

		securityPatch.setName(name);

		return securityPatchPersistence.update(securityPatch);
	}

	protected void validate(int envLFR) throws PortalException {
		ListType listType = listTypeLocalService.getListType(envLFR);

		String type = listType.getType();

		if (!type.equals(ProductEntryConstants.LIST_TYPE_PORTAL_ALL_VERSIONS) &&
			!type.equals(
				ProductEntryConstants.
					LIST_TYPE_DIGITAL_ENTERPRISE_ALL_VERSIONS)) {

			throw new NoSuchListTypeException();
		}
	}

}