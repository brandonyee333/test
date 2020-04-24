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

package com.liferay.osb.loop.asset.sharing.internal.upgrade.v1_1_0;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Calvin Keum
 */
public class UpgradeClassName extends BaseUpgradeClassName {

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(5);

		sb.append("update ClassName_ set value = '");
		sb.append(CLASS_NAME);
		sb.append("' where value = '");
		sb.append(getStaleClassName(CLASS_NAME));
		sb.append(StringPool.APOSTROPHE);

		runSQL(sb.toString());
	}

}