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

package com.liferay.osb.hook.upgrade.v5_0_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Amos Fong
 */
public class Upgrade_20190117102736593_PartnerWorker
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20190117102736593L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(4);

		sb.append("delete pw1 from OSB_PartnerWorker pw1 inner join ");
		sb.append("OSB_PartnerWorker pw2 on (pw1.partnerEntryId = ");
		sb.append("pw2.partnerEntryId) and (pw1.userId = pw2.userId) where ");
		sb.append("pw1.partnerWorkerId > pw2.partnerWorkerId");

		runSQL(sb.toString());
	}

}