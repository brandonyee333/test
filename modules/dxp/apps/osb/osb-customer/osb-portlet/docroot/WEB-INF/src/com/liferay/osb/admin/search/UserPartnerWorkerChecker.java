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

package com.liferay.osb.admin.search;

import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;

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

	@Override
	public boolean isRememberCheckBoxState() {
		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(
		UserPartnerWorkerChecker.class);

	private final PartnerEntry _partnerEntry;

}