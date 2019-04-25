/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.headless.delivery.client.serdes.v1_0;

import com.liferay.headless.delivery.client.dto.v1_0.RelatedAsset;
import com.liferay.headless.delivery.client.json.BaseJSONParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class RelatedAssetSerDes {

	public static RelatedAsset toDTO(String json) {
		RelatedAssetJSONParser relatedAssetJSONParser =
			new RelatedAssetJSONParser();

		return relatedAssetJSONParser.parseToDTO(json);
	}

	public static RelatedAsset[] toDTOs(String json) {
		RelatedAssetJSONParser relatedAssetJSONParser =
			new RelatedAssetJSONParser();

		return relatedAssetJSONParser.parseToDTOs(json);
	}

	public static String toJSON(RelatedAsset relatedAsset) {
		if (relatedAsset == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (relatedAsset.getContentType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"contentType\": ");

			sb.append("\"");

			sb.append(_escape(relatedAsset.getContentType()));

			sb.append("\"");
		}

		if (relatedAsset.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(relatedAsset.getId());
		}

		if (relatedAsset.getTitle() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"title\": ");

			sb.append("\"");

			sb.append(_escape(relatedAsset.getTitle()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, String> toMap(RelatedAsset relatedAsset) {
		if (relatedAsset == null) {
			return null;
		}

		Map<String, String> map = new HashMap<>();

		if (relatedAsset.getContentType() == null) {
			map.put("contentType", null);
		}
		else {
			map.put(
				"contentType", String.valueOf(relatedAsset.getContentType()));
		}

		if (relatedAsset.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(relatedAsset.getId()));
		}

		if (relatedAsset.getTitle() == null) {
			map.put("title", null);
		}
		else {
			map.put("title", String.valueOf(relatedAsset.getTitle()));
		}

		return map;
	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static class RelatedAssetJSONParser
		extends BaseJSONParser<RelatedAsset> {

		@Override
		protected RelatedAsset createDTO() {
			return new RelatedAsset();
		}

		@Override
		protected RelatedAsset[] createDTOArray(int size) {
			return new RelatedAsset[size];
		}

		@Override
		protected void setField(
			RelatedAsset relatedAsset, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "contentType")) {
				if (jsonParserFieldValue != null) {
					relatedAsset.setContentType((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					relatedAsset.setId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "title")) {
				if (jsonParserFieldValue != null) {
					relatedAsset.setTitle((String)jsonParserFieldValue);
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

}