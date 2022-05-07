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

package com.liferay.osb.asah.batch.curator.rest.controller.test;

import com.liferay.osb.asah.batch.curator.rest.controller.AdminRestController;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringExtension;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.elasticsearch.index.query.QueryBuilders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@ExtendWith(OSBAsahSpringExtension.class)
@SpringBootTest(classes = OSBAsahBatchCuratorSpringBootApplication.class)
public class AdminRestControllerTest
	implements OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "pages", resourcePath = "page_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testCloseSessions() throws Exception {
		_adminRestController.closeSessions();

		_elasticsearchInvoker.refresh();

		Assertions.assertEquals(
			2,
			_elasticsearchInvoker.count(
				"pages", QueryBuilders.existsQuery("sessionId")));
		Assertions.assertFalse(
			_elasticsearchInvoker.exists(
				"user-sessions", QueryBuilders.termQuery("completed", false)));
	}

	@Autowired
	private AdminRestController _adminRestController;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}