/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.loop.asset.entry.set.internal.handler;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.osb.loop.asset.entry.set.constants.AssetEntrySetConstants;
import com.liferay.osb.loop.asset.entry.set.internal.configuration.AssetEntrySetServiceConfigurationValues;
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.asset.entry.set.util.AssetEntrySetImage;
import com.liferay.osb.loop.asset.entry.set.util.AssetEntrySetParticipantInfo;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Matthew Kong
 * @author Timothy Bell
 */
@Component(immediate = true, service = AssetEntrySetHandler.class)
public class AssetEntrySetHandler {

	public JSONObject interpret(
			long userId, long assetEntrySetId, JSONObject oldPayloadJSONObject,
			JSONObject payloadJSONObject)
		throws PortalException {

		JSONObject jsonObject = _interpret(
			userId, oldPayloadJSONObject, payloadJSONObject);

		JSONArray imageDataJSONArray = payloadJSONObject.getJSONArray(
			"imageData");

		if (imageDataJSONArray == null) {
			return jsonObject;
		}

		Set<Long> assetEntryIds = new HashSet<>();
		JSONArray processedImageDataJSONArray = _jsonFactory.createJSONArray();

		String[] assetTagNames = StringUtil.split(
			payloadJSONObject.getString(
				AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES));

		for (int i = 0; i < imageDataJSONArray.length(); i++) {
			JSONObject processedImageJSONObject =
				_jsonFactory.createJSONObject();

			JSONObject imageJSONObject = imageDataJSONArray.getJSONObject(i);

			for (String imageType :
					AssetEntrySetServiceConfigurationValues.
						ASSET_ENTRY_SET_IMAGE_TYPES) {

				long fileEntryId = imageJSONObject.getLong(imageType);

				DLFileEntry dlFileEntry = _dlFileEntryLocalService.getFileEntry(
					fileEntryId);

				dlFileEntry.setClassPK(assetEntrySetId);

				dlFileEntry = _dlFileEntryLocalService.updateDLFileEntry(
					dlFileEntry);

				AssetEntry assetEntry = _updateAssetEntry(
					dlFileEntry, assetTagNames);

				assetEntryIds.add(assetEntry.getEntryId());

				FileEntry fileEntry =
					_portletFileRepository.getPortletFileEntry(fileEntryId);

				processedImageJSONObject =
					_assetEntrySetImage.getImageJSONObject(
						processedImageJSONObject, fileEntry, imageType);
			}

			processedImageDataJSONArray.put(processedImageJSONObject);
		}

		jsonObject.put("assetEntryIds", StringUtil.merge(assetEntryIds));
		jsonObject.put("imageData", processedImageDataJSONArray);

		return jsonObject;
	}

	private JSONArray _dedupeSharedToJSONArray(JSONArray sharedToJSONArray) {
		Map<Long, List<Long>> entityClassNameIds = new HashMap<>();

		JSONArray newSharedToJSONArray = _jsonFactory.createJSONArray();

		for (int i = 0; i < sharedToJSONArray.length(); i++) {
			JSONObject sharedToJSONObject = sharedToJSONArray.getJSONObject(i);

			long entityClassNameId = sharedToJSONObject.getLong(
				"entityClassNameId");

			List<Long> entityClassPKs = entityClassNameIds.get(
				entityClassNameId);

			if (entityClassPKs == null) {
				entityClassPKs = new ArrayList<>();
			}

			long entityClassPK = sharedToJSONObject.getLong("entityClassPK");

			if (entityClassPKs.contains(entityClassPK)) {
				continue;
			}

			entityClassPKs.add(entityClassPK);

			entityClassNameIds.put(entityClassNameId, entityClassPKs);

			newSharedToJSONArray.put(sharedToJSONObject);
		}

		return newSharedToJSONArray;
	}

	private JSONArray _filterSharedToJSONArray(JSONArray sharedToJSONArray)
		throws PortalException {

		JSONArray newSharedToJSONArray = _jsonFactory.createJSONArray();

		for (int i = 0; i < sharedToJSONArray.length(); i++) {
			JSONObject sharedToJSONObject = sharedToJSONArray.getJSONObject(i);

			JSONObject newSharedToJSONObject = _jsonFactory.createJSONObject(
				sharedToJSONObject.toString());

			Iterator<String> itr = sharedToJSONObject.keys();

			while (itr.hasNext()) {
				String key = itr.next();

				if (!ArrayUtil.contains(
						AssetEntrySetServiceConfigurationValues.
							ASSET_ENTRY_SET_SHARED_TO_JSON_OBJECT_KEYS,
						key)) {

					newSharedToJSONObject.remove(key);
				}
			}

			newSharedToJSONArray.put(newSharedToJSONObject);
		}

		return newSharedToJSONArray;
	}

