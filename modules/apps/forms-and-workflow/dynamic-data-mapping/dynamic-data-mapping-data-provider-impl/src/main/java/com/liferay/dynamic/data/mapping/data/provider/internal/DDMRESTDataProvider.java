/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.data.provider.internal;

import com.liferay.dynamic.data.mapping.data.provider.DDMDataProvider;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderContext;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderException;
import com.liferay.portal.kernel.cache.MultiVMPool;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, property = "ddm.data.provider.type=rest")
public class DDMRESTDataProvider implements DDMDataProvider {

	@Override
	public List<KeyValuePair> getData(
			DDMDataProviderContext ddmDataProviderContext)
		throws DDMDataProviderException {

		try {
			return doGetData(ddmDataProviderContext);
		}
		catch (PortalException pe) {
			throw new DDMDataProviderException(pe);
		}
	}

	@Override
	public Class<?> getSettings() {
		return DDMRESTDataProviderSettings.class;
	}

	protected List<KeyValuePair> doGetData(
			DDMDataProviderContext ddmDataProviderContext)
		throws PortalException {

		DDMRESTDataProviderSettings ddmRESTDataProviderSettings =
			ddmDataProviderContext.getSettingsInstance(
				DDMRESTDataProviderSettings.class);

		HttpRequest httpRequest = HttpRequest.get(
			ddmRESTDataProviderSettings.url());

		if (Validator.isNotNull(ddmRESTDataProviderSettings.username())) {
			httpRequest.basicAuthentication(
				ddmRESTDataProviderSettings.username(),
				ddmRESTDataProviderSettings.password());
		}

		httpRequest.query(ddmDataProviderContext.getParameters());

		String cacheKey = getCacheKey(httpRequest);

		DDMRESTDataProviderResult ddmRESTDataProviderResult = _portalCache.get(
			cacheKey);

		if ((ddmRESTDataProviderResult != null) &&
			ddmRESTDataProviderSettings.cacheable()) {

			return ddmRESTDataProviderResult.getKeyValuePairs();
		}

		HttpResponse httpResponse = httpRequest.send();

		httpResponse.charset("UTF-8");

		String responseBodyText = _removeUTFBOM(httpResponse.bodyText());

		JSONArray jsonArray = _jsonFactory.createJSONArray(responseBodyText);

		List<KeyValuePair> results = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			String key = jsonObject.getString(
				ddmRESTDataProviderSettings.key());
			String value = jsonObject.getString(
				ddmRESTDataProviderSettings.value());

			results.add(new KeyValuePair(key, value));
		}

		if (ddmRESTDataProviderSettings.cacheable()) {
			_portalCache.put(cacheKey, new DDMRESTDataProviderResult(results));
		}

		return results;
	}

	protected String getCacheKey(HttpRequest httpRequest) {
		return httpRequest.url();
	}

	@Reference(unbind = "-")
	protected void setJSONFactory(JSONFactory jsonFactory) {
		_jsonFactory = jsonFactory;
	}

	@Reference(unbind = "-")
	protected void setMultiVMPool(MultiVMPool multiVMPool) {
		_portalCache =
			(PortalCache<String, DDMRESTDataProviderResult>)
				multiVMPool.getPortalCache(DDMRESTDataProvider.class.getName());
	}

	private String _removeUTFBOM(String bodyText) {
		for (int i = 0; i < bodyText.length(); i++) {
			if ((bodyText.charAt(i) == '[') || (bodyText.charAt(i) == '{')) {
				return bodyText.substring(i);
			}
		}

		return "";
	}

	private JSONFactory _jsonFactory;
	private PortalCache<String, DDMRESTDataProviderResult> _portalCache;

	private static class DDMRESTDataProviderResult implements Serializable {

		public DDMRESTDataProviderResult(List<KeyValuePair> keyValuePairs) {
			_keyValuePairs = keyValuePairs;
		}

		public List<KeyValuePair> getKeyValuePairs() {
			return _keyValuePairs;
		}

		private final List<KeyValuePair> _keyValuePairs;

	}

}