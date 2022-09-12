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

package com.liferay.twilio.service;

import com.liferay.portal.kernel.model.Phone;
import com.liferay.twilio.api.TwilioException;
import com.liferay.twilio.api.WhatsAppNotificationService;
import com.liferay.twilio.configuration.WhatsAppNotificationServiceConfigurationWrapper;
import com.liferay.twilio.constants.TwilioWhatsAppConstants;
import com.liferay.twilio.model.Notification;
import com.liferay.twilio.model.NotificationCreator;
import com.liferay.twilio.util.StringUtil;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.concurrent.Semaphore;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Peter Richards
 */
@Component(immediate = true, service = WhatsAppNotificationService.class)
public class WhatsAppNotificationServiceImpl
	implements WhatsAppNotificationService {

	@Override
	public void init() {
		init(
			_configurationWrapper.getAccountSid(),
			_configurationWrapper.getAuthToken());
	}

	@Override
	public void init(String accountSid, String authToken) {
		try {
			mutex.acquire();

			if (StringUtil.isBlank(accountSid)) {
				throw new IllegalArgumentException(
					"The accountSid must have a value");
			}

			if (StringUtil.isBlank(authToken)) {
				throw new IllegalArgumentException(
					"The authToken must have a value");
			}

			Twilio.init(accountSid, authToken);
			_initialised = true;
		}
		catch (InterruptedException ie) {
			throw new TwilioException(
				"Interrupted while initialising Twilio", ie);
		}
		finally {
			mutex.release();
		}
	}

	@Override
	public Notification sendNotification(Notification notification)
		throws TwilioException {

		try {
			mutex.acquire();

			if (!_initialised) {
				throw new IllegalStateException(
					"The init method must be before a notification can be sent");
			}
		}
		catch (InterruptedException ie) {
			throw new TwilioException("Interrupted while sending message", ie);
		}
		finally {
			mutex.release();
		}

		PhoneNumber sender = buildPhoneNumber(notification.getSender());
		PhoneNumber recipient = buildPhoneNumber(notification.getRecipient());

		Message message = Message.creator(
			recipient, sender, notification.getMessage()
		).create();

		return updateNotification(notification, message);
	}

	@Override
	public Notification sendNotification(
			Phone sender, Phone recipient, String message)
		throws TwilioException {

		return sendNotification(
			sender, recipient, message,
			_configurationWrapper.getDefaultCountryCode());
	}

	@Override
	public Notification sendNotification(
			Phone sender, Phone recipient, String message,
			String defaultCountryCode)
		throws TwilioException {

		NotificationCreator creator = new NotificationCreator(
			defaultCountryCode);

		creator.withSender(
			sender
		).withRecipient(
			recipient
		).withMessage(
			message
		);

		return sendNotification(creator.create());
	}

	@Override
	public Notification sendNotification(
			String senderNumber, String recipientNumber, String message)
		throws TwilioException {

		return sendNotification(
			senderNumber, recipientNumber, message,
			_configurationWrapper.getDefaultCountryCode());
	}

	@Override
	public Notification sendNotification(
			String senderNumber, String recipientNumber, String message,
			String defaultCountryCode)
		throws TwilioException {

		NotificationCreator creator = new NotificationCreator(
			defaultCountryCode);

		creator.withSender(
			senderNumber
		).withRecipient(
			recipientNumber
		).withMessage(
			message
		);

		return sendNotification(creator.create());
	}

	private PhoneNumber buildPhoneNumber(String number) {
		return new PhoneNumber(
			String.format(
				"%s%s", TwilioWhatsAppConstants.WHATSAPP_NUMBER_PREFIX,
				number));
	}

	private Notification updateNotification(
		Notification notification, Message message) {

		NotificationCreator creator = new NotificationCreator();

		Notification updatedNotification = creator.withRecipient(
			notification.getRecipient()
		).withSender(
			notification.getSender()
		).withMessage(
			notification.getMessage()
		).create();

		updatedNotification.setSid(message.getSid());
		updatedNotification.setStatus(
			message.getStatus(
			).toString());

		updatedNotification.setErrorCode(message.getErrorCode());
		updatedNotification.setErrorMessage(message.getErrorMessage());

		updatedNotification.setDateCreated(message.getDateCreated());
		updatedNotification.setDateSent(message.getDateSent());

		return updatedNotification;
	}

	private static final Semaphore mutex = new Semaphore(1);

	@Reference
	private WhatsAppNotificationServiceConfigurationWrapper
		_configurationWrapper;

	private boolean _initialised;

}