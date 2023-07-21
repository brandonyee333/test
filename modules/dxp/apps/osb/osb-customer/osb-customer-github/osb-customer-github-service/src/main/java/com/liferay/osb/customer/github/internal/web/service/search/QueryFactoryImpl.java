/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.github.internal.web.service.search;

import com.liferay.osb.customer.github.web.service.search.Query;
import com.liferay.osb.customer.github.web.service.search.QueryFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = QueryFactory.class)
public class QueryFactoryImpl implements QueryFactory {

	public Query createQuery() {
		return new QueryImpl();
	}

}