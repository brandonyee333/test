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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.batch.curator.bot.nanite.CSVUsersNanite;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQCSVUserRepository;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.HashMap;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 * @author Leslie Wong
 */
public class CSVUsersNaniteTest extends BaseIndividualsNaniteTestCase {

	@Test
	public void test() throws Exception {
		addDataSource("CSV");
		addEmailFieldMapping();
		addStandardFieldMappings();

		_bqCSVUserRepository.save(
			FaroInfoTestUtil.buildBQCSVUser(
				getUser1PK(), getDataSourceId(),
				_objectMapper.convertValue(
					getIndividual1FieldsMap(), JSONObject.class)));

		_bqCSVUserRepository.save(
			FaroInfoTestUtil.buildBQCSVUser(
				getUser2PK(), getDataSourceId(),
				_objectMapper.convertValue(
					getIndividual2FieldsMap(), JSONObject.class)));

		_csvUsersNanite.run(
			JSONUtil.put(
				"dataSourceId", String.valueOf(getDataSourceId())
			).put(
				"type", "reprocess"
			));

		assertIndividuals();
	}

	@Test
	public void testIndividualEmailAddressIsCaseInsensitive() throws Exception {
		addDataSource("CSV");
		addEmailFieldMapping();

		_bqCSVUserRepository.save(
			FaroInfoTestUtil.buildBQCSVUser(
				generateUserPK(), getDataSourceId(),
				_objectMapper.convertValue(
					new HashMap<String, Object>() {
						{
							put("email", "test@liferay.com");
						}
					},
					JSONObject.class)));

		_bqCSVUserRepository.save(
			FaroInfoTestUtil.buildBQCSVUser(
				generateUserPK(), getDataSourceId(),
				_objectMapper.convertValue(
					new HashMap<String, Object>() {
						{
							put("email", "TEST@LIFERAY.COM");
						}
					},
					JSONObject.class)));

		_bqCSVUserRepository.save(
			FaroInfoTestUtil.buildBQCSVUser(
				generateUserPK(), getDataSourceId(),
				_objectMapper.convertValue(
					new HashMap<String, Object>() {
						{
							put("email", "TeSt@LiFeRaY.CoM");
						}
					},
					JSONObject.class)));

		_csvUsersNanite.run(
			JSONUtil.put(
				"dataSourceId", String.valueOf(getDataSourceId())
			).put(
				"type", "reprocess"
			));

		long individualsCount = faroInfoElasticsearchInvoker.count(
			"individuals", QueryBuilders.matchAllQuery());

		Assertions.assertEquals(
			1, individualsCount,
			"Found " + individualsCount + " individuals when 1 individual is " +
				"expected from 3 CSV individuals with the same email address " +
					"in different casing");
	}

	@Override
	protected String generateUserPK() {
		return RandomTestUtil.randomUUID();
	}

	@Override
	protected String getEmailDataSourceFieldName() {
		return "email";
	}

	@Autowired
	private BQCSVUserRepository _bqCSVUserRepository;

	@Autowired
	private CSVUsersNanite _csvUsersNanite;

	@Autowired
	private ObjectMapper _objectMapper;

}