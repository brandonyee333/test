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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.entity.DataExportTask;
import com.liferay.osb.asah.common.repository.DataExportTaskRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 * @author Marcellus Tavares
 */
@Component
public class DataExportTaskDog {

	public DataExportTask addDataExportTask(
		Date fromDate, Date toDate, DataExportTask.Type type) {

		DataExportTask dataExportTask = new DataExportTask();

		dataExportTask.setCreateDate(new Date());
		dataExportTask.setFromDate(fromDate);
		dataExportTask.setStatus(DataExportTask.Status.PENDING);
		dataExportTask.setToDate(toDate);
		dataExportTask.setType(type);

		return _dataExportTaskRepository.save(dataExportTask);
	}

	public DataExportTask fetchLastDataExportTask(DataExportTask.Type type) {
		return _dataExportTaskRepository.findFirstByTypeOrderByIdDesc(type);
	}

	public DataExportTask getDataExportTask(Long dataExportTaskId) {
		Optional<DataExportTask> dataExportTaskOptional =
			_dataExportTaskRepository.findById(dataExportTaskId);

		return dataExportTaskOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no data export task with ID " + dataExportTaskId));
	}

	public File getDataExportTaskFile(Long dataExportTaskId) {
		Path path = Paths.get(
			_exportPath, FilenameUtils.getName(dataExportTaskId + ".json"));

		path = path.normalize();

		if (!path.startsWith(_exportPath)) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"Invalid file name: " + dataExportTaskId);
		}

		return path.toFile();
	}

	public List<DataExportTask> getDataExportTasks(
		DataExportTask.Status status) {

		return _dataExportTaskRepository.findByStatus(status);
	}

	public DataExportTask updateDataExportTask(
		Long dataExportTaskId, DataExportTask.Status status) {

		DataExportTask dataExportTask = getDataExportTask(dataExportTaskId);

		if (status == DataExportTask.Status.COMPLETED) {
			dataExportTask.setCompletedDate(new Date());
		}
		else if (status == DataExportTask.Status.RUNNING) {
			dataExportTask.setStartedDate(new Date());
		}

		dataExportTask.setStatus(status);

		return _dataExportTaskRepository.save(dataExportTask);
	}

	@Autowired
	private DataExportTaskRepository _dataExportTaskRepository;

	@Value("${osb.asah.batch.curator.data.export.path:/export}")
	private String _exportPath;

}