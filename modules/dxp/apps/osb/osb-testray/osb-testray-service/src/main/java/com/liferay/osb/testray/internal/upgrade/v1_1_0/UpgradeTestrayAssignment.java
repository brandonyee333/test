/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.internal.upgrade.v1_1_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.service.ClassNameLocalService;

/**
 * @author Ethan Bustad
 */
public class UpgradeTestrayAssignment extends BaseUpgradeClassName {

	public UpgradeTestrayAssignment(
		ClassNameLocalService classNameLocalService) {

		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		for (String className : CLASS_NAMES) {
			long newClassNameId = _classNameLocalService.getClassNameId(
				className);
			long oldClassNameId = _classNameLocalService.getClassNameId(
				getStaleClassName(className));

			runSQL(
				StringBundler.concat(
					"update OSB_TestrayAssignment set classNameId = ",
					newClassNameId, " where classNameId = ", oldClassNameId));
		}
	}

	private final ClassNameLocalService _classNameLocalService;

}