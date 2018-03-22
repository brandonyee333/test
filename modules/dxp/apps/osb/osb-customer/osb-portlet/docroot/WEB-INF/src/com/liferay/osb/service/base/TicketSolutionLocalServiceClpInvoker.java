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

import com.liferay.osb.service.TicketSolutionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketSolutionLocalServiceClpInvoker {
	public TicketSolutionLocalServiceClpInvoker() {
		_methodName0 = "addTicketSolution";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.TicketSolution"
			};

		_methodName1 = "createTicketSolution";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteTicketSolution";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteTicketSolution";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.TicketSolution"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchTicketSolution";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getTicketSolution";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getActionableDynamicQuery";

		_methodParameterTypes12 = new String[] {  };

		_methodName13 = "getIndexableActionableDynamicQuery";

		_methodParameterTypes13 = new String[] {  };

		_methodName15 = "deletePersistedModel";

		_methodParameterTypes15 = new String[] {
				"com.liferay.portal.kernel.model.PersistedModel"
			};

		_methodName16 = "getPersistedModel";

		_methodParameterTypes16 = new String[] { "java.io.Serializable" };

		_methodName17 = "getTicketSolutions";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getTicketSolutionsCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateTicketSolution";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.TicketSolution"
			};

		_methodName278 = "getOSGiServiceIdentifier";

		_methodParameterTypes278 = new String[] {  };

		_methodName283 = "addTicketSolution";

		_methodParameterTypes283 = new String[] {
				"long", "long", "java.lang.String", "boolean", "int",
				"java.lang.String", "int", "boolean", "boolean", "boolean",
				"boolean", "int", "int", "java.lang.String", "java.util.List",
				"java.util.List", "java.util.List"
			};

		_methodName284 = "getActiveTicketSolution";

		_methodParameterTypes284 = new String[] { "long" };

		_methodName285 = "getTicketSolutions";

		_methodParameterTypes285 = new String[] { "long" };

		_methodName286 = "updateStatus";

		_methodParameterTypes286 = new String[] { "long", "int", "int" };

		_methodName287 = "updateTicketSolution";

		_methodParameterTypes287 = new String[] {
				"long", "long", "int", "long", "java.lang.String", "int"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.addTicketSolution((com.liferay.osb.model.TicketSolution)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.createTicketSolution(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.deleteTicketSolution(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.deleteTicketSolution((com.liferay.osb.model.TicketSolution)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.fetchTicketSolution(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.getTicketSolution(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.getTicketSolutions(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.getTicketSolutionsCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.updateTicketSolution((com.liferay.osb.model.TicketSolution)arguments[0]);
		}

		if (_methodName278.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes278, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName283.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes283, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.addTicketSolution(((Long)arguments[0]).longValue(),
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

		if (_methodName284.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes284, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.getActiveTicketSolution(((Long)arguments[0]).longValue());
		}

		if (_methodName285.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes285, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.getTicketSolutions(((Long)arguments[0]).longValue());
		}

		if (_methodName286.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes286, parameterTypes)) {
			TicketSolutionLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());

			return null;
		}

		if (_methodName287.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes287, parameterTypes)) {
			return TicketSolutionLocalServiceUtil.updateTicketSolution(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName18;
	private String[] _methodParameterTypes18;
	private String _methodName19;
	private String[] _methodParameterTypes19;
	private String _methodName278;
	private String[] _methodParameterTypes278;
	private String _methodName283;
	private String[] _methodParameterTypes283;
	private String _methodName284;
	private String[] _methodParameterTypes284;
	private String _methodName285;
	private String[] _methodParameterTypes285;
	private String _methodName286;
	private String[] _methodParameterTypes286;
	private String _methodName287;
	private String[] _methodParameterTypes287;
}