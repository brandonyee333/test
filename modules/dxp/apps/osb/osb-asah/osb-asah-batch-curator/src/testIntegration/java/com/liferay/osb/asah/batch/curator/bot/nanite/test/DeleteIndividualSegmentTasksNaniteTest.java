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
import com.liferay.osb.asah.common.dog.BQMembershipChangeDog;
import com.liferay.osb.asah.common.dog.BQMembershipDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Individual;
import com.liferay.osb.asah.common.repository.BQMembershipChangeRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leslie Wong
 */
public class DeleteIndividualSegmentTasksNaniteTest
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@Disabled
	@Test
	public void testDeleteIndividualSegmentTasks() throws Exception {
		DataSource dataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildLiferayDataSource());

		// TODO Add BQFieldMapping "email", "Text"

		Long segmentId = RandomTestUtil.randomNumber();

		Segment segment = new Segment();

		segment.setId(segmentId);
		segment.setIsNew(Boolean.TRUE);

		_segmentRepository.save(segment);

		Individual individual = FaroInfoTestUtil.buildIndividual(dataSource);

		individual.setSegmentIds(Collections.singleton(segmentId));

		_individualDog.addIndividual(individual, false);

		_bqMembershipChangeDog.addBQMembershipChange(
			FaroInfoTestUtil.buildBQMembershipChange(segmentId));

		_bqMembershipDog.addBQMembership(
			FaroInfoTestUtil.buildBQMembership(
				String.valueOf(individual.getId()), segmentId));

		_deleteIndividualSegmentTasksNanite.run(
			JSONUtil.put("individualSegmentIds", JSONUtil.put(segmentId)));

		Assertions.assertEquals(
			0, _bqMembershipRepository.countBySegmentId(segmentId),
			"Entries within memberships related to the deleted individual " +
				"segment should be deleted");
		Assertions.assertEquals(
			0, _bqMembershipChangeRepository.countBySegmentId(segmentId),
			"Entries within membership-changes related to the deleted " +
				"individual segment should be deleted");

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
	private BQMembershipChangeDog _bqMembershipChangeDog;

	@Autowired
	private BQMembershipChangeRepository _bqMembershipChangeRepository;

	@Autowired
	private BQMembershipDog _bqMembershipDog;

	@Autowired
	private BQMembershipRepository _bqMembershipRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private DeleteIndividualSegmentTasksNanite
		_deleteIndividualSegmentTasksNanite;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private SegmentRepository _segmentRepository;

}