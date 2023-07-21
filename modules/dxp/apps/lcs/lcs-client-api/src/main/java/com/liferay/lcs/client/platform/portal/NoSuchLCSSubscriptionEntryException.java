/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.platform.portal;

/**
 * @author Mladen Cikara
 */
public class NoSuchLCSSubscriptionEntryException extends Exception {

	public NoSuchLCSSubscriptionEntryException() {
	}

	public NoSuchLCSSubscriptionEntryException(String msg) {
		super(msg);
	}

	public NoSuchLCSSubscriptionEntryException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchLCSSubscriptionEntryException(Throwable cause) {
		super(cause);
	}

}