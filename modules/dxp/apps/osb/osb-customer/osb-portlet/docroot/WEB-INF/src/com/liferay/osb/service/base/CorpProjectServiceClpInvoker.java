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

import com.liferay.osb.service.CorpProjectServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class CorpProjectServiceClpInvoker {
	public CorpProjectServiceClpInvoker() {
		_methodName688 = "getBeanIdentifier";

		_methodParameterTypes688 = new String[] {  };

		_methodName689 = "setBeanIdentifier";

		_methodParameterTypes689 = new String[] { "java.lang.String" };

		_methodName694 = "addCorpProject";

		_methodParameterTypes694 = new String[] { "java.lang.String" };

		_methodName695 = "addCorpProject";

		_methodParameterTypes695 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName696 = "addCorpProject";

		_methodParameterTypes696 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName697 = "addCorpProjectUsers";

		_methodParameterTypes697 = new String[] { "long", "long[][]" };

		_methodName698 = "addCorpProjectUsers";

		_methodParameterTypes698 = new String[] { "long", "java.lang.String[][]" };

		_methodName699 = "addUserCorpProjectRoles";

		_methodParameterTypes699 = new String[] { "long", "long[][]", "long" };

		_methodName700 = "addUserCorpProjectRoles";

		_methodParameterTypes700 = new String[] {
				"long", "java.lang.String[][]", "java.lang.String"
			};

		_methodName701 = "deleteCorpProject";

		_methodParameterTypes701 = new String[] { "long" };

		_methodName702 = "deleteUserCorpProjectRoles";

		_methodParameterTypes702 = new String[] { "long", "long[][]", "long" };

		_methodName703 = "deleteUserCorpProjectRoles";

		_methodParameterTypes703 = new String[] {
				"long", "java.lang.String[][]", "long"
			};

		_methodName704 = "getCorpProject";

		_methodParameterTypes704 = new String[] { "long" };

		_methodName705 = "getUserCorpProjects";

		_methodParameterTypes705 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName706 = "hasUserCorpProject";

		_methodParameterTypes706 = new String[] { "long", "long" };

		_methodName707 = "hasUserCorpProject";

		_methodParameterTypes707 = new String[] { "java.lang.String", "long" };

		_methodName708 = "hasUserCorpProjectRole";

		_methodParameterTypes708 = new String[] {
				"java.lang.String", "long", "java.lang.String"
			};

		_methodName709 = "unsetCorpProjectUsers";

		_methodParameterTypes709 = new String[] { "long", "long[][]" };

		_methodName710 = "unsetCorpProjectUsers";

		_methodParameterTypes710 = new String[] { "long", "java.lang.String[][]" };

		_methodName711 = "updateCorpProject";

		_methodParameterTypes711 = new String[] { "long", "java.lang.String" };

		_methodName712 = "updateCorpProject";

		_methodParameterTypes712 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "long"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName688.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes688, parameterTypes)) {
			return CorpProjectServiceUtil.getBeanIdentifier();
		}

		if (_methodName689.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes689, parameterTypes)) {
			CorpProjectServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			return CorpProjectServiceUtil.addCorpProject((java.lang.String)arguments[0]);
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			return CorpProjectServiceUtil.addCorpProject((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			return CorpProjectServiceUtil.addCorpProject((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			CorpProjectServiceUtil.addCorpProjectUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
			CorpProjectServiceUtil.addCorpProjectUsers(((Long)arguments[0]).longValue(),
				(java.lang.String[])arguments[1]);

			return null;
		}

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			CorpProjectServiceUtil.addUserCorpProjectRoles(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			CorpProjectServiceUtil.addUserCorpProjectRoles(((Long)arguments[0]).longValue(),
				(java.lang.String[])arguments[1], (java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return CorpProjectServiceUtil.deleteCorpProject(((Long)arguments[0]).longValue());
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			CorpProjectServiceUtil.deleteUserCorpProjectRoles(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			CorpProjectServiceUtil.deleteUserCorpProjectRoles(((Long)arguments[0]).longValue(),
				(java.lang.String[])arguments[1],
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return CorpProjectServiceUtil.getCorpProject(((Long)arguments[0]).longValue());
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return CorpProjectServiceUtil.getUserCorpProjects((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName706.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes706, parameterTypes)) {
			return CorpProjectServiceUtil.hasUserCorpProject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName707.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes707, parameterTypes)) {
			return CorpProjectServiceUtil.hasUserCorpProject((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
			return CorpProjectServiceUtil.hasUserCorpProjectRole((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			CorpProjectServiceUtil.unsetCorpProjectUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName710.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes710, parameterTypes)) {
			CorpProjectServiceUtil.unsetCorpProjectUsers(((Long)arguments[0]).longValue(),
				(java.lang.String[])arguments[1]);

			return null;
		}

		if (_methodName711.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes711, parameterTypes)) {
			return CorpProjectServiceUtil.updateCorpProject(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName712.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes712, parameterTypes)) {
			return CorpProjectServiceUtil.updateCorpProject(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], ((Long)arguments[4]).longValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName688;
	private String[] _methodParameterTypes688;
	private String _methodName689;
	private String[] _methodParameterTypes689;
	private String _methodName694;
	private String[] _methodParameterTypes694;
	private String _methodName695;
	private String[] _methodParameterTypes695;
	private String _methodName696;
	private String[] _methodParameterTypes696;
	private String _methodName697;
	private String[] _methodParameterTypes697;
	private String _methodName698;
	private String[] _methodParameterTypes698;
	private String _methodName699;
	private String[] _methodParameterTypes699;
	private String _methodName700;
	private String[] _methodParameterTypes700;
	private String _methodName701;
	private String[] _methodParameterTypes701;
	private String _methodName702;
	private String[] _methodParameterTypes702;
	private String _methodName703;
	private String[] _methodParameterTypes703;
	private String _methodName704;
	private String[] _methodParameterTypes704;
	private String _methodName705;
	private String[] _methodParameterTypes705;
	private String _methodName706;
	private String[] _methodParameterTypes706;
	private String _methodName707;
	private String[] _methodParameterTypes707;
	private String _methodName708;
	private String[] _methodParameterTypes708;
	private String _methodName709;
	private String[] _methodParameterTypes709;
	private String _methodName710;
	private String[] _methodParameterTypes710;
	private String _methodName711;
	private String[] _methodParameterTypes711;
	private String _methodName712;
	private String[] _methodParameterTypes712;
}