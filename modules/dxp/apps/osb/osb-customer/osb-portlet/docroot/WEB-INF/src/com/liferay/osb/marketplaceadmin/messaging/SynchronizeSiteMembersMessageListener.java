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

package com.liferay.osb.marketplaceadmin.messaging;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.osb.model.DeveloperEntryConstants;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter Shin
 */
public class SynchronizeSiteMembersMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {

		// Corp entry

		List<Long> userIdsList = getCorpEntryDeveloperAddSiteMemberUserIds();

		UserLocalServiceUtil.addGroupUsers(
			OSBConstants.GROUP_MARKETPLACE_DEVELOPER_PORTAL_ID,
			StringUtil.split(StringUtil.merge(userIdsList), 0L));

		// User

		userIdsList = getUserDeveloperAddSiteMemberUserIds();

		UserLocalServiceUtil.addGroupUsers(
			OSBConstants.GROUP_MARKETPLACE_DEVELOPER_PORTAL_ID,
			StringUtil.split(StringUtil.merge(userIdsList), 0L));

		// Remove

		userIdsList = getRemoveSiteMemberUserIds();

		UserLocalServiceUtil.unsetGroupUsers(
			OSBConstants.GROUP_MARKETPLACE_DEVELOPER_PORTAL_ID,
			StringUtil.split(StringUtil.merge(userIdsList), 0L), null);
	}

	protected List<Long> getCorpEntryDeveloperAddSiteMemberUserIds()
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			String sql = CustomSQLUtil.get(
				_FIND_BY_CORP_ENTRY_DEVELOPER_ADD_SITE_MEMBER_USER_IDS);

			long[] roleIds = {
				OSBConstants.ROLE_OSB_CORP_ADMIN_ID,
				OSBConstants.ROLE_OSB_CORP_DEVELOPER_ID
			};

			sql = StringUtil.replace(
				sql, "[$ROLE_IDS$]", StringUtil.merge(roleIds));

			ps = con.prepareStatement(sql);

			ps.setLong(1, OSBConstants.ORGANIZATION_CORPORATION_PARENT_ID);
			ps.setInt(2, WorkflowConstants.STATUS_APPROVED);
			ps.setInt(3, DeveloperEntryConstants.TYPE_COMPANY);
			ps.setInt(4, WorkflowConstants.STATUS_APPROVED);
			ps.setLong(5, OSBConstants.GROUP_MARKETPLACE_DEVELOPER_PORTAL_ID);

			rs = ps.executeQuery();

			List<Long> userIds = new ArrayList<Long>();

			while (rs.next()) {
				long userId = rs.getLong(1);

				userIds.add(userId);
			}

			return userIds;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected List<Long> getRemoveSiteMemberUserIds() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			String sql = CustomSQLUtil.get(
				_FIND_BY_REMOVE_SITE_MEMBER_USER_IDS);

			long[] roleIds = {
				OSBConstants.ROLE_OSB_CORP_ADMIN_ID,
				OSBConstants.ROLE_OSB_CORP_DEVELOPER_ID
			};

			sql = StringUtil.replace(
				sql, "[$ROLE_IDS$]", StringUtil.merge(roleIds));

			ps = con.prepareStatement(sql);

			ps.setLong(1, OSBConstants.GROUP_MARKETPLACE_DEVELOPER_PORTAL_ID);
			ps.setLong(2, OSBConstants.ORGANIZATION_CORPORATION_PARENT_ID);
			ps.setInt(3, WorkflowConstants.STATUS_APPROVED);
			ps.setInt(4, DeveloperEntryConstants.TYPE_COMPANY);
			ps.setInt(5, WorkflowConstants.STATUS_APPROVED);
			ps.setInt(6, DeveloperEntryConstants.TYPE_USER);
			ps.setInt(7, WorkflowConstants.STATUS_APPROVED);

			rs = ps.executeQuery();

			List<Long> userIds = new ArrayList<Long>();

			while (rs.next()) {
				long userId = rs.getLong(1);

				userIds.add(userId);
			}

			return userIds;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected List<Long> getUserDeveloperAddSiteMemberUserIds()
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			String sql = CustomSQLUtil.get(
				_FIND_BY_USER_DEVELOPER_ADD_SITE_MEMBER_USER_IDS);

			ps = con.prepareStatement(sql);

			ps.setInt(1, DeveloperEntryConstants.TYPE_USER);
			ps.setInt(2, WorkflowConstants.STATUS_APPROVED);
			ps.setLong(3, OSBConstants.GROUP_MARKETPLACE_DEVELOPER_PORTAL_ID);

			rs = ps.executeQuery();

			List<Long> userIds = new ArrayList<Long>();

			while (rs.next()) {
				long userId = rs.getLong(1);

				userIds.add(userId);
			}

			return userIds;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static final String
		_FIND_BY_CORP_ENTRY_DEVELOPER_ADD_SITE_MEMBER_USER_IDS =
			SynchronizeSiteMembersMessageListener.class.getName() +
				".findByCorpEntryDeveloperAddSiteMemberUserIds";

	private static final String _FIND_BY_REMOVE_SITE_MEMBER_USER_IDS =
		SynchronizeSiteMembersMessageListener.class.getName() +
			".findByRemoveSiteMemberUserIds";

	private static final String
		_FIND_BY_USER_DEVELOPER_ADD_SITE_MEMBER_USER_IDS =
			SynchronizeSiteMembersMessageListener.class.getName() +
				".findByUserDeveloperAddSiteMemberUserIds";

}