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

package com.liferay.osb.hook.upgrade.v3_2_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AuditEntry;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.service.AuditEntryLocalServiceUtil;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

*/

/**
 * @author Kyle Bischof
 */
public class Upgrade_20150326091908799_AuditEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20150326091908799L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<TicketEntry> ticketEntries =
			TicketEntryLocalServiceUtil.getTicketEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(
			TicketEntry.class.getName());

		int[] visibilities = new int[] {
			VisibilityConstants.ADMIN, VisibilityConstants.LIFERAY_INC,
			VisibilityConstants.PUBLIC, VisibilityConstants.WORKERS
		};

		for (TicketEntry ticketEntry : ticketEntries) {
			List<AuditEntry> auditEntries =
				AuditEntryLocalServiceUtil.getAuditEntries(
					classNameId, ticketEntry.getTicketEntryId(), visibilities);

			if (!auditEntries.isEmpty()) {
				continue;
			}

			StringBundler sb = new StringBundler(25);

			sb.append("insert into OSB_AuditEntry (auditEntryId, userId, ");
			sb.append("userName, createDate, classNameId, classPK, ");
			sb.append("previousAuditEntryId, auditSetId, fieldClassNameId, ");
			sb.append("fieldClassPK, action, field, visibility, oldLabel, ");
			sb.append("oldValue, newLabel, newValue, i18n) values ('");
			sb.append(increment());
			sb.append("', '");
			sb.append(OSBConstants.USER_DEFAULT_USER_ID);
			sb.append("', '', '");

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

			sb.append(simpleDateFormat.format(new Date()));

			sb.append("', '");
			sb.append(classNameId);
			sb.append("', '");
			sb.append(ticketEntry.getTicketEntryId());
			sb.append("', '0', '1', '");
			sb.append(PortalUtil.getClassNameId(TicketEntry.class.getName()));
			sb.append("', '");
			sb.append(ticketEntry.getTicketEntryId());
			sb.append("', '");
			sb.append(AuditEntryConstants.ACTION_ADD);
			sb.append("', '");
			sb.append(AuditEntryConstants.FIELD_USER);
			sb.append("', '");
			sb.append(VisibilityConstants.ADMIN);
			sb.append("', '', '', '', '', '0')");

			runSQL(sb.toString());
		}
	}

}
*/

}