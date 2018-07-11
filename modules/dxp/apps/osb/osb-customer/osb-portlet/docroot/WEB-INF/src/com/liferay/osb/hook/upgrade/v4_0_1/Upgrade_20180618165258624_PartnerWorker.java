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

package com.liferay.osb.hook.upgrade.v4_0_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

/**
 * @author Wesley Gong
 */
public class Upgrade_20180618165258624_PartnerWorker
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20180618165258624L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<PartnerEntry> partnerEntries =
			PartnerEntryLocalServiceUtil.getPartnerEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (PartnerEntry partnerEntry : partnerEntries) {
			PartnerWorkerLocalServiceUtil.syncPartnerWorkers(
				partnerEntry.getPartnerEntryId(), null,
				partnerEntry.getDossieraAccountKey());
		}
	}

}