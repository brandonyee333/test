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

package com.liferay.osb.trainingcertificateverification.portlet;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.NoSuchTrainingCertificateException;
import com.liferay.osb.model.TrainingCertificate;
import com.liferay.osb.model.TrainingCertificateTemplateConstants;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.service.TrainingCertificateLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Calvin Keum
 * @author Eddie Olson
 */
public class TrainingCertificateVerificationPortlet extends MVCPortlet {

	public void verifyCertificateKey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String certificateKey = ParamUtil.getString(
			actionRequest, "certificateKey");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		int trainingCertificateType = ParamUtil.getInteger(
			actionRequest, "trainingCertificateType");

		try {
			long classNameId = PortalUtil.getClassNameId(TrainingEvent.class);

			if (trainingCertificateType ==
					TrainingCertificateTemplateConstants.TYPE_TRAINING_EXAM) {

				classNameId = PortalUtil.getClassNameId(
					TrainingExamResult.class);
			}

			TrainingCertificate trainingCertificate =
				TrainingCertificateLocalServiceUtil.getTrainingCertificate(
					certificateKey, lastName, classNameId);

			SessionMessages.add(
				actionRequest, "certificateKeyVerified", trainingCertificate);
		}
		catch (NoSuchTrainingCertificateException nstce) {
			SessionMessages.add(
				actionRequest, PortalUtil.getPortletId(actionRequest) +
					SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);

			throw nstce;
		}
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (cause instanceof NoSuchTrainingCertificateException) {
			return true;
		}
		else {
			return false;
		}
	}

}