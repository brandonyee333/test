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
 * @author Carlos Sierra Andrés
 */
public class SAMLContextImpl
	<InboundMessageType extends SAMLObject,
	 OutboundMessageType extends SAMLObject, R extends Resolver>
		implements Resolver.SAMLContext<R> {

	public SAMLContextImpl(
		SAMLMessageContext<InboundMessageType, OutboundMessageType, NameID>
			samlMessageContext) {

		_samlMessageContext = samlMessageContext;
	}

	public <T> T resolve(Resolver.SAMLCommand<T, ? super R> samlCommand) {
		Function
			<SAMLMessageContext
				<InboundMessageType, OutboundMessageType, NameID>,
			 T> function =
				((SAMLCommandImpl
					<InboundMessageType, OutboundMessageType, T, R>)
						samlCommand).getSamlMessageContextFunction();

		return function.apply(_samlMessageContext);
	}

	private final SAMLMessageContext
		<InboundMessageType, OutboundMessageType, NameID> _samlMessageContext;

}