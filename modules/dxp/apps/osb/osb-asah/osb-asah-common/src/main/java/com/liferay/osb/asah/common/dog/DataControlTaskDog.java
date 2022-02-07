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

import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DataControlTask;
import com.liferay.osb.asah.common.entity.Suppression;
import com.liferay.osb.asah.common.model.DataControlTaskStatus;
import com.liferay.osb.asah.common.model.DataControlTaskType;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.DataControlTaskRepository;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.File;

import java.nio.file.Path;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class DataControlTaskDog {

	public boolean addDataControlTasks(
		List<String> emailAddresses, Path path, String ownerId,
		List<String> types) {

		if (path != null) {
			File file = path.toFile();

			if (!file.exists()) {
				return false;
			}

			emailAddresses = _readFile(file);

			if (!file.delete() && _log.isWarnEnabled()) {
				_log.warn("Unable to delete file " + file.getName());
			}
		}

		List<DataControlTask> dataControlTasks = new ArrayList<>();

		Long batchId = _timeOrderedUuidGenerator.generateIdAsLong();
		Date date = new Date();

		for (String emailAddress : emailAddresses) {
			for (String type : types) {
				if (type.equals(DataControlTaskType.UNSUPPRESS.toString())) {
					_updateSuppression(batchId, emailAddress, date);
				}

				DataControlTask dataControlTask = new DataControlTask();

				dataControlTask.setBatchId(batchId);
				dataControlTask.setCreateDate(date);
				dataControlTask.setEmailAddress(emailAddress);
				dataControlTask.setOwnerId(ownerId);
				dataControlTask.setStatus(
					DataControlTaskStatus.PENDING.toString());
				dataControlTask.setType(type);

				dataControlTasks.add(dataControlTask);
			}
		}

		_dataControlTaskRepository.saveAll(dataControlTasks);

		return true;
	}

	public Page<DataControlTask> getDataControlTaskPage(
		Long batchId, String keywords, Integer rangeKey, int page, int size,
		Sort sort, List<String> statuses, List<String> types) {

		Date startCreateDate = _getStartCreateDate(rangeKey);

		if (StringUtils.contains(sort.getColumn(), "Date")) {
			sort = new Sort("id", sort.getType());
		}

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_dataControlTaskRepository.searchDataControlTasks(
				batchId, keywords, startCreateDate, statuses, types,
				pageRequest),
			pageRequest,
			() -> _dataControlTaskRepository.countDataControlTasks(
				batchId, keywords, startCreateDate, statuses, types));
	}

	private Date _getStartCreateDate(Integer rangeKey) {
		if (rangeKey == null) {
			return null;
		}

		LocalDateTime localDateTime = LocalDateTime.now(
			_timeZoneDog.getZoneId());

		localDateTime = localDateTime.minusDays(rangeKey);
		localDateTime = localDateTime.with(LocalTime.MIDNIGHT);

		return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
	}

	private List<String> _readFile(File file) {
		CsvParserSettings csvParserSettings = new CsvParserSettings();

		csvParserSettings.setHeaderExtractionEnabled(false);

		CsvParser csvParser = new CsvParser(csvParserSettings);

		return ListUtil.map(csvParser.parseAll(file), row -> row[0]);
	}

	private void _updateSuppression(
		Long batchId, String emailAddress, Date date) {

		Suppression suppression = _suppressionDog.fetchSuppression(
			emailAddress);

		if (suppression == null) {
			return;
		}

		suppression.setDataControlTaskBatchId(batchId);
		suppression.setDataControlTaskCreateDate(date);
		suppression.setDataControlTaskStatus(
			DataControlTaskStatus.PENDING.toString());

		_suppressionDog.updateSuppression(suppression);
	}

	private static final Log _log = LogFactory.getLog(DataControlTaskDog.class);

	@Autowired
	private DataControlTaskRepository _dataControlTaskRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private SuppressionDog _suppressionDog;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

	@Autowired
	private TimeZoneDog _timeZoneDog;

}