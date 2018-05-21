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

import com.liferay.osb.exception.DuplicateOfferingDefinitionException;
import com.liferay.osb.exception.RequiredOfferingDefinitionException;
import com.liferay.osb.model.OfferingDefinition;
import com.liferay.osb.service.base.OfferingDefinitionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

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
		throws PortalException {

		User user = userLocalService.getUser(userId);
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

		return offeringDefinitionPersistence.update(offeringDefinition);
	}

	@Override
	public OfferingDefinition deleteOfferingDefinition(
			long offeringDefinitionId)
		throws PortalException {

		if (offeringDefinitionPersistence.containsOfferingBundles(
				offeringDefinitionId)) {

			throw new RequiredOfferingDefinitionException();
		}

		return offeringDefinitionPersistence.remove(offeringDefinitionId);
	}

	public List<OfferingDefinition> getOfferingDefinitions(
		long[] productEntryIds, long[] supportResponseIds, int start, int end) {

		if ((productEntryIds.length <= 0) && (supportResponseIds.length <= 0)) {
			return offeringDefinitionPersistence.findAll(start, end);
		}
		else {
			return offeringDefinitionPersistence.findByPEI_SRI(
				productEntryIds, supportResponseIds, start, end, null);
		}
	}

	public int getOfferingDefinitionsCount(
		long[] productEntryIds, long[] supportResponseIds) {

		if ((productEntryIds.length <= 0) && (supportResponseIds.length <= 0)) {
			return offeringDefinitionPersistence.countAll();
		}
		else {
			return offeringDefinitionPersistence.countByPEI_SRI(
				productEntryIds, supportResponseIds);
		}
	}

	public List<OfferingDefinition> getProductEntryOfferingDefinitions(
		long productEntryId) {

		return offeringDefinitionPersistence.findByProductEntryId(
			productEntryId);
	}

	public OfferingDefinition updateOfferingDefinition(
			long offeringDefinitionId, long productEntryId,
			long supportResponseId, String productDescription, boolean licenses,
			boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
			boolean supportTickets)
		throws PortalException {

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

		return offeringDefinitionPersistence.update(offeringDefinition);
	}

	protected void validate(
			long offeringDefinitionId, long productEntryId,
			long supportResponseId, String productDescription, boolean licenses,
			boolean unlimitedLicenses, boolean supportTickets)
		throws PortalException {

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