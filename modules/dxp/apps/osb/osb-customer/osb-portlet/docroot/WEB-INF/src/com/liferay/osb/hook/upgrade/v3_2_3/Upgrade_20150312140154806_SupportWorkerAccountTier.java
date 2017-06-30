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

package com.liferay.osb.hook.upgrade.v3_2_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.model.impl.SupportWorkerAccountTierModelImpl;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20150312140154806_SupportWorkerAccountTier
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150312140154806L;
	}

	protected void addSupportWorkerAccountTierRows() throws Exception {
		if (tableHasData("OSB_SupportWorkerAccountTier")) {
			return;
		}

		List<SupportWorker> supportWorkers =
			SupportWorkerLocalServiceUtil.getSupportWorkers(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (SupportWorker supportWorker : supportWorkers) {
			if (supportWorker.getRole() ==
					SupportWorkerConstants.ROLE_WATCHER) {

				continue;
			}

			StringBundler sb = new StringBundler(9);

			sb.append("insert into OSB_SupportWorkerAccountTier ");
			sb.append("(supportWorkerAccountTierId, supportWorkerId, ");
			sb.append("accountTier) values (");
			sb.append(increment());
			sb.append(StringPool.COMMA);
			sb.append(supportWorker.getSupportWorkerId());
			sb.append(StringPool.COMMA);
			sb.append(AccountEntryConstants.TIER_OEM);
			sb.append(");");

			runSQL(sb.toString(), false);

			sb = new StringBundler(9);

			sb.append("insert into OSB_SupportWorkerAccountTier ");
			sb.append("(supportWorkerAccountTierId, supportWorkerId, ");
			sb.append("accountTier) values (");
			sb.append(increment());
			sb.append(StringPool.COMMA);
			sb.append(supportWorker.getSupportWorkerId());
			sb.append(StringPool.COMMA);
			sb.append(AccountEntryConstants.TIER_PREMIER);
			sb.append(");");

			runSQL(sb.toString(), false);

			sb = new StringBundler(9);

			sb.append("insert into OSB_SupportWorkerAccountTier ");
			sb.append("(supportWorkerAccountTierId, supportWorkerId, ");
			sb.append("accountTier) values (");
			sb.append(increment());
			sb.append(StringPool.COMMA);
			sb.append(supportWorker.getSupportWorkerId());
			sb.append(StringPool.COMMA);
			sb.append(AccountEntryConstants.TIER_REGULAR);
			sb.append(");");

			runSQL(sb.toString(), false);

			sb = new StringBundler(9);

			sb.append("insert into OSB_SupportWorkerAccountTier ");
			sb.append("(supportWorkerAccountTierId, supportWorkerId, ");
			sb.append("accountTier) values (");
			sb.append(increment());
			sb.append(StringPool.COMMA);
			sb.append(supportWorker.getSupportWorkerId());
			sb.append(StringPool.COMMA);
			sb.append(AccountEntryConstants.TIER_STRATEGIC);
			sb.append(");");

			runSQL(sb.toString(), false);
		}
	}

	protected void createSupportWorkerAccountTier() throws Exception {
		if (hasTable("OSB_SupportWorkerAccountTier")) {
			return;
		}

		runSQL(SupportWorkerAccountTierModelImpl.TABLE_SQL_CREATE);
	}

	protected void createSupportWorkerAccountTierIndex() throws Exception {
		if (hasIndex("OSB_SupportWorkerAccountTier", "IX_749683CF")) {
			return;
		}

		runSQL(
			"create index IX_749683CF on OSB_SupportWorkerAccountTier " +
				"(supportWorkerId)");
	}

	@Override
	protected void doUpgrade() throws Exception {
		createSupportWorkerAccountTier();

		createSupportWorkerAccountTierIndex();

		addSupportWorkerAccountTierRows();
	}

}