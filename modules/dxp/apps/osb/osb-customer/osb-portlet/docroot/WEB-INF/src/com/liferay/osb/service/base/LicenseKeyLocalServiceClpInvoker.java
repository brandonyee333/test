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

		_methodName182 = "getOSGiServiceIdentifier";

		_methodParameterTypes182 = new String[] {  };

		_methodName187 = "addDeveloperLicenseKey";

		_methodParameterTypes187 = new String[] {
				"long", "long", "java.lang.String", "int"
			};

		_methodName188 = "addLicenseKey";

		_methodParameterTypes188 = new String[] {
				"long", "com.liferay.osb.model.LicenseKeySet",
				"java.lang.String", "com.liferay.osb.model.OfferingEntry",
				"com.liferay.osb.model.LicenseEntry",
				"com.liferay.osb.model.ProductEntry", "int", "long",
				"java.lang.String", "int", "int", "java.lang.String",
				"java.lang.String[][]", "java.lang.String[][]",
				"java.lang.String[][]", "java.lang.String[][]", "java.util.Date",
				"java.util.Date", "java.lang.String", "boolean", "boolean"
			};

		_methodName189 = "addLicenseKey";

		_methodParameterTypes189 = new String[] {
				"long", "long", "java.lang.String", "long", "long", "long",
				"int", "long", "java.lang.String", "int", "int",
				"java.lang.String", "java.lang.String[][]",
				"java.lang.String[][]", "java.lang.String[][]",
				"java.lang.String[][]", "java.util.Date", "boolean", "boolean"
			};

		_methodName190 = "addLicenseKey";

		_methodParameterTypes190 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "int",
				"java.lang.String", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.util.Date", "java.util.Date"
			};

		_methodName191 = "buyLicenseKey";

		_methodParameterTypes191 = new String[] { "long", "long" };

		_methodName192 = "getAccountEntryLicenseKeys";

		_methodParameterTypes192 = new String[] { "long" };

		_methodName193 = "getAssetReceiptLicenseLicenseKeys";

		_methodParameterTypes193 = new String[] { "java.lang.String", "boolean" };

		_methodName194 = "getAssetReceiptLicenseLicenseKeys";

		_methodParameterTypes194 = new String[] {
				"java.lang.String", "boolean", "boolean"
			};

		_methodName195 = "getAssetReceiptLicenseLicenseKeysCount";

		_methodParameterTypes195 = new String[] {
				"java.lang.String", "boolean", "boolean"
			};

		_methodName196 = "getFirstLicenseKey";

		_methodParameterTypes196 = new String[] {
				"long", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName197 = "getLicenseKeyByUuid";

		_methodParameterTypes197 = new String[] { "java.lang.String" };

		_methodName198 = "getLicenseKeys";

		_methodParameterTypes198 = new String[] { "long", "long" };

		_methodName199 = "getLicenseKeys";

		_methodParameterTypes199 = new String[] { "long", "java.lang.String" };

		_methodName200 = "getLicenseKeys";

		_methodParameterTypes200 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName201 = "getLicenseKeys";

		_methodParameterTypes201 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName202 = "getLicenseKeysByName";

		_methodParameterTypes202 = new String[] {
				"java.lang.String", "java.lang.String", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName203 = "getLicenseKeySetLicenseKeys";

		_methodParameterTypes203 = new String[] { "long" };

		_methodName204 = "getOfferingEntryGroupLicenseKeys";

		_methodParameterTypes204 = new String[] {
				"long[][]", "boolean", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName205 = "getOfferingEntryGroupLicenseKeysCount";

		_methodParameterTypes205 = new String[] { "long[][]", "boolean", "boolean" };

		_methodName206 = "getOfferingEntryLicenseKeys";

		_methodParameterTypes206 = new String[] { "long" };

		_methodName207 = "getOfferingEntryLicenseKeys";

		_methodParameterTypes207 = new String[] { "long", "boolean", "boolean" };

		_methodName208 = "getOfferingEntryLicenseKeys";

		_methodParameterTypes208 = new String[] { "long", "long" };

		_methodName209 = "getOfferingEntryLicenseKeys";

		_methodParameterTypes209 = new String[] { "long", "long", "boolean" };

		_methodName210 = "getOfferingEntryLicenseKeysCount";

		_methodParameterTypes210 = new String[] { "long" };

		_methodName211 = "getOfferingEntryLicenseKeysCount";

		_methodParameterTypes211 = new String[] { "long", "boolean", "boolean" };

		_methodName212 = "getOfferingEntryLicenseKeysCount";

		_methodParameterTypes212 = new String[] { "long", "long" };

		_methodName213 = "getOfferingEntryLicenseKeysCount";

		_methodParameterTypes213 = new String[] { "long", "long", "boolean" };

		_methodName214 = "getUserLicenseKeysCount";

		_methodParameterTypes214 = new String[] { "long", "long" };

		_methodName215 = "renewLicenseKey";

		_methodParameterTypes215 = new String[] {
				"long", "long", "java.util.Date", "java.util.Date"
			};

		_methodName216 = "renewLicenseKey";

		_methodParameterTypes216 = new String[] {
				"long", "long", "java.util.Date", "int"
			};

		_methodName217 = "renewTrialLicenseKey";

		_methodParameterTypes217 = new String[] { "long" };

		_methodName218 = "search";

		_methodParameterTypes218 = new String[] {
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

		_methodName219 = "search";

		_methodParameterTypes219 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName220 = "searchCount";

		_methodParameterTypes220 = new String[] {
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

		_methodName221 = "searchCount";

		_methodParameterTypes221 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName222 = "sendRegisteredEmail";

		_methodParameterTypes222 = new String[] {
				"com.liferay.portal.kernel.model.User",
				"com.liferay.osb.model.LicenseKey"
			};

		_methodName223 = "sendTrialRenewalNotificationEmail";

		_methodParameterTypes223 = new String[] { "java.lang.String", "long" };

		_methodName224 = "updateLicenseKey";

		_methodParameterTypes224 = new String[] { "long", "long", "boolean" };

		_methodName225 = "updateLicenseKey";

		_methodParameterTypes225 = new String[] { "long", "long", "long", "long" };

		_methodName226 = "updateLicenseKey";

		_methodParameterTypes226 = new String[] {
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

		if (_methodName182.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes182, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName187.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes187, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.addDeveloperLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue());
		}

		if (_methodName188.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes188, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.addLicenseKey(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.LicenseKeySet)arguments[1],
				(java.lang.String)arguments[2],
				(com.liferay.osb.model.OfferingEntry)arguments[3],
				(com.liferay.osb.model.LicenseEntry)arguments[4],
				(com.liferay.osb.model.ProductEntry)arguments[5],
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
				(java.lang.String)arguments[18],
				((Boolean)arguments[19]).booleanValue(),
				((Boolean)arguments[20]).booleanValue());
		}

		if (_methodName189.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes189, parameterTypes)) {
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
				(java.util.Date)arguments[16],
				((Boolean)arguments[17]).booleanValue(),
				((Boolean)arguments[18]).booleanValue());
		}

		if (_methodName190.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes190, parameterTypes)) {
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

		if (_methodName191.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes191, parameterTypes)) {
			LicenseKeyLocalServiceUtil.buyLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName192.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes192, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getAccountEntryLicenseKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName193.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes193, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeys((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName194.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes194, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeys((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName195.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes195, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeysCount((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName196.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes196, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getFirstLicenseKey(((Long)arguments[0]).longValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[1]);
		}

		if (_methodName197.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes197, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeyByUuid((java.lang.String)arguments[0]);
		}

		if (_methodName198.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes198, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeys(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName199.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes199, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeys(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName200.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes200, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeys((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName201.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes201, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeys((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[6]);
		}

		if (_methodName202.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes202, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeysByName((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName203.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes203, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeySetLicenseKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName204.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes204, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryGroupLicenseKeys((long[])arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName205.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes205, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryGroupLicenseKeysCount((long[])arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName206.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes206, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName207.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes207, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName208.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes208, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName209.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes209, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName210.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes210, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(((Long)arguments[0]).longValue());
		}

		if (_methodName211.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes211, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName212.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes212, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName213.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes213, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName214.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes214, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getUserLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName215.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes215, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.renewLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Date)arguments[2],
				(java.util.Date)arguments[3]);
		}

		if (_methodName216.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes216, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.renewLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Date)arguments[2],
				((Integer)arguments[3]).intValue());
		}

		if (_methodName217.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes217, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.renewTrialLicenseKey(((Long)arguments[0]).longValue());
		}

		if (_methodName218.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes218, parameterTypes)) {
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

		if (_methodName219.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes219, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName220.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes220, parameterTypes)) {
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

		if (_methodName221.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes221, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName222.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes222, parameterTypes)) {
			LicenseKeyLocalServiceUtil.sendRegisteredEmail((com.liferay.portal.kernel.model.User)arguments[0],
				(com.liferay.osb.model.LicenseKey)arguments[1]);

			return null;
		}

		if (_methodName223.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes223, parameterTypes)) {
			LicenseKeyLocalServiceUtil.sendTrialRenewalNotificationEmail((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName224.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes224, parameterTypes)) {
			LicenseKeyLocalServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue());

			return null;
		}

		if (_methodName225.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes225, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue());
		}

		if (_methodName226.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes226, parameterTypes)) {
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
	private String _methodName182;
	private String[] _methodParameterTypes182;
	private String _methodName187;
	private String[] _methodParameterTypes187;
	private String _methodName188;
	private String[] _methodParameterTypes188;
	private String _methodName189;
	private String[] _methodParameterTypes189;
	private String _methodName190;
	private String[] _methodParameterTypes190;
	private String _methodName191;
	private String[] _methodParameterTypes191;
	private String _methodName192;
	private String[] _methodParameterTypes192;
	private String _methodName193;
	private String[] _methodParameterTypes193;
	private String _methodName194;
	private String[] _methodParameterTypes194;
	private String _methodName195;
	private String[] _methodParameterTypes195;
	private String _methodName196;
	private String[] _methodParameterTypes196;
	private String _methodName197;
	private String[] _methodParameterTypes197;
	private String _methodName198;
	private String[] _methodParameterTypes198;
	private String _methodName199;
	private String[] _methodParameterTypes199;
	private String _methodName200;
	private String[] _methodParameterTypes200;
	private String _methodName201;
	private String[] _methodParameterTypes201;
	private String _methodName202;
	private String[] _methodParameterTypes202;
	private String _methodName203;
	private String[] _methodParameterTypes203;
	private String _methodName204;
	private String[] _methodParameterTypes204;
	private String _methodName205;
	private String[] _methodParameterTypes205;
	private String _methodName206;
	private String[] _methodParameterTypes206;
	private String _methodName207;
	private String[] _methodParameterTypes207;
	private String _methodName208;
	private String[] _methodParameterTypes208;
	private String _methodName209;
	private String[] _methodParameterTypes209;
	private String _methodName210;
	private String[] _methodParameterTypes210;
	private String _methodName211;
	private String[] _methodParameterTypes211;
	private String _methodName212;
	private String[] _methodParameterTypes212;
	private String _methodName213;
	private String[] _methodParameterTypes213;
	private String _methodName214;
	private String[] _methodParameterTypes214;
	private String _methodName215;
	private String[] _methodParameterTypes215;
	private String _methodName216;
	private String[] _methodParameterTypes216;
	private String _methodName217;
	private String[] _methodParameterTypes217;
	private String _methodName218;
	private String[] _methodParameterTypes218;
	private String _methodName219;
	private String[] _methodParameterTypes219;
	private String _methodName220;
	private String[] _methodParameterTypes220;
	private String _methodName221;
	private String[] _methodParameterTypes221;
	private String _methodName222;
	private String[] _methodParameterTypes222;
	private String _methodName223;
	private String[] _methodParameterTypes223;
	private String _methodName224;
	private String[] _methodParameterTypes224;
	private String _methodName225;
	private String[] _methodParameterTypes225;
	private String _methodName226;
	private String[] _methodParameterTypes226;
}