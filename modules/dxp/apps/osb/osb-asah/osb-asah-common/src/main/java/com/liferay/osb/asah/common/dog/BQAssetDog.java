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

import com.liferay.osb.asah.common.entity.BQAsset;
import com.liferay.osb.asah.common.repository.BQAssetRepository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * @author Ivica Cardic
 */
@Component
public class BQAssetDog {

	public BQAssetDog(BQAssetRepository bqAssetRepository) {
		_bqAssetEventRepository = bqAssetRepository;
	}

	public List<BQAsset> searchBQAssets(
		String filterString, Pageable pageable) {

		return _bqAssetEventRepository.searchBQAssets(filterString, pageable);
	}

	private final BQAssetRepository _bqAssetEventRepository;

}