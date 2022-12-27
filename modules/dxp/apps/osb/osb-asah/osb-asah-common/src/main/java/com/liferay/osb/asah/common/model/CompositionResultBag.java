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

package com.liferay.osb.asah.common.model;

import java.util.List;
import java.util.Objects;

/**
 * @author Matthew Kong
 */
public class CompositionResultBag extends ResultBag<Composition> {

	public CompositionResultBag() {
	}

	public CompositionResultBag(
		List<Composition> results, long total, long totalCount) {

		if (!results.isEmpty()) {
			Composition composition = results.get(0);

			_maxCount = composition.getCount();
		}

		this.results = results;
		this.total = total;
		_totalCount = totalCount;
	}

	public CompositionResultBag(
		long maxCount, List<Composition> results, long total, long totalCount) {

		_maxCount = maxCount;
		this.results = results;
		this.total = total;
		_totalCount = totalCount;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		Class<?> clazz = getClass();

		if ((obj == null) || !clazz.isAssignableFrom(obj.getClass())) {
			return false;
		}

		CompositionResultBag compositionResultBag = (CompositionResultBag)obj;

		if (Objects.equals(results, compositionResultBag.results) &&
			Objects.equals(total, compositionResultBag.total) &&
			Objects.equals(_totalCount, compositionResultBag._totalCount)) {

			return true;
		}

		return false;
	}

	public long getMaxCount() {
		return _maxCount;
	}

	public long getTotalCount() {
		return _totalCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(results, total, _totalCount);
	}

	public void setMaxCount(long maxCount) {
		_maxCount = maxCount;
	}

	public void setTotalCount(long totalCount) {
		_totalCount = totalCount;
	}

	private long _maxCount;
	private long _totalCount;

}