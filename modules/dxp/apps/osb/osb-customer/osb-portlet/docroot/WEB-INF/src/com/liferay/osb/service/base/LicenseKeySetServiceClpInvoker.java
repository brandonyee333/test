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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.LicenseKeySetServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class LicenseKeySetServiceClpInvoker {
	public LicenseKeySetServiceClpInvoker() {
		_methodName56 = "getOSGiServiceIdentifier";

		_methodParameterTypes56 = new String[] {  };

		_methodName61 = "addLicenseKeySet";

		_methodParameterTypes61 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName62 = "deleteLicenseKeySet";

		_methodParameterTypes62 = new String[] { "long" };

		_methodName63 = "getLicenseKeySet";

		_methodParameterTypes63 = new String[] { "long" };

		_methodName64 = "updateLicenseKeySet";

		_methodParameterTypes64 = new String[] { "long", "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return LicenseKeySetServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return LicenseKeySetServiceUtil.addLicenseKeySet(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return LicenseKeySetServiceUtil.deleteLicenseKeySet(((Long)arguments[0]).longValue());
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return LicenseKeySetServiceUtil.getLicenseKeySet(((Long)arguments[0]).longValue());
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return LicenseKeySetServiceUtil.updateLicenseKeySet(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName64;
	private String[] _methodParameterTypes64;
}