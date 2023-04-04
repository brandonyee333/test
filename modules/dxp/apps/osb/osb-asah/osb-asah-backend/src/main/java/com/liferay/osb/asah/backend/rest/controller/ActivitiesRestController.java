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

import com.liferay.osb.asah.backend.dto.BQAssetDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.common.dog.BQAssetDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.entity.BQAsset;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.json.JSONArray;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public PageDTO<BQAssetDTO> getBQAssetDTOPageDTO(
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		Page<BQAsset> bqAssetsPage = _bqAssetDog.searchBQAssets(
			filterString, PageRequest.of(page, size, SortUtil.getSort(sorts)));

		return new PageDTO(
			"_embedded",
			JSONUtil.put(
				"activities",
				new org.jooq.tools.json.JSONArray(
					_toBQAssetDTOs(bqAssetsPage.getContent()))),
			bqAssetsPage.getNumber(), bqAssetsPage.getSize(),
			bqAssetsPage.getTotalElements(), bqAssetsPage.getTotalPages());
	}

	private List<BQAssetDTO> _toBQAssetDTOs(List<BQAsset> bqAssets) {
		List<BQAssetDTO> bqAssetDTOs = new ArrayList<>();

		if (bqAssets.isEmpty()) {
			return bqAssetDTOs;
		}

		List<DataSource> dataSources = _dataSourceDog.getDataSources();

		for (BQAsset bqAsset : bqAssets) {
			BQAssetDTO bqAssetDTO = new BQAssetDTO(bqAsset);

			for (DataSource dataSource : dataSources) {
				if (Objects.equals(
						dataSource.getId(), bqAssetDTO.getDataSourceId())) {

					bqAssetDTO.setDataSourceName(dataSource.getName());

					break;
				}
			}

			bqAssetDTOs.add(bqAssetDTO);
		}

		return bqAssetDTOs;
	}

	private final BQAssetDog _bqAssetDog;
	private final DataSourceDog _dataSourceDog;

}