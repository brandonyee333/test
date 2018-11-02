import React from 'react';
import PropTypes from 'prop-types';

import Button from './Button';

export default class JournalArticleDownloads extends React.Component {
	static propTypes = {
		downloadGroups: PropTypes.array.isRequired
	};

	state = {
		downloadName: '',
		downloadURL: '',
		downloadDetails: {
			fileSize: '',
			md5: ''
		}
	};

	componentDidMount() {
		const {downloadGroups} = this.props;

		const firstDownload = downloadGroups.find(downloadGroup => downloadGroup).downloads.find(download => download);

		this.setState(
			{
				downloadName: firstDownload.downloadName,
				downloadURL: firstDownload.downloadURL,
				downloadDetails: {
					fileSize: firstDownload.downloadDetails["File Size"],
					md5: firstDownload.downloadDetails["MD5"]
				}
			}
		);
	};

	handleSelectChange = event => {
		const {downloadGroups} = this.props;

		const {options} = event.target;

		const selectedDownloadGroup = downloadGroups.find(downloadGroup => downloadGroup.downloadGroupName == options[options.selectedIndex].parentNode.label);
		const selectedDownload = selectedDownloadGroup.downloads.find(download => download.downloadName == event.target.value);

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
		const {downloadGroups} = this.props;
		const {downloadName, downloadURL, downloadDetails} = this.state;

		return (
			<React.Fragment>
				<div className="dropdown-row">
					{(downloadGroups.length > 1 || downloadGroups.find(downloads => downloads).downloads.length > 1) &&
						<select className="download-dropdown form-control" onChange={this.handleSelectChange}>
							{downloadGroups.map(
								(downloadGroup) => (
									<React.Fragment>
										<optgroup label={downloadGroup.downloadGroupName}>
											{downloadGroup.downloads.map(
												(download) => (
													<option label={download.downloadName} value={download.downloadName} />
												)
											)}
										</optgroup>
									</React.Fragment>
								)
							)}
						</select>
					}


					<Button
						display="primary"
						size="sm"
						type="button"
					>
						<a className="download-url" href={downloadURL}>
							{Liferay.Language.get('download')}
						</a>
					</Button>
				</div>

				<span className="detail-label">{Liferay.Language.get('file-size')}:</span> <span className="detail-value">{downloadDetails.fileSize}</span>

				<span className="detail-label">{Liferay.Language.get('md5')}:</span> <span className="detail-value">{downloadDetails.md5}</span>
			</React.Fragment>
		);
	}
}