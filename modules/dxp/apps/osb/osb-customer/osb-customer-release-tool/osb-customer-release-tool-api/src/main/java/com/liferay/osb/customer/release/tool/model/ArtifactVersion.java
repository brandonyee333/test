/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ArtifactVersion service. Represents a row in the &quot;OSBCustomer_ArtifactVersion&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ArtifactVersionModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.customer.release.tool.model.impl.ArtifactVersionImpl"
)
@ProviderType
public interface ArtifactVersion extends ArtifactVersionModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.customer.release.tool.model.impl.ArtifactVersionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ArtifactVersion, Long>
		ARTIFACT_VERSION_ID_ACCESSOR = new Accessor<ArtifactVersion, Long>() {

			@Override
			public Long get(ArtifactVersion artifactVersion) {
				return artifactVersion.getArtifactVersionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ArtifactVersion> getTypeClass() {
				return ArtifactVersion.class;
			}

		};

}