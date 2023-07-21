/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.content.web.dynamic.data.mapping.util;

import com.liferay.dynamic.data.mapping.util.DDMDisplay;
import com.liferay.journal.constants.JournalContentPortletKeys;
import com.liferay.journal.web.dynamic.data.mapping.util.JournalDDMDisplay;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eudaldo Alonso
 */
@Component(
	property = "javax.portlet.name=" + JournalContentPortletKeys.JOURNAL_CONTENT,
	service = DDMDisplay.class
)
public class JournalContentDDMDisplay extends JournalDDMDisplay {

	@Override
	public String getPortletId() {
		return JournalContentPortletKeys.JOURNAL_CONTENT;
	}

	@Override
	public boolean isShowConfirmSelectTemplate() {
		return false;
	}

}