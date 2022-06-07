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

import com.liferay.osb.asah.common.dog.MembershipChangeDog;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Collections;

import org.jooq.Condition;
import org.jooq.impl.DSL;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author Robson Pastor
 */
public class SegmentFilterStringConverterHelperTest {

	@Test
	public void test() {
		Mockito.when(
			_membershipChangeDog.findIndividualSegmentIdByFilterString(
				"individualsCount eq 3")
		).thenReturn(
			Collections.singletonList(3L)
		);
		Mockito.when(
			_membershipChangeDog.findIndividualSegmentIdByFilterString(
				"individualsCount gt 3")
		).thenReturn(
			Collections.singletonList(5L)
		);
		Mockito.when(
			_membershipChangeDog.findIndividualSegmentIdByFilterString(
				"individualsCount lt 3")
		).thenReturn(
			Collections.singletonList(2L)
		);

		FilterHelper filterHelper1 = new FilterHelper(
			null, "((individualCount eq 3))",
			_segmentFilterStringConverterHelper);

		Condition condition1 = DSL.field(
			"id"
		).in(
			Collections.singletonList(3L)
		);

		Assertions.assertEquals(condition1, filterHelper1.getCondition());

		FilterHelper filterHelper2 = new FilterHelper(
			null, "((individualCount gt 3))",
			_segmentFilterStringConverterHelper);

		Condition condition2 = DSL.field(
			"id"
		).in(
			Collections.singletonList(5L)
		);

		Assertions.assertEquals(condition2, filterHelper2.getCondition());

		FilterHelper filterHelper3 = new FilterHelper(
			null, "((individualCount lt 3))",
			_segmentFilterStringConverterHelper);

		Condition condition3 = DSL.field(
			"id"
		).in(
			Collections.singletonList(2L)
		);

		Assertions.assertEquals(condition3, filterHelper3.getCondition());
	}

	@MockBean
	private MembershipChangeDog _membershipChangeDog = Mockito.mock(
		MembershipChangeDog.class);

	private final SegmentFilterStringConverterHelper
		_segmentFilterStringConverterHelper =
			new SegmentFilterStringConverterHelper(_membershipChangeDog);

}