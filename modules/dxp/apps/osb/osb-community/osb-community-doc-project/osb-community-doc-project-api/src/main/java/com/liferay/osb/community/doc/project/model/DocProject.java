/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.community.doc.project.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the DocProject service. Represents a row in the &quot;OSBCommunity_DocProject&quot; database table, with each column mapped to a property of this class.
 *
 * @author Ryan Park
 * @see DocProjectModel
 * @see com.liferay.osb.community.doc.project.model.impl.DocProjectImpl
 * @see com.liferay.osb.community.doc.project.model.impl.DocProjectModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.community.doc.project.model.impl.DocProjectImpl")
@ProviderType
public interface DocProject extends DocProjectModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.community.doc.project.model.impl.DocProjectImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DocProject, Long> DOC_PROJECT_ID_ACCESSOR = new Accessor<DocProject, Long>() {
			@Override
			public Long get(DocProject docProject) {
				return docProject.getDocProjectId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DocProject> getTypeClass() {
				return DocProject.class;
			}
		};

	public DocProjectTypeSettings getDocProjectTypeSettings();

	public java.io.InputStream getIconInputStream()
		throws com.liferay.portal.kernel.exception.PortalException;
}