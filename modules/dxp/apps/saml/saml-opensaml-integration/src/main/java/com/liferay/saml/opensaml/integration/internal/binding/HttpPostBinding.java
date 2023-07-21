/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.internal.binding;

import org.apache.velocity.app.VelocityEngine;

import org.opensaml.common.xml.SAMLConstants;
import org.opensaml.saml2.binding.decoding.HTTPPostDecoder;
import org.opensaml.saml2.binding.encoding.HTTPPostEncoder;
import org.opensaml.xml.parse.ParserPool;

/**
 * @author Mika Koivisto
 */
public class HttpPostBinding extends BaseSamlBinding {

	public HttpPostBinding(
		ParserPool parserPool, VelocityEngine velocityEngine) {

		super(
			new HTTPPostDecoder(parserPool),
			new HTTPPostEncoder(
				velocityEngine, "/templates/saml2-post-binding.vm"));
	}

	@Override
	public String getCommunicationProfileId() {
		return SAMLConstants.SAML2_POST_BINDING_URI;
	}

}