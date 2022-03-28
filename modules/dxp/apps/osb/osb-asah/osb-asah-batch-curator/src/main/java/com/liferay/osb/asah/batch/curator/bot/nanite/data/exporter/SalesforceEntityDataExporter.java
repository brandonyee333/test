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

import com.liferay.osb.asah.common.dog.SalesforceEntityDog;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
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
			SalesforceEntityDog salesforceEntityDog, SalesforceEntity.Type type)
		throws Exception {

		super(jsonFactory, outputStream, null);

		_fieldName = fieldName;
		_fieldValue = fieldValue;

		jsonGenerator.useDefaultPrettyPrinter();

		_objectMapper = objectMapper;
		_salesforceEntityDog = salesforceEntityDog;
		_type = type;
	}

	@Override
	protected JSONObject doGetResultPageJSONObject(String after) {
		return JSONUtil.put(
			"results",
			new JSONArray(
				ListUtil.map(
					_salesforceEntityDog.getSalesforceEntities(
						after, _fieldName, _fieldValue, _PAGE_SIZE, _type),
					this::_toJSONObject)));
	}

	private JSONObject _toJSONObject(SalesforceEntity salesforceEntity) {
		return _objectMapper.convertValue(salesforceEntity, JSONObject.class);
	}

	private static final int _PAGE_SIZE = 50;

	private final String _fieldName;
	private final String _fieldValue;
	private final ObjectMapper _objectMapper;
	private final SalesforceEntityDog _salesforceEntityDog;
	private final SalesforceEntity.Type _type;

}