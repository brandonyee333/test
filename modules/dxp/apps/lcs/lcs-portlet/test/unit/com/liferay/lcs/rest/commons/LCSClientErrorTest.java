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

package com.liferay.lcs.rest.commons;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Igor Beslic
 */
@RunWith(PowerMockRunner.class)
public class LCSClientErrorTest extends PowerMockito {

	@Test
	public void testToJSON() throws Exception {
		String restErrorJSON =
			LCSRestError.NO_SUCH_LCS_SUBSCRIPTION_ENTRY.toJSON(
				"Test Message", 400, "arg1", "10000", "arg2", 20000);

		ObjectMapper objectMapper = new ObjectMapper();

		ObjectNode objectNode = objectMapper.readValue(
			restErrorJSON, ObjectNode.class);

		Assert.assertTrue(objectNode.has("args"));
		Assert.assertTrue(objectNode.has("errorCode"));
		Assert.assertTrue(objectNode.has("message"));
		Assert.assertTrue(objectNode.has("status"));

		JsonNode errorCodeJsonNode = objectNode.get("errorCode");

		Assert.assertEquals(
			LCSRestError.NO_SUCH_LCS_SUBSCRIPTION_ENTRY.getErrorCode(),
			errorCodeJsonNode.asInt());

		JsonNode statusJsonNode = objectNode.get("status");

		Assert.assertEquals(400, statusJsonNode.asInt());

		restErrorJSON = LCSRestError.NO_SUCH_LCS_SUBSCRIPTION_ENTRY.toJSON(
			"Test Message", 404);

		objectNode = objectMapper.readValue(restErrorJSON, ObjectNode.class);

		Assert.assertFalse(objectNode.has("args"));
		Assert.assertTrue(objectNode.has("errorCode"));
		Assert.assertTrue(objectNode.has("message"));
		Assert.assertTrue(objectNode.has("status"));

		statusJsonNode = objectNode.get("status");

		Assert.assertEquals(404, statusJsonNode.asInt());
	}

	@Test
	public void testToRESTError() {
		for (LCSRestError lcsRestError : LCSRestError.values()) {
			Assert.assertEquals(
				lcsRestError,
				LCSRestError.toLCSClientError(lcsRestError.getErrorCode()));
		}
	}

}