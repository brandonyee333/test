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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.InvokableLocalService;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountEntryLocalServiceClp implements AccountEntryLocalService {
	public AccountEntryLocalServiceClp(
		InvokableLocalService invokableLocalService) {
		_invokableLocalService = invokableLocalService;

		_methodName0 = "hasSupportRegionAccountEntries";

		_methodParameterTypes0 = new String[] { "long" };

		_methodName1 = "hasSupportRegionAccountEntry";

		_methodParameterTypes1 = new String[] { "long", "long" };

		_methodName2 = "hasValidLicenseAccountEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "hasValidSupportAccountEntry";

		_methodParameterTypes3 = new String[] { "long" };

		_methodName4 = "addAccountEntry";

		_methodParameterTypes4 = new String[] {
				"com.liferay.osb.model.AccountEntry"
			};

		_methodName5 = "addAccountEntry";

		_methodParameterTypes5 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "int", "int", "long",
				"boolean", "int", "int", "java.lang.String", "java.lang.String",
				"java.lang.String[][]", "long[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "java.lang.String"
			};

		_methodName6 = "addAccountEntryWithWorkflow";

		_methodParameterTypes6 = new String[] {
				"java.lang.String", "com.liferay.osb.model.AccountEntry",
				"com.liferay.osb.model.CorpProject",
				"com.liferay.osb.model.PartnerEntry",
				"com.liferay.portal.kernel.model.Address",
				"com.liferay.osb.model.AccountWorker", "java.util.List",
				"java.util.ArrayList",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName7 = "createAccountEntry";

		_methodParameterTypes7 = new String[] { "long" };

		_methodName8 = "deleteAccountEntry";

		_methodParameterTypes8 = new String[] {
				"com.liferay.osb.model.AccountEntry"
			};

		_methodName9 = "deleteAccountEntry";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "fetchAccountEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "fetchCorpProjectAccountEntry";

		_methodParameterTypes11 = new String[] { "java.lang.String" };

		_methodName12 = "fetchCorpProjectAccountEntry";

		_methodParameterTypes12 = new String[] { "long" };

		_methodName13 = "fetchUserTrialAccountEntry";

		_methodParameterTypes13 = new String[] { "long" };

		_methodName14 = "getAccountEntry";

		_methodParameterTypes14 = new String[] { "long" };

		_methodName15 = "getAccountEntryByCode";

		_methodParameterTypes15 = new String[] { "java.lang.String" };

		_methodName16 = "getAccountEntryByName";

		_methodParameterTypes16 = new String[] { "java.lang.String" };

		_methodName17 = "updateAccountEntry";

		_methodParameterTypes17 = new String[] {
				"com.liferay.osb.model.AccountEntry"
			};

		_methodName18 = "updateAccountEntry";

		_methodParameterTypes18 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "int", "int", "long",
				"boolean", "int", "int", "java.lang.String", "java.lang.String",
				"java.lang.String[][]", "long[][]", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "long", "long", "java.lang.String"
			};

		_methodName19 = "updateInstructions";

		_methodParameterTypes19 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName20 = "updateStatus";

		_methodParameterTypes20 = new String[] {
				"long", "long", "int",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName21 = "updateStatus";

		_methodParameterTypes21 = new String[] {
				"long", "long", "java.lang.String", "int",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName22 = "updateTier";

		_methodParameterTypes22 = new String[] { "long", "long", "int" };

		_methodName23 = "getActionableDynamicQuery";

		_methodParameterTypes23 = new String[] {  };

		_methodName24 = "dynamicQuery";

		_methodParameterTypes24 = new String[] {  };

		_methodName25 = "getIndexableActionableDynamicQuery";

		_methodParameterTypes25 = new String[] {  };

		_methodName26 = "deletePersistedModel";

		_methodParameterTypes26 = new String[] {
				"com.liferay.portal.kernel.model.PersistedModel"
			};

		_methodName27 = "getPersistedModel";

		_methodParameterTypes27 = new String[] { "java.io.Serializable" };

		_methodName28 = "getAccountEntriesCount";

		_methodParameterTypes28 = new String[] {  };

		_methodName29 = "getSupportRegionAccountEntriesCount";

		_methodParameterTypes29 = new String[] { "long" };

		_methodName30 = "getUserAccountEntriesCount";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "searchCount";

		_methodParameterTypes31 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int[][]",
				"java.lang.Boolean", "int[][]", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.util.LinkedHashMap", "boolean"
			};

		_methodName32 = "searchCount";

		_methodParameterTypes32 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName34 = "getOSGiServiceIdentifier";

		_methodParameterTypes34 = new String[] {  };

		_methodName35 = "dynamicQuery";

		_methodParameterTypes35 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName36 = "dynamicQuery";

		_methodParameterTypes36 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName37 = "dynamicQuery";

		_methodParameterTypes37 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName38 = "getAccountEntries";

		_methodParameterTypes38 = new String[] { "int", "int" };

		_methodName39 = "getAccountEntries";

		_methodParameterTypes39 = new String[] {
				"int[][]", "int[][]", "int", "int"
			};

		_methodName40 = "getAccountEntries";

		_methodParameterTypes40 = new String[] { "int[][]", "int", "int" };

		_methodName41 = "getActiveAccountEntries";

		_methodParameterTypes41 = new String[] { "int", "int" };

		_methodName42 = "getPartnerAccountEntries";

		_methodParameterTypes42 = new String[] { "long" };

		_methodName43 = "getRedirectAccountEntries";

		_methodParameterTypes43 = new String[] { "long" };

		_methodName44 = "getSecurityPatchAccountEntries";

		_methodParameterTypes44 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName45 = "getSupportRegionAccountEntries";

		_methodParameterTypes45 = new String[] { "long" };

		_methodName46 = "getSupportRegionAccountEntries";

		_methodParameterTypes46 = new String[] { "long", "int", "int" };

		_methodName47 = "getSupportRegionAccountEntries";

		_methodParameterTypes47 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName48 = "getUserAccountEntries";

		_methodParameterTypes48 = new String[] { "long", "int", "int" };

		_methodName49 = "getUserAccountEntryIds";

		_methodParameterTypes49 = new String[] { "long", "int", "int" };

		_methodName50 = "getUserAccountEntryNames";

		_methodParameterTypes50 = new String[] { "long" };

		_methodName51 = "getUserActiveAccountEntries";

		_methodParameterTypes51 = new String[] { "long", "int", "int" };

		_methodName52 = "search";

		_methodParameterTypes52 = new String[] {
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.Long", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "int[][]",
				"java.lang.Boolean", "int[][]", "int[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.Long", "java.lang.Long", "java.lang.String",
				"java.lang.String", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName53 = "search";

		_methodParameterTypes53 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName54 = "search";

		_methodParameterTypes54 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName55 = "dynamicQueryCount";

		_methodParameterTypes55 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName56 = "dynamicQueryCount";

		_methodParameterTypes56 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName57 = "getSupportRegionPrimaryKeys";

		_methodParameterTypes57 = new String[] { "long" };

		_methodName58 = "addSupportRegionAccountEntries";

		_methodParameterTypes58 = new String[] { "long", "java.util.List" };

		_methodName59 = "addSupportRegionAccountEntries";

		_methodParameterTypes59 = new String[] { "long", "long[][]" };

		_methodName60 = "addSupportRegionAccountEntry";

		_methodParameterTypes60 = new String[] {
				"long", "com.liferay.osb.model.AccountEntry"
			};

		_methodName61 = "addSupportRegionAccountEntry";

		_methodParameterTypes61 = new String[] { "long", "long" };

		_methodName62 = "addTrialAccountEntry";

		_methodParameterTypes62 = new String[] { "long" };

		_methodName63 = "auditAccountEntries";

		_methodParameterTypes63 = new String[] {  };

		_methodName64 = "auditAccountEntry";

		_methodParameterTypes64 = new String[] { "long", "long" };

		_methodName65 = "clearSupportRegionAccountEntries";

		_methodParameterTypes65 = new String[] { "long" };

		_methodName66 = "deleteSupportRegionAccountEntries";

		_methodParameterTypes66 = new String[] { "long", "java.util.List" };

		_methodName67 = "deleteSupportRegionAccountEntries";

		_methodParameterTypes67 = new String[] { "long", "long[][]" };

		_methodName68 = "deleteSupportRegionAccountEntry";

		_methodParameterTypes68 = new String[] {
				"long", "com.liferay.osb.model.AccountEntry"
			};

		_methodName69 = "deleteSupportRegionAccountEntry";

		_methodParameterTypes69 = new String[] { "long", "long" };

		_methodName70 = "recalculateHighestSupportResponse";

		_methodParameterTypes70 = new String[] { "long" };

		_methodName71 = "setSupportRegionAccountEntries";

		_methodParameterTypes71 = new String[] { "long", "long[][]" };

		_methodName72 = "updateAccountEntryWithWorkflow";

		_methodParameterTypes72 = new String[] {
				"java.lang.String", "com.liferay.osb.model.AccountEntry",
				"com.liferay.osb.model.PartnerEntry",
				"com.liferay.portal.kernel.model.Address", "java.util.List",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName73 = "updateLastAuditDate";

		_methodParameterTypes73 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String"
			};

		_methodName74 = "updateStatus";

		_methodParameterTypes74 = new String[] { "long" };

		_methodName75 = "validate";

		_methodParameterTypes75 = new String[] {
				"com.liferay.osb.model.AccountEntry"
			};
	}

	@Override
	public boolean hasSupportRegionAccountEntries(long supportRegionId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName0,
					_methodParameterTypes0, new Object[] { supportRegionId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Boolean)returnObj).booleanValue();
	}

	@Override
	public boolean hasSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName1,
					_methodParameterTypes1,
					new Object[] { supportRegionId, accountEntryId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Boolean)returnObj).booleanValue();
	}

	@Override
	public boolean hasValidLicenseAccountEntry(long userId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName2,
					_methodParameterTypes2, new Object[] { userId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Boolean)returnObj).booleanValue();
	}

	@Override
	public boolean hasValidSupportAccountEntry(long userId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName3,
					_methodParameterTypes3, new Object[] { userId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Boolean)returnObj).booleanValue();
	}

	@Override
	public com.liferay.osb.model.AccountEntry addAccountEntry(
		com.liferay.osb.model.AccountEntry accountEntry) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName4,
					_methodParameterTypes4,
					new Object[] { ClpSerializer.translateInput(accountEntry) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry addAccountEntry(long userId,
		java.lang.String corpProjectUuid, java.lang.String corpEntryName,
		java.lang.String name, java.lang.String code, int type, int industry,
		long partnerEntryId, boolean partnerManagedSupport, int tier,
		int maxCustomers, java.lang.String instructions,
		java.lang.String notes, java.lang.String[] languageIds,
		long[] supportRegionIds, java.lang.String street1,
		java.lang.String street2, java.lang.String street3,
		java.lang.String city, java.lang.String zip, long regionId,
		long countryId, java.lang.String ewsaDossieraProjectKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName5,
					_methodParameterTypes5,
					new Object[] {
						userId,
						
					ClpSerializer.translateInput(corpProjectUuid),
						
					ClpSerializer.translateInput(corpEntryName),
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(code),
						
					type,
						
					industry,
						
					partnerEntryId,
						
					partnerManagedSupport,
						
					tier,
						
					maxCustomers,
						
					ClpSerializer.translateInput(instructions),
						
					ClpSerializer.translateInput(notes),
						
					ClpSerializer.translateInput(languageIds),
						
					ClpSerializer.translateInput(supportRegionIds),
						
					ClpSerializer.translateInput(street1),
						
					ClpSerializer.translateInput(street2),
						
					ClpSerializer.translateInput(street3),
						
					ClpSerializer.translateInput(city),
						
					ClpSerializer.translateInput(zip),
						
					regionId,
						
					countryId,
						
					ClpSerializer.translateInput(ewsaDossieraProjectKey)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry addAccountEntryWithWorkflow(
		java.lang.String salesforceOpportunityKey,
		com.liferay.osb.model.AccountEntry accountEntry,
		com.liferay.osb.model.CorpProject corpProject,
		com.liferay.osb.model.PartnerEntry partnerEntry,
		com.liferay.portal.kernel.model.Address address,
		com.liferay.osb.model.AccountWorker accountWorker,
		java.util.List<com.liferay.osb.model.OrderEntry> orderEntries,
		java.util.ArrayList<com.liferay.portal.kernel.model.User> users,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName6,
					_methodParameterTypes6,
					new Object[] {
						ClpSerializer.translateInput(salesforceOpportunityKey),
						
					ClpSerializer.translateInput(accountEntry),
						
					ClpSerializer.translateInput(corpProject),
						
					ClpSerializer.translateInput(partnerEntry),
						
					ClpSerializer.translateInput(address),
						
					ClpSerializer.translateInput(accountWorker),
						
					ClpSerializer.translateInput(orderEntries),
						
					ClpSerializer.translateInput(users),
						
					ClpSerializer.translateInput(serviceContext)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry createAccountEntry(
		long accountEntryId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName7,
					_methodParameterTypes7, new Object[] { accountEntryId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry deleteAccountEntry(
		com.liferay.osb.model.AccountEntry accountEntry) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName8,
					_methodParameterTypes8,
					new Object[] { ClpSerializer.translateInput(accountEntry) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry deleteAccountEntry(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName9,
					_methodParameterTypes9, new Object[] { accountEntryId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry fetchAccountEntry(
		long accountEntryId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName10,
					_methodParameterTypes10, new Object[] { accountEntryId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry fetchCorpProjectAccountEntry(
		java.lang.String corpProjectUuid) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName11,
					_methodParameterTypes11,
					new Object[] { ClpSerializer.translateInput(corpProjectUuid) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry fetchCorpProjectAccountEntry(
		long corpProjectId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName12,
					_methodParameterTypes12, new Object[] { corpProjectId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry fetchUserTrialAccountEntry(
		long userId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName13,
					_methodParameterTypes13, new Object[] { userId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry getAccountEntry(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName14,
					_methodParameterTypes14, new Object[] { accountEntryId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry getAccountEntryByCode(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName15,
					_methodParameterTypes15,
					new Object[] { ClpSerializer.translateInput(code) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry getAccountEntryByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName16,
					_methodParameterTypes16,
					new Object[] { ClpSerializer.translateInput(name) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry updateAccountEntry(
		com.liferay.osb.model.AccountEntry accountEntry) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName17,
					_methodParameterTypes17,
					new Object[] { ClpSerializer.translateInput(accountEntry) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry updateAccountEntry(long userId,
		long accountEntryId, java.lang.String corpProjectUuid,
		java.lang.String corpEntryName, java.lang.String name,
		java.lang.String code, int type, int industry, long partnerEntryId,
		boolean partnerManagedSupport, int tier, int maxCustomers,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String[] languageIds, long[] supportRegionIds,
		long addressId, java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId, java.lang.String ewsaDossieraProjectKey)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName18,
					_methodParameterTypes18,
					new Object[] {
						userId,
						
					accountEntryId,
						
					ClpSerializer.translateInput(corpProjectUuid),
						
					ClpSerializer.translateInput(corpEntryName),
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(code),
						
					type,
						
					industry,
						
					partnerEntryId,
						
					partnerManagedSupport,
						
					tier,
						
					maxCustomers,
						
					ClpSerializer.translateInput(instructions),
						
					ClpSerializer.translateInput(notes),
						
					ClpSerializer.translateInput(languageIds),
						
					ClpSerializer.translateInput(supportRegionIds),
						
					addressId,
						
					ClpSerializer.translateInput(street1),
						
					ClpSerializer.translateInput(street2),
						
					ClpSerializer.translateInput(street3),
						
					ClpSerializer.translateInput(city),
						
					ClpSerializer.translateInput(zip),
						
					regionId,
						
					countryId,
						
					ClpSerializer.translateInput(ewsaDossieraProjectKey)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry updateInstructions(long userId,
		long accountEntryId, java.lang.String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName19,
					_methodParameterTypes19,
					new Object[] {
						userId,
						
					accountEntryId,
						
					ClpSerializer.translateInput(instructions)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry updateStatus(long userId,
		long accountEntryId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName20,
					_methodParameterTypes20,
					new Object[] {
						userId,
						
					accountEntryId,
						
					status,
						
					ClpSerializer.translateInput(serviceContext)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry updateStatus(long userId,
		long accountEntryId, java.lang.String salesforceOpportunityKey,
		int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName21,
					_methodParameterTypes21,
					new Object[] {
						userId,
						
					accountEntryId,
						
					ClpSerializer.translateInput(salesforceOpportunityKey),
						
					status,
						
					ClpSerializer.translateInput(serviceContext)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEntry updateTier(long userId,
		long accountEntryId, int tier)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName22,
					_methodParameterTypes22,
					new Object[] { userId, accountEntryId, tier });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.AccountEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName23,
					_methodParameterTypes23, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName24,
					_methodParameterTypes24, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.dao.orm.DynamicQuery)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName25,
					_methodParameterTypes25, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName26,
					_methodParameterTypes26,
					new Object[] { ClpSerializer.translateInput(persistedModel) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.model.PersistedModel)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName27,
					_methodParameterTypes27,
					new Object[] { ClpSerializer.translateInput(primaryKeyObj) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.model.PersistedModel)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public int getAccountEntriesCount() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName28,
					_methodParameterTypes28, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	@Override
	public int getSupportRegionAccountEntriesCount(long supportRegionId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName29,
					_methodParameterTypes29, new Object[] { supportRegionId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	@Override
	public int getUserAccountEntriesCount(long userId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName30,
					_methodParameterTypes30, new Object[] { userId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	@Override
	public int searchCount(java.lang.Long createUserId, int createDateGTDay,
		int createDateGTMonth, int createDateGTYear, int createDateLTDay,
		int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear, java.lang.String name,
		java.lang.String code, int[] industries,
		java.lang.Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String partnerEntryCode, java.lang.String street,
		java.lang.Long countryId, java.lang.Long regionId,
		java.lang.String city, java.lang.String zip,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName31,
					_methodParameterTypes31,
					new Object[] {
						ClpSerializer.translateInput(createUserId),
						
					createDateGTDay,
						
					createDateGTMonth,
						
					createDateGTYear,
						
					createDateLTDay,
						
					createDateLTMonth,
						
					createDateLTYear,
						
					ClpSerializer.translateInput(modifiedUserId),
						
					modifiedDateGTDay,
						
					modifiedDateGTMonth,
						
					modifiedDateGTYear,
						
					modifiedDateLTDay,
						
					modifiedDateLTMonth,
						
					modifiedDateLTYear,
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(code),
						
					ClpSerializer.translateInput(industries),
						
					ClpSerializer.translateInput(partnerManagedSupport),
						
					ClpSerializer.translateInput(tiers),
						
					ClpSerializer.translateInput(statuses),
						
					ClpSerializer.translateInput(instructions),
						
					ClpSerializer.translateInput(notes),
						
					ClpSerializer.translateInput(partnerEntryCode),
						
					ClpSerializer.translateInput(street),
						
					ClpSerializer.translateInput(countryId),
						
					ClpSerializer.translateInput(regionId),
						
					ClpSerializer.translateInput(city),
						
					ClpSerializer.translateInput(zip),
						
					ClpSerializer.translateInput(params),
						
					andOperator
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	@Override
	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName32,
					_methodParameterTypes32,
					new Object[] {
						ClpSerializer.translateInput(keywords),
						
					ClpSerializer.translateInput(params)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		throw new UnsupportedOperationException();
	}

	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName34,
					_methodParameterTypes34, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName35,
					_methodParameterTypes35,
					new Object[] { ClpSerializer.translateInput(dynamicQuery) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<T>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName36,
					_methodParameterTypes36,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					start,
						
					end
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<T>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName37,
					_methodParameterTypes37,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					start,
						
					end,
						
					ClpSerializer.translateInput(orderByComparator)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<T>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		int start, int end) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName38,
					_methodParameterTypes38, new Object[] { start, end });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		int[] notTypes, int[] statuses, int start, int end) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName39,
					_methodParameterTypes39,
					new Object[] {
						ClpSerializer.translateInput(notTypes),
						
					ClpSerializer.translateInput(statuses),
						
					start,
						
					end
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		int[] statuses, int start, int end) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName40,
					_methodParameterTypes40,
					new Object[] {
						ClpSerializer.translateInput(statuses),
						
					start,
						
					end
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getActiveAccountEntries(
		int start, int end) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName41,
					_methodParameterTypes41, new Object[] { start, end });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getPartnerAccountEntries(
		long partnerEntryId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName42,
					_methodParameterTypes42, new Object[] { partnerEntryId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getRedirectAccountEntries(
		long accountEntryId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName43,
					_methodParameterTypes43, new Object[] { accountEntryId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getSecurityPatchAccountEntries(
		java.lang.String portletId,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName44,
					_methodParameterTypes44,
					new Object[] {
						ClpSerializer.translateInput(portletId),
						
					ClpSerializer.translateInput(params)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName45,
					_methodParameterTypes45, new Object[] { supportRegionId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId, int start, int end) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName46,
					_methodParameterTypes46,
					new Object[] { supportRegionId, start, end });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getSupportRegionAccountEntries(
		long supportRegionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.AccountEntry> orderByComparator) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName47,
					_methodParameterTypes47,
					new Object[] {
						supportRegionId,
						
					start,
						
					end,
						
					ClpSerializer.translateInput(orderByComparator)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getUserAccountEntries(
		long userId, int start, int end) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName48,
					_methodParameterTypes48, new Object[] { userId, start, end });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<java.lang.Long> getUserAccountEntryIds(long userId,
		int start, int end) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName49,
					_methodParameterTypes49, new Object[] { userId, start, end });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<java.lang.Long>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<java.lang.String> getUserAccountEntryNames(
		long userId) throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName50,
					_methodParameterTypes50, new Object[] { userId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<java.lang.String>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> getUserActiveAccountEntries(
		long userId, int start, int end) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName51,
					_methodParameterTypes51, new Object[] { userId, start, end });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> search(
		java.lang.Long createUserId, int createDateGTDay,
		int createDateGTMonth, int createDateGTYear, int createDateLTDay,
		int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear, java.lang.String name,
		java.lang.String code, int[] industries,
		java.lang.Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String partnerEntryCode, java.lang.String street,
		java.lang.Long countryId, java.lang.Long regionId,
		java.lang.String city, java.lang.String zip,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName52,
					_methodParameterTypes52,
					new Object[] {
						ClpSerializer.translateInput(createUserId),
						
					createDateGTDay,
						
					createDateGTMonth,
						
					createDateGTYear,
						
					createDateLTDay,
						
					createDateLTMonth,
						
					createDateLTYear,
						
					ClpSerializer.translateInput(modifiedUserId),
						
					modifiedDateGTDay,
						
					modifiedDateGTMonth,
						
					modifiedDateGTYear,
						
					modifiedDateLTDay,
						
					modifiedDateLTMonth,
						
					modifiedDateLTYear,
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(code),
						
					ClpSerializer.translateInput(industries),
						
					ClpSerializer.translateInput(partnerManagedSupport),
						
					ClpSerializer.translateInput(tiers),
						
					ClpSerializer.translateInput(statuses),
						
					ClpSerializer.translateInput(instructions),
						
					ClpSerializer.translateInput(notes),
						
					ClpSerializer.translateInput(partnerEntryCode),
						
					ClpSerializer.translateInput(street),
						
					ClpSerializer.translateInput(countryId),
						
					ClpSerializer.translateInput(regionId),
						
					ClpSerializer.translateInput(city),
						
					ClpSerializer.translateInput(zip),
						
					ClpSerializer.translateInput(params),
						
					andOperator,
						
					start,
						
					end,
						
					ClpSerializer.translateInput(obc)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName53,
					_methodParameterTypes53,
					new Object[] {
						ClpSerializer.translateInput(keywords),
						
					ClpSerializer.translateInput(params),
						
					start,
						
					end,
						
					ClpSerializer.translateInput(obc)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.AccountEntry> search(
		java.lang.String name, java.lang.String code) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName54,
					_methodParameterTypes54,
					new Object[] {
						ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(code)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.AccountEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName55,
					_methodParameterTypes55,
					new Object[] { ClpSerializer.translateInput(dynamicQuery) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName56,
					_methodParameterTypes56,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					ClpSerializer.translateInput(projection)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	@Override
	public long[] getSupportRegionPrimaryKeys(long accountEntryId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName57,
					_methodParameterTypes57, new Object[] { accountEntryId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (long[])ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public void addSupportRegionAccountEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries) {
		try {
			_invokableLocalService.invokeMethod(_methodName58,
				_methodParameterTypes58,
				new Object[] {
					supportRegionId,
					
				ClpSerializer.translateInput(accountEntries)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void addSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds) {
		try {
			_invokableLocalService.invokeMethod(_methodName59,
				_methodParameterTypes59,
				new Object[] {
					supportRegionId,
					
				ClpSerializer.translateInput(accountEntryIds)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void addSupportRegionAccountEntry(long supportRegionId,
		com.liferay.osb.model.AccountEntry accountEntry) {
		try {
			_invokableLocalService.invokeMethod(_methodName60,
				_methodParameterTypes60,
				new Object[] {
					supportRegionId,
					
				ClpSerializer.translateInput(accountEntry)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void addSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId) {
		try {
			_invokableLocalService.invokeMethod(_methodName61,
				_methodParameterTypes61,
				new Object[] { supportRegionId, accountEntryId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void addTrialAccountEntry(long userId) throws java.lang.Exception {
		try {
			_invokableLocalService.invokeMethod(_methodName62,
				_methodParameterTypes62, new Object[] { userId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.lang.Exception) {
				throw (java.lang.Exception)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void auditAccountEntries()
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName63,
				_methodParameterTypes63, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void auditAccountEntry(long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName64,
				_methodParameterTypes64, new Object[] { userId, accountEntryId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void clearSupportRegionAccountEntries(long supportRegionId) {
		try {
			_invokableLocalService.invokeMethod(_methodName65,
				_methodParameterTypes65, new Object[] { supportRegionId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void deleteSupportRegionAccountEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries) {
		try {
			_invokableLocalService.invokeMethod(_methodName66,
				_methodParameterTypes66,
				new Object[] {
					supportRegionId,
					
				ClpSerializer.translateInput(accountEntries)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void deleteSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds) {
		try {
			_invokableLocalService.invokeMethod(_methodName67,
				_methodParameterTypes67,
				new Object[] {
					supportRegionId,
					
				ClpSerializer.translateInput(accountEntryIds)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void deleteSupportRegionAccountEntry(long supportRegionId,
		com.liferay.osb.model.AccountEntry accountEntry) {
		try {
			_invokableLocalService.invokeMethod(_methodName68,
				_methodParameterTypes68,
				new Object[] {
					supportRegionId,
					
				ClpSerializer.translateInput(accountEntry)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void deleteSupportRegionAccountEntry(long supportRegionId,
		long accountEntryId) {
		try {
			_invokableLocalService.invokeMethod(_methodName69,
				_methodParameterTypes69,
				new Object[] { supportRegionId, accountEntryId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void recalculateHighestSupportResponse(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName70,
				_methodParameterTypes70, new Object[] { accountEntryId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void setSupportRegionAccountEntries(long supportRegionId,
		long[] accountEntryIds) {
		try {
			_invokableLocalService.invokeMethod(_methodName71,
				_methodParameterTypes71,
				new Object[] {
					supportRegionId,
					
				ClpSerializer.translateInput(accountEntryIds)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void updateAccountEntryWithWorkflow(
		java.lang.String salesforceOpportunityKey,
		com.liferay.osb.model.AccountEntry accountEntry,
		com.liferay.osb.model.PartnerEntry partnerEntry,
		com.liferay.portal.kernel.model.Address address,
		java.util.List<com.liferay.osb.model.OrderEntry> orderEntries,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName72,
				_methodParameterTypes72,
				new Object[] {
					ClpSerializer.translateInput(salesforceOpportunityKey),
					
				ClpSerializer.translateInput(accountEntry),
					
				ClpSerializer.translateInput(partnerEntry),
					
				ClpSerializer.translateInput(address),
					
				ClpSerializer.translateInput(orderEntries),
					
				ClpSerializer.translateInput(serviceContext)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void updateLastAuditDate(long userId, long accountEntryId,
		java.lang.String auditLabel, java.lang.String auditValue)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName73,
				_methodParameterTypes73,
				new Object[] {
					userId,
					
				accountEntryId,
					
				ClpSerializer.translateInput(auditLabel),
					
				ClpSerializer.translateInput(auditValue)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void updateStatus(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName74,
				_methodParameterTypes74, new Object[] { accountEntryId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	@Override
	public void validate(com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName75,
				_methodParameterTypes75,
				new Object[] { ClpSerializer.translateInput(accountEntry) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	private InvokableLocalService _invokableLocalService;
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
	private String _methodName54;
	private String[] _methodParameterTypes54;
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName64;
	private String[] _methodParameterTypes64;
	private String _methodName65;
	private String[] _methodParameterTypes65;
	private String _methodName66;
	private String[] _methodParameterTypes66;
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName70;
	private String[] _methodParameterTypes70;
	private String _methodName71;
	private String[] _methodParameterTypes71;
	private String _methodName72;
	private String[] _methodParameterTypes72;
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName74;
	private String[] _methodParameterTypes74;
	private String _methodName75;
	private String[] _methodParameterTypes75;
}