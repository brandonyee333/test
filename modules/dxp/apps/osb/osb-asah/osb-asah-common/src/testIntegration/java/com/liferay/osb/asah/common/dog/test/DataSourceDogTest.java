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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.FieldMappingDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.IterableUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author André Miranda
 */
public class DataSourceDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@Test
	public void testAddDataSourceWithDefaultChannel() {
		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource());

		Assertions.assertNotNull(
			_dataSourceDog.getDefaultChannelId(dataSource.getId()));
	}

	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testChannelsCleared() {
		_dataSourceDog.disconnectDataSource(405201047787757795L);

		List<Channel> channels = _channelRepository.findByDataSourceId(
			405201047787757795L);

		Assertions.assertEquals(0, channels.size());

		channels = IterableUtils.toList(_channelRepository.findAll());

		Assertions.assertEquals(3, channels.size());

		for (Channel channel : channels) {
			for (ChannelDataSource channelDataSource :
					channel.getChannelDataSources()) {

				Assertions.assertNotEquals(
					405201047787757795L, channelDataSource.getDataSourceId());
			}
		}
	}

	@Test
	public void testDisconnectDataSource() {
		DataSource dataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildLiferayDataSource(
				"Token Authentication", "Liferay", "http://localhost:8080"));

		Assertions.assertEquals("CREDENTIALS_VALID", dataSource.getState());
		Assertions.assertEquals("ACTIVE", dataSource.getStatus());

		dataSource = _dataSourceDog.disconnectDataSource(dataSource.getId());

		Assertions.assertEquals("DISCONNECTED", dataSource.getState());
		Assertions.assertEquals("INACTIVE", dataSource.getStatus());
	}

	@ElasticsearchIndex(
		name = "field-mappings", resourcePath = "field_mappings.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testFieldMappingsCleared() throws Exception {
		DataSource dataSource = _dataSourceDog.fetchDataSource(
			405201047787757795L);

		_dataSourceDog.deleteDataSource(dataSource);

		List<FieldMapping> fieldMappings = _fieldMappingDog.getFieldMappings(
			405201047787757795L);

		Assertions.assertTrue(fieldMappings.isEmpty());

		fieldMappings = _fieldMappingDog.getFieldMappings(
			Collections.singleton(340477857996688156L));

		Assertions.assertTrue(fieldMappings.isEmpty());

		fieldMappings = _fieldMappingDog.getFieldMappings(
			Collections.singleton(379649767240351068L));

		Assertions.assertEquals(1, fieldMappings.size());

		fieldMappings = _fieldMappingDog.getFieldMappings(405201047787757796L);

		Assertions.assertEquals(2, fieldMappings.size());
	}

	@RepositoryResource(
		repositoryClass = ChannelRepository.class,
		resourcePath = "osbasahfaroinfo/channels.json"
	)
	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testGetDataSources() {
		Page<DataSource> dataSourcePage = _dataSourceDog.getDataSourcePage(
			"(channelId eq [1])", 0, 10, null);

		Assertions.assertFalse(dataSourcePage.isEmpty());

		dataSourcePage = _dataSourceDog.getDataSourcePage(
			"(channelId eq [4])", 0, 10, null);

		Assertions.assertTrue(dataSourcePage.isEmpty());
	}

	@Test
	public void testPatchDataSource() throws Exception {
		DataSource dataSource = new DataSource();

		dataSource.setId(405201047787757795L);
		dataSource.setIsNew(true);
		dataSource.setName("Test Data Source");
		dataSource.setProviderType("LIFERAY");

		dataSource = _dataSourceDog.addDataSource(dataSource);

		DXPEntity dxpEntity = new DXPEntity();

		dxpEntity.setDataSourceId(dataSource.getId());
		dxpEntity.setDataSourceName("Data Source Test");
		dxpEntity.setIsNew(true);

		_dxpEntityDog.addDXPEntity(dxpEntity, DXPEntity.Type.USER);

		Individual individual = new Individual();

		individual.addDataSourceIndividual(
			new DataSourceIndividual(
				Collections.emptySet(), 405201047787757795L, null,
				Collections.singleton("123")));

		Field emailField = new Field();

		emailField.setContext("demographics");
		emailField.setDataSourceId(dataSource.getId());
		emailField.setDataSourceName("Source 1");
		emailField.setFieldType("Text");
		emailField.setName("email");
		emailField.setOwnerId(123L);
		emailField.setOwnerType("individual");
		emailField.setSourceName("emailAddress");
		emailField.setValue("test@liferay.com");

		_fieldRepository.save(emailField);

		individual.setFields(Collections.singleton(emailField));

		individual.setId(123L);

		individual = _individualDog.addIndividual(individual, false);

		dataSource.setName("Edited Data Source Test");

		BoundedExecutor boundedExecutor = BoundedExecutor.newBoundedExecutor(
			10, 1);

		ReflectionTestUtils.setField(
			_dataSourceDog, "_boundedExecutor", boundedExecutor);

		dataSource = _dataSourceDog.patchDataSource(dataSource);

		boundedExecutor.awaitPendingTasks();

		Assertions.assertEquals(
			"Edited Data Source Test", dataSource.getName());

		List<Field> fields =
			_fieldRepository.
				findByContextAndOwnerIdInGroupByMaxModifiedDateAndNameAndOwnerId(
					"demographics", Collections.singletonList(123L));

		fields.forEach(
			field -> Assertions.assertEquals(
				"Edited Data Source Test", field.getDataSourceName()));

		individual = _individualDog.getIndividual(individual.getId());

		Set<Field> customFields = individual.getCustomFields();

		customFields.forEach(
			customField -> Assertions.assertEquals(
				"Edited Data Source Test", customField.getDataSourceName()));

		Set<Field> individualFields = individual.getFields();

		individualFields.forEach(
			field -> Assertions.assertEquals(
				"Edited Data Source Test", field.getDataSourceName()));
	}

	@RepositoryResource(
		repositoryClass = DataSourceRepository.class,
		resourcePath = "osbasahfaroinfo/data_sources.json"
	)
	@Test
	public void testUpgradeFromOAuthToToken() {
		DataSource liferayDataSource = FaroInfoTestUtil.buildLiferayDataSource(
			"Token Authentication", "Liferay", "http://localhost:8080");

		liferayDataSource.setId(405201047787757796L);

		DataSource dataSource = _dataSourceDog.patchDataSource(
			liferayDataSource);

		Assertions.assertNotNull(dataSource.getPrivateKey());
		Assertions.assertNotNull(dataSource.getPublicKey());
		Assertions.assertEquals(
			"Token Authentication", dataSource.getCredentialType());
	}

	@MockBean
	private ChannelHttp _channelHttp;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualDog _individualDog;

}