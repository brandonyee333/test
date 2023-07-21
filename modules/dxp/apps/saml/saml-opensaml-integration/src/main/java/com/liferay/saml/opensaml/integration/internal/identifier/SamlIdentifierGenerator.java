/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.saml.opensaml.integration.internal.identifier;

import com.liferay.portal.kernel.security.SecureRandom;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeFormatter;

import org.opensaml.common.IdentifierGenerator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Mika Koivisto
 */
@Component(immediate = true, service = IdentifierGenerator.class)
public class SamlIdentifierGenerator implements IdentifierGenerator {

	@Override
	public String generateIdentifier() {
		return generateIdentifier(16);
	}

	@Override
	public String generateIdentifier(int size) {
		byte[] bytes = new byte[size];

		_secureRandom.nextBytes(bytes);

		return StringPool.UNDERLINE.concat(UnicodeFormatter.bytesToHex(bytes));
	}

	private final SecureRandom _secureRandom = new SecureRandom();

}