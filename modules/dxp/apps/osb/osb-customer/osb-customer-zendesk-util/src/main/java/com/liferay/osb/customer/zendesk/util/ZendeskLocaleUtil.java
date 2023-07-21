/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.util;

import com.liferay.osb.customer.zendesk.constants.ZendeskLocales;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ZendeskLocaleUtil.class)
public class ZendeskLocaleUtil {

	public String convertToZendeskLocale(Locale locale) {
		if (locale.equals(LocaleUtil.US)) {
			return ZendeskLocales.US;
		}
		else if (locale.equals(LocaleUtil.SPAIN)) {
			return ZendeskLocales.SPAIN;
		}
		else if (locale.equals(LocaleUtil.JAPAN)) {
			return ZendeskLocales.JAPAN;
		}
		else if (locale.equals(LocaleUtil.BRAZIL)) {
			return ZendeskLocales.PORTUGAL;
		}
		else if (locale.equals(LocaleUtil.CHINA)) {
			return ZendeskLocales.CHINA;
		}

		return StringPool.BLANK;
	}

	public String convertToZendeskLocale(String languageId) {
		if (languageId.equals("en_US")) {
			return ZendeskLocales.US;
		}
		else if (languageId.equals("es_ES")) {
			return ZendeskLocales.SPAIN;
		}
		else if (languageId.equals("ja_JP")) {
			return ZendeskLocales.JAPAN;
		}
		else if (languageId.equals("pt_BR")) {
			return ZendeskLocales.PORTUGAL;
		}
		else if (languageId.equals("zh_CN")) {
			return ZendeskLocales.CHINA;
		}

		return StringPool.BLANK;
	}

}