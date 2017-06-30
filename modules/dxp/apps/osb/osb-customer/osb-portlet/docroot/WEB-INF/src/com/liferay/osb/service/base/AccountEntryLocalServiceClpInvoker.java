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

import com.liferay.osb.service.AccountEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
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

		_methodName9 = "fetchAccountEntry";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getAccountEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getAccountEntries";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getAccountEntriesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateAccountEntry";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.AccountEntry"
			};

		_methodName15 = "updateAccountEntry";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.AccountEntry", "boolean"
			};

		_methodName16 = "addSupportRegionAccountEntry";

		_methodParameterTypes16 = new String[] { "long", "long" };

		_methodName17 = "addSupportRegionAccountEntry";

		_methodParameterTypes17 = new String[] {
				"long", "com.liferay.osb.model.AccountEntry"
			};

		_methodName18 = "addSupportRegionAccountEntries";

		_methodParameterTypes18 = new String[] { "long", "long[][]" };

		_methodName19 = "addSupportRegionAccountEntries";

		_methodParameterTypes19 = new String[] { "long", "java.util.List" };

		_methodName20 = "clearSupportRegionAccountEntries";

		_methodParameterTypes20 = new String[] { "long" };

		_methodName21 = "deleteSupportRegionAccountEntry";

		_methodParameterTypes21 = new String[] { "long", "long" };

		_methodName22 = "deleteSupportRegionAccountEntry";

		_methodParameterTypes22 = new String[] {
				"long", "com.liferay.osb.model.AccountEntry"
			};

		_methodName23 = "deleteSupportRegionAccountEntries";

		_methodParameterTypes23 = new String[] { "long", "long[][]" };

		_methodName24 = "deleteSupportRegionAccountEntries";

		_methodParameterTypes24 = new String[] { "long", "java.util.List" };

		_methodName25 = "getSupportRegionAccountEntries";

		_methodParameterTypes25 = new String[] { "long" };

		_methodName26 = "getSupportRegionAccountEntries";

		_methodParameterTypes26 = new String[] { "long", "int", "int" };

		_methodName27 = "getSupportRegionAccountEntries";

		_methodParameterTypes27 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName28 = "getSupportRegionAccountEntriesCount";

		_methodParameterTypes28 = new String[] { "long" };

		_methodName29 = "hasSupportRegionAccountEntry";

		_methodParameterTypes29 = new String[] { "long", "long" };

		_methodName30 = "hasSupportRegionAccountEntries";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "setSupportRegionAccountEntries";

		_methodParameterTypes31 = new String[] { "long", "long[][]" };

		_methodName32 = "addSupportTeamAccountEntry";

		_methodParameterTypes32 = new String[] { "long", "long" };

		_methodName33 = "addSupportTeamAccountEntry";

		_methodParameterTypes33 = new String[] {
				"long", "com.liferay.osb.model.AccountEntry"
			};

		_methodName34 = "addSupportTeamAccountEntries";

		_methodParameterTypes34 = new String[] { "long", "long[][]" };

		_methodName35 = "addSupportTeamAccountEntries";

		_methodParameterTypes35 = new String[] { "long", "java.util.List" };

		_methodName36 = "clearSupportTeamAccountEntries";

		_methodParameterTypes36 = new String[] { "long" };

		_methodName37 = "deleteSupportTeamAccountEntry";

		_methodParameterTypes37 = new String[] { "long", "long" };

		_methodName38 = "deleteSupportTeamAccountEntry";

		_methodParameterTypes38 = new String[] {
				"long", "com.liferay.osb.model.AccountEntry"
			};

		_methodName39 = "deleteSupportTeamAccountEntries";

		_methodParameterTypes39 = new String[] { "long", "long[][]" };

		_methodName40 = "deleteSupportTeamAccountEntries";

		_methodParameterTypes40 = new String[] { "long", "java.util.List" };

		_methodName41 = "getSupportTeamAccountEntries";

		_methodParameterTypes41 = new String[] { "long" };

		_methodName42 = "getSupportTeamAccountEntries";

		_methodParameterTypes42 = new String[] { "long", "int", "int" };

		_methodName43 = "getSupportTeamAccountEntries";

		_methodParameterTypes43 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName44 = "getSupportTeamAccountEntriesCount";

		_methodParameterTypes44 = new String[] { "long" };

		_methodName45 = "hasSupportTeamAccountEntry";

		_methodParameterTypes45 = new String[] { "long", "long" };

		_methodName46 = "hasSupportTeamAccountEntries";

		_methodParameterTypes46 = new String[] { "long" };

		_methodName47 = "setSupportTeamAccountEntries";

		_methodParameterTypes47 = new String[] { "long", "long[][]" };

		_methodName736 = "getBeanIdentifier";

		_methodParameterTypes736 = new String[] {  };

		_methodName737 = "setBeanIdentifier";

		_methodParameterTypes737 = new String[] { "java.lang.String" };

		_methodName742 = "addAccountEntry";

		_methodParameterTypes742 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "int", "int", "long", "boolean", "int",
				"int", "java.lang.String", "java.lang.String",
				"java.lang.String[][]", "long[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "java.lang.String"
			};

		_methodName743 = "addAccountEntryWithWorkflow";

		_methodParameterTypes743 = new String[] {
				"java.lang.String", "com.liferay.osb.model.AccountEntry",
				"com.liferay.osb.model.CorpProject",
				"com.liferay.osb.model.PartnerEntry",
				"com.liferay.portal.model.Address",
				"com.liferay.osb.model.AccountWorker", "java.util.List",
				"java.util.ArrayList",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName744 = "addTrialAccountEntry";

		_methodParameterTypes744 = new String[] { "long", "long" };

		_methodName745 = "auditAccountEntries";

		_methodParameterTypes745 = new String[] {  };

		_methodName746 = "auditAccountEntry";

		_methodParameterTypes746 = new String[] { "long", "long" };

		_methodName747 = "deleteAccountEntry";

		_methodParameterTypes747 = new String[] { "long" };

		_methodName748 = "fetchCorpProjectAccountEntry";

		_methodParameterTypes748 = new String[] { "long" };

		_methodName749 = "fetchUserTrialAccountEntry";

		_methodParameterTypes749 = new String[] { "long" };

		_methodName750 = "getAccountEntries";

		_methodParameterTypes750 = new String[] { "int[][]", "int", "int" };

		_methodName751 = "getAccountEntries";

		_methodParameterTypes751 = new String[] {
				"int[][]", "int[][]", "int", "int"
			};

		_methodName752 = "getAccountEntry";

		_methodParameterTypes752 = new String[] { "long" };

		_methodName753 = "getAccountEntryByCode";

		_methodParameterTypes753 = new String[] { "java.lang.String" };

		_methodName754 = "getAccountEntryByName";

		_methodParameterTypes754 = new String[] { "java.lang.String" };

		_methodName755 = "getActiveAccountEntries";

		_methodParameterTypes755 = new String[] { "int", "int" };

		_methodName756 = "getPartnerAccountEntries";

		_methodParameterTypes756 = new String[] { "long" };

		_methodName757 = "getRedirectAccountEntries";

		_methodParameterTypes757 = new String[] { "long" };

		_methodName758 = "getSecurityPatchAccountEntries";

		_methodParameterTypes758 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName759 = "getUserAccountEntries";

		_methodParameterTypes759 = new String[] { "long", "int", "int" };

		_methodName760 = "getUserAccountEntriesCount";

		_methodParameterTypes760 = new String[] { "long" };

		_methodName761 = "getUserAccountEntryIds";

		_methodParameterTypes761 = new String[] { "long", "int", "int" };

		_methodName762 = "getUserAccountEntryNames";

		_methodParameterTypes762 = new String[] { "long" };

		_methodName763 = "getUserActiveAccountEntries";

		_methodParameterTypes763 = new String[] { "long", "int", "int" };

		_methodName764 = "hasValidLicenseAccountEntry";

		_methodParameterTypes764 = new String[] { "long" };

		_methodName765 = "hasValidSupportAccountEntry";

		_methodParameterTypes765 = new String[] { "long" };

		_methodName766 = "recalculateHighestSupportResponse";

		_methodParameterTypes766 = new String[] { "long" };

		_methodName767 = "reindexAccountEntry";

		_methodParameterTypes767 = new String[] { "long" };

		_methodName768 = "search";

		_methodParameterTypes768 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int[][]",
				"java.lang.Boolean", "int[][]", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName769 = "search";

		_methodParameterTypes769 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName770 = "search";

		_methodParameterTypes770 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName771 = "searchCount";

		_methodParameterTypes771 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int[][]",
				"java.lang.Boolean", "int[][]", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.util.LinkedHashMap", "boolean"
			};

		_methodName772 = "searchCount";

		_methodParameterTypes772 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName773 = "updateAccountEntry";

		_methodParameterTypes773 = new String[] {
				"long", "long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "int", "int", "long", "boolean", "int",
				"int", "java.lang.String", "java.lang.String",
				"java.lang.String[][]", "long[][]", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "java.lang.String"
			};

		_methodName774 = "updateAccountEntryWithWorkflow";

		_methodParameterTypes774 = new String[] {
				"java.lang.String", "com.liferay.osb.model.AccountEntry",
				"com.liferay.osb.model.PartnerEntry",
				"com.liferay.portal.model.Address", "java.util.List",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName775 = "updateCorpProject";

		_methodParameterTypes775 = new String[] { "long", "long" };

		_methodName776 = "updateInstructions";

		_methodParameterTypes776 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName777 = "updateLastAuditDate";

		_methodParameterTypes777 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String"
			};

		_methodName778 = "updateStatus";

		_methodParameterTypes778 = new String[] { "long" };

		_methodName779 = "updateStatus";

		_methodParameterTypes779 = new String[] {
				"long", "long", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName780 = "updateStatus";

		_methodParameterTypes780 = new String[] {
				"long", "long", "java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName781 = "updateTier";

		_methodParameterTypes781 = new String[] { "long", "long", "int" };

		_methodName782 = "validate";

		_methodParameterTypes782 = new String[] {
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
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AccountEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AccountEntryLocalServiceUtil.fetchAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntriesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateAccountEntry((com.liferay.osb.model.AccountEntry)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateAccountEntry((com.liferay.osb.model.AccountEntry)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportRegionAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportRegionAccountEntry(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.AccountEntry)arguments[1]);

			return null;
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.AccountEntry>)arguments[1]);

			return null;
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			AccountEntryLocalServiceUtil.clearSupportRegionAccountEntries(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportRegionAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportRegionAccountEntry(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.AccountEntry)arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.AccountEntry>)arguments[1]);

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportRegionAccountEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportRegionAccountEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return AccountEntryLocalServiceUtil.hasSupportRegionAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return AccountEntryLocalServiceUtil.hasSupportRegionAccountEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			AccountEntryLocalServiceUtil.setSupportRegionAccountEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportTeamAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportTeamAccountEntry(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.AccountEntry)arguments[1]);

			return null;
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			AccountEntryLocalServiceUtil.addSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.AccountEntry>)arguments[1]);

			return null;
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			AccountEntryLocalServiceUtil.clearSupportTeamAccountEntries(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportTeamAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportTeamAccountEntry(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.AccountEntry)arguments[1]);

			return null;
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			AccountEntryLocalServiceUtil.deleteSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.AccountEntry>)arguments[1]);

			return null;
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportTeamAccountEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSupportTeamAccountEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return AccountEntryLocalServiceUtil.hasSupportTeamAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return AccountEntryLocalServiceUtil.hasSupportTeamAccountEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			AccountEntryLocalServiceUtil.setSupportTeamAccountEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName736.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes736, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName737.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes737, parameterTypes)) {
			AccountEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName742.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes742, parameterTypes)) {
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

		if (_methodName743.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes743, parameterTypes)) {
			return AccountEntryLocalServiceUtil.addAccountEntryWithWorkflow((java.lang.String)arguments[0],
				(com.liferay.osb.model.AccountEntry)arguments[1],
				(com.liferay.osb.model.CorpProject)arguments[2],
				(com.liferay.osb.model.PartnerEntry)arguments[3],
				(com.liferay.portal.model.Address)arguments[4],
				(com.liferay.osb.model.AccountWorker)arguments[5],
				(java.util.List<com.liferay.osb.model.OrderEntry>)arguments[6],
				(java.util.ArrayList<com.liferay.portal.model.User>)arguments[7],
				(com.liferay.portal.service.ServiceContext)arguments[8]);
		}

		if (_methodName744.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes744, parameterTypes)) {
			AccountEntryLocalServiceUtil.addTrialAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName745.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes745, parameterTypes)) {
			AccountEntryLocalServiceUtil.auditAccountEntries();

			return null;
		}

		if (_methodName746.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes746, parameterTypes)) {
			AccountEntryLocalServiceUtil.auditAccountEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName747.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes747, parameterTypes)) {
			return AccountEntryLocalServiceUtil.deleteAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName748.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes748, parameterTypes)) {
			return AccountEntryLocalServiceUtil.fetchCorpProjectAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName749.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes749, parameterTypes)) {
			return AccountEntryLocalServiceUtil.fetchUserTrialAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName750.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes750, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntries((int[])arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName751.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes751, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntries((int[])arguments[0],
				(int[])arguments[1], ((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName752.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes752, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName753.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes753, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntryByCode((java.lang.String)arguments[0]);
		}

		if (_methodName754.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes754, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getAccountEntryByName((java.lang.String)arguments[0]);
		}

		if (_methodName755.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes755, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getActiveAccountEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName756.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes756, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getPartnerAccountEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName757.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes757, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getRedirectAccountEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName758.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes758, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getSecurityPatchAccountEntries((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName759.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes759, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getUserAccountEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName760.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes760, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getUserAccountEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName761.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes761, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getUserAccountEntryIds(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName762.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes762, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getUserAccountEntryNames(((Long)arguments[0]).longValue());
		}

		if (_methodName763.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes763, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getUserActiveAccountEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName764.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes764, parameterTypes)) {
			return AccountEntryLocalServiceUtil.hasValidLicenseAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName765.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes765, parameterTypes)) {
			return AccountEntryLocalServiceUtil.hasValidSupportAccountEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName766.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes766, parameterTypes)) {
			AccountEntryLocalServiceUtil.recalculateHighestSupportResponse(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName767.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes767, parameterTypes)) {
			AccountEntryLocalServiceUtil.reindexAccountEntry(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName768.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes768, parameterTypes)) {
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

		if (_methodName769.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes769, parameterTypes)) {
			return AccountEntryLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName770.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes770, parameterTypes)) {
			return AccountEntryLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName771.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes771, parameterTypes)) {
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

		if (_methodName772.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes772, parameterTypes)) {
			return AccountEntryLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName773.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes773, parameterTypes)) {
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

		if (_methodName774.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes774, parameterTypes)) {
			AccountEntryLocalServiceUtil.updateAccountEntryWithWorkflow((java.lang.String)arguments[0],
				(com.liferay.osb.model.AccountEntry)arguments[1],
				(com.liferay.osb.model.PartnerEntry)arguments[2],
				(com.liferay.portal.model.Address)arguments[3],
				(java.util.List<com.liferay.osb.model.OrderEntry>)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);

			return null;
		}

		if (_methodName775.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes775, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateCorpProject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName776.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes776, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateInstructions(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName777.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes777, parameterTypes)) {
			AccountEntryLocalServiceUtil.updateLastAuditDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3]);

			return null;
		}

		if (_methodName778.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes778, parameterTypes)) {
			AccountEntryLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName779.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes779, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName780.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes780, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName781.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes781, parameterTypes)) {
			return AccountEntryLocalServiceUtil.updateTier(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName782.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes782, parameterTypes)) {
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
	private String _methodName767;
	private String[] _methodParameterTypes767;
	private String _methodName768;
	private String[] _methodParameterTypes768;
	private String _methodName769;
	private String[] _methodParameterTypes769;
	private String _methodName770;
	private String[] _methodParameterTypes770;
	private String _methodName771;
	private String[] _methodParameterTypes771;
	private String _methodName772;
	private String[] _methodParameterTypes772;
	private String _methodName773;
	private String[] _methodParameterTypes773;
	private String _methodName774;
	private String[] _methodParameterTypes774;
	private String _methodName775;
	private String[] _methodParameterTypes775;
	private String _methodName776;
	private String[] _methodParameterTypes776;
	private String _methodName777;
	private String[] _methodParameterTypes777;
	private String _methodName778;
	private String[] _methodParameterTypes778;
	private String _methodName779;
	private String[] _methodParameterTypes779;
	private String _methodName780;
	private String[] _methodParameterTypes780;
	private String _methodName781;
	private String[] _methodParameterTypes781;
	private String _methodName782;
	private String[] _methodParameterTypes782;
}