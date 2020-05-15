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

import java.util.Objects;

/**
 * @author Matthew Kong
 */
public class Composition {

	public Composition() {
	}

	public Composition(long count, String name) {
		_count = count;
		_name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Composition)) {
			return false;
		}

		Composition composition = (Composition)obj;

		if (Objects.equals(_name, composition._name) &&
			Objects.equals(_count, composition._count)) {

			return true;
		}

		return false;
	}

	public long getCount() {
		return _count;
	}

	public String getName() {
		return _name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_name, _count);
	}

	public void setCount(long count) {
		_count = count;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _count;
	private String _name;

}