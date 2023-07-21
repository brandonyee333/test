/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.marketplace.app.manager.web.internal.util.comparator;

import com.liferay.marketplace.app.manager.web.internal.util.ModuleGroupDisplay;

import java.util.Comparator;

/**
 * @author Ryan Park
 */
public class ModuleGroupDisplayComparator
	implements Comparator<ModuleGroupDisplay> {

	public ModuleGroupDisplayComparator(String orderByType) {
		if (!orderByType.equals("asc")) {
			_ascending = false;
		}
		else {
			_ascending = true;
		}
	}

	@Override
	public int compare(
		ModuleGroupDisplay moduleGroupDisplay1,
		ModuleGroupDisplay moduleGroupDisplay2) {

		int value = moduleGroupDisplay1.compareTo(moduleGroupDisplay2);

		if (_ascending) {
			return value;
		}

		return -value;
	}

	private final boolean _ascending;

}