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

package com.liferay.osb.asah.common.mapper;

import com.liferay.osb.asah.common.dto.DataExportTaskDTO;
import com.liferay.osb.asah.common.model.DataExportTask;

import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class DataExportTaskMapper
	extends Mapper<DataExportTask, DataExportTaskDTO> {

	@Override
	protected DataExportTaskDTO toDTO(DataExportTask dataExportTask) {
		return new DataExportTaskDTO(dataExportTask);
	}

	@Override
	protected DataExportTask toModel(JSONObject jsonObject) {
		DataExportTask dataExportTask = new DataExportTask();

		if (jsonObject.has("completedDate") &&
			!jsonObject.isNull("completedDate")) {

			dataExportTask.setCompletedDate(
				toUTCDate(jsonObject.get("completedDate")));
		}

		if (jsonObject.has("createDate") && !jsonObject.isNull("createDate")) {
			dataExportTask.setCreateDate(
				toUTCDate(jsonObject.get("createDate")));
		}
		else if (jsonObject.has("createdDate") &&
				 !jsonObject.isNull("createdDate")) {

			dataExportTask.setCreateDate(
				toUTCDate(jsonObject.get("createdDate")));
		}

		if (jsonObject.has("id") && !jsonObject.isNull("id")) {
			dataExportTask.setId(jsonObject.getLong("id"));
		}

		if (jsonObject.has("startedDate") &&
			!jsonObject.isNull("startedDate")) {

			dataExportTask.setStartedDate(
				toUTCDate(jsonObject.get("startedDate")));
		}

		if (jsonObject.has("status") && !jsonObject.isNull("status")) {
			dataExportTask.setStatus(
				DataExportTask.Status.valueOf(jsonObject.getString("status")));
		}

		if (jsonObject.has("type") && !jsonObject.isNull("type")) {
			dataExportTask.setType(
				DataExportTask.Type.valueOf(jsonObject.getString("type")));
		}

		return dataExportTask;
	}

}