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

package com.liferay.osb.hook.upgrade.v3_6_7;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

*/

/**
 * @author Haote Chou
 */
public class Upgrade_20170131094443006_TrainingCustomer extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20170131094443006L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeTrainingCustomer(
			UserLocalServiceUtil.fetchUserByEmailAddress(
				OSBConstants.COMPANY_ID, "cristiano.menezes@ios.com.br"));

		upgradeTrainingCustomer(
			UserLocalServiceUtil.fetchUserByEmailAddress(
				OSBConstants.COMPANY_ID, "joaofmatos.10@gmail.com"));

		upgradeTrainingCustomer(
			UserLocalServiceUtil.fetchUserByEmailAddress(
				OSBConstants.COMPANY_ID, "wallace.fcs@gmail.com"));
	}

	protected void upgradeTrainingCustomer(User user) throws Exception {
		if (user == null) {
			return;
		}

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(
			TrainingEvent.class);

		TrainingCustomer trainingCustomer =
			TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(
				user.getUserId(), classNameId, 29001603);

		if (trainingCustomer != null) {
			trainingCustomer.setClassPK(43191832);

			TrainingCustomerLocalServiceUtil.updateTrainingCustomer(
				trainingCustomer);
		}
	}

}

*/

}