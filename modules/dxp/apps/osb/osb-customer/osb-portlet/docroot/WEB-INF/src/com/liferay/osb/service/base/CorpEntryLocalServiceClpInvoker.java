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

import com.liferay.osb.service.CorpEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class CorpEntryLocalServiceClpInvoker {
	public CorpEntryLocalServiceClpInvoker() {
		_methodName0 = "addCorpEntry";

		_methodParameterTypes0 = new String[] { "com.liferay.osb.model.CorpEntry" };

		_methodName1 = "createCorpEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteCorpEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteCorpEntry";

		_methodParameterTypes3 = new String[] { "com.liferay.osb.model.CorpEntry" };

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

		_methodName9 = "fetchCorpEntry";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getCorpEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getCorpEntries";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getCorpEntriesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateCorpEntry";

		_methodParameterTypes14 = new String[] { "com.liferay.osb.model.CorpEntry" };

		_methodName15 = "updateCorpEntry";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.CorpEntry", "boolean"
			};

		_methodName16 = "addCorpGroupCorpEntry";

		_methodParameterTypes16 = new String[] { "long", "long" };

		_methodName17 = "addCorpGroupCorpEntry";

		_methodParameterTypes17 = new String[] {
				"long", "com.liferay.osb.model.CorpEntry"
			};

		_methodName18 = "addCorpGroupCorpEntries";

		_methodParameterTypes18 = new String[] { "long", "long[][]" };

		_methodName19 = "addCorpGroupCorpEntries";

		_methodParameterTypes19 = new String[] { "long", "java.util.List" };

		_methodName20 = "clearCorpGroupCorpEntries";

		_methodParameterTypes20 = new String[] { "long" };

		_methodName21 = "deleteCorpGroupCorpEntry";

		_methodParameterTypes21 = new String[] { "long", "long" };

		_methodName22 = "deleteCorpGroupCorpEntry";

		_methodParameterTypes22 = new String[] {
				"long", "com.liferay.osb.model.CorpEntry"
			};

		_methodName23 = "deleteCorpGroupCorpEntries";

		_methodParameterTypes23 = new String[] { "long", "long[][]" };

		_methodName24 = "deleteCorpGroupCorpEntries";

		_methodParameterTypes24 = new String[] { "long", "java.util.List" };

		_methodName25 = "getCorpGroupCorpEntries";

		_methodParameterTypes25 = new String[] { "long" };

		_methodName26 = "getCorpGroupCorpEntries";

		_methodParameterTypes26 = new String[] { "long", "int", "int" };

		_methodName27 = "getCorpGroupCorpEntries";

		_methodParameterTypes27 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName28 = "getCorpGroupCorpEntriesCount";

		_methodParameterTypes28 = new String[] { "long" };

		_methodName29 = "hasCorpGroupCorpEntry";

		_methodParameterTypes29 = new String[] { "long", "long" };

		_methodName30 = "hasCorpGroupCorpEntries";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "setCorpGroupCorpEntries";

		_methodParameterTypes31 = new String[] { "long", "long[][]" };

		_methodName736 = "getBeanIdentifier";

		_methodParameterTypes736 = new String[] {  };

		_methodName737 = "setBeanIdentifier";

		_methodParameterTypes737 = new String[] { "java.lang.String" };

		_methodName742 = "addCorpEntry";

		_methodParameterTypes742 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.io.File",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName743 = "deleteCorpEntry";

		_methodParameterTypes743 = new String[] {
				"com.liferay.osb.model.CorpEntry"
			};

		_methodName744 = "deleteCorpEntry";

		_methodParameterTypes744 = new String[] { "long" };

		_methodName745 = "fetchCorpEntry";

		_methodParameterTypes745 = new String[] { "java.lang.String" };

		_methodName746 = "fetchOrganizationCorpEntry";

		_methodParameterTypes746 = new String[] { "long" };

		_methodName747 = "getCorpEntry";

		_methodParameterTypes747 = new String[] { "java.lang.String" };

		_methodName748 = "getCorpGroupCorpEntries";

		_methodParameterTypes748 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName749 = "getCorpGroupCorpEntries";

		_methodParameterTypes749 = new String[] {
				"long", "long", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName750 = "getOrganizationCorpEntry";

		_methodParameterTypes750 = new String[] { "long" };

		_methodName751 = "getUserCorpEntries";

		_methodParameterTypes751 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName752 = "getUserCorpEntries";

		_methodParameterTypes752 = new String[] {
				"long", "long", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName753 = "getUserCorpEntries";

		_methodParameterTypes753 = new String[] {
				"long", "java.lang.String", "int", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName754 = "getUserCorpEntries";

		_methodParameterTypes754 = new String[] {
				"long", "java.lang.String", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName755 = "hasUserCorpEntry";

		_methodParameterTypes755 = new String[] { "long", "long" };

		_methodName756 = "hasUserCorpEntryRole";

		_methodParameterTypes756 = new String[] { "long", "long", "long" };

		_methodName757 = "hasUserCorpEntryRole";

		_methodParameterTypes757 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName758 = "mergeCorpEntry";

		_methodParameterTypes758 = new String[] {
				"long", "long", "long", "java.lang.String", "java.util.Map",
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName759 = "search";

		_methodParameterTypes759 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName760 = "search";

		_methodParameterTypes760 = new String[] {
				"java.lang.String", "int", "long[][]", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName761 = "searchCount";

		_methodParameterTypes761 = new String[] { "java.lang.String" };

		_methodName762 = "searchCount";

		_methodParameterTypes762 = new String[] {
				"java.lang.String", "int", "long[][]"
			};

		_methodName763 = "updateCorpEntry";

		_methodParameterTypes763 = new String[] {
				"long", "java.lang.String", "java.util.Map", "java.io.File",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName764 = "updateSite";

		_methodParameterTypes764 = new String[] { "long", "int", "boolean" };

		_methodName765 = "updateStatus";

		_methodParameterTypes765 = new String[] {
				"long", "long", "int", "java.lang.String"
			};

		_methodName766 = "validateMembership";

		_methodParameterTypes766 = new String[] { "long", "long", "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return CorpEntryLocalServiceUtil.addCorpEntry((com.liferay.osb.model.CorpEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return CorpEntryLocalServiceUtil.createCorpEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return CorpEntryLocalServiceUtil.deleteCorpEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return CorpEntryLocalServiceUtil.deleteCorpEntry((com.liferay.osb.model.CorpEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return CorpEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return CorpEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return CorpEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return CorpEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return CorpEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return CorpEntryLocalServiceUtil.fetchCorpEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getCorpEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getCorpEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getCorpEntriesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return CorpEntryLocalServiceUtil.updateCorpEntry((com.liferay.osb.model.CorpEntry)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return CorpEntryLocalServiceUtil.updateCorpEntry((com.liferay.osb.model.CorpEntry)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			CorpEntryLocalServiceUtil.addCorpGroupCorpEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			CorpEntryLocalServiceUtil.addCorpGroupCorpEntry(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.CorpEntry)arguments[1]);

			return null;
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			CorpEntryLocalServiceUtil.addCorpGroupCorpEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			CorpEntryLocalServiceUtil.addCorpGroupCorpEntries(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.CorpEntry>)arguments[1]);

			return null;
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			CorpEntryLocalServiceUtil.clearCorpGroupCorpEntries(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			CorpEntryLocalServiceUtil.deleteCorpGroupCorpEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			CorpEntryLocalServiceUtil.deleteCorpGroupCorpEntry(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.CorpEntry)arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			CorpEntryLocalServiceUtil.deleteCorpGroupCorpEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			CorpEntryLocalServiceUtil.deleteCorpGroupCorpEntries(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.CorpEntry>)arguments[1]);

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getCorpGroupCorpEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getCorpGroupCorpEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getCorpGroupCorpEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getCorpGroupCorpEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return CorpEntryLocalServiceUtil.hasCorpGroupCorpEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return CorpEntryLocalServiceUtil.hasCorpGroupCorpEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			CorpEntryLocalServiceUtil.setCorpGroupCorpEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName736.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes736, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName737.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes737, parameterTypes)) {
			CorpEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName742.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes742, parameterTypes)) {
			return CorpEntryLocalServiceUtil.addCorpEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.io.File)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				((Long)arguments[9]).longValue(),
				((Long)arguments[10]).longValue(),
				(java.lang.String)arguments[11],
				(java.lang.String)arguments[12],
				(java.lang.String)arguments[13],
				(java.lang.String)arguments[14],
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				(com.liferay.portal.service.ServiceContext)arguments[17]);
		}

		if (_methodName743.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes743, parameterTypes)) {
			return CorpEntryLocalServiceUtil.deleteCorpEntry((com.liferay.osb.model.CorpEntry)arguments[0]);
		}

		if (_methodName744.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes744, parameterTypes)) {
			return CorpEntryLocalServiceUtil.deleteCorpEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName745.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes745, parameterTypes)) {
			return CorpEntryLocalServiceUtil.fetchCorpEntry((java.lang.String)arguments[0]);
		}

		if (_methodName746.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes746, parameterTypes)) {
			return CorpEntryLocalServiceUtil.fetchOrganizationCorpEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName747.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes747, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getCorpEntry((java.lang.String)arguments[0]);
		}

		if (_methodName748.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes748, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getCorpGroupCorpEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName749.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes749, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getCorpGroupCorpEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName750.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes750, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getOrganizationCorpEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName751.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes751, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getUserCorpEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName752.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes752, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getUserCorpEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName753.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes753, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getUserCorpEntries(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[6]);
		}

		if (_methodName754.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes754, parameterTypes)) {
			return CorpEntryLocalServiceUtil.getUserCorpEntries(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName755.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes755, parameterTypes)) {
			return CorpEntryLocalServiceUtil.hasUserCorpEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName756.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes756, parameterTypes)) {
			return CorpEntryLocalServiceUtil.hasUserCorpEntryRole(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName757.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes757, parameterTypes)) {
			return CorpEntryLocalServiceUtil.hasUserCorpEntryRole(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName758.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes758, parameterTypes)) {
			return CorpEntryLocalServiceUtil.mergeCorpEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[4],
				((Long)arguments[5]).longValue(),
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				(java.lang.String)arguments[8], (java.lang.String)arguments[9],
				(java.lang.String)arguments[10],
				((Long)arguments[11]).longValue(),
				((Long)arguments[12]).longValue(),
				(java.lang.String)arguments[13],
				(java.lang.String)arguments[14],
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				(java.lang.String)arguments[17],
				(java.lang.String)arguments[18],
				(com.liferay.portal.service.ServiceContext)arguments[19]);
		}

		if (_methodName759.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes759, parameterTypes)) {
			return CorpEntryLocalServiceUtil.search((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName760.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes760, parameterTypes)) {
			return CorpEntryLocalServiceUtil.search((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(), (long[])arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName761.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes761, parameterTypes)) {
			return CorpEntryLocalServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName762.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes762, parameterTypes)) {
			return CorpEntryLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(), (long[])arguments[2]);
		}

		if (_methodName763.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes763, parameterTypes)) {
			return CorpEntryLocalServiceUtil.updateCorpEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.io.File)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				((Long)arguments[9]).longValue(),
				((Long)arguments[10]).longValue(),
				(java.lang.String)arguments[11],
				(java.lang.String)arguments[12],
				(java.lang.String)arguments[13],
				(java.lang.String)arguments[14],
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				(com.liferay.portal.service.ServiceContext)arguments[17]);
		}

		if (_methodName764.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes764, parameterTypes)) {
			CorpEntryLocalServiceUtil.updateSite(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Boolean)arguments[2]).booleanValue());

			return null;
		}

		if (_methodName765.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes765, parameterTypes)) {
			return CorpEntryLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				(java.lang.String)arguments[3]);
		}

		if (_methodName766.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes766, parameterTypes)) {
			CorpEntryLocalServiceUtil.validateMembership(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());

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
	private String _methodName736;
	private String[] _methodParameterTypes736;
	private String _methodName737;
	private String[] _methodParameterTypes737;
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
	private String _methodName765;
	private String[] _methodParameterTypes765;
	private String _methodName766;
	private String[] _methodParameterTypes766;
}