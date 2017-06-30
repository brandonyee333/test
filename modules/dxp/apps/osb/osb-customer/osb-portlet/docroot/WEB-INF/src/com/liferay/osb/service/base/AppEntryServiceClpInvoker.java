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

import com.liferay.osb.service.AppEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class AppEntryServiceClpInvoker {
	public AppEntryServiceClpInvoker() {
		_methodName716 = "getBeanIdentifier";

		_methodParameterTypes716 = new String[] {  };

		_methodName717 = "setBeanIdentifier";

		_methodParameterTypes717 = new String[] { "java.lang.String" };

		_methodName722 = "addAppEntry";

		_methodParameterTypes722 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean", "int",
				"java.lang.String", "java.util.Map", "java.io.File", "boolean",
				"java.util.Map", "int", "java.lang.String", "boolean", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName723 = "addAppEntryMedia";

		_methodParameterTypes723 = new String[] {
				"long", "java.lang.String", "java.io.File"
			};

		_methodName724 = "buildLiferayPackage";

		_methodParameterTypes724 = new String[] {
				"com.liferay.osb.model.AppPackage", "boolean"
			};

		_methodName725 = "deleteAppEntry";

		_methodParameterTypes725 = new String[] { "long" };

		_methodName726 = "deleteAppEntryMedia";

		_methodParameterTypes726 = new String[] { "long", "long" };

		_methodName727 = "getAppEntry";

		_methodParameterTypes727 = new String[] { "long" };

		_methodName728 = "updateAppEntry";

		_methodParameterTypes728 = new String[] {
				"long", "long", "boolean", "long[][]", "boolean", "long[][]",
				"boolean", "boolean"
			};

		_methodName729 = "updateAppEntry";

		_methodParameterTypes729 = new String[] {
				"long", "java.lang.String", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName730 = "updateAppEntry";

		_methodParameterTypes730 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean", "int",
				"java.lang.String", "int", "java.util.Map", "java.io.File",
				"boolean", "int", "java.util.Map", "int", "java.lang.String",
				"boolean", "boolean", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName731 = "updateStatus";

		_methodParameterTypes731 = new String[] {
				"long", "int", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName716.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes716, parameterTypes)) {
			return AppEntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName717.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes717, parameterTypes)) {
			AppEntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName722.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes722, parameterTypes)) {
			return AppEntryServiceUtil.addAppEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				((Boolean)arguments[9]).booleanValue(),
				((Integer)arguments[10]).intValue(),
				(java.lang.String)arguments[11],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[12],
				(java.io.File)arguments[13],
				((Boolean)arguments[14]).booleanValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[15],
				((Integer)arguments[16]).intValue(),
				(java.lang.String)arguments[17],
				((Boolean)arguments[18]).booleanValue(),
				((Boolean)arguments[19]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[20]);
		}

		if (_methodName723.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes723, parameterTypes)) {
			return AppEntryServiceUtil.addAppEntryMedia(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.io.File)arguments[2]);
		}

		if (_methodName724.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes724, parameterTypes)) {
			return AppEntryServiceUtil.buildLiferayPackage((com.liferay.osb.model.AppPackage)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName725.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes725, parameterTypes)) {
			return AppEntryServiceUtil.deleteAppEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName726.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes726, parameterTypes)) {
			AppEntryServiceUtil.deleteAppEntryMedia(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName727.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes727, parameterTypes)) {
			return AppEntryServiceUtil.getAppEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName728.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes728, parameterTypes)) {
			return AppEntryServiceUtil.updateAppEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue(), (long[])arguments[3],
				((Boolean)arguments[4]).booleanValue(), (long[])arguments[5],
				((Boolean)arguments[6]).booleanValue(),
				((Boolean)arguments[7]).booleanValue());
		}

		if (_methodName729.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes729, parameterTypes)) {
			return AppEntryServiceUtil.updateAppEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName730.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes730, parameterTypes)) {
			return AppEntryServiceUtil.updateAppEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				((Boolean)arguments[9]).booleanValue(),
				((Integer)arguments[10]).intValue(),
				(java.lang.String)arguments[11],
				((Integer)arguments[12]).intValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[13],
				(java.io.File)arguments[14],
				((Boolean)arguments[15]).booleanValue(),
				((Integer)arguments[16]).intValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[17],
				((Integer)arguments[18]).intValue(),
				(java.lang.String)arguments[19],
				((Boolean)arguments[20]).booleanValue(),
				((Boolean)arguments[21]).booleanValue(),
				((Integer)arguments[22]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[23]);
		}

		if (_methodName731.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes731, parameterTypes)) {
			return AppEntryServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName716;
	private String[] _methodParameterTypes716;
	private String _methodName717;
	private String[] _methodParameterTypes717;
	private String _methodName722;
	private String[] _methodParameterTypes722;
	private String _methodName723;
	private String[] _methodParameterTypes723;
	private String _methodName724;
	private String[] _methodParameterTypes724;
	private String _methodName725;
	private String[] _methodParameterTypes725;
	private String _methodName726;
	private String[] _methodParameterTypes726;
	private String _methodName727;
	private String[] _methodParameterTypes727;
	private String _methodName728;
	private String[] _methodParameterTypes728;
	private String _methodName729;
	private String[] _methodParameterTypes729;
	private String _methodName730;
	private String[] _methodParameterTypes730;
	private String _methodName731;
	private String[] _methodParameterTypes731;
}