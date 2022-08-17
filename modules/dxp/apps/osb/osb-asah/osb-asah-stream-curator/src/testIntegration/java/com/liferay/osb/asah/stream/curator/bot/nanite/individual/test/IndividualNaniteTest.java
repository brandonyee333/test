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

package com.liferay.osb.asah.stream.curator.bot.nanite.individual.test;

import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.BQDataSourceUser;
import com.liferay.osb.asah.common.entity.BQIdentityChannel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.repository.SuppressionRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.OSBAsahStreamCuratorSpringTestContext;
import com.liferay.osb.asah.stream.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.individual.IndividualNanite;
import com.liferay.osb.asah.stream.curator.bot.nanite.test.BaseNaniteTestCase;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.MessageBusChannel;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;

import org.apache.commons.codec.digest.DigestUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author André Miranda
 */
public class IndividualNaniteTest
	extends BaseNaniteTestCase
	implements OSBAsahStreamCuratorSpringTestContext {

	@BeforeEach
	public void setUp() {
		DataSource dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(Long.parseLong(RandomTestUtil.randomId()));

		// TODO Add BQFieldMapping "email", "Text"

	}

	@MessageBusChannel(
		channel = Channel.IDENTITY_MESSAGE,
		resourcePath = "identity_message_5.json"
	)
	@Test
	public void testAddIndividuals() {
		runNanite();

		Assertions.assertNotNull(
			_individualDog.fetchIndividualByEmailAddressHashed(
				"e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b93" +
					"4ca495991b7852b850"));
		Assertions.assertNotNull(
			_individualDog.fetchIndividualByEmailAddressHashed(
				"e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b93" +
					"4ca495991b7852b851"));
	}

	@Disabled
	@ElasticsearchIndex(
		name = "blogs", resourcePath = "blog_info_old.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.IDENTITY_MESSAGE,
		resourcePath = "identity_message_1.json"
	)
	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = SegmentRepository.class,
		resourcePath = "osbasahfaroinfo/individual_segments_1_info.json"
	)
	@Test
	public void testIndividualResolution() throws Exception {
		Individual individual1 = new Individual();

		individual1.setBQDataSourceUsers(
			Collections.singleton(
				new BQDataSourceUser(
					Collections.emptySet(), 1L, 100L,
					Collections.singleton("1"))));
		individual1.setEmailAddressHashed(
			"3bb3aa73a3e59526dcc7975c1516997e8a604083e661aa01f348ebc7b18a81dd");
		individual1.setId(100L);
		individual1.setSegmentIds(SetUtil.of(10L, 11L));

		Field field1 = new Field();

		field1.setName("email");
		field1.setOwnerId(100L);
		field1.setValue("john@liferay.com");

		_fieldRepository.save(field1);

		_individualDog.addIndividual(individual1, false);

		Individual individual2 = new Individual();

		individual2.setBQDataSourceUsers(
			Collections.singleton(
				new BQDataSourceUser(
					Collections.emptySet(), 2L, 200L,
					Collections.singleton("2"))));
		individual2.setEmailAddressHashed(
			"d35fe6a6d0d6043c61ca7b2b4694b6b5228c626321b0b103f35d346662e88227");
		individual2.setId(200L);
		individual2.setSegmentIds(Collections.singleton(20L));

		Field field2 = new Field();

		field2.setName("email");
		field2.setOwnerId(200L);
		field2.setValue("jane@liferay.com");

		_fieldRepository.save(field2);

		_individualDog.addIndividual(individual2, false);

		runNanite();

		JSONArray jsonArray = _cerebroInfoElasticsearchInvoker.get("blogs");

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/osbasahcerebroinfo/blog_info_new.json", this),
			jsonArray, false);
	}

	@Disabled
	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.IDENTITY_MESSAGE,
		resourcePath = "identity_message_2.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testMergeIndividual1() {
		Individual individual1 = new Individual();

		individual1.setBQDataSourceUsers(
			Collections.singleton(
				new BQDataSourceUser(
					Collections.emptySet(), 1L, 100L,
					Collections.singleton("1"))));
		individual1.setBQIdentityChannels(
			Collections.singleton(
				new BQIdentityChannel(1L, 1L, 100L, null, null)));
		individual1.setEmailAddressHashed(
			"3bb3aa73a3e59526dcc7975c1516997e8a604083e661aa01f348ebc7b18a81dd");
		individual1.setId(100L);

		Field field1 = new Field();

		field1.setContext("demographics");
		field1.setName("email");
		field1.setOwnerId(100L);
		field1.setValue("john@liferay.com");

		_fieldRepository.save(field1);

		_individualDog.addIndividual(individual1, false);

		Individual individual2 = new Individual();

		individual2.setBQDataSourceUsers(
			Collections.singleton(
				new BQDataSourceUser(
					Collections.emptySet(), 1L, 200L,
					Collections.singleton("2"))));
		individual2.setBQIdentityChannels(
			SetUtil.of(
				new BQIdentityChannel(1L, 1L, 200L, null, null),
				new BQIdentityChannel(1L, 2L, 200L, null, null)));
		individual2.setId(200L);

		_individualDog.addIndividual(individual2, false);

		Assertions.assertTrue(_individualDog.existsById(200L));

		runNanite();

		Assertions.assertFalse(_individualDog.existsById(200L));

		// TODO Test individual activities count

	}

	@Disabled
	@ElasticsearchIndex(
		name = "fields", resourcePath = "fields_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.IDENTITY_MESSAGE,
		resourcePath = "identity_message_2.json"
	)
	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_5_info.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testMergeIndividual2() {
		Assertions.assertTrue(_individualDog.existsById(100L));
		Assertions.assertTrue(_individualDog.existsById(200L));

		runNanite();

		Assertions.assertTrue(_individualDog.existsById(100L));
		Assertions.assertFalse(_individualDog.existsById(200L));
	}

	@Disabled
	@ElasticsearchIndex(
		name = "pages", resourcePath = "pages_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.IDENTITY_MESSAGE,
		resourcePath = "identity_message_4.json"
	)
	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_4_info.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testMergeIndividualWithEmptyEmailAddress() {
		runNanite();

		Assertions.assertNull(
			_individualDog.fetchIndividualByEmailAddressHashed(
				DigestUtils.sha256Hex("")));
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "blog_info_old.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.IDENTITY_MESSAGE,
		resourcePath = "identity_message_3.json"
	)
	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_3_info.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testSkipUpdatePageAndAsset() {
		runNanite();

		JSONArray jsonArray = _cerebroInfoElasticsearchInvoker.get("blogs");

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject blogJSONObject = jsonArray.getJSONObject(i);

			Assertions.assertFalse(
				blogJSONObject.getBoolean("knownIndividual"));
		}
	}

	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.IDENTITY_MESSAGE,
		resourcePath = "identity_message_2.json"
	)
	@RepositoryResource(
		repositoryClass = IndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals_2_info.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@RepositoryResource(
		repositoryClass = SuppressionRepository.class,
		resourcePath = "osbasahfaroinfo/suppressions.json"
	)
	@Test
	public void testSuppressedUserUpdate() {
		runNanite();

		JSONArray jsonArray = _cerebroInfoElasticsearchInvoker.get(
			"user-sessions");

		Assertions.assertEquals(1, jsonArray.length());

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		Assertions.assertEquals("200", jsonObject.get("individualId"));
		Assertions.assertEquals("2", jsonObject.get("userId"));

		Individual individual = _individualDog.fetchIndividualByEmailAddress(
			"john@liferay.com");

		Assertions.assertNull(individual.getLastActivityDate());
	}

	@Disabled
	@ElasticsearchIndex(
		name = "user-sessions", resourcePath = "session_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@MessageBusChannel(
		channel = Channel.IDENTITY_MESSAGE,
		resourcePath = "identity_message_2.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testUserSessionUpdate() {
		Individual individual1 = new Individual();

		individual1.setBQDataSourceUsers(
			Collections.singleton(
				new BQDataSourceUser(
					Collections.emptySet(), 1L, 100L,
					Collections.singleton("1"))));
		individual1.setBQIdentityChannels(
			Collections.singleton(
				new BQIdentityChannel(1L, 1L, 100L, null, null)));
		individual1.setEmailAddressHashed(
			"3bb3aa73a3e59526dcc7975c1516997e8a604083e661aa01f348ebc7b18a81dd");
		individual1.setId(100L);

		Field field1 = new Field();

		field1.setContext("demographics");
		field1.setName("email");
		field1.setOwnerId(100L);
		field1.setValue("john@liferay.com");

		_fieldRepository.save(field1);

		_individualDog.addIndividual(individual1, false);

		Individual individual2 = new Individual();

		individual2.setBQDataSourceUsers(
			Collections.singleton(
				new BQDataSourceUser(
					Collections.emptySet(), 1L, 200L,
					Collections.singleton("2"))));
		individual2.setBQIdentityChannels(
			SetUtil.of(
				new BQIdentityChannel(1L, 1L, 200L, null, null),
				new BQIdentityChannel(1L, 2L, 200L, null, null)));
		individual2.setId(200L);

		_individualDog.addIndividual(individual2, false);

		runNanite();

		JSONArray jsonArray = _cerebroInfoElasticsearchInvoker.get(
			"user-sessions");

		Assertions.assertEquals(1, jsonArray.length());

		JSONObject jsonObject = jsonArray.getJSONObject(0);

		Assertions.assertEquals("100", jsonObject.get("individualId"));
		Assertions.assertEquals("2", jsonObject.get("userId"));

		// TODO Test individual enrichment

	}

	@Override
	protected Nanite getNanite() {
		return _individualNanite;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private IndividualNanite _individualNanite;

}