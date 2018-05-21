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