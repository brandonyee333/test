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

import com.liferay.osb.service.AppEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class AppEntryLocalServiceClpInvoker {
	public AppEntryLocalServiceClpInvoker() {
		_methodName0 = "addAppEntry";

		_methodParameterTypes0 = new String[] { "com.liferay.osb.model.AppEntry" };

		_methodName1 = "createAppEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAppEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAppEntry";

		_methodParameterTypes3 = new String[] { "com.liferay.osb.model.AppEntry" };

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

		_methodName9 = "fetchAppEntry";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getAppEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getAppEntries";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getAppEntriesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateAppEntry";

		_methodParameterTypes14 = new String[] { "com.liferay.osb.model.AppEntry" };

		_methodName15 = "updateAppEntry";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.AppEntry", "boolean"
			};

		_methodName732 = "getBeanIdentifier";

		_methodParameterTypes732 = new String[] {  };

		_methodName733 = "setBeanIdentifier";

		_methodParameterTypes733 = new String[] { "java.lang.String" };

		_methodName738 = "addAppEntry";

		_methodParameterTypes738 = new String[] {
				"long", "long", "java.lang.String", "java.util.Map",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean", "int", "java.lang.String", "java.util.Map",
				"java.io.File", "boolean", "java.util.Map", "int",
				"java.lang.String", "boolean", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName739 = "addEntryResources";

		_methodParameterTypes739 = new String[] {
				"com.liferay.osb.model.AppEntry", "boolean", "boolean"
			};

		_methodName740 = "addEntryResources";

		_methodParameterTypes740 = new String[] {
				"com.liferay.osb.model.AppEntry", "java.lang.String[][]",
				"java.lang.String[][]"
			};

		_methodName741 = "buildLiferayPackage";

		_methodParameterTypes741 = new String[] {
				"com.liferay.osb.model.AppPackage", "boolean"
			};

		_methodName742 = "buildLiferayPackages";

		_methodParameterTypes742 = new String[] {
				"com.liferay.osb.model.AppVersion"
			};

		_methodName743 = "deleteAppEntry";

		_methodParameterTypes743 = new String[] { "com.liferay.osb.model.AppEntry" };

		_methodName744 = "deleteAppEntry";

		_methodParameterTypes744 = new String[] { "long" };

		_methodName745 = "expireAppEntries";

		_methodParameterTypes745 = new String[] { "long" };

		_methodName746 = "fetchAppEntry";

		_methodParameterTypes746 = new String[] { "long" };

		_methodName747 = "getAppEntries";

		_methodParameterTypes747 = new String[] { "int", "int", "int" };

		_methodName748 = "getAppEntries";

		_methodParameterTypes748 = new String[] {
				"long", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName749 = "getAppEntries";

		_methodParameterTypes749 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName750 = "getAppEntriesCount";

		_methodParameterTypes750 = new String[] { "int" };

		_methodName751 = "getAppEntriesCount";

		_methodParameterTypes751 = new String[] { "long", "int" };

		_methodName752 = "getAppEntriesCount";

		_methodParameterTypes752 = new String[] { "long", "java.lang.String" };

		_methodName753 = "getAppEntry";

		_methodParameterTypes753 = new String[] { "java.lang.String" };

		_methodName754 = "getPrepackagedAppEntries";

		_methodParameterTypes754 = new String[] { "int" };

		_methodName755 = "getPrepackagedAppEntriesCount";

		_methodParameterTypes755 = new String[] { "int" };

		_methodName756 = "incrementDownloadCount";

		_methodParameterTypes756 = new String[] { "long", "long", "int" };

		_methodName757 = "search";

		_methodParameterTypes757 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName758 = "searchCount";

		_methodParameterTypes758 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "boolean"
			};

		_methodName759 = "updateAppEntry";

		_methodParameterTypes759 = new String[] {
				"long", "long", "long", "boolean", "long[][]", "boolean",
				"long[][]", "boolean", "boolean"
			};

		_methodName760 = "updateAppEntry";

		_methodParameterTypes760 = new String[] {
				"long", "long", "java.lang.String", "java.util.Map",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean", "int", "java.lang.String", "int", "java.util.Map",
				"java.io.File", "boolean", "int", "java.util.Map", "int",
				"java.lang.String", "boolean", "boolean", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName761 = "updateAppEntry";

		_methodParameterTypes761 = new String[] {
				"long", "java.lang.String", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName762 = "updateAsset";

		_methodParameterTypes762 = new String[] {
				"long", "com.liferay.osb.model.AppEntry",
				"com.liferay.osb.model.AppVersion", "long[][]",
				"java.lang.String[][]"
			};

		_methodName763 = "updateStatus";

		_methodParameterTypes763 = new String[] {
				"long", "long", "int", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName764 = "validatePurchase";

		_methodParameterTypes764 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AppEntryLocalServiceUtil.addAppEntry((com.liferay.osb.model.AppEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AppEntryLocalServiceUtil.createAppEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AppEntryLocalServiceUtil.deleteAppEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AppEntryLocalServiceUtil.deleteAppEntry((com.liferay.osb.model.AppEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AppEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AppEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AppEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AppEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AppEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AppEntryLocalServiceUtil.fetchAppEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AppEntryLocalServiceUtil.getAppEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AppEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AppEntryLocalServiceUtil.getAppEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AppEntryLocalServiceUtil.getAppEntriesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return AppEntryLocalServiceUtil.updateAppEntry((com.liferay.osb.model.AppEntry)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AppEntryLocalServiceUtil.updateAppEntry((com.liferay.osb.model.AppEntry)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName732.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes732, parameterTypes)) {
			return AppEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName733.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes733, parameterTypes)) {
			AppEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName738.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes738, parameterTypes)) {
			return AppEntryLocalServiceUtil.addAppEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				(java.lang.String)arguments[8], (java.lang.String)arguments[9],
				((Boolean)arguments[10]).booleanValue(),
				((Integer)arguments[11]).intValue(),
				(java.lang.String)arguments[12],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[13],
				(java.io.File)arguments[14],
				((Boolean)arguments[15]).booleanValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[16],
				((Integer)arguments[17]).intValue(),
				(java.lang.String)arguments[18],
				((Boolean)arguments[19]).booleanValue(),
				((Boolean)arguments[20]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[21]);
		}

		if (_methodName739.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes739, parameterTypes)) {
			AppEntryLocalServiceUtil.addEntryResources((com.liferay.osb.model.AppEntry)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue());

			return null;
		}

		if (_methodName740.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes740, parameterTypes)) {
			AppEntryLocalServiceUtil.addEntryResources((com.liferay.osb.model.AppEntry)arguments[0],
				(java.lang.String[])arguments[1],
				(java.lang.String[])arguments[2]);

			return null;
		}

		if (_methodName741.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes741, parameterTypes)) {
			return AppEntryLocalServiceUtil.buildLiferayPackage((com.liferay.osb.model.AppPackage)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName742.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes742, parameterTypes)) {
			AppEntryLocalServiceUtil.buildLiferayPackages((com.liferay.osb.model.AppVersion)arguments[0]);

			return null;
		}

		if (_methodName743.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes743, parameterTypes)) {
			return AppEntryLocalServiceUtil.deleteAppEntry((com.liferay.osb.model.AppEntry)arguments[0]);
		}

		if (_methodName744.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes744, parameterTypes)) {
			return AppEntryLocalServiceUtil.deleteAppEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName745.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes745, parameterTypes)) {
			AppEntryLocalServiceUtil.expireAppEntries(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName746.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes746, parameterTypes)) {
			return AppEntryLocalServiceUtil.fetchAppEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName747.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes747, parameterTypes)) {
			return AppEntryLocalServiceUtil.getAppEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName748.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes748, parameterTypes)) {
			return AppEntryLocalServiceUtil.getAppEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName749.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes749, parameterTypes)) {
			return AppEntryLocalServiceUtil.getAppEntries(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName750.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes750, parameterTypes)) {
			return AppEntryLocalServiceUtil.getAppEntriesCount(((Integer)arguments[0]).intValue());
		}

		if (_methodName751.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes751, parameterTypes)) {
			return AppEntryLocalServiceUtil.getAppEntriesCount(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName752.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes752, parameterTypes)) {
			return AppEntryLocalServiceUtil.getAppEntriesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName753.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes753, parameterTypes)) {
			return AppEntryLocalServiceUtil.getAppEntry((java.lang.String)arguments[0]);
		}

		if (_methodName754.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes754, parameterTypes)) {
			return AppEntryLocalServiceUtil.getPrepackagedAppEntries(((Integer)arguments[0]).intValue());
		}

		if (_methodName755.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes755, parameterTypes)) {
			return AppEntryLocalServiceUtil.getPrepackagedAppEntriesCount(((Integer)arguments[0]).intValue());
		}

		if (_methodName756.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes756, parameterTypes)) {
			return AppEntryLocalServiceUtil.incrementDownloadCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName757.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes757, parameterTypes)) {
			return AppEntryLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName758.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes758, parameterTypes)) {
			return AppEntryLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName759.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes759, parameterTypes)) {
			return AppEntryLocalServiceUtil.updateAppEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Boolean)arguments[3]).booleanValue(), (long[])arguments[4],
				((Boolean)arguments[5]).booleanValue(), (long[])arguments[6],
				((Boolean)arguments[7]).booleanValue(),
				((Boolean)arguments[8]).booleanValue());
		}

		if (_methodName760.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes760, parameterTypes)) {
			return AppEntryLocalServiceUtil.updateAppEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				(java.lang.String)arguments[8], (java.lang.String)arguments[9],
				((Boolean)arguments[10]).booleanValue(),
				((Integer)arguments[11]).intValue(),
				(java.lang.String)arguments[12],
				((Integer)arguments[13]).intValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[14],
				(java.io.File)arguments[15],
				((Boolean)arguments[16]).booleanValue(),
				((Integer)arguments[17]).intValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[18],
				((Integer)arguments[19]).intValue(),
				(java.lang.String)arguments[20],
				((Boolean)arguments[21]).booleanValue(),
				((Boolean)arguments[22]).booleanValue(),
				((Integer)arguments[23]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[24]);
		}

		if (_methodName761.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes761, parameterTypes)) {
			return AppEntryLocalServiceUtil.updateAppEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName762.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes762, parameterTypes)) {
			AppEntryLocalServiceUtil.updateAsset(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.AppEntry)arguments[1],
				(com.liferay.osb.model.AppVersion)arguments[2],
				(long[])arguments[3], (java.lang.String[])arguments[4]);

			return null;
		}

		if (_methodName763.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes763, parameterTypes)) {
			return AppEntryLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				(java.lang.String)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName764.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes764, parameterTypes)) {
			AppEntryLocalServiceUtil.validatePurchase(((Long)arguments[0]).longValue());

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
	private String _methodName732;
	private String[] _methodParameterTypes732;
	private String _methodName733;
	private String[] _methodParameterTypes733;
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
	private String _methodName753;
	private String[] _methodParameterTypes753;
	private String _methodName754;
	private String[] _methodParameterTypes754;
	private String _methodName755;
	private String[] _methodParameterTypes755;
	private String _methodName756;
	private String[] _methodParameterTypes756;
	private String _methodName757;
	private String[] _methodParameterTypes757;
	private String _methodName758;
	private String[] _methodParameterTypes758;
	private String _methodName759;
	private String[] _methodParameterTypes759;
	private String _methodName760;
	private String[] _methodParameterTypes760;
	private String _methodName761;
	private String[] _methodParameterTypes761;
	private String _methodName762;
	private String[] _methodParameterTypes762;
	private String _methodName763;
	private String[] _methodParameterTypes763;
	private String _methodName764;
	private String[] _methodParameterTypes764;
}