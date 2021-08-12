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

import com.liferay.osb.asah.common.entity.MembershipChange;

import java.util.List;

import org.jooq.DSLContext;

import org.springframework.data.domain.Pageable;

/**
 * @author Rachael Koestartyo
 */
public class MembershipChangeRepositoryImpl {

	public MembershipChangeRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	public long countMembershipChanges(String filterString, Long segmentId) {

		// TODO

		return 0;
	}

	public List<MembershipChange> searchMembershipChanges(
		String filterString, Long segmentId, Pageable pageable) {

		// TODO

		return null;
	}

	private final DSLContext _dslContext;

}