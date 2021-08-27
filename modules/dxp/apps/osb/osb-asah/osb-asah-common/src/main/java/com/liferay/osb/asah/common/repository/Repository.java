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

package com.liferay.osb.asah.common.repository;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Inácio Nery
 */
@NoRepositoryBean
public interface Repository<T, ID> extends CrudRepository<T, ID> {

	@Cacheable
	@Override
	public long count();

	@CacheEvict(allEntries = true)
	@Modifying
	@Override
	public void delete(T entity);

	@CacheEvict(allEntries = true)
	@Modifying
	@Override
	public void deleteAll();

	@CacheEvict(allEntries = true)
	@Modifying
	@Override
	public void deleteAll(Iterable<? extends T> entities);

	@CacheEvict(allEntries = true)
	@Modifying
	@Override
	public void deleteById(ID id);

	@Cacheable
	@Override
	public boolean existsById(ID id);

	@Cacheable
	@Override
	public Iterable<T> findAll();

	@Cacheable
	@Override
	public Iterable<T> findAllById(Iterable<ID> ids);

	@Cacheable
	@Override
	public Optional<T> findById(ID id);

	@CacheEvict(allEntries = true)
	@Modifying
	@Override
	public <S extends T> S save(S entity);

	@CacheEvict(allEntries = true)
	@Modifying
	@Override
	public <S extends T> Iterable<S> saveAll(Iterable<S> entities);

}