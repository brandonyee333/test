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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.AssetKeyword;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.BQIdentityInterestScoreRepository;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class AssetDog {

	public void deleteAsset(Asset asset, String deletionDayDateString) {
		Long assetId = asset.getId();

		if (assetId != null) {
			_assetRepository.deleteById(assetId);
		}

		Set<AssetKeyword> assetKeywords = asset.getAssetKeywords();

		if (assetKeywords != null) {
			for (AssetKeyword assetKeyword : assetKeywords) {
				_bqIdentityInterestScoreRepository.
					deleteByKeywordAndRecordedDateGreaterThanEqual(
						assetKeyword.getKeyword(),
						DateUtil.toUTCDate(deletionDayDateString));
			}
		}
	}

	public Page<Asset> getAssetPage(
		String assetType, @Nullable String filterString,
		@Nullable String keyword, int page, int size, Sort sort) {

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_assetRepository.findByAssetTypeAndFilterStringAndKeywords(
				assetType, filterString, keyword, pageRequest),
			pageRequest,
			() -> _assetRepository.countByAssetTypeAndFilterStringAndKeywords(
				assetType, filterString, keyword));
	}

	public List<Asset> getAssets(List<Long> channelIds, int page, int size) {
		return _assetRepository.findByChannelIds(
			channelIds, PageRequest.of(page, size));
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

	@Autowired
	private BQIdentityInterestScoreRepository
		_bqIdentityInterestScoreRepository;

}