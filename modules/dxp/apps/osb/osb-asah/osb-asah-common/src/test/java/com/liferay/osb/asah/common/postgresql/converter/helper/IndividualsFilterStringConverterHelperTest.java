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

import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.json.JSONUtil;

import org.jooq.Condition;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Marcos Martins
 */
public class IndividualsFilterStringConverterHelperTest {

	@Test
	public void testGetUserIdCondition() {
		DXPEntityDog dxpEntityDog = Mockito.mock(DXPEntityDog.class);

		Mockito.when(
			dxpEntityDog.fetchByFieldsAndType(Mockito.anyMap(), Mockito.any())
		).thenReturn(
			new DXPEntity() {
				{
					setDataSourceId(1L);
					setFieldsJSONObject(JSONUtil.put("uuid", "1"));
				}
			}
		);

		ReflectionTestUtils.setField(
			_individualsFilterStringConverterHelper, "_dxpEntityDog",
			dxpEntityDog);

		Condition condition = (Condition)ReflectionTestUtils.invokeMethod(
			_individualsFilterStringConverterHelper, "_getUserIdCondition",
			false, 1L);

		Assertions.assertNotNull(condition);
		Assertions.assertEquals(
			"(\n  datasourceindividual.datasourceid = 1\n" +
				"  and cast(array[datasourceindividual.individualpks] as " +
					"varchar[]) @> cast(array['1'] as varchar[])\n)",
			condition.toString());
	}

	private final IndividualsFilterStringConverterHelper
		_individualsFilterStringConverterHelper =
			new IndividualsFilterStringConverterHelper();

}