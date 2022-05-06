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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AccountDog;
import com.liferay.osb.asah.common.dog.MembershipChangeDog;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.IndividualRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.time.DateUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alejo Ceballos
 */
public class MembershipChangeDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		List<String> channelNames = Arrays.asList(
			RandomTestUtil.randomString(), RandomTestUtil.randomString());

		Date date = DateUtil.newDayDate();

		channelNames.forEach(
			channelName -> {
				int daysAgoCreation = -32;

				Segment segment = _addSegment(
					_channelRepository.save(new Channel(channelName)),
					DateUtils.addDays(date, daysAgoCreation));

				for (int daysAgo = daysAgoCreation; daysAgo <= 0; daysAgo++) {
					BQMembershipChange bqMembershipChange =
						_addMembershipChange(
							33 + daysAgo +
								(channelNames.indexOf(channelName) * 33),
							DateUtils.addDays(date, daysAgo), segment);

					if (daysAgo == -1) {
						_bqMembershipChangeByIndividualSegmentId.put(
							segment.getId(), bqMembershipChange);
					}
				}
			});
	}

	@Test
	public void testGetAccountNamesJSONObjects() {
		Account account = new Account();

		account.setAccountPK("1");
		account.setDataSourceId(1L);

		Field field = new Field();

		field.setContext("organization");
		field.setDataSourceId(1L);
		field.setDataSourceName("Data Source Name");
		field.setFieldType("Text");
		field.setName("accountName");
		field.setOwnerType("account");
		field.setOwnerId(1L);
		field.setSourceName("id");
		field.setValue("Test");

		field = _fieldRepository.save(field);

		account.setFields(Collections.singleton(field));

		account.setId(1L);
		account.setIsNew(true);

		_accountDog.addAccount(account);

		Individual individual = new Individual();

		Individual.DataSourceAccountPK dataSourceAccountPK =
			new Individual.DataSourceAccountPK();

		dataSourceAccountPK.setAccountPKs(Collections.singleton("1"));
		dataSourceAccountPK.setDataSourceId(1L);

		individual.setDataSourceAccountPKs(
			Collections.singleton(dataSourceAccountPK));

		individual.setId(32L);
		individual.setSegmentIds(Collections.singleton(1L));

		_individualRepository.save(individual);

		ArrayList<BQMembershipChange> bqMembershipChanges = new ArrayList<>();

		IntStream.range(
			32, 34
		).forEach(
			i -> {
				Optional<BQMembershipChange> bqMembershipChangeOptional =
					_bqMembershipChangeRepository.findByIndividualId((long)i);

				Assertions.assertTrue(bqMembershipChangeOptional.isPresent());

				bqMembershipChanges.add(bqMembershipChangeOptional.get());
			}
		);

		Map<Long, JSONObject> accountNamesJSONObjects =
			_membershipChangeDog.getAccountNamesJSONObjects(
				bqMembershipChanges);

		Collection<JSONObject> values = accountNamesJSONObjects.values();

		Stream<JSONObject> stream = values.stream();

		List<JSONObject> jsonObjects = stream.filter(
			accountNamesJSONObject -> !Objects.isNull(accountNamesJSONObject)
		).collect(
			Collectors.toList()
		);

		Assertions.assertEquals(1, jsonObjects.size(), jsonObjects.toString());

		JSONObject jsonObject = jsonObjects.get(0);

		JSONArray jsonArray = jsonObject.getJSONArray("account-names");

		Assertions.assertEquals("Test", jsonArray.get(0));
	}

	@Test
	public void testGetLastBeforeTodayByIndividualSegmentsId1() {
		List<Long> segmentIds = new ArrayList(
			_bqMembershipChangeByIndividualSegmentId.keySet());

		List<BQMembershipChange> bqMembershipChanges =
			_membershipChangeDog.getLastBeforeTodayByIndividualSegmentsId(
				segmentIds);

		Assertions.assertEquals(
			2, bqMembershipChanges.size(), bqMembershipChanges.toString());
		Assertions.assertNotEquals(
			bqMembershipChanges.get(0), bqMembershipChanges.get(1));

		for (BQMembershipChange bqMembershipChange : bqMembershipChanges) {
			Assertions.assertEquals(
				_bqMembershipChangeByIndividualSegmentId.get(
					bqMembershipChange.getIndividualSegmentId()),
				bqMembershipChange);
		}
	}

	@Test
	public void testGetLastBeforeTodayByIndividualSegmentsId2() {
		Date date = DateUtil.newDayDate();

		Segment segment = _addSegment(
			_channelRepository.save(new Channel(RandomTestUtil.randomString())),
			DateUtils.addDays(date, -1));

		segment.setIncludeAnonymousUsers(true);

		segment = _segmentRepository.save(segment);

		BQMembershipChange bqMembershipChange = _addMembershipChange(
			1, DateUtils.addDays(date, -1), segment);

		bqMembershipChange.setIndividualEmail(null);

		_bqMembershipChangeRepository.save(bqMembershipChange);

		List<BQMembershipChange> bqMembershipChanges =
			_membershipChangeDog.getLastBeforeTodayByIndividualSegmentsId(
				false, Arrays.asList(segment.getId()));

		Assertions.assertTrue(bqMembershipChanges.isEmpty());

		bqMembershipChanges =
			_membershipChangeDog.getLastBeforeTodayByIndividualSegmentsId(
				true, Arrays.asList(segment.getId()));

		Assertions.assertEquals(
			1, bqMembershipChanges.size(), bqMembershipChanges.toString());

		bqMembershipChange = bqMembershipChanges.get(0);

		Assertions.assertNull(bqMembershipChange.getIndividualEmail());
	}

	private BQMembershipChange _addMembershipChange(
		int index, Date modifiedDate, Segment segment) {

		BQMembershipChange bqMembershipChange = new BQMembershipChange();

		bqMembershipChange.setIndividualDeleted(Boolean.FALSE);
		bqMembershipChange.setIndividualsCount((long)index);
		bqMembershipChange.setIndividualEmail(
			String.format("individual.%d@liferay.com", index));
		bqMembershipChange.setIndividualId((long)index);
		bqMembershipChange.setIndividualName(
			String.format("Individual Name %d", index));

		bqMembershipChange.setIndividualSegmentId(segment.getId());

		bqMembershipChange.setJoinedDate(modifiedDate);
		bqMembershipChange.setKnownIndividualsCount((long)index);
		bqMembershipChange.setModifiedDate(modifiedDate);
		bqMembershipChange.setOperation("ADDED");

		return _bqMembershipChangeRepository.save(bqMembershipChange);
	}

	private Segment _addSegment(Channel channel, Date createDate) {
		Segment segment = new Segment();

		segment.setChannelId(channel.getId());

		segment.setCreateDate(createDate);
		segment.setFilter(
			String.format("(channelId eq '%d')", channel.getId()));
		segment.setName(
			String.format("Segment of channel %s", channel.getName()));
		segment.setReferencedAssetDataSourceIds(SetUtil.of(5L, 6L));
		segment.setReferencedAssetIds(SetUtil.of(3L, 4L));
		segment.setReferencedFieldMappingIds(SetUtil.of(7L, 8L));
		segment.setState("READY");
		segment.setStatus("STARTED");
		segment.setType(Segment.Type.DYNAMIC);

		return _segmentRepository.save(segment);
	}

	@Autowired
	private AccountDog _accountDog;

	private final Map<Long, BQMembershipChange>
		_bqMembershipChangeByIndividualSegmentId = new HashMap<>();

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualRepository _individualRepository;

	@Autowired
	private MembershipChangeDog _membershipChangeDog;

	@Autowired
	private SegmentRepository _segmentRepository;

}