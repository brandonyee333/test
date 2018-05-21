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

package com.liferay.osb.hook.upgrade.v3_2_8;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class Upgrade_20150611162042197_OfferingEntry
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150611162042197L;
	}

	protected void deleteOfferingEntry(OfferingEntry offeringEntry)
		throws Exception {

		runSQL(
			"delete from OSB_OfferingEntry where offeringEntryId = " +
				offeringEntry.getOfferingEntryId());
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("OSB_OfferingEntry", "quantity")) {
			runSQL("alter table OSB_OfferingEntry add column quantity INTEGER");

			runSQL("update OSB_OfferingEntry set quantity = 1");
		}

		mergeOfferingEntries();
	}

	protected void mergeOfferingEntries() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			StringBundler sb = new StringBundler(9);

			sb.append("select OSB_OrderEntry.orderEntryId from ");
			sb.append("OSB_OrderEntry where OSB_OrderEntry.orderEntryId in ");
			sb.append("(select OSB_OfferingEntry.orderEntryId from ");
			sb.append("OSB_OfferingEntry where OSB_OfferingEntry.type_ != ");
			sb.append(OfferingEntryConstants.TYPE_TRIAL);
			sb.append(" and OSB_OfferingEntry.orderEntryId = ");
			sb.append("OSB_OrderEntry.orderEntryId group by ");
			sb.append("OSB_OfferingEntry.offeringDefinitionId having ");
			sb.append("count(*) > 1)");

			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				mergeOfferingEntries(rs.getLong("orderEntryId"));
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void mergeOfferingEntries(long orderEntryId) throws Exception {
		Map<String, OfferingEntry> mergedOfferingEntries = new HashMap<>();

		List<OfferingEntry> offeringEntries =
			OfferingEntryLocalServiceUtil.getOrderEntryOfferingEntries(
				orderEntryId);

		for (OfferingEntry offeringEntry : offeringEntries) {
			if (offeringEntry.getType() == OfferingEntryConstants.TYPE_TRIAL) {
				continue;
			}

			/*String key =
				offeringEntry.getOfferingDefinitionId() + "#" +
					offeringEntry.getType();

			if (mergedOfferingEntries.containsKey(key)) {
				OfferingEntry mergedOfferingEntry = mergedOfferingEntries.get(
					key);

				mergedOfferingEntry.setQuantity(
					mergedOfferingEntry.getQuantity() + 1);

				transferLicenseKeys(
					offeringEntry.getOfferingEntryId(),
					mergedOfferingEntry.getOfferingEntryId());
				transferTicketEntries(
					offeringEntry.getOfferingEntryId(),
					mergedOfferingEntry.getOfferingEntryId());

				deleteOfferingEntry(offeringEntry);
			}
			else {
				mergedOfferingEntries.put(key, offeringEntry);
			}*/
		}

		for (OfferingEntry offeringEntry : mergedOfferingEntries.values()) {
			OfferingEntryLocalServiceUtil.updateOfferingEntry(offeringEntry);
		}
	}

	protected void transferLicenseKeys(
			long fromOfferingEntryId, long toOfferingEntryId)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		sb.append("update OSB_LicenseKey set offeringEntryId = ");
		sb.append(toOfferingEntryId);
		sb.append(" where offeringEntryId = ");
		sb.append(fromOfferingEntryId);

		runSQL(sb.toString());
	}

}