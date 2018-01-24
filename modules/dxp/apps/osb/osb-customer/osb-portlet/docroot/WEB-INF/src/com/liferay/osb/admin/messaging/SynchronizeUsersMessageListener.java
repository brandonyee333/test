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

package com.liferay.osb.admin.messaging;

import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

/**
 * @author Amos Fong
 */
public class SynchronizeUsersMessageListener extends BaseMessageListener {

	protected void addAccountWorkers(long accountEntryId, String getUserIdsSQL)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(5);

			sb.append(getUserIdsSQL);

			if (getUserIdsSQL.contains("where")) {
				sb.append(" and ");
			}
			else {
				sb.append(" where ");
			}

			sb.append("User_.userId not in (select OSB_AccountWorker.userId ");
			sb.append("from OSB_AccountWorker where ");
			sb.append("OSB_AccountWorker.accountEntryId = ?)");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			ps.setLong(1, accountEntryId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long userId = rs.getLong("userId");

				AccountWorkerLocalServiceUtil.addAccountWorkers(
					OSBConstants.USER_DEFAULT_USER_ID, new long[] {userId},
					accountEntryId, new int[] {0},
					new int[] {AccountWorkerConstants.NOTIFICATIONS_NONE});
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void addOrganizationUsers(
			long organizationId, String getUserIdsSQL, String userIdColumn)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(5);

			sb.append(getUserIdsSQL);

			if (getUserIdsSQL.indexOf("where") != -1) {
				sb.append(" and ");
			}
			else {
				sb.append(" where ");
			}

			sb.append(userIdColumn);
			sb.append(" not in (select Users_Orgs.userId from Users_Orgs ");
			sb.append("where Users_Orgs.organizationId = ?)");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			ps.setLong(1, organizationId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long userId = rs.getLong("userId");

				UserLocalServiceUtil.addOrganizationUsers(
					organizationId, new long[] {userId});
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void addRoleUsers(long roleId, String getUserIdsSQL)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			StringBundler sb = new StringBundler(3);

			sb.append(getUserIdsSQL);
			sb.append(" and User_.userId not in (select Users_Roles.userId ");
			sb.append("from Users_Roles where Users_Roles.roleId = ?)");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			ps.setLong(1, roleId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long userId = rs.getLong("userId");

				UserLocalServiceUtil.addRoleUsers(roleId, new long[] {userId});
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		StringBundler sb = new StringBundler(21);

		sb.append("select distinct(OSB_AccountCustomer.userId) from ");
		sb.append("OSB_AccountCustomer inner join Users_Roles on ");
		sb.append("Users_Roles.userId = OSB_AccountCustomer.userId inner ");
		sb.append("join OSB_AccountEntry on OSB_AccountEntry.accountEntryId ");
		sb.append("= OSB_AccountCustomer.accountEntryId left join ");
		sb.append("OSB_OfferingEntry on OSB_OfferingEntry.accountEntryId = ");
		sb.append("OSB_AccountCustomer.accountEntryId where ");
		sb.append("(Users_Roles.roleId = '");
		sb.append(OSBConstants.ROLE_VERIFIED_USER_ID);
		sb.append("') and (OSB_AccountEntry.type_ != '");
		sb.append(AccountEntryConstants.TYPE_INTERNAL_TEST);
		sb.append("') and (OSB_AccountEntry.type_ != '");
		sb.append(AccountEntryConstants.TYPE_TRIAL);
		sb.append("') and ((OSB_OfferingEntry.status = '");
		sb.append(OfferingEntryConstants.STATUS_ACTIVE);
		sb.append("') or (OSB_OfferingEntry.supportEndDate is null)) and ");
		sb.append("(OSB_AccountCustomer.userId not in (select ");
		sb.append("Users_Orgs.userId from Users_Orgs where ");
		sb.append("Users_Orgs.organizationId = '");
		sb.append(OSBConstants.ORGANIZATION_LIFERAY_INC_ID);
		sb.append("'))");

		updateOrganizationUserIds(
			OSBConstants.ORGANIZATION_CUSTOMER_ID, sb.toString(),
			"OSB_AccountCustomer.userId");

		sb = new StringBundler(13);

		sb.append("select distinct(User_.userId) from User_ inner join ");
		sb.append("Users_Roles on Users_Roles.userId = User_.userId inner ");
		sb.append("join OSB_PartnerWorker on OSB_PartnerWorker.userId = ");
		sb.append("User_.userId inner join OSB_PartnerEntry on ");
		sb.append("OSB_PartnerEntry.partnerEntryId = ");
		sb.append("OSB_PartnerWorker.partnerEntryId where ");
		sb.append("(Users_Roles.roleId = '");
		sb.append(OSBConstants.ROLE_VERIFIED_USER_ID);
		sb.append("') and (OSB_PartnerEntry.status = ");
		sb.append(WorkflowConstants.STATUS_APPROVED);
		sb.append(") and (User_.status = ");
		sb.append(WorkflowConstants.STATUS_APPROVED);
		sb.append(")");

		updateOrganizationUserIds(
			OSBConstants.ORGANIZATION_PARTNER_ID, sb.toString(),
			"OSB_PartnerWorker.userId");

		sb = new StringBundler(6);

		sb.append("select distinct(User_.userId) from User_ inner join ");
		sb.append("Users_Orgs on User_.userId = Users_Orgs.userId where ");
		sb.append("Users_Orgs.organizationId = ");
		sb.append(OSBConstants.ORGANIZATION_LIFERAY_INC_ID);
		sb.append(" and User_.status = ");
		sb.append(WorkflowConstants.STATUS_APPROVED);

		updateAccountWorkers(
			OSBConstants.ACCOUNT_ENTRY_LRDCOM_ID, sb.toString());

		updateRoles(OSBConstants.ROLE_LIFERAY_EMPLOYEE_ID, sb.toString());

		sb = new StringBundler(20);

		sb.append("select distinct(OSB_AccountCustomer.userId) from ");
		sb.append("OSB_AccountCustomer inner join Users_Roles on ");
		sb.append("Users_Roles.userId = OSB_AccountCustomer.userId inner ");
		sb.append("join OSB_AccountEntry on OSB_AccountEntry.accountEntryId ");
		sb.append("= OSB_AccountCustomer.accountEntryId inner join ");
		sb.append("OSB_OfferingEntry on OSB_OfferingEntry.accountEntryId = ");
		sb.append("OSB_AccountCustomer.accountEntryId where ");
		sb.append("(Users_Roles.roleId = '");
		sb.append(OSBConstants.ROLE_VERIFIED_USER_ID);
		sb.append("') and (OSB_AccountEntry.type_ != '");
		sb.append(AccountEntryConstants.TYPE_TRIAL);
		sb.append("') and (OSB_OfferingEntry.version = ");
		sb.append(ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		sb.append(") and (OSB_OfferingEntry.status = ");
		sb.append(OfferingEntryConstants.STATUS_ACTIVE);
		sb.append(") and (OSB_AccountCustomer.userId not in (select ");
		sb.append("Users_Orgs.userId from Users_Orgs where ");
		sb.append("Users_Orgs.organizationId = '");
		sb.append(OSBConstants.ORGANIZATION_LIFERAY_INC_ID);
		sb.append("'))");

		updateOrganizationUserIds(
			OSBConstants.ORGANIZATION_CUSTOMER_DXP_ID, sb.toString(),
			"OSB_AccountCustomer.userId");

		sb = new StringBundler(22);

		sb.append("select distinct(OSB_AccountCustomer.userId) from ");
		sb.append("OSB_AccountCustomer inner join Users_Roles on ");
		sb.append("Users_Roles.userId = OSB_AccountCustomer.userId inner ");
		sb.append("join OSB_AccountEntry on OSB_AccountEntry.accountEntryId ");
		sb.append("= OSB_AccountCustomer.accountEntryId inner join ");
		sb.append("OSB_OfferingEntry on OSB_OfferingEntry.accountEntryId = ");
		sb.append("OSB_AccountCustomer.accountEntryId where ");
		sb.append("(Users_Roles.roleId = '");
		sb.append(OSBConstants.ROLE_VERIFIED_USER_ID);
		sb.append("') and (OSB_AccountEntry.type_ != '");
		sb.append(AccountEntryConstants.TYPE_TRIAL);
		sb.append("') and ((OSB_OfferingEntry.version = ");
		sb.append(ProductEntryConstants.PORTAL_VERSION_6);
		sb.append(") or (OSB_OfferingEntry.version = ");
		sb.append(ProductEntryConstants.PORTAL_VERSION_5);
		sb.append(")) and (OSB_OfferingEntry.status = ");
		sb.append(OfferingEntryConstants.STATUS_ACTIVE);
		sb.append(") and (OSB_AccountCustomer.userId not in (select ");
		sb.append("Users_Orgs.userId from Users_Orgs where ");
		sb.append("Users_Orgs.organizationId = '");
		sb.append(OSBConstants.ORGANIZATION_LIFERAY_INC_ID);
		sb.append("'))");

		updateOrganizationUserIds(
			OSBConstants.ORGANIZATION_CUSTOMER_PORTAL_ID, sb.toString(),
			"OSB_AccountCustomer.userId");

		sb = new StringBundler(21);

		sb.append("select distinct(OSB_AccountCustomer.userId) from ");
		sb.append("OSB_AccountCustomer inner join Users_Roles on ");
		sb.append("Users_Roles.userId = OSB_AccountCustomer.userId inner ");
		sb.append("join OSB_AccountEntry on OSB_AccountEntry.accountEntryId =");
		sb.append("OSB_AccountCustomer.accountEntryId inner join ");
		sb.append("OSB_OfferingEntry on OSB_OfferingEntry.accountEntryId = ");
		sb.append("OSB_AccountCustomer.accountEntryId inner join ");
		sb.append("OSB_ProductEntry on OSB_ProductEntry.productEntryId = ");
		sb.append("OSB_OfferingEntry.productEntryId where ");
		sb.append("(Users_Roles.roleId = '");
		sb.append(OSBConstants.ROLE_VERIFIED_USER_ID);
		sb.append("') and (OSB_AccountEntry.type_ != '");
		sb.append(AccountEntryConstants.TYPE_TRIAL);
		sb.append("') and OSB_ProductEntry.name like 'Enterprise Search - ");
		sb.append("Standard%' and (OSB_OfferingEntry.status = ");
		sb.append(OfferingEntryConstants.STATUS_ACTIVE);
		sb.append(") and (OSB_AccountCustomer.userId not in (select ");
		sb.append("Users_Orgs.userId from Users_Orgs where ");
		sb.append("Users_Orgs.organizationId = '");
		sb.append(OSBConstants.ORGANIZATION_LIFERAY_INC_ID);
		sb.append("'))");

		updateOrganizationUserIds(
			OSBConstants.ORGANIZATION_CUSTOMER_SEARCH_STANDARD_ID,
			sb.toString(), "OSB_AccountCustomer.userId");

		sb = new StringBundler(21);

		sb.append("select distinct(OSB_AccountCustomer.userId) from ");
		sb.append("OSB_AccountCustomer inner join Users_Roles on ");
		sb.append("Users_Roles.userId = OSB_AccountCustomer.userId inner ");
		sb.append("join OSB_AccountEntry on OSB_AccountEntry.accountEntryId ");
		sb.append("= OSB_AccountCustomer.accountEntryId inner join ");
		sb.append("OSB_OfferingEntry on OSB_OfferingEntry.accountEntryId = ");
		sb.append("OSB_AccountCustomer.accountEntryId inner join ");
		sb.append("OSB_ProductEntry on OSB_ProductEntry.productEntryId = ");
		sb.append("OSB_OfferingEntry.productEntryId where ");
		sb.append("(Users_Roles.roleId = '");
		sb.append(OSBConstants.ROLE_VERIFIED_USER_ID);
		sb.append("') and (OSB_AccountEntry.type_ != '");
		sb.append(AccountEntryConstants.TYPE_TRIAL);
		sb.append("') and OSB_ProductEntry.name like 'Enterprise Search - ");
		sb.append("Premium%' and (OSB_OfferingEntry.status = ");
		sb.append(OfferingEntryConstants.STATUS_ACTIVE);
		sb.append(") and (OSB_AccountCustomer.userId not in (select ");
		sb.append("Users_Orgs.userId from Users_Orgs where ");
		sb.append("Users_Orgs.organizationId = '");
		sb.append(OSBConstants.ORGANIZATION_LIFERAY_INC_ID);
		sb.append("'))");

		updateOrganizationUserIds(
			OSBConstants.ORGANIZATION_CUSTOMER_SEARCH_PREMIUM_ID, sb.toString(),
			"OSB_AccountCustomer.userId");

		sb = new StringBundler(11);

		sb.append("select distinct(OSB_AccountCustomer.userId) from ");
		sb.append("OSB_AccountCustomer inner join OSB_AccountEntry on ");
		sb.append("OSB_AccountEntry.accountEntryId = ");
		sb.append("OSB_AccountCustomer.accountEntryId inner join ");
		sb.append("OSB_OfferingEntry on OSB_OfferingEntry.accountEntryId = ");
		sb.append("OSB_AccountCustomer.accountEntryId where ");
		sb.append("(OSB_AccountEntry.type_ = '");
		sb.append(AccountEntryConstants.TYPE_TRIAL);
		sb.append("') and (OSB_OfferingEntry.status = ");
		sb.append(OfferingEntryConstants.STATUS_ACTIVE);
		sb.append(") and (OSB_OfferingEntry.supportEndDate > now())");

		updateOrganizationUserIds(
			OSBConstants.ORGANIZATION_TRIAL_ID, sb.toString(),
			"OSB_AccountCustomer.userId");
	}

	protected long[] getUserIds(String getUserIdsSQL, String userIdColumn) {

		// Abstract this

		return new long[0];
	}

	protected void removeAccountWorkers(
			long accountEntryId, String getUserIdsSQL)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			DataSource dataSource = InfrastructureUtil.getDataSource();

			con = dataSource.getConnection();

			StringBundler sb = new StringBundler(6);

			sb.append("select OSB_AccountWorker.userId from ");
			sb.append("OSB_AccountWorker where ");
			sb.append("OSB_AccountWorker.accountEntryId = ? and ");
			sb.append("OSB_AccountWorker.userId not in (");
			sb.append(getUserIdsSQL);
			sb.append(")");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			ps.setLong(1, accountEntryId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long userId = rs.getLong("userId");

				AccountWorkerLocalServiceUtil.deleteAccountWorkers(
					OSBConstants.USER_DEFAULT_USER_ID, new long[] {userId},
					accountEntryId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void removeOrganizationUsers(
			long organizationId, String getUserIdsSQL)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			DataSource dataSource = InfrastructureUtil.getDataSource();

			con = dataSource.getConnection();

			StringBundler sb = new StringBundler(5);

			sb.append("select userId from Users_Orgs where organizationId = ");
			sb.append(organizationId);
			sb.append(" and userId not in (");
			sb.append(getUserIdsSQL);
			sb.append(")");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long userId = rs.getLong(1);

				UserLocalServiceUtil.unsetOrganizationUsers(
					organizationId, new long[] {userId});
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void removeRoleUsers(long roleId, String getUserIdsSQL)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			DataSource dataSource = InfrastructureUtil.getDataSource();

			con = dataSource.getConnection();

			StringBundler sb = new StringBundler(4);

			sb.append("select Users_Roles.userId from Users_Roles where ");
			sb.append("Users_Roles.roleId = ? and Users_Roles.userId not in (");
			sb.append(getUserIdsSQL);
			sb.append(")");

			String sql = sb.toString();

			ps = con.prepareStatement(sql);

			ps.setLong(1, roleId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long userId = rs.getLong("userId");

				UserLocalServiceUtil.deleteRoleUser(roleId, userId);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateAccountWorkers(
			long accountEntryId, String getUserIdsSQL)
		throws Exception {

		addAccountWorkers(accountEntryId, getUserIdsSQL);
		removeAccountWorkers(accountEntryId, getUserIdsSQL);
	}

	protected void updateOrganizationUserIds(
			long organizationId, String getUserIdsSQL, String userIdColumn)
		throws Exception {

		addOrganizationUsers(organizationId, getUserIdsSQL, userIdColumn);
		removeOrganizationUsers(organizationId, getUserIdsSQL);
	}

	protected void updateRoles(long roleId, String getUserIdsSQL)
		throws Exception {

		addRoleUsers(roleId, getUserIdsSQL);
		removeRoleUsers(roleId, getUserIdsSQL);
	}

}