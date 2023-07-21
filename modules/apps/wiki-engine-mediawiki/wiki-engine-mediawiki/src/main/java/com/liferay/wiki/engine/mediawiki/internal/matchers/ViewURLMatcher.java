/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.engine.mediawiki.internal.matchers;

import javax.portlet.PortletURL;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class ViewURLMatcher extends PortletURLMatcher {

	public ViewURLMatcher(PortletURL portletURL) {
		super(portletURL);

		setRegex(_REGEX);
	}

	private static final String _REGEX =
		"<a href=\"[^\"]*?Special:Node[^\"]*?/([^\"/]+?/?)\"";

}