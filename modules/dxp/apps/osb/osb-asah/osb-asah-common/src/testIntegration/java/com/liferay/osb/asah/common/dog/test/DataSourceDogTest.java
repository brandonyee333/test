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

import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.elasticsearch.index.query.QueryBuilders;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

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

	@ElasticsearchIndex(
		name = "channels", resourcePath = "channels.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testChannelsCleared() {
		_dataSourceDog.disconnectDataSource(405201047787757795L);

		Assertions.assertEquals(
			0,
			faroInfoElasticsearchInvoker.count(
				"channels",
				QueryBuilders.termQuery(
					"dataSources.id", "405201047787757795")));

		Assertions.assertEquals(
			2,
			faroInfoElasticsearchInvoker.count(
				"channels",
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.termQuery(
						"dataSources.id", "405201047787757795")
				).filter(
					QueryBuilders.existsQuery("dataSources")
				)));
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

		dataSource = _dataSourceDog.patchDataSource(dataSource);

		Thread.sleep(500);

		Assertions.assertEquals(
			"Edited Data Source Test", dataSource.getName());

		List<Field> fields =
			_fieldRepository.
				findByContextAndOwnerIdInGroupByMaxModifiedDateAndName(
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

	@ElasticsearchIndex(
		name = "data-sources", resourcePath = "data_sources.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
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
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualDog _individualDog;

}