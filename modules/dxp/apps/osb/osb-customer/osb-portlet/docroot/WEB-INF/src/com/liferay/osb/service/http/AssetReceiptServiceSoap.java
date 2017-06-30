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

import com.liferay.osb.service.AssetReceiptServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.osb.service.AssetReceiptServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.model.AssetReceiptSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.model.AssetReceipt}, that is translated to a
 * {@link com.liferay.osb.model.AssetReceiptSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/api/secure/axis. Set the property
 * <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetReceiptServiceHttp
 * @see       com.liferay.osb.model.AssetReceiptSoap
 * @see       com.liferay.osb.service.AssetReceiptServiceUtil
 * @generated
 */
public class AssetReceiptServiceSoap {
	public static com.liferay.osb.model.AssetReceiptSoap fetchAssetReceipt(
		long assetReceiptId) throws RemoteException {
		try {
			com.liferay.osb.model.AssetReceipt returnValue = AssetReceiptServiceUtil.fetchAssetReceipt(assetReceiptId);

			return com.liferay.osb.model.AssetReceiptSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AssetReceiptSoap fetchAssetReceipt(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productClassName, long productClassPK)
		throws RemoteException {
		try {
			com.liferay.osb.model.AssetReceipt returnValue = AssetReceiptServiceUtil.fetchAssetReceipt(ownerClassName,
					ownerClassPK, productClassName, productClassPK);

			return com.liferay.osb.model.AssetReceiptSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AssetReceiptSoap getAssetReceipt(
		long assetReceiptId) throws RemoteException {
		try {
			com.liferay.osb.model.AssetReceipt returnValue = AssetReceiptServiceUtil.getAssetReceipt(assetReceiptId);

			return com.liferay.osb.model.AssetReceiptSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AssetReceiptSoap getAssetReceipt(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productClassName, long productClassPK)
		throws RemoteException {
		try {
			com.liferay.osb.model.AssetReceipt returnValue = AssetReceiptServiceUtil.getAssetReceipt(ownerClassName,
					ownerClassPK, productClassName, productClassPK);

			return com.liferay.osb.model.AssetReceiptSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AssetReceiptSoap purchaseAssets(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String legalEntityName, java.lang.String productClassName,
		long productClassPK, long type, long ecDocumentEntryId, long countryId,
		int domain, boolean validateContractEntries, long eulaContractEntryId,
		long tosContractEntryId) throws RemoteException {
		try {
			com.liferay.osb.model.AssetReceipt returnValue = AssetReceiptServiceUtil.purchaseAssets(ownerClassName,
					ownerClassPK, legalEntityName, productClassName,
					productClassPK, type, ecDocumentEntryId, countryId, domain,
					validateContractEntries, eulaContractEntryId,
					tosContractEntryId);

			return com.liferay.osb.model.AssetReceiptSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AssetReceiptSoap updateAssetReceipt(
		long assetReceiptId, java.lang.String ownerClassName, long ownerClassPK)
		throws RemoteException {
		try {
			com.liferay.osb.model.AssetReceipt returnValue = AssetReceiptServiceUtil.updateAssetReceipt(assetReceiptId,
					ownerClassName, ownerClassPK);

			return com.liferay.osb.model.AssetReceiptSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AssetReceiptServiceSoap.class);
}