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

package com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.dog.BQUserDog;
import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.ListUtil;

import java.io.OutputStream;

import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.data.domain.PageRequest;

/**
 * @author Marcos Martins
 */
public class DXPEntityDataExporter extends BaseDataExporter {

	public DXPEntityDataExporter(
			BQUserDog bqUserDog, String fieldName, String fieldValue,
			JsonFactory jsonFactory, ObjectMapper objectMapper,
			OutputStream outputStream)
		throws Exception {

		super(jsonFactory, outputStream, null);

		_bqUserDog = bqUserDog;
		_fieldName = fieldName;
		_fieldValue = fieldValue;

		jsonGenerator.useDefaultPrettyPrinter();

		_objectMapper = objectMapper;
	}

	@Override
	public void export() throws Exception {
		int page = 0;

		while (true) {
			JSONObject resultPageJSONObject = doGetResultPageJSONObject(
				String.valueOf(page++));

			JSONArray resultsJSONArray = resultPageJSONObject.getJSONArray(
				"results");

			if (resultsJSONArray.length() == 0) {
				break;
			}

			exportResults(resultsJSONArray);
		}

		jsonGenerator.close();
	}

	@Override
	protected JSONObject doGetResultPageJSONObject(String page) {
		return JSONUtil.put(
			"results",
			new JSONArray(
				ListUtil.map(
					_bqUserDog.findByFields(
						Collections.singletonMap(_fieldName, _fieldValue),
						PageRequest.of(Integer.parseInt(page), _PAGE_SIZE)),
					this::_toJSONObject)));
	}

	private JSONObject _toJSONObject(BQUser bqUser) {
		return _objectMapper.convertValue(bqUser, JSONObject.class);
	}

	private static final int _PAGE_SIZE = 50;

	private final BQUserDog _bqUserDog;
	private final String _fieldName;
	private final String _fieldValue;
	private final ObjectMapper _objectMapper;

}