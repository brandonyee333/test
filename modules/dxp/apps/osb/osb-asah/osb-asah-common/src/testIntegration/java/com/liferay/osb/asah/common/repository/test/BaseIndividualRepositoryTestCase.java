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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoIndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.IndividualChannel;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.postgresql.converter.helper.IndividualsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.repository.Repository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.yaml.snakeyaml.util.ArrayUtils;

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

		Individual individual1 = new Individual();

		individual1.setChannelIds(Collections.singleton(channel1.getId()));

		Date date = new Date();

		individual1.setCreateDate(date);

		String emailAddress = "test@liferay.com";

		individual1.setEmailAddressHashed(DigestUtils.sha256Hex(emailAddress));

		individual1.setModifiedDate(date);

		setUpRepository(individual1);

		individual1 = entityModels.get(0);

		_individual1Id = individual1.getId();

		IndividualChannel individualChannel = new IndividualChannel(
			3L, channel1.getId(), _individual1Id, date, null);

		individual1.setActivitiesCounts(
			Collections.singleton(
				new Individual.ActivitiesCount(individualChannel)));

		individual1.setDataSourceIndividuals(
			Collections.singleton(
				new DataSourceIndividual(
					SetUtil.of("1234", "5678"), dataSource1.getId(),
					_individual1Id,
					Collections.singleton("23432-cd-3242-asf23"))));
		individual1.setIndividualChannels(
			Collections.singleton(individualChannel));

		individual1 = individualRepository.save(individual1);

		Field field1 = new Field();

		field1.setContext("demographics");
		field1.setDataSourceId(dataSource1.getId());
		field1.setDataSourceName("Source 1");
		field1.setFieldType("Text");
		field1.setModifiedDate(date);
		field1.setName("email");
		field1.setOwnerId(_individual1Id);
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
		field2.setOwnerId(_individual1Id);
		field2.setOwnerType("individual");
		field2.setSourceName("Field 1");
		field2.setValue("field two");

		fieldRepository.save(field2);

		Field field3 = new Field();

		field3.setContext("demographics");
		field3.setDataSourceId(dataSource1.getId());
		field3.setDataSourceName("Source 1");
		field3.setFieldType("Text");
		field3.setModifiedDate(date);
		field3.setName("field3");
		field3.setOwnerId(_individual1Id);
		field3.setOwnerType("individual");
		field3.setSourceName("Field 3");
		field3.setValue("field three");

		fieldRepository.save(field3);

		Set<Field> fields = new HashSet<>();

		fields.add(field1);
		fields.add(field2);
		fields.add(field3);

		individual1.setFields(fields);

		Segment segment = new Segment();

		segment.setActiveIndividualCount(1L);
		segment.setActivitiesCount(3L);
		segment.setAnonymousIndividualCount(0L);
		segment.setChannelId(channel1.getId());
		segment.setCreateDate(date);
		segment.setFilter("(demographics/field3/value eq 'field three')");
		segment.setIndividualCount(1L);
		segment.setLastActivityDate(date);
		segment.setModifiedDate(date);
		segment.setName("Test Segment");
		segment.setState("READY");
		segment.setStatus("ACTIVE");
		segment.setType(Segment.Type.DYNAMIC);

		segment = segmentRepository.save(segment);

		_segmentId = segment.getId();

		individual1.setSegmentIds(Collections.singleton(_segmentId));

		individualRepository.save(individual1);

		Individual individual2 = new Individual();

		individual2.setCreateDate(DateUtil.addDays(date, -1));

		individual2 = individualRepository.save(individual2);

		_individual2Id = individual2.getId();

		individualChannel = new IndividualChannel(
			5L, channel1.getId(), _individual2Id, date, null);

		individual2.setLastActivityDate(date);

		individual2.setIndividualChannels(
			Collections.singleton(individualChannel));

		individual2 = individualRepository.save(individual2);

		Individual individual3 = _addIndividualWithActivityDates(
			channel1, null, DateUtil.addDays(date, -1));

		_individual3Id = individual3.getId();

		Individual individual4 = _addIndividualWithActivityDates(
			channel1, null, null);

		_individual4Id = individual4.getId();

		Individual individual5 = _addIndividualWithActivityDates(
			channel1, DateUtil.addDays(date, -31), DateUtil.addDays(date, -31));

		_individual5Id = individual5.getId();

		entityModels = Arrays.asList(
			individual1, individual2, individual3, individual4, individual5);
	}

	@Override
	public void tearDown() {
		super.tearDown();

		segmentRepository.deleteAll();

		_fieldMappingRepository.deleteAll();

		_channelRepository.deleteAll();
		_dataSourceRepository.deleteAll();

		fieldRepository.deleteAll();
		individualRepository.deleteAll();
	}

	@Test
	public void testCountByChannelIdsAndLastActivityDatesAndPreviousActivityDatesAndSegmentIdsIn() {
		Date date = DateUtil.newDayDate();

		Date endOfDayDate = DateUtil.newEndOfDayDate(date);

		Date endOfDayYesterdayDate = DateUtil.addDays(endOfDayDate, -1);

		Date beforeYesterdayDate = DateUtil.addDays(endOfDayYesterdayDate, -1);

		Date monthAgoDate = DateUtil.addDays(endOfDayDate, -30);

		Assert.assertEquals(
			0,
			individualRepository.
				countByChannelIdsAndLastActivityDatesAndPreviousActivityDatesAndSegmentIdsIn(
					11L, beforeYesterdayDate, beforeYesterdayDate,
					Collections.singletonList(_segmentId), monthAgoDate,
					monthAgoDate));

		Date yesterdayDate = DateUtil.addDays(date, -1);

		Assert.assertEquals(
			1,
			individualRepository.
				countByChannelIdsAndLastActivityDatesAndPreviousActivityDatesAndSegmentIdsIn(
					11L, endOfDayDate, null,
					Collections.singletonList(_segmentId), yesterdayDate,
					null));
		Assert.assertEquals(
			1,
			individualRepository.
				countByChannelIdsAndLastActivityDatesAndPreviousActivityDatesAndSegmentIdsIn(
					11L, endOfDayDate, endOfDayDate,
					Collections.singletonList(_segmentId), yesterdayDate,
					date));
		Assert.assertEquals(
			1,
			individualRepository.
				countByChannelIdsAndLastActivityDatesAndPreviousActivityDatesAndSegmentIdsIn(
					11L, null, endOfDayDate,
					Collections.singletonList(_segmentId), null,
					yesterdayDate));
		Assert.assertEquals(
			1,
			individualRepository.
				countByChannelIdsAndLastActivityDatesAndPreviousActivityDatesAndSegmentIdsIn(
					11L, endOfDayYesterdayDate, endOfDayDate,
					Collections.singletonList(_segmentId), yesterdayDate,
					yesterdayDate));
		Assert.assertEquals(
			2,
			individualRepository.
				countByChannelIdsAndLastActivityDatesAndPreviousActivityDatesAndSegmentIdsIn(
					11L, endOfDayDate, endOfDayDate,
					Collections.singletonList(_segmentId), yesterdayDate,
					yesterdayDate));
	}

	@Test
	public void testCountByIdAfter() {
		Assert.assertEquals(5, individualRepository.countByIdAfter(0L));
		Assert.assertEquals(
			4, individualRepository.countByIdAfter(_individual1Id));
	}

	@Test
	public void testCountByIdInAndKeywords() {
		Assert.assertEquals(
			1,
			individualRepository.countByIdInAndKeywords(
				Collections.singletonList(_individual1Id), "liferay"));
	}

	@Test
	public void testCountByQueryAndSegmentId() {
		List<String> fieldNames =
			_fieldMappingRepository.
				findFieldNameByContextAndFieldTypeAndOwnerType(
					"demographics", "Text", "individual");

		Assert.assertEquals(
			1,
			individualRepository.countByFieldNamesAndQueryAndSegmentId(
				fieldNames, "eld", _segmentId));
	}

	@Test
	public void testCountIndividuals() {
		Assert.assertEquals(
			1,
			individualRepository.countIndividuals(
				11L,
				new FilterHelper(
					_faroInfoIndividualsFilterStringConverterHelper, null,
					_individualsFilterStringConverterHelper),
				false, 11L, _segmentId));
	}

	@Test
	public void testExistsByChannelIdAndFilterStringAndId() {
		Assert.assertTrue(
			individualRepository.existsByChannelIdAndFilterStringAndId(
				11L,
				new FilterHelper(
					_faroInfoIndividualsFilterStringConverterHelper,
					"(demographics/field3/value eq 'field three')",
					_individualsFilterStringConverterHelper),
				_individual1Id));
	}

	@Test
	public void testExistsByChannelIdAndFilterStringAndIncludeAnonymousUsersAndId() {
		Assert.assertTrue(
			individualRepository.
				existsByChannelIdAndFilterStringAndIncludeAnonymousUsersAndId(
					11L,
					new FilterHelper(
						_faroInfoIndividualsFilterStringConverterHelper,
						"(demographics/field3/value eq 'field three')",
						_individualsFilterStringConverterHelper),
					false, _individual1Id));
	}

	@Test
	public void testExistsByFilterStringAndId() {
		Assert.assertTrue(
			individualRepository.existsByFilterStringAndId(
				new FilterHelper(
					_faroInfoIndividualsFilterStringConverterHelper,
					"(demographics/field3/value eq 'field three')",
					_individualsFilterStringConverterHelper),
				_individual1Id));
	}

	@Test
	public void testFindAccountPKsByAnyChannelIdsAnySegmentIds() {
		List<String> accountPKs =
			individualRepository.findAccountPKsByChannelIdAndSegmentId(
				11L, _segmentId);

		Assert.assertEquals(Arrays.asList("1234", "5678"), accountPKs);
	}

	@Test
	public void testFindActivitiesCounts() {
		List<Individual.ActivitiesCount> activitiesCounts =
			individualRepository.findActivitiesCounts(false, _segmentId);

		Individual.ActivitiesCount activitiesCount = activitiesCounts.get(0);

		Assert.assertEquals((Long)3L, activitiesCount.getActivitiesCount());
	}

	@Test
	public void testFindAnonymousByCreateDateAndLastActivityDate() {
		List<Individual> individuals =
			individualRepository.findAnonymousByCreateDateAndLastActivityDate(
				DateUtil.newDateString(), PageRequest.of(0, 10));

		Assert.assertEquals(individuals.toString(), 1, individuals.size());

		Individual individual = individuals.get(0);

		Assert.assertEquals(_individual2Id, individual.getId());
	}

	@Test
	public void testFindByAssociatedIdNotAndDataSourceIdAndIndividualPK() {
		Individual individual =
			individualRepository.
				findByAssociatedIdNotAndDataSourceIdAndIndividualPK(
					1234L, 1L, "organizationIds", "23432-cd-3242-asf23");

		Assert.assertNotNull(individual);
	}

	@Test
	public void testFindByDataSourceId() {
		List<Individual> individuals = individualRepository.findByDataSourceId(
			1L, PageRequest.of(0, 1));

		Assert.assertEquals(individuals.toString(), 1, individuals.size());

		Individual individual = individuals.get(0);

		Assert.assertEquals(_individual1Id, individual.getId());
	}

	@Test
	public void testFindByDataSourceIdAndIndividualPK() {
		Individual individual =
			individualRepository.findByDataSourceIdAndIndividualPK(
				1L, "23432-cd-3242-asf23");

		Assert.assertNotNull(individual);

		individual = individualRepository.findByDataSourceIdAndIndividualPK(
			1L, "23432");

		Assert.assertNull(individual);
	}

	@Test
	public void testFindByEmailAddress() {
		Individual individual = individualRepository.findByEmailAddress(
			"test@liferay.com");

		Assert.assertNotNull(individual);
	}

	@Test
	public void testFindByEmailAddressHashed() {
		Individual individual = individualRepository.findByEmailAddressHashed(
			DigestUtils.sha256Hex("test@liferay.com"));

		Assert.assertNotNull(individual);
	}

	@Test
	public void testFindByIdAfter() {
		List<Individual> individuals = individualRepository.findByIdAfter(
			_individual1Id - 1L, PageRequest.of(0, 1));

		Assert.assertEquals(individuals.toString(), 1, individuals.size());

		Individual individual = individuals.get(0);

		Assert.assertEquals(_individual1Id, individual.getId());
	}

	@Test
	public void testFindByIdInAndKeywords() {
		List<Individual> individuals =
			individualRepository.findByIdInAndKeywords(
				Collections.singletonList(_individual1Id), "liferay",
				PageRequest.of(0, 1));

		Assert.assertEquals(individuals.toString(), 1, individuals.size());

		Individual individual = individuals.get(0);

		Assert.assertEquals(_individual1Id, individual.getId());
	}

	@Test
	public void testFindByQueryAndSegmentId() {
		List<String> fieldNames =
			_fieldMappingRepository.
				findFieldNameByContextAndFieldTypeAndOwnerType(
					"demographics", "Text", "individual");

		List<Individual> individuals =
			individualRepository.findByFieldNamesAndQueryAndSegmentId(
				fieldNames, "eld", _segmentId, PageRequest.of(0, 1));

		Assert.assertEquals(individuals.toString(), 1, individuals.size());

		Individual individual = individuals.get(0);

		Assert.assertEquals(_individual1Id, individual.getId());
	}

	@Test
	public void testFindBySegmentIds() {
		List<Individual> individuals = individualRepository.findBySegmentIds(
			_segmentId);

		Assert.assertEquals(individuals.toString(), 4, individuals.size());

		Stream<Individual> stream = individuals.stream();

		Set<Long> actualIndividualIds = stream.map(
			Individual::getId
		).collect(
			Collectors.toSet()
		);

		Set<Long> expectedIndividualIds = new HashSet<>(
			Arrays.asList(
				_individual1Id, _individual3Id, _individual4Id,
				_individual5Id));

		Assert.assertEquals(
			actualIndividualIds.toString(), expectedIndividualIds.size(),
			actualIndividualIds.size());
		Assert.assertTrue(
			actualIndividualIds.toString(),
			expectedIndividualIds.containsAll(actualIndividualIds));
	}

	@Test
	public void testFindIndividualCounts() {
		Map<Long, Long> individualCounts =
			individualRepository.findIndividualCounts(false, _segmentId);

		Assert.assertEquals((Long)1L, individualCounts.get(11L));
	}

	@Test
	public void testFindKnownIndividualIds() {
		Assert.assertEquals(
			Arrays.asList(_individual1Id),
			individualRepository.findKnownIndividualIds(
				FilterHelper.EMPTY, _segmentId));
	}

	@Test
	public void testGetIndividualDistributions() throws Exception {
		List<Distribution> distributions =
			individualRepository.getIndividualDistributions(
				"age", "Number", FilterHelper.EMPTY,
				PageRequest.of(0, 10, Sort.by(Sort.Order.asc("values"))));

		_assertEquals(
			distributions.get(0), 1,
			ArrayUtils.toUnmodifiableList(new Integer[] {20, 28}));
		_assertEquals(
			distributions.get(1), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {28, 36}));
		_assertEquals(
			distributions.get(2), 1,
			ArrayUtils.toUnmodifiableList(new Integer[] {36, 44}));
		_assertEquals(
			distributions.get(3), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {44, 52}));
		_assertEquals(
			distributions.get(4), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {52, 60}));
		_assertEquals(
			distributions.get(5), 1,
			ArrayUtils.toUnmodifiableList(new Integer[] {60, 68}));
		_assertEquals(
			distributions.get(6), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {68, 76}));
		_assertEquals(
			distributions.get(7), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {76, 84}));
		_assertEquals(
			distributions.get(8), 0,
			ArrayUtils.toUnmodifiableList(new Integer[] {84, 92}));
		_assertEquals(
			distributions.get(9), 1,
			ArrayUtils.toUnmodifiableList(new Integer[] {92, 100}));

		distributions = individualRepository.getIndividualDistributions(
			"jobTitle", "Text", FilterHelper.EMPTY,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("name"))));

		_assertEquals(
			distributions.get(0), 3,
			ArrayUtils.toUnmodifiableList(new String[] {"employee"}));
	}

	@Test
	public void testSearchIndividuals1() {
		List<Individual> individuals = individualRepository.searchIndividuals(
			new FilterHelper(
				_faroInfoIndividualsFilterStringConverterHelper,
				"(demographics/email/value eq 'test@liferay.com')",
				_individualsFilterStringConverterHelper),
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("activitiesCount"))));

		Assert.assertEquals(individuals.toString(), 1, individuals.size());
	}

	@Test
	public void testSearchIndividuals2() {
		List<Individual> individuals = individualRepository.searchIndividuals(
			11L, FilterHelper.EMPTY, false, null, null,
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(individuals.toString(), 1, individuals.size());
	}

	@Test
	public void testUpdateAssociatedIds() {
		individualRepository.updateAssociatedIds(
			"organizationIds", Collections.singleton(234L), _individual1Id);

		Optional<Individual> individualOptional = individualRepository.findById(
			_individual1Id);

		Individual individual = individualOptional.get();

		Assert.assertEquals(
			Collections.singleton(234L), individual.getOrganizationIds());
	}

	@Override
	protected Repository<Individual, Long> getRepository() {
		return individualRepository;
	}

	protected void setUpDataSources() {
		Channel channel = new Channel();

		channel.setId(1L);
		channel.setIsNew(true);

		_channelRepository.save(channel);

		DataSource dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(331238757269547423L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(351238757269547424L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(351238757269547425L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(366588399828298918L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(366588441118531472L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(366588489711802687L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);

		dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(366573382114568984L);
		dataSource.setIsNew(true);

		_dataSourceRepository.save(dataSource);
	}

	@Autowired
	protected FieldMappingRepository fieldMappingRepository;

	@Autowired
	protected FieldRepository fieldRepository;

	@Autowired
	protected IndividualRepository individualRepository;

	@Autowired
	protected SegmentRepository segmentRepository;

	private Individual _addIndividualWithActivityDates(
		Channel channel1, Date lastActivityDate, Date previousActivityDate) {

		Individual individual = new Individual();

		individual.setChannelIds(Collections.singleton(channel1.getId()));
		individual.setIndividualChannels(
			Collections.singleton(
				new IndividualChannel(
					0L, channel1.getId(), null, lastActivityDate,
					previousActivityDate)));
		individual.setSegmentIds(Collections.singleton(_segmentId));

		return individualRepository.save(individual);
	}

	private void _assertEquals(
		Distribution distribution, int expectedCount,
		List<Object> expectedValues) {

		Assert.assertEquals(expectedCount, (int)distribution.getCount());
		Assert.assertEquals(expectedValues, distribution.getValues());
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private FaroInfoIndividualsFilterStringConverterHelper
		_faroInfoIndividualsFilterStringConverterHelper;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	private Long _individual1Id;
	private Long _individual2Id;
	private Long _individual3Id;
	private Long _individual4Id;
	private Long _individual5Id;

	@Autowired
	private IndividualsFilterStringConverterHelper
		_individualsFilterStringConverterHelper;

	private Long _segmentId;

}