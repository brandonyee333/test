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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class AccountWorkerImpl extends AccountWorkerBaseImpl {

	public AccountWorkerImpl() {
	}

	public AccountEntry getAccountEntry()
		throws PortalException, SystemException {

		return AccountEntryLocalServiceUtil.getAccountEntry(
			getAccountEntryId());
	}

	public String getKey() {
		return AccountWorkerConstants.getKey(getUserId(), getRole());
	}

	public String getNotificationsLabel() {
		return AccountWorkerConstants.getNotificationsLabel(getNotifications());
	}

	public String getRoleLabel() {
		return AccountWorkerConstants.getRoleLabel(getRole());
	}

}