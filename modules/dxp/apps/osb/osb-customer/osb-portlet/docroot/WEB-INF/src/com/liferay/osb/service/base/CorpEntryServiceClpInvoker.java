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

import com.liferay.osb.service.CorpEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class CorpEntryServiceClpInvoker {
	public CorpEntryServiceClpInvoker() {
		_methodName704 = "getBeanIdentifier";

		_methodParameterTypes704 = new String[] {  };

		_methodName705 = "setBeanIdentifier";

		_methodParameterTypes705 = new String[] { "java.lang.String" };

		_methodName710 = "addCorpEntry";

		_methodParameterTypes710 = new String[] {
				"java.lang.String", "java.util.Map", "java.io.File",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName711 = "addCorpEntryUsers";

		_methodParameterTypes711 = new String[] { "long", "long[][]" };

		_methodName712 = "addUserCorpEntryRoles";

		_methodParameterTypes712 = new String[] { "long", "long[][]", "long" };

		_methodName713 = "addUserCorpEntryRoles";

		_methodParameterTypes713 = new String[] {
				"long", "java.lang.String[][]", "java.lang.String"
			};

		_methodName714 = "deleteCorpEntry";

		_methodParameterTypes714 = new String[] { "long" };

		_methodName715 = "deleteUserCorpEntryRoles";

		_methodParameterTypes715 = new String[] { "long", "long[][]", "long" };

		_methodName716 = "getCorpEntry";

		_methodParameterTypes716 = new String[] { "long" };

		_methodName717 = "hasUserCorpEntry";

		_methodParameterTypes717 = new String[] { "long", "long" };

		_methodName718 = "mergeCorpEntry";

		_methodParameterTypes718 = new String[] {
				"long", "long", "java.lang.String", "java.util.Map", "long",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName719 = "unsetCorpEntryUsers";

		_methodParameterTypes719 = new String[] { "long", "long[][]" };

		_methodName720 = "updateCorpEntry";

		_methodParameterTypes720 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.io.File",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName721 = "updateStatus";

		_methodParameterTypes721 = new String[] {
				"long", "int", "java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return CorpEntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			CorpEntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName710.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes710, parameterTypes)) {
			return CorpEntryServiceUtil.addCorpEntry((java.lang.String)arguments[0],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.io.File)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				((Long)arguments[8]).longValue(),
				((Long)arguments[9]).longValue(),
				(java.lang.String)arguments[10],
				(java.lang.String)arguments[11],
				(java.lang.String)arguments[12],
				(java.lang.String)arguments[13],
				(java.lang.String)arguments[14],
				(java.lang.String)arguments[15],
				(com.liferay.portal.service.ServiceContext)arguments[16]);
		}

		if (_methodName711.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes711, parameterTypes)) {
			CorpEntryServiceUtil.addCorpEntryUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName712.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes712, parameterTypes)) {
			CorpEntryServiceUtil.addUserCorpEntryRoles(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName713.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes713, parameterTypes)) {
			CorpEntryServiceUtil.addUserCorpEntryRoles(((Long)arguments[0]).longValue(),
				(java.lang.String[])arguments[1], (java.lang.String)arguments[2]);

			return null;
		}

		if (_methodName714.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes714, parameterTypes)) {
			return CorpEntryServiceUtil.deleteCorpEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName715.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes715, parameterTypes)) {
			CorpEntryServiceUtil.deleteUserCorpEntryRoles(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName716.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes716, parameterTypes)) {
			return CorpEntryServiceUtil.getCorpEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName717.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes717, parameterTypes)) {
			return CorpEntryServiceUtil.hasUserCorpEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName718.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes718, parameterTypes)) {
			return CorpEntryServiceUtil.mergeCorpEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				(java.lang.String)arguments[9],
				((Long)arguments[10]).longValue(),
				((Long)arguments[11]).longValue(),
				(java.lang.String)arguments[12],
				(java.lang.String)arguments[13],
				(java.lang.String)arguments[14],
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				(java.lang.String)arguments[17],
				(com.liferay.portal.service.ServiceContext)arguments[18]);
		}

		if (_methodName719.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes719, parameterTypes)) {
			CorpEntryServiceUtil.unsetCorpEntryUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName720.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes720, parameterTypes)) {
			return CorpEntryServiceUtil.updateCorpEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.io.File)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				((Long)arguments[9]).longValue(),
				((Long)arguments[10]).longValue(),
				(java.lang.String)arguments[11],
				(java.lang.String)arguments[12],
				(java.lang.String)arguments[13],
				(java.lang.String)arguments[14],
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				(com.liferay.portal.service.ServiceContext)arguments[17]);
		}

		if (_methodName721.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes721, parameterTypes)) {
			return CorpEntryServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName704;
	private String[] _methodParameterTypes704;
	private String _methodName705;
	private String[] _methodParameterTypes705;
	private String _methodName710;
	private String[] _methodParameterTypes710;
	private String _methodName711;
	private String[] _methodParameterTypes711;
	private String _methodName712;
	private String[] _methodParameterTypes712;
	private String _methodName713;
	private String[] _methodParameterTypes713;
	private String _methodName714;
	private String[] _methodParameterTypes714;
	private String _methodName715;
	private String[] _methodParameterTypes715;
	private String _methodName716;
	private String[] _methodParameterTypes716;
	private String _methodName717;
	private String[] _methodParameterTypes717;
	private String _methodName718;
	private String[] _methodParameterTypes718;
	private String _methodName719;
	private String[] _methodParameterTypes719;
	private String _methodName720;
	private String[] _methodParameterTypes720;
	private String _methodName721;
	private String[] _methodParameterTypes721;
}