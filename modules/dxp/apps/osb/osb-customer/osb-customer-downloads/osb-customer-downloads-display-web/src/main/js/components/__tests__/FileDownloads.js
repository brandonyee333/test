import React from 'react';
import TestRenderer from 'react-test-renderer';

import FileDownloads from '../FileDownloads';

describe('FileDownloads', () => {
	const url = '/';

	const multipleDownloadGroups = [
		{
			'downloadGroupName': 'Download Group Name 1',
			'downloads': [
				{
					'downloadDetails': {
						'label': 'value'
					},
					'downloadName': 'Download Name 1',
					'downloadURL': url
				}
			]
		},
		{
			'downloadGroupName': 'Download Group Name 2',
			'downloads': [
				{
					'downloadDetails': {
						'label': 'value'
					},
					'downloadName': 'Download Name 2',
					'downloadURL': url
				}
			]
		}
	]

	const requiredAgreement = {
		'acceptAgreementURL': url,
		'agreementContentURL': url,
		'verifyAgreementURL': url
	};

	const singleDownloadGroups = [
		{
			'downloadGroupName': 'Download Group Name',
			'downloads': [
				{
					'downloadDetails': {
						'label': 'value'
					},
					'downloadName': 'Download Name',
					'downloadURL': url
				}
			]
		}
	];

	it('renders single download correctly', () => {
		const tree = TestRenderer.create(
			<FileDownloads
				downloadGroups={singleDownloadGroups}
				journalArticleId={123}
				requiredAgreement={requiredAgreement}
			/>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});

	it('renders multiple downloads correctly', () => {
		const tree = TestRenderer.create(
			<FileDownloads
				downloadGroups={multipleDownloadGroups}
				journalArticleId={123}
				requiredAgreement={requiredAgreement}
			/>
		).toJSON();

		expect(tree).toMatchSnapshot();
	});
});