/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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