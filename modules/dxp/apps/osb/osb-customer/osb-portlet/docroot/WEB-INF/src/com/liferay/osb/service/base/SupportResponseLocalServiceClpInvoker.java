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

import com.liferay.osb.service.SupportResponseLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class SupportResponseLocalServiceClpInvoker {
	public SupportResponseLocalServiceClpInvoker() {
		_methodName0 = "addSupportResponse";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.SupportResponse"
			};

		_methodName1 = "createSupportResponse";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteSupportResponse";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteSupportResponse";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.SupportResponse"
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

		_methodName9 = "fetchSupportResponse";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getSupportResponse";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getSupportResponses";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getSupportResponsesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateSupportResponse";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.SupportResponse"
			};

		_methodName15 = "updateSupportResponse";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.SupportResponse", "boolean"
			};

		_methodName680 = "getBeanIdentifier";

		_methodParameterTypes680 = new String[] {  };

		_methodName681 = "setBeanIdentifier";

		_methodParameterTypes681 = new String[] { "java.lang.String" };

		_methodName686 = "addSupportResponse";

		_methodParameterTypes686 = new String[] {
				"long", "java.lang.String", "int", "int", "int", "int", "int",
				"int", "int"
			};

		_methodName687 = "deleteSupportResponse";

		_methodParameterTypes687 = new String[] { "long" };

		_methodName688 = "fetchSupportResponseByName";

		_methodParameterTypes688 = new String[] { "java.lang.String" };

		_methodName689 = "getSupportResponseByName";

		_methodParameterTypes689 = new String[] { "java.lang.String" };

		_methodName690 = "updateSupportResponse";

		_methodParameterTypes690 = new String[] {
				"long", "java.lang.String", "int", "int", "int", "int", "int",
				"int", "int"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SupportResponseLocalServiceUtil.addSupportResponse((com.liferay.osb.model.SupportResponse)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SupportResponseLocalServiceUtil.createSupportResponse(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SupportResponseLocalServiceUtil.deleteSupportResponse(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SupportResponseLocalServiceUtil.deleteSupportResponse((com.liferay.osb.model.SupportResponse)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SupportResponseLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SupportResponseLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SupportResponseLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SupportResponseLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SupportResponseLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SupportResponseLocalServiceUtil.fetchSupportResponse(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SupportResponseLocalServiceUtil.getSupportResponse(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SupportResponseLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SupportResponseLocalServiceUtil.getSupportResponses(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SupportResponseLocalServiceUtil.getSupportResponsesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SupportResponseLocalServiceUtil.updateSupportResponse((com.liferay.osb.model.SupportResponse)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SupportResponseLocalServiceUtil.updateSupportResponse((com.liferay.osb.model.SupportResponse)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName680.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes680, parameterTypes)) {
			return SupportResponseLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName681.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes681, parameterTypes)) {
			SupportResponseLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return SupportResponseLocalServiceUtil.addSupportResponse(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue());
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			return SupportResponseLocalServiceUtil.deleteSupportResponse(((Long)arguments[0]).longValue());
		}

		if (_methodName688.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes688, parameterTypes)) {
			return SupportResponseLocalServiceUtil.fetchSupportResponseByName((java.lang.String)arguments[0]);
		}

		if (_methodName689.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes689, parameterTypes)) {
			return SupportResponseLocalServiceUtil.getSupportResponseByName((java.lang.String)arguments[0]);
		}

		if (_methodName690.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes690, parameterTypes)) {
			return SupportResponseLocalServiceUtil.updateSupportResponse(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue());
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
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName680;
	private String[] _methodParameterTypes680;
	private String _methodName681;
	private String[] _methodParameterTypes681;
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
}