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
				"update OSB_TestrayAssignment set classNameId = " +
					newClassNameId + " where classNameId = " + oldClassNameId);
		}
	}

	private final ClassNameLocalService _classNameLocalService;

}