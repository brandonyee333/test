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

import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.Transient;

/**
 * @author Rachael Koestartyo
 */
public class Distribution {

	public Distribution() {
	}

	public Distribution(Integer count, List<Object> values) {
		_count = count;
		_values = values;
	}

	public Distribution(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Distribution)) {
			return false;
		}

		Distribution distribution = (Distribution)obj;

		if (Objects.equals(_count, distribution._count) &&
			Objects.equals(_values, distribution._values)) {

			return true;
		}

		return false;
	}

	public Integer getCount() {
		return _count;
	}

	public List<Object> getValues() {
		return _values;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_count, _values);
	}

	public void setCount(Integer count) {
		_count = count;
	}

	public void setValues(List<Object> values) {
		_values = values;
	}

	@Transient
	private Integer _count;

	@Transient
	private List<Object> _values;

}