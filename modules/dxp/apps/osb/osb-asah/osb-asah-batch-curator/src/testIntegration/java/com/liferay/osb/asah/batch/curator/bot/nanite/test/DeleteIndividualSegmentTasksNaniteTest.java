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

import com.liferay.osb.asah.batch.curator.bot.nanite.DeleteIndividualSegmentTasksNanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.MembershipChangeDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leslie Wong
 */
public class DeleteIndividualSegmentTasksNaniteTest
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@Test
	public void testDeleteIndividualSegmentTasks() throws Exception {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildIndividualFieldMapping(
				dataSource.getId(), "email", "email", "Text"));

		String dayDateString = DateUtil.newDayDateString();
		Long segmentId = RandomTestUtil.randomNumber();

		Segment segment = new Segment();

		segment.setId(segmentId);

		_segmentRepository.save(segment);

		JSONObject assetJSONObject = FaroInfoTestUtil.buildPageAssetJSONObject(
			dataSource.getId());

		Individual individual = FaroInfoTestUtil.buildIndividual(dataSource);

		individual.setSegmentIds(Collections.singleton(segmentId));

		_fieldRepository.saveAll(individual.getFields());

		_individualDog.addIndividual(individual, false);

		_membershipChangeDog.addBQMembershipChange(
			FaroInfoTestUtil.buildBQMembershipChange(
				false, individual, segmentId, "ADDED"));

		_membershipDog.addBQMembership(
			FaroInfoTestUtil.buildBQMembership(individual.getId(), segmentId));

		faroInfoElasticsearchInvoker.add(
			"visited-pages",
			FaroInfoTestUtil.buildIndividualSegmentVisitedPagesJSONArray(
				assetJSONObject, dayDateString, segmentId,
				RandomUtils.nextInt()));

		_deleteIndividualSegmentTasksNanite.run(
			JSONUtil.put("individualSegmentIds", JSONUtil.put(segmentId)));

		Assertions.assertFalse(
			faroInfoElasticsearchInvoker.exists(
				"visited-pages",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("ownerId", segmentId)
				).filter(
					QueryBuilders.termQuery("ownerType", "individual-segment")
				)),
			"Entries within visited-pages related to the deleted individual " +
				"segment should be deleted");

		for (String collectionName :
				new String[] {"memberships", "membership-changes"}) {

			Assertions.assertFalse(
				faroInfoElasticsearchInvoker.exists(
					collectionName,
					QueryBuilders.termQuery("individualSegmentId", segmentId)),
				"Entries within " + collectionName + " related to the " +
					"deleted individual segment should be deleted");
		}

		individual = _individualDog.fetchIndividual(individual.getId());

		Assertions.assertFalse(CollectionUtils.isEmpty(individual.getFields()));

		Set<Long> segmentIds = individual.getSegmentIds();

		Iterator<Long> iterator = segmentIds.iterator();

		while (iterator.hasNext()) {
			Assertions.assertNotSame(
				segmentId, iterator.next(),
				"Individual segment ID should be removed from individual on " +
					"individual segment deletion");
		}
	}

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private DeleteIndividualSegmentTasksNanite
		_deleteIndividualSegmentTasksNanite;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private MembershipChangeDog _membershipChangeDog;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private SegmentRepository _segmentRepository;

}