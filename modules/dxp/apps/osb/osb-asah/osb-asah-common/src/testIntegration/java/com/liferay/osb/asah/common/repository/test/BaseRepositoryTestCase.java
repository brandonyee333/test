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

import com.liferay.osb.asah.common.repository.OSBAsahRepository;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import org.springframework.data.domain.Persistable;

/**
 * @author Inácio Nery
 */
public abstract class BaseRepositoryTestCase<T extends Persistable<ID>, ID> {

	@After
	public void tearDown() {
		OSBAsahRepository<T, ID> osbAsahRepository = getOSBAsahRepository();

		osbAsahRepository.deleteAll();
	}

	@Test
	public void testCount() {
		OSBAsahRepository<T, ID> osbAsahRepository = getOSBAsahRepository();

		Assert.assertEquals(entityModels.size(), osbAsahRepository.count());
	}

	@Test
	public void testDelete() {
		OSBAsahRepository<T, ID> osbAsahRepository = getOSBAsahRepository();

		osbAsahRepository.delete(entityModels.get(0));

		Assert.assertEquals(entityModels.size() - 1, osbAsahRepository.count());
	}

	@Test
	public void testDeleteAll1() {
		OSBAsahRepository<T, ID> osbAsahRepository = getOSBAsahRepository();

		osbAsahRepository.deleteAll();

		Assert.assertEquals(0, osbAsahRepository.count());
	}

	@Test
	public void testDeleteAll2() {
		OSBAsahRepository<T, ?> osbAsahRepository = getOSBAsahRepository();

		osbAsahRepository.deleteAll(entityModels);

		Assert.assertEquals(0, osbAsahRepository.count());
	}

	@Test
	public void testDeleteById() {
		T model = entityModels.get(0);

		ID id = model.getId();

		Assert.assertNotNull(id);

		OSBAsahRepository<T, ID> osbAsahRepository = getOSBAsahRepository();

		osbAsahRepository.deleteById(id);

		Assert.assertEquals(entityModels.size() - 1, osbAsahRepository.count());
	}

	@Test
	public void testExistsById() {
		T model = entityModels.get(0);

		OSBAsahRepository<T, ID> osbAsahRepository = getOSBAsahRepository();

		Assert.assertTrue(osbAsahRepository.existsById(model.getId()));
	}

	@Test
	public void testFindAll() {
		OSBAsahRepository<T, ID> osbAsahRepository = getOSBAsahRepository();

		Assert.assertEquals(entityModels, osbAsahRepository.findAll());
	}

	@Test
	public void testFindAllById() {
		OSBAsahRepository<T, ID> osbAsahRepository = getOSBAsahRepository();

		Assert.assertEquals(
			entityModels,
			osbAsahRepository.findAllById(
				ListUtil.map(entityModels, T::getId)));
	}

	@Test
	public void testFindById() {
		T model = entityModels.get(0);

		ID id = model.getId();

		Assert.assertNotNull(id);

		OSBAsahRepository<T, ID> osbAsahRepository = getOSBAsahRepository();

		Optional<T> modelOptional = osbAsahRepository.findById(id);

		Assert.assertTrue(modelOptional.isPresent());
	}

	@Test
	public void testSave() {
		OSBAsahRepository<T, ID> osbAsahRepository = getOSBAsahRepository();

		Assert.assertEquals(
			entityModels.get(0), osbAsahRepository.save(entityModels.get(0)));
	}

	@Test
	public void testSaveAll() {
		OSBAsahRepository<T, ID> osbAsahRepository = getOSBAsahRepository();

		Assert.assertEquals(
			entityModels, osbAsahRepository.saveAll(entityModels));
	}

	protected abstract OSBAsahRepository<T, ID> getOSBAsahRepository();

	protected void setUpRepository(T... entityModels) {
		OSBAsahRepository<T, ID> osbAsahRepository = getOSBAsahRepository();

		this.entityModels = IterableUtils.toList(
			osbAsahRepository.saveAll(Arrays.asList(entityModels)));
	}

	protected List<T> entityModels;

}