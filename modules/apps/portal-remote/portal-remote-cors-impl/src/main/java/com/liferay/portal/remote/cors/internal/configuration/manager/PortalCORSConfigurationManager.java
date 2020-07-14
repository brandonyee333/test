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

package com.liferay.portal.remote.cors.internal.configuration.manager;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.remote.cors.configuration.PortalCORSConfiguration;
import com.liferay.portal.remote.cors.internal.CORSSupport;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.osgi.framework.Constants;
import org.osgi.service.cm.ConfigurationException;
import org.osgi.service.cm.ManagedServiceFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Carlos Sierra Andrés
 * @author Arthur Chan
 */
@Component(
	immediate = true,
	property = Constants.SERVICE_PID + "=com.liferay.portal.remote.cors.configuration.PortalCORSConfiguration",
	service = {
		ManagedServiceFactory.class, PortalCORSConfigurationManager.class
	}
)
public class PortalCORSConfigurationManager implements ManagedServiceFactory {

	@Override
	public void deleted(String pid) {
		Dictionary<String, ?> properties = _configurationPidsProperties.remove(
			pid);

		long companyId = GetterUtil.getLong(properties.get("companyId"));

		if (companyId == CompanyConstants.SYSTEM) {
			_rebuild();
		}
		else {
			_rebuild(companyId);
		}

		if (_configurationPidsProperties.isEmpty()) {
			_rebuildDefault();
		}
	}

	public Map<String, Dictionary<String, ?>> getConfigurationPidsProperties() {
		return _configurationPidsProperties;
	}

	public Map<String, CORSSupport> getExactPatternMatcher(long companyId) {
		return _exactPatternMatchers.get(companyId);
	}

	public Map<String, CORSSupport> getExtensionPatternMatcher(long companyId) {
		return _extensionPatternMatchers.get(companyId);
	}

	@Override
	public String getName() {
		return StringPool.BLANK;
	}

	public Map<String, CORSSupport> getWildcardPatternMatcher(long companyId) {
		return _wildcardPatternMatchers.get(companyId);
	}

	@Override
	public void updated(String pid, Dictionary<String, ?> properties)
		throws ConfigurationException {

		Dictionary<String, ?> oldProperties = _configurationPidsProperties.put(
			pid, properties);

		long companyId = GetterUtil.getLong(
			properties.get("companyId"), CompanyConstants.SYSTEM);

		if (companyId == CompanyConstants.SYSTEM) {
			_rebuild();

			return;
		}

		if (oldProperties != null) {
			long oldCompanyId = GetterUtil.getLong(
				oldProperties.get("companyId"));

			if (oldCompanyId == CompanyConstants.SYSTEM) {
				_rebuild();

				return;
			}

			if (oldCompanyId != companyId) {
				_rebuild(oldCompanyId);
			}
		}

		_rebuild(companyId);
	}

	@Activate
	protected void activate() {
		_rebuildDefault();
	}

	private void _addCORSConfigurationToPatternMatcher(
		PortalCORSConfiguration portalCORSConfiguration,
		Map<String, CORSSupport> exactPatternMatcher,
		Map<String, CORSSupport> extensionPatternMatcher,
		Map<String, CORSSupport> wildcardPatternMatcher) {

		Map<String, String> corsHeaders = CORSSupport.buildCORSHeaders(
			portalCORSConfiguration.headers());

		CORSSupport corsSupport = new CORSSupport();

		corsSupport.setCORSHeaders(corsHeaders);

		for (String pattern :
				portalCORSConfiguration.filterMappingURLPatterns()) {

			if (_isWildcardPattern(pattern)) {
				if (!wildcardPatternMatcher.containsKey(pattern)) {
					wildcardPatternMatcher.put(
						pattern.substring(0), corsSupport);
				}

				continue;
			}

			if (_isExtensionPattern(pattern)) {
				if (!extensionPatternMatcher.containsKey(pattern)) {
					extensionPatternMatcher.put(
						pattern.substring(0), corsSupport);
				}

				continue;
			}

			if (!exactPatternMatcher.containsKey(pattern)) {
				exactPatternMatcher.put(pattern, corsSupport);
			}
		}
	}

