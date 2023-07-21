/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.internal.binding;

import com.liferay.saml.opensaml.integration.SamlBinding;

import org.opensaml.ws.message.decoder.MessageDecoder;
import org.opensaml.ws.message.encoder.MessageEncoder;

/**
 * @author Mika Koivisto
 */
public abstract class BaseSamlBinding implements SamlBinding {

	public BaseSamlBinding(
		MessageDecoder messageDecoder, MessageEncoder messageEncoder) {

		_messageDecoder = messageDecoder;
		_messageEncoder = messageEncoder;
	}

	@Override
	public MessageDecoder getMessageDecoder() {
		return _messageDecoder;
	}

	@Override
	public MessageEncoder getMessageEncoder() {
		return _messageEncoder;
	}

	private final MessageDecoder _messageDecoder;
	private final MessageEncoder _messageEncoder;

}