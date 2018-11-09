import React from 'react';
import PropTypes from 'prop-types';

import "core-js/fn/array/flat-map";

import Button from './Button';

class Downloads extends React.Component {
	state = {
		metadata: ''
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
		showDropdown: PropTypes.bool.isRequired
	};

	componentDidMount() {
		this.setState(
			{
				metadata: this.setMetadata(this.props.metadata[0].id)
			}
		);
	}

	handleSelectChange = () => {
		this.setState(
			{
				metadata: this.setMetadata(event.target.selectedOptions[0].id)
			}
		)
	}

	setMetadata = (id) => {
		const metadata = this.props.metadata;

		return metadata.find(data => data.id === id);
	}

	render() {
		const {children, journalArticleId, showDropdown} = this.props;
		const {metadata} = this.state;

		const {details, url} = metadata;

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
												<option id={`${journalArticleId}-${childIndex}-${downloadIndex}`} key={downloadIndex} value={download.downloadName}>
													{download.downloadName}
												</option>
											)
										)}
									</optgroup>
								)
							)}
						</select>
					)}

					{url && (
						<Button
							display="primary"
							href={url}
							size="sm"
						>
							{Liferay.Language.get('download')}
						</Button>
					)}
				</div>

				{details && (
					<div class="download-details small">
						{Object.entries(details).map(
							detail => (
								<React.Fragment>
									<span className="detail-label">{detail[0]}</span>:{' '}
									<span className="detail-value">{detail[1]}</span>
								</React.Fragment>
							)
						)}
					</div>
				)}
			</React.Fragment>
		);
	}
}

const FileDownloads = props => {
	const {downloadGroups, journalArticleId} = props;

	let showDropdown = true;

	if (
		downloadGroups.length === 1 &&
		downloadGroups.every(group => group.downloads.length === 1)
	) {
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
	journalArticleId: PropTypes.number.isRequired
};

export default FileDownloads;