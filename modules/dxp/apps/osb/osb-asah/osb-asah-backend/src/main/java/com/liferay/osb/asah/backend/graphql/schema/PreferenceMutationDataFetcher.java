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

package com.liferay.osb.asah.backend.graphql.schema;

import com.liferay.osb.asah.backend.graphql.GraphQLTypeWiring;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoPreferenceDog;
import com.liferay.osb.asah.common.model.Preference;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
@GraphQLTypeWiring(fieldName = "preference", typeName = "MutationType")
public class PreferenceMutationDataFetcher implements DataFetcher<Preference> {

	@Override
	public Preference get(DataFetchingEnvironment dataFetchingEnvironment) {
		String key = dataFetchingEnvironment.getArgument("key");
		String value = dataFetchingEnvironment.getArgument("value");

		_validate(key, value);

		return _faroInfoPreferenceDog.addPreference(key, value);
	}

	private void _validate(String key, String value) {
		Function<String, Boolean> function = _validatorFunctions.get(key);

		if ((function == null) || !function.apply(value)) {
			throw new RuntimeException(
				String.format("Invalid preference: %s=%s", key, value));
		}
	}

	private static final Map<String, Function<String, Boolean>>
		_validatorFunctions = new HashMap<String, Function<String, Boolean>>() {
			{
				put(
					"data-retention-period",
					value -> {
						long longValue = Long.parseLong(value);

						if ((longValue <= 0) ||
							(longValue > TimeUnit.DAYS.toMillis(30 * 13))) {

							return false;
						}

						return true;
					});
				put(
					"search-query-strings",
					value -> {
						try{
							new JSONArray(value);
						}catch(JSONException ex){
							return false;
						}

						return true;
					});
			}
		};

	@Autowired
	private FaroInfoPreferenceDog _faroInfoPreferenceDog;

}