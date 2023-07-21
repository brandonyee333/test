/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.github.internal.web.service.search;

import com.liferay.osb.customer.github.web.service.search.Query;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jenny Chen
 */
public class QueryImpl implements Query {

	public void addParameter(String key, String value) {
		_parameters.put(key, value);
	}

	public int getPage() {
		return _page;
	}

	public Map<String, String> getParameters() {
		Map<String, String> parameters = new HashMap<>();

		if (_page > 0) {
			parameters.put("page", String.valueOf(_page));
		}

		if (_perPage > 0) {
			parameters.put("per_page", String.valueOf(_perPage));
		}

		if (!_parameters.isEmpty()) {
			parameters.putAll(_parameters);
		}

		return parameters;
	}

	public int getPerPage() {
		return _perPage;
	}

	public void setPage(int page) {
		_page = page;
	}

	public void setPerPage(int perPage) {
		_perPage = perPage;
	}

	private int _page;
	private final Map<String, String> _parameters = new HashMap<>();
	private int _perPage;

}