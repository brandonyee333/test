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

package com.liferay.osb.asah.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Leslie Wong
 */
public class IndividualIdThreadLocal {

	public static Long getIndividualId() {
		return _individualId.get();
	}

	public static void remove() {
		_individualId.remove();
	}

	public static void setIndividualId(Long individualId) {
		if (_log.isDebugEnabled()) {
			_log.debug("setIndividualId" + individualId);
		}

		_individualId.set(individualId);
	}

	private static final Log _log = LogFactory.getLog(
		IndividualIdThreadLocal.class);

	private static final ThreadLocal<Long> _individualId = new ThreadLocal<>();

}