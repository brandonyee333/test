/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.internal.resolver;

import com.liferay.saml.opensaml.integration.resolver.UserResolver;

import java.util.function.Function;

import org.opensaml.common.SAMLObject;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.core.NameID;
import org.opensaml.saml2.core.Response;

/**
 * @author Tomas Polesovsky
 */
public class UserResolverSAMLCommand<T, R extends UserResolver>
	extends SAMLCommandImpl<Response, SAMLObject, T, R> {

	public UserResolverSAMLCommand(
		Function<SAMLMessageContext<Response, SAMLObject, NameID>, T>
			samlMessageContextFunction) {

		super(samlMessageContextFunction);
	}

}