	private void _addMatcher(
		Map<String, CORSSupport> exactPatternMatcher,
		Map<String, CORSSupport> extensionPatternMatcher,
		Map<String, CORSSupport> wildcardPatternMatcher, long companyId) {

		_exactPatternMatchers.put(companyId, exactPatternMatcher);
		_extensionPatternMatchers.put(companyId, extensionPatternMatcher);
		_wildcardPatternMatchers.put(companyId, wildcardPatternMatcher);
	}

	/**
	 * A valid ExtensionPattern:
	 * 1. Without the leading '*', it abides by the format of a segment of path
	 *         of URI specification: https://tools.ietf.org/html/rfc3986#section-3.3
	 * 2. It also abides by:
	 *         https://download.oracle.com/otndocs/jcp/servlet-4-final-eval-spec/index.html#12.1.3, and
	 *         https://download.oracle.com/otndocs/jcp/servlet-4-final-eval-spec/index.html#12.2
	 *
	 * @param pathPattern the given pathPattern
	 * @return a boolean value indicating if the pathPattern a valid extensionPattern
	 */
	private boolean _isExtensionPattern(String pathPattern) {
		if ((pathPattern.length() < 3) || (pathPattern.charAt(0) != '*') ||
			(pathPattern.charAt(1) != '.')) {

			return false;
		}

		for (int i = 2; i < pathPattern.length(); ++i) {
			if (pathPattern.charAt(i) == '/') {
				return false;
			}

			if (pathPattern.charAt(i) == '.') {
				return false;
			}
		}

		return true;
	}

	private boolean _isMatcherEmpty(
		Map<String, CORSSupport> exactPatternMatcher,
		Map<String, CORSSupport> extensionPatternMatcher,
		Map<String, CORSSupport> wildcardPatternMatcher) {

		if (exactPatternMatcher.isEmpty() &&
			extensionPatternMatcher.isEmpty() &&
			wildcardPatternMatcher.isEmpty()) {

			return true;
		}

		return false;
	}

	/**
	 * A valid WildcardPattern:
	 * 1. Without the trailing '*', it abides by the format of path of URI specification:
	 *         https://tools.ietf.org/html/rfc3986#section-3.3
	 * 2. It also abides by:
	 *         https://download.oracle.com/otndocs/jcp/servlet-4-final-eval-spec/index.html#12.2
	 *
	 * @param pathPattern the given pathPattern
	 * @return a boolean value indicating if the pathPattern a valid wildCardPattern
	 */
	private boolean _isWildcardPattern(String pathPattern) {
		if ((pathPattern.length() < 2) || (pathPattern.charAt(0) != '/') ||
			(pathPattern.charAt(pathPattern.length() - 1) != '*') ||
			(pathPattern.charAt(pathPattern.length() - 2) != '/')) {

			return false;
		}

		try {
			String path = pathPattern.substring(0, pathPattern.length() - 1);

			URI uri = new URI("https://test" + path);

			if (!path.contentEquals(uri.getPath())) {
				return false;
			}
		}
		catch (URISyntaxException uriSyntaxException) {
			return false;
		}

		return true;
	}

	private void _mergeCORSConfiguration(
		Map<String, CORSSupport> exactPatternMatcher,
		Map<String, CORSSupport> extensionPatternMatcher,
		Map<String, CORSSupport> wildcardPatternMatcher, long companyId) {

		for (Dictionary<String, ?> properties :
				_configurationPidsProperties.values()) {

			if (companyId != GetterUtil.getLong(properties.get("companyId"))) {
				continue;
			}

			PortalCORSConfiguration portalCORSConfiguration =
				ConfigurableUtil.createConfigurable(
					PortalCORSConfiguration.class, properties);

			_addCORSConfigurationToPatternMatcher(
				portalCORSConfiguration, exactPatternMatcher,
				extensionPatternMatcher, wildcardPatternMatcher);
		}
	}

