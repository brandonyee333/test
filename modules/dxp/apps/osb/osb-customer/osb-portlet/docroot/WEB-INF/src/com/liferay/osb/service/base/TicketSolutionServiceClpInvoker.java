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

import com.liferay.osb.service.TicketSolutionServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketSolutionServiceClpInvoker {
	public TicketSolutionServiceClpInvoker() {
		_methodName372 = "getOSGiServiceIdentifier";

		_methodParameterTypes372 = new String[] {  };

		_methodName377 = "addTicketSolution";

		_methodParameterTypes377 = new String[] {
				"long", "long", "java.lang.String", "boolean", "int",
				"java.lang.String", "int", "boolean", "boolean", "boolean",
				"boolean", "int", "int", "java.lang.String", "java.util.List",
				"java.util.List", "java.util.List"
			};

		_methodName378 = "updateTicketSolution";

		_methodParameterTypes378 = new String[] {
				"long", "long", "int", "long", "java.lang.String", "int"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			return TicketSolutionServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName377.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes377, parameterTypes)) {
			return TicketSolutionServiceUtil.addTicketSolution(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				(java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue(),
				((Boolean)arguments[7]).booleanValue(),
				((Boolean)arguments[8]).booleanValue(),
				((Boolean)arguments[9]).booleanValue(),
				((Boolean)arguments[10]).booleanValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				(java.lang.String)arguments[13],
				(java.util.List<java.lang.String>)arguments[14],
				(java.util.List<java.lang.Integer>)arguments[15],
				(java.util.List<com.liferay.osb.model.TicketAttachment>)arguments[16]);
		}

		if (_methodName378.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes378, parameterTypes)) {
			return TicketSolutionServiceUtil.updateTicketSolution(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName372;
	private String[] _methodParameterTypes372;
	private String _methodName377;
	private String[] _methodParameterTypes377;
	private String _methodName378;
	private String[] _methodParameterTypes378;
}