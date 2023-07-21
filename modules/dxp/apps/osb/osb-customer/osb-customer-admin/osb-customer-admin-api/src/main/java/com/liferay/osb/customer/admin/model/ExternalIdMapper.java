/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ExternalIdMapper service. Represents a row in the &quot;OSB_ExternalIdMapper&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ExternalIdMapperModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.osb.customer.admin.model.impl.ExternalIdMapperImpl"
)
@ProviderType
public interface ExternalIdMapper
	extends ExternalIdMapperModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.osb.customer.admin.model.impl.ExternalIdMapperImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ExternalIdMapper, Long>
		EXTERNAL_ID_MAPPER_ID_ACCESSOR =
			new Accessor<ExternalIdMapper, Long>() {

				@Override
				public Long get(ExternalIdMapper externalIdMapper) {
					return externalIdMapper.getExternalIdMapperId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<ExternalIdMapper> getTypeClass() {
					return ExternalIdMapper.class;
				}

			};

}