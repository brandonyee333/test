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

package com.liferay.osb.hook.upgrade.v3_3_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.ecommerce.model.ECDocumentEntryConstants;
import com.liferay.ecommerce.service.ECDocumentEntryLocalServiceUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.marketplace.util.ECDocumentEntryExtraSettings;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

*/

/**
 * @author Joan Kim
 */
public class Upgrade_20150721162723892_ECDocumentEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20150721162723892L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			StringBundler sb = new StringBundler(5);

			sb.append("select ecDocumentEntryId from ");
			sb.append("ECommerce_ECDocumentEntry where status <>");
			sb.append(ECDocumentEntryConstants.STATUS_UNPAID);
			sb.append(" and extraSettings not like ");
			sb.append("'%marketplaceDeveloperEntryId%'");

			ps = connection.prepareStatement(sb.toString());

			rs = ps.executeQuery();

			while (rs.next()) {
				long ecDocumentEntryId = rs.getLong("ecDocumentEntryId");

				ECDocumentEntry ecDocumentEntry =
					ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
						ecDocumentEntryId);

				ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
					new ECDocumentEntryExtraSettings(ecDocumentEntry);

				AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
					ecDocumentEntryExtraSettings.getAppEntryId());

				ecDocumentEntryExtraSettings.setDeveloperEntryId(
					appEntry.getDeveloperEntryId());

				ECDocumentEntryLocalServiceUtil.updateECDocumentEntry(
					ecDocumentEntryId, ecDocumentEntryExtraSettings.toString());
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	 */

}