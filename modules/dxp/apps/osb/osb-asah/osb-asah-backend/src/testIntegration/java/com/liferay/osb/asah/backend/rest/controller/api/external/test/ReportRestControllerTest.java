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

package com.liferay.osb.asah.backend.rest.controller.api.external.test;

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.dto.DataExportTaskDTO;
import com.liferay.osb.asah.backend.dto.ReportAccountDTO;
import com.liferay.osb.asah.backend.rest.controller.api.external.ReportRestController;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.DataExportTask;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

/**
 * @author Marcellus Tavares
 */
public class ReportRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@SQLResource(
		resourcePath = "osbasahfaroinfo/report_rest_controller_completed_same_type.sql"
	)
	@Test
	public void testGetDataExportTaskWhenDateRangeAndTypeHasBeenAlreadyExported() {
		Date fromDate = DateUtil.toUTCDate("2022-03-01T12:00:00.000Z");
		Date toDate = DateUtil.toUTCDate("2022-03-31T12:00:00.000Z");

		ResponseEntity<DataExportTaskDTO> responseEntity =
			_reportRestController.getDataExportTask(fromDate, toDate, "page");

		Assertions.assertNotNull(responseEntity);

		_assertDataExportTaskDTO(
			DateUtil.toUTCDate("2022-04-03T00:00:00.000Z"),
			DateUtil.toUTCDate("2022-04-01T00:00:00.000Z"),
			responseEntity.getBody(),
			DateUtil.toUTCDate("2022-03-01T12:00:00.000Z"),
			DateUtil.toUTCDate("2022-04-02T12:00:00.000Z"),
			DataExportTask.Status.COMPLETED,
			DateUtil.toUTCDate("2022-03-31T12:00:00.000Z"),
			DataExportTask.Type.PAGE);
	}

	@Test
	public void testGetDataExportTaskWhenLastStatusIsCompleted() {
	}

	@SQLResource(
		resourcePath = "osbasahfaroinfo/report_rest_controller_last_error_same_type.sql"
	)
	@Test
	public void testGetDataExportTaskWhenLastStatusIsErrorOfSameType() {
		Date date = DateUtil.newDayDate();

		Date toDate = DateUtil.addDays(date, -1);

		Date fromDate = DateUtil.addDays(toDate, -1);

		ResponseEntity<DataExportTaskDTO> responseEntity =
			_reportRestController.getDataExportTask(fromDate, toDate, "page");

		Assertions.assertNotNull(responseEntity);

		_assertDataExportTaskDTO(
			null, date, responseEntity.getBody(), fromDate, null,
			DataExportTask.Status.PENDING, toDate, DataExportTask.Type.PAGE);
	}

	@SQLResource(
		resourcePath = "osbasahfaroinfo/report_rest_controller_running_same_type.sql"
	)
	@Test
	public void testGetDataExportTaskWhenLastStatusIsRunningOfSameType() {
		Date date = DateUtil.newDayDate();

		ResponseEntity<DataExportTaskDTO> responseEntity =
			_reportRestController.getDataExportTask(
				DateUtil.addDays(date, -2), DateUtil.addDays(date, -1), "page");

		Assertions.assertNotNull(responseEntity);

		_assertDataExportTaskDTO(
			null, DateUtil.toUTCDate("2022-04-01T00:00:00.000Z"),
			responseEntity.getBody(),
			DateUtil.toUTCDate("2022-03-01T12:00:00.000Z"),
			DateUtil.toUTCDate("2022-04-02T12:00:00.000Z"),
			DataExportTask.Status.RUNNING,
			DateUtil.toUTCDate("2022-03-31T12:00:00.000Z"),
			DataExportTask.Type.PAGE);
	}

	@Test
	public void testGetDataExportTaskWithNoFromDate() {
		Exception exception = Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> _reportRestController.getDataExportTask(
				null, DateUtil.newDayDate(), "page"));

		Assertions.assertEquals(
			"Date range is mandatory", exception.getMessage());
	}

	@Test
	public void testGetDataExportTaskWithNoPreviousTask() {
		Date date = DateUtil.newDayDate();

		Date toDate = DateUtil.addDays(date, -1);

		Date fromDate = DateUtil.addDays(toDate, -1);

		ResponseEntity<DataExportTaskDTO> responseEntity =
			_reportRestController.getDataExportTask(fromDate, toDate, "page");

		Assertions.assertNotNull(responseEntity);

		_assertDataExportTaskDTO(
			null, date, responseEntity.getBody(), fromDate, null,
			DataExportTask.Status.PENDING, toDate, DataExportTask.Type.PAGE);
	}

	@Test
	public void testGetDataExportTaskWithNoToDate() {
		Exception exception = Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> _reportRestController.getDataExportTask(
				DateUtil.newDayDate(), null, "page"));

		Assertions.assertEquals(
			"Date range is mandatory", exception.getMessage());
	}

	@Test
	public void testGetDataExportTaskWithNoToDateLesserThanFromDate() {
		Exception exception = Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> _reportRestController.getDataExportTask(
				DateUtil.newDayDate(),
				DateUtil.addDays(DateUtil.newDayDate(), -1), "page"));

		Assertions.assertEquals("Wrong range date", exception.getMessage());
	}

	@SQLResource(
		resourcePath = "osbasahfaroinfo/report_rest_controller_pending_different_type.sql"
	)
	@Test
	public void testGetDataExportTaskWithPendingTaskOfDifferentType() {
		Date date = DateUtil.newDayDate();

		Date toDate = DateUtil.addDays(date, -1);

		Date fromDate = DateUtil.addDays(toDate, -1);

		ResponseEntity<DataExportTaskDTO> responseEntity =
			_reportRestController.getDataExportTask(fromDate, toDate, "page");

		Assertions.assertNotNull(responseEntity);

		_assertDataExportTaskDTO(
			null, date, responseEntity.getBody(), fromDate, null,
			DataExportTask.Status.PENDING, toDate, DataExportTask.Type.PAGE);
	}

	@SQLResource(
		resourcePath = "osbasahfaroinfo/report_rest_controller_pending_same_type.sql"
	)
	@Test
	public void testGetDataExportTaskWithPendingTaskOfSameType() {
		Date date = DateUtil.newDayDate();

		ResponseEntity<DataExportTaskDTO> responseEntity =
			_reportRestController.getDataExportTask(
				DateUtil.addDays(date, -2), DateUtil.addDays(date, -1), "page");

		Assertions.assertNotNull(responseEntity);

		_assertDataExportTaskDTO(
			null, DateUtil.toUTCDate("2022-04-01T00:00:00.000Z"),
			responseEntity.getBody(),
			DateUtil.toUTCDate("2022-03-01T12:00:00.000Z"), null,
			DataExportTask.Status.PENDING,
			DateUtil.toUTCDate("2022-03-31T12:00:00.000Z"),
			DataExportTask.Type.PAGE);
	}

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetReportAccountDTOEntityModel() {
		EntityModel<ReportAccountDTO> reportAccountDTOEntityModel =
			_reportRestController.getReportAccountDTOEntityModel(
				379649798552539340L);

		ReportAccountDTO reportAccountDTO =
			reportAccountDTOEntityModel.getContent();

		Assertions.assertNotNull(reportAccountDTO);

		Assertions.assertEquals(
			12, reportAccountDTO.getActiveIndividualsCount());
		Assertions.assertEquals(
			"2019-10-16T21:25:31.053Z",
			DateUtil.toUTCString(reportAccountDTO.getCreateDate()));
		Assertions.assertEquals(
			"2019-10-16T21:26:31.053Z",
			DateUtil.toUTCString(reportAccountDTO.getModifiedDate()));
		Assertions.assertEquals("379649798552539340", reportAccountDTO.getId());
		Assertions.assertEquals(13, reportAccountDTO.getIndividualsCount());
	}

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "accounts", resourcePath = "accounts_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetReportAccountDTOResultBagEntityModel() {
		EntityModel<ResultBag<EntityModel<ReportAccountDTO>>>
			reportAccountDTOResultBagEntityModel =
				_reportRestController.getReportAccountDTOResultBagEntityModel(
					0);

		ResultBag<EntityModel<ReportAccountDTO>> resultBag =
			reportAccountDTOResultBagEntityModel.getContent();

		Assertions.assertNotNull(resultBag);

		Assertions.assertEquals(6, resultBag.getTotal());
		Assertions.assertEquals(
			SetUtil.of(
				"Heard Island and McDonald Islands", "Maldives", "Swaziland",
				"Uzbekistan", "Virgin Islands"),
			_getAccountPropertiesValues(
				resultBag.getResults(), "billingCountry"));
	}

	private void _assertDataExportTaskDTO(
		Date completedDate, Date createDate,
		DataExportTaskDTO dataExportTaskDTO, Date fromDate, Date startedDate,
		DataExportTask.Status status, Date toDate, DataExportTask.Type type) {

		Assertions.assertNotNull(dataExportTaskDTO);

		if (completedDate == null) {
			Assertions.assertNull(dataExportTaskDTO.getCompletedDate());
		}
		else {
			_assertEqualsTruncatedDates(
				dataExportTaskDTO.getCompletedDate(), completedDate);
		}

		_assertEqualsTruncatedDates(
			dataExportTaskDTO.getCreateDate(), createDate);

		Assertions.assertEquals(dataExportTaskDTO.getFromDate(), fromDate);

		if (startedDate == null) {
			Assertions.assertNull(dataExportTaskDTO.getStartedDate());
		}
		else {
			_assertEqualsTruncatedDates(
				dataExportTaskDTO.getStartedDate(), startedDate);
		}

		Assertions.assertEquals(
			dataExportTaskDTO.getStatus(), status.toString());
		Assertions.assertEquals(dataExportTaskDTO.getToDate(), toDate);
		Assertions.assertEquals(dataExportTaskDTO.getType(), type.toString());
	}

	private void _assertEqualsTruncatedDates(
		Date actualDate, Date expectedDate) {

		Assertions.assertEquals(
			DateUtil.newBeginningOfDayDate(expectedDate),
			DateUtil.newBeginningOfDayDate(actualDate));
	}

	private Set<String> _getAccountPropertiesValues(
		List<EntityModel<ReportAccountDTO>> reportAccountEntityModel,
		String fieldName) {

		Stream<EntityModel<ReportAccountDTO>> stream =
			reportAccountEntityModel.stream();

		return stream.map(
			EntityModel::getContent
		).map(
			ReportAccountDTO::getReportAccountPropertiesDTO
		).map(
			ReportAccountDTO.ReportAccountPropertiesDTO::getProperties
		).map(
			properties -> properties.get(fieldName)
		).filter(
			propertyValue -> !Objects.isNull(propertyValue)
		).map(
			String::valueOf
		).collect(
			Collectors.toSet()
		);
	}

	@Autowired
	private ReportRestController _reportRestController;

}