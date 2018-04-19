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

import com.liferay.osb.service.LicenseKeyServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link LicenseKeyServiceUtil} service utility. The
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
 * @see LicenseKeyServiceSoap
 * @see HttpPrincipal
 * @see LicenseKeyServiceUtil
 * @generated
 */
@ProviderType
public class LicenseKeyServiceHttp {
	public static com.liferay.osb.model.LicenseKey addLicenseKey(
		HttpPrincipal httpPrincipal, long userId, long licenseKeySetId,
		java.lang.String name, long offeringEntryId, long licenseEntryId,
		long productEntryId, int productVersion, long clusterId,
		java.lang.String owner, int maxServers, int maxHttpSessions,
		java.lang.String description, java.lang.String[] hostNames,
		java.lang.String[] ipAddresses, java.lang.String[] macAddresses,
		java.lang.String[] serverIds, int startDateMonth, int startDateDay,
		int startDateYear, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"addLicenseKey", _addLicenseKeyParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					licenseKeySetId, name, offeringEntryId, licenseEntryId,
					productEntryId, productVersion, clusterId, owner,
					maxServers, maxHttpSessions, description, hostNames,
					ipAddresses, macAddresses, serverIds, startDateMonth,
					startDateDay, startDateYear, complimentary, active);

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

