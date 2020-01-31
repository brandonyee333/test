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

package com.liferay.osb.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.AccountEntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link AccountEntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryServiceSoap
 * @see HttpPrincipal
 * @see AccountEntryServiceUtil
 * @generated
 */
@ProviderType
public class AccountEntryServiceHttp {
	public static com.liferay.osb.model.AccountEntry deleteAccountEntry(
		HttpPrincipal httpPrincipal, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEntryServiceUtil.class,
					"deleteAccountEntry", _deleteAccountEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.AccountEntry fetchCorpProjectAccountEntry(
		HttpPrincipal httpPrincipal, java.lang.String corpProjectUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEntryServiceUtil.class,
					"fetchCorpProjectAccountEntry",
					_fetchCorpProjectAccountEntryParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					corpProjectUuid);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.AccountEntry fetchCorpProjectIdAccountEntry(
		HttpPrincipal httpPrincipal, long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEntryServiceUtil.class,
					"fetchCorpProjectIdAccountEntry",
					_fetchCorpProjectIdAccountEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					corpProjectId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		HttpPrincipal httpPrincipal, java.lang.String userUuid,
		long[] productEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEntryServiceUtil.class,
					"getAccountEntries", _getAccountEntriesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					userUuid, productEntryIds);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.osb.model.AccountEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.AccountEntry getAccountEntry(
		HttpPrincipal httpPrincipal, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEntryServiceUtil.class,
					"getAccountEntry", _getAccountEntryParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.AccountEntry getAccountEntryByCode(
		HttpPrincipal httpPrincipal, java.lang.String code)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEntryServiceUtil.class,
					"getAccountEntryByCode",
					_getAccountEntryByCodeParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, code);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.AccountEntry getCorpProjectAccountEntry(
		HttpPrincipal httpPrincipal, java.lang.String corpProjectUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEntryServiceUtil.class,
					"getCorpProjectAccountEntry",
					_getCorpProjectAccountEntryParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					corpProjectUuid);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.model.AccountEntry> search(
		HttpPrincipal httpPrincipal, java.lang.Long createUserId,
		int createDateGTDay, int createDateGTMonth, int createDateGTYear,
		int createDateLTDay, int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		java.lang.String dossieraAccountKey, java.lang.String corpEntryName,
		java.lang.String name, java.lang.String code, int[] industries,
		java.lang.Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String partnerEntryCode, java.lang.String street,
		java.lang.Long countryId, java.lang.Long regionId,
		java.lang.String city, java.lang.String zip,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEntryServiceUtil.class,
					"search", _searchParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					createUserId, createDateGTDay, createDateGTMonth,
					createDateGTYear, createDateLTDay, createDateLTMonth,
					createDateLTYear, modifiedUserId, modifiedDateGTDay,
					modifiedDateGTMonth, modifiedDateGTYear, modifiedDateLTDay,
					modifiedDateLTMonth, modifiedDateLTYear,
					dossieraAccountKey, corpEntryName, name, code, industries,
					partnerManagedSupport, tiers, statuses, instructions,
					notes, partnerEntryCode, street, countryId, regionId, city,
					zip, params, andOperator, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.osb.model.AccountEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCount(HttpPrincipal httpPrincipal,
		java.lang.Long createUserId, int createDateGTDay,
		int createDateGTMonth, int createDateGTYear, int createDateLTDay,
		int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		java.lang.String dossieraAccountKey, java.lang.String corpEntryName,
		java.lang.String name, java.lang.String code, int[] industries,
		java.lang.Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String partnerEntryCode, java.lang.String street,
		java.lang.Long countryId, java.lang.Long regionId,
		java.lang.String city, java.lang.String zip,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEntryServiceUtil.class,
					"searchCount", _searchCountParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					createUserId, createDateGTDay, createDateGTMonth,
					createDateGTYear, createDateLTDay, createDateLTMonth,
					createDateLTYear, modifiedUserId, modifiedDateGTDay,
					modifiedDateGTMonth, modifiedDateGTYear, modifiedDateLTDay,
					modifiedDateLTMonth, modifiedDateLTYear,
					dossieraAccountKey, corpEntryName, name, code, industries,
					partnerManagedSupport, tiers, statuses, instructions,
					notes, partnerEntryCode, street, countryId, regionId, city,
					zip, params, andOperator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.AccountEntry updateInstructions(
		HttpPrincipal httpPrincipal, long accountEntryId,
		java.lang.String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEntryServiceUtil.class,
					"updateInstructions", _updateInstructionsParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountEntryId, instructions);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AccountEntryServiceHttp.class);
	private static final Class<?>[] _deleteAccountEntryParameterTypes0 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchCorpProjectAccountEntryParameterTypes1 =
		new Class[] { java.lang.String.class };
	private static final Class<?>[] _fetchCorpProjectIdAccountEntryParameterTypes2 =
		new Class[] { long.class };
	private static final Class<?>[] _getAccountEntriesParameterTypes3 = new Class[] {
			java.lang.String.class, long[].class
		};
	private static final Class<?>[] _getAccountEntryParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getAccountEntryByCodeParameterTypes5 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _getCorpProjectAccountEntryParameterTypes6 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _searchParameterTypes7 = new Class[] {
			java.lang.Long.class, int.class, int.class, int.class, int.class,
			int.class, int.class, java.lang.Long.class, int.class, int.class,
			int.class, int.class, int.class, int.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, int[].class, java.lang.Boolean.class,
			int[].class, int[].class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.Long.class, java.lang.Long.class,
			java.lang.String.class, java.lang.String.class,
			java.util.LinkedHashMap.class, boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchCountParameterTypes8 = new Class[] {
			java.lang.Long.class, int.class, int.class, int.class, int.class,
			int.class, int.class, java.lang.Long.class, int.class, int.class,
			int.class, int.class, int.class, int.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, int[].class, java.lang.Boolean.class,
			int[].class, int[].class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.Long.class, java.lang.Long.class,
			java.lang.String.class, java.lang.String.class,
			java.util.LinkedHashMap.class, boolean.class
		};
	private static final Class<?>[] _updateInstructionsParameterTypes9 = new Class[] {
			long.class, java.lang.String.class
		};
}