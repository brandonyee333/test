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

import com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.DocumentLibrariesRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Matthew Kong
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class DocumentLibrariesRestControllerTest {

	@ElasticsearchIndex(
		name = "document-libraries", resourcePath = "document-libraries.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetDownloadCount() {
		Assert.assertEquals(
			"10",
			_documentLibrariesRestController.getDownloadCount(
				"26413838", "480920708921930199", "480920708626120699", null,
				null));
	}

	@ElasticsearchIndex(
		name = "document-libraries", resourcePath = "document-libraries.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testGetDownloadCountWithDates() {
		Assert.assertEquals(
			"8",
			_documentLibrariesRestController.getDownloadCount(
				"26413838", "480920708921930199", "480920708626120699",
				LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 20)));
	}

	@Autowired
	private DocumentLibrariesRestController _documentLibrariesRestController;

}