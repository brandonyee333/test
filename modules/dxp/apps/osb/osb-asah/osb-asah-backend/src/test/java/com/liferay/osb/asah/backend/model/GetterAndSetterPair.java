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

import java.lang.reflect.Method;

/**
 * @author Inácio Nery
 */
public class GetterAndSetterPair {

	public Method getGetMethod() {
		return _getMethod;
	}

	public Method getSetMethod() {
		return _setMethod;
	}

	public boolean hasGetMethodAndSetMethod() {
		if ((_getMethod != null) && (_setMethod != null)) {
			return true;
		}

		return false;
	}

	public void setGetMethod(Method getMethod) {
		_getMethod = getMethod;
	}

	public void setSetMethod(Method setMethod) {
		_setMethod = setMethod;
	}

	private Method _getMethod;
	private Method _setMethod;

}