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

package com.liferay.osb.testray.internal.upgrade.v1_19_0;

import com.liferay.message.boards.kernel.model.MBMessage;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author William Newbury
 */
public class UpgradeTestrayCaseResult extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		for (MBMessage mbMessage :
				MBMessageLocalServiceUtil.getMBMessages(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {

			String body = mbMessage.getBody();

			if (body.contains(
					"This has been automatically populated from a previous " +
						"result")) {

				runSQL(
					"update OSB_TestrayCaseResult set commentMBMessageId=0 " +
						"where commentMBMessageId=" + mbMessage.getMessageId());

				MBMessageLocalServiceUtil.deleteDiscussionMessage(
					mbMessage.getMessageId());
			}
		}
	}

}