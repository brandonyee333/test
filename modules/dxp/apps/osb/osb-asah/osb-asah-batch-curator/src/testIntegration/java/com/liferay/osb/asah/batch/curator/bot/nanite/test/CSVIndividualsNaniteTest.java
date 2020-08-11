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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.liferay.osb.asah.batch.curator.bot.nanite.CSVIndividualsNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.HashMap;

import org.elasticsearch.index.query.QueryBuilders;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Vishal Reddy
 * @author Leslie Wong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahBatchCuratorSpringBootApplication.class
	}
)
public class CSVIndividualsNaniteTest extends BaseIndividualsNaniteTestCase {

	@Test
	public void test() throws Exception {
		addDataSource("CSV");
		addEmailFieldMapping();
		addStandardFieldMappings();

		faroInfoElasticsearchInvoker.add(
			"csv-individuals",
			JSONUtil.putAll(
				FaroInfoTestUtil.buildCSVIndividualJSONObject(
					getDataSourceId(), getIndividual1PK(),
					getIndividual1FieldsMap()),
				FaroInfoTestUtil.buildCSVIndividualJSONObject(
					getDataSourceId(), getIndividual2PK(),
					getIndividual2FieldsMap())));

		_csvIndividualsNanite.run(
			JSONUtil.put(
				"dataSourceId", getDataSourceId()
			).put(
				"type", "reprocess"
			));

		assertIndividuals();
	}

	@Test
	public void testIndividualEmailAddressIsCaseInsensitive() throws Exception {
		addDataSource("CSV");
		addEmailFieldMapping();

		faroInfoElasticsearchInvoker.add(
			"csv-individuals",
			JSONUtil.putAll(
				FaroInfoTestUtil.buildCSVIndividualJSONObject(
					getDataSourceId(), generateIndividualPK(),
					new HashMap<String, Object>() {
						{
							put("email", "test@liferay.com");
						}
					}),
				FaroInfoTestUtil.buildCSVIndividualJSONObject(
					getDataSourceId(), generateIndividualPK(),
					new HashMap<String, Object>() {
						{
							put("email", "TEST@LIFERAY.COM");
						}
					}),
				FaroInfoTestUtil.buildCSVIndividualJSONObject(
					getDataSourceId(), generateIndividualPK(),
					new HashMap<String, Object>() {
						{
							put("email", "TeSt@LiFeRaY.CoM");
						}
					})));

		_csvIndividualsNanite.run(
			JSONUtil.put(
				"dataSourceId", getDataSourceId()
			).put(
				"type", "reprocess"
			));

		long individualCount = faroInfoElasticsearchInvoker.count(
			"individuals", QueryBuilders.matchAllQuery());

		Assert.assertEquals(
			"Found " + individualCount + " individuals when 1 individual is " +
				"expected from 3 CSV individuals with the same email address " +
					"in different casing",
			1, individualCount);
	}

	@Override
	protected String generateIndividualPK() {
		return RandomTestUtil.randomUUID();
	}

	@Override
	protected String getEmailDataSourceFieldName() {
		return "email";
	}

	@Autowired
	private CSVIndividualsNanite _csvIndividualsNanite;

}