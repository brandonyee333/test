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

import com.liferay.osb.asah.common.model.Asset;
import com.liferay.osb.asah.common.model.PropertyFilter;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.AssetRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class AssetDog {

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

	@Autowired
	private AssetRepository _assetRepository;

}