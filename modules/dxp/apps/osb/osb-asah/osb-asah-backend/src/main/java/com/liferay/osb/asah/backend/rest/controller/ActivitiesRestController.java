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

import com.liferay.osb.asah.common.dog.BQAssetDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.entity.BQAsset;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author Ivica Cardic
 */
@RequestMapping("/activities")
@RestController
public class ActivitiesRestController extends BaseRestController {

	public ActivitiesRestController(
		BQAssetDog bqAssetDog, DataSourceDog dataSourceDog) {

		_bqAssetDog = bqAssetDog;
		_dataSourceDog = dataSourceDog;
	}

	@GetMapping("/assets")
	public String getAssetTransformations(
			@RequestParam(name = "filter", required = false) String
				filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return JSONUtil.put(
			"_embedded",
			JSONUtil.put(
				"activities",
				_toTransformations(
					_bqAssetDog.searchBQAssets(
						filterString,
						PageRequest.of(page, size, Sort.by(sorts)))))
		).put(
			"page", getPageJSONObject(page, size, size)
		).toString();
	}

	private JSONArray _toTransformations(List<BQAsset> bqAssets) {
		List<JSONObject> jsonObjects = new ArrayList<>();

		List<DataSource> dataSources = _dataSourceDog.getDataSources();

		for (BQAsset bqAsset : bqAssets) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put("count", bqAsset.getViews());
			jsonObject.put("dataSourceAssetPK", bqAsset.getDataSourceId());

			for (DataSource dataSource : dataSources) {
				if (Objects.equals(
						dataSource.getId(), bqAsset.getDataSourceId())) {

					jsonObject.put("dataSourceName", dataSource.getName());

					break;
				}
			}

			jsonObject.put("id", bqAsset.getId());
			jsonObject.put("name", bqAsset.getAssetTitle());

			jsonObjects.add(jsonObject);
		}

		return new JSONArray(jsonObjects);
	}

	private final BQAssetDog _bqAssetDog;
	private final DataSourceDog _dataSourceDog;

}