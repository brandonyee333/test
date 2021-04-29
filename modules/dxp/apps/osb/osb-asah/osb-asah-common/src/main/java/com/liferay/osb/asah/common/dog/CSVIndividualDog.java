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

import com.liferay.osb.asah.common.entity.CSVIndividual;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.CSVIndividualRepository;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Marcellus Tavares
 */
@Component
public class CSVIndividualDog {

	public void addCSVIndividuals(List<CSVIndividual> csvIndividuals) {
		if (csvIndividuals.isEmpty()) {
			return;
		}

		_csvIndividualRepository.saveAll(csvIndividuals);

		CSVIndividual csvIndividual = csvIndividuals.get(0);

		_asahTaskDog.scheduleAsahTask(
			"CSVIndividualsNanite",
			JSONUtil.put(
				"dataSourceId", String.valueOf(csvIndividual.getDataSourceId())
			).put(
				"type", "reprocess"
			));
	}

	public void deleteCSVIndividuals(Long dataSourceId) {
		_csvIndividualRepository.deleteByDataSourceId(dataSourceId);
	}

	public void deleteCSVIndividuals(
		Long dataSourceId, List<String> dataSourceIndividualPKs) {

		_csvIndividualRepository.
			deleteByDataSourceIdAndDataSourceIndividualPKIn(
				dataSourceId, dataSourceIndividualPKs);
	}

	public CSVIndividual fetchCSVIndividual(
		Long dataSourceId, String fieldKey, String fieldValue) {

		List<CSVIndividual> csvIndividuals =
			_csvIndividualRepository.findByDataSourceIdAndFieldKeyEquals(
				dataSourceId, fieldKey, fieldValue);

		if (csvIndividuals.isEmpty()) {
			return null;
		}

		if ((csvIndividuals.size() > 1) && _log.isWarnEnabled()) {
			_log.warn("Multiple CSV Individuals were fetched");
		}

		return csvIndividuals.get(0);
	}

	public List<CSVIndividual> getCSVIndividuals(
		Long dataSourceId, int page, int size, Sort sort) {

		return _csvIndividualRepository.findByDataSourceId(
			dataSourceId, PageRequest.of(page, size, sort));
	}

	public long getCSVIndividualsCount(Long dataSourceId) {
		return _csvIndividualRepository.countByDataSourceId(dataSourceId);
	}

	private static final Log _log = LogFactory.getLog(CSVIndividualDog.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private CSVIndividualRepository _csvIndividualRepository;

}