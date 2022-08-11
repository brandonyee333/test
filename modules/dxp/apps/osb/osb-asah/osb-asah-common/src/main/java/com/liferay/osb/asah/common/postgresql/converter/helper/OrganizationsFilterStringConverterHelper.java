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
import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.repository.BQOrganizationRepository;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.jooq.Condition;
import org.jooq.impl.DSL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class OrganizationsFilterStringConverterHelper
	extends DefaultFilterStringConverterHelper {

	@Override
	public Map<String, String> getFieldNameConversionMap() {
		Map<String, String> map = new HashMap<>();

		map.put("dataSourceId", "organization.dataSourceId");
		map.put("id", "organization.id");
		map.put("name", "organization.name");

		return map;
	}

	@Override
	public Condition getLogicFunctionCondition(
		String fieldName, String operator, String valueString) {

		if (!fieldName.equalsIgnoreCase("parentId")) {
			return null;
		}

		Optional<BQOrganization> bqOrganizationOptional =
			_bqOrganizationRepository.findById(StringUtil.unquote(valueString));

		if (!bqOrganizationOptional.isPresent()) {
			return null;
		}

		BQOrganization bqOrganization = bqOrganizationOptional.get();

		Condition condition = DSL.and(
			DSL.field(
				"organization.dataSourceId"
			).eq(
				bqOrganization.getDataSourceId()
			),
			DSL.field(
				"organization.parentOrganizationId"
			).eq(
				bqOrganization.getOrganizationId()
			));

		if (operator.equalsIgnoreCase("eq")) {
			return condition;
		}

		if (operator.equalsIgnoreCase("ne")) {
			return DSL.not(condition);
		}

		return null;
	}

	@Autowired
	private BQOrganizationRepository _bqOrganizationRepository;

}