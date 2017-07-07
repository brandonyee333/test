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

package com.liferay.osb.hook.upgrade.v1_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingLocation;
import com.liferay.osb.service.TrainingEventLocalServiceUtil;
import com.liferay.osb.service.TrainingLocationLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;

import java.util.List;

*/

/**
 * @author Calvin Keum
 */
public class UpgradeTrainingEvent extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	protected void doUpgrade() throws Exception {
		updateTrainingEvent();
	}

	protected void updateTrainingEvent() throws Exception {
		if (tableHasColumn("OSB_TrainingEvent", "trainingCourseName")) {
			runSQL(
				"alter table OSB_TrainingEvent drop column trainingCourseName");
		}

		if (tableHasColumn("OSB_TrainingEvent", "trainingCourseCreditAmount")) {
			runSQL(
				"alter table OSB_TrainingEvent drop column " +
					"trainingCourseCreditAmount");
		}

		if (!tableHasColumn("OSB_TrainingEvent", "addressId")) {
			runSQL("alter table OSB_TrainingEvent add column addressId long");
		}

		List<TrainingEvent> trainingEvents =
			TrainingEventLocalServiceUtil.getTrainingEvents(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (TrainingEvent trainingEvent : trainingEvents) {
			if (trainingEvent.getAddressId() > 0) {
				continue;
			}

			TrainingLocation trainingLocation =
				TrainingLocationLocalServiceUtil.getTrainingLocation(
					trainingEvent.getTrainingLocationId());

			Address trainingLocationAddress = trainingLocation.getAddress();

			Address trainingEventAddress = AddressLocalServiceUtil.addAddress(
				trainingEvent.getUserId(), TrainingEvent.class.getName(),
				trainingEvent.getTrainingEventId(),
				trainingLocationAddress.getStreet1(),
				trainingLocationAddress.getStreet2(),
				trainingLocationAddress.getStreet3(),
				trainingLocationAddress.getCity(),
				trainingLocationAddress.getZip(),
				trainingLocationAddress.getRegionId(),
				trainingLocationAddress.getCountryId(), 0, true, true);

			trainingEvent.setAddressId(trainingEventAddress.getAddressId());

			TrainingEventLocalServiceUtil.updateTrainingEvent(trainingEvent);
		}
	}

}
*/

}