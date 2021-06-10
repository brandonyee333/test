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

import com.liferay.osb.asah.common.entity.CSVIndividual;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Marcellus Tavares
 */
@Primary
public interface CSVIndividualRepository
	extends CrudRepository<CSVIndividual, Long> {

	public long countByDataSourceId(Long dataSourceId);

	@Modifying
	public void deleteByDataSourceId(Long dataSourceId);

	@Modifying
	public void deleteByDataSourceIdAndDataSourceIndividualPKIn(
		Long dataSourceId, List<String> dataSourceIndividualPKs);

	public List<CSVIndividual> findByDataSourceId(
		Long dataSourceId, Pageable pageable);

	public List<CSVIndividual> findByDataSourceIdAndFieldKeyEquals(
		Long dataSourceId, String fieldKey, String fieldValue);

}