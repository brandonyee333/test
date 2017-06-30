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

import com.liferay.osb.service.PartnerEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class PartnerEntryLocalServiceClpInvoker {
	public PartnerEntryLocalServiceClpInvoker() {
		_methodName0 = "addPartnerEntry";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.PartnerEntry"
			};

		_methodName1 = "createPartnerEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deletePartnerEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deletePartnerEntry";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.PartnerEntry"
			};

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

		_methodName9 = "fetchPartnerEntry";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getPartnerEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getPartnerEntries";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getPartnerEntriesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updatePartnerEntry";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.PartnerEntry"
			};

		_methodName15 = "updatePartnerEntry";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.PartnerEntry", "boolean"
			};

		_methodName16 = "addSupportRegionPartnerEntry";

		_methodParameterTypes16 = new String[] { "long", "long" };

		_methodName17 = "addSupportRegionPartnerEntry";

		_methodParameterTypes17 = new String[] {
				"long", "com.liferay.osb.model.PartnerEntry"
			};

		_methodName18 = "addSupportRegionPartnerEntries";

		_methodParameterTypes18 = new String[] { "long", "long[][]" };

		_methodName19 = "addSupportRegionPartnerEntries";

		_methodParameterTypes19 = new String[] { "long", "java.util.List" };

		_methodName20 = "clearSupportRegionPartnerEntries";

		_methodParameterTypes20 = new String[] { "long" };

		_methodName21 = "deleteSupportRegionPartnerEntry";

		_methodParameterTypes21 = new String[] { "long", "long" };

		_methodName22 = "deleteSupportRegionPartnerEntry";

		_methodParameterTypes22 = new String[] {
				"long", "com.liferay.osb.model.PartnerEntry"
			};

		_methodName23 = "deleteSupportRegionPartnerEntries";

		_methodParameterTypes23 = new String[] { "long", "long[][]" };

		_methodName24 = "deleteSupportRegionPartnerEntries";

		_methodParameterTypes24 = new String[] { "long", "java.util.List" };

		_methodName25 = "getSupportRegionPartnerEntries";

		_methodParameterTypes25 = new String[] { "long" };

		_methodName26 = "getSupportRegionPartnerEntries";

		_methodParameterTypes26 = new String[] { "long", "int", "int" };

		_methodName27 = "getSupportRegionPartnerEntries";

		_methodParameterTypes27 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName28 = "getSupportRegionPartnerEntriesCount";

		_methodParameterTypes28 = new String[] { "long" };

		_methodName29 = "hasSupportRegionPartnerEntry";

		_methodParameterTypes29 = new String[] { "long", "long" };

		_methodName30 = "hasSupportRegionPartnerEntries";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "setSupportRegionPartnerEntries";

		_methodParameterTypes31 = new String[] { "long", "long[][]" };

		_methodName708 = "getBeanIdentifier";

		_methodParameterTypes708 = new String[] {  };

		_methodName709 = "setBeanIdentifier";

		_methodParameterTypes709 = new String[] { "java.lang.String" };

		_methodName714 = "addPartnerEntry";

		_methodParameterTypes714 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "long[][]"
			};

		_methodName715 = "deletePartnerEntry";

		_methodParameterTypes715 = new String[] { "long" };

		_methodName716 = "fetchPartnerEntry";

		_methodParameterTypes716 = new String[] { "java.lang.String" };

		_methodName717 = "getChildPartnerEntries";

		_methodParameterTypes717 = new String[] { "long", "boolean" };

		_methodName718 = "getPartnerEntry";

		_methodParameterTypes718 = new String[] { "long" };

		_methodName719 = "getPartnerEntryByCode";

		_methodParameterTypes719 = new String[] { "java.lang.String" };

		_methodName720 = "getUserPartnerEntries";

		_methodParameterTypes720 = new String[] { "long", "int", "int" };

		_methodName721 = "search";

		_methodParameterTypes721 = new String[] {
				"java.lang.String", "int[][]", "java.util.LinkedHashMap",
				"boolean", "int", "int"
			};

		_methodName722 = "search";

		_methodParameterTypes722 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int"
			};

		_methodName723 = "searchCount";

		_methodParameterTypes723 = new String[] {
				"java.lang.String", "int[][]", "java.util.LinkedHashMap",
				"boolean"
			};

		_methodName724 = "searchCount";

		_methodParameterTypes724 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName725 = "updatePartnerEntry";

		_methodParameterTypes725 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "int", "long[][]"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.addPartnerEntry((com.liferay.osb.model.PartnerEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.createPartnerEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.deletePartnerEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.deletePartnerEntry((com.liferay.osb.model.PartnerEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.fetchPartnerEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.getPartnerEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.getPartnerEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.getPartnerEntriesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.updatePartnerEntry((com.liferay.osb.model.PartnerEntry)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.updatePartnerEntry((com.liferay.osb.model.PartnerEntry)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			PartnerEntryLocalServiceUtil.addSupportRegionPartnerEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			PartnerEntryLocalServiceUtil.addSupportRegionPartnerEntry(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.PartnerEntry)arguments[1]);

			return null;
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			PartnerEntryLocalServiceUtil.addSupportRegionPartnerEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			PartnerEntryLocalServiceUtil.addSupportRegionPartnerEntries(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.PartnerEntry>)arguments[1]);

			return null;
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			PartnerEntryLocalServiceUtil.clearSupportRegionPartnerEntries(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			PartnerEntryLocalServiceUtil.deleteSupportRegionPartnerEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			PartnerEntryLocalServiceUtil.deleteSupportRegionPartnerEntry(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.PartnerEntry)arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			PartnerEntryLocalServiceUtil.deleteSupportRegionPartnerEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			PartnerEntryLocalServiceUtil.deleteSupportRegionPartnerEntries(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.PartnerEntry>)arguments[1]);

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.getSupportRegionPartnerEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.getSupportRegionPartnerEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.getSupportRegionPartnerEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.getSupportRegionPartnerEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.hasSupportRegionPartnerEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.hasSupportRegionPartnerEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			PartnerEntryLocalServiceUtil.setSupportRegionPartnerEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			PartnerEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName714.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes714, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.addPartnerEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (long[])arguments[5]);
		}

		if (_methodName715.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes715, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.deletePartnerEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName716.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes716, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.fetchPartnerEntry((java.lang.String)arguments[0]);
		}

		if (_methodName717.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes717, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.getChildPartnerEntries(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName718.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes718, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.getPartnerEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName719.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes719, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.getPartnerEntryByCode((java.lang.String)arguments[0]);
		}

		if (_methodName720.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes720, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.getUserPartnerEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName721.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes721, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.search((java.lang.String)arguments[0],
				(int[])arguments[1],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue());
		}

		if (_methodName722.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes722, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName723.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes723, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(int[])arguments[1],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[2],
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName724.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes724, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName725.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes725, parameterTypes)) {
			return PartnerEntryLocalServiceUtil.updatePartnerEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue(), (long[])arguments[6]);
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
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName18;
	private String[] _methodParameterTypes18;
	private String _methodName19;
	private String[] _methodParameterTypes19;
	private String _methodName20;
	private String[] _methodParameterTypes20;
	private String _methodName21;
	private String[] _methodParameterTypes21;
	private String _methodName22;
	private String[] _methodParameterTypes22;
	private String _methodName23;
	private String[] _methodParameterTypes23;
	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName25;
	private String[] _methodParameterTypes25;
	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName28;
	private String[] _methodParameterTypes28;
	private String _methodName29;
	private String[] _methodParameterTypes29;
	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
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
}