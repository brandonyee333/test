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

import com.liferay.osb.asah.common.entity.BQDataSourceUser;
import com.liferay.osb.asah.common.repository.CustomBQDataSourceUserRepository;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

/**
 * @author Ivica Cardic
 */
public class BQDataSourceUserRepositoryImpl
	extends BaseRepository implements CustomBQDataSourceUserRepository {

	public BQDataSourceUserRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public List<BQDataSourceUser> findBQDataSourceUsersByUserEmailAddressHashed(
		String userEmailAddressHashed) {

		SelectSelectStep<Record> selectSelectStep = _dslContext.select(
			DSL.table(
				"BQDataSourceUser"
			).asterisk());

		return selectSelectStep.from(
			"BQDataSourceUser"
		).join(
			DSL.table(
				"BQUser"
			).as(
				"user"
			)
		).on(
			DSL.field(
				"userId"
			).eq(
				DSL.field("user.id")
			)
		).where(
			DSL.field(
				"user.emailAddressHashed"
			).eq(
				userEmailAddressHashed
			)
		).fetch(
		).map(
			record -> new BQDataSourceUser(record.intoMap())
		);
	}

	private final DSLContext _dslContext;

}