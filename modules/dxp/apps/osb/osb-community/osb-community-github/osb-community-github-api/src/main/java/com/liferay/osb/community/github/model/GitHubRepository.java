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

package com.liferay.osb.community.github.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the GitHubRepository service. Represents a row in the &quot;OSBCommunity_GitHubRepository&quot; database table, with each column mapped to a property of this class.
 *
 * @author Haote Chou
 * @see GitHubRepositoryModel
 * @see com.liferay.osb.community.github.model.impl.GitHubRepositoryImpl
 * @see com.liferay.osb.community.github.model.impl.GitHubRepositoryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.community.github.model.impl.GitHubRepositoryImpl")
@ProviderType
public interface GitHubRepository extends GitHubRepositoryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.community.github.model.impl.GitHubRepositoryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<GitHubRepository, Long> GIT_HUB_REPOSITORY_ID_ACCESSOR =
		new Accessor<GitHubRepository, Long>() {
			@Override
			public Long get(GitHubRepository gitHubRepository) {
				return gitHubRepository.getGitHubRepositoryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<GitHubRepository> getTypeClass() {
				return GitHubRepository.class;
			}
		};
}