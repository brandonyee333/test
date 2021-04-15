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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.model.Asset;
import com.liferay.osb.asah.common.model.PropertyFilter;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class AssetDog {

	public Asset addAsset(Asset asset) {
		if (!asset.isNew()) {
			throw new IllegalArgumentException("Unable to add asset not new");
		}

		return _assetRepository.save(asset);
	}

	public Asset fetchAsset(String dataSourceAssetPK, Long dataSourceId) {
		Optional<Asset> assetOptional =
			_assetRepository.findByDataSourceAssetPKAndDataSourceId(
				dataSourceAssetPK, dataSourceId);

		return assetOptional.orElse(null);
	}

	public Asset getAsset(Long assetId) {
		Optional<Asset> assetOptional = _assetRepository.findById(assetId);

		return assetOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no asset with ID " + assetId));
	}

	public Page<Asset> getAssetPage(
		String assetType, String keyword, List<PropertyFilter> propertyFilters,
		int page, int size, Sort sort) {

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_assetRepository.searchAssets(
				assetType, keyword, propertyFilters, pageRequest),
			pageRequest,
			() -> _assetRepository.countAssets(
				assetType, keyword, propertyFilters));
	}

	public Page<Asset> searchAssetsPage(
		String filterString, int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return PageableExecutionUtils.getPage(
			_assetRepository.searchAssets(filterString, pageRequest),
			pageRequest, () -> _assetRepository.countAssets(filterString));
	}

	public Asset updateAsset(Asset asset) {
		if (asset.getId() == null) {
			throw new IllegalArgumentException(
				"Unable to update asset with ID null");
		}

		return _assetRepository.save(asset);
	}

	@Autowired
	private AssetRepository _assetRepository;

}