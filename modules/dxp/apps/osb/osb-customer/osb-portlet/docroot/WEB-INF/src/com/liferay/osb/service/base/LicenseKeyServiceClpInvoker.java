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

import com.liferay.osb.service.LicenseKeyServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class LicenseKeyServiceClpInvoker {
	public LicenseKeyServiceClpInvoker() {
		_methodName692 = "getBeanIdentifier";

		_methodParameterTypes692 = new String[] {  };

		_methodName693 = "setBeanIdentifier";

		_methodParameterTypes693 = new String[] { "java.lang.String" };

		_methodName698 = "addLicenseKey";

		_methodParameterTypes698 = new String[] {
				"long", "long", "long", "int", "java.lang.String", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "int", "int", "int"
			};

		_methodName699 = "addLicenseKey";

		_methodParameterTypes699 = new String[] {
				"long", "long", "java.lang.String", "long", "long", "long",
				"int", "long", "java.lang.String", "int", "int",
				"java.lang.String", "java.lang.String[][]",
				"java.lang.String[][]", "java.lang.String[][]",
				"java.lang.String[][]", "int", "int", "int", "boolean",
				"boolean"
			};

		_methodName700 = "addLicenseKey";

		_methodParameterTypes700 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String[][]", "java.lang.String[][]",
				"java.lang.String[][]", "java.lang.String[][]", "int", "int",
				"int"
			};

		_methodName701 = "exportToXML";

		_methodParameterTypes701 = new String[] { "long" };

		_methodName702 = "getLicenseKey";

		_methodParameterTypes702 = new String[] { "long" };

		_methodName703 = "getLicenseKeys";

		_methodParameterTypes703 = new String[] { "long", "java.lang.String" };

		_methodName704 = "getLicenseKeySetLicenseKeys";

		_methodParameterTypes704 = new String[] { "long" };

		_methodName705 = "getOfferingEntryGroupLicenseKeys";

		_methodParameterTypes705 = new String[] {
				"long[][]", "boolean", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName706 = "getOfferingEntryGroupLicenseKeysCount";

		_methodParameterTypes706 = new String[] { "long[][]", "boolean", "boolean" };

		_methodName707 = "isActive";

		_methodParameterTypes707 = new String[] { "long", "java.lang.String" };

		_methodName708 = "renewLicenseKey";

		_methodParameterTypes708 = new String[] { "long" };

		_methodName709 = "renewLicenseKey";

		_methodParameterTypes709 = new String[] { "long", "java.util.Date", "int" };

		_methodName710 = "search";

		_methodParameterTypes710 = new String[] {
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

		_methodName711 = "search";

		_methodParameterTypes711 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName712 = "searchCount";

		_methodParameterTypes712 = new String[] {
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

		_methodName713 = "searchCount";

		_methodParameterTypes713 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName714 = "updateLicenseKey";

		_methodParameterTypes714 = new String[] { "long", "boolean" };

		_methodName715 = "updateLicenseKey";

		_methodParameterTypes715 = new String[] {
				"long", "long", "long", "boolean"
			};

		_methodName716 = "updateLicenseKey";

		_methodParameterTypes716 = new String[] {
				"long", "long", "long", "long", "java.lang.String", "boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			return LicenseKeyServiceUtil.getBeanIdentifier();
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			LicenseKeyServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
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

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
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

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
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

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return LicenseKeyServiceUtil.exportToXML(((Long)arguments[0]).longValue());
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return LicenseKeyServiceUtil.getLicenseKey(((Long)arguments[0]).longValue());
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return LicenseKeyServiceUtil.getLicenseKeys(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return LicenseKeyServiceUtil.getLicenseKeySetLicenseKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return LicenseKeyServiceUtil.getOfferingEntryGroupLicenseKeys((long[])arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName706.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes706, parameterTypes)) {
			return LicenseKeyServiceUtil.getOfferingEntryGroupLicenseKeysCount((long[])arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName707.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes707, parameterTypes)) {
			return LicenseKeyServiceUtil.isActive(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
			return LicenseKeyServiceUtil.renewLicenseKey(((Long)arguments[0]).longValue());
		}

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			return LicenseKeyServiceUtil.renewLicenseKey(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1], ((Integer)arguments[2]).intValue());
		}

		if (_methodName710.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes710, parameterTypes)) {
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

		if (_methodName711.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes711, parameterTypes)) {
			return LicenseKeyServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName712.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes712, parameterTypes)) {
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

		if (_methodName713.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes713, parameterTypes)) {
			return LicenseKeyServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName714.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes714, parameterTypes)) {
			LicenseKeyServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());

			return null;
		}

		if (_methodName715.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes715, parameterTypes)) {
			LicenseKeyServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName716.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes716, parameterTypes)) {
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

	private String _methodName692;
	private String[] _methodParameterTypes692;
	private String _methodName693;
	private String[] _methodParameterTypes693;
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
	private String _methodName713;
	private String[] _methodParameterTypes713;
	private String _methodName714;
	private String[] _methodParameterTypes714;
	private String _methodName715;
	private String[] _methodParameterTypes715;
	private String _methodName716;
	private String[] _methodParameterTypes716;
}