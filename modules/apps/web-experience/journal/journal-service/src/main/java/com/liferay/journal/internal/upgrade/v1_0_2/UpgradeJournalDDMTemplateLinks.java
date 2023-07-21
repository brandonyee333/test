/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.upgrade.v1_0_2;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Roberto Díaz
 */
public class UpgradeJournalDDMTemplateLinks extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			StringBundler sb = new StringBundler(6);

			sb.append("update DDMTemplateLink set classNameId = ");
			sb.append(
				PortalUtil.getClassNameId(JournalArticle.class.getName()));
			sb.append(" where DDMTemplateLink.classNameId = ");
			sb.append(PortalUtil.getClassNameId(DDMStructure.class.getName()));
			sb.append(" and exists (select id_ from JournalArticle where ");
			sb.append("DDMTemplateLink.classPK = JournalArticle.id_)");

			runSQL(sb.toString());
		}
	}

}