			return (com.liferay.osb.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.LicenseKey addLicenseKey(
		HttpPrincipal httpPrincipal, java.lang.String userUuid,
		java.lang.String assetReceiptLicenseUuid,
		java.lang.String licenseEntryType, java.lang.String productEntryName,
		java.lang.String productId, int productVersion, java.lang.String owner,
		long maxUsers, java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddresses, java.lang.String macAddresses,
		java.lang.String serverId, java.util.Date startDate,
		java.util.Date expirationDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"addLicenseKey", _addLicenseKeyParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					userUuid, assetReceiptLicenseUuid, licenseEntryType,
					productEntryName, productId, productVersion, owner,
					maxUsers, description, hostName, ipAddresses, macAddresses,
					serverId, startDate, expirationDate);

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

			return (com.liferay.osb.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getAssetReceiptLicenseLicenseKeys(
		HttpPrincipal httpPrincipal, java.lang.String assetReceiptLicenseUuid,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"getAssetReceiptLicenseLicenseKeys",
					_getAssetReceiptLicenseLicenseKeysParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					assetReceiptLicenseUuid, complimentary, active);

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

			return (java.util.List<com.liferay.osb.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getAssetReceiptLicenseLicenseKeysCount(
		HttpPrincipal httpPrincipal, java.lang.String assetReceiptLicenseUuid,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"getAssetReceiptLicenseLicenseKeysCount",
					_getAssetReceiptLicenseLicenseKeysCountParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					assetReceiptLicenseUuid, complimentary, active);

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

	public static com.liferay.osb.model.LicenseKey getLicenseKey(
		HttpPrincipal httpPrincipal, long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"getLicenseKey", _getLicenseKeyParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					licenseKeyId);

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

			return (com.liferay.osb.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.LicenseKey getLicenseKey(
		HttpPrincipal httpPrincipal, java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"getLicenseKey", _getLicenseKeyParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, uuid);

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

			return (com.liferay.osb.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		HttpPrincipal httpPrincipal, long userId, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"getLicenseKeys", _getLicenseKeysParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					productId);

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

			return (java.util.List<com.liferay.osb.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeySetLicenseKeys(
		HttpPrincipal httpPrincipal, long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"getLicenseKeySetLicenseKeys",
					_getLicenseKeySetLicenseKeysParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					licenseKeySetId);

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

			return (java.util.List<com.liferay.osb.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryGroupLicenseKeys(
		HttpPrincipal httpPrincipal, long[] offeringEntryIds,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"getOfferingEntryGroupLicenseKeys",
					_getOfferingEntryGroupLicenseKeysParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					offeringEntryIds, complimentary, active, start, end, obc);

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

			return (java.util.List<com.liferay.osb.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getOfferingEntryGroupLicenseKeysCount(
		HttpPrincipal httpPrincipal, long[] offeringEntryIds,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"getOfferingEntryGroupLicenseKeysCount",
					_getOfferingEntryGroupLicenseKeysCountParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					offeringEntryIds, complimentary, active);

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

	public static com.liferay.osb.model.LicenseKey renewLicenseKey(
		HttpPrincipal httpPrincipal, long licenseKeyId,
		java.util.Date startDate, int renewTime) throws java.lang.Exception {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"renewLicenseKey", _renewLicenseKeyParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					licenseKeyId, startDate, renewTime);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof java.lang.Exception) {
					throw (java.lang.Exception)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.LicenseKey renewLicenseKey(
		HttpPrincipal httpPrincipal, java.lang.String uuid,
		java.util.Date startDate, java.util.Date expirationDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"renewLicenseKey", _renewLicenseKeyParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey, uuid,
					startDate, expirationDate);

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

			return (com.liferay.osb.model.LicenseKey)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> search(
		HttpPrincipal httpPrincipal, java.lang.Long createUserId,
		int createDateGTDay, int createDateGTMonth, int createDateGTYear,
		int createDateLTDay, int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		java.lang.String accountEntryName, java.lang.String licenseKeySetName,
		int startDateGTDay, int startDateGTMonth, int startDateGTYear,
		int startDateLTDay, int startDateLTMonth, int startDateLTYear,
		long[] licenseEntryIds, long[] productEntryIds,
		java.lang.String productEntryName, java.lang.String productId,
		int[] productVersions, java.lang.String owner,
		java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddress, java.lang.String macAddress,
		java.lang.String serverId, java.lang.String key,
		int expirationDateGTDay, int expirationDateGTMonth,
		int expirationDateGTYear, int expirationDateLTDay,
		int expirationDateLTMonth, int expirationDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"search", _searchParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					createUserId, createDateGTDay, createDateGTMonth,
					createDateGTYear, createDateLTDay, createDateLTMonth,
					createDateLTYear, modifiedUserId, modifiedDateGTDay,
					modifiedDateGTMonth, modifiedDateGTYear, modifiedDateLTDay,
					modifiedDateLTMonth, modifiedDateLTYear, accountEntryName,
					licenseKeySetName, startDateGTDay, startDateGTMonth,
					startDateGTYear, startDateLTDay, startDateLTMonth,
					startDateLTYear, licenseEntryIds, productEntryIds,
					productEntryName, productId, productVersions, owner,
					description, hostName, ipAddress, macAddress, serverId,
					key, expirationDateGTDay, expirationDateGTMonth,
					expirationDateGTYear, expirationDateLTDay,
					expirationDateLTMonth, expirationDateLTYear, params,
					andSearch, start, end, obc);

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

			return (java.util.List<com.liferay.osb.model.LicenseKey>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> search(
		HttpPrincipal httpPrincipal, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"search", _searchParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					keywords, params, start, end, obc);

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

			return (java.util.List<com.liferay.osb.model.LicenseKey>)returnObj;
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
		java.lang.String accountEntryName, java.lang.String licenseKeySetName,
		int startDateGTDay, int startDateGTMonth, int startDateGTYear,
		int startDateLTDay, int startDateLTMonth, int startDateLTYear,
		long[] licenseEntryIds, long[] productEntryIds,
		java.lang.String productEntryName, java.lang.String productId,
		int[] productVersions, java.lang.String owner,
		java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddress, java.lang.String macAddress,
		java.lang.String serverId, java.lang.String key,
		int expirationDateGTDay, int expirationDateGTMonth,
		int expirationDateGTYear, int expirationDateLTDay,
		int expirationDateLTMonth, int expirationDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"searchCount", _searchCountParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					createUserId, createDateGTDay, createDateGTMonth,
					createDateGTYear, createDateLTDay, createDateLTMonth,
					createDateLTYear, modifiedUserId, modifiedDateGTDay,
					modifiedDateGTMonth, modifiedDateGTYear, modifiedDateLTDay,
					modifiedDateLTMonth, modifiedDateLTYear, accountEntryName,
					licenseKeySetName, startDateGTDay, startDateGTMonth,
					startDateGTYear, startDateLTDay, startDateLTMonth,
					startDateLTYear, licenseEntryIds, productEntryIds,
					productEntryName, productId, productVersions, owner,
					description, hostName, ipAddress, macAddress, serverId,
					key, expirationDateGTDay, expirationDateGTMonth,
					expirationDateGTYear, expirationDateLTDay,
					expirationDateLTMonth, expirationDateLTYear, params,
					andSearch);

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

	public static int searchCount(HttpPrincipal httpPrincipal,
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"searchCount", _searchCountParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					keywords, params);

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

	public static void updateLicenseKey(HttpPrincipal httpPrincipal,
		long userId, long licenseKeyId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"updateLicenseKey", _updateLicenseKeyParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					licenseKeyId, active);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void updateLicenseKey(HttpPrincipal httpPrincipal,
		long userId, long licenseKeyId, long licenseKeySetId,
		long offeringEntryId, java.lang.String name, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"updateLicenseKey", _updateLicenseKeyParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					licenseKeyId, licenseKeySetId, offeringEntryId, name, active);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void updateLicenseKey(HttpPrincipal httpPrincipal,
		java.lang.String userUuid, java.lang.String uuid, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"updateLicenseKey", _updateLicenseKeyParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					userUuid, uuid, active);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void updateLicenseKeys(HttpPrincipal httpPrincipal,
		java.lang.String assetReceiptLicenseUuid, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(LicenseKeyServiceUtil.class,
					"updateLicenseKeys", _updateLicenseKeysParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					assetReceiptLicenseUuid, active);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LicenseKeyServiceHttp.class);
	private static final Class<?>[] _addLicenseKeyParameterTypes0 = new Class[] {
			long.class, long.class, java.lang.String.class, long.class,
			long.class, long.class, int.class, long.class,
			java.lang.String.class, int.class, int.class, java.lang.String.class,
			java.lang.String[].class, java.lang.String[].class,
			java.lang.String[].class, java.lang.String[].class, int.class,
			int.class, int.class, boolean.class, boolean.class
		};
	private static final Class<?>[] _addLicenseKeyParameterTypes1 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, int.class, java.lang.String.class,
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.util.Date.class, java.util.Date.class
		};
	private static final Class<?>[] _getAssetReceiptLicenseLicenseKeysParameterTypes2 =
		new Class[] { java.lang.String.class, boolean.class, boolean.class };
	private static final Class<?>[] _getAssetReceiptLicenseLicenseKeysCountParameterTypes3 =
		new Class[] { java.lang.String.class, boolean.class, boolean.class };
	private static final Class<?>[] _getLicenseKeyParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getLicenseKeyParameterTypes5 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _getLicenseKeysParameterTypes6 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getLicenseKeySetLicenseKeysParameterTypes7 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getOfferingEntryGroupLicenseKeysParameterTypes8 =
		new Class[] {
			long[].class, boolean.class, boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getOfferingEntryGroupLicenseKeysCountParameterTypes9 =
		new Class[] { long[].class, boolean.class, boolean.class };
	private static final Class<?>[] _renewLicenseKeyParameterTypes10 = new Class[] {
			long.class, java.util.Date.class, int.class
		};
	private static final Class<?>[] _renewLicenseKeyParameterTypes11 = new Class[] {
			java.lang.String.class, java.util.Date.class, java.util.Date.class
		};
	private static final Class<?>[] _searchParameterTypes12 = new Class[] {
			java.lang.Long.class, int.class, int.class, int.class, int.class,
			int.class, int.class, java.lang.Long.class, int.class, int.class,
			int.class, int.class, int.class, int.class, java.lang.String.class,
			java.lang.String.class, int.class, int.class, int.class, int.class,
			int.class, int.class, long[].class, long[].class,
			java.lang.String.class, java.lang.String.class, int[].class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, int.class, int.class, int.class, int.class,
			int.class, int.class, java.util.LinkedHashMap.class, boolean.class,
			int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchParameterTypes13 = new Class[] {
			java.lang.String.class, java.util.LinkedHashMap.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchCountParameterTypes14 = new Class[] {
			java.lang.Long.class, int.class, int.class, int.class, int.class,
			int.class, int.class, java.lang.Long.class, int.class, int.class,
			int.class, int.class, int.class, int.class, java.lang.String.class,
			java.lang.String.class, int.class, int.class, int.class, int.class,
			int.class, int.class, long[].class, long[].class,
			java.lang.String.class, java.lang.String.class, int[].class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, int.class, int.class, int.class, int.class,
			int.class, int.class, java.util.LinkedHashMap.class, boolean.class
		};
	private static final Class<?>[] _searchCountParameterTypes15 = new Class[] {
			java.lang.String.class, java.util.LinkedHashMap.class
		};
	private static final Class<?>[] _updateLicenseKeyParameterTypes16 = new Class[] {
			long.class, long.class, boolean.class
		};
	private static final Class<?>[] _updateLicenseKeyParameterTypes17 = new Class[] {
			long.class, long.class, long.class, long.class,
			java.lang.String.class, boolean.class
		};
	private static final Class<?>[] _updateLicenseKeyParameterTypes18 = new Class[] {
			java.lang.String.class, java.lang.String.class, boolean.class
		};
	private static final Class<?>[] _updateLicenseKeysParameterTypes19 = new Class[] {
			java.lang.String.class, boolean.class
		};
}