import React from 'react';
import PropTypes from 'prop-types';

import 'core-js/fn/array/find';
import 'core-js/fn/array/flat-map';
import 'core-js/fn/object/entries';

import axios from 'axios';

import Button from './Button';
import Modal from './Modal';

class Downloads extends React.Component {
	state = {
		agreementContent: '',
		eulaAccepted: false,
		metadata: '',
		showEULA: false,
		showModal: false
	}

	static propTypes = {
		children: PropTypes.array.isRequired,
		journalArticleId: PropTypes.number.isRequired,
		metadata: PropTypes.arrayOf(
			PropTypes.shape(
				{
					details: PropTypes.object,
					id: PropTypes.string,
					name: PropTypes.string,
					url: PropTypes.string
				}
			)
		).isRequired,
		requiredAgreement: PropTypes.object.isRequired,
		showDropdown: PropTypes.bool.isRequired
	};

	componentDidMount() {
		const {metadata, requiredAgreement} = this.props;

		if (Object.keys(requiredAgreement).length) {
			axios.all(
				[
					axios.get(requiredAgreement.agreementContentURL),
					axios.get(requiredAgreement.verifyAgreementURL)
				]
			)
				.then(
					(response) => {
						const [content, verification] = response;

						this.setState(
							{
								agreementContent: content.data,
								showEULA: !verification.data.verified
							}
						);
					}
				)
				.catch(
					(err) => {
						if (process.env.NODE_ENV === 'development') {
							console.log(err);
						}
					}
				);
		}

		this.setState(
			{
				metadata: this.getMetadata(metadata[0].id)
			}
		);
	}

	getMetadata = (id) => {
		const metadata = this.props.metadata;

		return metadata.find(data => data.id === id);
	};

	handleAcceptEULA = event => {
		this.setState(
			{
				eulaAccepted: event.target.checked
			}
		);
	};

	handleDownload = () => {
		const {requiredAgreement} = this.props;
		const {metadata} = this.state;

		axios.post(requiredAgreement.acceptAgreementURL)
			.then(
				() => {
					this.setState(
						{
							showEULA: false
						},
						() => {
							window.location = metadata.url;
						}
					);
				}
			)
			.catch(
				(err) => {
					if (process.env.NODE_ENV === 'development') {
						console.log(err);
					}
				}
			);
	};

	handleCloseModal = () => {
		this.setState(
			{
				eulaAccepted: false,
				showModal: false
			}
		);
	};

	handleDisplayModal = () =>
		this.setState(
			{
				showModal: true
			}
		);

	handleSelectChange = event => {
		const eventTarget = event.target;

		const selectedIndex = eventTarget.selectedIndex;

		this.setState(
			{
				metadata: this.getMetadata(eventTarget[selectedIndex].id)
			}
		);
	};

	render() {
		const {children, journalArticleId, showDropdown} = this.props;
		const {
			agreementContent,
			eulaAccepted,
			metadata,
			showEULA,
			showModal
		} = this.state;

		const {details, name, url} = metadata;

		const eulaHeader = Liferay.Language.get('download') + ' ' + name;

		const eulaFooter = (
			<div className="eula-footer">
				<Button
					display="outline"
					onClick={this.handleCloseModal}
					type="button"
				>
					{Liferay.Language.get('cancel')}
				</Button>

				<Button
					disabled={!eulaAccepted}
					display="primary"
					onClick={this.handleDownload}
					type="button"
				>
					{Liferay.Language.get('download')}
				</Button>
			</div>
		);

		return (
			<React.Fragment>
				<div className="dropdown-row">
					{showDropdown && (
						<select className="download-dropdown form-control" onChange={this.handleSelectChange}>
							{children.map(
								(child, childIndex) => (
									<optgroup key={childIndex} label={child.downloadGroupName}>
										{child.downloads.map(
											(download, downloadIndex) => (
												<option key={downloadIndex} id={`${journalArticleId}-${childIndex}-${downloadIndex}`} value={download.downloadName}>
													{download.downloadName}
												</option>
											)
										)}
									</optgroup>
								)
							)}
						</select>
					)}

					{!showEULA && url && (
						<Button
							display="primary"
							href={url}
							size="sm"
							type="button"
						>
							{Liferay.Language.get('download')}
						</Button>
					)}

					{showEULA && url && (
						<React.Fragment>
							<Button
								display="primary"
								onClick={this.handleDisplayModal}
								size="sm"
								type="button"
							>
								{Liferay.Language.get('download')}
							</Button>

							<Modal
								footer={eulaFooter}
								header={eulaHeader}
								onClose={this.handleCloseModal}
								show={showModal}
							>
								<h5 className="secondary-text-color section-subtitle">
									{Liferay.Language.get('before-downloading-you-must-agree-to-the-following-terms-and-conditions')}
								</h5>

								<div className="agreement-content">
									<h3>
										{Liferay.Language.get('terms-and-conditions')}
									</h3>

									<div dangerouslySetInnerHTML={{__html: agreementContent}} />
								</div>

								<div className="eula-agree-terms">
									<label className="eula-label">
										<input className="eula-checkbox" name="eulaCheckbox" onChange={this.handleAcceptEULA} type="checkbox" />

										<span>{Liferay.Language.get('i-have-read-and-agree-with-the-above-terms-and-conditions')}</span>
									</label>
								</div>
							</Modal>
						</React.Fragment>
					)}
				</div>

				{details && (
					<div class="download-details small">
						{Object.entries(details).map(
							([key, value], index) => (
								key && (
									<span key={index} className="details">
										<span className="detail-label">{key}</span>:{' '}
										<span className="detail-value">{value}</span>
									</span>
								)
							)
						)}
					</div>
				)}
			</React.Fragment>
		);
	}
}

const FileDownloads = props => {
	const {downloadGroups, journalArticleId, requiredAgreement} = props;

	let showDropdown = true;

	if (downloadGroups.length === 1 && downloadGroups.every(group => group.downloads.length === 1)) {
		showDropdown = false;
	}

	const metadata = downloadGroups.flatMap(
		(group, groupIndex) => (
			group.downloads.map(
				(download, downloadIndex) => (
					{
						details: download.downloadDetails,
						id: `${journalArticleId}-${groupIndex}-${downloadIndex}`,
						name: download.downloadName,
						url: download.downloadURL
					}
				)
			)
		)
	);

	return (
		<Downloads
			metadata={metadata}
			journalArticleId={journalArticleId}
			requiredAgreement={requiredAgreement}
			showDropdown={showDropdown}
		>
			{downloadGroups}
		</Downloads>
	);
};

FileDownloads.propTypes = {
	downloadGroups: PropTypes.arrayOf(
		PropTypes.shape(
			{
				downloadGroupName: PropTypes.string,
				downloads: PropTypes.arrayOf(
					PropTypes.shape(
						{
							downloadDetails: PropTypes.object,
							downloadName: PropTypes.string,
							downloadURL: PropTypes.string
						}
					)
				)
			}
		)
	).isRequired,
	journalArticleId: PropTypes.number.isRequired,
	requiredAgreement: PropTypes.shape(
		{
			acceptAgreementURL: PropTypes.string,
			agreementContentURL: PropTypes.string,
			verifyAgreementURL: PropTypes.string
		}
	).isRequired
};

export default FileDownloads;