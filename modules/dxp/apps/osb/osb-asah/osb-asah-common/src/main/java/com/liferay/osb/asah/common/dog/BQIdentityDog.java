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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class BQIdentityDog {

	public List<BQIdentity> getBQIdentities(List<String> bqIdentityIds) {
		return _bqIdentityRepository.findByIdIn(bqIdentityIds);
	}

	public List<String> getBQIdentityIds(String bqIndividualId) {
		return _bqIdentityRepository.getBQIdentityIds(bqIndividualId);
	}

	public String getBQIndividualId(String bqIdentityId) {
		return _bqIdentityRepository.getBQIndividualId(bqIdentityId);
	}

	@Autowired
	private BQIdentityRepository _bqIdentityRepository;

}