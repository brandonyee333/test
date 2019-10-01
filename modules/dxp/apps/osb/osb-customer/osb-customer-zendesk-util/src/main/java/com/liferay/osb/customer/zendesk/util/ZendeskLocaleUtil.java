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
		else {
			return StringPool.BLANK;
		}
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
		else {
			return StringPool.BLANK;
		}
	}

}