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
public class UpgradeTestrayArchive extends BaseUpgradeClassName {

	public UpgradeTestrayArchive(ClassNameLocalService classNameLocalService) {
		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasColumn("OSB_TestrayArchive", "classNameId")) {
			return;
		}

		runSQL(
			"alter table OSB_TestrayArchive add column classNameId long " +
				"after modifiedDate");

		for (String className : CLASS_NAMES) {
			long newClassNameId = _classNameLocalService.getClassNameId(
				className);

			runSQL(
				StringBundler.concat(
					"update OSB_TestrayArchive set classNameId = ",
					newClassNameId, " where className = '",
					getStaleClassName(className), "'"));
		}

		runSQL("alter table OSB_TestrayArchive drop className");
	}

	private final ClassNameLocalService _classNameLocalService;

}