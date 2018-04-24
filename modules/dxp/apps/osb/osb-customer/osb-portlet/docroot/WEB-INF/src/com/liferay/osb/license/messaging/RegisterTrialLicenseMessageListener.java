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

package com.liferay.osb.license.messaging;

import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

/* TODO update OSBUtil integration
import com.liferay.osb.shared.util.OSBUtil;
*/

/**
 * @author Amos Fong
 */
public class RegisterTrialLicenseMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		long userId = 0;

		try {
			userId = message.getLong("userId");

			AccountEntryLocalServiceUtil.addTrialAccountEntry(userId);
		}
		catch (Exception e) {
			/* TODO update OSBUtil integration
			OSBUtil.setTrialEULA(
				OSBConstants.COMPANY_ID, userId, new String[0]);
			 */
			_log.error(e, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RegisterTrialLicenseMessageListener.class);

}