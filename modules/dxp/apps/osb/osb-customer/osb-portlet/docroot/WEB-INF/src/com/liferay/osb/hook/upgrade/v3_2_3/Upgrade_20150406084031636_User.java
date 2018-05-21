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

package com.liferay.osb.hook.upgrade.v3_2_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Joan Kim
 */
public class Upgrade_20150406084031636_User extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150406084031636L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		Indexer indexer = IndexerRegistryUtil.getIndexer(User.class);

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select distinct userId from OSB_TrainingCustomer");

			rs = ps.executeQuery();

			while (rs.next()) {
				long userId = rs.getLong("userId");

				if (userId > 0) {
					indexer.reindex(userId);
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

}