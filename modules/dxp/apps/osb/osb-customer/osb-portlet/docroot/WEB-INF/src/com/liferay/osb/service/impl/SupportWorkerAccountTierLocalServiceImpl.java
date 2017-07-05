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

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.osb.model.SupportWorkerAccountTier;
import com.liferay.osb.service.base.SupportWorkerAccountTierLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Brent Krone-Schmidt
 */
public class SupportWorkerAccountTierLocalServiceImpl
	extends SupportWorkerAccountTierLocalServiceBaseImpl {

	public List<SupportWorkerAccountTier> getSupportWorkerAccountTiers(
			long supportWorkerId)
		throws SystemException {

		return supportWorkerAccountTierPersistence.findBySupportWorkerId(
			supportWorkerId);
	}

	public void setSupportWorkerAccountTiers(
			long supportWorkerId, int[] accountTiers)
		throws SystemException {

		List<SupportWorkerAccountTier> supportWorkerAccountTiers =
			supportWorkerAccountTierPersistence.findBySupportWorkerId(
				supportWorkerId);

		for (SupportWorkerAccountTier supportWorkerAccountTier :
				supportWorkerAccountTiers) {

			int accountTier = supportWorkerAccountTier.getAccountTier();

			if (ArrayUtil.contains(accountTiers, accountTier)) {
				accountTiers = ArrayUtil.remove(accountTiers, accountTier);
			}
			else {
				supportWorkerAccountTierPersistence.remove(
					supportWorkerAccountTier);
			}
		}

		for (int accountTier : accountTiers) {
			long supportWorkerAccountTierId = counterLocalService.increment();

			SupportWorkerAccountTier supportWorkerAccountTier =
				supportWorkerAccountTierPersistence.create(
					supportWorkerAccountTierId);

			supportWorkerAccountTier.setSupportWorkerId(supportWorkerId);
			supportWorkerAccountTier.setAccountTier(accountTier);

			supportWorkerAccountTierPersistence.update(
				supportWorkerAccountTier, false);
		}
	}

}