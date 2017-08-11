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

package com.liferay.osb.hook.upgrade.v2_1_4;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;

*/

/**
 * @author Calvin Keum
 */
public class UpgradeTrainingCustomer extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	protected void doUpgrade() throws Exception {
		updateTrainingCustomer();
	}

	protected void updateTrainingCustomer() throws Exception {
		for (long[] trainingEventCustomers : _TRAINING_EVENT_CUSTOMERS) {
			long trainingEventId = trainingEventCustomers[0];

			for (int i = 1; i < trainingEventCustomers.length; i++) {
				runSQL(
					"update OSB_TrainingCustomer set classPK = " +
						trainingEventId + " where trainingCustomerId = " +
							trainingEventCustomers[i]);
			}
		}
	}

	private static final long[][] _TRAINING_EVENT_CUSTOMERS = {
		{
			31560726, 31435926, 31435940, 31435954, 31435968, 31435982,
			31435996, 31436010, 31436024
		},
		{31561037, 31444574, 31444602, 31444644, 31444658, 31444686},
		{31561392, 31455780, 31455794, 31455808},
		{31561563, 31459513, 31459527},
		{
			31561723, 31451172, 31451186, 31451200, 31451214, 31451228,
			31451242, 31451257, 31451271
		},
		{31561881, 31456864, 31456878, 31456892}
	};

	 */

}