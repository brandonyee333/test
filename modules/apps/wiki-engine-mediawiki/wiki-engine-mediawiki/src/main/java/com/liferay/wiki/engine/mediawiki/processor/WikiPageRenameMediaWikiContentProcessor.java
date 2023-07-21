/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.mediawiki.processor;

import com.liferay.wiki.processor.BaseWikiPageRenameContentProcessor;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Roberto Díaz
 * @author Daniel Sanz
 */
public class WikiPageRenameMediaWikiContentProcessor
	extends BaseWikiPageRenameContentProcessor {

	@Activate
	@Modified
	public void activate() {
		regexps.put("\\[\\[Image:@old_title@/", "[[Image:@new_title@/");
	}

}