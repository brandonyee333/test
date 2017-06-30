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

import com.liferay.osb.DuplicateOfferingDefinitionException;
import com.liferay.osb.RequiredOfferingDefinitionException;
import com.liferay.osb.model.OfferingDefinition;
import com.liferay.osb.service.base.OfferingDefinitionLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class OfferingDefinitionLocalServiceImpl
	extends OfferingDefinitionLocalServiceBaseImpl {

	public OfferingDefinition addOfferingDefinition(
			long userId, long productEntryId, long supportResponseId,
			String productDescription, boolean licenses,
			boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
			boolean supportTickets)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(
			0, productEntryId, supportResponseId, productDescription, licenses,
			unlimitedLicenses, supportTickets);

		long offeringDefinitionId = counterLocalService.increment();

		OfferingDefinition offeringDefinition =
			offeringDefinitionPersistence.create(offeringDefinitionId);

		offeringDefinition.setUserId(user.getUserId());
		offeringDefinition.setUserName(user.getFullName());
		offeringDefinition.setCreateDate(now);
		offeringDefinition.setModifiedDate(now);
		offeringDefinition.setProductEntryId(productEntryId);
		offeringDefinition.setSupportResponseId(supportResponseId);
		offeringDefinition.setProductDescription(productDescription);
		offeringDefinition.setLicenses(licenses);
		offeringDefinition.setUnlimitedLicenses(unlimitedLicenses);
		offeringDefinition.setMaxConcurrentUsers(maxConcurrentUsers);
		offeringDefinition.setMaxUsers(maxUsers);
		offeringDefinition.setSupportTickets(supportTickets);

		offeringDefinitionPersistence.update(offeringDefinition, false);

		return offeringDefinition;
	}

	@Override
	public OfferingDefinition deleteOfferingDefinition(
			long offeringDefinitionId)
		throws PortalException, SystemException {

		if (offeringDefinitionId == OSBConstants.OFFERING_DEFINITION_TRIAL_ID) {
			throw new RequiredOfferingDefinitionException();
		}

		if (offeringDefinitionPersistence.containsOfferingBundles(
				offeringDefinitionId)) {

			throw new RequiredOfferingDefinitionException();
		}

		return offeringDefinitionPersistence.remove(offeringDefinitionId);
	}

	public List<OfferingDefinition> getOfferingDefinitions(
			long[] productEntryIds, long[] supportResponseIds, int start,
			int end)
		throws SystemException {

		if ((productEntryIds.length <= 0) && (supportResponseIds.length <= 0)) {
			return offeringDefinitionPersistence.findAll(start, end);
		}
		else {
			return offeringDefinitionPersistence.findByPEI_SRI(
				productEntryIds, supportResponseIds, start, end, null);
		}
	}

	public int getOfferingDefinitionsCount(
			long[] productEntryIds, long[] supportResponseIds)
		throws SystemException {

		if ((productEntryIds.length <= 0) && (supportResponseIds.length <= 0)) {
			return offeringDefinitionPersistence.countAll();
		}
		else {
			return offeringDefinitionPersistence.countByPEI_SRI(
				productEntryIds, supportResponseIds);
		}
	}

	public List<OfferingDefinition> getProductEntryOfferingDefinitions(
			long productEntryId)
		throws SystemException {

		return offeringDefinitionPersistence.findByProductEntryId(
			productEntryId);
	}

	public List<OfferingDefinition> getSupportResponseOfferingDefinitions(
			long supportResponseId)
		throws SystemException {

		return offeringDefinitionPersistence.findBySupportResponseId(
			supportResponseId);
	}

	public OfferingDefinition updateOfferingDefinition(
			long offeringDefinitionId, long productEntryId,
			long supportResponseId, String productDescription, boolean licenses,
			boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
			boolean supportTickets)
		throws PortalException, SystemException {

		validate(
			offeringDefinitionId, productEntryId, supportResponseId,
			productDescription, licenses, unlimitedLicenses, supportTickets);

		OfferingDefinition offeringDefinition =
			offeringDefinitionPersistence.findByPrimaryKey(
				offeringDefinitionId);

		offeringDefinition.setModifiedDate(new Date());
		offeringDefinition.setProductEntryId(productEntryId);
		offeringDefinition.setSupportResponseId(supportResponseId);
		offeringDefinition.setProductDescription(productDescription);
		offeringDefinition.setLicenses(licenses);
		offeringDefinition.setUnlimitedLicenses(unlimitedLicenses);
		offeringDefinition.setMaxConcurrentUsers(maxConcurrentUsers);
		offeringDefinition.setMaxUsers(maxUsers);
		offeringDefinition.setSupportTickets(supportTickets);

		offeringDefinitionPersistence.update(offeringDefinition, false);

		return offeringDefinition;
	}

	protected void validate(
			long offeringDefinitionId, long productEntryId,
			long supportResponseId, String productDescription, boolean licenses,
			boolean unlimitedLicenses, boolean supportTickets)
		throws PortalException, SystemException {

		if (offeringDefinitionId == OSBConstants.OFFERING_DEFINITION_TRIAL_ID) {
			throw new RequiredOfferingDefinitionException();
		}

		productEntryPersistence.findByPrimaryKey(productEntryId);

		supportResponsePersistence.findByPrimaryKey(supportResponseId);

		OfferingDefinition offeringDefinition =
			offeringDefinitionPersistence.fetchByPEI_SRI_PD_L_UL_ST(
				productEntryId, supportResponseId, productDescription, licenses,
				unlimitedLicenses, supportTickets);

		if ((offeringDefinition != null) &&
			(offeringDefinition.getOfferingDefinitionId() !=
				offeringDefinitionId)) {

			throw new DuplicateOfferingDefinitionException();
		}
	}

}