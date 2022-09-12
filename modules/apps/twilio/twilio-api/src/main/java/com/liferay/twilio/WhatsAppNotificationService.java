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

package com.liferay.twilio;

import com.liferay.portal.kernel.model.Phone;
import com.liferay.twilio.model.Notification;

/**
 * @author Peter Richards
 */
public interface WhatsAppNotificationService {

	void init();

	void init(String accountSid, String authToken);

	Notification sendNotification(Notification notification)
		throws TwilioException;

	Notification sendNotification(Phone sender, Phone recipient, String message)
		throws TwilioException;

	Notification sendNotification(
			Phone sender, Phone recipient, String message,
			String defaultCountryCode)
		throws TwilioException;

	Notification sendNotification(
			String senderNumber, String recipientNumber, String message)
		throws TwilioException;

	Notification sendNotification(
			String senderNumber, String recipientNumber, String message,
			String defaultCountryCode)
		throws TwilioException;

}