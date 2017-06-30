/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.model;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Kyle Bischof
 */
public class TrainingCustomerConstants {

	public static final String EMAIL_ADDRESS_SUFFIX = "@training-customer.com";

	public static final String SCREEN_NAME_PREFIX = "training.customer.";

	public static final int STATUS_ATTENDED = 1;

	public static final int STATUS_CERTIFIED = 3;

	public static final int STATUS_PENDING_PARTICIPANT_FORM_COMPLETION = 2;

	public static final int STATUS_REGISTERED = 0;

	public static final String TYPE_REGULAR_USER = "regular-user";

	public static final String TYPE_TRAINING_USER = "training-user";

	public static String getStatusLabel(int status) {
		if (status == STATUS_ATTENDED) {
			return "attended";
		}
		else if (status == STATUS_CERTIFIED) {
			return "completed";
		}
		else if (status == STATUS_PENDING_PARTICIPANT_FORM_COMPLETION) {
			return "email-sent";
		}
		else if (status == STATUS_REGISTERED) {
			return "registered";
		}

		return StringPool.BLANK;
	}

}