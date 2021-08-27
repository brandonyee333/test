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

import com.liferay.osb.asah.common.repository.Repository;
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
		Repository<T, ID> repository = getRepository();

		repository.deleteAll();
	}

	@Test
	public void testCount() {
		Repository<T, ID> repository = getRepository();

		Assert.assertEquals(entityModels.size(), repository.count());
	}

	@Test
	public void testDelete() {
		Repository<T, ID> repository = getRepository();

		repository.delete(entityModels.get(0));

		Assert.assertEquals(entityModels.size() - 1, repository.count());
	}

	@Test
	public void testDeleteAll1() {
		Repository<T, ID> repository = getRepository();

		repository.deleteAll();

		Assert.assertEquals(0, repository.count());
	}

	@Test
	public void testDeleteAll2() {
		Repository<T, ?> repository = getRepository();

		repository.deleteAll(entityModels);

		Assert.assertEquals(0, repository.count());
	}

	@Test
	public void testDeleteById() {
		T model = entityModels.get(0);

		ID id = model.getId();

		Assert.assertNotNull(id);

		Repository<T, ID> repository = getRepository();

		repository.deleteById(id);

		Assert.assertEquals(entityModels.size() - 1, repository.count());
	}

	@Test
	public void testExistsById() {
		T model = entityModels.get(0);

		Repository<T, ID> repository = getRepository();

		Assert.assertTrue(repository.existsById(model.getId()));
	}

	@Test
	public void testFindAll() {
		Repository<T, ID> repository = getRepository();

		Assert.assertEquals(entityModels, repository.findAll());
	}

	@Test
	public void testFindAllById() {
		Repository<T, ID> repository = getRepository();

		Assert.assertEquals(
			entityModels,
			repository.findAllById(ListUtil.map(entityModels, T::getId)));
	}

	@Test
	public void testFindById() {
		T model = entityModels.get(0);

		ID id = model.getId();

		Assert.assertNotNull(id);

		Repository<T, ID> repository = getRepository();

		Optional<T> modelOptional = repository.findById(id);

		Assert.assertTrue(modelOptional.isPresent());
	}

	@Test
	public void testSave() {
		Repository<T, ID> repository = getRepository();

		Assert.assertEquals(
			entityModels.get(0), repository.save(entityModels.get(0)));
	}

	@Test
	public void testSaveAll() {
		Repository<T, ID> repository = getRepository();

		Assert.assertEquals(entityModels, repository.saveAll(entityModels));
	}

	protected abstract Repository<T, ID> getRepository();

	protected void setUpRepository(T... entityModels) {
		Repository<T, ID> repository = getRepository();

		this.entityModels = IterableUtils.toList(
			repository.saveAll(Arrays.asList(entityModels)));
	}

	protected List<T> entityModels;

}