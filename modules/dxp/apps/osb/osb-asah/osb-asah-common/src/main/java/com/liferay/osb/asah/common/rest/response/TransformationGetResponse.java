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

package com.liferay.osb.asah.common.rest.response;

import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Brian Wing Shun Chan
 * @author Eddie Olson
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
public class TransformationGetResponse extends CollectionGetResponse {

	public void setApply(String apply) {
		_apply = apply;
	}

	public void setSorts(
		Map<String, String> sortFieldMappings, String[] sorts) {

		if (sorts == null) {
			return;
		}

		for (int i = sorts.length - 1; i > 0; i--) {
			String sort = sorts[i];

			if (!sort.equals("asc") && !sort.equals("desc")) {
				continue;
			}

			String sortField = sorts[i - 1];

			if ((sortFieldMappings != null) &&
				sortFieldMappings.containsKey(sortField)) {

				sortField = sortFieldMappings.get(sortField);
			}

			SortOrder sortOrder = SortOrder.ASC;

			if (sort.equals("desc")) {
				sortOrder = SortOrder.DESC;
			}

			sortOrderPairs.add(Pair.of(sortField, sortOrder));
		}
	}

	public void setSupportedFieldName(String supportedFieldName) {
		_supportedFieldName = supportedFieldName;
	}

	public void setTransformationJSONArrayFunction(
		TransformationJSONArrayFunction transformationJSONArrayFunction) {

		_transformationJSONArrayFunction = transformationJSONArrayFunction;
	}

	public void setTransformationName(String transformationName) {
		_transformationName = transformationName;
	}

	@Override
	protected JSONObject getEmbeddedJSONObject() throws Exception {
		JSONArray transformationJSONArray = _getTransformationJSONArray();

		if (_transformationJSONArrayFunction != null) {
			totalElements = _transformationJSONArrayFunction.getTotalElements();
		}

		return JSONUtil.put(_transformationName, transformationJSONArray);
	}

	private String _getComputeFunctionString() {
		int computeFunctionOpenParenthesisIndex = _apply.indexOf(
			'(', _APPLY_PREFIX_COMPUTE.length() + 1);

		if (computeFunctionOpenParenthesisIndex < 0) {
			throw new IllegalArgumentException(
				"No function found inside compute: " + _apply);
		}

		String computeFunction = _apply.substring(
			_APPLY_PREFIX_COMPUTE.length(),
			computeFunctionOpenParenthesisIndex);

		computeFunction = computeFunction.trim();

		int computeFunctionCloseParenthesisIndex = _apply.indexOf(
			')', computeFunctionOpenParenthesisIndex + 1);

		if (computeFunctionCloseParenthesisIndex < 0) {
			throw new IllegalArgumentException(
				"Unclosed function found inside compute: " + _apply);
		}

		String fieldName = _apply.substring(
			computeFunctionOpenParenthesisIndex + 1,
			computeFunctionCloseParenthesisIndex);

		fieldName = fieldName.trim();

		if (!fieldName.equals(_supportedFieldName)) {
			throw new IllegalArgumentException(
				"Compute only supports the field \"" + _supportedFieldName +
					"\" for " + _transformationName);
		}

		return computeFunction;
	}

	private JSONArray _getTransformationJSONArray() throws Exception {
		if (StringUtils.isEmpty(_apply) &&
			(_transformationJSONArrayFunction != null)) {

			return _transformationJSONArrayFunction.apply(
				collectionName, "day", elasticsearchInvoker, page, size,
				sortOrderPairs, _supportedFieldName, queryBuilder);
		}

		if (!StringUtils.isEmpty(_apply) &&
			_apply.startsWith(_APPLY_PREFIX_COMPUTE)) {

			if (_supportedFieldName == null) {
				throw new IllegalArgumentException(
					"Compute function not supported for " +
						_transformationName);
			}

			return _transformationJSONArrayFunction.apply(
				collectionName, _getComputeFunctionString(),
				elasticsearchInvoker, page, size, sortOrderPairs,
				_supportedFieldName, queryBuilder);
		}

		throw new IllegalArgumentException("Invalid apply: " + _apply);
	}

	private static final String _APPLY_PREFIX_COMPUTE = "compute(";

	private String _apply;
	private String _supportedFieldName;
	private TransformationJSONArrayFunction _transformationJSONArrayFunction;
	private String _transformationName;

}