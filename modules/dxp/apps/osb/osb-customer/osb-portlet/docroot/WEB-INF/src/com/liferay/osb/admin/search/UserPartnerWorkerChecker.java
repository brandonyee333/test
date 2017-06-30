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

package com.liferay.osb.admin.search;

import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;

import javax.portlet.RenderResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class UserPartnerWorkerChecker extends RowChecker {

	public UserPartnerWorkerChecker(
		RenderResponse renderResponse, PartnerEntry partnerEntry) {

		super(renderResponse);

		_partnerEntry = partnerEntry;
	}

	@Override
	public boolean isChecked(Object obj) {
		User user = (User)obj;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		ClassLoader portletClassLoader =
			UserPartnerWorkerChecker.class.getClassLoader();

		try {
			if (contextClassLoader != portletClassLoader) {
				currentThread.setContextClassLoader(portletClassLoader);
			}

			return PartnerWorkerLocalServiceUtil.hasPartnerWorker(
				user.getUserId(), _partnerEntry.getPartnerEntryId());
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
		finally {
			if (contextClassLoader != portletClassLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		UserPartnerWorkerChecker.class);

	private PartnerEntry _partnerEntry;

}