/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.sync.model.impl;

import com.liferay.osb.customer.zendesk.constants.ZendeskLocales;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Amos Fong
 */
public class ZendeskArticleImpl extends ZendeskArticleBaseImpl {

	public ZendeskArticleImpl() {
	}

	public String getRemoteHtmlURL(String zendeskLocale) {
		String remoteHtmlUrl = getRemoteHtmlURL();

		if (zendeskLocale.equals(ZendeskLocales.US)) {
			return remoteHtmlUrl;
		}

		return StringUtil.replace(
			remoteHtmlUrl,
			StringPool.SLASH + ZendeskLocales.US + StringPool.SLASH,
			StringPool.SLASH + zendeskLocale + StringPool.SLASH);
	}

}