/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.internal.resolver;

import com.liferay.saml.opensaml.integration.resolver.Resolver;

import java.util.function.Function;

import org.opensaml.common.SAMLObject;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.core.NameID;

/**
 * @author Tomas Polesovsky
 */
public class SAMLCommandImpl
	<InboundMessageType extends SAMLObject,
	 OutboundMessageType extends SAMLObject, T, R extends Resolver>
		implements Resolver.SAMLCommand<T, R> {

	public SAMLCommandImpl(
		Function
			<SAMLMessageContext
				<InboundMessageType, OutboundMessageType, NameID>,
			 T> samlMessageContextFunction) {

		_samlMessageContextFunction = samlMessageContextFunction;
	}

	protected Function
		<SAMLMessageContext<InboundMessageType, OutboundMessageType, NameID>, T>
			getSamlMessageContextFunction() {

		return _samlMessageContextFunction;
	}

	private final Function
		<SAMLMessageContext<InboundMessageType, OutboundMessageType, NameID>, T>
			_samlMessageContextFunction;

}