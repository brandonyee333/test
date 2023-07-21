/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.security.Permission;

import javax.management.MBeanPermission;
import javax.management.MBeanTrustPermission;

/**
 * @author Raymond Augé
 */
public class MBeanChecker extends BaseChecker {

	@Override
	public void afterPropertiesSet() {
	}

	@Override
	public boolean implies(Permission permission) {
		String name = permission.getName();
		String actions = permission.getActions();

		if ((permission instanceof MBeanPermission) &&
			(actions.equals(MBEAN_PERMISSION_IS_INSTANCE_OF) ||
			 actions.equals(MBEAN_PERMISSION_REGISTER_MBEAN) ||
			 actions.equals(MBEAN_PERMISSION_UNREGISTER_MBEAN))) {

			return true;
		}
		else if ((permission instanceof MBeanTrustPermission) &&
				 name.equals(MBEAN_TRUST_PERMISSION_REGISTER)) {

			return true;
		}

		logSecurityException(
			_log, "Attempted to perform MBean operation " + permission);

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(MBeanChecker.class);

}