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

/**
 * @author Matthew Kong
 */
public class Tuple3<T1, T2, T3> {

	public Tuple3(T1 t1, T2 t2, T3 t3) {
		_t1 = t1;
		_t2 = t2;
		_t3 = t3;
	}

	public T1 getT1() {
		return _t1;
	}

	public T2 getT2() {
		return _t2;
	}

	public T3 getT3() {
		return _t3;
	}

	private final T1 _t1;
	private final T2 _t2;
	private final T3 _t3;

}