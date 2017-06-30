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

package com.liferay.osb.trainingcertificationagreement.portlet;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.service.ContractAuditServiceUtil;
import com.liferay.osb.service.TrainingCertificateLocalServiceUtil;
import com.liferay.osb.service.TrainingCertificateTemplateLocalServiceUtil;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.TrainingExamResultLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Douglas Wong
 * @author Ryan Park
 */
public class TrainingCertificationAgreementPortlet extends MVCPortlet {

	public void acceptEULA(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		User user = themeDisplay.getUser();

		long trainingCertificateTemplateId =
			OSBConstants.
				TRAINING_CERTIFICATE_TEMPLATE_CERTIFIED_DEVELOPER_61_ID;

		boolean passedCertifiedDeveloper61Exam =
			TrainingCertificateTemplateLocalServiceUtil.
				hasCompletedTrainingCertificateTemplate(
					user.getUserId(),
					PortalUtil.getClassNameId(TrainingExamResult.class),
					trainingCertificateTemplateId);

		if (passedCertifiedDeveloper61Exam) {
			UserLocalServiceUtil.addRoleUsers(
				OSBConstants.ROLE_OSB_TRAINING_EXAM_DOCUMENT_61_ID,
				new long[] {user.getUserId()});
		}

		long contractEntryId = ParamUtil.getLong(
			actionRequest, "contractEntryId");

		ContractAuditServiceUtil.addContractAudit(
			contractEntryId, User.class.getName(), user.getUserId(),
			StringPool.BLANK, ContractEntryConstants.DEFAULT_CLASS_PK);

		sendTrainingCertificate(user.getUserId());
	}

	@Override
	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {
	}

	protected void sendTrainingCertificate(long userId) throws Exception {
		List<TrainingCustomer> trainingCustomers =
			TrainingCustomerLocalServiceUtil.getUserTrainingCustomers(
				userId, PortalUtil.getClassNameId(TrainingExamResult.class));

		for (TrainingCustomer trainingCustomer : trainingCustomers) {
			TrainingExamResult trainingExamResult =
				TrainingExamResultLocalServiceUtil.fetchTrainingExamResult(
					trainingCustomer.getClassPK());

			if ((trainingExamResult == null) ||
				!trainingExamResult.hasPassedGrade()) {

				continue;
			}

			TrainingCertificateLocalServiceUtil.sendTrainingCertificate(
				trainingCustomer.getTrainingCustomerId());
		}
	}

	protected static final String TRAINING_CERTIFICATE_AGREEMENT_LOCALE =
		"en_US";

}