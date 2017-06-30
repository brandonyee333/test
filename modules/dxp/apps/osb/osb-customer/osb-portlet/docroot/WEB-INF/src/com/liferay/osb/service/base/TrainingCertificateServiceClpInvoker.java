/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service.base;

import com.liferay.osb.service.TrainingCertificateServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingCertificateServiceClpInvoker {
	public TrainingCertificateServiceClpInvoker() {
		_methodName668 = "getBeanIdentifier";

		_methodParameterTypes668 = new String[] {  };

		_methodName669 = "setBeanIdentifier";

		_methodParameterTypes669 = new String[] { "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName668.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes668, parameterTypes)) {
			return TrainingCertificateServiceUtil.getBeanIdentifier();
		}

		if (_methodName669.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes669, parameterTypes)) {
			TrainingCertificateServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName668;
	private String[] _methodParameterTypes668;
	private String _methodName669;
	private String[] _methodParameterTypes669;
}