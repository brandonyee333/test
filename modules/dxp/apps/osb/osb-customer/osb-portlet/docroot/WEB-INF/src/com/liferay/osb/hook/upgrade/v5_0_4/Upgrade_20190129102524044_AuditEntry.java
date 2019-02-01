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

package com.liferay.osb.hook.upgrade.v5_0_4;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import java.text.Format;

/**
 * @author Jenny Chen
 */
public class Upgrade_20190129102524044_AuditEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20190129102524044L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!tableHasColumn("OSB_AuditEntry", "description")) {
			runSQL(
				"alter table OSB_AuditEntry add column description " +
					"VARCHAR(255) null");
		}

		updateAuditEntries();
	}

	protected void updateAuditEntries() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBundler sb = new StringBundler(13);

		sb.append("select OSB_AuditEntry.auditEntryId, ");
		sb.append("OSB_OfferingEntry.offeringEntryId, OSB_ProductEntry.name, ");
		sb.append("OSB_OrderEntry.startDate from OSB_AuditEntry inner join ");
		sb.append("OSB_OfferingEntry on OSB_AuditEntry.fieldClassPK = ");
		sb.append("OSB_OfferingEntry.offeringEntryId inner join ");
		sb.append("OSB_OrderEntry on OSB_OfferingEntry.orderEntryId = ");
		sb.append("OSB_OrderEntry.orderEntryId inner join OSB_ProductEntry ");
		sb.append("on OSB_OfferingEntry.productEntryId = ");
		sb.append("OSB_ProductEntry.productEntryId where ");
		sb.append("OSB_AuditEntry.fieldClassNameId = ");
		sb.append(_OFFERING_ENTRY_CLASS_NAME_ID);
		sb.append(" and OSB_AuditEntry.field = ");
		sb.append(AuditEntryConstants.FIELD_STATUS);

		try {
			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long auditEntryId = rs.getLong("auditEntryId");
				long offeringEntryId = rs.getLong("offeringEntryId");

				String productEntryName = rs.getString("name");

				Timestamp timestamp = rs.getTimestamp("startDate");

				String startDate = _dateFormat.format(timestamp);

				String description =
					"ID: " + String.valueOf(offeringEntryId) + ", Name: " +
						productEntryName + ", Start Date: " + startDate;

				runSQL(
					"update OSB_AuditEntry set description = '" + description +
						"' where auditEntryId = " + auditEntryId);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	private static final long _OFFERING_ENTRY_CLASS_NAME_ID = 1400969;

	private final Format _dateFormat =
		FastDateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

}