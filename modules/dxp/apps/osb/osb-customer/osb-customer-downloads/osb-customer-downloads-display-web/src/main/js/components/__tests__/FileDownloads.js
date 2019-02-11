import React from 'react';
import {render} from 'react-testing-library';

import FileDownloads from '../FileDownloads';

describe('FileDownloads', () => {
	const url = '/';

	const multipleDownloadGroups = [
		{
			downloadGroupName: 'Download Group Name 1',
			downloads: [
				{
					downloadDetails: {
						label: 'value'
					},
					downloadName: 'Download Name 1',
					downloadURL: url
				}
			]
		},
		{
			downloadGroupName: 'Download Group Name 2',
			downloads: [
				{
					downloadDetails: {
						label: 'value'
					},
					downloadName: 'Download Name 2',
					downloadURL: url
				}
			]
		}
	];

	const requiredAgreement = {
		acceptAgreementURL: url,
		agreementContentURL: url,
		verifyAgreementURL: url
	};

	const singleDownloadGroups = [
		{
			downloadGroupName: 'Download Group Name',
			downloads: [
				{
					downloadDetails: {
						label: 'value'
					},
					downloadName: 'Download Name',
					downloadURL: url
				}
			]
		}
	];

	it('renders single download correctly', () => {
		const {container} = render(
			<FileDownloads
				downloadGroups={singleDownloadGroups}
				journalArticleId={123}
				requiredAgreement={requiredAgreement}
			/>
		);

		expect(container).toMatchSnapshot();
	});

	it('renders multiple downloads correctly', () => {
		const {container} = render(
			<FileDownloads
				downloadGroups={multipleDownloadGroups}
				journalArticleId={123}
				requiredAgreement={requiredAgreement}
			/>
		);

		expect(container).toMatchSnapshot();
	});
});