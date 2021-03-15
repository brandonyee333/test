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

package com.liferay.osb.asah.backend.rest.controller;

import com.liferay.osb.asah.backend.rest.response.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoFieldMappingDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualSegmentDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 */
@RequestMapping("/field-mappings")
@RestController
public class FieldMappingsRestController extends BaseRestController {

	@DeleteMapping("/{id}")
	public void deleteFieldMapping(@PathVariable String id) throws Exception {
		JSONObject fieldMappingJSONObject = faroInfoElasticsearchInvoker.fetch(
			"field-mappings", id);

		if (fieldMappingJSONObject == null) {
			return;
		}

		faroInfoElasticsearchInvoker.delete("field-mappings", id);

		_addReprocessAsahTask(fieldMappingJSONObject);
	}

	@GetMapping("/{id}")
	public String getFieldMapping(@PathVariable String id) throws Exception {
		return toItemGetResponse("field-mappings", id);
	}

	@GetMapping(params = "!apply")
	public String getFieldMappings(
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toCollectionGetResponse(
			"field-mappings", null, page,
			FilterStringToQueryBuilderConverter.convert(filterString), size,
			sorts);
	}

	@GetMapping(params = "apply")
	public String getFieldMappingTransformations(
			@RequestParam String apply,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return toTransformationGetResponse(
			"field-mappings", page,
			FilterStringToQueryBuilderConverter.convert(filterString), size,
			null, null,
			new TermsAggregationTransformationJSONArrayFunction(apply, null),
			"field-mapping-transformations");
	}

	@PatchMapping("/{id}")
	public String patchFieldMapping(
			@PathVariable String id, @RequestBody Map<String, Object> map)
		throws Exception {

		JSONObject fieldMappingJSONObject = faroInfoElasticsearchInvoker.get(
			"field-mappings", id);

		JSONObject dataSourceFieldNamesJSONObject =
			fieldMappingJSONObject.getJSONObject("dataSourceFieldNames");

		String dataSourceId = String.valueOf(map.get("dataSourceId"));
		Object dataSourceFieldName = map.get("fieldName");
		List<String> deletedFieldMappingIds = new ArrayList<>();

		if ((dataSourceFieldName == null) ||
			StringUtils.isEmpty(String.valueOf(dataSourceFieldName))) {

			dataSourceFieldNamesJSONObject.remove(dataSourceId);

			if ((dataSourceFieldNamesJSONObject.length() == 0) &&
				_faroInfoFieldMappingDog.deleteFieldMapping(
					fieldMappingJSONObject)) {

				deletedFieldMappingIds.add(id);
			}
		}
		else {
			dataSourceFieldNamesJSONObject.put(
				dataSourceId, dataSourceFieldName);
		}

		String responseJSON = null;

		if (deletedFieldMappingIds.isEmpty()) {
			responseJSON = toPatchResponse(
				"field-mappings", id, fieldMappingJSONObject.toString());
		}

		_faroInfoIndividualSegmentDog.disableDynamicIndividualSegments(
			null, deletedFieldMappingIds);

		_addReprocessAsahTask(
			Long.valueOf(dataSourceId),
			fieldMappingJSONObject.getString("ownerType"));

		return responseJSON;
	}

