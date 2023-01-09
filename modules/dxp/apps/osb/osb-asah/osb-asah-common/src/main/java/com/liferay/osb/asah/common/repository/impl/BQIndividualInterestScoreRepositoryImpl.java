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
import com.liferay.osb.asah.common.entity.BQIndividualInterestScore;
import com.liferay.osb.asah.common.model.Composition;
import com.liferay.osb.asah.common.model.CompositionResultBag;
import com.liferay.osb.asah.common.model.IndividualInterestScore;
import com.liferay.osb.asah.common.postgresql.converter.helper.InterestFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.CustomBQIndividualInterestScoreRepository;
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
import org.jooq.Record3;
import org.jooq.Record5;
import org.jooq.SelectFinalStep;
import org.jooq.SelectJoinStep;
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
public class BQIndividualInterestScoreRepositoryImpl
	extends BaseRepository
	implements CustomBQIndividualInterestScoreRepository {

	public BQIndividualInterestScoreRepositoryImpl(DSLContext dslContext) {
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

		return _queryExecutor.queryForLong(
			selectSelectStep.from(
				"BQIndividualInterestScore"
			).join(
				"BQIdentity"
			).on(
				DSL.field(
					"BQIndividualInterestScore.identityId"
				).eq(
					DSL.field("BQIdentity.id")
				)
			).where(
				_getConditions(filterHelper, null, null, null, null, score)
			));
	}

	@Override
	public long countByIndividualId(String individualId) {
		return _queryExecutor.queryForLong(
			_dslContext.selectCount(
			).from(
				"BQIndividualInterestScore"
			).join(
				"BQIdentity"
			).on(
				DSL.field(
					"BQIndividualInterestScore.identityId"
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

	@Override
	public List<IndividualInterestScore>
		findByFilterStringAndScoreGreaterThanEqual(
			@Nullable FilterHelper filterHelper, @Nullable Double score,
			Pageable pageable) {

		return _queryExecutor.queryForList(
			record -> new IndividualInterestScore(
				new BQIndividualInterestScore(record),
				(String)record.get("individualId")),
			_dslContext.select(
				DSL.field(
					"BQIndividualInterestScore.id"
				).as(
					"id"
				),
				DSL.field(
					"BQIndividualInterestScore.identityId"
				).as(
					"identityId"
				),
				DSL.field(
					"BQIndividualInterestScore.interestScore"
				).as(
					"interestScore"
				),
				DSL.field(
					"BQIndividualInterestScore.keyword"
				).as(
					"keyword"
				),
				DSL.field(
					"BQIndividualInterestScore.recordedDate"
				).as(
					"recordedDate"
				),
				DSL.field(
					"BQIdentity.individualId"
				).as(
					"individualId"
				)
			).from(
				"BQIndividualInterestScore"
			).join(
				"BQIdentity"
			).on(
				DSL.field(
					"BQIndividualInterestScore.identityId"
				).eq(
					DSL.field("BQIdentity.id")
				)
			).where(
				_getConditions(filterHelper, null, null, null, null, score)
			).orderBy(
				_getSortFields(pageable.getSort(), null)
			).limit(
				pageable.getPageSize()
			).offset(
				pageable.getOffset()
			));
	}

	@Override
	public List<BQIndividualInterestScore> findByIndividualId(
		String individualId, Pageable pageable) {

		return _queryExecutor.queryForList(
			BQIndividualInterestScore::new,
			_dslContext.selectDistinct(
				DSL.field(
					"BQIndividualInterestScore.id"
				).as(
					"id"
				),
				DSL.field(
					"BQIndividualInterestScore.identityId"
				).as(
					"identityId"
				),
				DSL.field(
					"BQIndividualInterestScore.interestScore"
				).as(
					"interestScore"
				),
				DSL.field(
					"BQIndividualInterestScore.keyword"
				).as(
					"keyword"
				),
				DSL.field(
					"BQIndividualInterestScore.recordedDate"
				).as(
					"recordedDate"
				)
			).from(
				"BQIndividualInterestScore"
			).join(
				"BQIdentity"
			).on(
				DSL.field(
					"BQIndividualInterestScore.identityId"
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
	public List<BQIndividualInterestScore>
		findByIndividualIdAndKeywordAndRecordedDateBetween(
			String individualId, String keyword, Date recordedDate1,
			Date recordedDate2) {

		return _queryExecutor.queryForList(
			BQIndividualInterestScore::new,
			_dslContext.selectDistinct(
				DSL.field(
					"BQIndividualInterestScore.id"
				).as(
					"id"
				),
				DSL.field(
					"BQIndividualInterestScore.identityId"
				).as(
					"identityId"
				),
				DSL.field(
					"BQIndividualInterestScore.interestScore"
				).as(
					"interestScore"
				),
				DSL.field(
					"BQIndividualInterestScore.keyword"
				).as(
					"keyword"
				),
				DSL.field(
					"BQIndividualInterestScore.recordedDate"
				).as(
					"recordedDate"
				)
			).from(
				"BQIndividualInterestScore"
			).join(
				"BQIdentity"
			).on(
				DSL.field(
					"BQIndividualInterestScore.identityId"
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
					"BQIndividualInterestScore.keyword"
				).eq(
					keyword
				)
			).and(
				DSL.field(
					"BQIndividualInterestScore.recordedDate"
				).between(
					recordedDate1, recordedDate2
				)
			));
	}

	@Override
	public List<BQIndividualInterestScore> findByRecordedDate(
		@Nullable Long interestId, @Nullable Date recordedDate, int size) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForList(
			BQIndividualInterestScore::new,
			selectSelectStep.from(
				"BQIndividualInterestScore"
			).where(
				_getConditions(null, interestId, null, null, recordedDate, null)
			).orderBy(
				DSL.field("id")
			).limit(
				size
			));
	}

	@Override
	public List<String> findIndividualIdsByFilterStringAndIndividualId(
		FilterHelper filterHelper, String individualId) {

		Field<String> field = DSL.field("individualId", String.class);

		SelectJoinStep<Record1<String>> selectJoinStep = _dslContext.select(
			field
		).from(
			"BQIndividualInterestScore"
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
					filterHelper, null, individualIds, null, null, null)));
	}

	@Override
	public Optional<IndividualInterestScore> findIndividualInterestScoreById(
		Long id) {

		return _queryExecutor.queryForObject(
			record -> new IndividualInterestScore(
				new BQIndividualInterestScore(record),
				(String)record.get("individualId")),
			(SelectFinalStep)_dslContext.select(
				DSL.field(
					"BQIndividualInterestScore.id"
				).as(
					"id"
				),
				DSL.field(
					"BQIndividualInterestScore.identityId"
				).as(
					"identityId"
				),
				DSL.field(
					"BQIndividualInterestScore.interestScore"
				).as(
					"interestScore"
				),
				DSL.field(
					"BQIndividualInterestScore.keyword"
				).as(
					"keyword"
				),
				DSL.field(
					"BQIndividualInterestScore.recordedDate"
				).as(
					"recordedDate"
				),
				DSL.field(
					"BQIdentity.individualId"
				).as(
					"individualId"
				)
			).from(
				"BQIndividualInterestScore"
			).join(
				"BQIdentity"
			).on(
				DSL.field(
					"BQIndividualInterestScore.identityId"
				).eq(
					DSL.field("BQIdentity.id")
				)
			).where(
				DSL.field(
					"BQIndividualInterestScore.id"
				).eq(
					id
				)
			));
	}

	@Override
	public BQIndividualInterestScore getByIndividualIdAndKeywordAndRecordedDate(
		String individualId, String keyword, Date recordedDate) {

		SelectSelectStep selectSelectStep = _dslContext.selectDistinct(
			DSL.field(
				"BQIndividualInterestScore.id"
			).as(
				"id"
			),
			DSL.field(
				"BQIndividualInterestScore.identityId"
			).as(
				"identityId"
			),
			DSL.field(
				"BQIndividualInterestScore.interestScore"
			).as(
				"interestScore"
			),
			DSL.field(
				"BQIndividualInterestScore.keyword"
			).as(
				"keyword"
			),
			DSL.field(
				"BQIndividualInterestScore.recordedDate"
			).as(
				"recordedDate"
			));

		Optional<BQIndividualInterestScore> bqIndividualInterestScoreOptional =
			_queryExecutor.queryForObject(
				BQIndividualInterestScore::new,
				selectSelectStep.from(
					"BQIndividualInterestScore"
				).join(
					"BQIdentity"
				).on(
					DSL.field(
						"BQIndividualInterestScore.identityId"
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
						"BQIndividualInterestScore.keyword"
					).eq(
						keyword
					),
					DSL.field(
						"BQIndividualInterestScore.recordedDate"
					).eq(
						recordedDate
					)
				));

		return bqIndividualInterestScoreOptional.orElseThrow(
			IllegalArgumentException::new);
	}

	@Override
	public CompositionResultBag getInterestCompositionResultBag(
		boolean active, @Nullable Long channelId, @Nullable String keywords,
		@Nullable Long segmentId, Pageable pageable) {

		SelectSelectStep<Record5<Integer, String, Integer, Integer, BigDecimal>>
			selectSelectStep = _dslContext.select(
				DSL.count(
				).as(
					"count"
				),
				DSL.field("keyword", String.class),
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
				DSL.sum(
					DSL.count()
				).over(
				).as(
					"totalCount"
				));

		SelectJoinStep<Record5<Integer, String, Integer, Integer, BigDecimal>>
			selectJoinStep = selectSelectStep.from(
				"BQIndividualInterestScore"
			).join(
				"BQIdentityActivity"
			).on(
				DSL.field(
					"BQIndividualInterestScore.identityId"
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
					"BQIdentityActivity.lastActivityDate"
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

		if (!StringUtils.isBlank(keywords)) {
			conditions.add(_dslHelper.containsSubstring("keyword", keywords));
		}

		if (segmentId != null) {
			selectJoinStep = selectJoinStep.join(
				"BQMembership"
			).on(
				DSL.and(
					DSL.field(
						"BQIdentityActivity.identityId"
					).eq(
						DSL.field("BQMembership.userId")
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
			selectJoinStep.where(
				conditions
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
				totalCountBigDecimal = (BigDecimal)record.get("totalCount");
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

	@Override
	public List<String> getTopKeywordsByIndividualId(
		String individualId, int size) {

		SelectSelectStep<Record1<String>> selectSelectStep = _dslContext.select(
			DSL.field("keyword", String.class));

		return selectSelectStep.from(
			"BQIndividualInterestScore"
		).join(
			"BQIdentity"
		).on(
			DSL.field(
				"BQIndividualInterestScore.identityId"
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
			"BQIndividualInterestScore"
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
					"BQIndividualInterestScore.identityId"
				).eq(
					DSL.field("BQIdentity.id")
				)
			);
		}

		return selectJoinStep;
	}

	private List<Condition> _getConditions(
		@Nullable FilterHelper filterHelper, @Nullable Long interestId,
		@Nullable List<String> individualIds, @Nullable String keywords,
		@Nullable Date recordedDate, @Nullable Double interestScore) {

		List<Condition> conditions = new ArrayList<>();

		if ((filterHelper != null) &&
			!StringUtils.isEmpty(filterHelper.getFilterString())) {

			conditions.add(filterHelper.getCondition());
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

		if (interestId != null) {
			conditions.add(
				DSL.field(
					"id"
				).gt(
					interestId
				));
		}

		if ((individualIds != null) && !individualIds.isEmpty()) {
			conditions.add(
				DSL.field(
					"individualId", String.class
				).in(
					individualIds
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

		if (interestScore != null) {
			conditions.add(
				DSL.field(
					"interestScore"
				).greaterOrEqual(
					interestScore
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