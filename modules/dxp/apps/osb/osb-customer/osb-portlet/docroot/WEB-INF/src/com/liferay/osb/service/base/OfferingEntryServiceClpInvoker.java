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

import com.liferay.osb.service.OfferingEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class OfferingEntryServiceClpInvoker {
	public OfferingEntryServiceClpInvoker() {
		_methodName210 = "getOSGiServiceIdentifier";

		_methodParameterTypes210 = new String[] {  };

		_methodName215 = "getAccountEntryOfferingEntries";

		_methodParameterTypes215 = new String[] { "long" };

		_methodName216 = "getOrderEntryOfferingEntries";

		_methodParameterTypes216 = new String[] { "long" };

		_methodName217 = "updateOfferingEntry";

		_methodParameterTypes217 = new String[] {
				"long", "long", "long", "long", "long", "java.lang.String",
				"int", "int", "boolean", "long", "long", "long", "boolean",
				"long", "int", "int"
			};

		_methodName218 = "updateStatus";

		_methodParameterTypes218 = new String[] { "long", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName210.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes210, parameterTypes)) {
			return OfferingEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName215.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes215, parameterTypes)) {
			return OfferingEntryServiceUtil.getAccountEntryOfferingEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName216.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes216, parameterTypes)) {
			return OfferingEntryServiceUtil.getOrderEntryOfferingEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName217.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes217, parameterTypes)) {
			return OfferingEntryServiceUtil.updateOfferingEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Boolean)arguments[8]).booleanValue(),
				((Long)arguments[9]).longValue(),
				((Long)arguments[10]).longValue(),
				((Long)arguments[11]).longValue(),
				((Boolean)arguments[12]).booleanValue(),
				((Long)arguments[13]).longValue(),
				((Integer)arguments[14]).intValue(),
				((Integer)arguments[15]).intValue());
		}

		if (_methodName218.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes218, parameterTypes)) {
			return OfferingEntryServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName210;
	private String[] _methodParameterTypes210;
	private String _methodName215;
	private String[] _methodParameterTypes215;
	private String _methodName216;
	private String[] _methodParameterTypes216;
	private String _methodName217;
	private String[] _methodParameterTypes217;
	private String _methodName218;
	private String[] _methodParameterTypes218;
}