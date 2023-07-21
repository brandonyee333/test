/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.internal.binding;

import com.liferay.saml.opensaml.integration.internal.transport.HttpClientOutTransport;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;

import org.opensaml.saml2.binding.encoding.HTTPSOAP11Encoder;
import org.opensaml.ws.message.MessageContext;
import org.opensaml.ws.message.encoder.MessageEncodingException;
import org.opensaml.ws.transport.OutTransport;

/**
 * @author Mika Koivisto
 */
public class HttpSoap11Encoder extends HTTPSOAP11Encoder {

	public HttpSoap11Encoder() {
		this(HttpClients.createDefault());
	}

	public HttpSoap11Encoder(HttpClient httpClient) {
		_httpClient = httpClient;
	}

	@Override
	public void encode(MessageContext messageContext)
		throws MessageEncodingException {

		super.encode(messageContext);

		OutTransport outTransport =
			messageContext.getOutboundMessageTransport();

		if (outTransport instanceof HttpClientOutTransport) {
			HttpClientOutTransport httpClientTransport =
				(HttpClientOutTransport)outTransport;

			HttpPost httpPost = httpClientTransport.getHttpPost();

			try {
				_httpClient.execute(httpPost);
			}
			catch (Exception e) {
				throw new MessageEncodingException(e);
			}
		}
	}

	private final HttpClient _httpClient;

}