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

import com.liferay.osb.asah.common.util.ListUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import org.springframework.data.domain.Persistable;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Inácio Nery
 */
public abstract class BaseRepositoryTestCase<T extends Persistable<Long>> {

	@After
	public void tearDown() {
		CrudRepository<T, Long> crudRepository = getCrudRepository();

		crudRepository.deleteAll();
	}

	@Test
	public void testCount() {
		CrudRepository<T, Long> crudRepository = getCrudRepository();

		Assert.assertEquals(entityModels.size(), crudRepository.count());
	}

	@Test
	public void testDelete() {
		CrudRepository<T, Long> crudRepository = getCrudRepository();

		crudRepository.delete(entityModels.get(0));

		Assert.assertEquals(entityModels.size() - 1, crudRepository.count());
	}

	@Test
	public void testDeleteAll1() {
		CrudRepository<T, Long> crudRepository = getCrudRepository();

		crudRepository.deleteAll();

		Assert.assertEquals(0, crudRepository.count());
	}

	@Test
	public void testDeleteAll2() {
		CrudRepository<T, Long> crudRepository = getCrudRepository();

		crudRepository.deleteAll(entityModels);

		Assert.assertEquals(0, crudRepository.count());
	}

	@Test
	public void testDeleteById() {
		T model = entityModels.get(0);

		Long id = model.getId();

		Assert.assertNotNull(id);

		CrudRepository<T, Long> crudRepository = getCrudRepository();

		crudRepository.deleteById(id);

		Assert.assertEquals(entityModels.size() - 1, crudRepository.count());
	}

	@Test
	public void testExistsById() {
		T model = entityModels.get(0);

		CrudRepository<T, Long> crudRepository = getCrudRepository();

		Assert.assertTrue(crudRepository.existsById(model.getId()));
	}

	@Test
	public void testFindAll() {
		CrudRepository<T, Long> crudRepository = getCrudRepository();

		Assert.assertEquals(entityModels, crudRepository.findAll());
	}

	@Test
	public void testFindAllById() {
		CrudRepository<T, Long> crudRepository = getCrudRepository();

		Assert.assertEquals(
			entityModels,
			crudRepository.findAllById(ListUtil.map(entityModels, T::getId)));
	}

	@Test
	public void testFindById() {
		T model = entityModels.get(0);

		Long id = model.getId();

		Assert.assertNotNull(id);

		CrudRepository<T, Long> crudRepository = getCrudRepository();

		Optional<T> modelOptional = crudRepository.findById(id);

		Assert.assertTrue(modelOptional.isPresent());
	}

	@Test
	public void testSave() {
		CrudRepository<T, Long> crudRepository = getCrudRepository();

		Assert.assertEquals(
			entityModels.get(0), crudRepository.save(entityModels.get(0)));
	}

	@Test
	public void testSaveAll() {
		CrudRepository<T, Long> crudRepository = getCrudRepository();

		Assert.assertEquals(entityModels, crudRepository.saveAll(entityModels));
	}

	protected abstract CrudRepository<T, Long> getCrudRepository();

	protected void setUpRepository(T... entityModels) {
		CrudRepository<T, Long> crudRepository = getCrudRepository();

		this.entityModels = IterableUtils.toList(
			crudRepository.saveAll(Arrays.asList(entityModels)));
	}

	protected List<T> entityModels;

}