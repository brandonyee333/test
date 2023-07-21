/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.exportimport.lifecycle;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lifecycle.BaseExportImportLifecycleListener;
import com.liferay.exportimport.kernel.lifecycle.ExportImportLifecycleListener;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.util.JournalContent;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Máté Thurzó
 */
@Component(service = ExportImportLifecycleListener.class)
public class JournalCacheExportImportLifecycleListener
	extends BaseExportImportLifecycleListener {

	@Override
	public boolean isParallel() {
		return false;
	}

	protected void clearCache() {
		_journalContent.clearCache();
	}

	@Override
	protected void onLayoutImportProcessFinished(
			PortletDataContext portletDataContext)
		throws Exception {

		clearCache();
	}

	@Override
	protected void onPortletImportProcessFinished(
			PortletDataContext portletDataContext)
		throws Exception {

		String rootPortletId = portletDataContext.getRootPortletId();

		if (!rootPortletId.equals(JournalPortletKeys.JOURNAL)) {
			return;
		}

		clearCache();
	}

	@Reference(unbind = "-")
	protected void setJournalContent(JournalContent journalContent) {
		_journalContent = journalContent;
	}

	private JournalContent _journalContent;

}