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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

/**
 * @author Ivica Cardic
 */
@Component
public class BQAssetDog {

	public BQAssetDog(BQAssetRepository bqAssetRepository) {
		_bqAssetEventRepository = bqAssetRepository;
	}

	public List<BQAsset> getBQAssets(Collection<String> ids) {
		if (ids.isEmpty()) {
			return Collections.emptyList();
		}

		return _bqAssetEventRepository.findByIdIn(ids);
	}

	public Page<BQAsset> searchBQAssets(
		String filterString, Pageable pageable) {

		return PageableExecutionUtils.getPage(
			_bqAssetEventRepository.searchBQAssets(filterString, pageable),
			pageable,
			() -> _bqAssetEventRepository.countBQAssets(filterString));
	}

	private final BQAssetRepository _bqAssetEventRepository;

}