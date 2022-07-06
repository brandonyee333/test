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

import com.liferay.osb.asah.common.dog.BQSalesforceEntityDog;
import com.liferay.osb.asah.common.entity.BQSalesforceEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.ListUtil;

import java.io.OutputStream;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Robson Pastor
 */
public class SalesforceEntityDataExporter extends BaseDataExporter {

	public SalesforceEntityDataExporter(
			String fieldName, String fieldValue, JsonFactory jsonFactory,
			ObjectMapper objectMapper, OutputStream outputStream,
			BQSalesforceEntityDog salesforceEntityDog,
			BQSalesforceEntity.Type type)
		throws Exception {

		super(jsonFactory, outputStream, null);

		_fieldName = fieldName;
		_fieldValue = fieldValue;
		_objectMapper = objectMapper;
		_type = type;

		_bqSalesforceEntityDog = salesforceEntityDog;

		jsonGenerator.useDefaultPrettyPrinter();
	}

	@Override
	protected JSONObject doGetResultPageJSONObject(String after) {
		return JSONUtil.put(
			"results",
			new JSONArray(
				ListUtil.map(
					_bqSalesforceEntityDog.getBQSalesforceEntities(
						after, _fieldName, _fieldValue, _PAGE_SIZE, _type),
					this::_toJSONObject)));
	}

	private JSONObject _toJSONObject(BQSalesforceEntity bqSalesforceEntity) {
		return _objectMapper.convertValue(bqSalesforceEntity, JSONObject.class);
	}

	private static final int _PAGE_SIZE = 50;

	private final BQSalesforceEntityDog _bqSalesforceEntityDog;
	private final String _fieldName;
	private final String _fieldValue;
	private final ObjectMapper _objectMapper;
	private final BQSalesforceEntity.Type _type;

}