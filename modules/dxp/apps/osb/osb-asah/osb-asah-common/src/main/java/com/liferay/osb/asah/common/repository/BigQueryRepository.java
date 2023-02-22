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

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * @author Marcellus Tavares
 */
@NoRepositoryBean
public interface BigQueryRepository<T, ID> extends Repository<T, ID> {

	public long count();

	public Iterable<T> findAll();

	public Iterable<T> findAll(Pageable pageable);

	public Iterable<T> findAll(Sort sort);

	public Optional<T> findById(ID id);

}