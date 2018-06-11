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

package com.liferay.osb.customer.rabbitmq.connector.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class DuplicateConsumerException extends PortalException {

	public DuplicateConsumerException() {
	}

	public DuplicateConsumerException(String msg) {
		super(msg);
	}

	public DuplicateConsumerException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DuplicateConsumerException(Throwable cause) {
		super(cause);
	}

}