	private void _rebuild() {
		Map<String, CORSSupport> exactPatternMatcher = new HashMap<>();
		Map<String, CORSSupport> extensionPatternMatcher = new HashMap<>();
		Map<String, CORSSupport> wildcardPatternMatcher = new HashMap<>();

		_mergeCORSConfiguration(
			exactPatternMatcher, extensionPatternMatcher,
			wildcardPatternMatcher, CompanyConstants.SYSTEM);

		// A system level matcher is always required even if it's empty

		_addMatcher(
			exactPatternMatcher, extensionPatternMatcher,
			wildcardPatternMatcher, CompanyConstants.SYSTEM);

		for (long companyId : _exactPatternMatchers.keySet()) {
			if (companyId != CompanyConstants.SYSTEM) {
				_rebuild(companyId);
			}
		}
	}

	private void _rebuild(long companyId) {
		Map<String, CORSSupport> exactPatternMatcher = new HashMap<>();
		Map<String, CORSSupport> extensionPatternMatcher = new HashMap<>();
		Map<String, CORSSupport> wildcardPatternMatcher = new HashMap<>();

		// If there are patterns in both instance settings, and system settings,
		// the patterns in instance settings will be used.

		_mergeCORSConfiguration(
			exactPatternMatcher, extensionPatternMatcher,
			wildcardPatternMatcher, companyId);

		if (_isMatcherEmpty(
				exactPatternMatcher, extensionPatternMatcher,
				wildcardPatternMatcher)) {

			_removeMatcher(companyId);

			return;
		}

		// If there are patterns not in instance settings but in system
		// settings, these patterns will also be used.

		_mergeCORSConfiguration(
			exactPatternMatcher, extensionPatternMatcher,
			wildcardPatternMatcher, CompanyConstants.SYSTEM);

		_addMatcher(
			exactPatternMatcher, extensionPatternMatcher,
			wildcardPatternMatcher, companyId);
	}

	/**
	 * Backward compatibility with current portal behavior:
	 * Add default configuration to matcher when there is no entry in
	 * properties map, because empty properties map means no CORS
	 * settings is configured.
	 */
	private void _rebuildDefault() {
		Map<String, CORSSupport> exactPatternMatcher = new HashMap<>();
		Map<String, CORSSupport> extensionPatternMatcher = new HashMap<>();
		Map<String, CORSSupport> wildcardPatternMatcher = new HashMap<>();

		_addCORSConfigurationToPatternMatcher(
			ConfigurableUtil.createConfigurable(
				PortalCORSConfiguration.class, new HashMapDictionary<>()),
			exactPatternMatcher, extensionPatternMatcher,
			wildcardPatternMatcher);

		_addMatcher(
			exactPatternMatcher, extensionPatternMatcher,
			wildcardPatternMatcher, CompanyConstants.SYSTEM);
	}

	private void _removeMatcher(long companyId) {
		_exactPatternMatchers.remove(companyId);
		_extensionPatternMatchers.remove(companyId);
		_wildcardPatternMatchers.remove(companyId);
	}

	private final Map<String, Dictionary<String, ?>>
		_configurationPidsProperties = Collections.synchronizedMap(
			new LinkedHashMap<>());
	private final Map<Long, Map<String, CORSSupport>> _exactPatternMatchers =
		new LinkedHashMap<>();
	private final Map<Long, Map<String, CORSSupport>>
		_extensionPatternMatchers = new LinkedHashMap<>();
	private final Map<Long, Map<String, CORSSupport>> _wildcardPatternMatchers =
		new LinkedHashMap<>();

}