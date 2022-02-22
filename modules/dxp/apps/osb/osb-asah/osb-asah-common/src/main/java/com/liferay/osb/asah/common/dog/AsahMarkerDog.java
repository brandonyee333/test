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

	public AsahMarker addAsahMarker(AsahMarker asahMarker) {
		if (!asahMarker.isNew()) {
			throw new IllegalArgumentException("Unable to add Asah Marker");
		}

		return _asahMarkerRepository.save(asahMarker);
	}

	public void deleteAsahMarker(String asahMarkerId) {
		_asahMarkerRepository.deleteById(asahMarkerId);
	}

	public void deleteAsahMarkers(List<String> asahMarkerIds) {
		for (String asahMarkerId : asahMarkerIds) {
			_asahMarkerRepository.deleteById(asahMarkerId);
		}
	}

	public AsahMarker fetchAsahMarker(String asahMarkerId) {
		Optional<AsahMarker> asahMarkerOptional =
			_asahMarkerRepository.findById(asahMarkerId);

		AsahMarker asahMarker = asahMarkerOptional.orElse(null);

		if (asahMarker != null) {
			asahMarker.setIsNew(Boolean.FALSE);
		}

		return asahMarker;
	}

	public AsahMarker getAsahMarker(String asahMarkerId) {
		AsahMarker asahMarker = fetchAsahMarker(asahMarkerId);

		if (asahMarker == null) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no Asah Marker with ID " + asahMarkerId);
		}

		return asahMarker;
	}

	public AsahMarker updateAsahMarker(AsahMarker asahMarker) {
		if (asahMarker.isNew()) {
			throw new IllegalArgumentException("Unable to update Asah Marker");
		}

		return _asahMarkerRepository.save(asahMarker);
	}

	@Autowired
	private AsahMarkerRepository _asahMarkerRepository;

}