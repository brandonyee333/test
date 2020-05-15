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

package com.liferay.osb.asah.common.json;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vishal Reddy
 */
public class JSONArrayPaginatorTest {

	@Test
	public void testPaginate() throws Exception {
		final TestService testService = new TestService(2000);

		new JSONArrayPaginator() {

			@Override
			protected JSONArray paginate(int start, int end) {
				JSONArray jsonArray = testService.getJSONArray(start, end);

				processedCount += jsonArray.length();

				Assert.assertEquals(start + jsonArray.length(), processedCount);

				return jsonArray;
			}

		};

		Assert.assertEquals(0, testService.getUnread());
	}

	private static class TestService {

		public TestService(int size) {
			JSONArray jsonArray = new JSONArray();

			for (int i = 0; i < size; i++) {
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("foo", "bar");

				jsonArray.put(jsonObject);
			}

			_jsonArray = jsonArray;
			_unread = size;
		}

		public JSONArray getJSONArray(int start, int end) {
			if (start >= _jsonArray.length()) {
				return new JSONArray();
			}

			JSONArray jsonArray = new JSONArray();

			if (start < 0) {
				start = 0;
			}

			if (end >= _jsonArray.length()) {
				end = _jsonArray.length();
			}

			for (int i = start; i < end; i++) {
				jsonArray.put(_jsonArray.get(i));

				_unread -= 1;
			}

			return jsonArray;
		}

		public int getUnread() {
			return _unread;
		}

		private final JSONArray _jsonArray;
		private int _unread;

	}

}