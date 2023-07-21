/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.jspwiki.internal;

import com.ecyrd.jspwiki.WikiEngine;
import com.ecyrd.jspwiki.WikiException;
import com.ecyrd.jspwiki.WikiPage;

import java.util.Collection;
import java.util.Properties;

import javax.servlet.ServletContext;

/**
 * @author Jorge Ferrer
 */
public class LiferayJSPWikiEngine extends WikiEngine {

	public LiferayJSPWikiEngine(Properties properties) throws WikiException {
		super(properties);
	}

	public LiferayJSPWikiEngine(
			ServletContext context, String appId, Properties props)
		throws WikiException {

		super(context, appId, props);
	}

	@Override
	public Collection<String> scanWikiLinks(WikiPage page, String pageData) {
		return super.scanWikiLinks(page, pageData);
	}

}