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

package com.liferay.osb.customer.zendesk.web.service.internal.search;

import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;

import java.util.List;

/**
 * @author Amos Fong
 */
public class SearchHitsImpl<T> implements SearchHits<T> {

	public int getCount() {
		return _count;
	}

	public int getNextPage() {
		return _nextPage;
	}

	public List<T> getResults() {
		return _results;
	}

	public boolean hasNextPage() {
		if (_nextPage > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setCount(int count) {
		_count = count;
	}

	public void setNextPage(int nextPage) {
		_nextPage = nextPage;
	}

	public void setResults(List<T> results) {
		_results = results;
	}

	private int _count;
	private int _nextPage;
	private List<T> _results;

}