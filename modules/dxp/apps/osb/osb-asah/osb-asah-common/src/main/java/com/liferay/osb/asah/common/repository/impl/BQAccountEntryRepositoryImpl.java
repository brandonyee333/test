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

import com.liferay.osb.asah.common.entity.BQAccountEntry;
import com.liferay.osb.asah.common.repository.CustomBQAccountEntryRepository;
import com.liferay.osb.asah.common.repository.executor.QueryExecutor;

import java.util.Date;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

/**
 * @author Marcellus Tavares
 */
public class BQAccountEntryRepositoryImpl
	implements CustomBQAccountEntryRepository {

	public BQAccountEntryRepositoryImpl(
		DSLContext dslContext, QueryExecutor queryExecutor) {

		_dslContext = dslContext;
		_queryExecutor = queryExecutor;
	}

	@Override
	public long count() {
		return _queryExecutor.queryForLong(
			_dslContext.selectCount(
			).from(
				DSL.table("BQAccountEntry")
			));
	}

	@Override
	public void deleteById(String id) {
		_queryExecutor.queryExecute(
			_dslContext.delete(
				DSL.table("BQAccountEntry")
			).where(
				DSL.field(
					"id"
				).eq(
					id
				)
			));
	}

	@Override
	public BQAccountEntry insert(BQAccountEntry bqAccountEntry) {
		_queryExecutor.queryExecute(
			_dslContext.insertInto(
				DSL.table("BQAccountEntry")
			).columns(
				DSL.field("accountEntryId", Long.class),
				DSL.field("createDate", Date.class),
				DSL.field("dataSourceId", Long.class),
				DSL.field("defaultCPaymentMethodKey"), DSL.field("description"),
				DSL.field("domains"), DSL.field("emailAddress"),
				DSL.field("id"), DSL.field("logoId"),
				DSL.field("modifiedDate", Date.class), DSL.field("name"),
				DSL.field("parentAccountEntryId"), DSL.field("status"),
				DSL.field("taxExemptionCode"), DSL.field("taxIdNumber"),
				DSL.field("type")
			).values(
				bqAccountEntry.getAccountEntryId(),
				bqAccountEntry.getCreateDate(),
				bqAccountEntry.getDataSourceId(),
				bqAccountEntry.getDefaultCPaymentMethodKey(),
				bqAccountEntry.getDescription(), bqAccountEntry.getDomains(),
				bqAccountEntry.getEmailAddress(), bqAccountEntry.getId(),
				bqAccountEntry.getLogoId(), bqAccountEntry.getModifiedDate(),
				bqAccountEntry.getName(),
				bqAccountEntry.getParentAccountEntryId(),
				bqAccountEntry.getStatus(),
				bqAccountEntry.getTaxExemptionCode(),
				bqAccountEntry.getTaxIdNumber(), bqAccountEntry.getType()
			));

		return bqAccountEntry;
	}

	private final DSLContext _dslContext;
	private final QueryExecutor _queryExecutor;

}