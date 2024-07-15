/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';
import moment from 'moment';

import {partnerHelperTest} from '../../../fixtures/partnerHelperTest';
import {partnerPagesTest} from '../../../fixtures/partnerPagesTest';
import {partnerSiteTest} from '../../../fixtures/partnerSiteTest';
import {mdfRequestCreate} from '../../../mocks/mdfMocks';

const test = mergeTests(partnerHelperTest, partnerSiteTest, partnerPagesTest);

test.describe('MDF Request Form', () => {
	const accountName = 'Deathray, Inc.*';
	const accountRole = '[Account] Partner Manager (PM)';
	let accountUser;

	test.beforeEach(async ({partnerHelper, mdfRequestListPage}) => {
		const { account } = await partnerHelper.createAccountUser({accountName, accountType: 'business'});
		await partnerHelper.assignUserToAccountRole({accountId: account.id, accountRole});

		accountUser = account;

		await mdfRequestListPage.goto();
	});

	test.afterEach(async ({partnerHelper}) => {
		await partnerHelper.apiHelpers.headlessAdminUser.deleteAccount(accountUser.id);
	})

	test('Should Create a New MDF Request', async ({
		page,
		mdfRequestFormPage,
	}) => {
		const activityName = page.getByText('Test Activity').first();
		const campaignDescription = await page.getByRole('cell', {
			name: 'Campaign Description',
		});
		const campaignName = await page.getByRole('cell', {
			name: 'Campaign Name',
		});
		const companyName = await page.getByRole('cell', {
			name: 'Deathray, Inc.*',
		});
		const endDate = await page.getByRole('cell', {
			name: moment().add(2, 'days').format('l'),
		});
		const expenses = await page.getByRole('cell', {name: '$500.00'});
		const expensePercentage = page.getByText('$250.00').first();
		const leadGenerated = page.getByRole('cell', {name: 'No'});
		const liferayBusinessSalesGoals = await page.getByRole('cell', {
			name: 'Lead generation',
		});
		const marketingActivity = await page.getByRole('cell', {
			name: 'Marketing Description',
		});
		const mdfRequestData = mdfRequestCreate;
		const startDate = await page.getByRole('cell', {
			name: moment().add(1, 'days').format('l'),
		});
		const tactic = await page.getByRole('cell', {name: 'Other'});
		const targetAudienceRoles = await page.getByRole('cell', {
			name: 'C-Level/Executive/VP; Administrator',
		});
		const targetMarkets = await page.getByRole('cell', {
			name: 'Aerospace & Defense; Agriculture',
		});
		const typeOfActivity = await page.getByRole('cell', {
			name: 'Miscellaneous Marketing',
		});

		await mdfRequestFormPage.createNewRequest(mdfRequestData);

		await expect(companyName).toBeVisible();
		await expect(campaignName).toBeVisible();
		await expect(campaignDescription).toBeVisible();
		await expect(liferayBusinessSalesGoals).toBeVisible();
		await expect(targetMarkets).toBeVisible();
		await expect(targetAudienceRoles).toBeVisible();
		await expect(activityName).toBeVisible();
		await expect(expensePercentage).toBeVisible();

		await page.getByRole('tab', {name: 'MDF Requested'}).click();

		await expect(activityName).toBeVisible();
		await expect(typeOfActivity).toBeVisible();
		await expect(tactic).toBeVisible();
		await expect(marketingActivity).toBeVisible();
		await expect(startDate).toBeVisible();
		await expect(endDate).toBeVisible();
		await expect(expenses).toBeVisible();
		await expect(leadGenerated).toBeVisible();

		await page.getByRole('tab', {name: 'MDF Requested'}).click();

		await expect(expensePercentage).toBeVisible();

		await mdfRequestFormPage.submitButton.click();

		await expect(mdfRequestFormPage.successMessage).toBeVisible();
	});
});
