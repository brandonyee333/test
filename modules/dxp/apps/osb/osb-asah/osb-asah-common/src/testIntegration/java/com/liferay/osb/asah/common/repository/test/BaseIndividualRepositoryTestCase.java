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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.IndividualChannel;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Rachael Koestartyo
 */
public abstract class BaseIndividualRepositoryTestCase
	extends BaseRepositoryTestCase<Individual, Long> {

	@Before
	public void setUp() {
		DataSource dataSource1 = new DataSource("Liferay Brazil");

		dataSource1.setCredentialType("Token Authentication");

		Channel channel1 = new Channel("channel1");

		channel1.setId(11L);
		channel1.setIsNew(true);

		_channelRepository.save(channel1);

		dataSource1.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource1.setId(1L);
		dataSource1.setIsNew(true);
		dataSource1.setProviderType("LIFERAY");
		dataSource1.setState("READY");
		dataSource1.setStatus("STARTED");
		dataSource1.setURL("");

		_dataSourceRepository.save(dataSource1);

		Individual individual = new Individual();

		individual.setChannelIds(Collections.singleton(channel1.getId()));
		individual.setCreateDate(new Date());

		String emailAddress = "test@liferay.com";

		individual.setEmailAddressHashed(DigestUtils.sha256Hex(emailAddress));

		individual.setModifiedDate(new Date());

		setUpRepository(individual);

		individual = entityModels.get(0);

		_individualId = individual.getId();

		IndividualChannel individualChannel = new IndividualChannel(
			3L, channel1.getId(), _individualId, new Date());

		individual.setActivitiesCounts(
			Collections.singleton(
				new Individual.ActivitiesCount(individualChannel)));

		individual.setDataSourceIndividuals(
			Collections.singleton(
				new DataSourceIndividual(
					Collections.emptySet(), dataSource1.getId(), _individualId,
					Collections.singleton("23432-cd-3242-asf23"))));
		individual.setIndividualChannels(
			Collections.singleton(individualChannel));

		individual = individualRepository.save(individual);

		Field field1 = new Field();

		field1.setContext("demographics");
		field1.setDataSourceId(dataSource1.getId());
		field1.setDataSourceName("Source 1");
		field1.setFieldType("Text");
		field1.setModifiedDate(new Date());
		field1.setName("email");
		field1.setOwnerId(_individualId);
		field1.setOwnerType("individual");
		field1.setSourceName("emailAddress");
		field1.setValue(emailAddress);

		fieldRepository.save(field1);

		Field field2 = new Field();

		field2.setContext("demographics");
		field2.setDataSourceId(dataSource1.getId());
		field2.setDataSourceName("Source 1");
		field2.setFieldType("Text");
		field2.setModifiedDate(new Date(System.currentTimeMillis()));
		field2.setName("field1");
		field2.setOwnerId(_individualId);
		field2.setOwnerType("individual");
		field2.setSourceName("Field 1");
		field2.setValue("field two");

		fieldRepository.save(field2);

		Field field3 = new Field();

		field3.setContext("demographics");
		field3.setDataSourceId(dataSource1.getId());
		field3.setDataSourceName("Source 1");
		field3.setFieldType("Text");
		field3.setModifiedDate(new Date());
		field3.setName("field3");
		field3.setOwnerId(_individualId);
		field3.setOwnerType("individual");
		field3.setSourceName("Field 3");
		field3.setValue("field three");

		fieldRepository.save(field3);

		Set<Field> fields = new HashSet<>();

		fields.add(field1);
		fields.add(field2);
		fields.add(field3);

		individual.setFields(fields);

		Segment segment = new Segment();

		segment.setActiveIndividualCount(1L);
		segment.setActivitiesCount(3L);
		segment.setAnonymousIndividualCount(0L);
		segment.setChannelId(channel1.getId());
		segment.setCreateDate(new Date());
		segment.setFilter("(demographics/field3/value eq 'field three')");
		segment.setIndividualCount(1L);
		segment.setLastActivityDate(new Date());
		segment.setModifiedDate(new Date());
		segment.setName("Test Segment");
		segment.setState("READY");
		segment.setStatus("ACTIVE");
		segment.setType(Segment.Type.DYNAMIC);

		segment = segmentRepository.save(segment);

		_segmentId = segment.getId();

		individual.setSegmentIds(Collections.singleton(_segmentId));

		individualRepository.save(individual);

		entityModels = Collections.singletonList(individual);
	}

	@Override
	public void tearDown() {
		super.tearDown();

		segmentRepository.deleteAll();

		_channelRepository.deleteAll();
		_dataSourceRepository.deleteAll();
		_fieldMappingRepository.deleteAll();
		fieldRepository.deleteAll();
		individualRepository.deleteAll();
	}

	@Test
	public void testExistsByChannelIdAndFilterStringAndId() {
		Assert.assertTrue(
			individualRepository.existsByChannelIdAndFilterStringAndId(
				11L, "(demographics/field3/value eq 'field three')",
				_individualId));
	}

	@Test
	public void testExistsByFilterStringAndId() {
		Assert.assertTrue(
			individualRepository.existsByFilterStringAndId(
				"(demographics/field3/value eq 'field three')", _individualId));
	}

	@Test
	public void testFindActivitiesCounts() {
		List<Individual.ActivitiesCount> activitiesCounts =
			individualRepository.findActivitiesCounts(false, _segmentId);

		Individual.ActivitiesCount activitiesCount = activitiesCounts.get(0);

		Assert.assertEquals((Long)3L, activitiesCount.getActivitiesCount());
	}

	@Test
	public void testFindByAnySegmentIds() {
		List<Individual> individuals = individualRepository.findByAnySegmentIds(
			_segmentId);

		Assert.assertEquals(individuals.toString(), 1, individuals.size());

		Individual individual = individuals.get(0);

		Assert.assertEquals(_individualId, individual.getId());
	}

	@Test
	public void testFindByAssociatedIdNotAndDataSourceIdAndIndividualPK() {
		Optional<Individual> individualOptional =
			individualRepository.
				findByAssociatedIdNotAndDataSourceIdAndIndividualPK(
					1234L, 1L, "organizationIds", "23432-cd-3242-asf23");

		Assert.assertTrue(individualOptional.isPresent());
	}

	@Test
	public void testFindByDataSourceId() {
		List<Individual> individuals = individualRepository.findByDataSourceId(
			1L, PageRequest.of(0, 1));

		Assert.assertEquals(individuals.toString(), 1, individuals.size());

		Individual individual = individuals.get(0);

		Assert.assertEquals(_individualId, individual.getId());
	}

	@Test
	public void testFindByDataSourceIdAndIndividualPK() {
		Optional<Individual> individualOptional =
			individualRepository.findByDataSourceIdAndIndividualPK(
				1L, "23432-cd-3242-asf23");

		Assert.assertTrue(individualOptional.isPresent());

		individualOptional =
			individualRepository.findByDataSourceIdAndIndividualPK(1L, "23432");

		Assert.assertFalse(individualOptional.isPresent());
	}

	@Test
	public void testFindByEmailAddress() {
		Optional<Individual> individualOptional =
			individualRepository.findByEmailAddress("test@liferay.com");

		Assert.assertTrue(individualOptional.isPresent());
	}

	@Test
	public void testFindByEmailAddressHashed() {
		Optional<Individual> individualOptional =
			individualRepository.findByEmailAddressHashed(
				DigestUtils.sha256Hex("test@liferay.com"));

		Assert.assertTrue(individualOptional.isPresent());
	}

	@Test
	public void testFindByEmailAddressOrEmailAddressHashed() {
		Optional<Individual> individualOptional =
			individualRepository.findByEmailAddressOrEmailAddressHashed(
				"test@liferay.com", null);

		Assert.assertTrue(individualOptional.isPresent());

		individualOptional =
			individualRepository.findByEmailAddressOrEmailAddressHashed(
				null, DigestUtils.sha256Hex("test@liferay.com"));

		Assert.assertTrue(individualOptional.isPresent());
	}

	@Test
	public void testFindIndividualCounts() {
		Map<Long, Long> individualCounts =
			individualRepository.findIndividualCounts(false, _segmentId);

		Assert.assertEquals((Long)1L, individualCounts.get(11L));
	}

	@Test
	public void testUpdateAssociatedIds() {
		individualRepository.updateAssociatedIds(
			"organizationIds", Collections.singleton(234L), _individualId);

		Optional<Individual> individualOptional = individualRepository.findById(
			_individualId);

		Individual individual = individualOptional.get();

		Assert.assertEquals(
			Collections.singleton(234L), individual.getOrganizationIds());
	}

	@Override
	protected CrudRepository<Individual, Long> getCrudRepository() {
		return individualRepository;
	}

	@Autowired
	protected FieldRepository fieldRepository;

	@Autowired
	protected IndividualRepository individualRepository;

	@Autowired
	protected SegmentRepository segmentRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	private Long _individualId;
	private Long _segmentId;

}