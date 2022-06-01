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

import com.liferay.osb.asah.common.repository.BQOrderRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;
import com.liferay.osb.asah.common.repository.helper.DSLHelper;
import com.liferay.osb.asah.common.util.GetterUtil;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;

import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.SelectHavingStep;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Riccardo Ferrari
 */
@Repository
public class BQOrderRepositoryImpl implements BQOrderRepository {

	public BQOrderRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public Map<String, BigDecimal> getOrderTotalValue(
		List<Long> dataSourceIds, LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId) {

		SelectHavingStep<Record2<String, BigDecimal>> record =
			_dslContext.select(
				DSL.field("currencyCode", String.class),
				DSL.sum(
					DSL.field(
						"total"
					).cast(
						BigDecimal.class
					)
				).as(
					"totalOrderValue"
				)
			).from(
				DSL.table("BQOrder")
			).where(
				DSL.field(
					"datasourceId"
				).in(
					dataSourceIds
				),
				DSL.field(
					"orderDate"
				).between(
					_dslHelper.getDateParam(
						rangeStartLocalDateTime, timeZoneId),
					_dslHelper.getDateParam(rangeEndLocalDateTime, timeZoneId)
				)
			).groupBy(
				DSL.field("currencyCode")
			);

		return _queryExecutor.queryForMap(
			GetterUtil::getString, record, GetterUtil::getBigDecimal);
	}

	private final DSLContext _dslContext;

	@Autowired
	private DSLHelper _dslHelper;

	@Autowired
	private QueryExecutor _queryExecutor;

}