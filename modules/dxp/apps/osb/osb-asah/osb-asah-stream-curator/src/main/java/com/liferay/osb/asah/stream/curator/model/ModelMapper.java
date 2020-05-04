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

package com.liferay.osb.asah.stream.curator.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.Collection;
import java.util.List;

import org.json.JSONArray;

/**
 * @author Brian Wing Shun Chan
 */
public class ModelMapper {

	public static JSONArray toJSONArray(Collection<? extends Model> models) {
		try {
			return new JSONArray(_objectMapper.writeValueAsString(models));
		}
		catch (JsonProcessingException jpe) {
			throw new RuntimeException(jpe);
		}
	}

	public static <T extends Model> List<T> toModels(
		JSONArray jsonArray, Class<? extends Model> clazz) {

		try {
			return _objectMapper.readValue(
				jsonArray.toString(), _getCollectionType(clazz));
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static <T> CollectionType _getCollectionType(Class<T> clazz) {
		TypeFactory typeFactory = TypeFactory.defaultInstance();

		return typeFactory.constructCollectionType(List.class, clazz);
	}

	private static final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			TypeFactory typeFactory = TypeFactory.defaultInstance();

			typeFactory = typeFactory.withClassLoader(
				ModelMapper.class.getClassLoader());

			setTypeFactory(typeFactory);
		}
	};

}