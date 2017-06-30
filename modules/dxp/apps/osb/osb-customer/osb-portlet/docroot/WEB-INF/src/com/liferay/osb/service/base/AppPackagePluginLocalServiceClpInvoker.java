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

import com.liferay.osb.service.AppPackagePluginLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class AppPackagePluginLocalServiceClpInvoker {
	public AppPackagePluginLocalServiceClpInvoker() {
		_methodName0 = "addAppPackagePlugin";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.AppPackagePlugin"
			};

		_methodName1 = "createAppPackagePlugin";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAppPackagePlugin";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAppPackagePlugin";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.AppPackagePlugin"
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

		_methodName9 = "fetchAppPackagePlugin";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getAppPackagePlugin";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getAppPackagePlugins";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getAppPackagePluginsCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateAppPackagePlugin";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.AppPackagePlugin"
			};

		_methodName15 = "updateAppPackagePlugin";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.AppPackagePlugin", "boolean"
			};

		_methodName686 = "getBeanIdentifier";

		_methodParameterTypes686 = new String[] {  };

		_methodName687 = "setBeanIdentifier";

		_methodParameterTypes687 = new String[] { "java.lang.String" };

		_methodName692 = "addAppPackagePlugin";

		_methodParameterTypes692 = new String[] {
				"long", "long", "java.lang.String", "java.io.File", "int",
				"boolean"
			};

		_methodName693 = "copyAppPackagePlugins";

		_methodParameterTypes693 = new String[] { "long", "long" };

		_methodName694 = "deleteAppPackagePlugin";

		_methodParameterTypes694 = new String[] {
				"com.liferay.osb.model.AppPackagePlugin"
			};

		_methodName695 = "deleteAppPackagePlugin";

		_methodParameterTypes695 = new String[] { "long" };

		_methodName696 = "deleteAppPackagePlugins";

		_methodParameterTypes696 = new String[] { "long" };

		_methodName697 = "deleteInvalidAppPackagePlugins";

		_methodParameterTypes697 = new String[] { "long" };

		_methodName698 = "getAppPackagePlugins";

		_methodParameterTypes698 = new String[] { "long" };

		_methodName699 = "getAppPackagePlugins";

		_methodParameterTypes699 = new String[] { "long", "boolean" };

		_methodName700 = "getAppPackagePluginsCount";

		_methodParameterTypes700 = new String[] { "long" };

		_methodName701 = "getAppPackagePluginsCount";

		_methodParameterTypes701 = new String[] { "long", "boolean" };

		_methodName702 = "getOtherAppPackagePlugins";

		_methodParameterTypes702 = new String[] { "long", "java.lang.String" };

		_methodName703 = "getOtherAppPackagePluginsCount";

		_methodParameterTypes703 = new String[] { "long", "java.lang.String" };

		_methodName704 = "validateAppPackagePlugin";

		_methodParameterTypes704 = new String[] {
				"com.liferay.osb.model.AppVersion",
				"com.liferay.osb.model.AppPackage",
				"com.liferay.osb.model.AppPackagePlugin"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.addAppPackagePlugin((com.liferay.osb.model.AppPackagePlugin)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.createAppPackagePlugin(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.deleteAppPackagePlugin(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.deleteAppPackagePlugin((com.liferay.osb.model.AppPackagePlugin)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.fetchAppPackagePlugin(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.getAppPackagePlugin(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.getAppPackagePlugins(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.getAppPackagePluginsCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.updateAppPackagePlugin((com.liferay.osb.model.AppPackagePlugin)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.updateAppPackagePlugin((com.liferay.osb.model.AppPackagePlugin)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			AppPackagePluginLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.addAppPackagePlugin(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.io.File)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Boolean)arguments[5]).booleanValue());
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			AppPackagePluginLocalServiceUtil.copyAppPackagePlugins(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.deleteAppPackagePlugin((com.liferay.osb.model.AppPackagePlugin)arguments[0]);
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.deleteAppPackagePlugin(((Long)arguments[0]).longValue());
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			AppPackagePluginLocalServiceUtil.deleteAppPackagePlugins(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			AppPackagePluginLocalServiceUtil.deleteInvalidAppPackagePlugins(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.getAppPackagePlugins(((Long)arguments[0]).longValue());
		}

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.getAppPackagePlugins(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.getAppPackagePluginsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.getAppPackagePluginsCount(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.getOtherAppPackagePlugins(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return AppPackagePluginLocalServiceUtil.getOtherAppPackagePluginsCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			AppPackagePluginLocalServiceUtil.validateAppPackagePlugin((com.liferay.osb.model.AppVersion)arguments[0],
				(com.liferay.osb.model.AppPackage)arguments[1],
				(com.liferay.osb.model.AppPackagePlugin)arguments[2]);

			return null;
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
	private String _methodName686;
	private String[] _methodParameterTypes686;
	private String _methodName687;
	private String[] _methodParameterTypes687;
	private String _methodName692;
	private String[] _methodParameterTypes692;
	private String _methodName693;
	private String[] _methodParameterTypes693;
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
}