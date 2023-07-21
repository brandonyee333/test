/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.jspwiki.internal.plugin;

import com.ecyrd.jspwiki.WikiContext;
import com.ecyrd.jspwiki.parser.Heading;
import com.ecyrd.jspwiki.plugin.PluginException;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;

/**
 * <p>
 * This is a modification of JSPWiki's core TableOfContents plugin for use
 * within Liferay. This plugin modifies the original behavior by producing
 * ordered lists and making contents collapsable.
 * </p>
 *
 * @author Alexander Chow
 * @author Jorge Ferrer
 */
public class TableOfContents extends com.ecyrd.jspwiki.plugin.TableOfContents {

	@Override
	@SuppressWarnings("rawtypes")
	public String execute(WikiContext context, Map params)
		throws PluginException {

		if (!params.containsKey(PARAM_NUMBERED)) {
			params.put(PARAM_NUMBERED, Boolean.TRUE.toString());
		}

		String result = super.execute(context, params);

		if (_count < 2) {
			return StringPool.BLANK;
		}

		int x = result.indexOf("<div class=\"collapsebox\">\n");

		x = result.indexOf("</h4>", x);

		int y = x + "</h4>".length();

		if ((x != -1) && (y != -1)) {
			StringBundler sb = new StringBundler(7);

			sb.append(result.substring(0, x));
			sb.append(StringPool.NBSP);
			sb.append("<a class=\"toc-trigger\" href=\"javascript:void(0);\">");
			sb.append("[-]</a></h4>");
			sb.append("<div class=\"toc-index\">\n");
			sb.append(result.substring(y));
			sb.append("</div>\n");

			result = sb.toString();
		}

		return result;
	}

	@Override
	public void headingAdded(WikiContext context, Heading heading) {
		super.headingAdded(context, heading);

		_count++;
	}

	private int _count;

}