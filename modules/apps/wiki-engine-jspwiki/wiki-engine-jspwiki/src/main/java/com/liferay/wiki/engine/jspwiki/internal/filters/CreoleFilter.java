/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.jspwiki.internal.filters;

import com.ecyrd.jspwiki.WikiContext;
import com.ecyrd.jspwiki.WikiEngine;
import com.ecyrd.jspwiki.filters.FilterException;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 * @author Samuel Liu
 */
public class CreoleFilter extends com.ecyrd.jspwiki.filters.CreoleFilter {

	@Override
	public void destroy(WikiEngine wikiEngine) {
		super.destroy(wikiEngine);
	}

	@Override
	public void initialize(WikiEngine wikiEngine, Properties properties) {
		if (_log.isDebugEnabled()) {
			_log.debug("Initializing");
		}
	}

	@Override
	public void postSave(WikiContext wikiContext, String content)
		throws FilterException {

		super.postSave(wikiContext, content);
	}

	@Override
	public String postTranslate(WikiContext wikiContext, String htmlContent)
		throws FilterException {

		return super.postTranslate(wikiContext, htmlContent);
	}

	@Override
	public String preSave(WikiContext wikiContext, String content)
		throws FilterException {

		return super.preSave(wikiContext, content);
	}

	@Override
	public String preTranslate(WikiContext wikiContext, String content)
		throws FilterException {

		return super.preTranslate(wikiContext, content);
	}

	private static final Log _log = LogFactoryUtil.getLog(CreoleFilter.class);

}