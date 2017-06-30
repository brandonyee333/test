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

import com.liferay.osb.model.AppFlag;
import com.liferay.osb.service.base.AppFlagLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;

/**
 * @author Enoch Chu
 */
public class AppFlagLocalServiceImpl extends AppFlagLocalServiceBaseImpl {

	public AppFlag addAppFlag(long appEntryId, long appVersionId, int type)
		throws PortalException, SystemException {

		long appFlagId = counterLocalService.increment();

		AppFlag appFlag = appFlagPersistence.create(appFlagId);

		appFlag.setCreateDate(new Date());
		appFlag.setAppEntryId(appEntryId);
		appFlag.setAppVersionId(appVersionId);
		appFlag.setType(type);

		return appFlagPersistence.update(appFlag, false);
	}

	public AppFlag fetchAppFlag(long appVersionId, int type)
		throws SystemException {

		return appFlagPersistence.fetchByAVI_T(appVersionId, type);
	}

	public boolean hasAppFlag(long appVersionId, int type)
		throws SystemException {

		if (fetchAppFlag(appVersionId, type) == null) {
			return false;
		}

		return true;
	}

	public boolean hasAppFlags(long developerEntryId) throws SystemException {
		int count = appFlagFinder.countByDeveloperEntryId(developerEntryId);

		if (count > 0) {
			return true;
		}

		return false;
	}

}