	@PatchMapping
	public String patchFieldMappings(
			@RequestParam String context, @RequestParam String dataSourceId,
			@RequestParam String ownerType,
			@RequestBody Map<String, String> map)
		throws Exception {

		JSONArray responseJSONArray = new JSONArray();

		List<String> deletedFieldMappingIds = new ArrayList<>();

		for (Map.Entry<String, String> entry : map.entrySet()) {
			String fieldName = entry.getKey();

			JSONObject fieldMappingJSONObject =
				faroInfoElasticsearchInvoker.fetch(
					"field-mappings",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("context", context)
					).filter(
						QueryBuilders.termQuery("fieldName", fieldName)
					).filter(
						QueryBuilders.termQuery("ownerType", ownerType)
					));

			if (fieldMappingJSONObject == null) {
				_log.error(
					"Unable to get field mapping with context \"" + context +
						"\", field name \"" + fieldName +
							"\", and owner type \"" + ownerType + "\"");

				continue;
			}

			String dataSourceFieldName = entry.getValue();
			JSONObject dataSourceFieldNamesJSONObject =
				fieldMappingJSONObject.getJSONObject("dataSourceFieldNames");
			boolean fieldMappingDeleted = false;
			String fieldMappingId = fieldMappingJSONObject.getString("id");

			if (StringUtils.isEmpty(dataSourceFieldName)) {
				dataSourceFieldNamesJSONObject.remove(dataSourceId);

				if ((dataSourceFieldNamesJSONObject.length() == 0) &&
					_faroInfoFieldMappingDog.deleteFieldMapping(
						fieldMappingJSONObject)) {

					deletedFieldMappingIds.add(fieldMappingId);

					fieldMappingDeleted = true;
				}
			}
			else {
				dataSourceFieldNamesJSONObject.put(
					dataSourceId, dataSourceFieldName);
			}

			if (!fieldMappingDeleted) {
				responseJSONArray.put(
					new JSONObject(
						toPatchResponse(
							"field-mappings", fieldMappingId,
							fieldMappingJSONObject.toString())));
			}
			else {
				responseJSONArray.put(JSONObject.NULL);
			}
		}

		_faroInfoIndividualSegmentDog.disableDynamicIndividualSegments(
			null, deletedFieldMappingIds);

		_addReprocessAsahTask(Long.valueOf(dataSourceId), ownerType);

		return responseJSONArray.toString();
	}

	@PostMapping
	public String postFieldMapping(@RequestBody String json) throws Exception {
		JSONObject jsonObject = new JSONObject(json);

		String fieldName = jsonObject.getString("fieldName");

		if (_faroInfoFieldMappingDog.fetchFieldMappingJSONObject(
				jsonObject.getString("context"), fieldName,
				jsonObject.getString("ownerType")) != null) {

			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST, "Duplicate field name " + fieldName);
		}

		String responseJSON = toPostResponse("field-mappings", json);

		_addReprocessAsahTask(new JSONObject(responseJSON));

		return responseJSON;
	}

	@PutMapping("/{id}")
	public String putFieldMapping(
			@PathVariable String id, @RequestBody String json)
		throws Exception {

		String responseJSON = toPutResponse("field-mappings", id, json);

		_addReprocessAsahTask(new JSONObject(responseJSON));

		return responseJSON;
	}

	private void _addReprocessAsahTask(JSONObject fieldMappingJSONObject)
		throws Exception {

		JSONObject dataSourceFieldNamesJSONObject =
			fieldMappingJSONObject.getJSONObject("dataSourceFieldNames");

		for (String dataSourceId : dataSourceFieldNamesJSONObject.keySet()) {
			_addReprocessAsahTask(
				Long.valueOf(dataSourceId),
				fieldMappingJSONObject.getString("ownerType"));
		}
	}

	private void _addReprocessAsahTask(Long dataSourceId, String ownerType)
		throws Exception {

		DataSource dataSource = _dataSourceDog.getDataSource(dataSourceId);

		String naniteClassName = _naniteClassNames.get(
			dataSource.getProviderType() + "#" + ownerType);

		if (naniteClassName == null) {
			if (_log.isInfoEnabled()) {
				StringBuilder sb = new StringBuilder();

				sb.append("Skip adding reprocess task for data source ");
				sb.append(dataSourceId);
				sb.append(" because reprocess operations are not supported ");
				sb.append("for data source type ");
				sb.append(dataSource.getProviderType());
				sb.append(" and owner type ");
				sb.append(ownerType);

				_log.info(sb.toString());
			}

			return;
		}

		_asahTaskDog.scheduleAsahTask(
			naniteClassName,
			JSONUtil.put(
				"dataSourceId", String.valueOf(dataSourceId)
			).put(
				"type", "reprocess"
			));
	}

	private static final Log _log = LogFactory.getLog(
		FieldMappingsRestController.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private FaroInfoFieldMappingDog _faroInfoFieldMappingDog;

	@Autowired
	private FaroInfoIndividualSegmentDog _faroInfoIndividualSegmentDog;

	private final Map<String, String> _naniteClassNames =
		new HashMap<String, String>() {
			{
				put("CSV#individual", "CSVIndividualsNanite");
				put("LIFERAY#individual", "DXPIndividualsNanite");
			}
		};

}