/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.internal.binding;

import org.apache.http.client.HttpClient;

import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.decoding.HTTPSOAP11Decoder;
import org.opensaml.xml.parse.ParserPool;

/**
 * @author Mika Koivisto
 */
public class HttpSoap11Binding extends BaseSamlBinding {

	public HttpSoap11Binding(ParserPool parserPool, HttpClient httpClient) {
		super(
			new HTTPSOAP11Decoder(parserPool),
			new HttpSoap11Encoder(httpClient));
	}

	@Override
	public String getCommunicationProfileId() {
		return SAMLConstants.SAML2_SOAP11_BINDING_URI;
	}

}