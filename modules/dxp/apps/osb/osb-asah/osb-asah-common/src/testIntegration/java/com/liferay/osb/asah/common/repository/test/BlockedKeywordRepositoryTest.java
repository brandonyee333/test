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
import com.liferay.osb.asah.common.entity.BlockedKeyword;
import com.liferay.osb.asah.common.repository.BlockedKeywordRepository;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Robson Pastor
 */
@Import(JDBCTestConfiguration.class)
public class BlockedKeywordRepositoryTest
	extends BaseRepositoryTestCase<BlockedKeyword, Long> {

	@BeforeEach
	public void setUp() {
		BlockedKeyword blockedKeyword1 = new BlockedKeyword(
			DateUtil.newDate(), "bLoCkEd");

		BlockedKeyword blockedKeyword2 = new BlockedKeyword(
			DateUtil.newDate(), "kEy");

		BlockedKeyword blockedKeyword3 = new BlockedKeyword(
			DateUtil.newDate(), "WoRd");

		setUpRepository(blockedKeyword1, blockedKeyword2, blockedKeyword3);

		_blockedKeyword1 = entityModels.get(0);
		_blockedKeyword2 = entityModels.get(1);
	}

	@Test
	public void testCountByKeywordContainingIgnoreCase() {
		Assertions.assertEquals(
			1,
			_blockedKeywordRepository.countByKeywordContainingIgnoreCase(
				"BLOCKED"));
		Assertions.assertEquals(
			1,
			_blockedKeywordRepository.countByKeywordContainingIgnoreCase(
				"blocked"));
		Assertions.assertEquals(
			2,
			_blockedKeywordRepository.countByKeywordContainingIgnoreCase("KE"));
	}

	@Test
	public void testDeleteByIdIn() {
		_blockedKeywordRepository.deleteByIdIn(
			new HashSet<>(
				Arrays.asList(
					_blockedKeyword1.getId(), _blockedKeyword2.getId())));

		Assertions.assertEquals(1, _blockedKeywordRepository.count());
	}

	@Test
	public void testFindByKeywordContainingIgnoreCase() {
		List<BlockedKeyword> blockedKeywords =
			_blockedKeywordRepository.findByKeywordContainingIgnoreCase(
				"ke",
				PageRequest.of(0, 10, Sort.by(Sort.Order.desc("keyword"))));

		Assertions.assertEquals(2, blockedKeywords.size());

		BlockedKeyword blockedKeyword = blockedKeywords.get(0);

		Assertions.assertEquals("kEy", blockedKeyword.getKeyword());
	}

	@Test
	public void testFindByKeywordIn() {
		List<BlockedKeyword> blockedKeywords =
			_blockedKeywordRepository.findByKeywordIn(
				new HashSet<>(Arrays.asList("bLoCkEd", "key")));

		Assertions.assertEquals(1, blockedKeywords.size());

		BlockedKeyword blockedKeyword = blockedKeywords.get(0);

		Assertions.assertEquals("bLoCkEd", blockedKeyword.getKeyword());
	}

	@Override
	protected PagingAndSortingRepository<BlockedKeyword, Long>
		getPagingAndSortingRepository() {

		return _blockedKeywordRepository;
	}

	private BlockedKeyword _blockedKeyword1;
	private BlockedKeyword _blockedKeyword2;

	@Autowired
	private BlockedKeywordRepository _blockedKeywordRepository;

}