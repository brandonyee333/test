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
 * @author Marcellus Tavares
 */
public class Tuple2<T1, T2> {

	public Tuple2(T1 t1, T2 t2) {
		_t1 = t1;
		_t2 = t2;
	}

	public T1 getT1() {
		return _t1;
	}

	public T2 getT2() {
		return _t2;
	}

	private final T1 _t1;
	private final T2 _t2;

}