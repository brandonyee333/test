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

package com.liferay.osb.hook.upgrade.v5_1_3;

import com.liferay.osb.service.RemoteUserLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jenny Chen
 */
public class UpgradeRole extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeExternalIdMapper();
		upgradeProductEntry();
		upgradeRole();
	}

	protected void updateOrganizationUsers(long userId) throws Exception {
		try {
			RemoteUserLocalServiceUtil.unsetOrganizationUsers(
				_ORGANIZATION_CUSTOMER_ENTERPRISE_SEARCH_STANDARD_ID,
				new long[] {userId});

			UserLocalServiceUtil.unsetOrganizationUsers(
				_ORGANIZATION_CUSTOMER_ENTERPRISE_SEARCH_STANDARD_ID,
				new long[] {userId});

			RemoteUserLocalServiceUtil.addOrganizationUsers(
				_ORGANIZATION_CUSTOMER_ENTERPRISE_SEARCH_PREMIUM_ID,
				new long[] {userId});

			UserLocalServiceUtil.addOrganizationUser(
				_ORGANIZATION_CUSTOMER_ENTERPRISE_SEARCH_PREMIUM_ID, userId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void upgradeExternalIdMapper() throws Exception {
		StringBundler sb = new StringBundler(8);

		sb.append("update OSB_ExternalIdMapper inner join OSB_ProductEntry ");
		sb.append("on OSB_ProductEntry.productEntryId = ");
		sb.append("OSB_ExternalIdMapper.classPK inner join ClassName_ on ");
		sb.append("ClassName_.classNameId = OSB_ExternalIdMapper.classNameId ");
		sb.append("set externalId = 'enterprise_search' where ");
		sb.append("OSB_ExternalIdMapper.type_ = 7 and ClassName_.value like ");
		sb.append("'%.ProductEntry' and OSB_ProductEntry.name like ");
		sb.append("'%Enterprise Search%'");

		runSQL(sb.toString());
	}

	protected void upgradeProductEntry() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select productEntryId, name from OSB_ProductEntry where " +
					"name like '%Enterprise Search%' and (name like " +
						"'%Premium%' or name like '%Standard%')");

			rs = ps.executeQuery();

			while (rs.next()) {
				long productEntryId = rs.getLong("productEntryId");

				String name = rs.getString("name");

				if (name.contains("Premium ")) {
					name = name.replace("Premium ", StringPool.BLANK);
				}
				else if (name.contains("Standard")) {
					name = name + " (Legacy)";
				}

				runSQL(
					"update OSB_ProductEntry set name = '" + name +
						"' where productEntryId = " + productEntryId);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void upgradeRole() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select userId from Users_Orgs where organizationId = " +
					_ORGANIZATION_CUSTOMER_ENTERPRISE_SEARCH_STANDARD_ID;

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long userId = rs.getLong("userId");

				updateOrganizationUsers(userId);
			}

			runSQL(
				"update Organization_ set name = 'Customer - Liferay " +
					"Enterprise Search' where organizationId = " +
						_ORGANIZATION_CUSTOMER_ENTERPRISE_SEARCH_PREMIUM_ID);

			runSQL(
				"update Role_ set name = 'Customer - Liferay Enterprise " +
					"Search' where roleId = " +
						_ROLE_CUSTOMER_ENTERPRISE_SEARCH_PREMIUM_ID);
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	private static final long
		_ORGANIZATION_CUSTOMER_ENTERPRISE_SEARCH_PREMIUM_ID = 2844850;

	private static final long
		_ORGANIZATION_CUSTOMER_ENTERPRISE_SEARCH_STANDARD_ID = 2844843;

	private static final long _ROLE_CUSTOMER_ENTERPRISE_SEARCH_PREMIUM_ID =
		1546578;

	private static final Log _log = LogFactoryUtil.getLog(UpgradeRole.class);

}