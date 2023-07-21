/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.upgrade.v1_1_0;

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.wsrp.internal.consumer.portlet.ConsumerPortlet;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Michael Young
 */
public class UpgradeUuid extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		List<WSRPConsumerPortlet> wsrpConsumerPortlets =
			WSRPConsumerPortletLocalServiceUtil.getWSRPConsumerPortlets(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (WSRPConsumerPortlet wsrpConsumerPortlet : wsrpConsumerPortlets) {
			StringBuilder sb = new StringBuilder(4);

			sb.append(ConsumerPortlet.PORTLET_NAME_PREFIX);
			sb.append(wsrpConsumerPortlet.getCompanyId());
			sb.append(StringPool.UNDERLINE);
			sb.append(wsrpConsumerPortlet.getWsrpConsumerPortletId());

			String oldPortletId = PortalUtil.getJsSafePortletId(sb.toString());

			String newPortletId = ConsumerPortlet.PORTLET_NAME_PREFIX.concat(
				wsrpConsumerPortlet.getUuid());

			newPortletId = PortalUtil.getJsSafePortletId(
				PortalUUIDUtil.toJsSafeUuid(newPortletId));

			updateLayout(oldPortletId, newPortletId);
			updateResourceAction(oldPortletId, newPortletId);
			updateResourcePermission(oldPortletId, newPortletId);
		}
	}

	protected void updateLayout(long plid, String typeSettings)
		throws Exception {

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(
				"update Layout set typeSettings = ? where plid = " + plid);

			ps.setString(1, typeSettings);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

	protected void updateLayout(String oldPortletId, String newPortletId)
		throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select plid, typeSettings from Layout where typeSettings " +
					"like ?");

			ps.setString(
				1, StringPool.PERCENT + oldPortletId + StringPool.PERCENT);

			rs = ps.executeQuery();

			while (rs.next()) {
				long plid = rs.getLong("plid");

				String typeSettings = rs.getString("typeSettings");

				typeSettings = StringUtil.replace(
					typeSettings, oldPortletId, newPortletId);

				updateLayout(plid, typeSettings);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateResourceAction(
			String oldPortletId, String newPortletId)
		throws Exception {

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(
				"update ResourceAction set name = ? where name = ? ");

			ps.setString(1, newPortletId);
			ps.setString(2, oldPortletId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

	protected void updateResourcePermission(
			long resourcePermissionId, String name, String primKey)
		throws Exception {

		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(
				"update ResourcePermission set name = ?, primKey = ? where " +
					"resourcePermissionId = ?");

			ps.setString(1, name);
			ps.setString(2, primKey);
			ps.setLong(3, resourcePermissionId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

	protected void updateResourcePermission(
			String oldPortletId, String newPortletId)
		throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select resourcePermissionId, primKey from " +
					"ResourcePermission where name = ?");

			ps.setString(1, oldPortletId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long resourcePermissionId = rs.getLong("resourcePermissionId");

				String primKey = rs.getString("primKey");

				primKey = StringUtil.replace(
					primKey, oldPortletId, newPortletId);

				updateResourcePermission(
					resourcePermissionId, newPortletId, primKey);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

}