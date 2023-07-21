/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;

import java.util.Map;

/**
 * @author Ambrín Chaudhary
 */
public class AddMenuItem extends MenuItem {

	public AddMenuItem(
		Map<String, Object> anchorData, String id, String label, String url) {

		_anchorData = anchorData;
		_id = id;
		_url = url;

		setLabel(label);
	}

	public AddMenuItem(String label, String url) {
		_url = url;

		_id = StringPool.BLANK;

		_anchorData = null;

		setLabel(label);
	}

	public AddMenuItem(String id, String label, String url) {
		_id = id;
		_url = url;

		_anchorData = null;

		setLabel(label);
	}

	public Map<String, Object> getAnchorData() {
		return _anchorData;
	}

	public String getId() {
		return _id;
	}

	public String getUrl() {
		return _url;
	}

	private final Map<String, Object> _anchorData;
	private final String _id;
	private final String _url;

}