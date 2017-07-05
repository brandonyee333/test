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

package com.liferay.osb.hook.upgrade.v2_4_7;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.osb.model.TrainingCustomerConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Calvin Keum
 */
public class UpgradeTrainingCustomer extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateTrainingCustomer();
	}

	protected void updateTrainingCustomer() throws Exception {
		if (!tableHasColumn("OSB_TrainingCustomer", "status")) {
			runSQL(
				"alter table OSB_TrainingCustomer add column status INTEGER");
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select trainingCustomerId from OSB_TrainingCertificate " +
					"where certifiedDate is not null and trainingCustomerId " +
						"is not null");

			rs = ps.executeQuery();

			while (rs.next()) {
				long trainingCustomerId = rs.getLong("trainingCustomerId");

				runSQL(
					"update OSB_TrainingCustomer set status = " +
						TrainingCustomerConstants.STATUS_CERTIFIED + " where " +
							"trainingCustomerId = " + trainingCustomerId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}