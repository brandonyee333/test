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

package com.liferay.osb.hook.upgrade.v5_2_1;

import com.liferay.osb.exception.NoSuchProductEntryException;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.LicenseEntryConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.LicenseEntryLocalServiceUtil;
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
		getProductEntries();
		updateLicenseEntries();
	}

	protected void getProductEntries() throws PortalException {
		_commerceDevelopmentProductEntryId = updateProductEntry(
			"Liferay Commerce Subscription Development",
			ProductEntryConstants.ENVIRONMENT_DEVELOPMENT);
	}

	protected void updateLicenseEntries() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select * from OSB_LicenseEntry where name like '" +
					"Liferay Commerce Subscription Development'";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			if (!rs.next()) {
				LicenseEntryLocalServiceUtil.addLicenseEntry(
					OSBConstants.USER_AMOS_FONG_USER_ID,
					_commerceDevelopmentProductEntryId,
					"Liferay Commerce Subscription Development",
					LicenseEntryConstants.TYPE_DEVELOPER, 0, 0);
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
				ProductEntryConstants.TYPE_ADD_ON, environment,
				"commerceLicenseProductVersions", new String[0], null);
		}

		return productEntry.getProductEntryId();
	}

	private long _commerceDevelopmentProductEntryId;

}