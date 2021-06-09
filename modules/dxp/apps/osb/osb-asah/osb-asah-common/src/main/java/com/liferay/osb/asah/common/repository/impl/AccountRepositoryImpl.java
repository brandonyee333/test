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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.postgresql.converter.helper.AccountsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.util.ConditionUtil;
import com.liferay.osb.asah.common.util.MatcherUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.nested.InternalNested;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import org.jooq.AggregateFunction;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectOnConditionStep;
import org.jooq.SelectOrderByStep;
import org.jooq.SelectSelectStep;
import org.jooq.SortField;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

/**
 * @author Rachael Koestartyo
 */
public class AccountRepositoryImpl extends BaseRepository {

	public AccountRepositoryImpl(
		AccountsFilterStringConverterHelper accountsFilterStringConverterHelper,
		DSLContext dslContext) {

		_accountsFilterStringConverterHelper =
			accountsFilterStringConverterHelper;
		_dslContext = dslContext;
	}

	public long countAccounts(
		@Nullable Set<String> accountPKs, @Nullable String filterString) {

		Table<Record> fieldTable = _buildFieldTable(
			ConditionUtil.toCondition(
				filterString, _accountsFilterStringConverterHelper),
			null);

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		Condition condition = DSL.noCondition();

		if ((accountPKs != null) && !accountPKs.isEmpty()) {
			condition = condition.and(
				DSL.field(
					"accountPK"
				).in(
					accountPKs
				));
		}

		return selectSelectStep.from(
			"Account"
		).join(
			fieldTable
		).on(
			DSL.field(
				"id"
			).eq(
				fieldTable.field("ownerId")
			)
		).where(
			condition
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	public List<Distribution> getAccountDistributions(
		@Nullable Long channelId, String fieldName, String fieldType,
		@Nullable String filterString, @Nullable Long individualSegmentId,
		Pageable pageable) {

		Set<Long> accountIds = new HashSet<>();

		if ((channelId != null) || (individualSegmentId != null)) {
			SelectSelectStep<Record> selectSelectStep = _dslContext.select();

			selectSelectStep.select(
				DSL.field("id")
			).from(
				"Account"
			).where(
				_getCondition(channelId, individualSegmentId)
			).fetch(
			).map(
				record -> accountIds.add((Long)record.get("id"))
			);

			if (accountIds.isEmpty()) {
				return Collections.emptyList();
			}
		}

		if (fieldType.equals("Number")) {
			return _getAccountNumbersDistributions(
				accountIds, fieldName, filterString, pageable);
		}

		Condition condition = ConditionUtil.toCondition(
			filterString, _accountsFilterStringConverterHelper);

		if (!accountIds.isEmpty()) {
			condition = condition.and(
				DSL.field(
					"ownerId"
				).in(
					accountIds
				));
		}

		AggregateFunction<Object> aggregateFunction = DSL.max(
			DSL.field("modifiedDate"));
		Field<Object> modifiedDateField = DSL.field("modifiedDate");
		Field<Object> nameField = DSL.field("name");
		Field<Object> ownerIdField = DSL.field("ownerId");
		Field<Object> valueField = DSL.field("value");

		SelectSelectStep<Record> modifiedDateSelectSelectStep =
			_dslContext.select();

		Table<Record> maxModifiedDateTable =
			modifiedDateSelectSelectStep.select(
				aggregateFunction.as("modifiedDate"), nameField.as("name"),
				ownerIdField.as("ownerId")
			).from(
				"Field"
			).where(
				condition
			).groupBy(
				ownerIdField, nameField
			).asTable(
				"maxModifiedDateTable"
			);

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.select(
			DSL.count(
				ownerIdField
			).as(
				"count"
			),
			valueField.as("values")
		).from(
			"Field"
		).join(
			maxModifiedDateTable
		).on(
			DSL.and(
				modifiedDateField.eq(
					maxModifiedDateTable.field("modifiedDate")),
				DSL.field(
					"field.name"
				).eq(
					maxModifiedDateTable.field("name")
				),
				ownerIdField.eq(maxModifiedDateTable.field("ownerId")))
		).where(
			DSL.field(
				"field.name"
			).eq(
				fieldName
			)
		).groupBy(
			valueField
		).orderBy(
			getSortFields(
				_getSortFieldNameConversionMap(), pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Distribution(
				(Integer)record.get("count"),
				Collections.singletonList(record.get("values")))
		);
	}

	public List<Transformation> getAccountTransformations(
		String apply, @Nullable Long channelId, @Nullable String filterString,
		Pageable pageable) {

		Matcher matcher = MatcherUtil.getMatcher(apply);

		if (!matcher.matches()) {
			throw new IllegalArgumentException(
				"Apply string " + apply + " does not match pattern " +
					MatcherUtil.getGroupByPattern());
		}

		String containsField = matcher.group("containsField");

		String groupByField = matcher.group("groupByField");

		String fieldName = groupByField.replace("organization/", "");

		fieldName = fieldName.replace("/value", "");

		AggregateFunction<Object> aggregateFunction = DSL.max(
			DSL.field("modifiedDate"));
		Field<Object> modifiedDateField = DSL.field("modifiedDate");
		Field<Object> nameField = DSL.field("name");
		Field<Object> ownerIdField = DSL.field("ownerId");
		Field<Object> valueField = DSL.field("value");

		Condition condition = ConditionUtil.toCondition(
			filterString, _accountsFilterStringConverterHelper);

		condition = condition.and(_getIncludeCondition(containsField));

		SelectSelectStep<Record> modifiedDateSelectSelectStep =
			_dslContext.select();

		Table<Record> maxModifiedDateTable =
			modifiedDateSelectSelectStep.select(
				aggregateFunction.as("modifiedDate"), nameField.as("name"),
				ownerIdField.as("ownerId")
			).from(
				"Field"
			).where(
				condition.and(nameField.eq(fieldName))
			).groupBy(
				ownerIdField, nameField
			).asTable(
				"maxModifiedDateTable"
			);

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.select(
			valueField.as("terms"),
			DSL.count(
				ownerIdField
			).as(
				"totalelements"
			)
		).from(
			"Field"
		).join(
			maxModifiedDateTable
		).on(
			DSL.and(
				modifiedDateField.eq(maxModifiedDateTable.field("modifiedDate"))
			).and(
				nameField.eq(maxModifiedDateTable.field("name"))
			).and(
				ownerIdField.eq(maxModifiedDateTable.field("ownerId"))
			)
		).groupBy(
			valueField
		).orderBy(
			getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Transformation(
				new Transformation.Term(
					Collections.singletonMap(
						groupByField, record.get("terms"))),
				(Integer)record.get("totalelements"))
		);
	}

	public List<Account> searchAccounts(
		@Nullable Set<String> accountPKs, @Nullable Long channelId,
		@Nullable String filterString, Pageable pageable,
		@Nullable Sort segmentSort) {

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
					fieldTable.field("ownerId")
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
					segmentTable.field("accountId")
				)
			).orderBy(
				getSortFields(segmentSort, segmentTable)
			);
		}

		if ((accountPKs != null) && !accountPKs.isEmpty()) {
			selectOnConditionStep.where(
				DSL.field(
					"account.accountPK"
				).in(
					accountPKs
				));
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

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		AggregateFunction<Object> aggregateFunction = DSL.max(
			DSL.field("modifiedDate"));
		Field<Object> nameField = DSL.field("name");
		Field<Object> ownerIdField = DSL.field("ownerId");

		selectSelectStep.select(
			aggregateFunction.as("modifiedDate"), nameField, ownerIdField
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
				"fieldTable"
			);
		}

		return selectSelectStep.asTable("fieldTable");
	}

