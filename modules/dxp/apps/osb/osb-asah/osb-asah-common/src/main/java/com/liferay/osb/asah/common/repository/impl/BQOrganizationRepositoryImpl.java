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

import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.repository.CustomBQOrganizationRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.eclipse.jetty.util.StringUtil;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
public class BQOrganizationRepositoryImpl
	extends BaseRepository implements CustomBQOrganizationRepository {

	public BQOrganizationRepositoryImpl(
		DSLContext dslContext, QueryExecutor queryExecutor) {

		_dslContext = dslContext;
		_queryExecutor = queryExecutor;
	}

	@Override
	public long count() {
		return _queryExecutor.queryForLong(
			_dslContext.selectCount(
			).from(
				DSL.table("BQOrganization")
			));
	}

	@Override
	public long countByDataSourceIdsAndName(
		List<Long> dataSourceIds, @Nullable String name) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return _queryExecutor.queryForLong(
			selectSelectStep.from(
				"BQOrganization"
			).where(
				ConditionUtil.toConditions(
					dataSourceIds, name, new String[] {"name"})
			));
	}

	@Override
	public long countByName(@Nullable String name) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return _queryExecutor.queryForLong(
			selectSelectStep.from(
				"BQOrganization"
			).where(
				_getCondition(name)
			));
	}

	@Override
	public void deleteById(String id) {
		_queryExecutor.queryExecute(
			_dslContext.delete(
				DSL.table("BQOrganization")
			).where(
				DSL.field(
					"id"
				).eq(
					id
				)
			));
	}

	@Override
	public List<BQOrganization> findAll() {
		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForList(
			BQOrganization::new, selectSelectStep.from("BQOrganization"));
	}

	@Override
	public Optional<BQOrganization> findByDataSourceIdAndOrganizationId(
		Long dataSourceId, Long organizationId) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForObject(
			BQOrganization::new,
			selectSelectStep.from(
				"BQOrganization"
			).where(
				DSL.and(
					DSL.field(
						"dataSourceId"
					).eq(
						dataSourceId
					),
					DSL.field(
						"organizationId"
					).eq(
						organizationId
					))
			));
	}

	@Override
	public List<BQOrganization> findByDataSourceIdAndOrganizationIdIn(
		Long dataSourceId, Collection<Long> organizationIds) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForList(
			BQOrganization::new,
			selectSelectStep.from(
				"BQOrganization"
			).where(
				DSL.and(
					DSL.field(
						"dataSourceId"
					).eq(
						dataSourceId
					),
					DSL.field(
						"organizationId"
					).in(
						organizationIds
					))
			));
	}

	@Override
	public Optional<BQOrganization> findById(String bqOrganizationId) {
		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForObject(
			BQOrganization::new,
			selectSelectStep.from(
				"BQOrganization"
			).where(
				DSL.field(
					"id"
				).eq(
					bqOrganizationId
				)
			));
	}

	@Override
	public List<BQOrganization> findByName(
		@Nullable String name, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForList(
			BQOrganization::new,
			selectSelectStep.from(
				"BQOrganization"
			).where(
				_getCondition(name)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	@Override
	public BQOrganization insert(BQOrganization bqOrganization) {
		_queryExecutor.queryExecute(
			_dslContext.insertInto(
				DSL.table("BQOrganization")
			).columns(
				DSL.field("createDate", Date.class),
				DSL.field("dataSourceId", Long.class), DSL.field("id"),
				DSL.field("modifiedDate", Date.class), DSL.field("name"),
				DSL.field("organizationId", Long.class),
				DSL.field("parentOrganizationId", Long.class),
				DSL.field("treePath"), DSL.field("type")
			).values(
				bqOrganization.getCreateDate(),
				bqOrganization.getDataSourceId(), bqOrganization.getId(),
				bqOrganization.getModifiedDate(), bqOrganization.getName(),
				bqOrganization.getOrganizationId(),
				bqOrganization.getParentOrganizationId(),
				bqOrganization.getTreePath(), bqOrganization.getType()
			));

		return bqOrganization;
	}

	@Override
	public List<BQOrganization> searchBQOrganizations(
		FilterHelper filterHelper, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForList(
			BQOrganization::new,
			selectSelectStep.from(
				"BQOrganization"
			).where(
				filterHelper.getCondition()
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	@Override
	public List<BQOrganization> searchByDataSourceIdsAndName(
		List<Long> dataSourceIds, @Nullable String name, Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForList(
			BQOrganization::new,
			selectSelectStep.from(
				"BQOrganization"
			).where(
				ConditionUtil.toConditions(
					dataSourceIds, name, new String[] {"name"})
			).orderBy(
				getSortFields(pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	private Condition _getCondition(String name) {
		if (StringUtil.isBlank(name)) {
			return DSL.noCondition();
		}

		return DSL.condition(String.format("name like '%s'", "%" + name + "%"));
	}

	private final DSLContext _dslContext;
	private final QueryExecutor _queryExecutor;

}