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
		_methodName364 = "getOSGiServiceIdentifier";

		_methodParameterTypes364 = new String[] {  };

		_methodName369 = "addTicketComment";

		_methodParameterTypes369 = new String[] {
				"long", "long", "java.lang.String", "int", "int", "int",
				"int[][]", "java.util.List", "java.util.List",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName370 = "addTicketComment";

		_methodParameterTypes370 = new String[] {
				"long", "long", "java.lang.String", "int", "int", "int",
				"int[][]", "com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName371 = "deleteTicketComment";

		_methodParameterTypes371 = new String[] { "long" };

		_methodName372 = "updateTicketComment";

		_methodParameterTypes372 = new String[] {
				"long", "long", "long", "java.lang.String", "int", "int",
				"int[][]", "java.util.List", "java.util.List"
			};

		_methodName373 = "updateTicketCommentType";

		_methodParameterTypes373 = new String[] { "long", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName364.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes364, parameterTypes)) {
			return TicketCommentServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName369.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes369, parameterTypes)) {
			return TicketCommentServiceUtil.addTicketComment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(), (int[])arguments[6],
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[7],
				(java.util.List<java.lang.Integer>)arguments[8],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[9]);
		}

		if (_methodName370.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes370, parameterTypes)) {
			return TicketCommentServiceUtil.addTicketComment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(), (int[])arguments[6],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[7]);
		}

		if (_methodName371.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes371, parameterTypes)) {
			return TicketCommentServiceUtil.deleteTicketComment(((Long)arguments[0]).longValue());
		}

		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			return TicketCommentServiceUtil.updateTicketComment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(), (int[])arguments[6],
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[7],
				(java.util.List<java.lang.Integer>)arguments[8]);
		}

		if (_methodName373.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes373, parameterTypes)) {
			return TicketCommentServiceUtil.updateTicketCommentType(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
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
	private String _methodName373;
	private String[] _methodParameterTypes373;
}