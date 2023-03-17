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

import java.text.SimpleDateFormat;

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
import org.jooq.InsertValuesStep6;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record3;
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
	public long count() {
		SelectSelectStep<Record1<Integer>> selectSelectStep =
			_dslContext.selectCount();

		return _queryExecutor.queryForLong(
			selectSelectStep.from("BQIdentityInterestScore"));
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
				_getConditions(filterHelper, Boolean.TRUE, null, null, null)
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
				_getConditions(null, null, null, keywords, null)
			));
	}

	@Override
	public void deleteByKeywordAndRecordedDateGreaterThanEqual(
		String keyword, Date recordedDate) {

		_queryExecutor.queryExecute(
			_dslContext.delete(
				DSL.table("BQIdentityInterestScore")
			).where(
				DSL.and(
					DSL.field(
						"keyword"
					).eq(
						keyword
					),
					DSL.field(
						"recordedDate"
					).ge(
						DateUtil.toUTCString(
							recordedDate,
							new SimpleDateFormat(DateUtil.PATTERN_SHORT))
					))
			));
	}

	@Override
	public void deleteByRecordedDate(Date recordedDate) {
		_queryExecutor.queryExecute(
			_dslContext.delete(
				DSL.table("BQIdentityInterestScore")
			).where(
				DSL.field(
					"recordedDate"
				).eq(
					DateUtil.toUTCString(
						recordedDate,
						new SimpleDateFormat(DateUtil.PATTERN_SHORT))
				)
			));
	}

	@Override
	public void deleteByRecordedDateLessThanEqual(Date recordedDate) {
		_queryExecutor.queryExecute(
			_dslContext.delete(
				DSL.table("BQIdentityInterestScore")
			).where(
				DSL.field(
					"recordedDate"
				).le(
					DateUtil.toUTCString(
						recordedDate,
						new SimpleDateFormat(DateUtil.PATTERN_SHORT))
				)
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
					"BQIdentityInterestScore.interested"
				).as(
					"interested"
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
				_getConditions(filterHelper, Boolean.TRUE, null, null, null)
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
					"BQIdentityInterestScore.interested"
				).as(
					"interested"
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
					"BQIdentityInterestScore.interested"
				).as(
					"interested"
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
					DateUtil.toUTCString(
						recordedDate1,
						new SimpleDateFormat(DateUtil.PATTERN_SHORT)),
					DateUtil.toUTCString(
						recordedDate2,
						new SimpleDateFormat(DateUtil.PATTERN_SHORT))
				)
			));
	}

	@Override
	public List<BQIdentityInterestScore> findByRecordedDate(
		@Nullable Date recordedDate, int size) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		return _queryExecutor.queryForList(
			BQIdentityInterestScore::new,
			selectSelectStep.from(
				"BQIdentityInterestScore"
			).where(
				_getConditions(null, null, null, null, recordedDate)
			).orderBy(
				DSL.field("keyword")
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
				_getConditions(filterHelper, null, individualIds, null, null)));
	}

	@Override
	public BQIdentityInterestScore getByIndividualIdAndKeywordAndRecordedDate(
		String individualId, String keyword, Date recordedDate) {

		SelectSelectStep selectSelectStep = _dslContext.selectDistinct(
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
				"BQIdentityInterestScore.interested"
			).as(
				"interested"
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
						DateUtil.toUTCString(
							recordedDate,
							new SimpleDateFormat(DateUtil.PATTERN_SHORT))
					)
				));

		return bqIdentityInterestScoreOptional.orElseThrow(
			IllegalArgumentException::new);
	}

	@Override
	public CompositionResultBag getInterestCompositionResultBag(
		boolean active, @Nullable Long channelId, @Nullable String keywords,
		@Nullable Long segmentId, Pageable pageable) {

		SelectJoinStep<Record3<String, String, Integer>> selectSelectStep =
			_dslContext.select(
				DSL.field("BQIdentityInterestScore.identityId", String.class),
				DSL.field("BQIdentityInterestScore.keyword", String.class),
				DSL.countDistinct(
					DSL.field("BQIdentityInterestScore.identityId")
				).over(
				).as(
					"totalCount"
				)
			).from(
				"BQIdentityInterestScore"
			);

		List<Condition> conditions = new ArrayList<>();

		if (active) {
			LocalDateTime newDayLocalDateTime = DateUtil.newDayLocalDateTime(
				TimeZoneDogUtil.getZoneId());

			conditions.add(
				DSL.field(
					"BQIdentityInterestScore.recordedDate"
				).ge(
					DateUtil.toUTCString(
						DateUtil.toUTCDate(newDayLocalDateTime.minusDays(30)),
						new SimpleDateFormat(DateUtil.PATTERN_SHORT))
				));
		}

		if (channelId != null) {
			conditions.add(
				DSL.field(
					"BQIdentityInterestScore.channelId", Long.class
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

		if (StringUtils.isNotBlank(keywords)) {
			conditions.add(_dslHelper.containsSubstring("keyword", keywords));
		}

		if (segmentId != null) {
			selectSelectStep = selectSelectStep.join(
				"BQMembership"
			).on(
				DSL.and(
					DSL.field(
						"BQIdentityInterestScore.identityId"
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
					DSL.field("keyword")
				).over(
				).as(
					"total"
				),
				DSL.field("totalCount")
			).from(
				DSL.table("KeywordIdentity")
			).groupBy(
				DSL.field("keyword"), DSL.field("totalCount")
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
				_getConditions(null, null, null, keywords, null)
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

		return _queryExecutor.queryForList(
			recordMap -> (String)recordMap.get("keyword"),
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
			));
	}

	@Override
	public List<Map<String, Object>> getTransformations(
		Date fromDate, @Nullable FilterHelper filterHelper, String period,
		Date toDate) {

		Field<OffsetDateTime> periodField = _getPeriodField(period);

		SelectSelectStep<Record3<OffsetDateTime, BigDecimal, Integer>>
			selectSelectStep = _dslContext.select(
				periodField,
				DSL.avg(
					DSL.field("interestScore", Double.class)
				).as(
					"scoreAvg"
				),
				DSL.field(
					"COUNTIF(keyword IS NOT NULL)", Integer.class
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

		return _queryExecutor.queryForList(
			record -> new HashMap<String, Object>() {
				{
					put(
						"intervalInitDate",
						DateUtil.toUTCString(
							(Date)record.get("intervalInitDate")));
					put(
						"scoreAvg",
						_getAggregationValue(
							(BigDecimal)record.get("scoreAvg")));
					put("totalElements", record.get("totalElements"));
				}
			},
			selectSelectStep.from(
				_dslHelper.getTimeSeriesTable(
					datePart, new Timestamp(fromDate.getTime()),
					new Timestamp(toDate.getTime()))
			).leftOuterJoin(
				"BQIdentityInterestScore"
			).on(
				conditions.toArray(new Condition[0])
			).groupBy(
				periodField
			).orderBy(
				periodField
			));
	}

	@Override
	public BQIdentityInterestScore insert(
		BQIdentityInterestScore bqIdentityInterestScore) {

		_queryExecutor.queryExecute(
			_dslContext.insertInto(
				DSL.table("BQIdentityInterestScore")
			).columns(
				DSL.field("channelId"), DSL.field("identityId"),
				DSL.field("interested", Boolean.class),
				DSL.field("interestScore", Double.class), DSL.field("keyword"),
				DSL.field("recordedDate", Object.class)
			).values(
				bqIdentityInterestScore.getChannelId(),
				bqIdentityInterestScore.getIdentityId(),
				bqIdentityInterestScore.getInterested(),
				bqIdentityInterestScore.getInterestScore(),
				bqIdentityInterestScore.getKeyword(),
				DateUtil.toUTCString(
					bqIdentityInterestScore.getRecordedDate(),
					new SimpleDateFormat(DateUtil.PATTERN_SHORT))
			));

		return bqIdentityInterestScore;
	}

	@Override
	public void insertAll(
		List<BQIdentityInterestScore> bqIdentityInterestScores) {

		InsertValuesStep6<Record, Long, String, Boolean, Double, Object, Object>
			insertValuesStep6 = _dslContext.insertInto(
				DSL.table("BQIdentityInterestScore")
			).columns(
				DSL.field("channelId", Long.class),
				DSL.field("identityId", String.class),
				DSL.field("interested", Boolean.class),
				DSL.field("interestScore", Double.class), DSL.field("keyword"),
				DSL.field("recordedDate", Object.class)
			);

		for (BQIdentityInterestScore bqIdentityInterestScore :
				bqIdentityInterestScores) {

			insertValuesStep6 = insertValuesStep6.values(
				bqIdentityInterestScore.getChannelId(),
				bqIdentityInterestScore.getIdentityId(),
				bqIdentityInterestScore.getInterested(),
				bqIdentityInterestScore.getInterestScore(),
				bqIdentityInterestScore.getKeyword(),
				DateUtil.toUTCString(
					bqIdentityInterestScore.getRecordedDate(),
					new SimpleDateFormat(DateUtil.PATTERN_SHORT)));
		}

		_queryExecutor.queryExecute(insertValuesStep6);
	}

	private double _getAggregationValue(BigDecimal value) {
		if ((value == null) || (value.doubleValue() < 0)) {
			return 0;
		}

		return value.doubleValue();
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
		@Nullable List<String> individualIds, @Nullable String keywords,
		@Nullable Date recordedDate) {

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

		if (StringUtils.isNotBlank(keywords)) {
			conditions.add(_dslHelper.containsSubstring("keyword", keywords));
		}

		if (recordedDate != null) {
			conditions.add(
				DSL.field(
					"recordedDate"
				).eq(
					DateUtil.toUTCString(
						recordedDate,
						new SimpleDateFormat(DateUtil.PATTERN_SHORT))
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