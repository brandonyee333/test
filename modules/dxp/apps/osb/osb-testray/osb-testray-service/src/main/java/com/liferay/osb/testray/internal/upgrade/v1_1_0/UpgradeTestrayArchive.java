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

package com.liferay.osb.testray.internal.upgrade.v1_1_0;

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
				"update OSB_TestrayArchive set classNameId = " +
					newClassNameId + " where className = '" +
						getStaleClassName(className) + "'");
		}

		runSQL("alter table OSB_TestrayArchive drop className");
	}

	private final ClassNameLocalService _classNameLocalService;

}