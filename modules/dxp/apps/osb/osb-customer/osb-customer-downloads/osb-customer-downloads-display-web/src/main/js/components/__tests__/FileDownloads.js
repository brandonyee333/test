import React from 'react';
import {
	fireEvent,
	getByValue,
	queryByText,
	render
} from 'react-testing-library';

import FileDownloads from '../FileDownloads';

describe('FileDownloads', () => {
	const url = '/';

	const multipleDownloadGroups = [
		{
			downloadGroupName: 'Download Group Name 1',
			downloads: [
				{
					downloadDetails: {
						fileSize: '100kb',
						md5: 'ABC'
					},
					downloadName: 'Download Name 1',
					downloadURL: 'group1/link1'
				},
				{
					downloadDetails: {
						fileSize: '200kb'
					},
					downloadName: 'Download Name 2',
					downloadURL: 'group1/link2'
				}
			]
		},
		{
			downloadGroupName: 'Download Group Name 2',
			downloads: [
				{
					downloadDetails: {
						fileSize: '300kb'
					},
					downloadName: 'Download Name 3',
					downloadURL: 'group2/link1'
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
						fileSize: '100kb'
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

	it('only renders a download link if the record contains one downloadable asset', () => {
		const {container} = render(
			<FileDownloads
				downloadGroups={singleDownloadGroups}
				journalArticleId={123}
				requiredAgreement={requiredAgreement}
			/>
		);

		const anchor = queryByText(container, 'download');
		const dropdown = queryByText(container, 'Download Name');

		expect(anchor).toBeDefined();
		expect(dropdown).toBeNull();
	});

	it('renders a dropdown dropdown and a download link if the record contains more than one downloadable asset', () => {
		const {container} = render(
			<FileDownloads
				downloadGroups={multipleDownloadGroups}
				journalArticleId={123}
				requiredAgreement={requiredAgreement}
			/>
		);

		const anchor = queryByText(container, 'download');
		const dropdown = getByValue(container, 'Download Name 1');

		expect(anchor).toBeDefined();
		expect(dropdown).toBeDefined();
	});

	it('renders download meta data', () => {
		const {container} = render(
			<FileDownloads
				downloadGroups={singleDownloadGroups}
				journalArticleId={123}
				requiredAgreement={requiredAgreement}
			/>
		);

		const metaDataLabel1 = queryByText(container, 'fileSize');
		const metaDataLabel2 = queryByText(container, 'md5');
		const metaDataValue1 = queryByText(container, '100kb');
		const metaDataValue2 = queryByText(container, 'ABC');

		expect(metaDataLabel1).toBeDefined();
		expect(metaDataLabel2).toBeDefined();
		expect(metaDataValue1).toBeDefined();
		expect(metaDataValue2).toBeDefined();
	});

	it('updates download meta data when different download option is selected', () => {
		const {container} = render(
			<FileDownloads
				downloadGroups={multipleDownloadGroups}
				journalArticleId={123}
				requiredAgreement={requiredAgreement}
			/>
		);

		const downloadSelect = container.querySelector('select');
		const initialMetaData = queryByText(container, '100kb');
		const initialMetaDataValue = initialMetaData.textContent;

		downloadSelect.value = 'Download Name 2';

		fireEvent.change(downloadSelect);

		const newMetaData = queryByText(container, '200kb');

		expect(initialMetaDataValue).not.toMatch(newMetaData.textContent);
	});

	it('changes download link url when a different download option is selected', () => {
		const {container} = render(
			<FileDownloads
				downloadGroups={multipleDownloadGroups}
				journalArticleId={123}
				requiredAgreement={requiredAgreement}
			/>
		);

		const anchor = queryByText(container, 'download');
		const downloadSelect = container.querySelector('select');
		const initialURL = anchor.href;

		downloadSelect.value = 'Download Name 2';

		fireEvent.change(downloadSelect);

		expect(initialURL).not.toMatch(anchor.href);
	});
});