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

package com.liferay.osb.customer.release.tool.service.impl;

import com.liferay.osb.customer.release.tool.exception.DuplicateArtifactVersionException;
import com.liferay.osb.customer.release.tool.model.ArtifactVersion;
import com.liferay.osb.customer.release.tool.service.base.ArtifactVersionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class ArtifactVersionLocalServiceImpl
	extends ArtifactVersionLocalServiceBaseImpl {

	public ArtifactVersion addArtifactVersion(
			long releaseAssetCategoryId, String group, String name,
			String version)
		throws PortalException {

		ArtifactVersion artifactVersion =
			artifactVersionPersistence.fetchByRACI_G_N(
				releaseAssetCategoryId, group, name);

		if (artifactVersion != null) {
			if (!version.equals(artifactVersion.getVersion())) {
				throw new DuplicateArtifactVersionException();
			}

			return artifactVersion;
		}

		long artifactVersionId = counterLocalService.increment();

		artifactVersion = artifactVersionPersistence.create(artifactVersionId);

		artifactVersion.setReleaseAssetCategoryId(releaseAssetCategoryId);
		artifactVersion.setGroup(group);
		artifactVersion.setName(name);
		artifactVersion.setVersion(version);

		return artifactVersionPersistence.update(artifactVersion);
	}

}