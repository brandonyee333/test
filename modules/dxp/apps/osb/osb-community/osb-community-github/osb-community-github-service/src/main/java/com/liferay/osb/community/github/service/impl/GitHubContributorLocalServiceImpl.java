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

package com.liferay.osb.community.github.service.impl;

import com.liferay.osb.community.github.exception.GitHubAPIException;
import com.liferay.osb.community.github.exception.GitHubContributorCountException;
import com.liferay.osb.community.github.internal.util.GitHubCommunicatorUtil;
import com.liferay.osb.community.github.internal.util.GitHubServiceConfigurationUtil;
import com.liferay.osb.community.github.model.GitHubContributor;
import com.liferay.osb.community.github.model.GitHubRepository;
import com.liferay.osb.community.github.service.base.GitHubContributorLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Haote Chou
 */
public class GitHubContributorLocalServiceImpl
	extends GitHubContributorLocalServiceBaseImpl {

	public List<GitHubContributor> getTopGitHubContributors(
			long userId, String owner, String name, int count)
		throws PortalException {

		validate(count);

		GitHubRepository gitHubRepository =
			gitHubRepositoryPersistence.findByO_N(owner, name);

		List<GitHubContributor> gitHubContributors =
			gitHubContributorPersistence.findByGitHubRepositoryId(
				gitHubRepository.getGitHubRepositoryId(), 0, count);

		if (!gitHubContributors.isEmpty()) {
			return gitHubContributors;
		}

		if (gitHubContributors.isEmpty()) {
			gitHubContributors = addGitHubContributors(
				userId, gitHubRepository.getGitHubRepositoryId(), owner, name);
		}

		return ListUtil.subList(gitHubContributors, 0, count);
	}

	protected List<GitHubContributor> addGitHubContributors(
			long userId, long gitHubRepositoryId, String owner, String name)
		throws PortalException {

		List<GitHubContributor> gitHubContributors = new ArrayList<>();

		List<GitHubContributor> gitHubContributorsHolders = null;

		try {
			gitHubContributorsHolders =
				GitHubCommunicatorUtil.getTopContributors(
					owner, name,
					GitHubServiceConfigurationUtil.
						getGitHubContributorMaxCount(),
					GitHubServiceConfigurationUtil.getAPIKey());
		}
		catch (Exception e) {
			throw new GitHubAPIException(e);
		}

		if (userId == 0) {
			userId = userLocalService.getDefaultUserId(
				PortalUtil.getDefaultCompanyId());
		}

		User user = userLocalService.getUser(userId);

		Date now = new Date();

		for (GitHubContributor gitHubContributorsHolder :
				gitHubContributorsHolders) {

			long gitHubContributorId = counterLocalService.increment();

			GitHubContributor gitHubContributor =
				gitHubContributorPersistence.create(gitHubContributorId);

			gitHubContributor.setCompanyId(user.getCompanyId());
			gitHubContributor.setUserId(userId);
			gitHubContributor.setUserName(user.getFullName());
			gitHubContributor.setCreateDate(now);
			gitHubContributor.setModifiedDate(now);
			gitHubContributor.setGitHubRepositoryId(gitHubRepositoryId);
			gitHubContributor.setName(gitHubContributorsHolder.getName());
			gitHubContributor.setAvatarURL(
				gitHubContributorsHolder.getAvatarURL());
			gitHubContributor.setContributions(
				gitHubContributorsHolder.getContributions());
			gitHubContributor.setProfileURL(
				gitHubContributorsHolder.getProfileURL());

			gitHubContributor = gitHubContributorPersistence.update(
				gitHubContributor);

			gitHubContributors.add(gitHubContributor);
		}

		return gitHubContributors;
	}

	protected void validate(int count) throws PortalException {
		if (count <= 0) {
			throw new GitHubContributorCountException(
				"GitHub contributor count must greater than 0");
		}

		if (count >
				GitHubServiceConfigurationUtil.getGitHubContributorMaxCount()) {

			throw new GitHubContributorCountException(
				"GitHub contributor count is greater than contributor max " +
					"count");
		}
	}

}