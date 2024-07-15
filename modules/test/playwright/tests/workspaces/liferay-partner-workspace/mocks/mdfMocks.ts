/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import moment from 'moment';

import {
	MDFRequestActivityBudgetExpense,
	MDFRequestActivityTactics,
	MDFRequestLiferayBusinessSalesGoals,
	MDFRequestTargetAudienceRoles,
	MDFRequestTargetMarkets,
	MDFRequestTypeOfActivity,
} from '../utils/enums';

export const mdfRequestCreate = {
	activities: [
		{
			activityName: 'Test Activity',
			claimPercent: 0.5,
			endDate: moment().add(2, 'days').format('YYYY-MM-DD'),
			expenses: [
				{
					type: MDFRequestActivityBudgetExpense.BROADCAST_ADVERTISING,
					value: 500,
				},
			],
			leadGenerated: false,
			marketingActivity: 'Marketing Description',
			startDate: moment().add(1, 'days').format('YYYY-MM-DD'),
			tactic: MDFRequestActivityTactics.OTHER,
			typeOfActivity:
				MDFRequestTypeOfActivity.MISCELLANEOUS_MARKETING,
		},
	],
	goals: {
		companyName: 'Deathray, Inc.*',
		liferayBusinessSalesGoals: [
			MDFRequestLiferayBusinessSalesGoals.LEAD_GENERATION,
		],
		overallCampaignDescription: 'Campaign Description',
		overallCampaignName: 'Campaign Name',
		targetAudienceRoles: [
			MDFRequestTargetAudienceRoles.C_LEVEL_EXECUTIVE_VP,
			MDFRequestTargetAudienceRoles.ADMINISTRATOR,
		],
		targetMarkets: [
			MDFRequestTargetMarkets.AEROSPACE_DEFENSE,
			MDFRequestTargetMarkets.AGRICULTURE,
		],
	},
};

export const mdfRequestGet = {
	claimPercent:0.5,
	companyName:"Deathray, Inc.*",
	convertedTotalCostOfExpense:50000,
	convertedTotalMDFRequestAmount:25000,
	liferayBusinessSalesGoals:"Lead generation",
	liferayBusinessSalesGoalsOther:"",
	maxDateActivity:"2024-07-12",
	minDateActivity:"2024-07-11",
	overallCampaignDescription:"Campaign Description",
	overallCampaignName:"Test Campaign",
	submitDate:"2024-07-10T18:11:39.346Z",
	targetAudienceRoles:"C-Level/Executive/VP; Independent Contractor",
	targetMarkets:"Aerospace & Defense; Agriculture",
	totalCostOfExpense:0,
	totalMDFRequestAmount:25000
}
