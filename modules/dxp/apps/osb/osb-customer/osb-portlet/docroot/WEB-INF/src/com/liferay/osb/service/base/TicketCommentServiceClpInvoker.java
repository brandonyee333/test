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

import com.liferay.osb.service.TicketCommentServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketCommentServiceClpInvoker {
	public TicketCommentServiceClpInvoker() {
		_methodName372 = "getOSGiServiceIdentifier";

		_methodParameterTypes372 = new String[] {  };

		_methodName377 = "addTicketComment";

		_methodParameterTypes377 = new String[] {
				"long", "long", "java.lang.String", "int", "int", "int", "long",
				"int[][]", "java.util.List", "java.util.List",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName378 = "addTicketComment";

		_methodParameterTypes378 = new String[] {
				"long", "long", "java.lang.String", "int", "int", "int", "long",
				"int[][]", "com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName379 = "deleteTicketComment";

		_methodParameterTypes379 = new String[] { "long" };

		_methodName380 = "updateTicketComment";

		_methodParameterTypes380 = new String[] {
				"long", "long", "long", "java.lang.String", "int", "int", "long",
				"int[][]", "java.util.List", "java.util.List"
			};

		_methodName381 = "updateTicketCommentType";

		_methodParameterTypes381 = new String[] { "long", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			return TicketCommentServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName377.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes377, parameterTypes)) {
			return TicketCommentServiceUtil.addTicketComment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Long)arguments[6]).longValue(), (int[])arguments[7],
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[8],
				(java.util.List<java.lang.Integer>)arguments[9],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[10]);
		}

		if (_methodName378.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes378, parameterTypes)) {
			return TicketCommentServiceUtil.addTicketComment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Long)arguments[6]).longValue(), (int[])arguments[7],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[8]);
		}

		if (_methodName379.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes379, parameterTypes)) {
			return TicketCommentServiceUtil.deleteTicketComment(((Long)arguments[0]).longValue());
		}

		if (_methodName380.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes380, parameterTypes)) {
			return TicketCommentServiceUtil.updateTicketComment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Long)arguments[6]).longValue(), (int[])arguments[7],
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[8],
				(java.util.List<java.lang.Integer>)arguments[9]);
		}

		if (_methodName381.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes381, parameterTypes)) {
			return TicketCommentServiceUtil.updateTicketCommentType(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName372;
	private String[] _methodParameterTypes372;
	private String _methodName377;
	private String[] _methodParameterTypes377;
	private String _methodName378;
	private String[] _methodParameterTypes378;
	private String _methodName379;
	private String[] _methodParameterTypes379;
	private String _methodName380;
	private String[] _methodParameterTypes380;
	private String _methodName381;
	private String[] _methodParameterTypes381;
}