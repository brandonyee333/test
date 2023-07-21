/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.handler;

import com.liferay.sync.engine.document.library.event.GetSyncContextEvent;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shinn Lok
 */
public class BaseJSONHandlerTest {

	@Test
	public void testGetException() {
		Handler handler = new BaseJSONHandler(
			new GetSyncContextEvent(0, Collections.<String, Object>emptyMap()));

		String expectedException = "NoSuchJSONWebServiceException";

		String response1 =
			"{\"error\":{\"message\":\"No JSON web service action with path " +
				"/sync-web.syncdlobject/get-sync-context and method POST for " +
					"sync-web\",\"type\":\"java.lang.RuntimeException\"}}";

		Assert.assertEquals(expectedException, handler.getException(response1));

		String response2 =
			"{\"message\":\"No JSON web service action associated with path " +
				"/sync-web.syncdlobject/get-sync-context and method POST for " +
					"sync-web\",\"exception\":\"java.lang.RuntimeException\"}";

		Assert.assertEquals(expectedException, handler.getException(response2));

		String response3 =
			"{\"exception\":\"No JSON web service action associated with " +
				"path /sync-web.syncdlobject/get-sync-context and method " +
					"POST for sync-web\"}";

		Assert.assertEquals(expectedException, handler.getException(response3));

		String response4 =
			"{\"message\":\"Foo\",\"exception\":" +
				"\"java.lang.RuntimeException\"}";

		Assert.assertNotEquals(
			expectedException, handler.getException(response4));
	}

}