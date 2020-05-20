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

import com.liferay.osb.exception.DuplicateSupportRegionException;
import com.liferay.osb.exception.RequiredSupportRegionException;
import com.liferay.osb.exception.SupportRegionNameException;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.service.base.SupportRegionLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;

/**
 * @author Brent Krone-Schmidt
 */
public class SupportRegionLocalServiceImpl
	extends SupportRegionLocalServiceBaseImpl {

	public SupportRegion addSupportRegion(
			long userId, String name, String description, String timeZoneId)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(0, name);

		long supportRegionId = counterLocalService.increment();

		SupportRegion supportRegion = supportRegionPersistence.create(
			supportRegionId);

		supportRegion.setCompanyId(OSBConstants.COMPANY_ID);
		supportRegion.setUserId(user.getUserId());
		supportRegion.setUserName(user.getFullName());
		supportRegion.setCreateDate(now);
		supportRegion.setModifiedDate(now);
		supportRegion.setName(name);
		supportRegion.setDescription(description);
		supportRegion.setTimeZoneId(timeZoneId);

		return supportRegionPersistence.update(supportRegion);
	}

	@Override
	public SupportRegion deleteSupportRegion(long supportRegionId)
		throws PortalException {

		if (accountEntryLocalService.getSupportRegionAccountEntriesCount(
				supportRegionId) > 0) {

			throw new RequiredSupportRegionException();
		}

		if (partnerEntryLocalService.getSupportRegionPartnerEntriesCount(
				supportRegionId) > 0) {

			throw new RequiredSupportRegionException();
		}

		return supportRegionPersistence.remove(supportRegionId);
	}

	public SupportRegion fetchSupportRegionByName(String name) {
		return supportRegionPersistence.fetchByName(name);
	}

	public List<SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId) {

		return accountEntryPersistence.getSupportRegions(accountEntryId);
	}

	public SupportRegion updateSupportRegion(
			long supportRegionId, String name, String description,
			String timeZoneId)
		throws PortalException {

		validate(supportRegionId, name);

		SupportRegion supportRegion = supportRegionPersistence.findByPrimaryKey(
			supportRegionId);

		supportRegion.setModifiedDate(new Date());
		supportRegion.setName(name);
		supportRegion.setDescription(description);
		supportRegion.setTimeZoneId(timeZoneId);

		return supportRegionPersistence.update(supportRegion);
	}

	protected void validate(long supportRegionId, String name)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new SupportRegionNameException();
		}

		SupportRegion supportRegion = supportRegionPersistence.fetchByName(
			name);

		if ((supportRegion != null) &&
			(supportRegion.getSupportRegionId() != supportRegionId)) {

			throw new DuplicateSupportRegionException();
		}
	}

}