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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.Interest;
import com.liferay.osb.asah.common.model.Distribution;
import com.liferay.osb.asah.common.postgresql.converter.helper.InterestFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.CustomInterestRepository;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.time.OffsetDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Record4;
import org.jooq.SelectSelectStep;
import org.jooq.SortField;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

/**
 * @author Robson Pastor
 */
public class InterestRepositoryImpl
	extends BaseRepository implements CustomInterestRepository {

	public InterestRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;

		InterestFilterStringConverterHelper
			interestFilterStringConverterHelper =
				new InterestFilterStringConverterHelper();

		_fieldNames =
			interestFilterStringConverterHelper.getFieldNameConversionMap();
	}

	@Override
	public long countByFilterStringAndScoreGreaterThanEqual(
		FilterHelper filterHelper, @Nullable Double score) {

		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return selectSelectStep.from(
			"Interest"
		).where(
			_getConditions(filterHelper, null, null, null, null, null, score)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public long countInterestDistributions(
		String keyword, List<String> ownerIds, String ownerType,
		Date recordedDate, Double score) {

		SelectSelectStep<Record1<Integer>> selectSelectStep1 =
			_dslContext.selectCount();

		SelectSelectStep<Record2<Integer, Object>> selectSelectStep2 =
			_dslContext.select(
				DSL.count(
				).as(
					"count"
				),
				DSL.field("name"));

		return selectSelectStep1.from(
			selectSelectStep2.from(
				"Interest"
			).where(
				_getConditions(
					null, null, keyword, ownerIds, ownerType, recordedDate,
					score)
			).groupBy(
				DSL.field("name")
			)
		).fetchOptional(
			0, Long.class
		).orElse(
			0L
		);
	}

	@Override
	public List<Interest> findByFilterStringAndScoreGreaterThanEqual(
		@Nullable FilterHelper filterHelper, @Nullable Double score,
		Pageable pageable) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Interest"
		).where(
			_getConditions(filterHelper, null, null, null, null, null, score)
		).orderBy(
			_getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
		).map(
			record -> new Interest(record.intoMap())
		);
	}

	@Override
	public List<Interest> findByNameAndOwnerIdAndRecordedDate(
		@Nullable String name, @Nullable String ownerId, Date recordedDate) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		List<String> ownerIds = new ArrayList<>();

		if (ownerId != null) {
			ownerIds.add(ownerId);
		}

		return selectSelectStep.from(
			"Interest"
		).where(
			_getConditions(null, null, name, ownerIds, null, recordedDate, null)
		).fetch(
		).map(
			record -> new Interest(record.intoMap())
		);
	}

	@Override
	public List<Interest> findByOwnerTypeAndRecordedDate(
		@Nullable Long interestId, @Nullable String ownerType,
		@Nullable Date recordedDate, int size) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return selectSelectStep.from(
			"Interest"
		).where(
			_getConditions(
				null, interestId, null, null, ownerType, recordedDate, null)
		).orderBy(
			DSL.field("id")
		).limit(
			size
		).fetch(
		).map(
			record -> new Interest(record.intoMap())
		);
	}

	@Override
	public List<String> findOwnerIdsByFilterStringAndOwnerId(
		FilterHelper filterHelper, String ownerId) {

		Field field = DSL.field("ownerId", Long.class);

		SelectSelectStep<Record1<Long>> selectSelectStep = _dslContext.select(
			field);

		List<String> ownerIds = new ArrayList<>();

		if (ownerId != null) {
			ownerIds.add(ownerId);
		}

		return selectSelectStep.from(
			"Interest"
		).where(
			_getConditions(filterHelper, null, null, ownerIds, null, null, null)
		).fetch(
			field
		);
	}

	@Override
	public List<Distribution> getInterestDistributions(
		@Nullable String keyword, @Nullable List<String> ownerIds,
		@Nullable String ownerType, @Nullable Date recordedDate,
		@Nullable Double score, Pageable pageable) {

		SelectSelectStep<Record2<Integer, Object>> selectSelectStep =
			_dslContext.select(
				DSL.count(
				).as(
					"count"
				),
				DSL.field("name"));

		return selectSelectStep.from(
			"Interest"
		).where(
			_getConditions(
				null, null, keyword, ownerIds, ownerType, recordedDate, score)
		).groupBy(
			DSL.field("name")
		).orderBy(
			_getSortFields(pageable.getSort(), null)
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch(
			record -> new Distribution(
				(Integer)record.get("count"),
				Collections.singletonList(record.getValue("name")))
		);
	}

	@Override
	public List<String> getTopNamesByOwnerIdAndOwnerType(
		String ownerId, String ownerType, int size) {

		SelectSelectStep<Record1<String>> selectSelectStep = _dslContext.select(
			DSL.field("name", String.class));

		return selectSelectStep.from(
			"Interest"
		).where(
			DSL.and(
				DSL.field(
					"ownerId"
				).equal(
					ownerId
				),
				DSL.field(
					"ownerType"
				).equal(
					ownerType
				))
		).orderBy(
			DSL.field(
				"score"
			).desc()
		).limit(
			size
		).offset(
			0
		).fetch(
			record -> (String)record.get("name")
		);
	}

	@Override
	public List<Map<String, Object>> getTransformations(
		Date fromDate, @Nullable FilterHelper filterHelper, String period,
		Date toDate) {

		Field<Date> periodField = _getPeriodField(period);

		SelectSelectStep<Record4<Date, BigDecimal, Integer, BigDecimal>>
			selectSelectStep = _dslContext.select(
				periodField,
				DSL.avg(
					DSL.field("score", Double.class)
				).as(
					"scoreAvg"
				),
				DSL.count(
					DSL.field("id")
				).as(
					"totalElements"
				),
				DSL.sum(
					DSL.field("views", Long.class)
				).as(
					"viewsSum"
				));

		List<Condition> conditions = new ArrayList<>();

		conditions.add(
			DSL.field(
				"recordedDate"
			).equal(
				DSL.field("generatedDate")
			));

		if (filterHelper != null) {
			conditions.add(filterHelper.getCondition());
		}

		DatePart datePart = DatePart.DAY;

		if (period.equals("hour")) {
			datePart = DatePart.HOUR;
		}

		return selectSelectStep.from(
			"Interest"
		).rightJoin(
			_dslHelper.getTimeSeriesTable(
				datePart, new Timestamp(fromDate.getTime()),
				new Timestamp(toDate.getTime()))
		).on(
			conditions.toArray(new Condition[0])
		).where(
			DSL.field(
				"generatedDate"
			).between(
				fromDate, toDate
			)
		).groupBy(
			periodField
		).orderBy(
			periodField
		).fetch(
			record -> new HashMap<String, Object>() {
				{
					put(
						"intervalInitDate",
						DateUtil.toUTCString(
							record.getValue("intervalInitDate", Date.class)));
					put(
						"scoreAvg",
						_getAggregationValue(
							record.getValue("scoreAvg", Double.class)));
					put(
						"totalElements",
						record.getValue("totalElements", Long.class));
					put(
						"viewsSum",
						_getAggregationValue(
							record.getValue("viewsSum", Double.class)));
				}
			}
		);
	}

	private double _getAggregationValue(Double value) {
		if ((value == null) || (value < 0)) {
			return 0;
		}

		return value;
	}

	private List<Condition> _getConditions(
		@Nullable FilterHelper filterHelper, @Nullable Long interestId,
		@Nullable String keywords, @Nullable List<String> ownerIds,
		@Nullable String ownerType, @Nullable Date recordedDate,
		@Nullable Double score) {

		List<Condition> conditions = new ArrayList<>();

		if ((filterHelper != null) &&
			!StringUtils.isEmpty(filterHelper.getFilterString())) {

			conditions.add(filterHelper.getCondition());
		}

		if (!StringUtils.isBlank(keywords)) {
			conditions.add(
				DSL.or(
					DSL.field(
						"name"
					).containsIgnoreCase(
						keywords
					)
				).or(
					DSL.field(
						"name"
					).similarTo(
						keywords
					)
				));
		}

		if (interestId != null) {
			conditions.add(
				DSL.field(
					"id"
				).gt(
					interestId
				));
		}

		if ((ownerIds != null) && !ownerIds.isEmpty()) {
			conditions.add(
				DSL.field(
					"ownerId", Long.class
				).in(
					ownerIds
				));
		}

		if (ownerType != null) {
			conditions.add(
				DSL.field(
					"ownerType"
				).eq(
					ownerType
				));
		}

		if (recordedDate != null) {
			conditions.add(
				DSL.field(
					"recordedDate"
				).eq(
					recordedDate
				));
		}

		if (score != null) {
			conditions.add(
				DSL.field(
					"score"
				).greaterOrEqual(
					score
				));
		}

		return conditions;
	}

	private Field _getPeriodField(String period) {
		if (!_validPeriods.containsKey(period)) {
			throw new IllegalArgumentException("Invalid period: " + period);
		}

		Field field = _dslHelper.dateTrunc(
			_validPeriods.get(period),
			DSL.field("generatedDate", OffsetDateTime.class));

		return field.as("intervalInitDate");
	}

	private Collection<SortField<?>> _getSortFields(Sort sort, Table<?> table) {
		List<Sort.Order> orders = new ArrayList<>();

		for (Sort.Order order : sort.toList()) {
			orders.add(
				new Sort.Order(
					order.getDirection(),
					_fieldNames.getOrDefault(
						order.getProperty(), order.getProperty())));
		}

		return getSortFields(null, Sort.by(orders), table);
	}

	private final DSLContext _dslContext;

	@Autowired
	private DSLHelper _dslHelper;

	private final Map<String, String> _fieldNames;
	private final Map<String, DatePart> _validPeriods =
		new HashMap<String, DatePart>() {
			{
				put("day", DatePart.DAY);
				put("hour", DatePart.HOUR);
				put("month", DatePart.MONTH);
				put("week", DatePart.WEEK);
			}
		};

}