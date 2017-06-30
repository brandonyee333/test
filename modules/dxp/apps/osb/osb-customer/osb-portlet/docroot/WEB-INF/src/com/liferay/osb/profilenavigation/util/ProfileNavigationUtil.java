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

package com.liferay.osb.profilenavigation.util;

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBatch;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.salesforce.service.SalesforceLocalServiceUtil;
import com.liferay.salesforce.util.SalesforceMessageConstants;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Peter Shin
 */
public class ProfileNavigationUtil {

	public static boolean hasSalesforceUser(
		long companyId, long userId, String emailAddress) {

		MessageBatch messageBatch = null;

		try {
			messageBatch = SalesforceLocalServiceUtil.executeQuery(
				companyId,
				"Select Id From User where Email = '" + emailAddress + "'");
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		if (messageBatch == null) {
			try {
				return OrganizationLocalServiceUtil.hasUserOrganization(
					userId, OSBConstants.ORGANIZATION_LIFERAY_INC_ID);
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			return false;
		}

		List<Message> messages = messageBatch.getMessages();

		if (messages.isEmpty()) {
			return false;
		}

		Message message = messages.get(0);

		String id = message.getString(SalesforceMessageConstants.SOBJECT_ID);

		if (Validator.isNull(id)) {
			return false;
		}

		return true;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ProfileNavigationUtil.class);

}