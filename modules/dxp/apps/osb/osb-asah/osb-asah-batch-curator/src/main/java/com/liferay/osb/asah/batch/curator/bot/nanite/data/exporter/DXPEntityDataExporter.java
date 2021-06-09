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

import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.ListUtil;

import java.io.OutputStream;

import java.util.Collections;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Marcos Martins
 */
public class DXPEntityDataExporter extends BaseDataExporter {

	public DXPEntityDataExporter(
			String collectionName, String fieldName, String fieldValue,
			JsonFactory jsonFactory, DXPEntityDog dxpEntityDog,
			ObjectMapper objectMapper, OutputStream outputStream)
		throws Exception {

		super(jsonFactory, outputStream, null);

		_collectionName = collectionName;
		_fieldName = fieldName;
		_fieldValue = fieldValue;
		_dxpEntityDog = dxpEntityDog;

		jsonGenerator.useDefaultPrettyPrinter();

		_objectMapper = objectMapper;
	}

	@Override
	protected JSONObject doGetResultPageJSONObject(String after) {
		return JSONUtil.put(
			"results",
			new JSONArray(
				ListUtil.map(
					_dxpEntityDog.findByAfterAndFieldsAndType(
						Long.valueOf(after),
						Collections.singletonMap(_fieldName, _fieldValue),
						_PAGE_SIZE,
						DXPEntity.Type.ofCollectionName(_collectionName)),
					this::_toJSONObject)));
	}

	private JSONObject _toJSONObject(DXPEntity dxpEntity) {
		return _objectMapper.convertValue(dxpEntity, JSONObject.class);
	}

	private static final int _PAGE_SIZE = 50;

	private final String _collectionName;
	private final DXPEntityDog _dxpEntityDog;
	private final String _fieldName;
	private final String _fieldValue;
	private final ObjectMapper _objectMapper;

}