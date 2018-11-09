import React from 'react';
import PropTypes from 'prop-types';

import Button from './Button';

export default class JournalArticleDownloads extends React.Component {
	static propTypes = {
		allDownloads: PropTypes.array.isRequired,
		downloadGroups: PropTypes.array.isRequired,
		firstDownload: PropTypes.object.isRequired
	};

	state = {
		downloadName: this.props.firstDownload.downloadName,
		downloadURL: this.props.firstDownload.downloadURL,
		downloadDetails: {
			fileSize: this.props.firstDownload.downloadDetails["File Size"],
			md5: this.props.firstDownload.downloadDetails["MD5"]
		}
	};

	handleSelectChange = event => {
		const {allDownloads} = this.props;

		const selectedDownload = allDownloads.find(download => download.downloadName == event.target.value);

		this.setState(
			{
				downloadName: selectedDownload.downloadName,
				downloadURL: selectedDownload.downloadURL,
				downloadDetails: {
					fileSize: selectedDownload.downloadDetails["File Size"],
					md5: selectedDownload.downloadDetails["MD5"]
				}
			}
		);
	}

	render() {
		const {allDownloads, downloadGroups} = this.props;
		const {downloadURL, downloadDetails} = this.state;

		return (
			<React.Fragment>
				<div className="dropdown-row">
					{allDownloads.length > 1 &&
						<select className="download-dropdown form-control" onChange={this.handleSelectChange}>
							{downloadGroups.map(
								(downloadGroup) => (
									<optgroup label={downloadGroup.downloadGroupName}>
										{downloadGroup.downloads.map(
											(download) => (
												<option label={download.downloadName} value={download.downloadName} />
											)
										)}
									</optgroup>
								)
							)}
						</select>
					}

					<Button
						display="primary"
						href={downloadURL}
						size="sm"
					>
						{Liferay.Language.get('download')}
					</Button>
				</div>

				<div class="download-details small">
					<span className="detail-label"></span>:{' '}
					<span className="detail-value"></span>
				</div>
			</React.Fragment>
		);
	}
}