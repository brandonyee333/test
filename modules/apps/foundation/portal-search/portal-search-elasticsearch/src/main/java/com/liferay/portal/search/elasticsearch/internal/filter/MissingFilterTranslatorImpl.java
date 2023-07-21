/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.filter;

import com.liferay.portal.kernel.search.filter.MissingFilter;

import org.elasticsearch.index.query.MissingQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = MissingFilterTranslator.class)
public class MissingFilterTranslatorImpl implements MissingFilterTranslator {

	@Override
	public QueryBuilder translate(MissingFilter missingFilter) {
		MissingQueryBuilder missingQueryBuilder = QueryBuilders.missingQuery(
			missingFilter.getField());

		if (missingFilter.isExists() != null) {
			missingFilter.setExists(missingFilter.isExists());
		}

		if (missingFilter.isNullValue() != null) {
			missingFilter.setNullValue(missingFilter.isNullValue());
		}

		return missingQueryBuilder;
	}

}