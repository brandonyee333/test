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

import com.liferay.osb.service.CorpGroupLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class CorpGroupLocalServiceClpInvoker {
	public CorpGroupLocalServiceClpInvoker() {
		_methodName0 = "addCorpGroup";

		_methodParameterTypes0 = new String[] { "com.liferay.osb.model.CorpGroup" };

		_methodName1 = "createCorpGroup";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteCorpGroup";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteCorpGroup";

		_methodParameterTypes3 = new String[] { "com.liferay.osb.model.CorpGroup" };

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

		_methodName9 = "fetchCorpGroup";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getCorpGroup";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getCorpGroups";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getCorpGroupsCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateCorpGroup";

		_methodParameterTypes14 = new String[] { "com.liferay.osb.model.CorpGroup" };

		_methodName15 = "updateCorpGroup";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.CorpGroup", "boolean"
			};

		_methodName16 = "addCorpEntryCorpGroup";

		_methodParameterTypes16 = new String[] { "long", "long" };

		_methodName17 = "addCorpEntryCorpGroup";

		_methodParameterTypes17 = new String[] {
				"long", "com.liferay.osb.model.CorpGroup"
			};

		_methodName18 = "addCorpEntryCorpGroups";

		_methodParameterTypes18 = new String[] { "long", "long[][]" };

		_methodName19 = "addCorpEntryCorpGroups";

		_methodParameterTypes19 = new String[] { "long", "java.util.List" };

		_methodName20 = "clearCorpEntryCorpGroups";

		_methodParameterTypes20 = new String[] { "long" };

		_methodName21 = "deleteCorpEntryCorpGroup";

		_methodParameterTypes21 = new String[] { "long", "long" };

		_methodName22 = "deleteCorpEntryCorpGroup";

		_methodParameterTypes22 = new String[] {
				"long", "com.liferay.osb.model.CorpGroup"
			};

		_methodName23 = "deleteCorpEntryCorpGroups";

		_methodParameterTypes23 = new String[] { "long", "long[][]" };

		_methodName24 = "deleteCorpEntryCorpGroups";

		_methodParameterTypes24 = new String[] { "long", "java.util.List" };

		_methodName25 = "getCorpEntryCorpGroups";

		_methodParameterTypes25 = new String[] { "long" };

		_methodName26 = "getCorpEntryCorpGroups";

		_methodParameterTypes26 = new String[] { "long", "int", "int" };

		_methodName27 = "getCorpEntryCorpGroups";

		_methodParameterTypes27 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName28 = "getCorpEntryCorpGroupsCount";

		_methodParameterTypes28 = new String[] { "long" };

		_methodName29 = "hasCorpEntryCorpGroup";

		_methodParameterTypes29 = new String[] { "long", "long" };

		_methodName30 = "hasCorpEntryCorpGroups";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "setCorpEntryCorpGroups";

		_methodParameterTypes31 = new String[] { "long", "long[][]" };

		_methodName714 = "getBeanIdentifier";

		_methodParameterTypes714 = new String[] {  };

		_methodName715 = "setBeanIdentifier";

		_methodParameterTypes715 = new String[] { "java.lang.String" };

		_methodName720 = "addCorpGroup";

		_methodParameterTypes720 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.io.File",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName721 = "fetchOrganizationCorpGroup";

		_methodParameterTypes721 = new String[] { "long" };

		_methodName722 = "getCorpEntryCorpGroups";

		_methodParameterTypes722 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName723 = "getOrganizationCorpGroup";

		_methodParameterTypes723 = new String[] { "long" };

		_methodName724 = "getUserCorpGroups";

		_methodParameterTypes724 = new String[] {
				"long", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName725 = "search";

		_methodParameterTypes725 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName726 = "search";

		_methodParameterTypes726 = new String[] {
				"java.lang.String", "long[][]", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName727 = "searchCount";

		_methodParameterTypes727 = new String[] { "java.lang.String" };

		_methodName728 = "searchCount";

		_methodParameterTypes728 = new String[] { "java.lang.String", "long[][]" };

		_methodName729 = "updateCorpGroup";

		_methodParameterTypes729 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.io.File",
				"java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName730 = "updateSite";

		_methodParameterTypes730 = new String[] { "long", "boolean" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return CorpGroupLocalServiceUtil.addCorpGroup((com.liferay.osb.model.CorpGroup)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return CorpGroupLocalServiceUtil.createCorpGroup(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return CorpGroupLocalServiceUtil.deleteCorpGroup(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return CorpGroupLocalServiceUtil.deleteCorpGroup((com.liferay.osb.model.CorpGroup)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return CorpGroupLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return CorpGroupLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return CorpGroupLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return CorpGroupLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return CorpGroupLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return CorpGroupLocalServiceUtil.fetchCorpGroup(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return CorpGroupLocalServiceUtil.getCorpGroup(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return CorpGroupLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return CorpGroupLocalServiceUtil.getCorpGroups(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return CorpGroupLocalServiceUtil.getCorpGroupsCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return CorpGroupLocalServiceUtil.updateCorpGroup((com.liferay.osb.model.CorpGroup)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return CorpGroupLocalServiceUtil.updateCorpGroup((com.liferay.osb.model.CorpGroup)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			CorpGroupLocalServiceUtil.addCorpEntryCorpGroup(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			CorpGroupLocalServiceUtil.addCorpEntryCorpGroup(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.CorpGroup)arguments[1]);

			return null;
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			CorpGroupLocalServiceUtil.addCorpEntryCorpGroups(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			CorpGroupLocalServiceUtil.addCorpEntryCorpGroups(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.CorpGroup>)arguments[1]);

			return null;
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			CorpGroupLocalServiceUtil.clearCorpEntryCorpGroups(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			CorpGroupLocalServiceUtil.deleteCorpEntryCorpGroup(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			CorpGroupLocalServiceUtil.deleteCorpEntryCorpGroup(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.CorpGroup)arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			CorpGroupLocalServiceUtil.deleteCorpEntryCorpGroups(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			CorpGroupLocalServiceUtil.deleteCorpEntryCorpGroups(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.CorpGroup>)arguments[1]);

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return CorpGroupLocalServiceUtil.getCorpEntryCorpGroups(((Long)arguments[0]).longValue());
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return CorpGroupLocalServiceUtil.getCorpEntryCorpGroups(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			return CorpGroupLocalServiceUtil.getCorpEntryCorpGroups(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return CorpGroupLocalServiceUtil.getCorpEntryCorpGroupsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return CorpGroupLocalServiceUtil.hasCorpEntryCorpGroup(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return CorpGroupLocalServiceUtil.hasCorpEntryCorpGroups(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			CorpGroupLocalServiceUtil.setCorpEntryCorpGroups(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName714.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes714, parameterTypes)) {
			return CorpGroupLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName715.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes715, parameterTypes)) {
			CorpGroupLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName720.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes720, parameterTypes)) {
			return CorpGroupLocalServiceUtil.addCorpGroup(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.io.File)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName721.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes721, parameterTypes)) {
			return CorpGroupLocalServiceUtil.fetchOrganizationCorpGroup(((Long)arguments[0]).longValue());
		}

		if (_methodName722.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes722, parameterTypes)) {
			return CorpGroupLocalServiceUtil.getCorpEntryCorpGroups(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName723.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes723, parameterTypes)) {
			return CorpGroupLocalServiceUtil.getOrganizationCorpGroup(((Long)arguments[0]).longValue());
		}

		if (_methodName724.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes724, parameterTypes)) {
			return CorpGroupLocalServiceUtil.getUserCorpGroups(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName725.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes725, parameterTypes)) {
			return CorpGroupLocalServiceUtil.search((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName726.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes726, parameterTypes)) {
			return CorpGroupLocalServiceUtil.search((java.lang.String)arguments[0],
				(long[])arguments[1], ((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName727.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes727, parameterTypes)) {
			return CorpGroupLocalServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName728.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes728, parameterTypes)) {
			return CorpGroupLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(long[])arguments[1]);
		}

		if (_methodName729.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes729, parameterTypes)) {
			return CorpGroupLocalServiceUtil.updateCorpGroup(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.io.File)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName730.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes730, parameterTypes)) {
			CorpGroupLocalServiceUtil.updateSite(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());

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
	private String _methodName714;
	private String[] _methodParameterTypes714;
	private String _methodName715;
	private String[] _methodParameterTypes715;
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
}