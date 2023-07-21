/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.marketplace.app.manager.web.internal.dao.search;

import com.liferay.marketplace.app.manager.web.internal.util.AppDisplay;
import com.liferay.marketplace.app.manager.web.internal.util.ModuleGroupDisplay;
import com.liferay.portal.kernel.dao.search.ResultRow;
import com.liferay.portal.kernel.dao.search.ResultRowSplitter;
import com.liferay.portal.kernel.dao.search.ResultRowSplitterEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan Park
 */
public class MarketplaceAppManagerResultRowSplitter
	implements ResultRowSplitter {

	@Override
	public List<ResultRowSplitterEntry> split(List<ResultRow> resultRows) {
		List<ResultRowSplitterEntry> resultRowSplitterEntries =
			new ArrayList<>();

		List<ResultRow> appSuiteResultRows = new ArrayList<>();
		List<ResultRow> appResultRows = new ArrayList<>();
		List<ResultRow> moduleResultRows = new ArrayList<>();

		for (ResultRow resultRow : resultRows) {
			Object object = resultRow.getObject();

			if (object instanceof AppDisplay) {
				AppDisplay appDisplay = (AppDisplay)object;

				if (appDisplay.hasModuleGroups()) {
					appSuiteResultRows.add(resultRow);
				}
				else {
					appResultRows.add(resultRow);
				}
			}
			else if (object instanceof ModuleGroupDisplay) {
				appResultRows.add(resultRow);
			}
			else {
				moduleResultRows.add(resultRow);
			}
		}

		if (!appSuiteResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry("app-suites", appSuiteResultRows));
		}

		if (!appResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry("apps", appResultRows));
		}

		if (!moduleResultRows.isEmpty()) {
			resultRowSplitterEntries.add(
				new ResultRowSplitterEntry("modules", moduleResultRows));
		}

		return resultRowSplitterEntries;
	}

}