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
import com.liferay.osb.asah.common.dog.DataExportTaskDog;
import com.liferay.osb.asah.common.entity.DataExportTask;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
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

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Marcellus Tavares
 */
public class ReportRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@SQLResource(
		resourcePath = "osbasahfaroinfo/test_report_rest_controller_data_export_task_2.sql"
	)
	@Test
	public void testGetDataExportTaskFile() throws Exception {
		ClassPathResource classPathResource = new ClassPathResource(
			"dependencies", getClass());

		String originalExportPath = (String)ReflectionTestUtils.getField(
			_dataExportTaskDog, "_exportPath");

		ReflectionTestUtils.setField(
			_dataExportTaskDog, "_exportPath", classPathResource.getPath());

		try {
			ResponseEntity<FileSystemResource> responseEntity =
				_reportRestController.getDataExportTaskFile(
					DateUtil.toUTCString(_fromDate),
					DateUtil.toUTCString(_toDate), "page");

			Assertions.assertNotNull(responseEntity);

			JSONAssert.assertEquals(
				ResourceUtil.readResourceToJSONObject(
					"dependencies/osbasahfaroinfo/1003.json", this),
				JSONUtil.put(
					"result",
					"com.liferay.osb.asah.backend.rest.controller.api." +
						"external.test.ReportRestControllerTest::" +
							"testGetDataExportTaskFile"),
				false);
		}
		finally {
			ReflectionTestUtils.setField(
				_dataExportTaskDog, "_exportPath", originalExportPath);
		}
	}

	@Test
	public void testGetDataExportTaskFileWithNoFromDate() {
		Exception exception = Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> _reportRestController.getDataExportTaskFile(
				null, DateUtil.toUTCString(DateUtil.newDayDate()), "page"));

		Assertions.assertEquals(
			"Date range is mandatory", exception.getMessage());
	}

	@Test
	public void testGetDataExportTaskFileWithNoPreviousTask() {
		Date date = DateUtil.newDayDate();

		Date toDate = DateUtil.addDays(date, -1);

		Date fromDate = DateUtil.addDays(toDate, -1);

		ResponseEntity<FileSystemResource> responseEntity =
			_reportRestController.getDataExportTaskFile(
				DateUtil.toUTCString(fromDate), DateUtil.toUTCString(toDate),
				"page");

		Assertions.assertNotNull(responseEntity);
		Assertions.assertEquals(
			HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}

	@Test
	public void testGetDataExportTaskFileWithNoToDate() {
		Exception exception = Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> _reportRestController.getDataExportTaskFile(
				DateUtil.toUTCString(DateUtil.newDayDate()), null, "page"));

		Assertions.assertEquals(
			"Date range is mandatory", exception.getMessage());
	}

	@Test
	public void testGetDataExportTaskFileWithToDateLesserThanFromDate() {
		Exception exception = Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> _reportRestController.getDataExportTaskFile(
				DateUtil.toUTCString(DateUtil.newDayDate()),
				DateUtil.toUTCString(
					DateUtil.addDays(DateUtil.newDayDate(), -1)),
				"page"));

		Assertions.assertEquals(
			"From date is after to date", exception.getMessage());
	}

	@Test
	public void testGetDataExportTaskWithNoFromDate() {
		Exception exception = Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> _reportRestController.getDataExportTask(
				null, DateUtil.toUTCString(DateUtil.newDayDate()), "page"));

		Assertions.assertEquals(
			"Date range is mandatory", exception.getMessage());
	}

	@Test
	public void testGetDataExportTaskWithNoPreviousTask() {
		Date date = DateUtil.newDayDate();

		Date toDate = DateUtil.addDays(date, -1);

		Date fromDate = DateUtil.addDays(toDate, -1);

		ResponseEntity<DataExportTaskDTO> responseEntity =
			_reportRestController.getDataExportTask(
				DateUtil.toUTCString(fromDate), DateUtil.toUTCString(toDate),
				"page");

		Assertions.assertNotNull(responseEntity);

		_assertDataExportTaskDTO(
			null, date, responseEntity.getBody(), fromDate, null, null, null,
			DataExportTask.Status.PENDING, toDate, DataExportTask.Type.PAGE);
	}

	@Test
	public void testGetDataExportTaskWithNoToDate() {
		Exception exception = Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> _reportRestController.getDataExportTask(
				DateUtil.toUTCString(DateUtil.newDayDate()), null, "page"));

		Assertions.assertEquals(
			"Date range is mandatory", exception.getMessage());
	}

	@Test
	public void testGetDataExportTaskWithToDateLesserThanFromDate() {
		Exception exception = Assertions.assertThrows(
			IllegalArgumentException.class,
			() -> _reportRestController.getDataExportTask(
				DateUtil.toUTCString(DateUtil.newDayDate()),
				DateUtil.toUTCString(
					DateUtil.addDays(DateUtil.newDayDate(), -1)),
				"page"));

		Assertions.assertEquals(
			"From date is after to date", exception.getMessage());
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

	@SQLResource(
		resourcePath = "osbasahfaroinfo/test_report_rest_controller_data_export_task_1.sql"
	)
	@Test
	public void testNoPreviousExportProcessForTheSameTypeAndDateRange() {
		ResponseEntity<DataExportTaskDTO> responseEntity =
			_reportRestController.getDataExportTask(
				DateUtil.toUTCString(_fromDate), DateUtil.toUTCString(_toDate),
				"page");

		Assertions.assertNotNull(responseEntity);

		_assertDataExportTaskDTO(
			null, DateUtil.newDayDate(), responseEntity.getBody(), _fromDate,
			null, null, null, DataExportTask.Status.PENDING, _toDate,
			DataExportTask.Type.PAGE);
	}

	@SQLResource(
		resourcePath = "osbasahfaroinfo/test_report_rest_controller_data_export_task_2.sql"
	)
	@Test
	public void testThereIsPreviousExportProcessForTheSameTypeAndDateRangeAndIsCompleted() {
		ResponseEntity<DataExportTaskDTO> responseEntity =
			_reportRestController.getDataExportTask(
				DateUtil.toUTCString(_fromDate), DateUtil.toUTCString(_toDate),
				"page");

		Assertions.assertNotNull(responseEntity);

		_assertDataExportTaskDTO(
			DateUtil.toUTCDate("2022-04-03T12:00:00.000Z"), _createDate,
			responseEntity.getBody(), _fromDate, "1003", null, _startedDate,
			DataExportTask.Status.COMPLETED, _toDate, DataExportTask.Type.PAGE);
	}

	@SQLResource(
		resourcePath = "osbasahfaroinfo/test_report_rest_controller_data_export_task_3.sql"
	)
	@Test
	public void testThereIsPreviousExportProcessForTheSameTypeAndDateRangeButIsPending() {
		ResponseEntity<DataExportTaskDTO> responseEntity =
			_reportRestController.getDataExportTask(
				DateUtil.toUTCString(_fromDate), DateUtil.toUTCString(_toDate),
				"page");

		Assertions.assertNotNull(responseEntity);

		_assertDataExportTaskDTO(
			null, _createDate, responseEntity.getBody(), _fromDate, "1000",
			DataExportTask.Status.PENDING, null, DataExportTask.Status.PENDING,
			_toDate, DataExportTask.Type.PAGE);
	}

	@SQLResource(
		resourcePath = "osbasahfaroinfo/test_report_rest_controller_data_export_task_4.sql"
	)
	@Test
	public void testThereIsPreviousExportProcessForTheSameTypeAndDateRangeButIsRunning() {
		ResponseEntity<DataExportTaskDTO> responseEntity =
			_reportRestController.getDataExportTask(
				DateUtil.toUTCString(_fromDate), DateUtil.toUTCString(_toDate),
				"page");

		Assertions.assertNotNull(responseEntity);

		_assertDataExportTaskDTO(
			null, _createDate, responseEntity.getBody(), _fromDate, "1002",
			null, _startedDate, DataExportTask.Status.RUNNING, _toDate,
			DataExportTask.Type.PAGE);
	}

	@SQLResource(
		resourcePath = "osbasahfaroinfo/test_report_rest_controller_data_export_task_5.sql"
	)
	@Test
	public void testThereIsPreviousExportProcessForTheSameTypeAndDateRangeButResultedInError() {
		ResponseEntity<DataExportTaskDTO> responseEntity =
			_reportRestController.getDataExportTask(
				DateUtil.toUTCString(_fromDate), DateUtil.toUTCString(_toDate),
				"page");

		Assertions.assertNotNull(responseEntity);

		_assertDataExportTaskDTO(
			null, DateUtil.newDayDate(), responseEntity.getBody(), _fromDate,
			null, DataExportTask.Status.ERROR, null,
			DataExportTask.Status.PENDING, _toDate, DataExportTask.Type.PAGE);
	}

	private void _assertDataExportTaskDTO(
		Date completedDate, Date createDate,
		DataExportTaskDTO dataExportTaskDTO, Date fromDate, String id,
		DataExportTask.Status previousStatus, Date startedDate,
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

		Assertions.assertEquals(fromDate, dataExportTaskDTO.getFromDate());

		if (id != null) {
			Assertions.assertEquals(id, dataExportTaskDTO.getId());
		}

		if (previousStatus == null) {
			Assertions.assertNull(dataExportTaskDTO.getPreviousStatus());
		}
		else {
			Assertions.assertEquals(
				previousStatus.toString(),
				dataExportTaskDTO.getPreviousStatus());
		}

		if (startedDate == null) {
			Assertions.assertNull(dataExportTaskDTO.getStartedDate());
		}
		else {
			_assertEqualsTruncatedDates(
				dataExportTaskDTO.getStartedDate(), startedDate);
		}

		Assertions.assertEquals(
			status.toString(), dataExportTaskDTO.getStatus());
		Assertions.assertEquals(toDate, dataExportTaskDTO.getToDate());
		Assertions.assertEquals(type.toString(), dataExportTaskDTO.getType());
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

	private static final Date _createDate = DateUtil.toUTCDate(
		"2022-04-01T12:00:00.000Z");
	private static final Date _fromDate = DateUtil.toUTCDate(
		"2022-03-01T12:00:00.000Z");
	private static final Date _startedDate = DateUtil.toUTCDate(
		"2022-04-02T12:00:00.000Z");
	private static final Date _toDate = DateUtil.toUTCDate(
		"2022-03-31T12:00:00.000Z");

	@Autowired
	private DataExportTaskDog _dataExportTaskDog;

	@Autowired
	private ReportRestController _reportRestController;

}