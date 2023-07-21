/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.downloads.proxy.web.internal.util;

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

		if (hasExpandoValue(userId, "osbCustomerESA", versionRequired)) {
			return true;
		}

		return false;
	}

	public static boolean hasEvaluationEULA(long userId, double versionRequired)
		throws PortalException {

		if (hasExpandoValue(
				userId, "osbCustomerEvaluationEULA", versionRequired)) {

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

		if (hasExpandoValue(userId, "osbCustomerStudioEULA", versionRequired)) {
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