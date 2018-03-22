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
		_methodName364 = "getOSGiServiceIdentifier";

		_methodParameterTypes364 = new String[] {  };

		_methodName369 = "addLicenseKeySet";

		_methodParameterTypes369 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName370 = "deleteLicenseKeySet";

		_methodParameterTypes370 = new String[] { "long" };

		_methodName371 = "getLicenseKeySet";

		_methodParameterTypes371 = new String[] { "long" };

		_methodName372 = "updateLicenseKeySet";

		_methodParameterTypes372 = new String[] { "long", "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName364.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes364, parameterTypes)) {
			return LicenseKeySetServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName369.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes369, parameterTypes)) {
			return LicenseKeySetServiceUtil.addLicenseKeySet(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName370.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes370, parameterTypes)) {
			return LicenseKeySetServiceUtil.deleteLicenseKeySet(((Long)arguments[0]).longValue());
		}

		if (_methodName371.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes371, parameterTypes)) {
			return LicenseKeySetServiceUtil.getLicenseKeySet(((Long)arguments[0]).longValue());
		}

		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			return LicenseKeySetServiceUtil.updateLicenseKeySet(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName364;
	private String[] _methodParameterTypes364;
	private String _methodName369;
	private String[] _methodParameterTypes369;
	private String _methodName370;
	private String[] _methodParameterTypes370;
	private String _methodName371;
	private String[] _methodParameterTypes371;
	private String _methodName372;
	private String[] _methodParameterTypes372;
}