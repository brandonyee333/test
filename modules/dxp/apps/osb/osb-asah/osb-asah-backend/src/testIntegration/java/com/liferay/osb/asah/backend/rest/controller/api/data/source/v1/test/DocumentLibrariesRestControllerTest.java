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

package com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.test;

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.DocumentLibrariesRestController;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Matthew Kong
 */
public class DocumentLibrariesRestControllerTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "document-libraries", resourcePath = "document-libraries.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetDownloadCountWithDates() {
		Assertions.assertEquals(
			"8",
			_documentLibrariesRestController.getDownloadsCount(
				"26413838", "480920708921930174", "480920708626120668",
				LocalDate.of(2021, 5, 20), LocalDate.of(2021, 5, 1)));
	}

	@ElasticsearchIndex(
		name = "document-libraries", resourcePath = "document-libraries.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetDownloadsCount() {
		Assertions.assertEquals(
			"10",
			_documentLibrariesRestController.getDownloadsCount(
				"26413838", "480920708921930174", "480920708626120668", null,
				null));
	}

	@ElasticsearchIndex(
		name = "document-libraries", resourcePath = "document-libraries.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetPreviewCountWithDates() {
		Assertions.assertEquals(
			"3",
			_documentLibrariesRestController.getPreviewsCount(
				"26413838", "480920708921930174", "480920708626120668",
				LocalDate.of(2021, 5, 20), LocalDate.of(2021, 5, 1)));
	}

	@ElasticsearchIndex(
		name = "document-libraries", resourcePath = "document-libraries.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetPreviewsCount() {
		Assertions.assertEquals(
			"11",
			_documentLibrariesRestController.getPreviewsCount(
				"26413838", "480920708921930174", "480920708626120668", null,
				null));
	}

	@Autowired
	private DocumentLibrariesRestController _documentLibrariesRestController;

}