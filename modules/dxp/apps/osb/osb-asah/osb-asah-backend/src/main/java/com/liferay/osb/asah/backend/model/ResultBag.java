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

package com.liferay.osb.asah.backend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author André Miranda
 * @author Inácio Nery
 */
public class ResultBag<T> {

	public ResultBag() {
	}

	public ResultBag(List<T> results, long total) {
		this.results = results;
		this.total = total;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ResultBag)) {
			return false;
		}

		ResultBag resultBag = (ResultBag)obj;

		if (Objects.equals(results, resultBag.results) &&
			Objects.equals(total, resultBag.total)) {

			return true;
		}

		return false;
	}

	public List<T> getResults() {
		return results;
	}

	public long getTotal() {
		return total;
	}

	@Override
	public int hashCode() {
		return Objects.hash(results, total);
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	protected List<T> results = new ArrayList<>();
	protected long total;

}