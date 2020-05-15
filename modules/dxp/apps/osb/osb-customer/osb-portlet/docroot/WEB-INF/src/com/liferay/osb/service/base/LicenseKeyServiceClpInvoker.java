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
		_methodName68 = "getOSGiServiceIdentifier";

		_methodParameterTypes68 = new String[] {  };

		_methodName73 = "addDeveloperLicenseKey";

		_methodParameterTypes73 = new String[] { "long", "java.lang.String", "int" };

		_methodName74 = "addLicenseKey";

		_methodParameterTypes74 = new String[] {
				"long", "long", "java.lang.String", "long", "long", "long",
				"int", "long", "java.lang.String", "int", "int",
				"java.lang.String", "java.lang.String[][]",
				"java.lang.String[][]", "java.lang.String[][]",
				"java.lang.String[][]", "java.util.Date", "java.util.Date",
				"boolean", "boolean"
			};

		_methodName75 = "addLicenseKey";

		_methodParameterTypes75 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "int",
				"java.lang.String", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.util.Date", "java.util.Date"
			};

		_methodName76 = "generateCommerceLicenseKey";

		_methodParameterTypes76 = new String[] {
				"java.lang.String", "java.util.Date", "long"
			};

		_methodName77 = "generateWeDeployLicenseKey";

		_methodParameterTypes77 = new String[] {
				"java.lang.String", "java.util.Date", "long"
			};

		_methodName78 = "getAssetReceiptLicenseLicenseKeys";

		_methodParameterTypes78 = new String[] {
				"java.lang.String", "boolean", "boolean"
			};

		_methodName79 = "getAssetReceiptLicenseLicenseKeysCount";

		_methodParameterTypes79 = new String[] {
				"java.lang.String", "boolean", "boolean"
			};

		_methodName80 = "getLicenseKey";

		_methodParameterTypes80 = new String[] { "long" };

		_methodName81 = "getLicenseKey";

		_methodParameterTypes81 = new String[] { "java.lang.String" };

		_methodName82 = "getLicenseKeys";

		_methodParameterTypes82 = new String[] { "long", "java.lang.String" };

		_methodName83 = "getLicenseKeys";

		_methodParameterTypes83 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName84 = "getLicenseKeys";

		_methodParameterTypes84 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName85 = "getLicenseKeysByName";

		_methodParameterTypes85 = new String[] {
				"java.lang.String", "java.lang.String", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName86 = "getLicenseKeySetLicenseKeys";

		_methodParameterTypes86 = new String[] { "long" };

		_methodName87 = "getOfferingEntryGroupLicenseKeys";

		_methodParameterTypes87 = new String[] {
				"long[][]", "boolean", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName88 = "getOfferingEntryGroupLicenseKeysCount";

		_methodParameterTypes88 = new String[] { "long[][]", "boolean", "boolean" };

		_methodName89 = "getOfferingEntryLicenseKeysCount";

		_methodParameterTypes89 = new String[] { "long", "boolean", "boolean" };

		_methodName90 = "isActive";

		_methodParameterTypes90 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName91 = "registerLicenseKey";

		_methodParameterTypes91 = new String[] {
				"java.lang.String", "java.lang.String", "int", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName92 = "renewLicenseKey";

		_methodParameterTypes92 = new String[] { "long", "java.util.Date", "int" };

		_methodName93 = "renewLicenseKey";

		_methodParameterTypes93 = new String[] {
				"java.lang.String", "java.util.Date", "java.util.Date"
			};

		_methodName94 = "search";

		_methodParameterTypes94 = new String[] {
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

		_methodName95 = "search";

		_methodParameterTypes95 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName96 = "searchCount";

		_methodParameterTypes96 = new String[] {
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

		_methodName97 = "searchCount";

		_methodParameterTypes97 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName98 = "updateLicenseKey";

		_methodParameterTypes98 = new String[] { "long", "long", "boolean" };

		_methodName99 = "updateLicenseKey";

		_methodParameterTypes99 = new String[] {
				"long", "long", "long", "long", "java.lang.String", "boolean"
			};

		_methodName100 = "updateLicenseKey";

		_methodParameterTypes100 = new String[] {
				"java.lang.String", "java.lang.String", "boolean"
			};

		_methodName101 = "updateLicenseKeys";

		_methodParameterTypes101 = new String[] { "java.lang.String", "boolean" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return LicenseKeyServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			return LicenseKeyServiceUtil.addDeveloperLicenseKey(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue());
		}

		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
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
				(java.util.Date)arguments[16], (java.util.Date)arguments[17],
				((Boolean)arguments[18]).booleanValue(),
				((Boolean)arguments[19]).booleanValue());
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			return LicenseKeyServiceUtil.addLicenseKey((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue(),
				(java.lang.String)arguments[6],
				((Long)arguments[7]).longValue(),
				(java.lang.String)arguments[8], (java.lang.String)arguments[9],
				(java.lang.String)arguments[10],
				(java.lang.String)arguments[11],
				(java.lang.String)arguments[12], (java.util.Date)arguments[13],
				(java.util.Date)arguments[14]);
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return LicenseKeyServiceUtil.generateCommerceLicenseKey((java.lang.String)arguments[0],
				(java.util.Date)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			return LicenseKeyServiceUtil.generateWeDeployLicenseKey((java.lang.String)arguments[0],
				(java.util.Date)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return LicenseKeyServiceUtil.getAssetReceiptLicenseLicenseKeys((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return LicenseKeyServiceUtil.getAssetReceiptLicenseLicenseKeysCount((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return LicenseKeyServiceUtil.getLicenseKey(((Long)arguments[0]).longValue());
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			return LicenseKeyServiceUtil.getLicenseKey((java.lang.String)arguments[0]);
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return LicenseKeyServiceUtil.getLicenseKeys(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return LicenseKeyServiceUtil.getLicenseKeys((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return LicenseKeyServiceUtil.getLicenseKeys((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[6]);
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			return LicenseKeyServiceUtil.getLicenseKeysByName((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return LicenseKeyServiceUtil.getLicenseKeySetLicenseKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return LicenseKeyServiceUtil.getOfferingEntryGroupLicenseKeys((long[])arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return LicenseKeyServiceUtil.getOfferingEntryGroupLicenseKeysCount((long[])arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			return LicenseKeyServiceUtil.getOfferingEntryLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return LicenseKeyServiceUtil.isActive((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			return LicenseKeyServiceUtil.registerLicenseKey((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6], (java.lang.String)arguments[7]);
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return LicenseKeyServiceUtil.renewLicenseKey(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1], ((Integer)arguments[2]).intValue());
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			return LicenseKeyServiceUtil.renewLicenseKey((java.lang.String)arguments[0],
				(java.util.Date)arguments[1], (java.util.Date)arguments[2]);
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
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

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return LicenseKeyServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
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

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			return LicenseKeyServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			LicenseKeyServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue());

			return null;
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return LicenseKeyServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue());
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			LicenseKeyServiceUtil.updateLicenseKey((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue());

			return null;
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			LicenseKeyServiceUtil.updateLicenseKeys((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue());

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName74;
	private String[] _methodParameterTypes74;
	private String _methodName75;
	private String[] _methodParameterTypes75;
	private String _methodName76;
	private String[] _methodParameterTypes76;
	private String _methodName77;
	private String[] _methodParameterTypes77;
	private String _methodName78;
	private String[] _methodParameterTypes78;
	private String _methodName79;
	private String[] _methodParameterTypes79;
	private String _methodName80;
	private String[] _methodParameterTypes80;
	private String _methodName81;
	private String[] _methodParameterTypes81;
	private String _methodName82;
	private String[] _methodParameterTypes82;
	private String _methodName83;
	private String[] _methodParameterTypes83;
	private String _methodName84;
	private String[] _methodParameterTypes84;
	private String _methodName85;
	private String[] _methodParameterTypes85;
	private String _methodName86;
	private String[] _methodParameterTypes86;
	private String _methodName87;
	private String[] _methodParameterTypes87;
	private String _methodName88;
	private String[] _methodParameterTypes88;
	private String _methodName89;
	private String[] _methodParameterTypes89;
	private String _methodName90;
	private String[] _methodParameterTypes90;
	private String _methodName91;
	private String[] _methodParameterTypes91;
	private String _methodName92;
	private String[] _methodParameterTypes92;
	private String _methodName93;
	private String[] _methodParameterTypes93;
	private String _methodName94;
	private String[] _methodParameterTypes94;
	private String _methodName95;
	private String[] _methodParameterTypes95;
	private String _methodName96;
	private String[] _methodParameterTypes96;
	private String _methodName97;
	private String[] _methodParameterTypes97;
	private String _methodName98;
	private String[] _methodParameterTypes98;
	private String _methodName99;
	private String[] _methodParameterTypes99;
	private String _methodName100;
	private String[] _methodParameterTypes100;
	private String _methodName101;
	private String[] _methodParameterTypes101;
}