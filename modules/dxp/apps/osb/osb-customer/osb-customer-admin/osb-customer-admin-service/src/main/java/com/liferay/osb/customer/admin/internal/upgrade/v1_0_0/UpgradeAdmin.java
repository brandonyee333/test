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

package com.liferay.osb.customer.admin.internal.upgrade.v1_0_0;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Amos Fong
 */
public class UpgradeAdmin extends UpgradeProcess {

	public UpgradeAdmin(ClassNameLocalService classNameLocalService) {
		_classNameLocalService = classNameLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeSchema();

		upgradeClassNameId();

		upgradeListTypes();
	}

	protected void upgradeClassNameId() throws Exception {
		long classNameId = _classNameLocalService.getClassNameId(
			AccountEntry.class);

		runSQL(
			"update OSB_ExternalIdMapper set classNameId = " + classNameId +
				" where classNameId = 1400963");
	}

	protected void upgradeListTypes() throws Exception {
		runSQL("delete from ListType where type_ like '%deprecated'");

		runSQL(
			"update ListType set type_ = 'com.liferay.osb.model.ProductEntry." +
				"socialOfficeMinorVersions' where listTypeId = 25000");

		runSQL(
			"update ListType set type_ = replace(type_, 'digitalEnterprise', " +
				"'dxp') where type_ like '%com.liferay.osb.model%'");
		runSQL(
			"update ListType set type_ = replace(type_, 'socialOffice', " +
				"'so') where type_ like '%com.liferay.osb.model%'");

		runSQL(
			"update ListType set type_ = replace(type_, 'osb.model', " +
				"'osb.customer.admin.model') where type_ like " +
					"'%com.liferay.osb.model%'");

		runSQL(
			"update OSB_ProductEntry set versionsListType = replace(" +
				"versionsListType, 'digitalEnterprise', 'dxp')");

		runSQL(
			"update OSB_ProductEntry set versionsListType = replace(" +
				"versionsListType, 'commerceLicenseProductVersions', " +
					"'commerceLicenseVersions')");
	}

	protected void upgradeSchema() throws Exception {
		runSQL(
			"alter table OSB_AccountEntry add column koroneikiAccountKey " +
				"varchar(75)");
		runSQL(
			"alter table OSB_AccountEntry add column supportEndDate datetime");
		runSQL(
			"alter table OSB_AccountEntry add column ticketSupportEndDate " +
				"datetime");

		runSQL(
			"alter table OSB_ProductEntry add column accountEnvironments " +
				"tinyint");
		runSQL(
			"alter table OSB_ProductEntry add column koroneikiProductKey " +
				"varchar(75)");
	}

	private final ClassNameLocalService _classNameLocalService;

}