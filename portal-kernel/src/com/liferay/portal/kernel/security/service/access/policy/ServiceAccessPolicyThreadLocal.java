/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.service.access.policy;

import com.liferay.petra.lang.CentralizedThreadLocal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mika Koivisto
 */
public class ServiceAccessPolicyThreadLocal {

	public static void addActiveServiceAccessPolicyName(
		String serviceAccessPolicyName) {

		List<String> activeServiceAccessPolicyNames =
			getActiveServiceAccessPolicyNames();

		if (activeServiceAccessPolicyNames == null) {
			activeServiceAccessPolicyNames = new ArrayList<>();

			setActiveServiceAccessPolicyNames(activeServiceAccessPolicyNames);
		}

		activeServiceAccessPolicyNames.add(serviceAccessPolicyName);
	}

	public static List<String> getActiveServiceAccessPolicyNames() {
		return _activeServiceAccessPolicyNames.get();
	}

	public static void setActiveServiceAccessPolicyNames(
		List<String> activeServiceAccessPolicyNames) {

		_activeServiceAccessPolicyNames.set(activeServiceAccessPolicyNames);
	}

	private static final ThreadLocal<List<String>>
		_activeServiceAccessPolicyNames = new CentralizedThreadLocal<>(
			ServiceAccessPolicyThreadLocal.class +
				"._activeServiceAccessPolicyNames");

}