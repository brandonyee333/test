/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging.internal.security;

import com.liferay.lcs.messaging.security.DigitalSignature;
import com.liferay.lcs.messaging.security.DigitalSignatureFactory;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(service = DigitalSignatureFactory.class)
public class DigitalSignatureFactoryImpl implements DigitalSignatureFactory {

	@Override
	public DigitalSignature getInstance(Map<String, Object> properties) {
		DigitalSignatureImpl digitalSignatureImpl = new DigitalSignatureImpl();

		digitalSignatureImpl.activate(properties);

		return digitalSignatureImpl;
	}

}