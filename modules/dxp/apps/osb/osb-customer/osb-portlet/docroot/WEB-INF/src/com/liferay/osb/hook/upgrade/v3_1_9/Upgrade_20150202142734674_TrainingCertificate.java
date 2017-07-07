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

package com.liferay.osb.hook.upgrade.v3_1_9;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

*/

/**
 * @author Val Nagy
 */
public class Upgrade_20150202142734674_TrainingCertificate
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20150202142734674L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler();

		sb.append("update OSB_TrainingCertificate set surveyStatus = 2, ");
		sb.append("certifiedDate = '2014-10-30 18:00:00' where ");
		sb.append("trainingCertificateId in (45248286, 45255345, 45247596, ");
		sb.append("45623467, 45923240)");

		runSQL(sb.toString());

		runSQL(
			"update OSB_TrainingCertificate set surveyStatus = 2, " +
				"certifiedDate = '2014-11-06 18:00:00' where " +
					"trainingCertificateId in (45019994, 45255323, 45003319)");

		runSQL(
			"update OSB_TrainingCustomer set status = 3 where " +
				"trainingCustomerId in (44995193, 44995205, 44995238, " +
					"44998419, 44998423, 44998433)");
	}

}
*/

}