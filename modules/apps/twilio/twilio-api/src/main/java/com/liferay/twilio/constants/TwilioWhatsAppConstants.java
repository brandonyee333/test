/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.twilio.constants;

import java.util.regex.Pattern;

/**
 * @author Peter Richards
 */
public class TwilioWhatsAppConstants {

	public static final Pattern PHONE_NUMBER_REGEX_PATTERN = Pattern.compile(
		TwilioWhatsAppConstants.PHONE_NUMBER_REGEX_STRING);

	public static final String PHONE_NUMBER_REGEX_STRING = "^([+\\d].*)?\\d$";

	public static final String WHATSAPP_NUMBER_PREFIX = "whatsapp:";

	public static final String ZERO = "0";

}