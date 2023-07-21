/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.facet.display.context;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;

import java.util.Locale;

/**
 * @author André de Oliveira
 */
public class ScopeSearchFacetTermDisplayContext {

	public ScopeSearchFacetTermDisplayContext(
		Group group, boolean selected, int count, boolean showCount,
		Locale locale) {

		_group = group;
		_selected = selected;
		_count = count;
		_showCount = showCount;
		_locale = locale;
	}

	public int getCount() {
		return _count;
	}

	public String getDescriptiveName() throws PortalException {
		return _group.getDescriptiveName(_locale);
	}

	public long getGroupId() {
		return _group.getGroupId();
	}

	public boolean isSelected() {
		return _selected;
	}

	public boolean isShowCount() {
		return _showCount;
	}

	private final int _count;
	private final Group _group;
	private final Locale _locale;
	private final boolean _selected;
	private final boolean _showCount;

}