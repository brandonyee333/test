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

package com.liferay.osb.hook.upgrade.v3_0_4;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

*/

/**
 * @author Joan Kim
 */
public class Upgrade_20140625121344898_CorpProject extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20140625121344898L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!tableHasColumn("OSB_CorpEntry", "type_")) {
			return;
		}

		upgradeCorpProject();

		validateCorpProject();

		upgradeCorpEntry();
	}

	protected void upgradeCorpEntry() throws Exception {
		runSQL("delete from OSB_CorpEntry where type_ = 0");
	}

	protected void upgradeCorpProject() throws Exception {
		runSQL("create index IX_C2AF513 on OSB_CorpProject (name)", false);
		runSQL(
			"create index IX_14940FF6 on OSB_CorpProject (organizationId)",
			false);

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select * from OSB_CorpEntry where type_ = 0");

			rs = ps.executeQuery();

			while (rs.next()) {

				// Corp project

				ps = con.prepareStatement(
					"insert into OSB_CorpProject (corpProjectId, userId, " +
						"userName, createDate, modifiedDate, name, " +
							"organizationId) values (?, ?, ?, ?, ?, ?, ?)");

				ps.setLong(1, rs.getLong("corpEntryId"));
				ps.setLong(2, rs.getLong("userId"));
				ps.setString(3, rs.getString("userName"));
				ps.setTimestamp(4, rs.getTimestamp("createDate"));
				ps.setTimestamp(5, rs.getTimestamp("modifiedDate"));
				ps.setString(6, rs.getString("name"));
				ps.setLong(7, rs.getLong("organizationId"));

				ps.executeUpdate();

				// Organization

				ps = con.prepareStatement(
					"update Organization_ set name = ?, parentOrganizationId " +
						"= ? where organizationId = ?");

				String name =
					"CorpProject (" + rs.getString("corpEntryId") + ") " +
						rs.getString("name");

				ps.setString(1, name);

				ps.setLong(
					2, OSBConstants.ORGANIZATION_CORPORATION_PROJECT_PARENT_ID);
				ps.setLong(3, rs.getLong("organizationId"));

				ps.executeUpdate();
			}

			// Asset receipt

			StringBundler sb = new StringBundler(4);

			sb.append("update OSB_AssetReceipt set ownerClassNameId = ");
			sb.append(PortalUtil.getClassNameId(CorpProject.class));
			sb.append(" where ownerClassNameId = ");
			sb.append(PortalUtil.getClassNameId(CorpEntry.class));

			runSQL(sb.toString());

			// Asset receipt license

			sb = new StringBundler(4);

			sb.append("update OSB_AssetReceiptLicense set ownerClassNameId = ");
			sb.append(PortalUtil.getClassNameId(CorpProject.class));
			sb.append(" where ownerClassNameId = ");
			sb.append(PortalUtil.getClassNameId(CorpEntry.class));

			runSQL(sb.toString());

			// Asset receipt support

			sb = new StringBundler(4);

			sb.append("update OSB_AssetReceiptSupport set ownerClassNameId = ");
			sb.append(PortalUtil.getClassNameId(CorpProject.class));
			sb.append(" where ownerClassNameId = ");
			sb.append(PortalUtil.getClassNameId(CorpEntry.class));

			runSQL(sb.toString());

			// E-commerce document entry

			sb = new StringBundler(8);

			sb.append("update ECommerce_ECDocumentEntry set ");
			sb.append("extraSettings = replace(extraSettings, '");
			sb.append(CorpEntry.class.getName());
			sb.append("', '");
			sb.append(CorpProject.class.getName());
			sb.append("') where extraSettings like '%ownerClassName=");
			sb.append(CorpEntry.class.getName());
			sb.append("%'");

			runSQL(sb.toString());
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void validateCorpProject() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			// Corp project

			StringBundler sb = new StringBundler(4);

			sb.append("select count(*) from OSB_CorpEntry left join ");
			sb.append("OSB_CorpProject on OSB_CorpEntry.corpEntryId = ");
			sb.append("OSB_CorpProject.corpProjectId where type_ = 0 ");
			sb.append("and (corpProjectId is null or corpProjectId = 0)");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			int count = 0;

			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				throw new UpgradeException(
					"Unable to migrate " + count + " corp entry consumers " +
						"as corp projects");
			}

			// Asset receipt

			sb = new StringBundler(3);

			sb.append("select count(*) from OSB_AssetReceipt where ");
			sb.append("ownerClassNameId = ");
			sb.append(PortalUtil.getClassNameId(CorpEntry.class));

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			count = 0;

			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				throw new UpgradeException(
					"Unable to update the owner class name ID for " + count +
						" asset receipts");
			}

			// Asset receipt license

			sb = new StringBundler(3);

			sb.append("select count(*) from OSB_AssetReceiptLicense where ");
			sb.append("ownerClassNameId = ");
			sb.append(PortalUtil.getClassNameId(CorpEntry.class));

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			count = 0;

			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				throw new UpgradeException(
					"Unable to update the owner class name ID for " + count +
						" asset receipt licenses");
			}

			// Asset receipt support

			sb = new StringBundler(3);

			sb.append("select count(*) from OSB_AssetReceiptSupport where ");
			sb.append("ownerClassNameId = ");
			sb.append(PortalUtil.getClassNameId(CorpEntry.class));

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			count = 0;

			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				throw new UpgradeException(
					"Unable to update the owner class name ID for " + count +
						" asset receipt supports");
			}

			// E-commerce document entry

			sb = new StringBundler(4);

			sb.append("select count(*) from ECommerce_ECDocumentEntry where ");
			sb.append("extraSettings like 'ownerClassName=");
			sb.append(PortalUtil.getClassNameId(CorpEntry.class));
			sb.append("%'");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			count = 0;

			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				throw new UpgradeException(
					"Unable to set extraSettings for " + count +
						" e-commerce document entries");
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

}
*/

}