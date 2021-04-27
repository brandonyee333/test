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

import com.liferay.osb.asah.backend.dto.DashboardDTO;
import com.liferay.osb.asah.common.entity.CustomAssetDashboard;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.CustomAssetDashboardRepository;

import java.io.IOException;
import java.io.InputStream;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;

import org.json.JSONObject;
import org.json.JSONTokener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class DashboardDog {

	public DashboardDTO getDashboard(String dashboardId) {
		Optional<CustomAssetDashboard> customAssetDashboardOptional =
			_customAssetDashboardRepository.findById(dashboardId);

		if (!customAssetDashboardOptional.isPresent()) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					String.format(
						"Unable to retrieve the dashboard definition for the " +
							"dashboard ID %s.",
						dashboardId));
			}

			return null;
		}

		return new DashboardDTO(customAssetDashboardOptional.get());
	}

	public ResultBag<DashboardDTO> getDashboardResultBag(
		String channelId, String keywords, int size, Sort sort, int start) {

		ResultBag<CustomAssetDashboard> resultBag =
			_customAssetDashboardRepository.searchCustomAssetDashboard(
				Long.valueOf(channelId), keywords,
				PageRequest.of(start / size, size, sort));

		List<CustomAssetDashboard> results = resultBag.getResults();

		Stream<CustomAssetDashboard> stream = results.stream();

		List<DashboardDTO> dashboardDTOs = stream.map(
			DashboardDTO::new
		).collect(
			Collectors.toList()
		);

		return new ResultBag<>(dashboardDTOs, resultBag.getTotal());
	}

	public DashboardDTO updateDashboard(
		String dashboardId, String definition, String modifiedByUserId,
		String modifiedByUserName) {

		_dashboardDefinitionSchema.validate(new JSONObject(definition));

		Optional<CustomAssetDashboard> customAssetDashboardOptional =
			_customAssetDashboardRepository.findById(dashboardId);

		if (!customAssetDashboardOptional.isPresent()) {
			return null;
		}

		CustomAssetDashboard customAssetDashboard =
			customAssetDashboardOptional.get();

		customAssetDashboard.setDefinition(definition);
		customAssetDashboard.setModifiedByUserId(modifiedByUserId);
		customAssetDashboard.setModifiedByUserName(modifiedByUserName);
		customAssetDashboard.setModifiedDate(new Date());

		return new DashboardDTO(
			_customAssetDashboardRepository.save(customAssetDashboard));
	}

	@PostConstruct
	private void _init() {
		Class<?> clazz = getClass();

		try (InputStream inputStream = clazz.getResourceAsStream(
				"dashboard_definition_schema.json")) {

			_dashboardDefinitionSchema = SchemaLoader.load(
				new JSONObject(new JSONTokener(inputStream)));
		}
		catch (IOException ioe) {
			_log.error(ioe);

			throw new IllegalStateException(
				"Unable to read dashboard definition schema", ioe);
		}
	}

	private static final Log _log = LogFactory.getLog(DashboardDog.class);

	@Autowired
	private CustomAssetDashboardRepository _customAssetDashboardRepository;

	private Schema _dashboardDefinitionSchema;

}