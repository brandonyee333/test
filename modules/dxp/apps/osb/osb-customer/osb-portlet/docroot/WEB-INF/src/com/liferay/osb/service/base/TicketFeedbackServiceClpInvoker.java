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

import com.liferay.osb.service.TicketFeedbackServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketFeedbackServiceClpInvoker {
	public TicketFeedbackServiceClpInvoker() {
		_methodName372 = "getOSGiServiceIdentifier";

		_methodParameterTypes372 = new String[] {  };

		_methodName377 = "addTicketFeedback";

		_methodParameterTypes377 = new String[] { "long", "int", "int" };

		_methodName378 = "fetchFirstOpenTicketFeedback";

		_methodParameterTypes378 = new String[] { "long", "long", "int" };

		_methodName379 = "fetchFirstTicketFeedback";

		_methodParameterTypes379 = new String[] { "long", "int" };

		_methodName380 = "getTicketFeedback";

		_methodParameterTypes380 = new String[] { "long" };

		_methodName381 = "getTicketFeedbacks";

		_methodParameterTypes381 = new String[] { "long", "int" };

		_methodName382 = "search";

		_methodParameterTypes382 = new String[] {
				"java.lang.String", "int", "int", "int", "int", "int", "int",
				"int", "int", "int", "int", "int", "int", "java.lang.Integer",
				"java.lang.String", "java.lang.Integer", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.util.LinkedHashMap", "boolean",
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName383 = "search";

		_methodParameterTypes383 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName384 = "searchCount";

		_methodParameterTypes384 = new String[] {
				"java.lang.String", "int", "int", "int", "int", "int", "int",
				"int", "int", "int", "int", "int", "int", "java.lang.Integer",
				"java.lang.String", "java.lang.Integer", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.util.LinkedHashMap", "boolean"
			};

		_methodName385 = "searchCount";

		_methodParameterTypes385 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName386 = "updateTicketFeedback";

		_methodParameterTypes386 = new String[] {
				"long", "int", "int", "int", "int", "int", "int", "int", "int",
				"java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			return TicketFeedbackServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName377.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes377, parameterTypes)) {
			return TicketFeedbackServiceUtil.addTicketFeedback(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName378.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes378, parameterTypes)) {
			return TicketFeedbackServiceUtil.fetchFirstOpenTicketFeedback(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName379.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes379, parameterTypes)) {
			return TicketFeedbackServiceUtil.fetchFirstTicketFeedback(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName380.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes380, parameterTypes)) {
			return TicketFeedbackServiceUtil.getTicketFeedback(((Long)arguments[0]).longValue());
		}

		if (_methodName381.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes381, parameterTypes)) {
			return TicketFeedbackServiceUtil.getTicketFeedbacks(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName382.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes382, parameterTypes)) {
			return TicketFeedbackServiceUtil.search((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				(java.lang.Integer)arguments[13],
				(java.lang.String)arguments[14],
				(java.lang.Integer)arguments[15],
				(java.lang.Integer[])arguments[16],
				(java.lang.Integer[])arguments[17],
				(java.lang.Integer[])arguments[18],
				(java.lang.Integer[])arguments[19],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[20],
				((Boolean)arguments[21]).booleanValue(),
				((Integer)arguments[22]).intValue(),
				((Integer)arguments[23]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[24]);
		}

		if (_methodName383.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes383, parameterTypes)) {
			return TicketFeedbackServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName384.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes384, parameterTypes)) {
			return TicketFeedbackServiceUtil.searchCount((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				(java.lang.Integer)arguments[13],
				(java.lang.String)arguments[14],
				(java.lang.Integer)arguments[15],
				(java.lang.Integer[])arguments[16],
				(java.lang.Integer[])arguments[17],
				(java.lang.Integer[])arguments[18],
				(java.lang.Integer[])arguments[19],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[20],
				((Boolean)arguments[21]).booleanValue());
		}

		if (_methodName385.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes385, parameterTypes)) {
			return TicketFeedbackServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName386.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes386, parameterTypes)) {
			return TicketFeedbackServiceUtil.updateTicketFeedback(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				(java.lang.String)arguments[9]);
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
	private String _methodName382;
	private String[] _methodParameterTypes382;
	private String _methodName383;
	private String[] _methodParameterTypes383;
	private String _methodName384;
	private String[] _methodParameterTypes384;
	private String _methodName385;
	private String[] _methodParameterTypes385;
	private String _methodName386;
	private String[] _methodParameterTypes386;
}