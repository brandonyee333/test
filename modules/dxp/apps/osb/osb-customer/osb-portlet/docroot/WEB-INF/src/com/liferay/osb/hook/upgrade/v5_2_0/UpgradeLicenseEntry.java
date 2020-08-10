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

package com.liferay.osb.hook.upgrade.v5_2_0;

import com.liferay.osb.exception.NoSuchProductEntryException;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.LicenseEntryConstants;
import com.liferay.osb.model.OfferingDefinition;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.LicenseEntryLocalServiceUtil;
import com.liferay.osb.service.OfferingDefinitionLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jenny Chen
 */
public class UpgradeLicenseEntry extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateListType();
		getProductEntries();
		updateLicenseEntries();
		updateOfferingDefinitions();
		updateOfferingEntries();
	}

	protected void getProductEntries() throws PortalException {
		_commerceBackupProductEntryId = updateProductEntry(
			"Liferay Commerce Subscription Backup",
			ProductEntryConstants.ENVIRONMENT_BACKUP);
		_commerceEnterpriseProductEntryId = updateProductEntry(
			"Liferay Commerce Subscription Unlimited Enterprise-Wide",
			ProductEntryConstants.ENVIRONMENT_ANY);
		_commerceNonProductionProductEntryId = updateProductEntry(
			"Liferay Commerce Subscription Non-Production",
			ProductEntryConstants.ENVIRONMENT_NON_PRODUCTION);
		_commerceProductionProductEntryId = updateProductEntry(
			"Liferay Commerce Subscription Production",
			ProductEntryConstants.ENVIRONMENT_PRODUCTION);
	}

	protected void updateLicenseEntries() throws Exception {
		if (hasIndex("OSB_LicenseEntry", "IX_8B5D7FE4")) {
			runSQL("drop index IX_8B5D7FE4 on OSB_LicenseEntry");
		}

		if (hasColumn("OSB_LicenseEntry", "portalVersionMin")) {
			runSQL(
				"alter table OSB_LicenseEntry change column portalVersionMin " +
					"versionMin INTEGER");
		}

		if (hasColumn("OSB_LicenseEntry", "portalVersionMax")) {
			runSQL(
				"alter table OSB_LicenseEntry change column portalVersionMax " +
					"versionMax INTEGER");
		}

		if (!hasIndex("OSB_LicenseEntry", "IX_19390E58")) {
			runSQL(
				"create index IX_19390E58 on OSB_LicenseEntry " +
					"(productEntryId, versionMin)");
		}

		LicenseEntryLocalServiceUtil.addLicenseEntry(
			OSBConstants.USER_AMOS_FONG_USER_ID, _commerceBackupProductEntryId,
			"Liferay Commerce Subscription Backup",
			LicenseEntryConstants.TYPE_PRODUCTION, 0, 0);
		LicenseEntryLocalServiceUtil.addLicenseEntry(
			OSBConstants.USER_AMOS_FONG_USER_ID,
			_commerceEnterpriseProductEntryId,
			"Liferay Commerce Subscription Unlimited Enterprise-Wide",
			LicenseEntryConstants.TYPE_ENTERPRISE, 0, 0);
		LicenseEntryLocalServiceUtil.addLicenseEntry(
			OSBConstants.USER_AMOS_FONG_USER_ID,
			_commerceNonProductionProductEntryId,
			"Liferay Commerce Subscription Non-Production",
			LicenseEntryConstants.TYPE_PRODUCTION, 0, 0);
		LicenseEntryLocalServiceUtil.addLicenseEntry(
			OSBConstants.USER_AMOS_FONG_USER_ID,
			_commerceProductionProductEntryId,
			"Liferay Commerce Subscription Production",
			LicenseEntryConstants.TYPE_PRODUCTION, 0, 0);
	}

	protected void updateListType() throws Exception {
		insertListType(
			47000, "1",
			"com.liferay.osb.model.ProductEntry." +
				"commerceLicenseProductVersions");
	}

	protected void updateOfferingDefinitions() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select offeringDefinitionId from OSB_OfferingDefinition " +
					"where productEntryId in (109807882, 120153268, " +
						"109807891, 109807893)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long offeringDefinitionId = rs.getLong("offeringDefinitionId");

				OfferingDefinition offeringDefinition =
					OfferingDefinitionLocalServiceUtil.getOfferingDefinition(
						offeringDefinitionId);

				offeringDefinition.setLicenses(Boolean.TRUE);

				OfferingDefinitionLocalServiceUtil.updateOfferingDefinition(
					offeringDefinition);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateOfferingEntries() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select offeringEntryId from OSB_OfferingEntry where " +
					"productEntryId in (109807882, 120153268, 109807891, " +
						"109807893)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long offeringEntryId = rs.getLong("offeringEntryId");

				OfferingEntry offeringEntry =
					OfferingEntryLocalServiceUtil.getOfferingEntry(
						offeringEntryId);

				offeringEntry.setLicenses(Boolean.TRUE);

				OfferingEntryLocalServiceUtil.updateOfferingEntry(
					offeringEntry);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected long updateProductEntry(String name, int environment)
		throws PortalException {

		ProductEntry productEntry = null;

		try {
			productEntry = ProductEntryLocalServiceUtil.getProductEntryByName(
				name);

			productEntry.setVersionsListType("commerceLicenseProductVersions");

			ProductEntryLocalServiceUtil.updateProductEntry(productEntry);
		}
		catch (NoSuchProductEntryException nspee) {
			productEntry = ProductEntryLocalServiceUtil.addProductEntry(
				OSBConstants.USER_AMOS_FONG_USER_ID, name,
				ProductEntryConstants.TYPE_REGULAR, environment,
				"commerceLicenseProductVersions", new String[0], null);
		}

		return productEntry.getProductEntryId();
	}

	private long _commerceBackupProductEntryId;
	private long _commerceEnterpriseProductEntryId;
	private long _commerceNonProductionProductEntryId;
	private long _commerceProductionProductEntryId;

}