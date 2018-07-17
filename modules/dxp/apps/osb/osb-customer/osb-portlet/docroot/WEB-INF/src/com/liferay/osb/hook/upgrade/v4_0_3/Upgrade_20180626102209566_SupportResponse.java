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

package com.liferay.osb.hook.upgrade.v4_0_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.model.SupportResponseConstants;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;

/**
 * @author Amos Fong
 */
public class Upgrade_20180626102209566_SupportResponse
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20180626102209566L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		SupportResponse supportResponse =
			SupportResponseLocalServiceUtil.fetchSupportResponseByName(
				"Not Applicable");

		if (supportResponse != null) {
			return;
		}

		SupportResponseLocalServiceUtil.addSupportResponse(
			OSBConstants.USER_AMOS_FONG_USER_ID, "Not Applicable",
			SupportResponseConstants.SUPPORT_LEVEL_NA, 0, 0, 0, 0, 0, 0);
	}

}