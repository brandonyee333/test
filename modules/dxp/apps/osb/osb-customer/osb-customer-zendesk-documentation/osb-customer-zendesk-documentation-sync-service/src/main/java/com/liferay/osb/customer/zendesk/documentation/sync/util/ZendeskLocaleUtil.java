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

package com.liferay.osb.customer.zendesk.documentation.sync.util;

import com.liferay.osb.customer.zendesk.constants.ZendeskLocales;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;

/**
 * @author Jenny Chen
 */
public class ZendeskLocaleUtil {

	public static Locale convertToLocale(String locale) {
		if (locale.equals(ZendeskLocales.CHINA)) {
			return LocaleUtil.CHINA;
		}
		else if (locale.equals(ZendeskLocales.JAPAN)) {
			return LocaleUtil.JAPAN;
		}
		else if (locale.equals(ZendeskLocales.PORTUGAL)) {
			return LocaleUtil.BRAZIL;
		}
		else if (locale.equals(ZendeskLocales.SPAIN)) {
			return LocaleUtil.SPAIN;
		}
		else {
			return LocaleUtil.US;
		}
	}

}