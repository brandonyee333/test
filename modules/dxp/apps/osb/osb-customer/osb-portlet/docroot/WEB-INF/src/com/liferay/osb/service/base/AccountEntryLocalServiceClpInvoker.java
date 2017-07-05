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

		_methodName302 = "getOSGiServiceIdentifier";

		_methodParameterTypes302 = new String[] {  };
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

		if (_methodName302.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes302, parameterTypes)) {
			return AccountEntryLocalServiceUtil.getOSGiServiceIdentifier();
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
	private String _methodName302;
	private String[] _methodParameterTypes302;
}