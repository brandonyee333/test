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

package com.liferay.osb.asah.common.spring.http.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Marcellus Tavares
 */
public class OSBAsahVirusFoundException extends OSBAsahException {

	public OSBAsahVirusFoundException(String virusName) {
		super(
			HttpStatus.BAD_REQUEST,
			String.format("Virus %s was detected ", virusName));
	}

}