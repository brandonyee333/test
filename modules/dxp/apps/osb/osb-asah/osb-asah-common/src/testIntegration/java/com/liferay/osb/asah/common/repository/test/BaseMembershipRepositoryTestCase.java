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

import com.liferay.osb.asah.common.model.Membership;
import com.liferay.osb.asah.common.repository.MembershipRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Inácio Nery
 */
public abstract class BaseMembershipRepositoryTestCase
	extends BaseRepositoryTestCase<Membership> {

	@Before
	public void setUp() {
		Membership membership1 = new Membership();

		membership1.setCreateDate(new Date());
		membership1.setIndividualId(12L);
		membership1.setIndividualSegmentId(34L);
		membership1.setModifiedDate(new Date());
		membership1.setRemovedDate(new Date());
		membership1.setStatus("ACTIVE");

		Membership membership2 = new Membership();

		membership2.setCreateDate(new Date());
		membership2.setIndividualId(12L);
		membership2.setIndividualSegmentId(56L);
		membership2.setModifiedDate(new Date());
		membership2.setRemovedDate(new Date());
		membership2.setStatus("INACTIVE");

		Membership membership3 = new Membership();

		membership3.setCreateDate(new Date());
		membership3.setIndividualId(78L);
		membership3.setIndividualSegmentId(34L);
		membership3.setModifiedDate(new Date());
		membership3.setRemovedDate(new Date());
		membership3.setStatus("ACTIVE");

		setUpRepository(membership1, membership2, membership3);
	}

	@Test
	public void testCountByIndividualIdInAndIndividualSegmentIdAndStatus() {
		Assert.assertEquals(
			2,
			_membershipRepository.
				countByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 34L, "ACTIVE"));
		Assert.assertEquals(
			0,
			_membershipRepository.
				countByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 34L, "INACTIVE"));
		Assert.assertEquals(
			1,
			_membershipRepository.
				countByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 56L, "INACTIVE"));
	}

	@Test
	public void testCountByIndividualSegmentIdAndStatus() {
		Assert.assertEquals(
			2,
			_membershipRepository.countByIndividualSegmentIdAndStatus(
				34L, "ACTIVE"));
		Assert.assertEquals(
			0,
			_membershipRepository.countByIndividualSegmentIdAndStatus(
				34L, "INACTIVE"));
		Assert.assertEquals(
			1,
			_membershipRepository.countByIndividualSegmentIdAndStatus(
				56L, "INACTIVE"));
	}

	@Test
	public void testDeleteByIndividualSegmentId() {
		_membershipRepository.deleteByIndividualSegmentId(56L);

		Assert.assertEquals(2, _membershipRepository.count());
	}

	@Test
	public void testExistsByIndividualIdAndIndividualSegmentIdAndStatus() {
		Assert.assertTrue(
			_membershipRepository.
				existsByIndividualIdAndIndividualSegmentIdAndStatus(
					12L, 34L, "ACTIVE"));
	}

	@Test
	public void testFindByIndividualIdAndIndividualSegmentIdAndStatus() {
		Membership membership =
			_membershipRepository.
				findByIndividualIdAndIndividualSegmentIdAndStatus(
					12L, 34L, "ACTIVE");

		Assert.assertEquals(entityModels.get(0), membership);
	}

	@Test
	public void testFindByIndividualIdAndIndividualSegmentIdInAndStatus() {
		List<Membership> memberships =
			_membershipRepository.
				findByIndividualIdAndIndividualSegmentIdInAndStatus(
					12L, Arrays.asList(34L, 56L), "ACTIVE");

		Assert.assertEquals(memberships.toString(), 1, memberships.size());

		Assert.assertEquals(entityModels.get(0), memberships.get(0));
	}

	@Test
	public void testFindByIndividualIdAndStatus() {
		List<Membership> memberships =
			_membershipRepository.findByIndividualIdAndStatus(12L, "ACTIVE");

		Assert.assertEquals(memberships.toString(), 1, memberships.size());

		Assert.assertEquals(entityModels.get(0), memberships.get(0));
	}

	@Test
	public void testFindByIndividualIdInAndIndividualSegmentIdAndStatus() {
		List<Membership> memberships =
			_membershipRepository.
				findByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 34L, "ACTIVE",
					PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(memberships.toString(), 2, memberships.size());

		Assert.assertEquals(entityModels.get(0), memberships.get(0));

		Assert.assertEquals(entityModels.get(2), memberships.get(1));

		memberships =
			_membershipRepository.
				findByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 34L, "INACTIVE",
					PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(memberships.toString(), 0, memberships.size());

		memberships =
			_membershipRepository.
				findByIndividualIdInAndIndividualSegmentIdAndStatus(
					Arrays.asList(12L, 78L), 56L, "INACTIVE",
					PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(memberships.toString(), 1, memberships.size());

		Assert.assertEquals(entityModels.get(1), memberships.get(0));
	}

	@Test
	public void testFindByIndividualSegmentIdAndStatus() {
		List<Membership> memberships =
			_membershipRepository.findByIndividualSegmentIdAndStatus(
				34L, "ACTIVE");

		Assert.assertEquals(memberships.toString(), 2, memberships.size());

		Assert.assertEquals(entityModels.get(0), memberships.get(0));

		Assert.assertEquals(entityModels.get(2), memberships.get(1));

		memberships = _membershipRepository.findByIndividualSegmentIdAndStatus(
			34L, "INACTIVE");

		Assert.assertEquals(memberships.toString(), 0, memberships.size());

		memberships = _membershipRepository.findByIndividualSegmentIdAndStatus(
			56L, "INACTIVE");

		Assert.assertEquals(memberships.toString(), 1, memberships.size());

		Assert.assertEquals(entityModels.get(1), memberships.get(0));

		memberships = _membershipRepository.findByIndividualSegmentIdAndStatus(
			34L, "ACTIVE",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(memberships.toString(), 2, memberships.size());

		Assert.assertEquals(entityModels.get(0), memberships.get(0));

		Assert.assertEquals(entityModels.get(2), memberships.get(1));

		memberships = _membershipRepository.findByIndividualSegmentIdAndStatus(
			34L, "INACTIVE",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(memberships.toString(), 0, memberships.size());

		memberships = _membershipRepository.findByIndividualSegmentIdAndStatus(
			56L, "INACTIVE",
			PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));

		Assert.assertEquals(memberships.toString(), 1, memberships.size());

		Assert.assertEquals(entityModels.get(1), memberships.get(0));
	}

	@Test
	public void testFindIndividualIdByIndividualSegmentIdAndStatus() {
		List<Long> individualIds =
			_membershipRepository.
				findIndividualIdByIndividualSegmentIdAndStatus(34L, "ACTIVE");

		Assert.assertEquals(individualIds.toString(), 2, individualIds.size());

		Assert.assertEquals(Long.valueOf(12), individualIds.get(0));

		Assert.assertEquals(Long.valueOf(78), individualIds.get(1));

		individualIds =
			_membershipRepository.
				findIndividualIdByIndividualSegmentIdAndStatus(34L, "INACTIVE");

		Assert.assertEquals(individualIds.toString(), 0, individualIds.size());

		individualIds =
			_membershipRepository.
				findIndividualIdByIndividualSegmentIdAndStatus(56L, "INACTIVE");

		Assert.assertEquals(individualIds.toString(), 1, individualIds.size());

		Assert.assertEquals(Long.valueOf(12), individualIds.get(0));
	}

	@Test
	public void testFindIndividualSegmentIdByIndividualIdAndStatus() {
		List<Long> individualSegmenIds =
			_membershipRepository.
				findIndividualSegmentIdByIndividualIdAndStatus(12L, "ACTIVE");

		Assert.assertEquals(
			individualSegmenIds.toString(), 1, individualSegmenIds.size());

		Assert.assertEquals(Long.valueOf(34), individualSegmenIds.get(0));

		individualSegmenIds =
			_membershipRepository.
				findIndividualSegmentIdByIndividualIdAndStatus(78L, "INACTIVE");

		Assert.assertEquals(
			individualSegmenIds.toString(), 0, individualSegmenIds.size());

		individualSegmenIds =
			_membershipRepository.
				findIndividualSegmentIdByIndividualIdAndStatus(12L, "INACTIVE");

		Assert.assertEquals(
			individualSegmenIds.toString(), 1, individualSegmenIds.size());

		Assert.assertEquals(Long.valueOf(56), individualSegmenIds.get(0));
	}

	@Test
	public void testFindIndividualSegmentIdByIndividualIdInAndStatus() {
		List<Long> individualSegmenIds =
			_membershipRepository.
				findIndividualSegmentIdByIndividualIdInAndStatus(
					Arrays.asList(12L, 78L), "ACTIVE");

		Assert.assertEquals(
			individualSegmenIds.toString(), 1, individualSegmenIds.size());

		Assert.assertEquals(Long.valueOf(34), individualSegmenIds.get(0));

		individualSegmenIds =
			_membershipRepository.
				findIndividualSegmentIdByIndividualIdInAndStatus(
					Arrays.asList(12L, 78L), "INACTIVE");

		Assert.assertEquals(
			individualSegmenIds.toString(), 1, individualSegmenIds.size());

		Assert.assertEquals(Long.valueOf(56), individualSegmenIds.get(0));
	}

	@Test
	public void testFindTop20IndividualSegmentIdByIndividualId() {
		List<Long> individualSegmenIds =
			_membershipRepository.findTop20IndividualSegmentIdByIndividualId(
				12L);

		Assert.assertEquals(
			individualSegmenIds.toString(), 2, individualSegmenIds.size());

		Assert.assertEquals(Long.valueOf(34), individualSegmenIds.get(0));

		Assert.assertEquals(Long.valueOf(56), individualSegmenIds.get(1));

		individualSegmenIds =
			_membershipRepository.findTop20IndividualSegmentIdByIndividualId(
				78L);

		Assert.assertEquals(
			individualSegmenIds.toString(), 1, individualSegmenIds.size());

		Assert.assertEquals(Long.valueOf(34), individualSegmenIds.get(0));
	}

	@Override
	protected CrudRepository<Membership, Long> getCrudRepository() {
		return _membershipRepository;
	}

	@Autowired
	private MembershipRepository _membershipRepository;

}