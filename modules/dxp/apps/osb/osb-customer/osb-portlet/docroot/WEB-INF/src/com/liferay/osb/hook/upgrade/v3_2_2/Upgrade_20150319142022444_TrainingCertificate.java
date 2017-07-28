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

package com.liferay.osb.hook.upgrade.v3_2_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TrainingCertificateConstants;

*/

/**
 * @author Ryan Park
 */
public class Upgrade_20150319142022444_TrainingCertificate
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20150319142022444L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update OSB_TrainingCertificate set surveyStatus = " +
				TrainingCertificateConstants.SURVEY_STATUS_NOT_APPLICABLE +
					" where surveyStatus is null");
	}

}

*/

}