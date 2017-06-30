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

import com.liferay.osb.service.SupportTeamLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class SupportTeamLocalServiceClpInvoker {
	public SupportTeamLocalServiceClpInvoker() {
		_methodName0 = "addSupportTeam";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.SupportTeam"
			};

		_methodName1 = "createSupportTeam";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteSupportTeam";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteSupportTeam";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.SupportTeam"
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

		_methodName9 = "fetchSupportTeam";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getSupportTeam";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getSupportTeams";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getSupportTeamsCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateSupportTeam";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.SupportTeam"
			};

		_methodName15 = "updateSupportTeam";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.SupportTeam", "boolean"
			};

		_methodName16 = "addAccountEntrySupportTeam";

		_methodParameterTypes16 = new String[] { "long", "long" };

		_methodName17 = "addAccountEntrySupportTeam";

		_methodParameterTypes17 = new String[] {
				"long", "com.liferay.osb.model.SupportTeam"
			};

		_methodName18 = "addAccountEntrySupportTeams";

		_methodParameterTypes18 = new String[] { "long", "long[][]" };

		_methodName19 = "addAccountEntrySupportTeams";

		_methodParameterTypes19 = new String[] { "long", "java.util.List" };

		_methodName20 = "clearAccountEntrySupportTeams";

		_methodParameterTypes20 = new String[] { "long" };

		_methodName21 = "deleteAccountEntrySupportTeam";

		_methodParameterTypes21 = new String[] { "long", "long" };

		_methodName22 = "deleteAccountEntrySupportTeam";

		_methodParameterTypes22 = new String[] {
				"long", "com.liferay.osb.model.SupportTeam"
			};

		_methodName23 = "deleteAccountEntrySupportTeams";

		_methodParameterTypes23 = new String[] { "long", "long[][]" };

		_methodName24 = "deleteAccountEntrySupportTeams";

		_methodParameterTypes24 = new String[] { "long", "java.util.List" };

		_methodName25 = "getAccountEntrySupportTeams";

		_methodParameterTypes25 = new String[] { "long" };

		_methodName26 = "getAccountEntrySupportTeams";

		_methodParameterTypes26 = new String[] { "long", "int", "int" };

		_methodName27 = "getAccountEntrySupportTeams";

		_methodParameterTypes27 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName28 = "getAccountEntrySupportTeamsCount";

		_methodParameterTypes28 = new String[] { "long" };

		_methodName29 = "hasAccountEntrySupportTeam";

		_methodParameterTypes29 = new String[] { "long", "long" };

		_methodName30 = "hasAccountEntrySupportTeams";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "setAccountEntrySupportTeams";

		_methodParameterTypes31 = new String[] { "long", "long[][]" };

		_methodName32 = "addSupportRegionSupportTeam";

		_methodParameterTypes32 = new String[] { "long", "long" };

		_methodName33 = "addSupportRegionSupportTeam";

		_methodParameterTypes33 = new String[] {
				"long", "com.liferay.osb.model.SupportTeam"
			};

		_methodName34 = "addSupportRegionSupportTeams";

		_methodParameterTypes34 = new String[] { "long", "long[][]" };

		_methodName35 = "addSupportRegionSupportTeams";

		_methodParameterTypes35 = new String[] { "long", "java.util.List" };

		_methodName36 = "clearSupportRegionSupportTeams";

		_methodParameterTypes36 = new String[] { "long" };

		_methodName37 = "deleteSupportRegionSupportTeam";

		_methodParameterTypes37 = new String[] { "long", "long" };

		_methodName38 = "deleteSupportRegionSupportTeam";

		_methodParameterTypes38 = new String[] {
				"long", "com.liferay.osb.model.SupportTeam"
			};

		_methodName39 = "deleteSupportRegionSupportTeams";

		_methodParameterTypes39 = new String[] { "long", "long[][]" };

		_methodName40 = "deleteSupportRegionSupportTeams";

		_methodParameterTypes40 = new String[] { "long", "java.util.List" };

		_methodName41 = "getSupportRegionSupportTeams";

		_methodParameterTypes41 = new String[] { "long" };

		_methodName42 = "getSupportRegionSupportTeams";

		_methodParameterTypes42 = new String[] { "long", "int", "int" };

		_methodName43 = "getSupportRegionSupportTeams";

		_methodParameterTypes43 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName44 = "getSupportRegionSupportTeamsCount";

		_methodParameterTypes44 = new String[] { "long" };

		_methodName45 = "hasSupportRegionSupportTeam";

		_methodParameterTypes45 = new String[] { "long", "long" };

		_methodName46 = "hasSupportRegionSupportTeams";

		_methodParameterTypes46 = new String[] { "long" };

		_methodName47 = "setSupportRegionSupportTeams";

		_methodParameterTypes47 = new String[] { "long", "long[][]" };

		_methodName712 = "getBeanIdentifier";

		_methodParameterTypes712 = new String[] {  };

		_methodName713 = "setBeanIdentifier";

		_methodParameterTypes713 = new String[] { "java.lang.String" };

		_methodName718 = "addSupportTeam";

		_methodParameterTypes718 = new String[] {
				"long", "long", "long", "long", "java.lang.String",
				"java.lang.String", "int"
			};

		_methodName719 = "deleteSupportTeam";

		_methodParameterTypes719 = new String[] { "long" };

		_methodName720 = "getChildSupportTeams";

		_methodParameterTypes720 = new String[] { "long", "boolean" };

		_methodName721 = "getSupportLaborSupportTeams";

		_methodParameterTypes721 = new String[] { "long" };

		_methodName722 = "getSupportTeams";

		_methodParameterTypes722 = new String[] {
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName723 = "getUserRoleSupportTeams";

		_methodParameterTypes723 = new String[] { "long", "int" };

		_methodName724 = "search";

		_methodParameterTypes724 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName725 = "search";

		_methodParameterTypes725 = new String[] {
				"java.lang.String", "java.lang.Integer", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName726 = "searchCount";

		_methodParameterTypes726 = new String[] { "java.lang.String" };

		_methodName727 = "searchCount";

		_methodParameterTypes727 = new String[] {
				"java.lang.String", "java.lang.Integer", "boolean"
			};

		_methodName728 = "setChildSupportTeams";

		_methodParameterTypes728 = new String[] { "long", "long[][]" };

		_methodName729 = "setSupportLaborId";

		_methodParameterTypes729 = new String[] { "long", "long[][]" };

		_methodName730 = "updateSupportTeam";

		_methodParameterTypes730 = new String[] {
				"long", "long", "long", "long", "java.lang.String",
				"java.lang.String", "int", "long[][]", "long[][]"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SupportTeamLocalServiceUtil.addSupportTeam((com.liferay.osb.model.SupportTeam)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SupportTeamLocalServiceUtil.createSupportTeam(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SupportTeamLocalServiceUtil.deleteSupportTeam(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SupportTeamLocalServiceUtil.deleteSupportTeam((com.liferay.osb.model.SupportTeam)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SupportTeamLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SupportTeamLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SupportTeamLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SupportTeamLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SupportTeamLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SupportTeamLocalServiceUtil.fetchSupportTeam(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportTeam(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportTeams(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportTeamsCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SupportTeamLocalServiceUtil.updateSupportTeam((com.liferay.osb.model.SupportTeam)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SupportTeamLocalServiceUtil.updateSupportTeam((com.liferay.osb.model.SupportTeam)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			SupportTeamLocalServiceUtil.addAccountEntrySupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			SupportTeamLocalServiceUtil.addAccountEntrySupportTeam(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.SupportTeam)arguments[1]);

			return null;
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			SupportTeamLocalServiceUtil.addAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			SupportTeamLocalServiceUtil.addAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.SupportTeam>)arguments[1]);

			return null;
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			SupportTeamLocalServiceUtil.clearAccountEntrySupportTeams(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteAccountEntrySupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteAccountEntrySupportTeam(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.SupportTeam)arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.SupportTeam>)arguments[1]);

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getAccountEntrySupportTeams(((Long)arguments[0]).longValue());
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getAccountEntrySupportTeamsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return SupportTeamLocalServiceUtil.hasAccountEntrySupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return SupportTeamLocalServiceUtil.hasAccountEntrySupportTeams(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			SupportTeamLocalServiceUtil.setAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			SupportTeamLocalServiceUtil.addSupportRegionSupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			SupportTeamLocalServiceUtil.addSupportRegionSupportTeam(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.SupportTeam)arguments[1]);

			return null;
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			SupportTeamLocalServiceUtil.addSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			SupportTeamLocalServiceUtil.addSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.SupportTeam>)arguments[1]);

			return null;
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			SupportTeamLocalServiceUtil.clearSupportRegionSupportTeams(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteSupportRegionSupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteSupportRegionSupportTeam(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.SupportTeam)arguments[1]);

			return null;
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.SupportTeam>)arguments[1]);

			return null;
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportRegionSupportTeams(((Long)arguments[0]).longValue());
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportRegionSupportTeamsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return SupportTeamLocalServiceUtil.hasSupportRegionSupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return SupportTeamLocalServiceUtil.hasSupportRegionSupportTeams(((Long)arguments[0]).longValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			SupportTeamLocalServiceUtil.setSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName712.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes712, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName713.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes713, parameterTypes)) {
			SupportTeamLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName718.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes718, parameterTypes)) {
			return SupportTeamLocalServiceUtil.addSupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue());
		}

		if (_methodName719.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes719, parameterTypes)) {
			return SupportTeamLocalServiceUtil.deleteSupportTeam(((Long)arguments[0]).longValue());
		}

		if (_methodName720.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes720, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getChildSupportTeams(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName721.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes721, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportLaborSupportTeams(((Long)arguments[0]).longValue());
		}

		if (_methodName722.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes722, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportTeams(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[2]);
		}

		if (_methodName723.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes723, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getUserRoleSupportTeams(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName724.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes724, parameterTypes)) {
			return SupportTeamLocalServiceUtil.search((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName725.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes725, parameterTypes)) {
			return SupportTeamLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.Integer)arguments[1],
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName726.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes726, parameterTypes)) {
			return SupportTeamLocalServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName727.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes727, parameterTypes)) {
			return SupportTeamLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.lang.Integer)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName728.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes728, parameterTypes)) {
			SupportTeamLocalServiceUtil.setChildSupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName729.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes729, parameterTypes)) {
			SupportTeamLocalServiceUtil.setSupportLaborId(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName730.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes730, parameterTypes)) {
			return SupportTeamLocalServiceUtil.updateSupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue(), (long[])arguments[7],
				(long[])arguments[8]);
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
	private String _methodName32;
	private String[] _methodParameterTypes32;
	private String _methodName33;
	private String[] _methodParameterTypes33;
	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
	private String _methodName38;
	private String[] _methodParameterTypes38;
	private String _methodName39;
	private String[] _methodParameterTypes39;
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
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
}