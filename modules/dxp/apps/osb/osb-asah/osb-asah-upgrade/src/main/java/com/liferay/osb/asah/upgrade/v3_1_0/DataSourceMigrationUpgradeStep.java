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

package com.liferay.osb.asah.upgrade.v3_1_0;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.repository.DataSourceRepository;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class DataSourceMigrationUpgradeStep extends BaseMigrationUpgradeStep {

	@Override
	protected Consumer<Object> getConsumer() {
		return object -> {
			DataSource dataSource = _objectMapper.convertValue(
				object, DataSource.class);

			dataSource.setIsNew(Boolean.TRUE);

			_dataSourceRepository.save(dataSource);
		};
	}

	@Override
	protected String getElasticsearchCollectionName() {
		return "data-sources";
	}

	@Override
	protected String getSelectLatestSQL() {
		return "SELECT id FROM datasource ORDER BY id DESC LIMIT 1";
	}

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}