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

package com.liferay.osb.hook.upgrade.v2_5_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Calvin Keum
 */
public class UpgradeTrainingCustomer extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateTrainingCustomer();
	}

	protected void updateTrainingCustomer() throws Exception {
		for (long trainingCustomerId : _TRAINING_CUSTOMER_IDS) {
			runSQL(
				"update OSB_TrainingCustomer set userId = 12309704 where " +
					"trainingCustomerId = " + trainingCustomerId);
		}
	}

	private static final long[] _TRAINING_CUSTOMER_IDS = {
		36271005, 36430427
	};

}