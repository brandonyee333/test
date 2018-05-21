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

package com.liferay.osb.hook.upgrade;

/**
 * Release Date: 2015-11-13
 *
 * @author Amos Fong
 */
public class UpgradeProcess_3_3_8 extends BaseUpgradeProcess {

	@Override
	public int getThreshold() {
		return 338;
	}

	@Override
	protected void doUpgrade() throws Exception {
	}

}