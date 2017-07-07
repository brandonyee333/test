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

package com.liferay.osb.hook.upgrade.v3_0_5;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.message.boards.kernel.util.MBUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.message.boards.kernel.NoSuchCategoryException;
import com.liferay.message.boards.kernel.model.MBCategory;
import com.liferay.message.boards.kernel.service.MBCategoryLocalServiceUtil;
import com.liferay.message.boards.kernel.service.MBMailingListLocalServiceUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

*/

/**
 * @author Joan Kim
 */
public class Upgrade_20140709164031998_MBMessage extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20140709164031998L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeMBMessage();

		validateMBMessage();

		upgradeMBCategory();
	}

	protected void upgradeMBCategory() throws Exception {
		try {
			MBCategoryLocalServiceUtil.deleteMBCategory(
				_MB_CATEGORY_COMMUNITY_DEVELOPERS_ID);
		}
		catch (NoSuchCategoryException nsce) {
		}

		MBCategory category = MBCategoryLocalServiceUtil.fetchMBCategory(
			_MB_CATEGORY_COMMUNITY_DEVELOPERS_ID);

		if (category != null) {
			throw new UpgradeException(
				"Unable to delete category " + category.getCategoryId());
		}

		runSQL(
			"update MBCategory set name = REPLACE(name, '3', '2') where " +
				"categoryId = " + _MB_CATEGORY_COMMUNITY_ANNOUNCEMENTS_ID);

		MBUtil.updateMBCategoryStatistics(
			_MB_CATEGORY_COMMUNITY_LIFERAY_MARKETPLACE_ID);

		MBUtil.updateMBCategoryStatistics(
			_MB_CATEGORY_DEVELOPER_PORTAL_DEVELOPERS_ID);
	}

	protected void upgradeMBMessage() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(
				"select * from MBCategory where categoryId = " +
					_MB_CATEGORY_COMMUNITY_DEVELOPERS_ID);

			rs = ps.executeQuery();

			if (!rs.next()) {
				return;
			}

			// Messages

			StringBundler sb = new StringBundler(8);

			sb.append("update MBMessage set groupId = ");
			sb.append(OSBConstants.GROUP_MARKETPLACE_DEVELOPER_PORTAL_ID);
			sb.append(", categoryId = ");
			sb.append(_MB_CATEGORY_DEVELOPER_PORTAL_DEVELOPERS_ID);
			sb.append(" where groupId = ");
			sb.append(OSBConstants.GROUP_GUEST_ID);
			sb.append(" and categoryId = ");
			sb.append(_MB_CATEGORY_COMMUNITY_DEVELOPERS_ID);

			runSQL(sb.toString(), false);

			// Threads

			sb = new StringBundler(8);

			sb.append("update MBThread set groupId = ");
			sb.append(OSBConstants.GROUP_MARKETPLACE_DEVELOPER_PORTAL_ID);
			sb.append(", categoryId = ");
			sb.append(_MB_CATEGORY_DEVELOPER_PORTAL_DEVELOPERS_ID);
			sb.append(" where groupId = ");
			sb.append(OSBConstants.GROUP_GUEST_ID);
			sb.append(" and categoryId = ");
			sb.append(_MB_CATEGORY_COMMUNITY_DEVELOPERS_ID);

			runSQL(sb.toString(), false);

			// Mailing list

			ps = con.prepareStatement(
				"select * from MBMailingList where groupId = " +
					OSBConstants.GROUP_GUEST_ID + " and categoryId = " +
						_MB_CATEGORY_COMMUNITY_DEVELOPERS_ID);

			rs = ps.executeQuery();

			while (rs.next()) {
				sb = new StringBundler(6);

				sb.append("update MBMailingList set groupId = ");
				sb.append(OSBConstants.GROUP_MARKETPLACE_DEVELOPER_PORTAL_ID);
				sb.append(", categoryId = ");
				sb.append(_MB_CATEGORY_DEVELOPER_PORTAL_DEVELOPERS_ID);
				sb.append(" where mailingListId = ");
				sb.append(rs.getLong("mailingListId"));

				runSQL(sb.toString(), false);

				MBMailingListLocalServiceUtil.deleteMBMailingList(
					rs.getLong("mailingListId"));
			}

			// Subscriptions

			sb = new StringBundler(7);

			sb.append("select userId from Subscription where classNameId = ");
			sb.append(PortalUtil.getClassNameId(MBCategory.class));
			sb.append(" and classPK in (");
			sb.append(_MB_CATEGORY_COMMUNITY_DEVELOPERS_ID);
			sb.append(",");
			sb.append(_MB_CATEGORY_DEVELOPER_PORTAL_DEVELOPERS_ID);
			sb.append(") group by userId having count(*) > 1");

			ps = con.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				sb = new StringBundler(6);

				sb.append("delete from Subscription where userId = ");
				sb.append(rs.getLong("userId"));
				sb.append(" and classNameId = ");
				sb.append(PortalUtil.getClassNameId(MBCategory.class));
				sb.append(" and classPK = ");
				sb.append(_MB_CATEGORY_COMMUNITY_DEVELOPERS_ID);

				runSQL(sb.toString());
			}

			sb = new StringBundler(6);

			sb.append("update Subscription set classPK = ");
			sb.append(_MB_CATEGORY_DEVELOPER_PORTAL_DEVELOPERS_ID);
			sb.append(" where classNameId = ");
			sb.append(PortalUtil.getClassNameId(MBCategory.class));
			sb.append(" and classPK = ");
			sb.append(_MB_CATEGORY_COMMUNITY_DEVELOPERS_ID);

			runSQL(sb.toString(), false);

			// Resources

			sb = new StringBundler(6);

			sb.append("update Resource_ set primKey = ");
			sb.append(_MB_CATEGORY_DEVELOPER_PORTAL_DEVELOPERS_ID);
			sb.append(" where codeId = ");
			sb.append(PortalUtil.getClassNameId(MBCategory.class));
			sb.append(" and primKey = ");
			sb.append(_MB_CATEGORY_COMMUNITY_DEVELOPERS_ID);

			runSQL(sb.toString(), false);
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void validateMBMessage() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			// Messages

			ps = con.prepareStatement(
				"select count(*) from MBMessage where categoryId = " +
					_MB_CATEGORY_COMMUNITY_DEVELOPERS_ID);

			rs = ps.executeQuery();

			int count = 0;

			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				throw new UpgradeException(
					"Unable to migrate " + count + " community messages " +
						"to developer portal");
			}

			// Threads

			ps = con.prepareStatement(
				"select count(*) from MBThread where categoryId = " +
					_MB_CATEGORY_COMMUNITY_DEVELOPERS_ID);

			rs = ps.executeQuery();

			count = 0;

			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				throw new UpgradeException(
					"Unable to migrate " + count + " community threads " +
						"to developer portal");
			}

			// Subscriptions

			ps = con.prepareStatement(
				"select count(*) from Subscription where classPK = " +
					_MB_CATEGORY_COMMUNITY_DEVELOPERS_ID);

			rs = ps.executeQuery();

			count = 0;

			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				throw new UpgradeException(
					"Unable to migrate " + count + " community subscriptions " +
						"to developer portal");
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static final long _MB_CATEGORY_COMMUNITY_ANNOUNCEMENTS_ID =
		10919231;

	private static final long _MB_CATEGORY_COMMUNITY_DEVELOPERS_ID = 10919228;

	private static final long _MB_CATEGORY_COMMUNITY_LIFERAY_MARKETPLACE_ID =
		10919192;

	private static final long _MB_CATEGORY_DEVELOPER_PORTAL_DEVELOPERS_ID =
		33575418;

}
*/

}