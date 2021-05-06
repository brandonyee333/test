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

import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.repository.AsahMarkerRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.util.WeDeployServiceThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class AsahMarkerDog {

	public AsahMarker addAsahMarker(
		AsahMarker asahMarker, WeDeployDataService weDeployDataService) {

		if (!asahMarker.isNew()) {
			throw new IllegalArgumentException("Unable to add Asah Marker");
		}

		try {
			WeDeployServiceThreadLocal.setWeDeployDataService(
				weDeployDataService);

			return _asahMarkerRepository.save(asahMarker);
		}
		finally {
			WeDeployServiceThreadLocal.remove();
		}
	}

	public void deleteAsahMarker(
		String asahMarkerId, WeDeployDataService weDeployDataService) {

		deleteAsahMarkers(Arrays.asList(asahMarkerId), weDeployDataService);
	}

	public void deleteAsahMarkers(
		List<String> asahMarkerIds, WeDeployDataService weDeployDataService) {

		try {
			WeDeployServiceThreadLocal.setWeDeployDataService(
				weDeployDataService);

			for (String asahMarkerId : asahMarkerIds) {
				Optional<AsahMarker> asahMarkerOptional =
					_asahMarkerRepository.findById(asahMarkerId);

				asahMarkerOptional.ifPresent(_asahMarkerRepository::delete);
			}
		}
		finally {
			WeDeployServiceThreadLocal.remove();
		}
	}

	public AsahMarker fetchAsahMarker(
		String asahMarkerId, WeDeployDataService weDeployDataService) {

		Optional<AsahMarker> asahMarkerOptional = _findAsahMarker(
			asahMarkerId, weDeployDataService);

		AsahMarker asahMarker = asahMarkerOptional.orElse(null);

		if (asahMarker != null) {
			asahMarker.setIsNew(Boolean.FALSE);
		}

		return asahMarker;
	}

	public AsahMarker getAsahMarker(
		String asahMarkerId, WeDeployDataService weDeployDataService) {

		Optional<AsahMarker> asahMarkerOptional = _findAsahMarker(
			asahMarkerId, weDeployDataService);

		if (!asahMarkerOptional.isPresent()) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no Asah Marker with ID " + asahMarkerId);
		}

		AsahMarker asahMarker = asahMarkerOptional.get();

		asahMarker.setIsNew(Boolean.FALSE);

		return asahMarker;
	}

	public AsahMarker updateAsahMarker(
		AsahMarker asahMarker, WeDeployDataService weDeployDataService) {

		if (asahMarker.isNew()) {
			throw new IllegalArgumentException("Unable to update Asah Marker");
		}

		try {
			WeDeployServiceThreadLocal.setWeDeployDataService(
				weDeployDataService);

			return _asahMarkerRepository.save(asahMarker);
		}
		finally {
			WeDeployServiceThreadLocal.remove();
		}
	}

	private Optional<AsahMarker> _findAsahMarker(
		String asahMarkerId, WeDeployDataService weDeployDataService) {

		try {
			WeDeployServiceThreadLocal.setWeDeployDataService(
				weDeployDataService);

			return _asahMarkerRepository.findById(asahMarkerId);
		}
		finally {
			WeDeployServiceThreadLocal.remove();
		}
	}

	@Autowired
	private AsahMarkerRepository _asahMarkerRepository;

}