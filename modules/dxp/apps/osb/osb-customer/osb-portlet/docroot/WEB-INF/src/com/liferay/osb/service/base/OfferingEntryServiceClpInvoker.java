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

import com.liferay.osb.service.OfferingEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class OfferingEntryServiceClpInvoker {
	public OfferingEntryServiceClpInvoker() {
		_methodName672 = "getBeanIdentifier";

		_methodParameterTypes672 = new String[] {  };

		_methodName673 = "setBeanIdentifier";

		_methodParameterTypes673 = new String[] { "java.lang.String" };

		_methodName678 = "getAccountEntryOfferingEntries";

		_methodParameterTypes678 = new String[] { "long" };

		_methodName679 = "getOrderEntryOfferingEntries";

		_methodParameterTypes679 = new String[] { "long" };

		_methodName680 = "updateOfferingEntry";

		_methodParameterTypes680 = new String[] {
				"long", "long", "long", "long", "long", "java.lang.String",
				"int", "int", "boolean", "long", "long", "long", "boolean",
				"long", "int", "int"
			};

		_methodName681 = "updateStatus";

		_methodParameterTypes681 = new String[] { "long", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName672.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes672, parameterTypes)) {
			return OfferingEntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName673.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes673, parameterTypes)) {
			OfferingEntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName678.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes678, parameterTypes)) {
			return OfferingEntryServiceUtil.getAccountEntryOfferingEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName679.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes679, parameterTypes)) {
			return OfferingEntryServiceUtil.getOrderEntryOfferingEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName680.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes680, parameterTypes)) {
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

		if (_methodName681.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes681, parameterTypes)) {
			return OfferingEntryServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName672;
	private String[] _methodParameterTypes672;
	private String _methodName673;
	private String[] _methodParameterTypes673;
	private String _methodName678;
	private String[] _methodParameterTypes678;
	private String _methodName679;
	private String[] _methodParameterTypes679;
	private String _methodName680;
	private String[] _methodParameterTypes680;
	private String _methodName681;
	private String[] _methodParameterTypes681;
}