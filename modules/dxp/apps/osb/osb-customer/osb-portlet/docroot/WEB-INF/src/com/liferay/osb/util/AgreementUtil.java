/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.util;

import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Amos Fong
 */
public class AgreementUtil {

	public static boolean hasESA(long userId, double versionRequired)
		throws PortalException {

		if (hasExpandoValue(userId, "osbCustomerESA", versionRequired) ||
			hasExpandoValue(userId, "osbESA", versionRequired)) {

			return true;
		}

		return false;
	}

	public static boolean hasEvaluationEULA(long userId, double versionRequired)
		throws PortalException {

		if (hasExpandoValue(
				userId, "osbCustomerEvaluationEULA", versionRequired) ||
			hasExpandoValue(userId, "osbEvaluationEULA", versionRequired)) {

			return true;
		}

		return false;
	}

	public static boolean hasLiferaySyncEULA(long userId)
		throws PortalException {

		return hasExpandoValue(userId, "osbLiferaySyncEULA", 0);
	}

	public static boolean hasStudioEULA(long userId, double versionRequired)
		throws PortalException {

		if (hasExpandoValue(userId, "osbCustomerStudioEULA", versionRequired) ||
			hasExpandoValue(userId, "osbStudioEULA", versionRequired)) {

			return true;
		}

		return false;
	}

	public static boolean hasTrialEULA(long userId) throws PortalException {
		return hasExpandoValue(userId, "osbTrialEULA", 0);
	}

	protected static boolean hasExpandoValue(
			long userId, String expandoColumnName, double versionRequired)
		throws PortalException {

		User user = UserLocalServiceUtil.getUser(userId);

		String[] agreementData = ExpandoValueLocalServiceUtil.getData(
			user.getCompanyId(), User.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, expandoColumnName, userId,
			new String[0]);

		if ((agreementData != null) && (agreementData.length >= 4)) {
			if (versionRequired == 0) {
				return true;
			}

			double version = GetterUtil.getDouble(agreementData[3]);

			if (version >= versionRequired) {
				return true;
			}
		}

		return false;
	}

}