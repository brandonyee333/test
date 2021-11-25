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

package com.liferay.osb.asah.common.postgresql.converter;

import org.json.JSONArray;

import org.postgresql.util.PGobject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * @author Rachael Koestartyo
 */
@WritingConverter
public class JSONArrayToPGobjectConverter
	implements Converter<JSONArray, PGobject> {

	@Override
	public PGobject convert(JSONArray jsonArray) {
		try {
			PGobject pGobject = new PGobject();

			pGobject.setType("json");
			pGobject.setValue(jsonArray.toString());

			return pGobject;
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

}