	private JSONObject _getGeolocationJSONObject(JSONObject payloadJSONObject) {
		if (Validator.isNull(
				AssetEntrySetServiceConfigurationValues.GEONAMES_URL)) {

			return _jsonFactory.createJSONObject();
		}

		JSONObject geolocationJSONObject = payloadJSONObject.getJSONObject(
			"geolocation");

		if (geolocationJSONObject == null) {
			return _jsonFactory.createJSONObject();
		}

		double latitude = geolocationJSONObject.getDouble("latitude");
		double longitude = geolocationJSONObject.getDouble("longitude");

		geolocationJSONObject.put(
			"locationName", _getLocationName(latitude, longitude));

		geolocationJSONObject.remove("latitude");
		geolocationJSONObject.remove("longitude");

		return geolocationJSONObject;
	}

	private String _getLocationName(double latitude, double longitude) {
		try {
			String url = _http.addParameter(
				AssetEntrySetServiceConfigurationValues.GEONAMES_URL,
				"latitude", latitude);

			url = _http.addParameter(url, "longitude", longitude);

			JSONObject geoNamesJSONObject = _jsonFactory.createJSONObject(
				_http.URLtoString(url));

			String name = geoNamesJSONObject.getString("name");

			String countryCode = geoNamesJSONObject.getString("countryCode");

			Country country = _countryService.fetchCountryByA2(countryCode);

			if (country == null) {
				return name;
			}

			return name + StringPool.COMMA_AND_SPACE +
				country.getName(LocaleUtil.getDefault());
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	private JSONObject _interpret(
			long userId, JSONObject oldPayloadJSONObject,
			JSONObject payloadJSONObject)
		throws PortalException {

		JSONObject jsonObject = _jsonFactory.createJSONObject();

		if (oldPayloadJSONObject == null) {
			jsonObject.put(
				"geolocation", _getGeolocationJSONObject(payloadJSONObject));
		}
		else {
			if (_isContentModified(oldPayloadJSONObject, payloadJSONObject)) {
				jsonObject.put(
					"contentModifiedTime",
					Long.valueOf(System.currentTimeMillis()));
			}

			jsonObject.put(
				"geolocation",
				oldPayloadJSONObject.getJSONObject("geolocation"));
		}

		jsonObject.put("linkData", payloadJSONObject.getJSONObject("linkData"));
		jsonObject.put("message", payloadJSONObject.getString("message"));
		jsonObject.put("rawMessage", payloadJSONObject.getString("rawMessage"));
		jsonObject.put(
			"sendEmailNotifications",
			payloadJSONObject.getBoolean("sendEmailNotifications"));
		jsonObject.put("truncated", payloadJSONObject.getBoolean("truncated"));

		String truncatedMessage = payloadJSONObject.getString(
			"truncatedMessage");

		if (Validator.isNotNull(truncatedMessage)) {
			jsonObject.put("truncatedMessage", truncatedMessage);
		}

		jsonObject.put("type", payloadJSONObject.getString("type"));
		jsonObject.put("userAgent", payloadJSONObject.getString("userAgent"));

		JSONArray sharedToJSONArray = payloadJSONObject.getJSONArray(
			AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO);

		if (sharedToJSONArray != null) {
			String[] assetTagNames = StringUtil.split(
				payloadJSONObject.getString(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES));

			JSONArray assetTagsJSONArray =
				_assetEntrySetParticipantInfo.getAssetTagsJSONArray(
					userId, assetTagNames);

			for (int i = 0; i < assetTagsJSONArray.length(); i++) {
				sharedToJSONArray.put(assetTagsJSONArray.getJSONObject(i));
			}

			sharedToJSONArray = _processSharedToJSONArray(sharedToJSONArray);

			jsonObject.put(
				AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO,
				sharedToJSONArray);
		}

		return jsonObject;
	}

	private boolean _isContentModified(
		JSONObject oldPayloadJSONObject, JSONObject newPayloadJSONObject) {

		Iterator<String> keys = oldPayloadJSONObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			if (key.equals(
					AssetEntrySetConstants.PAYLOAD_KEY_ASSET_TAG_NAMES) ||
				key.equals(AssetEntrySetConstants.PAYLOAD_KEY_SHARED_TO)) {

				continue;
			}

			String oldValue = oldPayloadJSONObject.getString(key);
			String newValue = newPayloadJSONObject.getString(key);

			if (!Objects.equals(oldValue, newValue)) {
				return true;
			}
		}

		return false;
	}

	private JSONArray _processSharedToJSONArray(JSONArray sharedToJSONArray)
		throws PortalException {

		JSONArray newSharedToJSONArray = _dedupeSharedToJSONArray(
			sharedToJSONArray);

		return _filterSharedToJSONArray(newSharedToJSONArray);
	}

	private AssetEntry _updateAssetEntry(
			DLFileEntry dlFileEntry, String[] assetTagNames)
		throws PortalException {

		Group group = _groupLocalService.getCompanyGroup(
			dlFileEntry.getCompanyId());

		return _assetEntryLocalService.updateEntry(
			dlFileEntry.getUserId(), group.getGroupId(),
			AssetEntrySet.class.getName(), dlFileEntry.getFileEntryId(), null,
			assetTagNames);
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private AssetEntrySetImage _assetEntrySetImage;

	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile AssetEntrySetParticipantInfo _assetEntrySetParticipantInfo;

	@Reference
	private CountryService _countryService;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Http _http;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private PortletFileRepository _portletFileRepository;

}