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

package com.liferay.osb.asah.common.repository.impl;

import com.liferay.osb.asah.common.entity.DataSourceFieldMapping;
import com.liferay.osb.asah.common.repository.DataSourceFieldMappingRepository;

import java.util.Collection;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import org.springframework.stereotype.Repository;

/**
 * @author Marcos Martins
 */
@Repository
public class DataSourceFieldMappingRepositoryImpl
	implements DataSourceFieldMappingRepository {

	public DataSourceFieldMappingRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public List<DataSourceFieldMapping> findByFieldMappingIds(
		Collection<Long> fieldMappingIds) {

		return _dslContext.select(
			DSL.asterisk()
		).from(
			"DataSourceFieldMapping"
		).where(
			DSL.field(
				"fieldMappingId"
			).in(
				fieldMappingIds
			)
		).fetch(
			record -> new DataSourceFieldMapping(record.intoMap())
		);
	}

	private final DSLContext _dslContext;

}