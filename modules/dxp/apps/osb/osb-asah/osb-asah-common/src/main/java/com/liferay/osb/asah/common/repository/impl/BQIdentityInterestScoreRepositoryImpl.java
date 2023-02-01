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
import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.entity.BQIdentityInterestScore;
import com.liferay.osb.asah.common.model.Composition;
import com.liferay.osb.asah.common.model.CompositionResultBag;
import com.liferay.osb.asah.common.model.IdentityInterestScore;
import com.liferay.osb.asah.common.postgresql.converter.helper.InterestFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.CustomBQIdentityInterestScoreRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.DatePart;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.SelectFinalStep;
import org.jooq.SelectJoinStep;
import org.jooq.SelectOnConditionStep;
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
public class BQIdentityInterestScoreRepositoryImpl
	extends BaseRepository implements CustomBQIdentityInterestScoreRepository {

	public BQIdentityInterestScoreRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;

		InterestFilterStringConverterHelper
			interestFilterStringConverterHelper =
				new InterestFilterStringConverterHelper();

		_fieldNames =
			interestFilterStringConverterHelper.getFieldNameConversionMap();
	}

	@Override
	public long countByFilterString(FilterHelper filterHelper) {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return _queryExecutor.queryForLong(
			selectSelectStep.from(
				"BQIdentityInterestScore"
			).join(
				"BQIdentity"
			).on(
				DSL.field(
					"BQIdentityInterestScore.identityId"
				).eq(
					DSL.field("BQIdentity.id")
				)
			).where(
				_getConditions(
					filterHelper, Boolean.TRUE, null, null, null, null)
			));
	}

	@Override
	public long countByIndividualId(String individualId) {
		return _queryExecutor.queryForLong(
			_dslContext.selectCount(
			).from(
				"BQIdentityInterestScore"
			).join(
				"BQIdentity"
			).on(
				DSL.field(
					"BQIdentityInterestScore.identityId"
				).eq(
					DSL.field("BQIdentity.id")
				)
			).where(
				DSL.field(
					"BQIdentity.individualId"
				).eq(
					individualId
				)
			));
	}

	public long countKeywords(@Nullable String keywords) {
		return _queryExecutor.queryForLong(
			_dslContext.select(
				DSL.countDistinct(DSL.field("keyword", String.class))
			).from(
				"BQIdentityInterestScore"
			).where(
				_getConditions(null, null, null, null, keywords, null)
			));
	}

	@Override
	public List<IdentityInterestScore> findByFilterString(
		@Nullable FilterHelper filterHelper, Pageable pageable) {

		return _queryExecutor.queryForList(
			record -> new IdentityInterestScore(
				new BQIdentityInterestScore(record),
				(String)record.get("individualId")),
			_dslContext.select(
				DSL.field(
					"BQIdentityInterestScore.id"
				).as(
					"id"
				),
				DSL.field(
					"BQIdentityInterestScore.identityId"
				).as(
					"identityId"
				),
				DSL.field(
					"BQIdentityInterestScore.interestScore"
				).as(
					"interestScore"
				),
				DSL.field(
					"BQIdentityInterestScore.keyword"
				).as(
					"keyword"
				),
				DSL.field(
					"BQIdentityInterestScore.recordedDate"
				).as(
					"recordedDate"
				),
				DSL.field(
					"BQIdentity.individualId"
				).as(
					"individualId"
				)
			).from(
				"BQIdentityInterestScore"
			).join(
				"BQIdentity"
			).on(
				DSL.field(
					"BQIdentityInterestScore.identityId"
				).eq(
					DSL.field("BQIdentity.id")
				)
			).where(
				_getConditions(
					filterHelper, Boolean.TRUE, null, null, null, null)
			).orderBy(
				_getSortFields(pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	@Override
	public List<BQIdentityInterestScore> findByIndividualId(
		String individualId, Pageable pageable) {

		return _queryExecutor.queryForList(
			BQIdentityInterestScore::new,
			_dslContext.selectDistinct(
				DSL.field(
					"BQIdentityInterestScore.id"
				).as(
					"id"
				),
				DSL.field(
					"BQIdentityInterestScore.identityId"
				).as(
					"identityId"
				),
				DSL.field(
					"BQIdentityInterestScore.interestScore"
				).as(
					"interestScore"
				),
				DSL.field(
					"BQIdentityInterestScore.keyword"
				).as(
					"keyword"
				),
				DSL.field(
					"BQIdentityInterestScore.recordedDate"
				).as(
					"recordedDate"
				)
			).from(
				"BQIdentityInterestScore"
			).join(
				"BQIdentity"
			).on(
				DSL.field(
					"BQIdentityInterestScore.identityId"
				).eq(
					DSL.field("BQIdentity.id")
				)
			).where(
				DSL.field(
					"BQIdentity.individualId"
				).eq(
					individualId
				)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	@Override
	public List<BQIdentityInterestScore>
		findByIndividualIdAndKeywordAndRecordedDateBetween(
			String individualId, String keyword, Date recordedDate1,
			Date recordedDate2) {

		return _queryExecutor.queryForList(
			BQIdentityInterestScore::new,
			_dslContext.selectDistinct(
				DSL.field(
					"BQIdentityInterestScore.id"
				).as(
					"id"
				),
				DSL.field(
					"BQIdentityInterestScore.identityId"
				).as(
					"identityId"
				),
				DSL.field(
					"BQIdentityInterestScore.interestScore"
				).as(
					"interestScore"
				),
				DSL.field(
					"BQIdentityInterestScore.keyword"
				).as(
					"keyword"
				),
				DSL.field(
					"BQIdentityInterestScore.recordedDate"
				).as(
					"recordedDate"
				)
			).from(
				"BQIdentityInterestScore"
			).join(
				"BQIdentity"
			).on(
				DSL.field(
					"BQIdentityInterestScore.identityId"
				).eq(
					DSL.field("BQIdentity.id")
				)
			).where(
				DSL.field(
					"BQIdentity.individualId"
				).eq(
					individualId
				)
			).and(
				DSL.field(
					"BQIdentityInterestScore.keyword"
				).eq(
					keyword
				)
			).and(
				DSL.field(
					"BQIdentityInterestScore.recordedDate"
				).between(
					recordedDate1, recordedDate2
				)
			));
	}

	@Override
	public List<BQIdentityInterestScore> findByRecordedDate(
		@Nullable Long interestId, @Nullable Date recordedDate, int size) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForList(
			BQIdentityInterestScore::new,
			selectSelectStep.from(
				"BQIdentityInterestScore"
			).where(
				_getConditions(null, null, null, null, null, recordedDate)
			).orderBy(
				DSL.field("id")
			).limit(
				size
			));
	}

	@Override
	public Optional<IdentityInterestScore> findIdentityInterestScoreById(
		Long id) {

		return _queryExecutor.queryForObject(
			record -> new IdentityInterestScore(
				new BQIdentityInterestScore(record),
				(String)record.get("individualId")),
			(SelectFinalStep)_dslContext.select(
				DSL.field(
					"BQIdentityInterestScore.id"
				).as(
					"id"
				),
				DSL.field(
					"BQIdentityInterestScore.identityId"
				).as(
					"identityId"
				),
				DSL.field(
					"BQIdentityInterestScore.interestScore"
				).as(
					"interestScore"
				),
				DSL.field(
					"BQIdentityInterestScore.keyword"
				).as(
					"keyword"
				),
				DSL.field(
					"BQIdentityInterestScore.recordedDate"
				).as(
					"recordedDate"
				),
				DSL.field(
					"BQIdentity.individualId"
				).as(
					"individualId"
				)
			).from(
				"BQIdentityInterestScore"
			).join(
				"BQIdentity"
			).on(
				DSL.field(
					"BQIdentityInterestScore.identityId"
				).eq(
					DSL.field("BQIdentity.id")
				)
			).where(
				DSL.field(
					"BQIdentityInterestScore.id"
				).eq(
					id
				)
			));
	}

	@Override
	public List<String> findIndividualIdsByFilterStringAndIndividualId(
		FilterHelper filterHelper, String individualId) {

		Field<String> field = DSL.field("individualId", String.class);

		SelectJoinStep<Record1<String>> selectJoinStep = _dslContext.select(
			field
		).from(
			"BQIdentityInterestScore"
		);

		List<String> individualIds = new ArrayList<>();

		if (individualId != null) {
			individualIds.add(individualId);
		}

		selectJoinStep = _getBQIdentitySelectJoinStep(
			individualIds, selectJoinStep);

		return _queryExecutor.queryForList(
			record -> (String)record.get("individualId"),
			selectJoinStep.where(
				_getConditions(
					filterHelper, null, null, individualIds, null, null)));
	}

	@Override
	public BQIdentityInterestScore getByIndividualIdAndKeywordAndRecordedDate(
		String individualId, String keyword, Date recordedDate) {

		SelectSelectStep selectSelectStep = _dslContext.selectDistinct(
			DSL.field(
				"BQIdentityInterestScore.id"
			).as(
				"id"
			),
			DSL.field(
				"BQIdentityInterestScore.identityId"
			).as(
				"identityId"
			),
			DSL.field(
				"BQIdentityInterestScore.interestScore"
			).as(
				"interestScore"
			),
			DSL.field(
				"BQIdentityInterestScore.keyword"
			).as(
				"keyword"
			),
			DSL.field(
				"BQIdentityInterestScore.recordedDate"
			).as(
				"recordedDate"
			));

		Optional<BQIdentityInterestScore> bqIdentityInterestScoreOptional =
			_queryExecutor.queryForObject(
				BQIdentityInterestScore::new,
				selectSelectStep.from(
					"BQIdentityInterestScore"
				).join(
					"BQIdentity"
				).on(
					DSL.field(
						"BQIdentityInterestScore.identityId"
					).eq(
						DSL.field("BQIdentity.id")
					)
				).where(
					DSL.field(
						"BQIdentity.individualId"
					).eq(
						individualId
					),
					DSL.field(
						"BQIdentityInterestScore.keyword"
					).eq(
						keyword
					),
					DSL.field(
						"BQIdentityInterestScore.recordedDate"
					).eq(
						recordedDate
					)
				));

		return bqIdentityInterestScoreOptional.orElseThrow(
			IllegalArgumentException::new);
	}

	@Override
	public CompositionResultBag getInterestCompositionResultBag(
		boolean active, @Nullable Long channelId, @Nullable String keywords,
		@Nullable Long segmentId, Pageable pageable) {

		SelectOnConditionStep<Record2<String, String>> selectSelectStep =
			_dslContext.select(
				DSL.field("BQIdentityActivity.identityId", String.class),
				DSL.field("BQIdentityInterestScore.keyword", String.class)
			).from(
				"BQIdentityInterestScore"
			).join(
				"BQIdentityActivity"
			).on(
				DSL.field(
					"BQIdentityInterestScore.identityId"
				).eq(
					DSL.field("BQIdentityActivity.identityId")
				)
			);

		List<Condition> conditions = new ArrayList<>();

		if (active) {
			LocalDateTime newDayLocalDateTime = DateUtil.newDayLocalDateTime(
				TimeZoneDogUtil.getZoneId());

			conditions.add(
				DSL.field(
					"BQIdentityActivity.lastActivityDate", Date.class
				).ge(
					DateUtil.toUTCDate(newDayLocalDateTime.minusDays(30))
				));
		}

		if (channelId != null) {
			conditions.add(
				DSL.field(
					"BQIdentityActivity.channelId", Long.class
				).eq(
					channelId
				));
		}

		conditions.add(
			DSL.field(
				"BQIdentityInterestScore.interested", Boolean.class
			).eq(
				Boolean.TRUE
			));

		if (!StringUtils.isBlank(keywords)) {
			conditions.add(_dslHelper.containsSubstring("keyword", keywords));
		}

		if (segmentId != null) {
			selectSelectStep = selectSelectStep.join(
				"BQMembership"
			).on(
				DSL.and(
					DSL.field(
						"BQIdentityActivity.identityId"
					).eq(
						DSL.field("BQMembership.identityId")
					),
					DSL.field(
						"BQMembership.segmentId", Long.class
					).eq(
						segmentId
					))
			);
		}

		List<Map<String, Object>> records = _queryExecutor.queryForList(
			Function.identity(),
			_dslContext.with(
				"KeywordIdentity"
			).as(
				selectSelectStep.where(conditions)
			).with(
				"IdentitySummary"
			).as(
				_dslContext.select(
					DSL.countDistinct(
						DSL.field("identityId")
					).as(
						"totalCount"
					)
				).from(
					"KeywordIdentity"
				)
			).select(
				DSL.count(
				).as(
					"count"
				),
				DSL.field("keyword"),
				DSL.max(
					DSL.count()
				).over(
				).as(
					"maxCount"
				),
				DSL.count(
				).over(
				).as(
					"total"
				),
				DSL.max(
					DSL.field("totalCount")
				).as(
					"totalCount"
				)
			).from(
				DSL.table(
					"KeywordIdentity"
				).crossJoin(
					"IdentitySummary"
				)
			).groupBy(
				DSL.field("keyword")
			).orderBy(
				_getSortFields(pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));

		BigDecimal maxCountBigDecimal = BigDecimal.ZERO;
		BigDecimal totalBigDecimal = BigDecimal.ZERO;
		BigDecimal totalCountBigDecimal = BigDecimal.ZERO;

		List<Composition> compositions = new ArrayList<>();

		for (int i = 0; i < records.size(); i++) {
			Map<String, Object> record = records.get(i);

			if (i == 0) {
				maxCountBigDecimal = new BigDecimal(
					String.valueOf(record.get("maxCount")));
				totalBigDecimal = new BigDecimal(
					String.valueOf(record.get("total")));
				totalCountBigDecimal = new BigDecimal(
					String.valueOf(record.get("totalCount")));
			}

			BigDecimal count = new BigDecimal(
				String.valueOf(record.get("count")));

			compositions.add(
				new Composition(
					count.longValue(), (String)record.get("keyword")));
		}

		return new CompositionResultBag(
			maxCountBigDecimal.longValue(), compositions,
			totalBigDecimal.longValue(), totalCountBigDecimal.longValue());
	}

	public List<String> getKeywords(
		@Nullable String keywords, Pageable pageable) {

		Field<String> field = DSL.field("keyword", String.class);

		SelectSelectStep<Record1<String>> selectSelectStep =
			_dslContext.selectDistinct(field);

		return _queryExecutor.queryForList(
			record -> (String)record.get("keyword"),
			selectSelectStep.from(
				"BQIdentityInterestScore"
			).where(
				_getConditions(null, null, null, null, keywords, null)
			).orderBy(
				field.asc()
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	@Override
	public List<String> getTopKeywordsByIndividualId(
		String individualId, int size) {

		SelectSelectStep<Record1<String>> selectSelectStep = _dslContext.select(
			DSL.field("keyword", String.class));

		return selectSelectStep.from(
			"BQIdentityInterestScore"
		).join(
			"BQIdentity"
		).on(
			DSL.field(
				"BQIdentityInterestScore.identityId"
			).eq(
				DSL.field("BQIdentity.id")
			)
		).where(
			DSL.and(
				DSL.field(
					"individualId"
				).equal(
					individualId
				))
		).orderBy(
			DSL.field(
				"interestScore"
			).desc()
		).limit(
			size
		).offset(
			0
		).fetch(
			record -> (String)record.get("keyword")
		);
	}

	@Override
	public List<Map<String, Object>> getTransformations(
		Date fromDate, @Nullable FilterHelper filterHelper, String period,
		Date toDate) {

		Field<Date> periodField = _getPeriodField(period);

		SelectSelectStep<Record3<Date, BigDecimal, Integer>> selectSelectStep =
			_dslContext.select(
				periodField,
				DSL.avg(
					DSL.field("interestScore", Double.class)
				).as(
					"scoreAvg"
				),
				DSL.count(
					DSL.field("id")
				).as(
					"totalElements"
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
			"BQIdentityInterestScore"
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

	private <T extends Record> SelectJoinStep<T> _getBQIdentitySelectJoinStep(
		List<String> individualIds, SelectJoinStep<T> selectJoinStep) {

		if ((individualIds != null) && !individualIds.isEmpty()) {
			selectJoinStep = selectJoinStep.join(
				"BQIdentity"
			).on(
				DSL.field(
					"BQIdentityInterestScore.identityId"
				).eq(
					DSL.field("BQIdentity.id")
				)
			);
		}

		return selectJoinStep;
	}

	private List<Condition> _getConditions(
		@Nullable FilterHelper filterHelper, @Nullable Boolean interested,
		@Nullable Long interestId, @Nullable List<String> individualIds,
		@Nullable String keywords, @Nullable Date recordedDate) {

		List<Condition> conditions = new ArrayList<>();

		if ((filterHelper != null) &&
			!StringUtils.isEmpty(filterHelper.getFilterString())) {

			conditions.add(filterHelper.getCondition());
		}

		if ((individualIds != null) && !individualIds.isEmpty()) {
			conditions.add(
				DSL.field(
					"individualId", String.class
				).in(
					individualIds
				));
		}

		if (interested != null) {
			conditions.add(
				DSL.field(
					"interested", Boolean.class
				).eq(
					interested
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

		if (!StringUtils.isBlank(keywords)) {
			conditions.add(
				DSL.or(
					DSL.field(
						"keyword"
					).containsIgnoreCase(
						keywords
					)
				).or(
					DSL.field(
						"keyword"
					).similarTo(
						keywords
					)
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

	@Autowired
	private QueryExecutor _queryExecutor;

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