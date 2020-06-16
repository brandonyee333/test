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
 * The extended model interface for the GitHubContributor service. Represents a row in the &quot;OSBCommunity_GitHubContributor&quot; database table, with each column mapped to a property of this class.
 *
 * @author Haote Chou
 * @see GitHubContributorModel
 * @see com.liferay.osb.community.github.model.impl.GitHubContributorImpl
 * @see com.liferay.osb.community.github.model.impl.GitHubContributorModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.community.github.model.impl.GitHubContributorImpl")
@ProviderType
public interface GitHubContributor extends GitHubContributorModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.community.github.model.impl.GitHubContributorImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<GitHubContributor, Long> GIT_HUB_CONTRIBUTOR_ID_ACCESSOR =
		new Accessor<GitHubContributor, Long>() {
			@Override
			public Long get(GitHubContributor gitHubContributor) {
				return gitHubContributor.getGitHubContributorId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<GitHubContributor> getTypeClass() {
				return GitHubContributor.class;
			}
		};
}