/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.zendesk.documentation.sync.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.constants.ZendeskLocales;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Amos Fong
 */
@ProviderType
public class ZendeskArticleImpl extends ZendeskArticleBaseImpl {

	public ZendeskArticleImpl() {
	}

	public String getRemoteHtmlURL(String locale) {
		String remoteHtmlUrl = getRemoteHtmlURL();

		if (locale.equals(ZendeskLocales.US)) {
			return remoteHtmlUrl;
		}

		return StringUtil.replace(
			remoteHtmlUrl,
			StringPool.SLASH + ZendeskLocales.US + StringPool.SLASH,
			StringPool.SLASH + locale + StringPool.SLASH);
	}

	public String getRemoteTitle(String locale) {
		String remoteTitle = _remoteTitleMap.get(locale);

		if (Validator.isNull(remoteTitle)) {
			remoteTitle = _remoteTitleMap.get(ZendeskLocales.US);
		}

		return remoteTitle;
	}

	public void setRemoteTitle(String locale, String remoteTitle) {
		_remoteTitleMap.put(locale, remoteTitle);
	}

	private Map<String, String> _remoteTitleMap = new HashMap<>();

}