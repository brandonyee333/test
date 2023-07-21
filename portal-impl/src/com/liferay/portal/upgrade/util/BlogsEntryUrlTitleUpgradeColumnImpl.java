/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.util;

import com.liferay.portal.kernel.upgrade.util.BaseUpgradeColumnImpl;
import com.liferay.portal.kernel.upgrade.util.UpgradeColumn;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.blogs.util.BlogsUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class BlogsEntryUrlTitleUpgradeColumnImpl extends BaseUpgradeColumnImpl {

	public BlogsEntryUrlTitleUpgradeColumnImpl(
		UpgradeColumn entryIdColumn, UpgradeColumn titleColumn) {

		super("urlTitle");

		_entryIdColumn = entryIdColumn;
		_titleColumn = titleColumn;

		_urlTitles = new HashSet<>();
	}

	@Override
	public Object getNewValue(Object oldValue) throws Exception {
		//String oldUrlTitle = (String)oldValue;
		String oldUrlTitle = StringPool.BLANK;

		String newUrlTitle = oldUrlTitle;

		if (Validator.isNull(oldUrlTitle)) {
			long entryId = ((Long)_entryIdColumn.getOldValue()).longValue();

			String title = (String)_titleColumn.getOldValue();

			newUrlTitle = getUrlTitle(entryId, title);

			_urlTitles.add(newUrlTitle);
		}

		return newUrlTitle;
	}

	protected String getUrlTitle(long entryId, String title) {
		String urlTitle = BlogsUtil.getUrlTitle(entryId, title);

		String newUrlTitle = urlTitle;

		for (int i = 1;; i++) {
			if (!_urlTitles.contains(newUrlTitle)) {
				break;
			}

			newUrlTitle = urlTitle + "_" + i;
		}

		return newUrlTitle;
	}

	private final UpgradeColumn _entryIdColumn;
	private final UpgradeColumn _titleColumn;
	private final Set<String> _urlTitles;

}