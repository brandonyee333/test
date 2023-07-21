/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v6_1_0;

import com.liferay.document.library.kernel.model.DLFileEntryTypeConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Alexander Chow
 */
public class UpgradeWorkflow extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateWorkflowDefinitionLinks();
	}

	protected void updateWorkflowDefinitionLinks() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			StringBundler sb = new StringBundler(6);

			sb.append("update WorkflowDefinitionLink set classNameId = ");

			long folderClassNameId = PortalUtil.getClassNameId(
				"com.liferay.portlet.documentlibrary.model.DLFolder");

			sb.append(folderClassNameId);

			sb.append(", typePK = ");
			sb.append(DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_ALL);
			sb.append(" where classNameId = ");

			long fileEntryClassNameId = PortalUtil.getClassNameId(
				"com.liferay.portlet.documentlibrary.model.DLFileEntry");

			sb.append(fileEntryClassNameId);

			runSQL(sb.toString());
		}
	}

}