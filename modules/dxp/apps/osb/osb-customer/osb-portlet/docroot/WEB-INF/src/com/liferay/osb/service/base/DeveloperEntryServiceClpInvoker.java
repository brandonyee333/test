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

import com.liferay.osb.service.DeveloperEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class DeveloperEntryServiceClpInvoker {
	public DeveloperEntryServiceClpInvoker() {
		_methodName696 = "getBeanIdentifier";

		_methodParameterTypes696 = new String[] {  };

		_methodName697 = "setBeanIdentifier";

		_methodParameterTypes697 = new String[] { "java.lang.String" };

		_methodName702 = "addCompanyDeveloperEntry";

		_methodParameterTypes702 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"long", "long", "java.util.Map", "java.lang.String",
				"java.io.File", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.io.File", "long", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName703 = "addUserDeveloperEntry";

		_methodParameterTypes703 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName704 = "updateCompanyDeveloperEntry";

		_methodParameterTypes704 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "java.io.File",
				"java.util.Map", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.io.File",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName705 = "updateDeveloperEntry";

		_methodParameterTypes705 = new String[] { "long", "java.lang.String" };

		_methodName706 = "updateDeveloperEntry";

		_methodParameterTypes706 = new String[] {
				"long", "java.lang.String", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.util.Date", "int",
				"double", "java.lang.String", "java.io.File"
			};

		_methodName707 = "updateDeveloperEntry";

		_methodParameterTypes707 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName708 = "updateDeveloperEntryGoogleAnalyticsKey";

		_methodParameterTypes708 = new String[] { "long", "java.lang.String" };

		_methodName709 = "updateStatus";

		_methodParameterTypes709 = new String[] {
				"long", "int", "java.lang.String"
			};

		_methodName710 = "updateSubscription";

		_methodParameterTypes710 = new String[] { "long", "java.util.Date", "int" };

		_methodName711 = "updateUserDeveloperEntry";

		_methodParameterTypes711 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long",
				"java.lang.String", "java.io.File"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			return DeveloperEntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			DeveloperEntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return DeveloperEntryServiceUtil.addCompanyDeveloperEntry((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				((Long)arguments[9]).longValue(),
				((Long)arguments[10]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[11],
				(java.lang.String)arguments[12], (java.io.File)arguments[13],
				(java.lang.String)arguments[14],
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16], (java.io.File)arguments[17],
				((Long)arguments[18]).longValue(),
				((Long)arguments[19]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[20]);
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return DeveloperEntryServiceUtil.addUserDeveloperEntry((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Long)arguments[5]).longValue(),
				((Long)arguments[6]).longValue(),
				(java.lang.String)arguments[7],
				(com.liferay.portal.service.ServiceContext)arguments[8]);
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return DeveloperEntryServiceUtil.updateCompanyDeveloperEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				(java.lang.String)arguments[9],
				((Long)arguments[10]).longValue(),
				((Long)arguments[11]).longValue(), (java.io.File)arguments[12],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[13],
				(java.lang.String)arguments[14],
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16], (java.io.File)arguments[17],
				(com.liferay.portal.service.ServiceContext)arguments[18]);
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return DeveloperEntryServiceUtil.updateDeveloperEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName706.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes706, parameterTypes)) {
			return DeveloperEntryServiceUtil.updateDeveloperEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.util.Date)arguments[6],
				((Integer)arguments[7]).intValue(),
				((Double)arguments[8]).doubleValue(),
				(java.lang.String)arguments[9], (java.io.File)arguments[10]);
		}

		if (_methodName707.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes707, parameterTypes)) {
			return DeveloperEntryServiceUtil.updateDeveloperEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
			return DeveloperEntryServiceUtil.updateDeveloperEntryGoogleAnalyticsKey(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			return DeveloperEntryServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2]);
		}

		if (_methodName710.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes710, parameterTypes)) {
			return DeveloperEntryServiceUtil.updateSubscription(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1], ((Integer)arguments[2]).intValue());
		}

		if (_methodName711.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes711, parameterTypes)) {
			return DeveloperEntryServiceUtil.updateUserDeveloperEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				(java.lang.String)arguments[9],
				(java.lang.String)arguments[10],
				((Long)arguments[11]).longValue(),
				((Long)arguments[12]).longValue(),
				(java.lang.String)arguments[13], (java.io.File)arguments[14]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName696;
	private String[] _methodParameterTypes696;
	private String _methodName697;
	private String[] _methodParameterTypes697;
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
}