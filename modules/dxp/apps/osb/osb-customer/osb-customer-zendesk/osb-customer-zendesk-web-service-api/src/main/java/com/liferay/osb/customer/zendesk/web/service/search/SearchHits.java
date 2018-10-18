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

package com.liferay.osb.customer.zendesk.web.service.search;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface SearchHits<T> {

	public int getCount();

	public int getNextPage();

	public List<T> getResults();

	public boolean hasNextPage();

	public void setCount(int count);

	public void setNextPage(int nextPage);

	public void setResults(List<T> results);

}