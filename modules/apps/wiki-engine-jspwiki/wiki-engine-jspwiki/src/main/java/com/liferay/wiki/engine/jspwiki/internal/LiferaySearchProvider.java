/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.jspwiki.internal;

import com.ecyrd.jspwiki.WikiEngine;
import com.ecyrd.jspwiki.WikiPage;
import com.ecyrd.jspwiki.search.SearchProvider;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

/**
 * @author Jorge Ferrer
 */
public class LiferaySearchProvider implements SearchProvider {

	@Override
	public Collection<WikiPage> findPages(String query) {
		return Collections.emptyList();
	}

	@Override
	public String getProviderInfo() {
		return LiferaySearchProvider.class.getName();
	}

	@Override
	public void initialize(WikiEngine engine, Properties props) {
	}

	@Override
	public void pageRemoved(WikiPage page) {
	}

	@Override
	public void reindexPage(WikiPage page) {
	}

}