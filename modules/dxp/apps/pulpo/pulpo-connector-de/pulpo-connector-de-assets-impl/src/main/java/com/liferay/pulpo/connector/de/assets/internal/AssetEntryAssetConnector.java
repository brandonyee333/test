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

package com.liferay.pulpo.connector.de.assets.internal;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.lcs.messaging.MessageBusMessage;
import com.liferay.osb.lcs.messaging.LCSMessageBusService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pulpo.connector.de.assets.AssetConnector;
import com.liferay.pulpo.connector.de.assets.lcs.DestinationNames;
import com.liferay.pulpo.connector.de.assets.properties.provider.AssetConnectorPropertiesProvider;
import com.liferay.pulpo.connector.de.assets.properties.provider.registry.AssetConnectorPropertiesProviderRegistry;
import com.liferay.pulpo.connector.de.assets.url.provider.AssetConnectorURLProvider;
import com.liferay.pulpo.connector.de.assets.url.provider.registry.AssetConnectorURLProviderRegistry;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo Garcia
 */
@Component(
	immediate = true,
	service = {AssetConnector.class, AssetEntryAssetConnector.class}
)
public class AssetEntryAssetConnector implements AssetConnector<AssetEntry> {

	public static final String PULPO_ASSET_CONNECTOR_ENVIRONMENT_UNIQUENAME =
		"PULPO_ASSET_CONNECTOR_ENVIRONMENT_UNIQUENAME";

	@Override
	public void add(AssetEntry assetEntry) {
		JSONObject assetEntryJSONObject = _getAssetEntryJSONObject(assetEntry);

		String destinationName =
			DestinationNames.ADD_ENTRY + "_" + _getEnvironmentUniqueName();

		_sendMessage(destinationName, assetEntryJSONObject.toString());
	}

	@Override
	public void add(List<AssetEntry> assetEntries) {
		if (assetEntries.isEmpty()) {
			return;
		}

		JSONArray assetEntriesJSONArray = _getAssetEntriesJSONArray(
			assetEntries);

		String destinationName =
			DestinationNames.ADD_ENTRIES + "_" + _getEnvironmentUniqueName();

		_sendMessage(destinationName, assetEntriesJSONArray.toString());
	}

	@Override
	public void delete(AssetEntry assetEntry) {
		JSONObject assetEntryJSONObject = _getAssetEntryJSONObject(assetEntry);

		String destinationName =
			DestinationNames.DELETE_ENTRY + "_" + _getEnvironmentUniqueName();

		_sendMessage(destinationName, assetEntryJSONObject.toString());
	}

	@Override
	public void update(AssetEntry assetEntry) {
		JSONObject assetEntryJSONObject = _getAssetEntryJSONObject(assetEntry);

		String destinationName =
			DestinationNames.UPDATE_ENTRY + "_" + _getEnvironmentUniqueName();

		_sendMessage(destinationName, assetEntryJSONObject.toString());
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private String _getAddEntryDestinationName() {
		return DestinationNames.ADD_ENTRY + "_" + _getEnvironmentUniqueName();
	}

	private JSONArray _getAssetEntriesJSONArray(List<AssetEntry> assetEntries) {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (AssetEntry assetEntry : assetEntries) {
			 jsonArray.put(_getAssetEntryJSONObject(assetEntry));
		}

		return jsonArray;
	}

	private JSONObject _getAssetEntryJSONObject(AssetEntry assetEntry) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("assetType", assetEntry.getClassName());
		jsonObject.put("author", assetEntry.getUserName());
		jsonObject.put("defaultLanguageId", assetEntry.getDefaultLanguageId());
		jsonObject.put("description", assetEntry.getDescriptionMap());
		jsonObject.put("instanceId", assetEntry.getCompanyId());
		jsonObject.put("internalId", assetEntry.getEntryId());
		jsonObject.put("title", assetEntry.getTitleMap());
		jsonObject.put("url", _getAssetEntryURL(assetEntry));

		JSONArray propertiesJSONArray = JSONFactoryUtil.createJSONArray();

		AssetConnectorPropertiesProvider assetConnectorPropertiesProvider =
			_assetConnectorPropertiesProviderRegistry.getPropertiesProvider(
				assetEntry.getClassName());

		if (assetConnectorPropertiesProvider != null) {
			propertiesJSONArray =
				assetConnectorPropertiesProvider.getProperties(assetEntry);
		}

		jsonObject.put("properties", propertiesJSONArray);

		JSONArray tagsJSONArray = JSONFactoryUtil.createJSONArray();

		for (String tagName : assetEntry.getTagNames()) {
			tagsJSONArray.put(tagName);
		}

		jsonObject.put("tags", tagsJSONArray);

		return jsonObject;
	}

	private String _getAssetEntryURL(AssetEntry assetEntry) {
		AssetConnectorURLProvider assetConnectorURLProvider =
			_assetConnectorURLProviderRegistry.getURLProvider(
				assetEntry.getClassName());

		if (assetConnectorURLProvider != null) {
			return assetConnectorURLProvider.getURL(assetEntry);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext == null) {
			return StringPool.BLANK;
		}

		AssetRenderer assetRenderer = assetEntry.getAssetRenderer();

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		String url = StringPool.BLANK;

		if (themeDisplay != null) {
			url = assetRenderer.getURLDownload(themeDisplay);
		}

		LiferayPortletRequest liferayPortletRequest =
			serviceContext.getLiferayPortletRequest();
		LiferayPortletResponse liferayPortletResponse =
			serviceContext.getLiferayPortletResponse();

		if (Validator.isNull(url) && (liferayPortletRequest != null) &&
			(liferayPortletResponse != null)) {

			try {
				url = assetRenderer.getURLViewInContext(
					serviceContext.getLiferayPortletRequest(),
					serviceContext.getLiferayPortletResponse(),
					StringPool.BLANK);
			}
			catch (Exception e) {
				_log.error("Unable to get asset view url", e);
			}
		}

		return url;
	}

	private String _getEnvironmentUniqueName() {
		String environmentUniqueName = System.getenv(
			PULPO_ASSET_CONNECTOR_ENVIRONMENT_UNIQUENAME);

		if (Validator.isNull(environmentUniqueName)) {
			throw new RuntimeException(
				String.format(
					"Environment variable %s must be set",
					PULPO_ASSET_CONNECTOR_ENVIRONMENT_UNIQUENAME));
		}

		return environmentUniqueName;
	}

	private void _sendMessage(String destinationName, String payload) {
		MessageBusMessage messageBusMessage = new MessageBusMessage();

		messageBusMessage.setPayload(payload);

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Sending message %s to destination %s", payload,
					destinationName));
		}

		_lcsMessageBusService.sendMessage(destinationName, messageBusMessage);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetEntryAssetConnector.class);

	@Reference
	private AssetConnectorPropertiesProviderRegistry
		_assetConnectorPropertiesProviderRegistry;

	@Reference
	private AssetConnectorURLProviderRegistry
		_assetConnectorURLProviderRegistry;

	@Reference
	private LCSMessageBusService _lcsMessageBusService;

}