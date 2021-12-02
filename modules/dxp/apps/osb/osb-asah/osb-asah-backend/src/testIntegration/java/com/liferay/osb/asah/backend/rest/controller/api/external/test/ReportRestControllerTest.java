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
import com.liferay.osb.asah.backend.dto.ReportAccountDTO;
import com.liferay.osb.asah.backend.rest.controller.api.external.ReportRestController;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;

/**
 * @author Marcellus Tavares
 */
public class ReportRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

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