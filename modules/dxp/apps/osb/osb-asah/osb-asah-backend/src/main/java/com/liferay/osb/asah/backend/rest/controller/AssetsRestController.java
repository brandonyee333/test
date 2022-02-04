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

import com.liferay.osb.asah.backend.dto.AssetDTO;
import com.liferay.osb.asah.backend.dto.PageDTO;
import com.liferay.osb.asah.backend.dto.TransformationDTO;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.model.Transformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 */
@RequestMapping("/assets")
@RestController
public class AssetsRestController extends BaseRestController {

	@GetMapping("/{id}")
	public AssetDTO getAssetDTO(@PathVariable Long id) {
		return new AssetDTO(_assetDog.getAsset(id));
	}

	@GetMapping(params = "!apply")
	public PageDTO<AssetDTO> getAssetDTOsPageDTO(
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size,
		@RequestParam(name = "sort", required = false) String[] sorts) {

		return _toPageDTO(
			_assetDog.getAssetPage(
				filterString, page, Math.max(1, size), sorts));
	}

	@GetMapping(params = "apply")
	public PageDTO<TransformationDTO> getAssetTransformationDTOsPageDTO(
		@RequestParam String apply,
		@RequestParam(name = "filter", required = false) String filterString,
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "20") int size) {

		return _toTransformationDTOsPageDTO(
			"asset-transformations",
			_assetDog.getTransformationPage(apply, filterString, page, size));
	}

	private PageDTO<AssetDTO> _toPageDTO(
		AssetDTO assetDTO, Page<Asset> assetPage) {

		return new PageDTO<>(
			"_embedded", assetDTO, assetPage.getNumber(), assetPage.getSize(),
			assetPage.getTotalElements(), assetPage.getTotalPages());
	}

	private PageDTO<AssetDTO> _toPageDTO(Page<Asset> assetPage) {
		return _toPageDTO(new AssetDTO(assetPage.getContent()), assetPage);
	}

	private PageDTO<TransformationDTO> _toTransformationDTOsPageDTO(
		String transformationKey, Page<Transformation> transformationsPage) {

		return _toTransformationDTOsPageDTO(
			new TransformationDTO(
				transformationKey, transformationsPage.getContent()),
			transformationsPage);
	}

	private PageDTO<TransformationDTO> _toTransformationDTOsPageDTO(
		TransformationDTO transformationDTO,
		Page<Transformation> transformationsPage) {

		return new PageDTO<>(
			"_embedded", transformationDTO, transformationsPage.getNumber(),
			transformationsPage.getSize(),
			transformationsPage.getTotalElements(),
			transformationsPage.getTotalPages());
	}

	@Autowired
	private AssetDog _assetDog;

}