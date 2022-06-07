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

package com.liferay.osb.asah.common.postgresql.converter.helper;

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.dog.MembershipChangeDog;

import org.jooq.Condition;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Robson Pastor
 */
public class SegmentFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	public SegmentFilterStringConverterHelper(
		MembershipChangeDog membershipChangeDog) {

		_membershipChangeDog = membershipChangeDog;
	}

	@Override
	public Condition getLogicFunctionCondition(
		String fieldName, String operator, String valueString) {

		if (fieldName.equals("individualCount")) {
			return DSL.field(
				"id"
			).in(
				_membershipChangeDog.findIndividualSegmentIdByFilterString(
					"individualsCount " + operator + " " + valueString)
			);
		}

		return null;
	}

	@Autowired
	private final MembershipChangeDog _membershipChangeDog;

}