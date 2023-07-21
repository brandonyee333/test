/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.constants;

import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;

/**
 * @author Amos Fong
 */
public interface ZendeskLocales {

	public static final String CHINA = "zh-cn";

	public static final String JAPAN = "ja";

	public static final Locale[] LOCALES_ENABLED = {
		LocaleUtil.BRAZIL, LocaleUtil.CHINA, LocaleUtil.JAPAN, LocaleUtil.SPAIN,
		LocaleUtil.US
	};

	public static final String PORTUGAL = "pt";

	public static final String SPAIN = "es";

	public static final String US = "en-us";

	public static final String[] ZENDESK_LOCALES_ENABLED = {
		CHINA, JAPAN, PORTUGAL, SPAIN, US
	};

}