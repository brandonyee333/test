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

package com.liferay.osb.hook.upgrade.v3_4_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.CorpProjectMessageModelImpl;

*/

/**
 * @author Amos Fong
 */
public class Upgrade_20160208153736735_CorpProjectMessage
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20160208153736735L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(CorpProjectMessageModelImpl.TABLE_SQL_CREATE);

		runSQL(
			"create index IX_80F71C3B on OSB_CorpProjectMessage " +
				"(corpProjectId)");
		runSQL(
			"create index IX_F22B253A on OSB_CorpProjectMessage " +
				"(corpProjectId, type_)");
	}

}

*/

}