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

package com.liferay.osb.hook.upgrade.v2_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

*/

/**
 * @author Calvin Keum
 */
public class UpgradeTrainingCertificate extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	protected void doUpgrade() throws Exception {
		updateTrainingCertificate();
	}

	protected void updateTrainingCertificate() throws Exception {
		runSQL("drop index IX_2A2100B7 on OSB_TrainingCertificate");

		if (tableHasColumn(
				"OSB_TrainingCertificate", "trainingCustomerUserId") &&
			tableHasColumn("OSB_TrainingCertificate", "trainingEventId") &&
			!tableHasColumn("OSB_TrainingCertificate", "trainingCustomerId")) {

			runSQL(
				"alter table OSB_TrainingCertificate add column " +
					"trainingCustomerId LONG");

			runSQL(
				"create index IX_940016E9 on OSB_TrainingCertificate " +
					"(trainingCustomerId)");

			StringBundler sb = new StringBundler(8);

			sb.append("update OSB_TrainingCertificate ");
			sb.append("inner join OSB_TrainingCustomer on ");
			sb.append("OSB_TrainingCertificate.trainingEventId = ");
			sb.append("OSB_TrainingCustomer.classPK and ");
			sb.append("OSB_TrainingCertificate.trainingCustomerUserId = ");
			sb.append("OSB_TrainingCustomer.userId ");
			sb.append("set OSB_TrainingCertificate.trainingCustomerId = ");
			sb.append("OSB_TrainingCustomer.trainingCustomerId;");

			runSQL(sb.toString());
		}

		if (tableHasColumn("OSB_TrainingCertificate", "trainingEventId")) {
			runSQL(
				"alter table OSB_TrainingCertificate drop column " +
					"trainingEventId");
		}

		if (tableHasColumn(
				"OSB_TrainingCertificate", "trainingCustomerUserId")) {

			runSQL(
				"alter table OSB_TrainingCertificate drop column " +
					"trainingCustomerUserId");
		}
	}

}

*/

}