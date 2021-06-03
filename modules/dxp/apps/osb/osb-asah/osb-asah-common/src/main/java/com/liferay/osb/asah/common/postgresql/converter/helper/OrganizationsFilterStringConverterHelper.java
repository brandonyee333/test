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
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.repository.OrganizationRepository;
import com.liferay.osb.asah.common.util.StringUtil;

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
	public Condition getLogicFunctionCondition(
		String fieldName, String operator, String valueString) {

		if (!fieldName.equalsIgnoreCase("parentId")) {
			return null;
		}

		Optional<Organization> organizationOptional =
			_organizationRepository.findById(
				Long.valueOf(StringUtil.unquote(valueString)));

		if (!organizationOptional.isPresent()) {
			return null;
		}

		Organization organization = organizationOptional.get();

		Long dataSourceId = organization.getDataSourceId();
		Long organizationPK = organization.getOrganizationPK();

		Condition condition = DSL.and(
			DSL.field(
				"dataSourceId"
			).eq(
				dataSourceId
			),
			DSL.field(
				"organizationPK"
			).eq(
				organizationPK
			));

		if (operator.equalsIgnoreCase("eq")) {
			return condition;
		}
		else if (operator.equalsIgnoreCase("ne")) {
			return DSL.not(condition);
		}

		return null;
	}

	@Autowired
	private OrganizationRepository _organizationRepository;

}