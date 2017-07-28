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

import com.liferay.osb.service.LicenseKeyServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class LicenseKeyServiceClpInvoker {
	public LicenseKeyServiceClpInvoker() {
		_methodName400 = "getOSGiServiceIdentifier";

		_methodParameterTypes400 = new String[] {  };

		_methodName405 = "addLicenseKey";

		_methodParameterTypes405 = new String[] {
				"long", "long", "long", "int", "java.lang.String", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "int", "int", "int"
			};

		_methodName406 = "addLicenseKey";

		_methodParameterTypes406 = new String[] {
				"long", "long", "java.lang.String", "long", "long", "long",
				"int", "long", "java.lang.String", "int", "int",
				"java.lang.String", "java.lang.String[][]",
				"java.lang.String[][]", "java.lang.String[][]",
				"java.lang.String[][]", "int", "int", "int", "boolean",
				"boolean"
			};

		_methodName407 = "addLicenseKey";

		_methodParameterTypes407 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String[][]", "java.lang.String[][]",
				"java.lang.String[][]", "java.lang.String[][]", "int", "int",
				"int"
			};

		_methodName408 = "exportToXML";

		_methodParameterTypes408 = new String[] { "long" };

		_methodName409 = "getLicenseKey";

		_methodParameterTypes409 = new String[] { "long" };

		_methodName410 = "getLicenseKeys";

		_methodParameterTypes410 = new String[] { "long", "java.lang.String" };

		_methodName411 = "getLicenseKeySetLicenseKeys";

		_methodParameterTypes411 = new String[] { "long" };

		_methodName412 = "getOfferingEntryGroupLicenseKeys";

		_methodParameterTypes412 = new String[] {
				"long[][]", "boolean", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName413 = "getOfferingEntryGroupLicenseKeysCount";

		_methodParameterTypes413 = new String[] { "long[][]", "boolean", "boolean" };

		_methodName414 = "isActive";

		_methodParameterTypes414 = new String[] { "long", "java.lang.String" };

		_methodName415 = "renewLicenseKey";

		_methodParameterTypes415 = new String[] { "long" };

		_methodName416 = "renewLicenseKey";

		_methodParameterTypes416 = new String[] { "long", "java.util.Date", "int" };

		_methodName417 = "search";

		_methodParameterTypes417 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int", "int", "int",
				"int", "int", "int", "long[][]", "long[][]", "java.lang.String",
				"java.lang.String", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"int", "int", "int", "int", "int", "int",
				"java.util.LinkedHashMap", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName418 = "search";

		_methodParameterTypes418 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName419 = "searchCount";

		_methodParameterTypes419 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int", "int", "int",
				"int", "int", "int", "long[][]", "long[][]", "java.lang.String",
				"java.lang.String", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"int", "int", "int", "int", "int", "int",
				"java.util.LinkedHashMap", "boolean"
			};

		_methodName420 = "searchCount";

		_methodParameterTypes420 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName421 = "updateLicenseKey";

		_methodParameterTypes421 = new String[] { "long", "boolean" };

		_methodName422 = "updateLicenseKey";

		_methodParameterTypes422 = new String[] {
				"long", "long", "long", "boolean"
			};

		_methodName423 = "updateLicenseKey";

		_methodParameterTypes423 = new String[] {
				"long", "long", "long", "long", "java.lang.String", "boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName400.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes400, parameterTypes)) {
			return LicenseKeyServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName405.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes405, parameterTypes)) {
			return LicenseKeyServiceUtil.addLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				(java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue(),
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				(java.lang.String)arguments[8], (java.lang.String)arguments[9],
				(java.lang.String)arguments[10],
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue());
		}

		if (_methodName406.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes406, parameterTypes)) {
			return LicenseKeyServiceUtil.addLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Long)arguments[5]).longValue(),
				((Integer)arguments[6]).intValue(),
				((Long)arguments[7]).longValue(),
				(java.lang.String)arguments[8],
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				(java.lang.String)arguments[11],
				(java.lang.String[])arguments[12],
				(java.lang.String[])arguments[13],
				(java.lang.String[])arguments[14],
				(java.lang.String[])arguments[15],
				((Integer)arguments[16]).intValue(),
				((Integer)arguments[17]).intValue(),
				((Integer)arguments[18]).intValue(),
				((Boolean)arguments[19]).booleanValue(),
				((Boolean)arguments[20]).booleanValue());
		}

		if (_methodName407.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes407, parameterTypes)) {
			return LicenseKeyServiceUtil.addLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String[])arguments[4],
				(java.lang.String[])arguments[5],
				(java.lang.String[])arguments[6],
				(java.lang.String[])arguments[7],
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue());
		}

		if (_methodName408.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes408, parameterTypes)) {
			return LicenseKeyServiceUtil.exportToXML(((Long)arguments[0]).longValue());
		}

		if (_methodName409.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes409, parameterTypes)) {
			return LicenseKeyServiceUtil.getLicenseKey(((Long)arguments[0]).longValue());
		}

		if (_methodName410.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes410, parameterTypes)) {
			return LicenseKeyServiceUtil.getLicenseKeys(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName411.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes411, parameterTypes)) {
			return LicenseKeyServiceUtil.getLicenseKeySetLicenseKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName412.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes412, parameterTypes)) {
			return LicenseKeyServiceUtil.getOfferingEntryGroupLicenseKeys((long[])arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName413.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes413, parameterTypes)) {
			return LicenseKeyServiceUtil.getOfferingEntryGroupLicenseKeysCount((long[])arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName414.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes414, parameterTypes)) {
			return LicenseKeyServiceUtil.isActive(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName415.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes415, parameterTypes)) {
			return LicenseKeyServiceUtil.renewLicenseKey(((Long)arguments[0]).longValue());
		}

		if (_methodName416.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes416, parameterTypes)) {
			return LicenseKeyServiceUtil.renewLicenseKey(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1], ((Integer)arguments[2]).intValue());
		}

		if (_methodName417.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes417, parameterTypes)) {
			return LicenseKeyServiceUtil.search((java.lang.Long)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(java.lang.Long)arguments[7],
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				(java.lang.String)arguments[14],
				(java.lang.String)arguments[15],
				((Integer)arguments[16]).intValue(),
				((Integer)arguments[17]).intValue(),
				((Integer)arguments[18]).intValue(),
				((Integer)arguments[19]).intValue(),
				((Integer)arguments[20]).intValue(),
				((Integer)arguments[21]).intValue(), (long[])arguments[22],
				(long[])arguments[23], (java.lang.String)arguments[24],
				(java.lang.String)arguments[25], (int[])arguments[26],
				(java.lang.String)arguments[27],
				(java.lang.String)arguments[28],
				(java.lang.String)arguments[29],
				(java.lang.String)arguments[30],
				(java.lang.String)arguments[31],
				(java.lang.String)arguments[32],
				(java.lang.String)arguments[33],
				((Integer)arguments[34]).intValue(),
				((Integer)arguments[35]).intValue(),
				((Integer)arguments[36]).intValue(),
				((Integer)arguments[37]).intValue(),
				((Integer)arguments[38]).intValue(),
				((Integer)arguments[39]).intValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[40],
				((Boolean)arguments[41]).booleanValue(),
				((Integer)arguments[42]).intValue(),
				((Integer)arguments[43]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[44]);
		}

		if (_methodName418.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes418, parameterTypes)) {
			return LicenseKeyServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName419.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes419, parameterTypes)) {
			return LicenseKeyServiceUtil.searchCount((java.lang.Long)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(java.lang.Long)arguments[7],
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				(java.lang.String)arguments[14],
				(java.lang.String)arguments[15],
				((Integer)arguments[16]).intValue(),
				((Integer)arguments[17]).intValue(),
				((Integer)arguments[18]).intValue(),
				((Integer)arguments[19]).intValue(),
				((Integer)arguments[20]).intValue(),
				((Integer)arguments[21]).intValue(), (long[])arguments[22],
				(long[])arguments[23], (java.lang.String)arguments[24],
				(java.lang.String)arguments[25], (int[])arguments[26],
				(java.lang.String)arguments[27],
				(java.lang.String)arguments[28],
				(java.lang.String)arguments[29],
				(java.lang.String)arguments[30],
				(java.lang.String)arguments[31],
				(java.lang.String)arguments[32],
				(java.lang.String)arguments[33],
				((Integer)arguments[34]).intValue(),
				((Integer)arguments[35]).intValue(),
				((Integer)arguments[36]).intValue(),
				((Integer)arguments[37]).intValue(),
				((Integer)arguments[38]).intValue(),
				((Integer)arguments[39]).intValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[40],
				((Boolean)arguments[41]).booleanValue());
		}

		if (_methodName420.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes420, parameterTypes)) {
			return LicenseKeyServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName421.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes421, parameterTypes)) {
			LicenseKeyServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());

			return null;
		}

		if (_methodName422.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes422, parameterTypes)) {
			LicenseKeyServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName423.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes423, parameterTypes)) {
			LicenseKeyServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue());

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName400;
	private String[] _methodParameterTypes400;
	private String _methodName405;
	private String[] _methodParameterTypes405;
	private String _methodName406;
	private String[] _methodParameterTypes406;
	private String _methodName407;
	private String[] _methodParameterTypes407;
	private String _methodName408;
	private String[] _methodParameterTypes408;
	private String _methodName409;
	private String[] _methodParameterTypes409;
	private String _methodName410;
	private String[] _methodParameterTypes410;
	private String _methodName411;
	private String[] _methodParameterTypes411;
	private String _methodName412;
	private String[] _methodParameterTypes412;
	private String _methodName413;
	private String[] _methodParameterTypes413;
	private String _methodName414;
	private String[] _methodParameterTypes414;
	private String _methodName415;
	private String[] _methodParameterTypes415;
	private String _methodName416;
	private String[] _methodParameterTypes416;
	private String _methodName417;
	private String[] _methodParameterTypes417;
	private String _methodName418;
	private String[] _methodParameterTypes418;
	private String _methodName419;
	private String[] _methodParameterTypes419;
	private String _methodName420;
	private String[] _methodParameterTypes420;
	private String _methodName421;
	private String[] _methodParameterTypes421;
	private String _methodName422;
	private String[] _methodParameterTypes422;
	private String _methodName423;
	private String[] _methodParameterTypes423;
}