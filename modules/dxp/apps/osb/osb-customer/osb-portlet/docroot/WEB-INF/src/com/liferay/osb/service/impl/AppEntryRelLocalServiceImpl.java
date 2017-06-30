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

import com.liferay.osb.DuplicateAppEntryRelException;
import com.liferay.osb.model.AppEntryRel;
import com.liferay.osb.service.base.AppEntryRelLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Ryan Park
 */
public class AppEntryRelLocalServiceImpl
	extends AppEntryRelLocalServiceBaseImpl {

	public AppEntryRel addAppEntryRel(
			long appEntryId1, long appEntryId2, int type)
		throws PortalException, SystemException {

		validate(appEntryId1, appEntryId2, type);

		long appEntryRelId = counterLocalService.increment();

		AppEntryRel appEntryRel = appEntryRelPersistence.create(appEntryRelId);

		appEntryRel.setAppEntryId1(appEntryId1);
		appEntryRel.setAppEntryId2(appEntryId2);
		appEntryRel.setType(type);

		appEntryRelPersistence.update(appEntryRel, false);

		return appEntryRel;
	}

	public AppEntryRel deleteAppEntryRel(long appEntryRelId)
		throws PortalException, SystemException {

		AppEntryRel appEntryRel = appEntryRelPersistence.findByPrimaryKey(
			appEntryRelId);

		appEntryRelPersistence.remove(appEntryRel);

		return appEntryRel;
	}

	public List<AppEntryRel> getAppEntryRels(long appEntryId1, int type)
		throws SystemException {

		return appEntryRelPersistence.findByAEI1_T(appEntryId1, type);
	}

	protected void validate(long appEntryId1, long appEntryId2, int type)
		throws PortalException, SystemException {

		AppEntryRel appEntryRel =
			appEntryRelPersistence.fetchByAEI1_AEI2_T(
				appEntryId1, appEntryId2, type);

		if (appEntryRel != null) {
			throw new DuplicateAppEntryRelException();
		}
	}

}