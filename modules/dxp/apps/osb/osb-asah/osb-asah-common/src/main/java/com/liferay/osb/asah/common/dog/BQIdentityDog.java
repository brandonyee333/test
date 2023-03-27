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

import com.liferay.osb.asah.common.entity.BQIdentity;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class BQIdentityDog {

	public List<BQIdentity> getIdentities(List<String> identityIds) {
		return _bqIdentityRepository.findByIdIn(identityIds);
	}

	public List<String> getIdentityIds(String individualId) {
		return _bqIdentityRepository.getIdentityIds(individualId);
	}

	public String getIndividualId(String id) {
		Optional<String> individualIdOptional =
			_bqIdentityRepository.getIndividualIdOptional(id);

		return individualIdOptional.orElse(null);
	}

	@Autowired
	private BQIdentityRepository _bqIdentityRepository;

}