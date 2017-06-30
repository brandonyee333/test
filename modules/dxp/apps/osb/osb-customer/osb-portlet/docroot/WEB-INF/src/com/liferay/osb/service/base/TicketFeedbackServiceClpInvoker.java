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

import com.liferay.osb.service.TicketFeedbackServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TicketFeedbackServiceClpInvoker {
	public TicketFeedbackServiceClpInvoker() {
		_methodName678 = "getBeanIdentifier";

		_methodParameterTypes678 = new String[] {  };

		_methodName679 = "setBeanIdentifier";

		_methodParameterTypes679 = new String[] { "java.lang.String" };

		_methodName684 = "addTicketFeedback";

		_methodParameterTypes684 = new String[] { "long", "int", "int" };

		_methodName685 = "fetchFirstOpenTicketFeedback";

		_methodParameterTypes685 = new String[] { "long", "long", "int" };

		_methodName686 = "fetchFirstTicketFeedback";

		_methodParameterTypes686 = new String[] { "long", "int" };

		_methodName687 = "getTicketFeedback";

		_methodParameterTypes687 = new String[] { "long" };

		_methodName688 = "getTicketFeedbacks";

		_methodParameterTypes688 = new String[] { "long", "int" };

		_methodName689 = "search";

		_methodParameterTypes689 = new String[] {
				"java.lang.String", "int", "int", "int", "int", "int", "int",
				"int", "int", "int", "int", "int", "int", "java.lang.Integer",
				"java.lang.String", "java.lang.Integer", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.util.LinkedHashMap", "boolean",
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName690 = "search";

		_methodParameterTypes690 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName691 = "searchCount";

		_methodParameterTypes691 = new String[] {
				"java.lang.String", "int", "int", "int", "int", "int", "int",
				"int", "int", "int", "int", "int", "int", "java.lang.Integer",
				"java.lang.String", "java.lang.Integer", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.util.LinkedHashMap", "boolean"
			};

		_methodName692 = "searchCount";

		_methodParameterTypes692 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName693 = "updateTicketFeedback";

		_methodParameterTypes693 = new String[] {
				"long", "int", "int", "int", "int", "int", "int", "int", "int",
				"java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName678.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes678, parameterTypes)) {
			return TicketFeedbackServiceUtil.getBeanIdentifier();
		}

		if (_methodName679.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes679, parameterTypes)) {
			TicketFeedbackServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName684.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes684, parameterTypes)) {
			return TicketFeedbackServiceUtil.addTicketFeedback(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName685.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes685, parameterTypes)) {
			return TicketFeedbackServiceUtil.fetchFirstOpenTicketFeedback(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return TicketFeedbackServiceUtil.fetchFirstTicketFeedback(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			return TicketFeedbackServiceUtil.getTicketFeedback(((Long)arguments[0]).longValue());
		}

		if (_methodName688.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes688, parameterTypes)) {
			return TicketFeedbackServiceUtil.getTicketFeedbacks(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName689.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes689, parameterTypes)) {
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

		if (_methodName690.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes690, parameterTypes)) {
			return TicketFeedbackServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName691.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes691, parameterTypes)) {
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

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			return TicketFeedbackServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
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

	private String _methodName678;
	private String[] _methodParameterTypes678;
	private String _methodName679;
	private String[] _methodParameterTypes679;
	private String _methodName684;
	private String[] _methodParameterTypes684;
	private String _methodName685;
	private String[] _methodParameterTypes685;
	private String _methodName686;
	private String[] _methodParameterTypes686;
	private String _methodName687;
	private String[] _methodParameterTypes687;
	private String _methodName688;
	private String[] _methodParameterTypes688;
	private String _methodName689;
	private String[] _methodParameterTypes689;
	private String _methodName690;
	private String[] _methodParameterTypes690;
	private String _methodName691;
	private String[] _methodParameterTypes691;
	private String _methodName692;
	private String[] _methodParameterTypes692;
	private String _methodName693;
	private String[] _methodParameterTypes693;
}