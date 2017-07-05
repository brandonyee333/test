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

package com.liferay.osb.hook.upgrade;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.osb.hook.upgrade.v2_1_0.UpgradeTicketAttachment;
import com.liferay.osb.hook.upgrade.v2_1_0.UpgradeTrainingCertificate;
import com.liferay.osb.hook.upgrade.v2_1_0.UpgradeTrainingCertificateTemplate;
import com.liferay.osb.hook.upgrade.v2_1_0.UpgradeTrainingCustomer;
import com.liferay.osb.hook.upgrade.v2_1_0.UpgradeTrainingExamResult;
import com.liferay.osb.hook.upgrade.v2_1_0.UpgradeTrainingWorker;

/**
 * @author Rachael Koestartyo
 * @author Calvin Keum
 */
public class UpgradeProcess_2_1_0 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return 210;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeTicketAttachment.class);
		upgrade(UpgradeTrainingCustomer.class);
		upgrade(UpgradeTrainingCertificate.class);
		upgrade(UpgradeTrainingCertificateTemplate.class);
		upgrade(UpgradeTrainingExamResult.class);
		upgrade(UpgradeTrainingWorker.class);
	}

}