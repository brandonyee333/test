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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.common.entity.CustomAssetDashboard;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.CustomAssetDashboardRepository;

import java.io.IOException;
import java.io.InputStream;

import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;

import org.json.JSONObject;
import org.json.JSONTokener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class CustomAssetDashboardDog {

	public CustomAssetDashboard fetchCustomAssetDashboard(
		String customAssetDashboardId) {

		Optional<CustomAssetDashboard> customAssetDashboardOptional =
			_customAssetDashboardRepository.findById(customAssetDashboardId);

		return customAssetDashboardOptional.orElse(null);
	}

	public Page<CustomAssetDashboard> getCustomAssetDashboardPage(
		Long channelId, String keywords, int page, int size, Sort sort) {

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_customAssetDashboardRepository.searchCustomAssetDashboards(
				channelId, keywords, pageRequest),
			pageRequest,
			() -> _customAssetDashboardRepository.countCustomAssetDashboards(
				channelId, keywords));
	}

	public CustomAssetDashboard updateCustomAssetDashboard(
		String customAssetDashboardId, String definition,
		String modifiedByUserId, String modifiedByUserName) {

		_dashboardDefinitionSchema.validate(new JSONObject(definition));

		CustomAssetDashboard customAssetDashboard = fetchCustomAssetDashboard(
			customAssetDashboardId);

		if (customAssetDashboard == null) {
			return null;
		}

		customAssetDashboard.setDefinition(definition);
		customAssetDashboard.setModifiedByUserId(modifiedByUserId);
		customAssetDashboard.setModifiedByUserName(modifiedByUserName);
		customAssetDashboard.setModifiedDate(new Date());

		return _customAssetDashboardRepository.save(customAssetDashboard);
	}

	@PostConstruct
	private void _init() {
		Class<?> clazz = getClass();

		try (InputStream inputStream = clazz.getResourceAsStream(
				"custom_asset_dashboard_definition_schema.json")) {

			_dashboardDefinitionSchema = SchemaLoader.load(
				new JSONObject(new JSONTokener(inputStream)));
		}
		catch (IOException ioe) {
			_log.error(ioe);

			throw new IllegalStateException(
				"Unable to read custom asset dashboard definition schema", ioe);
		}
	}

	private static final Log _log = LogFactory.getLog(
		CustomAssetDashboardDog.class);

	@Autowired
	private CustomAssetDashboardRepository _customAssetDashboardRepository;

	private Schema _dashboardDefinitionSchema;

}