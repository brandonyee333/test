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

import com.liferay.osb.service.SupportTeamLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
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

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchSupportTeam";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getSupportTeam";

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

		_methodName17 = "getSupportTeams";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getSupportTeamsCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateSupportTeam";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.SupportTeam"
			};

		_methodName20 = "addAccountEntrySupportTeam";

		_methodParameterTypes20 = new String[] { "long", "long" };

		_methodName21 = "addAccountEntrySupportTeam";

		_methodParameterTypes21 = new String[] {
				"long", "com.liferay.osb.model.SupportTeam"
			};

		_methodName22 = "addAccountEntrySupportTeams";

		_methodParameterTypes22 = new String[] { "long", "long[][]" };

		_methodName23 = "addAccountEntrySupportTeams";

		_methodParameterTypes23 = new String[] { "long", "java.util.List" };

		_methodName24 = "clearAccountEntrySupportTeams";

		_methodParameterTypes24 = new String[] { "long" };

		_methodName25 = "deleteAccountEntrySupportTeam";

		_methodParameterTypes25 = new String[] { "long", "long" };

		_methodName26 = "deleteAccountEntrySupportTeam";

		_methodParameterTypes26 = new String[] {
				"long", "com.liferay.osb.model.SupportTeam"
			};

		_methodName27 = "deleteAccountEntrySupportTeams";

		_methodParameterTypes27 = new String[] { "long", "long[][]" };

		_methodName28 = "deleteAccountEntrySupportTeams";

		_methodParameterTypes28 = new String[] { "long", "java.util.List" };

		_methodName29 = "getAccountEntryPrimaryKeys";

		_methodParameterTypes29 = new String[] { "long" };

		_methodName30 = "getAccountEntrySupportTeams";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "getAccountEntrySupportTeams";

		_methodParameterTypes31 = new String[] { "long", "int", "int" };

		_methodName32 = "getAccountEntrySupportTeams";

		_methodParameterTypes32 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName33 = "getAccountEntrySupportTeamsCount";

		_methodParameterTypes33 = new String[] { "long" };

		_methodName34 = "hasAccountEntrySupportTeam";

		_methodParameterTypes34 = new String[] { "long", "long" };

		_methodName35 = "hasAccountEntrySupportTeams";

		_methodParameterTypes35 = new String[] { "long" };

		_methodName36 = "setAccountEntrySupportTeams";

		_methodParameterTypes36 = new String[] { "long", "long[][]" };

		_methodName37 = "addSupportRegionSupportTeam";

		_methodParameterTypes37 = new String[] { "long", "long" };

		_methodName38 = "addSupportRegionSupportTeam";

		_methodParameterTypes38 = new String[] {
				"long", "com.liferay.osb.model.SupportTeam"
			};

		_methodName39 = "addSupportRegionSupportTeams";

		_methodParameterTypes39 = new String[] { "long", "long[][]" };

		_methodName40 = "addSupportRegionSupportTeams";

		_methodParameterTypes40 = new String[] { "long", "java.util.List" };

		_methodName41 = "clearSupportRegionSupportTeams";

		_methodParameterTypes41 = new String[] { "long" };

		_methodName42 = "deleteSupportRegionSupportTeam";

		_methodParameterTypes42 = new String[] { "long", "long" };

		_methodName43 = "deleteSupportRegionSupportTeam";

		_methodParameterTypes43 = new String[] {
				"long", "com.liferay.osb.model.SupportTeam"
			};

		_methodName44 = "deleteSupportRegionSupportTeams";

		_methodParameterTypes44 = new String[] { "long", "long[][]" };

		_methodName45 = "deleteSupportRegionSupportTeams";

		_methodParameterTypes45 = new String[] { "long", "java.util.List" };

		_methodName46 = "getSupportRegionPrimaryKeys";

		_methodParameterTypes46 = new String[] { "long" };

		_methodName47 = "getSupportRegionSupportTeams";

		_methodParameterTypes47 = new String[] { "long" };

		_methodName48 = "getSupportRegionSupportTeams";

		_methodParameterTypes48 = new String[] { "long", "int", "int" };

		_methodName49 = "getSupportRegionSupportTeams";

		_methodParameterTypes49 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName50 = "getSupportRegionSupportTeamsCount";

		_methodParameterTypes50 = new String[] { "long" };

		_methodName51 = "hasSupportRegionSupportTeam";

		_methodParameterTypes51 = new String[] { "long", "long" };

		_methodName52 = "hasSupportRegionSupportTeams";

		_methodParameterTypes52 = new String[] { "long" };

		_methodName53 = "setSupportRegionSupportTeams";

		_methodParameterTypes53 = new String[] { "long", "long[][]" };

		_methodName308 = "getOSGiServiceIdentifier";

		_methodParameterTypes308 = new String[] {  };

		_methodName313 = "addSupportTeam";

		_methodParameterTypes313 = new String[] {
				"long", "long", "long", "long", "java.lang.String",
				"java.lang.String", "int"
			};

		_methodName314 = "deleteSupportTeam";

		_methodParameterTypes314 = new String[] { "long" };

		_methodName315 = "getChildSupportTeams";

		_methodParameterTypes315 = new String[] { "long", "boolean" };

		_methodName316 = "getSupportLaborSupportTeams";

		_methodParameterTypes316 = new String[] { "long" };

		_methodName317 = "getSupportTeams";

		_methodParameterTypes317 = new String[] {
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName318 = "getUserRoleSupportTeams";

		_methodParameterTypes318 = new String[] { "long", "int" };

		_methodName319 = "search";

		_methodParameterTypes319 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName320 = "search";

		_methodParameterTypes320 = new String[] {
				"java.lang.String", "java.lang.Integer", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName321 = "searchCount";

		_methodParameterTypes321 = new String[] { "java.lang.String" };

		_methodName322 = "searchCount";

		_methodParameterTypes322 = new String[] {
				"java.lang.String", "java.lang.Integer", "boolean"
			};

		_methodName323 = "setChildSupportTeams";

		_methodParameterTypes323 = new String[] { "long", "long[][]" };

		_methodName324 = "setSupportLaborId";

		_methodParameterTypes324 = new String[] { "long", "long[][]" };

		_methodName325 = "updateSupportTeam";

		_methodParameterTypes325 = new String[] {
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
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SupportTeamLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SupportTeamLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SupportTeamLocalServiceUtil.fetchSupportTeam(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportTeam(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SupportTeamLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportTeams(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportTeamsCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return SupportTeamLocalServiceUtil.updateSupportTeam((com.liferay.osb.model.SupportTeam)arguments[0]);
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			SupportTeamLocalServiceUtil.addAccountEntrySupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			SupportTeamLocalServiceUtil.addAccountEntrySupportTeam(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.SupportTeam)arguments[1]);

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			SupportTeamLocalServiceUtil.addAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			SupportTeamLocalServiceUtil.addAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.SupportTeam>)arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			SupportTeamLocalServiceUtil.clearAccountEntrySupportTeams(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteAccountEntrySupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteAccountEntrySupportTeam(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.SupportTeam)arguments[1]);

			return null;
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.SupportTeam>)arguments[1]);

			return null;
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getAccountEntryPrimaryKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getAccountEntrySupportTeams(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportTeam>)arguments[3]);
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getAccountEntrySupportTeamsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return SupportTeamLocalServiceUtil.hasAccountEntrySupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			return SupportTeamLocalServiceUtil.hasAccountEntrySupportTeams(((Long)arguments[0]).longValue());
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			SupportTeamLocalServiceUtil.setAccountEntrySupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			SupportTeamLocalServiceUtil.addSupportRegionSupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			SupportTeamLocalServiceUtil.addSupportRegionSupportTeam(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.SupportTeam)arguments[1]);

			return null;
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			SupportTeamLocalServiceUtil.addSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			SupportTeamLocalServiceUtil.addSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.SupportTeam>)arguments[1]);

			return null;
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			SupportTeamLocalServiceUtil.clearSupportRegionSupportTeams(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteSupportRegionSupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteSupportRegionSupportTeam(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.SupportTeam)arguments[1]);

			return null;
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			SupportTeamLocalServiceUtil.deleteSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.SupportTeam>)arguments[1]);

			return null;
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportRegionPrimaryKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportRegionSupportTeams(((Long)arguments[0]).longValue());
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportTeam>)arguments[3]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportRegionSupportTeamsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return SupportTeamLocalServiceUtil.hasSupportRegionSupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return SupportTeamLocalServiceUtil.hasSupportRegionSupportTeams(((Long)arguments[0]).longValue());
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			SupportTeamLocalServiceUtil.setSupportRegionSupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName308.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes308, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName313.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes313, parameterTypes)) {
			return SupportTeamLocalServiceUtil.addSupportTeam(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue());
		}

		if (_methodName314.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes314, parameterTypes)) {
			return SupportTeamLocalServiceUtil.deleteSupportTeam(((Long)arguments[0]).longValue());
		}

		if (_methodName315.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes315, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getChildSupportTeams(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName316.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes316, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportLaborSupportTeams(((Long)arguments[0]).longValue());
		}

		if (_methodName317.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes317, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getSupportTeams(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[2]);
		}

		if (_methodName318.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes318, parameterTypes)) {
			return SupportTeamLocalServiceUtil.getUserRoleSupportTeams(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName319.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes319, parameterTypes)) {
			return SupportTeamLocalServiceUtil.search((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName320.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes320, parameterTypes)) {
			return SupportTeamLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.Integer)arguments[1],
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName321.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes321, parameterTypes)) {
			return SupportTeamLocalServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName322.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes322, parameterTypes)) {
			return SupportTeamLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.lang.Integer)arguments[1],
				((Boolean)arguments[2]).booleanValue());
		}

		if (_methodName323.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes323, parameterTypes)) {
			SupportTeamLocalServiceUtil.setChildSupportTeams(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName324.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes324, parameterTypes)) {
			SupportTeamLocalServiceUtil.setSupportLaborId(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName325.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes325, parameterTypes)) {
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
	private String _methodName48;
	private String[] _methodParameterTypes48;
	private String _methodName49;
	private String[] _methodParameterTypes49;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName308;
	private String[] _methodParameterTypes308;
	private String _methodName313;
	private String[] _methodParameterTypes313;
	private String _methodName314;
	private String[] _methodParameterTypes314;
	private String _methodName315;
	private String[] _methodParameterTypes315;
	private String _methodName316;
	private String[] _methodParameterTypes316;
	private String _methodName317;
	private String[] _methodParameterTypes317;
	private String _methodName318;
	private String[] _methodParameterTypes318;
	private String _methodName319;
	private String[] _methodParameterTypes319;
	private String _methodName320;
	private String[] _methodParameterTypes320;
	private String _methodName321;
	private String[] _methodParameterTypes321;
	private String _methodName322;
	private String[] _methodParameterTypes322;
	private String _methodName323;
	private String[] _methodParameterTypes323;
	private String _methodName324;
	private String[] _methodParameterTypes324;
	private String _methodName325;
	private String[] _methodParameterTypes325;
}