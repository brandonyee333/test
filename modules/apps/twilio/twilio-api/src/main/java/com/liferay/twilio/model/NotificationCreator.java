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

package com.liferay.twilio.model;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.twilio.constants.TwilioWhatsAppConstants;
import com.liferay.twilio.util.StringUtil;

/**
 * @author Peter Richards
 */
public class NotificationCreator {

	public NotificationCreator() {
		_defaultCountryCode = null;
	}

	public NotificationCreator(String defaultCountryCode) {
		this._defaultCountryCode = normaliseCountryCode(defaultCountryCode);
	}

	public Notification create() {
		return new Notification(_sender, _recipient, _message);
	}

	public NotificationCreator withDefaultCountryCode(
		String defaultCountryCode) {

		this._defaultCountryCode = normaliseCountryCode(defaultCountryCode);

		return this;
	}

	public NotificationCreator withMessage(String message) {
		this._message = message;

		return this;
	}

	public NotificationCreator withRecipient(Phone phoneNumber) {
		_recipient = normalisePhoneNumber("recipient", phoneNumber.getNumber());

		return this;
	}

	public NotificationCreator withRecipient(String phoneNumber) {
		_recipient = normalisePhoneNumber("recipient", phoneNumber);

		return this;
	}

	public NotificationCreator withSender(Phone phoneNumber) {
		_sender = normalisePhoneNumber("sender", phoneNumber.getNumber());

		return this;
	}

	public NotificationCreator withSender(String phoneNumber) {
		_sender = normalisePhoneNumber("sender", phoneNumber);

		return this;
	}

	private String normaliseCountryCode(String countryCode) {
		if (StringUtil.isBlank(countryCode)) {
			return null;
		}

		if (!TwilioWhatsAppConstants.PHONE_NUMBER_REGEX_PATTERN.matcher(
				countryCode
			).matches()) {

			throw new IllegalArgumentException(
				"The country code must be a valid number with or without a plus prefix");
		}

		if (countryCode.startsWith(StringPool.PLUS)) {
			return countryCode;
		}

		return StringPool.PLUS + countryCode;
	}

	private String normalisePhoneNumber(String type, String phoneNumber) {
		if (StringUtil.isBlank(phoneNumber)) {
			throw new IllegalArgumentException(
				String.format(
					"The %s's phone number must have a value. It can be a local or international format",
					type));
		}

		if (!TwilioWhatsAppConstants.PHONE_NUMBER_REGEX_PATTERN.matcher(
				phoneNumber
			).matches()) {

			throw new IllegalArgumentException(
				String.format(
					"The %s's phone number must be a valid a local or international format number",
					type));
		}

		if (phoneNumber.startsWith(TwilioWhatsAppConstants.ZERO) &&
			(_defaultCountryCode == null)) {

			throw new IllegalArgumentException(
				String.format(
					"The %s's phone number is in local format but the default country code has not been set",
					type));
		}

		String internationalNumber =
			phoneNumber.startsWith(TwilioWhatsAppConstants.ZERO) ?
				phoneNumber.replaceFirst(
					TwilioWhatsAppConstants.ZERO, _defaultCountryCode) :
						phoneNumber;

		if (internationalNumber.startsWith(StringPool.PLUS)) {
			return internationalNumber;
		}

		return StringPool.PLUS + internationalNumber;
	}

	private String _defaultCountryCode;
	private String _message;
	private String _recipient;
	private String _sender;

}