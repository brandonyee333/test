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

package com.liferay.osb.asah.upgrade.v3_1_0.helper.test;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahElasticsearchTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_1_0.helper.EventsUpgradeHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.boot.test.mock.mockito.ResetMocksTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author Leslie Wong
 */
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.REPLACE_DEFAULTS,
	value = {
		DependencyInjectionTestExecutionListener.class,
		MockitoTestExecutionListener.class,
		OSBAsahElasticsearchTestExecutionListener.class,
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class,
		ResetMocksTestExecutionListener.class
	}
)
public class EventsUpgradeHelperTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws IOException {
		File file = new File("events-upgrade-step.properties");

		if (file.exists()) {
			FileUtils.forceDelete(file);
		}
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testUpgradeWithoutProperties() {
		try {
			ProjectIdThreadLocal.setProjectId("test");

			List<String> activityIds = new ArrayList<>();

			_eventsUpgradeHelper.doUpgrade(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.existsQuery("eventContext")
				).filter(
					QueryBuilders.existsQuery("sessionId")
				),
				new File("events-upgrade-step.properties"),
				(activitiesJSONArray, projectId) -> activityIds.addAll(
					JSONUtil.toStringList(activitiesJSONArray, "id")));

			Assertions.assertEquals(
				Arrays.asList(
					"348853926240043267", "348853926240043269",
					"348853926240043270", "348853926240043271"),
				activityIds);

			Properties properties = _getProperties(
				new File("events-upgrade-step.properties"));

			Assertions.assertEquals(
				"2022-01-15T11:13:00.123Z",
				properties.getProperty("test.date"));
			Assertions.assertEquals(
				"348853926240043271", properties.getProperty("test.id"));
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	@ElasticsearchIndex(
		name = "activities", resourcePath = "activities_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testUpgradeWithProperties() {
		Properties properties = new Properties();

		properties.put("test.date", "2021-10-30T22:00:33.000Z");
		properties.put("test.id", "348853926240043269");

		try (FileOutputStream fileOutputStream = new FileOutputStream(
				"events-upgrade-step.properties")) {

			properties.store(fileOutputStream, null);

			ProjectIdThreadLocal.setProjectId("test");

			List<String> activityIds = new ArrayList<>();

			_eventsUpgradeHelper.doUpgrade(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.existsQuery("eventContext")
				).filter(
					QueryBuilders.existsQuery("sessionId")
				),
				new File("events-upgrade-step.properties"),
				(activitiesJSONArray, projectId) -> activityIds.addAll(
					JSONUtil.toStringList(activitiesJSONArray, "id")));

			Assertions.assertEquals(
				Arrays.asList("348853926240043270", "348853926240043271"),
				activityIds);

			properties = _getProperties(
				new File("events-upgrade-step.properties"));

			Assertions.assertEquals(
				"2022-01-15T11:13:00.123Z",
				properties.getProperty("test.date"));
			Assertions.assertEquals(
				"348853926240043271", properties.getProperty("test.id"));
		}
		catch (IOException ioException) {
			Assertions.fail("Unable to write properties to file");
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	private Properties _getProperties(File propertiesFile) {
		try (FileInputStream fileInputStream = new FileInputStream(
				propertiesFile)) {

			Properties properties = new Properties();

			properties.load(fileInputStream);

			return properties;
		}
		catch (IOException ioException) {
			return new Properties();
		}
	}

	@Autowired
	private EventsUpgradeHelper _eventsUpgradeHelper;

}