	private Table<Record> _buildSegmentTable() {
		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Field<Long> accountIdField = DSL.replace(
			DSL.field("name", String.class), "Account: ", ""
		).cast(
			Long.class
		).as(
			"accountId"
		);
		Field<Object> activitiesCountField = DSL.field("activitiesCount");
		Field<Object> individualCountField = DSL.field("individualCount");

		return selectSelectStep.select(
			accountIdField, activitiesCountField, individualCountField
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

	private List<Distribution> _getAccountNumbersDistributions(
		Set<Long> accountIds, String fieldName, String filterString,
		Pageable pageable) {

		AggregateFunction<Object> maxModifiedDateField = DSL.max(
			DSL.field("modifiedDate"));
		Field<Object> nameField = DSL.field("name");
		Field<Object> ownerIdField = DSL.field("ownerId");
		Field<Integer> valueField = DSL.field(
			"value"
		).cast(
			Integer.class
		);

		Condition condition = ConditionUtil.toCondition(
			filterString, _accountsFilterStringConverterHelper);

		if (!accountIds.isEmpty()) {
			condition = condition.and(ownerIdField.in(accountIds));
		}

		SelectSelectStep<Record> maxModifiedDateSelectSelectStep =
			_dslContext.select();

		Table<Record> maxModifiedDateTable =
			maxModifiedDateSelectSelectStep.select(
				maxModifiedDateField.as("modifiedDate"),
				ownerIdField.as("ownerId")
			).from(
				"Field"
			).where(
				condition
			).groupBy(
				ownerIdField
			).asTable(
				"maxModifiedDateTable"
			);

		SelectSelectStep<Record> fieldsSelectSelectStep = _dslContext.select();

		Table<Record> fieldTable = fieldsSelectSelectStep.from(
			"Field"
		).join(
			maxModifiedDateTable
		).on(
			DSL.and(
				DSL.field(
					"modifiedDate"
				).eq(
					maxModifiedDateTable.field("modifiedDate")
				),
				ownerIdField.eq(maxModifiedDateTable.field("ownerId")))
		).where(
			nameField.eq(fieldName)
		).asTable(
			"fieldTable"
		);

		Field<Integer> maxValueField = DSL.max(valueField);
		Field<Integer> minValueField = DSL.min(valueField);

		final Integer[] values = new Integer[2];

		SelectSelectStep<Record> maxAndMinValuesSelectSelectStep =
			_dslContext.select();

		maxAndMinValuesSelectSelectStep.select(
			maxValueField.as("max"), minValueField.as("min")
		).from(
			fieldTable
		).fetch(
		).map(
			record -> {
				values[0] = (Integer)record.get("max");
				values[1] = (Integer)record.get("min");

				return null;
			}
		);

		int maxValue = values[0];
		int minValue = values[1];

		int size = pageable.getPageSize();

		if ((maxValue == minValue) || (size == 1)) {
			SelectSelectStep<Record> selectSelectStep = _dslContext.select();

			return selectSelectStep.select(
				DSL.val(
					minValue
				).as(
					"min"
				),
				DSL.val(
					maxValue
				).as(
					"max"
				),
				DSL.count(
					DSL.asterisk()
				).as(
					"count"
				)
			).from(
				fieldTable
			).where(
				valueField.eq(minValue)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			).fetch(
			).map(
				record -> new Distribution(
					(Integer)record.get("count"),
					new LinkedList<Object>() {
						{
							add(record.get("min"));
							add(record.get("max"));
						}
					})
			);
		}

		double diff = maxValue - minValue;

		double binSize = diff / size;

		if ((binSize != Math.ceil(binSize)) && (diff > 1)) {
			binSize = Math.ceil(binSize);
		}

		List<SelectConditionStep<Record>> selectConditionSteps =
			new ArrayList<>();

		for (int i = 0; i < size; i++) {
			Double from = minValue + (i * binSize);
			Double to = minValue + ((i + 1) * binSize);

			SelectSelectStep<Record> selectSelectStep = _dslContext.select();

			SelectJoinStep<Record> selectJoinStep = selectSelectStep.select(
				DSL.val(
					from.intValue()
				).as(
					"min"
				),
				DSL.val(
					to.intValue()
				).as(
					"max"
				),
				DSL.count(
					DSL.asterisk()
				).as(
					"count"
				)
			).from(
				fieldTable
			);

			if ((to == maxValue) && (i == (size - 1))) {
				selectConditionSteps.add(
					selectJoinStep.where(
						DSL.and(
							valueField.ge(from.intValue()),
							valueField.le(to.intValue()))));
			}
			else {
				selectConditionSteps.add(
					selectJoinStep.where(
						DSL.and(
							valueField.ge(from.intValue()),
							valueField.lt(to.intValue()))));
			}
		}

		SelectOrderByStep<Record> selectOrderByStep = selectConditionSteps.get(
			0);

		if (selectConditionSteps.size() > 1) {
			for (int i = 1; i < selectConditionSteps.size(); i++) {
				selectOrderByStep.union(selectConditionSteps.get(i));
			}
		}

		Sort sort = pageable.getSort();

		Collection<SortField<?>> sortFields = new ArrayList<>();

		for (Sort.Order order : sort.toList()) {
			Field<?> field = DSL.field(order.getProperty());

			if (StringUtils.equals(order.getProperty(), "values")) {
				field = DSL.field(
					"min"
				).cast(
					Integer.class
				);
			}

			if (order.getDirection() == Sort.Direction.ASC) {
				sortFields.add(field.asc());
			}
			else {
				sortFields.add(field.desc());
			}
		}

		return selectOrderByStep.orderBy(
			sortFields
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Distribution(
				(Integer)record.get("count"),
				new LinkedList<Object>() {
					{
						add(record.get("min"));
						add(record.get("max"));
					}
				})
		);
	}

	private Condition _getCondition(Long channelId, Long individualSegmentId) {
		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			"individuals",
			searchSourceBuilder -> {
				NestedAggregationBuilder nestedAggregationBuilder =
					AggregationBuilders.nested(
						"accounts", "dataSourceAccountPKs");

				nestedAggregationBuilder.subAggregation(
					AggregationBuilders.terms(
						"accountPKs"
					).field(
						"dataSourceAccountPKs.accountPKs"
					).size(
						Integer.MAX_VALUE
					));

				searchSourceBuilder.aggregation(nestedAggregationBuilder);

				BoolQueryBuilder individualsBoolQueryBuilder =
					QueryBuilders.boolQuery();

				if (channelId != null) {
					BoolQueryBuilderUtil.filterTerm(
						individualsBoolQueryBuilder, "channelIds",
						String.valueOf(channelId));
				}

				if (individualSegmentId != null) {
					BoolQueryBuilderUtil.filterTerm(
						individualsBoolQueryBuilder, "individualSegmentIds",
						String.valueOf(individualSegmentId));
				}

				searchSourceBuilder.query(individualsBoolQueryBuilder);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		InternalNested internalNested = aggregations.get("accounts");

		Aggregations nestedAggregations = internalNested.getAggregations();

		Terms terms = nestedAggregations.get("accountPKs");

		Set<String> accountPKs = new HashSet<>();

		for (Terms.Bucket bucket : terms.getBuckets()) {
			accountPKs.add(bucket.getKeyAsString());
		}

		Field<Object> accountPKField = DSL.field("accountPK");

		return accountPKField.in(accountPKs);
	}

	private Condition _getIncludeCondition(String contains) {
		if (contains == null) {
			return DSL.noCondition();
		}

		return DSL.field(
			"value"
		).containsIgnoreCase(
			contains
		);
	}

	private Map<String, String> _getSortFieldNameConversionMap() {
		return Collections.singletonMap("name", "values");
	}

	private final AccountsFilterStringConverterHelper
		_accountsFilterStringConverterHelper;
	private final DSLContext _dslContext;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}