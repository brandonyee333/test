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

import com.liferay.osb.service.LicenseKeyLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class LicenseKeyLocalServiceClpInvoker {
	public LicenseKeyLocalServiceClpInvoker() {
		_methodName0 = "addLicenseKey";

		_methodParameterTypes0 = new String[] { "com.liferay.osb.model.LicenseKey" };

		_methodName1 = "createLicenseKey";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteLicenseKey";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteLicenseKey";

		_methodParameterTypes3 = new String[] { "com.liferay.osb.model.LicenseKey" };

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

		_methodName10 = "fetchLicenseKey";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getLicenseKey";

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

		_methodName17 = "getLicenseKeies";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getLicenseKeiesCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateLicenseKey";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.LicenseKey"
			};

		_methodName72 = "getOSGiServiceIdentifier";

		_methodParameterTypes72 = new String[] {  };

		_methodName77 = "addDeveloperLicenseKey";

		_methodParameterTypes77 = new String[] {
				"long", "long", "java.lang.String", "int"
			};

		_methodName78 = "addLicenseKey";

		_methodParameterTypes78 = new String[] {
				"long", "long", "java.lang.String", "long", "long", "long",
				"int", "long", "java.lang.String", "int", "int",
				"java.lang.String", "java.lang.String[][]",
				"java.lang.String[][]", "java.lang.String[][]",
				"java.lang.String[][]", "java.util.Date", "java.util.Date",
				"boolean", "boolean"
			};

		_methodName79 = "addLicenseKey";

		_methodParameterTypes79 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "int",
				"java.lang.String", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.util.Date", "java.util.Date"
			};

		_methodName80 = "buyLicenseKey";

		_methodParameterTypes80 = new String[] { "long", "long" };

		_methodName81 = "getAccountEntryLicenseKeys";

		_methodParameterTypes81 = new String[] { "long" };

		_methodName82 = "getAssetReceiptLicenseLicenseKeys";

		_methodParameterTypes82 = new String[] { "java.lang.String", "boolean" };

		_methodName83 = "getAssetReceiptLicenseLicenseKeys";

		_methodParameterTypes83 = new String[] {
				"java.lang.String", "boolean", "boolean"
			};

		_methodName84 = "getAssetReceiptLicenseLicenseKeysCount";

		_methodParameterTypes84 = new String[] {
				"java.lang.String", "boolean", "boolean"
			};

		_methodName85 = "getFirstLicenseKey";

		_methodParameterTypes85 = new String[] {
				"long", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName86 = "getLicenseKeyByUuid";

		_methodParameterTypes86 = new String[] { "java.lang.String" };

		_methodName87 = "getLicenseKeys";

		_methodParameterTypes87 = new String[] { "long", "long" };

		_methodName88 = "getLicenseKeys";

		_methodParameterTypes88 = new String[] { "long", "java.lang.String" };

		_methodName89 = "getLicenseKeys";

		_methodParameterTypes89 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName90 = "getLicenseKeys";

		_methodParameterTypes90 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName91 = "getLicenseKeysByName";

		_methodParameterTypes91 = new String[] {
				"java.lang.String", "java.lang.String", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName92 = "getLicenseKeySetLicenseKeys";

		_methodParameterTypes92 = new String[] { "long" };

		_methodName93 = "getOfferingEntryGroupLicenseKeys";

		_methodParameterTypes93 = new String[] {
				"long[][]", "boolean", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName94 = "getOfferingEntryGroupLicenseKeysCount";

		_methodParameterTypes94 = new String[] { "long[][]", "boolean", "boolean" };

		_methodName95 = "getOfferingEntryLicenseKeys";

		_methodParameterTypes95 = new String[] { "long" };

		_methodName96 = "getOfferingEntryLicenseKeys";

		_methodParameterTypes96 = new String[] { "long", "boolean", "boolean" };

		_methodName97 = "getOfferingEntryLicenseKeys";

		_methodParameterTypes97 = new String[] { "long", "long" };

		_methodName98 = "getOfferingEntryLicenseKeys";

		_methodParameterTypes98 = new String[] { "long", "long", "boolean" };

		_methodName99 = "getOfferingEntryLicenseKeysCount";

		_methodParameterTypes99 = new String[] { "long" };

		_methodName100 = "getOfferingEntryLicenseKeysCount";

		_methodParameterTypes100 = new String[] { "long", "boolean", "boolean" };

		_methodName101 = "getOfferingEntryLicenseKeysCount";

		_methodParameterTypes101 = new String[] { "long", "long" };

		_methodName102 = "getOfferingEntryLicenseKeysCount";

		_methodParameterTypes102 = new String[] { "long", "long", "boolean" };

		_methodName103 = "getUserLicenseKeysCount";

		_methodParameterTypes103 = new String[] { "long", "long" };

		_methodName104 = "renewLicenseKey";

		_methodParameterTypes104 = new String[] {
				"long", "long", "java.util.Date", "java.util.Date"
			};

		_methodName105 = "renewLicenseKey";

		_methodParameterTypes105 = new String[] {
				"long", "long", "java.util.Date", "int"
			};

		_methodName106 = "search";

		_methodParameterTypes106 = new String[] {
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

		_methodName107 = "search";

		_methodParameterTypes107 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName108 = "searchCount";

		_methodParameterTypes108 = new String[] {
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

		_methodName109 = "searchCount";

		_methodParameterTypes109 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName110 = "sendRegisteredEmail";

		_methodParameterTypes110 = new String[] {
				"com.liferay.portal.kernel.model.User",
				"com.liferay.osb.model.LicenseKey"
			};

		_methodName111 = "updateLicenseKey";

		_methodParameterTypes111 = new String[] { "long", "long", "boolean" };

		_methodName112 = "updateLicenseKey";

		_methodParameterTypes112 = new String[] { "long", "long", "long", "long" };

		_methodName113 = "updateLicenseKey";

		_methodParameterTypes113 = new String[] {
				"long", "long", "long", "long", "java.lang.String", "boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.addLicenseKey((com.liferay.osb.model.LicenseKey)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.createLicenseKey(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.deleteLicenseKey(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.deleteLicenseKey((com.liferay.osb.model.LicenseKey)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.fetchLicenseKey(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKey(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeies(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeiesCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.updateLicenseKey((com.liferay.osb.model.LicenseKey)arguments[0]);
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.addDeveloperLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue());
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.addLicenseKey(((Long)arguments[0]).longValue(),
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

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.addLicenseKey(((Long)arguments[0]).longValue(),
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

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			LicenseKeyLocalServiceUtil.buyLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getAccountEntryLicenseKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeys((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeys((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeysCount((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getFirstLicenseKey(((Long)arguments[0]).longValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[1]);
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeyByUuid((java.lang.String)arguments[0]);
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeys(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeys(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeys((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeys((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[6]);
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeysByName((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeySetLicenseKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryGroupLicenseKeys((long[])arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryGroupLicenseKeysCount((long[])arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(((Long)arguments[0]).longValue());
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getUserLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.renewLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Date)arguments[2],
				(java.util.Date)arguments[3]);
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.renewLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Date)arguments[2],
				((Integer)arguments[3]).intValue());
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.search((java.lang.Long)arguments[0],
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

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.searchCount((java.lang.Long)arguments[0],
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

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			LicenseKeyLocalServiceUtil.sendRegisteredEmail((com.liferay.portal.kernel.model.User)arguments[0],
				(com.liferay.osb.model.LicenseKey)arguments[1]);

			return null;
		}

		if (_methodName111.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes111, parameterTypes)) {
			LicenseKeyLocalServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue());

			return null;
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue());
		}

		if (_methodName113.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes113, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue());
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
	private String _methodName72;
	private String[] _methodParameterTypes72;
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
	private String _methodName102;
	private String[] _methodParameterTypes102;
	private String _methodName103;
	private String[] _methodParameterTypes103;
	private String _methodName104;
	private String[] _methodParameterTypes104;
	private String _methodName105;
	private String[] _methodParameterTypes105;
	private String _methodName106;
	private String[] _methodParameterTypes106;
	private String _methodName107;
	private String[] _methodParameterTypes107;
	private String _methodName108;
	private String[] _methodParameterTypes108;
	private String _methodName109;
	private String[] _methodParameterTypes109;
	private String _methodName110;
	private String[] _methodParameterTypes110;
	private String _methodName111;
	private String[] _methodParameterTypes111;
	private String _methodName112;
	private String[] _methodParameterTypes112;
	private String _methodName113;
	private String[] _methodParameterTypes113;
}