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

import com.liferay.osb.model.SupportLabor;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.service.SupportLaborLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.portlet.RenderResponse;

/**
 * @author Brent Krone-Schmidt
 */
public class UserSupportLaborChecker extends RowChecker {

	public UserSupportLaborChecker(
		RenderResponse renderResponse, SupportLabor supportLabor) {

		super(renderResponse);

		_supportLabor = supportLabor;
	}

	@Override
	public boolean isChecked(Object obj) {
		SupportWorker supportWorker = (SupportWorker)obj;

		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		ClassLoader portletClassLoader =
			UserSupportLaborChecker.class.getClassLoader();

		try {
			if (contextClassLoader != portletClassLoader) {
				currentThread.setContextClassLoader(portletClassLoader);
			}

			return SupportLaborLocalServiceUtil.hasSupportWorker(
				supportWorker.getSupportWorkerId(),
				_supportLabor.getSupportLaborId());
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
		UserSupportLaborChecker.class);

	private final SupportLabor _supportLabor;

}