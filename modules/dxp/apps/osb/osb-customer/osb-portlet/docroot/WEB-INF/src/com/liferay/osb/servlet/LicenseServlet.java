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

package com.liferay.osb.servlet;

import com.liferay.osb.exception.LicenseKeyRegistrationException;
import com.liferay.osb.exception.LicenseKeyServerIdException;
import com.liferay.osb.exception.MaximumLicenseKeyException;
import com.liferay.osb.exception.NoSuchOrderEntryException;
import com.liferay.osb.exception.OfferingEntryStatusException;
import com.liferay.osb.license.util.LicenseUtil;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.LicenseEntry;
import com.liferay.osb.model.LicenseEntryConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.LicenseKeyConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.OfferingEntryGroup;
import com.liferay.osb.model.OfferingEntryGroupFactoryUtil;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.util.Encryptor;
/* TODO check dependency import
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.AssetReceipt;
**/

import java.io.IOException;

import java.net.URL;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

import javax.crypto.spec.SecretKeySpec;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Amos Fong
 */
public class LicenseServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		String json = StringUtil.read(request.getInputStream());

		if (Validator.isNull(json)) {
			return;
		}

		SecretKeySpec secretKey = null;

		if (!request.isSecure()) {
			secretKey = getSecretKey(json);

			json = decryptRequest(json, secretKey);

			if (Validator.isNull(json)) {
				return;
			}
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Request " + json);
		}

		JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject();

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);

			String cmd = jsonObject.getString("cmd");

			if (cmd.equals("GET_LICENSE_STATE")) {
				getLicenseState(jsonObject, responseJSONObject);
			}
			else if (cmd.equals("GET_ORDER")) {
				//getOrder(jsonObject, responseJSONObject);
			}
			else if (cmd.equals("QUERY")) {
				query(jsonObject, responseJSONObject);
			}
			else if (cmd.equals("REGISTER")) {
				register(jsonObject, responseJSONObject);
			}
			else if (cmd.equals("VALIDATE")) {
				validate(jsonObject, responseJSONObject);
			}
		}
		catch (LicenseKeyServerIdException lksie) {
			responseJSONObject.put(
				"errorMessage",
				"Server Id matching failed. Please clear your license folder.");
		}
		catch (NoSuchOrderEntryException nsoee) {
			responseJSONObject.put(
				"errorMessage", "Order does not exist. Please check your ID.");
		}
		catch (LicenseKeyRegistrationException lkre) {
			responseJSONObject.put("errorMessage", lkre.getMessage());
		}
		catch (Exception e) {
			_log.error(json, e);

			responseJSONObject.put(
				"errorMessage",
				"Server encountered an error. Please try again.");
		}
		/* TODO check License/Marketplace dependency
		catch (NoSuchAssetReceiptLicenseException nsarle) {
			responseJSONObject.put(
				"errorMessage", "Order does not exist. Please check your ID.");
		}

		*/
		String responseJSON = responseJSONObject.toString();

		if (_log.isDebugEnabled()) {
			_log.debug("Response " + responseJSON);
		}

		ServletOutputStream servletOutputStream = response.getOutputStream();

		if (!request.isSecure()) {
			servletOutputStream.write(encryptResponse(responseJSON, secretKey));
		}
		else {
			servletOutputStream.write(responseJSON.getBytes(StringPool.UTF8));
		}
	}

	protected LicenseKey addClusterLicenseKey(
			long offeringEntryId, long clusterId, String hostName,
			String ipAddresses, String macAddresses, String serverId)
		throws PortalException {

		List<LicenseKey> clusterLicenseKeys =
			LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeys(
				offeringEntryId, clusterId, true);

		if (clusterLicenseKeys.isEmpty()) {
			return null;
		}

		LicenseKey licenseKey = clusterLicenseKeys.get(0);

		Calendar cal = Calendar.getInstance();

		cal.setTime(licenseKey.getCreateDate());

		return LicenseKeyLocalServiceUtil.addLicenseKey(
			licenseKey.getUserId(), licenseKey.getLicenseKeySetId(), null,
			offeringEntryId, licenseKey.getLicenseEntryId(),
			licenseKey.getProductEntryId(), licenseKey.getProductVersion(),
			clusterId, licenseKey.getOwner(), licenseKey.getMaxServers(), 0,
			licenseKey.getDescription(), new String[] {hostName},
			new String[] {ipAddresses}, new String[] {macAddresses},
			new String[] {serverId}, cal.get(Calendar.MONTH),
			cal.get(Calendar.DATE), cal.get(Calendar.YEAR), false, true);
	}

	protected LicenseKey addLicenseKey(
			OrderEntry orderEntry, String productEntryName, boolean cluster,
			int liferayVersion, int maxServers, String hostName,
			String ipAddresses, String macAddresses, String serverId)
		throws PortalException {

		List<OfferingEntry> availableOfferingEntries = new ArrayList<>();

		List<OfferingEntry> offeringEntries = orderEntry.getOfferingEntries();

		for (OfferingEntry offeringEntry : offeringEntries) {
			ProductEntry productEntry = offeringEntry.getProductEntry();

			int environment = productEntry.getEnvironment();

			if ((environment ==
					ProductEntryConstants.ENVIRONMENT_DEVELOPMENT) ||
				(environment == ProductEntryConstants.ENVIRONMENT_NONE)) {

				continue;
			}

			String curProductEntryName = productEntry.getName();

			if (!curProductEntryName.equals(productEntryName)) {
				continue;
			}

			availableOfferingEntries.add(offeringEntry);
		}

		OfferingEntryGroup offeringEntryGroup =
			OfferingEntryGroupFactoryUtil.createOfferingEntryGroup(
				availableOfferingEntries);

		OfferingEntry offeringEntry =
			offeringEntryGroup.getAvailableLicenseOfferingEntry();

		ProductEntry productEntry = offeringEntry.getProductEntry();

		AccountEntry accountEntry = offeringEntry.getAccountEntry();

		long licenseEntryId = getLicenseEntryId(productEntry, cluster);

		int productVersion = getProductVersion(liferayVersion);

		Calendar cal = Calendar.getInstance();

		cal.setTime(orderEntry.getStartDate());

		try {
			return LicenseKeyLocalServiceUtil.addLicenseKey(
				offeringEntry.getUserId(), 0, accountEntry.getName(),
				offeringEntry.getOfferingEntryId(), licenseEntryId, 0,
				productVersion, 0, accountEntry.getName(), maxServers, 0,
				accountEntry.getName(), new String[] {hostName},
				new String[] {ipAddresses}, new String[] {macAddresses},
				new String[] {serverId}, cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE), cal.get(Calendar.YEAR), false, true);
		}
		catch (MaximumLicenseKeyException mlke) {
		}
		catch (OfferingEntryStatusException oese) {
		}

		return null;
	}

	protected String calculateLicensesLeft(
		OfferingEntry offeringEntry, String curLicensesLeft) {

		if (curLicensesLeft.equals("unlimited")) {
			return curLicensesLeft;
		}

		if (offeringEntry.getQuantity() >=
				OfferingEntryConstants.QUANTITY_UNLIMITED) {

			return "unlimited";
		}

		int licensesUsed =
			LicenseKeyLocalServiceUtil.getOfferingEntryLicenseKeysCount(
				offeringEntry.getOfferingEntryId(), false, true);

		int licensesLeft = GetterUtil.getInteger(curLicensesLeft);

		licensesLeft += offeringEntry.getQuantity() - licensesUsed;

		return String.valueOf(licensesLeft);
	}

	protected String decodeServerId(
			String serverIdEncoded, String hostName, String ipAddresses,
			String macAddresses)
		throws Exception {

		if (serverIdEncoded.length() <= 2) {
			return StringPool.BLANK;
		}

		serverIdEncoded = serverIdEncoded.substring(
			1, serverIdEncoded.length() - 1);

		String[] serverIdArray = StringUtil.split(serverIdEncoded);

		byte[] bytes = new byte[serverIdArray.length];

		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = Byte.valueOf(serverIdArray[i].trim());
		}

		Key[] keys = _getKeys();

		for (Key key : keys) {
			bytes = Encryptor.decryptUnencodedAsBytes(key, bytes);
		}

		Properties serverProperties = new Properties();

		PropertiesUtil.load(serverProperties, new String(bytes));

		String serverId = serverProperties.getProperty("serverId");

		byte[] serverIdBytes = (byte[])Base64.stringToObject(serverId);

		for (Key key : _keys) {
			serverIdBytes = Encryptor.decryptUnencodedAsBytes(
				key, serverIdBytes);
		}

		Properties serverIdProperties = new Properties();

		PropertiesUtil.load(serverIdProperties, new String(serverIdBytes));

		String serverIdHostName = GetterUtil.getString(
			serverIdProperties.getProperty("hostName"));

		if (Validator.isNotNull(hostName) &&
			StringUtil.equalsIgnoreCase(serverIdHostName, hostName)) {

			return serverId;
		}

		List<String> serverIdIpAddresses = ListUtil.fromArray(
			StringUtil.split(serverIdProperties.getProperty("ipAddresses")));

		if (Validator.isNotNull(ipAddresses)) {
			serverIdIpAddresses.retainAll(
				ListUtil.fromArray(StringUtil.split(ipAddresses)));

			if (!serverIdIpAddresses.isEmpty()) {
				return serverId;
			}
		}

		List<String> serverIdMacAddresses = ListUtil.fromArray(
			StringUtil.split(serverIdProperties.getProperty("macAddresses")));

		if (Validator.isNotNull(macAddresses)) {
			serverIdMacAddresses.retainAll(
				ListUtil.fromArray(StringUtil.split(macAddresses)));

			if (!serverIdMacAddresses.isEmpty()) {
				return serverId;
			}
		}

		throw new LicenseKeyServerIdException();
	}

	protected String decryptRequest(String json, SecretKeySpec secretKey) {
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);

			String encryptedJSON = jsonObject.getString("content");

			return Encryptor.decryptUnencodedAsString(
				secretKey, (byte[])Base64.stringToObject(encryptedJSON));
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	protected String encodeServerId(String serverId) throws Exception {
		Properties serverProperties = new Properties();

		serverProperties.put("serverId", serverId);

		String serverPropertiesString = PropertiesUtil.toString(
			serverProperties);

		byte[] bytes = serverPropertiesString.getBytes(StringPool.UTF8);

		Key[] keys = _getKeys();

		for (int i = keys.length - 1; i >= 0; i--) {
			bytes = Encryptor.encryptUnencoded(keys[i], bytes);
		}

		return Arrays.toString(bytes);
	}

	protected byte[] encryptResponse(String response, SecretKeySpec secretKey) {
		try {
			return Encryptor.encryptUnencoded(
				secretKey, response.getBytes(StringPool.UTF8));
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return response.getBytes();
	}

	protected long getLicenseEntryId(
		ProductEntry productEntry, boolean cluster) {

		List<LicenseEntry> licenseEntries = productEntry.getLicenseEntries();

		for (LicenseEntry licenseEntry : licenseEntries) {
			String type = licenseEntry.getType();

			if (type.equals(LicenseEntryConstants.TYPE_LIMITED) ||
				type.equals(LicenseEntryConstants.TYPE_PER_USER) ||
				type.equals(LicenseEntryConstants.TYPE_PRODUCTION)) {

				return licenseEntry.getLicenseEntryId();
			}
		}

		return 0;
	}

	protected void getLicenseState(
			JSONObject jsonObject, JSONObject responseJSONObject)
		throws Exception {

		String hostName = jsonObject.getString("hostName");
		String ipAddresses = jsonObject.getString("ipAddresses");
		String macAddresses = jsonObject.getString("macAddresses");
		String productId = jsonObject.getString("productId");
		int productVersion = jsonObject.getInt("productVersion");
		String randomUuid = jsonObject.getString("randomUuid");
		//long userCount = jsonObject.getLong("userCount");
		int version = jsonObject.getInt("version");

		if (Validator.isNull(productId) ||
			productId.equals(ProductEntryConstants.PRODUCT_ID_PORTAL)) {

			return;
		}

		String serverId = StringPool.BLANK;

		if (version == 1) {
			serverId = jsonObject.getString("serverId");
		}
		else if (version == 2) {
			serverId = decodeServerId(
				jsonObject.getString("serverId"), hostName, ipAddresses,
				macAddresses);
		}

		if (Validator.isNull(serverId)) {
			return;
		}

		List<LicenseKey> licenseKeys =
			LicenseKeyLocalServiceUtil.getLicenseKeys(productId, serverId);

		int licenseState = LicenseKeyConstants.STATE_ABSENT;

		for (LicenseKey licenseKey : licenseKeys) {
			if (!licenseKey.isActive()) {
				licenseState = LicenseKeyConstants.STATE_INACTIVE;

				continue;
			}

			if (licenseKey.isExpired()) {
				licenseState = LicenseKeyConstants.STATE_EXPIRED;

				continue;
			}

			/* TODO check License/Marketplace dependency
			AssetReceiptLicense assetReceiptLicense =
				AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicense(
					licenseKey.getAssetReceiptLicenseId());

			int licenseEntryType = assetReceiptLicense.getLicenseType();

			if (licenseEntryType ==
					AssetLicenseConstants.LICENSE_TYPE_PER_USER) {

				if ((licenseKey.getMaxUsers() > 0) &&
					(userCount > licenseKey.getMaxUsers())) {

					licenseState = LicenseKeyConstants.STATE_OVERLOAD;

					continue;
				}
			}

			**/

			if (productVersion > 0) {
				if (licenseKey.getProductVersion() > productVersion) {
					licenseState = LicenseKeyConstants.STATE_INVALID;

					continue;
				}
			}

			licenseState = LicenseKeyConstants.STATE_GOOD;

			break;
		}

		responseJSONObject.put("licenseState", String.valueOf(licenseState));
		responseJSONObject.put("randomUuid", randomUuid);
	}

	/* TODO check License/Marketplace dependency
	protected void getOrder(
			JSONObject jsonObject, JSONObject responseJSONObject)
		throws Exception {

		try {
			String productEntryName = jsonObject.getString("productEntryName");

			if (Validator.isNull(productEntryName)) {
				return;
			}

			String hostName = jsonObject.getString("hostName");
			String ipAddresses = jsonObject.getString("ipAddresses");
			String macAddresses = jsonObject.getString("macAddresses");
			String serverIdEncoded = jsonObject.getString("serverId");

			String serverId = decodeServerId(
				serverIdEncoded, hostName, ipAddresses, macAddresses);

			if (Validator.isNull(serverId)) {
				return;
			}

			List<LicenseKey> licenseKeys =
				LicenseKeyLocalServiceUtil.getLicenseKeysByName(
					productEntryName, serverId, true, 0, 1,
					new LicenseKeyExpirationDateComparator(false));

			if (licenseKeys.isEmpty()) {
				return;
			}

			LicenseKey licenseKey = licenseKeys.get(0);

			AssetReceiptLicense assetReceiptLicense =
				AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicense(
					licenseKey.getAssetReceiptLicenseId());

			responseJSONObject.put("orderUuid", assetReceiptLicense.getUuid());
		}
		catch (NoSuchAssetReceiptLicenseException nsarle) {
			if (!isEEVersion(jsonObject)) {
				throw nsarle;
			}
		}
	}

	*/

	protected PrivateKey getPrivateKey() {
		if (_privateKey != null) {
			return _privateKey;
		}

		try {
			Class<?> clazz = getClass();

			ClassLoader classLoader = clazz.getClassLoader();

			URL url = classLoader.getResource(
				"com/liferay/osb/servlet/private.key");

			byte[] bytes = FileUtil.getBytes(url.openStream());

			EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(bytes);

			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			_privateKey = keyFactory.generatePrivate(encodedKeySpec);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return _privateKey;
	}

	/* TODO AssetReceiptLicense placeholder methods missing
	protected JSONObject getProductsJSONObject(
			AssetReceiptLicense assetReceiptLicense)
		throws PortalException {

		JSONObject productsJSONObject = JSONFactoryUtil.createJSONObject();

		String licensesLeft = StringPool.BLANK;

		if (assetReceiptLicense.hasUnlimitedServers()) {
			licensesLeft = "unlimited";
		}
		else {
			long licenseKeysCount =
				LicenseKeyLocalServiceUtil.
					getAssetReceiptLicenseLicenseKeysCount(
						assetReceiptLicense.getAssetReceiptLicenseId(), false,
						true);

			if ((assetReceiptLicense.getServers() - licenseKeysCount) <= 0) {
				throw new LicenseKeyRegistrationException(
					"Your order does not have any available license keys.");
			}

			licensesLeft = String.valueOf(
				assetReceiptLicense.getServers() - licenseKeysCount);
		}

		AssetEntry assetEntry = assetReceiptLicense.getAssetEntry();

		productsJSONObject.put(assetEntry.getTitle(), licensesLeft);

		return productsJSONObject;
	}

	**/

	protected JSONObject getProductsJSONObject(OrderEntry orderEntry)
		throws PortalException {

		JSONObject productsJSONObject = JSONFactoryUtil.createJSONObject();

		List<OfferingEntry> offeringEntries = orderEntry.getOfferingEntries();

		for (OfferingEntry offeringEntry : offeringEntries) {
			ProductEntry productEntry = offeringEntry.getProductEntry();

			int environment = productEntry.getEnvironment();

			if ((environment ==
					ProductEntryConstants.ENVIRONMENT_DEVELOPMENT) ||
				(environment == ProductEntryConstants.ENVIRONMENT_NONE)) {

				continue;
			}

			String licensesLeft = productsJSONObject.getString(
				productEntry.getName());

			licensesLeft = calculateLicensesLeft(offeringEntry, licensesLeft);

			productsJSONObject.put(productEntry.getName(), licensesLeft);
		}

		Iterator<String> itr = productsJSONObject.keys();

		while (itr.hasNext()) {
			String key = itr.next();

			String licensesLeft = productsJSONObject.getString(key);

			if (!licensesLeft.equals("unlimited") &&
				(GetterUtil.getInteger(licensesLeft) <= 0)) {

				itr.remove();
			}
		}

		if (productsJSONObject.length() <= 0) {
			throw new LicenseKeyRegistrationException(
				"Your order does not have any available license keys.");
		}

		return productsJSONObject;
	}

	protected int getProductVersion(int liferayVersion) {
		if (liferayVersion == 6120) {
			return ProductEntryConstants.PORTAL_VERSION_6_1_20;
		}
		else if (liferayVersion == 6130) {
			return ProductEntryConstants.PORTAL_VERSION_6_1_30;
		}
		else if (liferayVersion == 6210) {
			return ProductEntryConstants.PORTAL_VERSION_6_2_10;
		}

		return ProductEntryConstants.PORTAL_VERSION_6_1_10;
	}

	protected SecretKeySpec getSecretKey(String json) {
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);

			String key = jsonObject.getString("key");

			byte[] bytes = (byte[])Base64.stringToObject(key);

			if ((bytes == null) || (bytes.length <= 0)) {
				return null;
			}

			bytes = Encryptor.decryptUnencodedAsBytes(getPrivateKey(), bytes);

			return new SecretKeySpec(bytes, "AES");
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return null;
	}

	protected boolean isActive(String serverId, String productId, String key) {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("active", true);

		int activeLicensesCount = LicenseKeyLocalServiceUtil.searchCount(
			null, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, null, null, 0, 0, 0,
			0, 0, 0, new long[0], new long[0], null, productId, new int[0],
			null, null, null, null, null, serverId, key, 0, 0, 0, 0, 0, 0,
			params, true);

		if (activeLicensesCount > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isEEVersion(JSONObject jsonObject) {
		int liferayVersion = jsonObject.getInt("liferayVersion");

		if ((liferayVersion % 100) >= 10) {
			return true;
		}
		else {
			return false;
		}
	}

	protected void query(JSONObject jsonObject, JSONObject responseJSONObject)
		throws Exception {

		String orderUuid = jsonObject.getString("orderUuid");

		if (Validator.isNull(orderUuid)) {
			return;
		}

		/* TODO AssetReceiptLicense placeholder methods missing
		try {
			AssetReceiptLicense assetReceiptLicense =
				AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicense(
					orderUuid);

			responseJSONObject.put(
				"productId", assetReceiptLicense.getProductId());

			JSONObject productsJSONObject = getProductsJSONObject(
				assetReceiptLicense);

			responseJSONObject.put("productsJSONObject", productsJSONObject);

			return;
		}
		catch (NoSuchAssetReceiptLicenseException nsarle) {
			if (!isEEVersion(jsonObject)) {
				throw nsarle;
			}
		}

		**/

		OrderEntry orderEntry = OrderEntryLocalServiceUtil.getOrderEntry(
			orderUuid);

		responseJSONObject.put("productId", orderEntry.getUuid());

		JSONObject productsJSONObject = getProductsJSONObject(orderEntry);

		responseJSONObject.put("productsJSONObject", productsJSONObject);
	}

	protected void register(
			JSONObject jsonObject, JSONObject responseJSONObject)
		throws Exception {

		String orderUuid = jsonObject.getString("orderUuid");

		if (Validator.isNull(orderUuid)) {
			return;
		}

		/* TODO AssetReceiptLicense placeholder methods missing
		try {
			AssetReceiptLicense assetReceiptLicense =
				AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicense(
					orderUuid);

			responseJSONObject.put(
				"productId", assetReceiptLicense.getProductId());

			JSONObject productsJSONObject = getProductsJSONObject(
				assetReceiptLicense);

			responseJSONObject.put("productsJSONObject", productsJSONObject);

			registerApp(jsonObject, responseJSONObject, assetReceiptLicense);

			return;
		}
		catch (NoSuchAssetReceiptLicenseException nsarle) {
			if (!isEEVersion(jsonObject)) {
				throw nsarle;
			}
		}

		**/

		OrderEntry orderEntry = OrderEntryLocalServiceUtil.getOrderEntry(
			orderUuid);

		responseJSONObject.put("productId", orderEntry.getUuid());

		JSONObject productsJSONObject = getProductsJSONObject(orderEntry);

		responseJSONObject.put("productsJSONObject", productsJSONObject);

		registerServer(jsonObject, responseJSONObject, orderEntry);
	}

	/* TODO AssetReceiptLicense placeholder methods missing
	protected void registerApp(
			JSONObject jsonObject, JSONObject responseJSONObject,
			AssetReceiptLicense assetReceiptLicense)
		throws Exception {

		if (assetReceiptLicense == null) {
			return;
		}

		AssetReceipt assetReceipt = assetReceiptLicense.getAssetReceipt();
		AssetLicense assetLicense = assetReceiptLicense.getAssetLicense();

		int version = jsonObject.getInt("version");
		String hostName = jsonObject.getString("hostName");
		String ipAddresses = jsonObject.getString("ipAddresses");
		String macAddresses = jsonObject.getString("macAddresses");

		String serverId = StringPool.BLANK;

		if (version == 1) {
			serverId = jsonObject.getString("serverId");
		}
		else if (version == 2) {
			String serverIdEncoded = jsonObject.getString("serverId");

			serverId = decodeServerId(
				serverIdEncoded, hostName, ipAddresses, macAddresses);

			if (Validator.isNull(serverId)) {
				serverId = KeyGenerator.getServerId(
					hostName, ipAddresses, macAddresses);

				responseJSONObject.put("serverId", encodeServerId(serverId));
			}
		}

		validateRegistration(hostName, ipAddresses, macAddresses, serverId);

		Calendar cal = Calendar.getInstance();

		cal.setTime(assetReceiptLicense.getStartDate());

		List<LicenseKey> licenseKeys =
			LicenseKeyLocalServiceUtil.getLicenseKeys(
				assetReceiptLicense.getAssetReceiptLicenseId(),
				assetReceiptLicense.getProductId(), serverId, true, 0, 1,
				new LicenseKeyExpirationDateComparator(false));

		LicenseKey licenseKey = null;

		if (licenseKeys.isEmpty()) {
			licenseKey = LicenseKeyLocalServiceUtil.addLicenseKey(
				assetReceiptLicense.getUserId(), assetReceiptLicense,
				assetReceipt.getFormalLegalEntityName(),
				assetLicense.getLicenseId(), new String[] {hostName},
				new String[] {ipAddresses}, new String[] {macAddresses},
				new String[] {serverId}, cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE), cal.get(Calendar.YEAR));
		}
		else {
			licenseKey = licenseKeys.get(0);
		}

		responseJSONObject.put(
			"licenseXML", LicenseUtil.exportToXML(licenseKey));
	}

	**/

	protected void registerServer(
			JSONObject jsonObject, JSONObject responseJSONObject,
			OrderEntry orderEntry)
		throws Exception {

		if (orderEntry == null) {
			return;
		}

		String productEntryName = jsonObject.getString("productEntryName");

		if (Validator.isNull(productEntryName)) {
			return;
		}

		int liferayVersion = jsonObject.getInt("liferayVersion");
		boolean cluster = jsonObject.getBoolean("cluster");
		int maxServers = jsonObject.getInt("maxServers");
		long offeringEntryId = jsonObject.getLong("offeringEntryId");
		long clusterId = jsonObject.getLong("clusterId");
		String hostName = jsonObject.getString("hostName");
		String ipAddresses = jsonObject.getString("ipAddresses");
		String macAddresses = jsonObject.getString("macAddresses");
		String serverId = jsonObject.getString("serverId");

		validateRegistration(hostName, ipAddresses, macAddresses, serverId);

		LicenseKey licenseKey = null;

		if ((clusterId > 0) && (offeringEntryId > 0)) {
			licenseKey = addClusterLicenseKey(
				offeringEntryId, clusterId, hostName, ipAddresses, macAddresses,
				serverId);
		}
		else {
			licenseKey = addLicenseKey(
				orderEntry, productEntryName, cluster, liferayVersion,
				maxServers, hostName, ipAddresses, macAddresses, serverId);
		}

		if (licenseKey != null) {
			responseJSONObject.put(
				"licenseXML", LicenseUtil.exportToXML(licenseKey));
		}
		else {
			throw new LicenseKeyRegistrationException(
				"Your order has no available " + productEntryName +
					" license keys.");
		}
	}

	protected void validate(
		JSONObject jsonObject, JSONObject responseJSONObject) {

		String serverId = jsonObject.getString("serverId");
		String productId = jsonObject.getString("productId");
		String key = jsonObject.getString("key");
		String randomUuid = jsonObject.getString("randomUuid");

		responseJSONObject.put("randomUuid", randomUuid);

		if (isActive(serverId, productId, key)) {
			responseJSONObject.put("active", true);
		}
		else {
			responseJSONObject.put("active", false);
		}
	}

	protected void validateRegistration(
			String hostName, String ipAddresses, String macAddresses,
			String serverId)
		throws PortalException {

		if (Validator.isNull(hostName)) {
			throw new LicenseKeyRegistrationException(
				"Unable to read host name");
		}

		if (Validator.isNull(ipAddresses)) {
			throw new LicenseKeyRegistrationException(
				"Unable to read IP Address");
		}

		if (Validator.isNull(macAddresses)) {
			throw new LicenseKeyRegistrationException(
				"Unable to read MAC Address");
		}

		if (Validator.isNull(serverId)) {
			throw new LicenseKeyRegistrationException(
				"Unable to read server ID");
		}
	}

	private Key[] _getKeys() {
		if (_keys != null) {
			return _keys;
		}

		_keys = new Key[3];

		try {
			Class<?> clazz = getClass();

			ClassLoader classLoader = clazz.getClassLoader();

			String[] keys = StringUtil.split(
				StringUtil.read(
					classLoader, "com/liferay/osb/admin/util/keys.txt"),
				StringPool.NEW_LINE);

			_keys[0] = (Key)Base64.stringToObject(keys[175]);
			_keys[1] = (Key)Base64.stringToObject(keys[542]);
			_keys[2] = (Key)Base64.stringToObject(keys[706]);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return _keys;
	}

	private static final Log _log = LogFactoryUtil.getLog(LicenseServlet.class);

	private Key[] _keys;
	private PrivateKey _privateKey;

}