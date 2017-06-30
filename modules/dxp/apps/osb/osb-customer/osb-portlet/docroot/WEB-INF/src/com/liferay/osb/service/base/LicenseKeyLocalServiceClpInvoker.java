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

import com.liferay.osb.service.LicenseKeyLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
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

		_methodName9 = "fetchLicenseKey";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getLicenseKey";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getLicenseKeies";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getLicenseKeiesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateLicenseKey";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.LicenseKey"
			};

		_methodName15 = "updateLicenseKey";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.LicenseKey", "boolean"
			};

		_methodName708 = "getBeanIdentifier";

		_methodParameterTypes708 = new String[] {  };

		_methodName709 = "setBeanIdentifier";

		_methodParameterTypes709 = new String[] { "java.lang.String" };

		_methodName714 = "addLicenseKey";

		_methodParameterTypes714 = new String[] {
				"long", "com.liferay.osb.model.AssetReceiptLicense",
				"java.lang.String", "java.lang.String", "java.lang.String[][]",
				"java.lang.String[][]", "java.lang.String[][]",
				"java.lang.String[][]", "int", "int", "int"
			};

		_methodName715 = "addLicenseKey";

		_methodParameterTypes715 = new String[] {
				"long", "com.liferay.osb.model.LicenseKeySet",
				"java.lang.String", "com.liferay.osb.model.OfferingEntry",
				"com.liferay.osb.model.LicenseEntry",
				"com.liferay.osb.model.ProductEntry", "int", "long",
				"java.lang.String", "int", "int", "java.lang.String",
				"java.lang.String[][]", "java.lang.String[][]",
				"java.lang.String[][]", "java.lang.String[][]", "int", "int",
				"int", "java.lang.String", "boolean", "boolean"
			};

		_methodName716 = "addLicenseKey";

		_methodParameterTypes716 = new String[] {
				"long", "long", "java.lang.String", "long", "long", "long",
				"int", "long", "java.lang.String", "int", "int",
				"java.lang.String", "java.lang.String[][]",
				"java.lang.String[][]", "java.lang.String[][]",
				"java.lang.String[][]", "int", "int", "int", "boolean",
				"boolean"
			};

		_methodName717 = "addSingleUseLicenseKey";

		_methodParameterTypes717 = new String[] {
				"java.lang.String", "int", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};

		_methodName718 = "buyLicenseKey";

		_methodParameterTypes718 = new String[] { "long", "long" };

		_methodName719 = "getAccountEntryLicenseKeys";

		_methodParameterTypes719 = new String[] { "long" };

		_methodName720 = "getAssetReceiptLicenseLicenseKeys";

		_methodParameterTypes720 = new String[] { "long", "boolean" };

		_methodName721 = "getAssetReceiptLicenseLicenseKeys";

		_methodParameterTypes721 = new String[] { "long", "boolean", "boolean" };

		_methodName722 = "getAssetReceiptLicenseLicenseKeysCount";

		_methodParameterTypes722 = new String[] { "long", "boolean", "boolean" };

		_methodName723 = "getFirstLicenseKey";

		_methodParameterTypes723 = new String[] {
				"long", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName724 = "getLicenseKeys";

		_methodParameterTypes724 = new String[] { "long", "long" };

		_methodName725 = "getLicenseKeys";

		_methodParameterTypes725 = new String[] { "long", "java.lang.String" };

		_methodName726 = "getLicenseKeys";

		_methodParameterTypes726 = new String[] {
				"long", "java.lang.String", "java.lang.String", "boolean", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName727 = "getLicenseKeys";

		_methodParameterTypes727 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName728 = "getLicenseKeysByName";

		_methodParameterTypes728 = new String[] {
				"java.lang.String", "java.lang.String", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName729 = "getLicenseKeySetLicenseKeys";

		_methodParameterTypes729 = new String[] { "long" };

		_methodName730 = "getOfferingEntryGroupLicenseKeys";

		_methodParameterTypes730 = new String[] {
				"long[][]", "boolean", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName731 = "getOfferingEntryGroupLicenseKeysCount";

		_methodParameterTypes731 = new String[] { "long[][]", "boolean", "boolean" };

		_methodName732 = "getOfferingEntryLicenseKeys";

		_methodParameterTypes732 = new String[] { "long" };

		_methodName733 = "getOfferingEntryLicenseKeys";

		_methodParameterTypes733 = new String[] { "long", "boolean", "boolean" };

		_methodName734 = "getOfferingEntryLicenseKeys";

		_methodParameterTypes734 = new String[] { "long", "long" };

		_methodName735 = "getOfferingEntryLicenseKeys";

		_methodParameterTypes735 = new String[] { "long", "long", "boolean" };

		_methodName736 = "getOfferingEntryLicenseKeysCount";

		_methodParameterTypes736 = new String[] { "long" };

		_methodName737 = "getOfferingEntryLicenseKeysCount";

		_methodParameterTypes737 = new String[] { "long", "boolean", "boolean" };

		_methodName738 = "getOfferingEntryLicenseKeysCount";

		_methodParameterTypes738 = new String[] { "long", "long" };

		_methodName739 = "getOfferingEntryLicenseKeysCount";

		_methodParameterTypes739 = new String[] { "long", "long", "boolean" };

		_methodName740 = "getUserLicenseKeysCount";

		_methodParameterTypes740 = new String[] { "long", "long" };

		_methodName741 = "renewLicenseKey";

		_methodParameterTypes741 = new String[] { "long", "long" };

		_methodName742 = "renewLicenseKey";

		_methodParameterTypes742 = new String[] {
				"long", "long", "java.util.Date", "int"
			};

		_methodName743 = "renewTrialLicenseKey";

		_methodParameterTypes743 = new String[] { "long" };

		_methodName744 = "search";

		_methodParameterTypes744 = new String[] {
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

		_methodName745 = "search";

		_methodParameterTypes745 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName746 = "searchCount";

		_methodParameterTypes746 = new String[] {
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

		_methodName747 = "searchCount";

		_methodParameterTypes747 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName748 = "sendRegisteredEmail";

		_methodParameterTypes748 = new String[] {
				"com.liferay.portal.model.User",
				"com.liferay.osb.model.LicenseKey"
			};

		_methodName749 = "sendTrialRenewalNotificationEmail";

		_methodParameterTypes749 = new String[] { "java.lang.String", "long" };

		_methodName750 = "updateLicenseKey";

		_methodParameterTypes750 = new String[] {
				"long", "long", "long", "boolean"
			};

		_methodName751 = "updateLicenseKey";

		_methodParameterTypes751 = new String[] { "long", "long", "long", "long" };

		_methodName752 = "updateLicenseKey";

		_methodParameterTypes752 = new String[] {
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
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.fetchLicenseKey(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKey(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeies(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeiesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.updateLicenseKey((com.liferay.osb.model.LicenseKey)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.updateLicenseKey((com.liferay.osb.model.LicenseKey)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			LicenseKeyLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName714.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes714, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.addLicenseKey(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.AssetReceiptLicense)arguments[1],
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String[])arguments[4],
				(java.lang.String[])arguments[5],
				(java.lang.String[])arguments[6],
				(java.lang.String[])arguments[7],
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue());
		}

		if (_methodName715.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes715, parameterTypes)) {
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
				((Integer)arguments[16]).intValue(),
				((Integer)arguments[17]).intValue(),
				((Integer)arguments[18]).intValue(),
				(java.lang.String)arguments[19],
				((Boolean)arguments[20]).booleanValue(),
				((Boolean)arguments[21]).booleanValue());
		}

		if (_methodName716.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes716, parameterTypes)) {
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
				((Integer)arguments[16]).intValue(),
				((Integer)arguments[17]).intValue(),
				((Integer)arguments[18]).intValue(),
				((Boolean)arguments[19]).booleanValue(),
				((Boolean)arguments[20]).booleanValue());
		}

		if (_methodName717.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes717, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.addSingleUseLicenseKey((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4]);
		}

		if (_methodName718.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes718, parameterTypes)) {
			LicenseKeyLocalServiceUtil.buyLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName719.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes719, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getAccountEntryLicenseKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName720.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes720, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeys(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName721.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes721, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeys(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName722.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes722, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName723.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes723, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getFirstLicenseKey(((Long)arguments[0]).longValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[1]);
		}

		if (_methodName724.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes724, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeys(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName725.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes725, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeys(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName726.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes726, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeys(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[6]);
		}

		if (_methodName727.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes727, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeys((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName728.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes728, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeysByName((java.lang.String)arguments[0],
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName729.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes729, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getLicenseKeySetLicenseKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName730.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes730, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryGroupLicenseKeys((long[])arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName731.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes731, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryGroupLicenseKeysCount((long[])arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName732.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes732, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName733.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes733, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName734.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes734, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName735.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes735, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName736.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes736, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(((Long)arguments[0]).longValue());
		}

		if (_methodName737.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes737, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName738.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes738, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName739.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes739, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName740.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes740, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.getUserLicenseKeysCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName741.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes741, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.renewLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName742.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes742, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.renewLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Date)arguments[2],
				((Integer)arguments[3]).intValue());
		}

		if (_methodName743.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes743, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.renewTrialLicenseKey(((Long)arguments[0]).longValue());
		}

		if (_methodName744.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes744, parameterTypes)) {
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

		if (_methodName745.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes745, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName746.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes746, parameterTypes)) {
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

		if (_methodName747.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes747, parameterTypes)) {
			return LicenseKeyLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName748.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes748, parameterTypes)) {
			LicenseKeyLocalServiceUtil.sendRegisteredEmail((com.liferay.portal.model.User)arguments[0],
				(com.liferay.osb.model.LicenseKey)arguments[1]);

			return null;
		}

		if (_methodName749.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes749, parameterTypes)) {
			LicenseKeyLocalServiceUtil.sendTrialRenewalNotificationEmail((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName750.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes750, parameterTypes)) {
			LicenseKeyLocalServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Boolean)arguments[3]).booleanValue());

			return null;
		}

		if (_methodName751.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes751, parameterTypes)) {
			LicenseKeyLocalServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue());

			return null;
		}

		if (_methodName752.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes752, parameterTypes)) {
			LicenseKeyLocalServiceUtil.updateLicenseKey(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4],
				((Boolean)arguments[5]).booleanValue());

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
	private String _methodName708;
	private String[] _methodParameterTypes708;
	private String _methodName709;
	private String[] _methodParameterTypes709;
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
	private String _methodName732;
	private String[] _methodParameterTypes732;
	private String _methodName733;
	private String[] _methodParameterTypes733;
	private String _methodName734;
	private String[] _methodParameterTypes734;
	private String _methodName735;
	private String[] _methodParameterTypes735;
	private String _methodName736;
	private String[] _methodParameterTypes736;
	private String _methodName737;
	private String[] _methodParameterTypes737;
	private String _methodName738;
	private String[] _methodParameterTypes738;
	private String _methodName739;
	private String[] _methodParameterTypes739;
	private String _methodName740;
	private String[] _methodParameterTypes740;
	private String _methodName741;
	private String[] _methodParameterTypes741;
	private String _methodName742;
	private String[] _methodParameterTypes742;
	private String _methodName743;
	private String[] _methodParameterTypes743;
	private String _methodName744;
	private String[] _methodParameterTypes744;
	private String _methodName745;
	private String[] _methodParameterTypes745;
	private String _methodName746;
	private String[] _methodParameterTypes746;
	private String _methodName747;
	private String[] _methodParameterTypes747;
	private String _methodName748;
	private String[] _methodParameterTypes748;
	private String _methodName749;
	private String[] _methodParameterTypes749;
	private String _methodName750;
	private String[] _methodParameterTypes750;
	private String _methodName751;
	private String[] _methodParameterTypes751;
	private String _methodName752;
	private String[] _methodParameterTypes752;
}