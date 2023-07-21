/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.blogs.util;

import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.portal.kernel.search.HitsOpenSearchImpl;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Brian Wing Shun Chan
 */
@OSGiBeanProperties
public class BlogsOpenSearchImpl extends HitsOpenSearchImpl {

	public static final String TITLE = "Liferay Blogs Search: ";

	@Override
	public String getClassName() {
		return BlogsEntry.class.getName();
	}

	@Override
	public Indexer<BlogsEntry> getIndexer() {
		return IndexerRegistryUtil.getIndexer(BlogsEntry.class);
	}

	@Override
	public String getSearchPath() {
		return StringPool.BLANK;
	}

	@Override
	public String getTitle(String keywords) {
		return TITLE + keywords;
	}

}