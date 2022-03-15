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

package com.liferay.osb.asah.upgrade.v3_2_0;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.DataExportTask;
import com.liferay.osb.asah.common.repository.DataExportTaskRepository;
import com.liferay.osb.asah.upgrade.BaseMigrationUpgradeStep;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class DataExportTaskMigrationUpgradeStep
	extends BaseMigrationUpgradeStep {

	@Override
	protected Consumer<Object> getConsumer() {
		return object -> {
			DataExportTask dataExportTask = _objectMapper.convertValue(
				object, DataExportTask.class);

			dataExportTask.setIsNew(Boolean.TRUE);

			_dataExportTaskRepository.save(dataExportTask);
		};
	}

	@Override
	protected String getElasticsearchCollectionName() {
		return "data-export-tasks";
	}

	@Override
	protected String getSelectLatestIdSQL() {
		return "SELECT id FROM dataexporttask ORDER BY id DESC LIMIT 1";
	}

	@Override
	protected String getSequenceName() {
		return "dataexporttask_id_seq";
	}

	@Autowired
	private DataExportTaskRepository _dataExportTaskRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}