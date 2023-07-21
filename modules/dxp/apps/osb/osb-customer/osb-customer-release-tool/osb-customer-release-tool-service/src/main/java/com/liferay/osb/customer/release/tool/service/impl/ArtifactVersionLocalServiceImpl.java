/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.service.impl;

import com.liferay.osb.customer.release.tool.model.ArtifactVersion;
import com.liferay.osb.customer.release.tool.model.ArtifactVersionRange;
import com.liferay.osb.customer.release.tool.service.base.ArtifactVersionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Amos Fong
 */
public class ArtifactVersionLocalServiceImpl
	extends ArtifactVersionLocalServiceBaseImpl {

	public ArtifactVersion addArtifactVersion(
		long releaseAssetCategoryId, int owner, int repository, String group,
		String name, String version, String packaging) {

		ArtifactVersion artifactVersion =
			artifactVersionPersistence.fetchByRACI_G_N(
				releaseAssetCategoryId, group, name);

		if (artifactVersion != null) {
			return artifactVersion;
		}

		long artifactVersionId = counterLocalService.increment();

		artifactVersion = artifactVersionPersistence.create(artifactVersionId);

		artifactVersion.setReleaseAssetCategoryId(releaseAssetCategoryId);
		artifactVersion.setOwner(owner);
		artifactVersion.setRepository(repository);
		artifactVersion.setGroup(group);
		artifactVersion.setName(name);
		artifactVersion.setVersion(version);
		artifactVersion.setPackaging(packaging);

		return artifactVersionPersistence.update(artifactVersion);
	}

	public List<ArtifactVersionRange> getArtifactVersionRanges(
			long fromReleaseAssetCategoryId, long toReleaseAssetCategoryId,
			int[] owners, String keywords, boolean changesOnly)
		throws PortalException {

		return artifactVersionFinder.findArtifactVersionRangesByRACI_RACI_O_K(
			fromReleaseAssetCategoryId, toReleaseAssetCategoryId, owners,
			keywords, changesOnly);
	}

}