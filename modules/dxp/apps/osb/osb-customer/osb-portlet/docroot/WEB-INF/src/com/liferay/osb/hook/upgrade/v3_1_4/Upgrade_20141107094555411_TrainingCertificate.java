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

package com.liferay.osb.hook.upgrade.v3_1_4;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TrainingCertificate;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.model.UserProfileHistory;
import com.liferay.osb.service.TrainingCertificateLocalServiceUtil;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.UserProfileHistoryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

/**
 * @author Val Nagy
 */
public class Upgrade_20141107094555411_TrainingCertificate
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20141107094555411L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeTrainingCertificate();
	}

	protected void updateTrainingCertificateUserProfileHistory(
			long trainingCertificateId, long userProfileHistoryId)
		throws Exception {

		runSQL(
			"update OSB_TrainingCertificate set userProfileHistoryId = " +
				userProfileHistoryId + " where trainingCertificateId = " +
					trainingCertificateId);
	}

	protected void upgradeTrainingCertificate() throws Exception {
		List<TrainingCertificate> trainingCertificates =
			TrainingCertificateLocalServiceUtil.getTrainingCertificates(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (TrainingCertificate trainingCertificate : trainingCertificates) {
			TrainingCustomer trainingCustomer =
				TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(
					trainingCertificate.getTrainingCustomerId());

			if (trainingCustomer == null) {
				continue;
			}

			if (trainingCustomer.getClassNameId() !=
					PortalUtil.getClassNameId(TrainingExamResult.class)) {

				continue;
			}

			if (trainingCertificate.getUserProfileHistoryId() > 0) {
				continue;
			}

			UserProfileHistory userProfileHistory =
				UserProfileHistoryLocalServiceUtil.getUserProfileHistory(
					trainingCustomer.getUserProfileHistoryId());

			userProfileHistory =
				UserProfileHistoryLocalServiceUtil.addUserProfileHistory(
					userProfileHistory.getUserId(),
					PortalUtil.getClassNameId(TrainingCertificate.class),
					trainingCertificate.getTrainingCertificateId(),
					userProfileHistory.getEmailAddress(),
					userProfileHistory.getFirstName(),
					userProfileHistory.getLastName(),
					userProfileHistory.getLegalEntityName());

			updateTrainingCertificateUserProfileHistory(
				trainingCertificate.getTrainingCertificateId(),
				userProfileHistory.getUserProfileHistoryId());
		}
	}

}