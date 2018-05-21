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

package com.liferay.osb.hook.upgrade.v2_3_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Amos Fong
 */
public class UpgradeAccountEnvironmentAttachment extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateAccountEnvironmentAttachment();
	}

	protected void updateAccountEnvironmentAttachment() throws Exception {
		if (hasTable("OSB_AccountEnvironmentAttachment")) {
			return;
		}

		runSQL(
			"rename table OSB_AccountEnvironmentFile to " +
				"OSB_AccountEnvironmentAttachment");

		runSQL(
			"alter table OSB_AccountEnvironmentAttachment change " +
				"accountEnvironmentFileId accountEnvironmentAttachmentId LONG");
	}

}