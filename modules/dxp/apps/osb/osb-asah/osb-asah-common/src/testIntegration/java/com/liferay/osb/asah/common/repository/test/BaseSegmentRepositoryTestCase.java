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
import com.liferay.osb.asah.common.repository.Repository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 * @author Inácio Nery
 */
public abstract class BaseSegmentRepositoryTestCase
	extends BaseRepositoryTestCase<Segment, Long> {

	@Before
	public void setUp() {
		Segment segment1 = new Segment();

		Channel channel1 = _addChannel(1, "Liferay Brazil");

		segment1.setChannelId(channel1.getId());

		segment1.setCreateDate(new Date());
		segment1.setFilter("(channelId eq '1')");
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

		segment2.setCreateDate(new Date());
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

		segment3.setChannelId(channel2.getId());
		segment3.setCreateDate(new Date());
		segment3.setFilter(
			"((dataSourceAccountPKs/accountPKs eq 'testAccount'))");
		segment3.setName("Account: 123");
		segment3.setState("READY");
		segment3.setStatus("INACTIVE");
		segment3.setType(Segment.Type.DYNAMIC);

		setUpRepository(segment1, segment2, segment3);

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

	@Test
	public void testCountByIdAfter() {
		Assert.assertEquals(3, _segmentRepository.countByIdAfter(0L));

		Segment segment = entityModels.get(0);

		Assert.assertEquals(
			2, _segmentRepository.countByIdAfter(segment.getId()));
	}

	@Test
	public void testDeleteByChannelIdIn() {
		_segmentRepository.deleteByChannelIdIn(
			SetUtil.map(entityModels, Segment::getChannelId));

		Assert.assertEquals(0, _segmentRepository.count());
	}

	@Override
	@Test
	public void testFindAll() {
		super.testFindAll();

		Assert.assertEquals(
			Arrays.asList(entityModels.get(0)),
			_segmentRepository.findAll(PageRequest.of(0, 1)));
	}

	@Test
	public void testFindByNameAndStatus() {
		Optional<Segment> segmentOptional =
			_segmentRepository.findByNameAndStatus("Segment 1", "STARTED");

		Assert.assertTrue(segmentOptional.isPresent());

		segmentOptional = _segmentRepository.findByNameAndStatus(
			"Segment 1", "END");

		Assert.assertFalse(segmentOptional.isPresent());
	}

	@Test
	public void testFindByReferencedAssetDataSourceIdsAndStateNotAndType() {
		Assert.assertEquals(
			Arrays.asList(entityModels.get(0), entityModels.get(1)),
			_segmentRepository.
				findByReferencedAssetDataSourceIdsAndStateNotAndType(
					5L, "INACTIVE", Segment.Type.DYNAMIC));
	}

	@Test
	public void testSearchDynamicSegments() {
		List<Segment> segments = _segmentRepository.searchDynamicSegments(
			Collections.singleton(
				new Individual.DataSourceAccountPK(
					new DataSourceIndividual(
						Collections.singleton("testAccount"), 100L,
						_individualId, Collections.emptySet()))),
			null, false, PageRequest.of(0, 10),
			Collections.singleton(_segment3Id));

		Assert.assertEquals(segments.toString(), 1, segments.size());

		Segment segment = segments.get(0);

		Assert.assertEquals(_segment3Id, segment.getId());

		segments = _segmentRepository.searchDynamicSegments(
			Collections.singleton(
				new Individual.DataSourceAccountPK(
					new DataSourceIndividual(
						Collections.singleton("testAccount"), 100L,
						_individualId, Collections.emptySet()))),
			null, true, PageRequest.of(0, 10),
			Collections.singleton(_segment3Id));

		Assert.assertEquals(segments.toString(), 0, segments.size());
	}

	@Override
	protected Repository<Segment, Long> getRepository() {
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

	private Long _segment3Id;

	@Autowired
	private SegmentRepository _segmentRepository;

}