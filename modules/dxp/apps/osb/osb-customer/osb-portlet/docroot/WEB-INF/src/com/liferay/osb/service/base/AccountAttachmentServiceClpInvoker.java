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

import com.liferay.osb.service.AccountAttachmentServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountAttachmentServiceClpInvoker {
	public AccountAttachmentServiceClpInvoker() {
		_methodName364 = "getOSGiServiceIdentifier";

		_methodParameterTypes364 = new String[] {  };

		_methodName369 = "addAccountAttachments";

		_methodParameterTypes369 = new String[] {
				"long", "long", "java.util.List", "java.util.List"
			};

		_methodName370 = "deleteAccountAttachment";

		_methodParameterTypes370 = new String[] { "long" };

		_methodName371 = "getAccountAttachment";

		_methodParameterTypes371 = new String[] { "long" };

		_methodName372 = "getAccountAttachments";

		_methodParameterTypes372 = new String[] { "long", "long", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName364.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes364, parameterTypes)) {
			return AccountAttachmentServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName369.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes369, parameterTypes)) {
			return AccountAttachmentServiceUtil.addAccountAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[2],
				(java.util.List<java.lang.Integer>)arguments[3]);
		}

		if (_methodName370.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes370, parameterTypes)) {
			return AccountAttachmentServiceUtil.deleteAccountAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName371.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes371, parameterTypes)) {
			return AccountAttachmentServiceUtil.getAccountAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			return AccountAttachmentServiceUtil.getAccountAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
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