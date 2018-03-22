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

import com.liferay.osb.service.AccountEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountEntryLocalServiceClpInvoker {
	public AccountEntryLocalServiceClpInvoker() {
		_methodName0 = "addAccountEntry";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.AccountEntry"
			};

		_methodName1 = "createAccountEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAccountEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAccountEntry";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.AccountEntry"
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

		_methodName10 = "fetchAccountEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getAccountEntry";

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

		_methodName17 = "getAccountEntries";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getAccountEntriesCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateAccountEntry";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.AccountEntry"
			};

		_methodName20 = "addSupportRegionAccountEntry";

		_methodParameterTypes20 = new String[] { "long", "long" };

		_methodName21 = "addSupportRegionAccountEntry";

		_methodParameterTypes21 = new String[] {
				"long", "com.liferay.osb.model.AccountEntry"
			};

		_methodName22 = "addSupportRegionAccountEntries";

		_methodParameterTypes22 = new String[] { "long", "long[][]" };

		_methodName23 = "addSupportRegionAccountEntries";

		_methodParameterTypes23 = new String[] { "long", "java.util.List" };

		_methodName24 = "clearSupportRegionAccountEntries";

		_methodParameterTypes24 = new String[] { "long" };

		_methodName25 = "deleteSupportRegionAccountEntry";

		_methodParameterTypes25 = new String[] { "long", "long" };

		_methodName26 = "deleteSupportRegionAccountEntry";

		_methodParameterTypes26 = new String[] {
				"long", "com.liferay.osb.model.AccountEntry"
			};

		_methodName27 = "deleteSupportRegionAccountEntries";

		_methodParameterTypes27 = new String[] { "long", "long[][]" };

		_methodName28 = "deleteSupportRegionAccountEntries";

		_methodParameterTypes28 = new String[] { "long", "java.util.List" };

		_methodName29 = "getSupportRegionPrimaryKeys";

		_methodParameterTypes29 = new String[] { "long" };

		_methodName30 = "getSupportRegionAccountEntries";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "getSupportRegionAccountEntries";

		_methodParameterTypes31 = new String[] { "long", "int", "int" };

		_methodName32 = "getSupportRegionAccountEntries";

		_methodParameterTypes32 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName33 = "getSupportRegionAccountEntriesCount";

		_methodParameterTypes33 = new String[] { "long" };

		_methodName34 = "hasSupportRegionAccountEntry";

		_methodParameterTypes34 = new String[] { "long", "long" };

		_methodName35 = "hasSupportRegionAccountEntries";

		_methodParameterTypes35 = new String[] { "long" };

		_methodName36 = "setSupportRegionAccountEntries";

		_methodParameterTypes36 = new String[] { "long", "long[][]" };

		_methodName37 = "addSupportTeamAccountEntry";

		_methodParameterTypes37 = new String[] { "long", "long" };

		_methodName38 = "addSupportTeamAccountEntry";

		_methodParameterTypes38 = new String[] {
				"long", "com.liferay.osb.model.AccountEntry"
			};

		_methodName39 = "addSupportTeamAccountEntries";

		_methodParameterTypes39 = new String[] { "long", "long[][]" };

		_methodName40 = "addSupportTeamAccountEntries";

		_methodParameterTypes40 = new String[] { "long", "java.util.List" };

		_methodName41 = "clearSupportTeamAccountEntries";

		_methodParameterTypes41 = new String[] { "long" };

		_methodName42 = "deleteSupportTeamAccountEntry";

		_methodParameterTypes42 = new String[] { "long", "long" };

		_methodName43 = "deleteSupportTeamAccountEntry";

		_methodParameterTypes43 = new String[] {
				"long", "com.liferay.osb.model.AccountEntry"
			};

		_methodName44 = "deleteSupportTeamAccountEntries";

		_methodParameterTypes44 = new String[] { "long", "long[][]" };

		_methodName45 = "deleteSupportTeamAccountEntries";

		_methodParameterTypes45 = new String[] { "long", "java.util.List" };

		_methodName46 = "getSupportTeamPrimaryKeys";

		_methodParameterTypes46 = new String[] { "long" };

		_methodName47 = "getSupportTeamAccountEntries";

		_methodParameterTypes47 = new String[] { "long" };

		_methodName48 = "getSupportTeamAccountEntries";

		_methodParameterTypes48 = new String[] { "long", "int", "int" };

		_methodName49 = "getSupportTeamAccountEntries";

		_methodParameterTypes49 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName50 = "getSupportTeamAccountEntriesCount";

		_methodParameterTypes50 = new String[] { "long" };

		_methodName51 = "hasSupportTeamAccountEntry";

		_methodParameterTypes51 = new String[] { "long", "long" };

		_methodName52 = "hasSupportTeamAccountEntries";

		_methodParameterTypes52 = new String[] { "long" };

		_methodName53 = "setSupportTeamAccountEntries";

		_methodParameterTypes53 = new String[] { "long", "long[][]" };

		_methodName326 = "getOSGiServiceIdentifier";

		_methodParameterTypes326 = new String[] {  };

		_methodName331 = "addAccountEntry";

		_methodParameterTypes331 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "int", "int", "long", "boolean", "int",
				"int", "java.lang.String", "java.lang.String",
				"java.lang.String[][]", "long[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "java.lang.String"
			};

		_methodName332 = "addAccountEntryWithWorkflow";

		_methodParameterTypes332 = new String[] {
				"java.lang.String", "com.liferay.osb.model.AccountEntry",
				"com.liferay.osb.model.CorpProject",
				"com.liferay.osb.model.PartnerEntry",
				"com.liferay.portal.kernel.model.Address",
				"com.liferay.osb.model.AccountWorker", "java.util.List",
				"java.util.ArrayList",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName333 = "addTrialAccountEntry";

		_methodParameterTypes333 = new String[] { "long", "long" };

		_methodName334 = "auditAccountEntries";

		_methodParameterTypes334 = new String[] {  };

		_methodName335 = "auditAccountEntry";

		_methodParameterTypes335 = new String[] { "long", "long" };

		_methodName336 = "deleteAccountEntry";

		_methodParameterTypes336 = new String[] { "long" };

		_methodName337 = "fetchCorpProjectAccountEntry";

		_methodParameterTypes337 = new String[] { "long" };

		_methodName338 = "fetchUserTrialAccountEntry";

		_methodParameterTypes338 = new String[] { "long" };

		_methodName339 = "getAccountEntries";

		_methodParameterTypes339 = new String[] { "int[][]", "int", "int" };

		_methodName340 = "getAccountEntries";

		_methodParameterTypes340 = new String[] {
				"int[][]", "int[][]", "int", "int"
			};

		_methodName341 = "getAccountEntry";

		_methodParameterTypes341 = new String[] { "long" };

		_methodName342 = "getAccountEntryByCode";

		_methodParameterTypes342 = new String[] { "java.lang.String" };

		_methodName343 = "getAccountEntryByName";

		_methodParameterTypes343 = new String[] { "java.lang.String" };

		_methodName344 = "getActiveAccountEntries";

		_methodParameterTypes344 = new String[] { "int", "int" };

		_methodName345 = "getPartnerAccountEntries";

		_methodParameterTypes345 = new String[] { "long" };

		_methodName346 = "getRedirectAccountEntries";

		_methodParameterTypes346 = new String[] { "long" };

		_methodName347 = "getSecurityPatchAccountEntries";

		_methodParameterTypes347 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName348 = "getUserAccountEntries";

		_methodParameterTypes348 = new String[] { "long", "int", "int" };

		_methodName349 = "getUserAccountEntriesCount";

		_methodParameterTypes349 = new String[] { "long" };

		_methodName350 = "getUserAccountEntryIds";

		_methodParameterTypes350 = new String[] { "long", "int", "int" };

		_methodName351 = "getUserAccountEntryNames";

		_methodParameterTypes351 = new String[] { "long" };

		_methodName352 = "getUserActiveAccountEntries";

		_methodParameterTypes352 = new String[] { "long", "int", "int" };

		_methodName353 = "hasValidLicenseAccountEntry";

		_methodParameterTypes353 = new String[] { "long" };

		_methodName354 = "hasValidSupportAccountEntry";

		_methodParameterTypes354 = new String[] { "long" };

		_methodName355 = "recalculateHighestSupportResponse";

		_methodParameterTypes355 = new String[] { "long" };

		_methodName356 = "reindexAccountEntry";

		_methodParameterTypes356 = new String[] { "long" };

		_methodName357 = "search";

		_methodParameterTypes357 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int[][]",
				"java.lang.Boolean", "int[][]", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName358 = "search";

		_methodParameterTypes358 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName359 = "search";

		_methodParameterTypes359 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName360 = "searchCount";

		_methodParameterTypes360 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int[][]",
				"java.lang.Boolean", "int[][]", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.util.LinkedHashMap", "boolean"
			};

		_methodName361 = "searchCount";

		_methodParameterTypes361 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName362 = "updateAccountEntry";

		_methodParameterTypes362 = new String[] {
				"long", "long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "int", "int", "long", "boolean", "int",
				"int", "java.lang.String", "java.lang.String",
				"java.lang.String[][]", "long[][]", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "java.lang.String"
			};

		_methodName363 = "updateAccountEntryWithWorkflow";

		_methodParameterTypes363 = new String[] {
				"java.lang.String", "com.liferay.osb.model.AccountEntry",
				"com.liferay.osb.model.PartnerEntry",
				"com.liferay.portal.kernel.model.Address", "java.util.List",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName364 = "updateCorpProject";

		_methodParameterTypes364 = new String[] { "long", "long" };

		_methodName365 = "updateInstructions";

		_methodParameterTypes365 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName366 = "updateLastAuditDate";

		_methodParameterTypes366 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String"
			};

		_methodName367 = "updateStatus";

		_methodParameterTypes367 = new String[] { "long" };

		_methodName368 = "updateStatus";

		_methodParameterTypes368 = new String[] {
				"long", "long", "int",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName369 = "updateStatus";

		_methodParameterTypes369 = new String[] {
				"long", "long", "java.lang.String", "int",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName370 = "updateTier";

		_methodParameterTypes370 = new String[] { "long", "long", "int" };

		_methodName371 = "validate";

		_methodParameterTypes371 = new String[] {
				"com.liferay.osb.model.AccountEntry"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AccountEntryLocalServiceUtil.addAccountEntry((com.liferay.osb.model.AccountEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AccountEntryLocalServiceUtil.createAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AccountEntryLocalServiceUtil.deleteAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AccountEntryLocalServiceUtil.deleteAccountEntry((com.liferay.osb.model.AccountEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AccountEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AccountEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AccountEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AccountEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AccountEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AccountEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AccountEntryLocalServiceUtil.fetchAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AccountEntryLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntriesCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateAccountEntry((com.liferay.osb.model.AccountEntry)arguments[0]);
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportRegionAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportRegionAccountEntry(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.AccountEntry)arguments[1]);

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.AccountEntry>)arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			AccountEntryLocalServiceUtil.clearSupportRegionAccountEntries(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportRegionAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportRegionAccountEntry(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.AccountEntry)arguments[1]);

			return null;
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.AccountEntry>)arguments[1]);

			return null;
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportRegionPrimaryKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportRegionAccountEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.AccountEntry>)arguments[3]);
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportRegionAccountEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return AccountEntryLocalServiceUtil.hasSupportRegionAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			return AccountEntryLocalServiceUtil.hasSupportRegionAccountEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			AccountEntryLocalServiceUtil.setSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportTeamAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportTeamAccountEntry(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.AccountEntry)arguments[1]);

			return null;
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.AccountEntry>)arguments[1]);

			return null;
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			AccountEntryLocalServiceUtil.clearSupportTeamAccountEntries(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportTeamAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportTeamAccountEntry(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.AccountEntry)arguments[1]);

			return null;
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.AccountEntry>)arguments[1]);

			return null;
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportTeamPrimaryKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportTeamAccountEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.AccountEntry>)arguments[3]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportTeamAccountEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return AccountEntryLocalServiceUtil.hasSupportTeamAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return AccountEntryLocalServiceUtil.hasSupportTeamAccountEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			AccountEntryLocalServiceUtil.setSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName326.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes326, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName331.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes331, parameterTypes)) {
			return AccountEntryLocalServiceUtil.addAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Long)arguments[7]).longValue(),
				((Boolean)arguments[8]).booleanValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				(java.lang.String)arguments[11],
				(java.lang.String)arguments[12],
				(java.lang.String[])arguments[13], (long[])arguments[14],
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				(java.lang.String)arguments[17],
				(java.lang.String)arguments[18],
				(java.lang.String)arguments[19],
				((Long)arguments[20]).longValue(),
				((Long)arguments[21]).longValue(),
				(java.lang.String)arguments[22]);
		}

		if (_methodName332.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes332, parameterTypes)) {
			return AccountEntryLocalServiceUtil.addAccountEntryWithWorkflow((java.lang.String)arguments[0],
				(com.liferay.osb.model.AccountEntry)arguments[1],
				(com.liferay.osb.model.CorpProject)arguments[2],
				(com.liferay.osb.model.PartnerEntry)arguments[3],
				(com.liferay.portal.kernel.model.Address)arguments[4],
				(com.liferay.osb.model.AccountWorker)arguments[5],
				(java.util.List<com.liferay.osb.model.OrderEntry>)arguments[6],
				(java.util.ArrayList<com.liferay.portal.kernel.model.User>)arguments[7],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[8]);
		}

		if (_methodName333.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes333, parameterTypes)) {
			AccountEntryLocalServiceUtil.addTrialAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName334.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes334, parameterTypes)) {
			AccountEntryLocalServiceUtil.auditAccountEntries();

			return null;
		}

		if (_methodName335.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes335, parameterTypes)) {
			AccountEntryLocalServiceUtil.auditAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName336.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes336, parameterTypes)) {
			return AccountEntryLocalServiceUtil.deleteAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName337.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes337, parameterTypes)) {
			return AccountEntryLocalServiceUtil.fetchCorpProjectAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName338.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes338, parameterTypes)) {
			return AccountEntryLocalServiceUtil.fetchUserTrialAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName339.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes339, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntries((int[])arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName340.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes340, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntries((int[])arguments[0],
				(int[])arguments[1], ((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName341.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes341, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName342.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes342, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntryByCode((java.lang.String)arguments[0]);
		}

		if (_methodName343.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes343, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntryByName((java.lang.String)arguments[0]);
		}

		if (_methodName344.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes344, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getActiveAccountEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName345.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes345, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getPartnerAccountEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName346.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes346, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getRedirectAccountEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName347.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes347, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSecurityPatchAccountEntries((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName348.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes348, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getUserAccountEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName349.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes349, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getUserAccountEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName350.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes350, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getUserAccountEntryIds(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName351.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes351, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getUserAccountEntryNames(((Long)arguments[0]).longValue());
		}

		if (_methodName352.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes352, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getUserActiveAccountEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName353.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes353, parameterTypes)) {
			return AccountEntryLocalServiceUtil.hasValidLicenseAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName354.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes354, parameterTypes)) {
			return AccountEntryLocalServiceUtil.hasValidSupportAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName355.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes355, parameterTypes)) {
			AccountEntryLocalServiceUtil.recalculateHighestSupportResponse(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName356.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes356, parameterTypes)) {
			AccountEntryLocalServiceUtil.reindexAccountEntry(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName357.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes357, parameterTypes)) {
			return AccountEntryLocalServiceUtil.search((java.lang.Long)arguments[0],
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
				(java.lang.String)arguments[15], (int[])arguments[16],
				(java.lang.Boolean)arguments[17], (int[])arguments[18],
				(int[])arguments[19], (java.lang.String)arguments[20],
				(java.lang.String)arguments[21],
				(java.lang.String)arguments[22],
				(java.lang.String)arguments[23], (java.lang.Long)arguments[24],
				(java.lang.Long)arguments[25], (java.lang.String)arguments[26],
				(java.lang.String)arguments[27],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[28],
				((Boolean)arguments[29]).booleanValue(),
				((Integer)arguments[30]).intValue(),
				((Integer)arguments[31]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[32]);
		}

		if (_methodName358.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes358, parameterTypes)) {
			return AccountEntryLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName359.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes359, parameterTypes)) {
			return AccountEntryLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName360.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes360, parameterTypes)) {
			return AccountEntryLocalServiceUtil.searchCount((java.lang.Long)arguments[0],
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
				(java.lang.String)arguments[15], (int[])arguments[16],
				(java.lang.Boolean)arguments[17], (int[])arguments[18],
				(int[])arguments[19], (java.lang.String)arguments[20],
				(java.lang.String)arguments[21],
				(java.lang.String)arguments[22],
				(java.lang.String)arguments[23], (java.lang.Long)arguments[24],
				(java.lang.Long)arguments[25], (java.lang.String)arguments[26],
				(java.lang.String)arguments[27],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[28],
				((Boolean)arguments[29]).booleanValue());
		}

		if (_methodName361.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes361, parameterTypes)) {
			return AccountEntryLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName362.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes362, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Long)arguments[8]).longValue(),
				((Boolean)arguments[9]).booleanValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				(java.lang.String)arguments[12],
				(java.lang.String)arguments[13],
				(java.lang.String[])arguments[14], (long[])arguments[15],
				((Long)arguments[16]).longValue(),
				(java.lang.String)arguments[17],
				(java.lang.String)arguments[18],
				(java.lang.String)arguments[19],
				(java.lang.String)arguments[20],
				(java.lang.String)arguments[21],
				((Long)arguments[22]).longValue(),
				((Long)arguments[23]).longValue(),
				(java.lang.String)arguments[24]);
		}

		if (_methodName363.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes363, parameterTypes)) {
			AccountEntryLocalServiceUtil.updateAccountEntryWithWorkflow((java.lang.String)arguments[0],
				(com.liferay.osb.model.AccountEntry)arguments[1],
				(com.liferay.osb.model.PartnerEntry)arguments[2],
				(com.liferay.portal.kernel.model.Address)arguments[3],
				(java.util.List<com.liferay.osb.model.OrderEntry>)arguments[4],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[5]);

			return null;
		}

		if (_methodName364.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes364, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateCorpProject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName365.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes365, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateInstructions(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName366.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes366, parameterTypes)) {
			AccountEntryLocalServiceUtil.updateLastAuditDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName367.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes367, parameterTypes)) {
			AccountEntryLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName368.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes368, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[3]);
		}

		if (_methodName369.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes369, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[4]);
		}

		if (_methodName370.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes370, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateTier(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName371.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes371, parameterTypes)) {
			AccountEntryLocalServiceUtil.validate((com.liferay.osb.model.AccountEntry)arguments[0]);

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
	private String _methodName326;
	private String[] _methodParameterTypes326;
	private String _methodName331;
	private String[] _methodParameterTypes331;
	private String _methodName332;
	private String[] _methodParameterTypes332;
	private String _methodName333;
	private String[] _methodParameterTypes333;
	private String _methodName334;
	private String[] _methodParameterTypes334;
	private String _methodName335;
	private String[] _methodParameterTypes335;
	private String _methodName336;
	private String[] _methodParameterTypes336;
	private String _methodName337;
	private String[] _methodParameterTypes337;
	private String _methodName338;
	private String[] _methodParameterTypes338;
	private String _methodName339;
	private String[] _methodParameterTypes339;
	private String _methodName340;
	private String[] _methodParameterTypes340;
	private String _methodName341;
	private String[] _methodParameterTypes341;
	private String _methodName342;
	private String[] _methodParameterTypes342;
	private String _methodName343;
	private String[] _methodParameterTypes343;
	private String _methodName344;
	private String[] _methodParameterTypes344;
	private String _methodName345;
	private String[] _methodParameterTypes345;
	private String _methodName346;
	private String[] _methodParameterTypes346;
	private String _methodName347;
	private String[] _methodParameterTypes347;
	private String _methodName348;
	private String[] _methodParameterTypes348;
	private String _methodName349;
	private String[] _methodParameterTypes349;
	private String _methodName350;
	private String[] _methodParameterTypes350;
	private String _methodName351;
	private String[] _methodParameterTypes351;
	private String _methodName352;
	private String[] _methodParameterTypes352;
	private String _methodName353;
	private String[] _methodParameterTypes353;
	private String _methodName354;
	private String[] _methodParameterTypes354;
	private String _methodName355;
	private String[] _methodParameterTypes355;
	private String _methodName356;
	private String[] _methodParameterTypes356;
	private String _methodName357;
	private String[] _methodParameterTypes357;
	private String _methodName358;
	private String[] _methodParameterTypes358;
	private String _methodName359;
	private String[] _methodParameterTypes359;
	private String _methodName360;
	private String[] _methodParameterTypes360;
	private String _methodName361;
	private String[] _methodParameterTypes361;
	private String _methodName362;
	private String[] _methodParameterTypes362;
	private String _methodName363;
	private String[] _methodParameterTypes363;
	private String _methodName364;
	private String[] _methodParameterTypes364;
	private String _methodName365;
	private String[] _methodParameterTypes365;
	private String _methodName366;
	private String[] _methodParameterTypes366;
	private String _methodName367;
	private String[] _methodParameterTypes367;
	private String _methodName368;
	private String[] _methodParameterTypes368;
	private String _methodName369;
	private String[] _methodParameterTypes369;
	private String _methodName370;
	private String[] _methodParameterTypes370;
	private String _methodName371;
	private String[] _methodParameterTypes371;
}