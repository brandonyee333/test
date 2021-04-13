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

import com.liferay.osb.asah.common.model.Account;
import com.liferay.osb.asah.common.postgresql.converter.helper.AccountsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;

import java.util.List;

import org.jooq.AggregateFunction;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectOnConditionStep;
import org.jooq.SelectSelectStep;
import org.jooq.SortField;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Rachael Koestartyo
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class AccountRepositoryImpl extends BaseRepository {

	public AccountRepositoryImpl(
		AccountsFilterStringConverterHelper accountsFilterStringConverterHelper,
		DSLContext dslContext) {

		_accountsFilterStringConverterHelper =
			accountsFilterStringConverterHelper;
		_dslContext = dslContext;
	}

	public long countAccounts(String filterString) {
		Table<Record> fieldTable = _buildFieldTable(
			ConditionUtil.toCondition(
				filterString, _accountsFilterStringConverterHelper),
			null);

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Account"
		).join(
			fieldTable
		).on(
			DSL.field(
				"id"
			).eq(
				fieldTable.field("fieldTable_ownerId")
			)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<Account> searchAccounts(
		Long channelId, String filterString, Pageable pageable,
		Sort segmentSort) {

		SortField sortField = null;

		if (segmentSort != null) {
			for (Sort.Order order : segmentSort.toList()) {
				String fieldName = order.getProperty();

				if (fieldName.startsWith("organization/")) {
					Field<?> field = DSL.field("value");

					if (order.getDirection() == Sort.Direction.ASC) {
						sortField = field.asc();
					}
					else {
						sortField = field.desc();
					}

					break;
				}
			}
		}

		Table<Record> fieldTable = _buildFieldTable(
			ConditionUtil.toCondition(
				filterString, _accountsFilterStringConverterHelper),
			sortField);

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		SelectOnConditionStep<Record> selectOnConditionStep =
			selectSelectStep.from(
				"Account"
			).join(
				fieldTable
			).on(
				DSL.field(
					"account.id"
				).eq(
					fieldTable.field("fieldTable_ownerId")
				)
			);

		if (segmentSort != null) {
			Table<Record> segmentTable = _buildSegmentTable();

			selectOnConditionStep.join(
				segmentTable
			).on(
				DSL.field(
					"account.id"
				).eq(
					segmentTable.field("segmentTable_accountId")
				)
			).orderBy(
				getSortFields(segmentSort, segmentTable)
			);
		}

		if (pageable.isPaged()) {
			selectOnConditionStep.limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			);
		}

		return selectOnConditionStep.fetch(
		).map(
			record -> new Account(record.intoMap())
		);
	}

	private Table<Record> _buildFieldTable(
		Condition condition, SortField sortField) {

		Field<Object> modifiedDateField = DSL.field("modifiedDate");
		Field<Object> nameField = DSL.field("name");
		Field<Object> ownerIdField = DSL.field("ownerId");

		Table<Record> maxModifiedDateTable = _buildMaxModifiedDateTable(
			condition, sortField);

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.select(
			ownerIdField.as("fieldTable_ownerId")
		).from(
			"Field"
		).join(
			maxModifiedDateTable
		).on(
			DSL.and(
				modifiedDateField.eq(
					maxModifiedDateTable.field(
						"maxModifiedDateTable_modifiedDate"))
			).and(
				nameField.eq(
					maxModifiedDateTable.field("maxModifiedDateTable_name"))
			).and(
				ownerIdField.eq(
					maxModifiedDateTable.field("maxModifiedDateTable_ownerId"))
			)
		).groupBy(
			ownerIdField
		).asTable(
			"fieldTable"
		);
	}

	private Table<Record> _buildMaxModifiedDateTable(
		Condition condition, SortField sortField) {

		Field<Object> modifiedDateField = DSL.field("modifiedDate");
		Field<Object> nameField = DSL.field("name");
		Field<Object> ownerIdField = DSL.field("ownerId");

		AggregateFunction<Object> aggregateFunction = DSL.max(
			modifiedDateField);

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		selectSelectStep.select(
			aggregateFunction.as("maxModifiedDateTable_modifiedDate"),
			nameField.as("maxModifiedDateTable_name"),
			ownerIdField.as("maxModifiedDateTable_ownerId")
		).from(
			"Field"
		).where(
			condition
		).groupBy(
			ownerIdField, nameField
		);

		if (sortField != null) {
			return selectSelectStep.orderBy(
				sortField
			).asTable(
				"maxModifiedDateTable"
			);
		}

		return selectSelectStep.asTable("maxModifiedDateTable");
	}

	private Table<Record> _buildSegmentTable() {
		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.select(
			DSL.field("activitiesCount"), DSL.field("individualCount"),
			DSL.replace(
				DSL.field("name", String.class), "Account: ", ""
			).cast(
				Long.class
			).as(
				"segmentTable_accountId"
			)
		).from(
			"Segment"
		).where(
			DSL.and(
				DSL.field(
					"name"
				).similarTo(
					"Account: %"
				),
				DSL.field(
					"status"
				).eq(
					"INACTIVE"
				))
		).asTable(
			"segmentTable"
		);
	}

	private final AccountsFilterStringConverterHelper
		_accountsFilterStringConverterHelper;
	private final DSLContext _dslContext;

}