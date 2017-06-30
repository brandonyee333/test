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

import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class DeveloperEntryLocalServiceClpInvoker {
	public DeveloperEntryLocalServiceClpInvoker() {
		_methodName0 = "addDeveloperEntry";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.DeveloperEntry"
			};

		_methodName1 = "createDeveloperEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteDeveloperEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteDeveloperEntry";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.DeveloperEntry"
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

		_methodName9 = "fetchDeveloperEntry";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getDeveloperEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getDeveloperEntries";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getDeveloperEntriesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateDeveloperEntry";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.DeveloperEntry"
			};

		_methodName15 = "updateDeveloperEntry";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.DeveloperEntry", "boolean"
			};

		_methodName712 = "getBeanIdentifier";

		_methodParameterTypes712 = new String[] {  };

		_methodName713 = "setBeanIdentifier";

		_methodParameterTypes713 = new String[] { "java.lang.String" };

		_methodName718 = "addCompanyDeveloperEntry";

		_methodParameterTypes718 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "java.util.Map",
				"java.lang.String", "java.io.File", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.io.File", "long",
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName719 = "addUserDeveloperEntry";

		_methodParameterTypes719 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"long", "long", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName720 = "deleteDeveloperEntry";

		_methodParameterTypes720 = new String[] {
				"com.liferay.osb.model.DeveloperEntry"
			};

		_methodName721 = "deleteDeveloperEntry";

		_methodParameterTypes721 = new String[] { "long" };

		_methodName722 = "deleteUserDeveloperEntry";

		_methodParameterTypes722 = new String[] { "long" };

		_methodName723 = "fetchDeveloperEntry";

		_methodParameterTypes723 = new String[] { "java.lang.String" };

		_methodName724 = "fetchUserDeveloperEntry";

		_methodParameterTypes724 = new String[] { "long" };

		_methodName725 = "getDeveloperEntries";

		_methodParameterTypes725 = new String[] { "int", "int", "int" };

		_methodName726 = "getDeveloperEntries";

		_methodParameterTypes726 = new String[] { "int", "int", "int", "int" };

		_methodName727 = "getDeveloperEntries";

		_methodParameterTypes727 = new String[] {
				"long", "int", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName728 = "getDeveloperEntriesCount";

		_methodParameterTypes728 = new String[] { "int" };

		_methodName729 = "getDeveloperEntriesCount";

		_methodParameterTypes729 = new String[] { "int", "int" };

		_methodName730 = "getDeveloperEntryByGroupId";

		_methodParameterTypes730 = new String[] { "long" };

		_methodName731 = "getUserDeveloperEntry";

		_methodParameterTypes731 = new String[] { "long" };

		_methodName732 = "updateCompanyDeveloperEntry";

		_methodParameterTypes732 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "java.io.File",
				"java.util.Map", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.io.File",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName733 = "updateDeveloperEntry";

		_methodParameterTypes733 = new String[] {
				"long", "long", "java.lang.String", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.util.Date", "int",
				"double", "java.lang.String", "java.io.File"
			};

		_methodName734 = "updateDeveloperEntry";

		_methodParameterTypes734 = new String[] { "long", "java.lang.String" };

		_methodName735 = "updateDeveloperEntry";

		_methodParameterTypes735 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName736 = "updateDeveloperEntryGoogleAnalyticsKey";

		_methodParameterTypes736 = new String[] { "long", "java.lang.String" };

		_methodName737 = "updateStatus";

		_methodParameterTypes737 = new String[] {
				"long", "long", "int", "java.lang.String"
			};

		_methodName738 = "updateSubscription";

		_methodParameterTypes738 = new String[] { "long", "java.util.Date", "int" };

		_methodName739 = "updateUserDeveloperEntry";

		_methodParameterTypes739 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long",
				"java.lang.String", "java.io.File"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.addDeveloperEntry((com.liferay.osb.model.DeveloperEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.createDeveloperEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.deleteDeveloperEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.deleteDeveloperEntry((com.liferay.osb.model.DeveloperEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.fetchDeveloperEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.getDeveloperEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.getDeveloperEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.getDeveloperEntriesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.updateDeveloperEntry((com.liferay.osb.model.DeveloperEntry)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.updateDeveloperEntry((com.liferay.osb.model.DeveloperEntry)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName712.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes712, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName713.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes713, parameterTypes)) {
			DeveloperEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName718.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes718, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.addCompanyDeveloperEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				(java.lang.String)arguments[9],
				((Long)arguments[10]).longValue(),
				((Long)arguments[11]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[12],
				(java.lang.String)arguments[13], (java.io.File)arguments[14],
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				(java.lang.String)arguments[17], (java.io.File)arguments[18],
				((Long)arguments[19]).longValue(),
				((Long)arguments[20]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[21]);
		}

		if (_methodName719.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes719, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.addUserDeveloperEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				((Long)arguments[6]).longValue(),
				((Long)arguments[7]).longValue(),
				(java.lang.String)arguments[8],
				(com.liferay.portal.service.ServiceContext)arguments[9]);
		}

		if (_methodName720.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes720, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.deleteDeveloperEntry((com.liferay.osb.model.DeveloperEntry)arguments[0]);
		}

		if (_methodName721.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes721, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.deleteDeveloperEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName722.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes722, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.deleteUserDeveloperEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName723.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes723, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.fetchDeveloperEntry((java.lang.String)arguments[0]);
		}

		if (_methodName724.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes724, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.fetchUserDeveloperEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName725.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes725, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.getDeveloperEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName726.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes726, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.getDeveloperEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName727.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes727, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.getDeveloperEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName728.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes728, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.getDeveloperEntriesCount(((Integer)arguments[0]).intValue());
		}

		if (_methodName729.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes729, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.getDeveloperEntriesCount(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName730.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes730, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.getDeveloperEntryByGroupId(((Long)arguments[0]).longValue());
		}

		if (_methodName731.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes731, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.getUserDeveloperEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName732.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes732, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.updateCompanyDeveloperEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				(java.lang.String)arguments[8], (java.lang.String)arguments[9],
				(java.lang.String)arguments[10],
				((Long)arguments[11]).longValue(),
				((Long)arguments[12]).longValue(), (java.io.File)arguments[13],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[14],
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				(java.lang.String)arguments[17], (java.io.File)arguments[18],
				(com.liferay.portal.service.ServiceContext)arguments[19]);
		}

		if (_methodName733.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes733, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.updateDeveloperEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6], (java.util.Date)arguments[7],
				((Integer)arguments[8]).intValue(),
				((Double)arguments[9]).doubleValue(),
				(java.lang.String)arguments[10], (java.io.File)arguments[11]);
		}

		if (_methodName734.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes734, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.updateDeveloperEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName735.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes735, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.updateDeveloperEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName736.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes736, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.updateDeveloperEntryGoogleAnalyticsKey(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName737.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes737, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				(java.lang.String)arguments[3]);
		}

		if (_methodName738.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes738, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.updateSubscription(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1], ((Integer)arguments[2]).intValue());
		}

		if (_methodName739.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes739, parameterTypes)) {
			return DeveloperEntryLocalServiceUtil.updateUserDeveloperEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				(java.lang.String)arguments[8], (java.lang.String)arguments[9],
				(java.lang.String)arguments[10],
				(java.lang.String)arguments[11],
				((Long)arguments[12]).longValue(),
				((Long)arguments[13]).longValue(),
				(java.lang.String)arguments[14], (java.io.File)arguments[15]);
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
	private String _methodName712;
	private String[] _methodParameterTypes712;
	private String _methodName713;
	private String[] _methodParameterTypes713;
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
}