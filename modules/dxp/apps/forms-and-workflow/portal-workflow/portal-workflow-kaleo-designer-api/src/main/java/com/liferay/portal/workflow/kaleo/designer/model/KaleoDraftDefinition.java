/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.designer.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the KaleoDraftDefinition service. Represents a row in the &quot;KaleoDraftDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * @author Eduardo Lundgren
 * @see KaleoDraftDefinitionModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionImpl"
)
@ProviderType
public interface KaleoDraftDefinition
	extends KaleoDraftDefinitionModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.portal.workflow.kaleo.designer.model.impl.KaleoDraftDefinitionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<KaleoDraftDefinition, Long>
		KALEO_DRAFT_DEFINITION_ID_ACCESSOR =
			new Accessor<KaleoDraftDefinition, Long>() {

				@Override
				public Long get(KaleoDraftDefinition kaleoDraftDefinition) {
					return kaleoDraftDefinition.getKaleoDraftDefinitionId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<KaleoDraftDefinition> getTypeClass() {
					return KaleoDraftDefinition.class;
				}

			};

}