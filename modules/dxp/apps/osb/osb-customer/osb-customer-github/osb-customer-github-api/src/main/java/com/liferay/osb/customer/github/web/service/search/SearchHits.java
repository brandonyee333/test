/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.github.web.service.search;

import java.util.List;

/**
 * @author Jenny Chen
 */
public interface SearchHits<T> {

	public int getNextPage();

	public List<T> getResults();

	public void setNextPage(int nextPage);

	public void setResults(List<T> results);

}