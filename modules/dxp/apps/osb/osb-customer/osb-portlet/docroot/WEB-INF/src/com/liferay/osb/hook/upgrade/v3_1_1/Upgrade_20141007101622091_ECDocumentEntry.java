/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.upgrade.v3_1_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.marketplace.util.MarketplaceEcommerceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

*/

/**
 * @author Douglas Wong
 */
public class Upgrade_20141007101622091_ECDocumentEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20141007101622091L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(3);

			sb.append("select * from ECommerce_ECDocumentEntry where ");
			sb.append("(status = 1 or status = 2) and extraSettings not like ");
			sb.append("'%vendorAmount%' collate utf8_bin");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long ecDocumentEntryId = rs.getLong("ecDocumentEntryId");

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Updated extraSettings for EC document entry " +
							ecDocumentEntryId);
				}

				updateECDocumentEntryVendorAmount(ecDocumentEntryId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateECDocumentEntryVendorAmount(long ecDocumentEntryId)
		throws Exception {

		try {
			Role role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, RoleConstants.ADMINISTRATOR);

			List<User> users = UserLocalServiceUtil.getRoleUsers(
				role.getRoleId());

			PermissionChecker permissionChecker =
				PermissionCheckerFactoryUtil.create(users.get(0));

			PermissionThreadLocal.setPermissionChecker(permissionChecker);

			MarketplaceEcommerceUtil.updateECDocumentEntryVendorAmount(
				ecDocumentEntryId);
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(null);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		Upgrade_20141007101622091_ECDocumentEntry.class);

	 */

}