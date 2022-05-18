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
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Inácio Nery
 */
public abstract class BaseSegmentRepositoryTestCase
	extends BaseRepositoryTestCase<Segment, Long> {

	@BeforeEach
	public void setUp() {
		Segment segment1 = new Segment();

		Channel channel1 = _addChannel(1, "Liferay Brazil");

		segment1.setChannelId(channel1.getId());

		segment1.setAuthorName("Test Test");
		segment1.setCreateDate(DateUtil.addDays(new Date(), -5));
		segment1.setFilter("(channelId eq '1')");
		segment1.setLastActivityDate(new Date());
		segment1.setName("Segment 1");
		segment1.setReferencedAssetDataSourceIds(SetUtil.of(5L, 6L));
		segment1.setReferencedAssetIds(SetUtil.of(3L, 4L));
		segment1.setReferencedFieldMappingIds(SetUtil.of(7L, 8L));
		segment1.setState("READY");
		segment1.setStatus("STARTED");
		segment1.setType(Segment.Type.DYNAMIC);

		Segment segment2 = new Segment();

		Channel channel2 = _addChannel(2, "Liferay USA");

		segment2.setChannelId(channel2.getId());

		segment2.setAuthorName("Marcos Martins");
		segment2.setCreateDate(DateUtil.addDays(new Date(), -3));
		segment2.setFilter("(channelId eq '2')");
		segment2.setName("Segment 2");
		segment2.setReferencedAssetDataSourceIds(SetUtil.of(5L, 6L));
		segment2.setReferencedAssetIds(SetUtil.of(3L, 4L));
		segment2.setReferencedFieldMappingIds(SetUtil.of(7L, 8L));
		segment2.setState("READY");
		segment2.setStatus("STARTED");
		segment2.setType(Segment.Type.DYNAMIC);

		DataSource dataSource = new DataSource("Liferay Brazil");

		dataSource.setCredentialType("Token Authentication");
		dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource.setId(100L);
		dataSource.setIsNew(true);
		dataSource.setProviderType("LIFERAY");
		dataSource.setState("READY");
		dataSource.setStatus("STARTED");
		dataSource.setURL("");

		_dataSourceRepository.save(dataSource);

		Account account = new Account();

		account.setAccountPK("testAccount");
		account.setCreateDate(new Date());
		account.setDataSourceId(dataSource.getId());
		account.setId(123L);
		account.setIsNew(true);
		account.setModifiedDate(new Date());

		_accountRepository.save(account);

		Segment segment3 = new Segment();

		segment3.setAuthorName("Test Test");
		segment3.setChannelId(channel2.getId());
		segment3.setCreateDate(DateUtil.addDays(new Date(), -1));
		segment3.setFilter(
			"((dataSourceAccountPKs/accountPKs eq 'testAccount'))");
		segment3.setName("Account: 123");
		segment3.setState("READY");
		segment3.setStatus("INACTIVE");
		segment3.setType(Segment.Type.DYNAMIC);

		Segment segment4 = new Segment();

		segment4.setName("Account: 456");

		setUpRepository(segment1, segment2, segment3, segment4);

		segment1 = entityModels.get(0);

		_segment1Id = segment1.getId();

		segment2 = entityModels.get(1);

		_segment2Id = segment2.getId();

		segment3 = entityModels.get(2);

		_segment3Id = segment3.getId();

		Individual individual = new Individual();

		individual.setChannelIds(Collections.singleton(channel1.getId()));
		individual.setCreateDate(new Date());

		String emailAddress = "test@liferay.com";

		individual.setEmailAddressHashed(DigestUtils.sha256Hex(emailAddress));

		individual.setModifiedDate(new Date());
		individual.setSegmentIds(Collections.singleton(segment1.getId()));

		individual = _individualRepository.save(individual);

		_individualId = individual.getId();

		individual.setDataSourceIndividuals(
			Collections.singleton(
				new DataSourceIndividual(
					Collections.singleton("testAccount"), dataSource.getId(),
					_individualId,
					Collections.singleton("23432-cd-3242-asf23"))));

		_individualRepository.save(individual);
	}

	@Override
	public void tearDown() {
		super.tearDown();

		_accountRepository.deleteAll();
		_channelRepository.deleteAll();
		_dataSourceRepository.deleteAll();
		_individualRepository.deleteAll();
	}

	@Test
	public void testCountByCreateDateBetweenAndIdAfter() {
		Date fromDate = DateUtil.addDays(new Date(), -4);
		Date toDate = DateUtil.addDays(new Date(), -2);

		Assertions.assertEquals(
			1,
			_segmentRepository.countByCreateDateBetweenAndIdAfter(
				fromDate, toDate, 0L));
		Assertions.assertEquals(
			0,
			_segmentRepository.countByCreateDateBetweenAndIdAfter(
				fromDate, toDate, _segment2Id));
	}

	@Test
	public void testCountByIdAfter() {
		Assertions.assertEquals(4, _segmentRepository.countByIdAfter(0L));

		Segment segment = entityModels.get(0);

		Assertions.assertEquals(
			3, _segmentRepository.countByIdAfter(segment.getId()));
	}

	@Test
	public void testDeleteByChannelIdIn() {
		_segmentRepository.deleteByChannelIdIn(
			SetUtil.map(entityModels, Segment::getChannelId));

		Assertions.assertEquals(1, _segmentRepository.count());
	}

	@Test
	public void testFindByChannelIdIn() {
		Segment segment1 = entityModels.get(0);
		Segment segment2 = entityModels.get(1);

		List<Segment> segments = _segmentRepository.findByChannelIdIn(
			SetUtil.of(segment1.getChannelId(), segment2.getChannelId()),
			PageRequest.of(0, 10));

		Assertions.assertEquals(3, segments.size(), segments.toString());
	}

	@Test
	public void testFindByChannelIdIsNotNullOrNameStartingWith() {
		Assertions.assertEquals(
			entityModels,
			_segmentRepository.findByChannelIdIsNotNullOrNameStartingWith(
				"Account: ", PageRequest.of(0, 10, Sort.by("id"))));
	}

	@Test
	public void testFindByCreateDateBetweenAndIdAfter() {
		List<Segment> segments =
			_segmentRepository.findByCreateDateBetweenAndIdAfter(
				DateUtil.addDays(new Date(), -10), new Date(), _segment1Id - 1L,
				PageRequest.of(0, 1));

		Assertions.assertEquals(1, segments.size(), segments.toString());

		Segment segment = segments.get(0);

		Assertions.assertEquals(_segment1Id, segment.getId());
	}

	@Test
	public void testFindByNameAndStatus() {
		Optional<Segment> segmentOptional =
			_segmentRepository.findByNameAndStatus("Segment 1", "STARTED");

		Assertions.assertTrue(segmentOptional.isPresent());

		segmentOptional = _segmentRepository.findByNameAndStatus(
			"Segment 1", "END");

		Assertions.assertFalse(segmentOptional.isPresent());
	}

	@Test
	public void testFindByReferencedAssetDataSourceIdsAndStateNotAndType() {
		Assertions.assertEquals(
			Arrays.asList(entityModels.get(0), entityModels.get(1)),
			_segmentRepository.
				findByReferencedAssetDataSourceIdsAndStateNotAndType(
					5L, "INACTIVE", Segment.Type.DYNAMIC));
	}

	@Test
	public void testFindIdByNameInAndStatus() {
		List<Long> ids = _segmentRepository.findIdByNameInAndStatus(
			Arrays.asList("Segment 1", "Segment 2"), "STARTED");

		Assertions.assertEquals(2, ids.size(), ids.toString());
	}

	@Test
	public void testSearchDynamicSegments() {
		List<Segment> segments = _segmentRepository.searchDynamicSegments(
			Collections.singleton(
				new Individual.DataSourceAccountPK(
					new DataSourceIndividual(
						Collections.singleton("testAccount"), 100L,
						_individualId, Collections.emptySet()))),
			FilterHelper.EMPTY, false, PageRequest.of(0, 10),
			Collections.singleton(_segment3Id));

		Assertions.assertEquals(1, segments.size(), segments.toString());

		Segment segment = segments.get(0);

		Assertions.assertEquals(_segment3Id, segment.getId());

		segments = _segmentRepository.searchDynamicSegments(
			Collections.singleton(
				new Individual.DataSourceAccountPK(
					new DataSourceIndividual(
						Collections.singleton("testAccount"), 100L,
						_individualId, Collections.emptySet()))),
			FilterHelper.EMPTY, true, PageRequest.of(0, 10),
			Collections.singleton(_segment3Id));

		Assertions.assertEquals(0, segments.size(), segments.toString());
	}

	@Test
	public void testSearchSegmentsOrderByAuthorName() {
		List<Segment> segments = _segmentRepository.searchSegments(
			Arrays.asList(1L, 2L), FilterHelper.EMPTY,
			PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "author/name")));

		Assertions.assertEquals(3, segments.size(), segments.toString());

		Segment segment = segments.get(0);

		Assertions.assertEquals("Marcos Martins", segment.getAuthorName());

		segments = _segmentRepository.searchSegments(
			Arrays.asList(1L, 2L), FilterHelper.EMPTY,
			PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "author/name")));

		segment = segments.get(2);

		Assertions.assertEquals("Marcos Martins", segment.getAuthorName());
	}

	@Test
	public void testUpdateRemoveLastActivityDate() {
		Optional<Segment> segmentOptional = _segmentRepository.findById(
			_segment1Id);

		Segment segment = segmentOptional.get();

		Assertions.assertNotNull(segment.getLastActivityDate());

		_segmentRepository.updateRemoveLastActivityDate();

		segmentOptional = _segmentRepository.findById(_segment1Id);

		segment = segmentOptional.get();

		Assertions.assertNull(segment.getLastActivityDate());
	}

	@Override
	protected PagingAndSortingRepository<Segment, Long>
		getPagingAndSortingRepository() {

		return _segmentRepository;
	}

	private Channel _addChannel(long id, String name) {
		Channel channel = new Channel(name);

		channel.setId(id);
		channel.setIsNew(true);

		return _channelRepository.save(channel);
	}

	@Autowired
	private AccountRepository _accountRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	private Long _individualId;

	@Autowired
	private IndividualRepository _individualRepository;

	private Long _segment1Id;
	private Long _segment2Id;
	private Long _segment3Id;

	@Autowired
	private SegmentRepository _segmentRepository;

}