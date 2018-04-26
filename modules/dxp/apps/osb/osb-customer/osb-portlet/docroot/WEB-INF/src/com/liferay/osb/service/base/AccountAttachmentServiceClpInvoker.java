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
		_methodName208 = "getOSGiServiceIdentifier";

		_methodParameterTypes208 = new String[] {  };

		_methodName213 = "addAccountAttachments";

		_methodParameterTypes213 = new String[] {
				"long", "long", "java.util.List", "java.util.List"
			};

		_methodName214 = "deleteAccountAttachment";

		_methodParameterTypes214 = new String[] { "long" };

		_methodName215 = "getAccountAttachment";

		_methodParameterTypes215 = new String[] { "long" };

		_methodName216 = "getAccountAttachments";

		_methodParameterTypes216 = new String[] { "long", "long", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName208.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes208, parameterTypes)) {
			return AccountAttachmentServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName213.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes213, parameterTypes)) {
			return AccountAttachmentServiceUtil.addAccountAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[2],
				(java.util.List<java.lang.Integer>)arguments[3]);
		}

		if (_methodName214.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes214, parameterTypes)) {
			return AccountAttachmentServiceUtil.deleteAccountAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName215.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes215, parameterTypes)) {
			return AccountAttachmentServiceUtil.getAccountAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName216.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes216, parameterTypes)) {
			return AccountAttachmentServiceUtil.getAccountAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName208;
	private String[] _methodParameterTypes208;
	private String _methodName213;
	private String[] _methodParameterTypes213;
	private String _methodName214;
	private String[] _methodParameterTypes214;
	private String _methodName215;
	private String[] _methodParameterTypes215;
	private String _methodName216;
	private String[] _methodParameterTypes216;
}