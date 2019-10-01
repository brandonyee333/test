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
		LocaleUtil.CHINA, LocaleUtil.JAPAN, LocaleUtil.PORTUGAL,
		LocaleUtil.SPAIN, LocaleUtil.US
	};

	public static final String PORTUGAL = "pt";

	public static final String SPAIN = "es";

	public static final String US = "en-us";

	public static final String[] ZENDESK_LOCALES_ENABLED = {
		CHINA, JAPAN, PORTUGAL, SPAIN, US
	};

}