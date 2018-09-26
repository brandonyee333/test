import PropTypes from 'prop-types';
import React from 'react';

import axios from 'axios';

import Button from './Button';
import Resumable from '../third-party/resumable.nocsf';
import {
	getFileProgress,
	handleFileError,
	handleFileSuccess
} from '../helpers/resumable-util';

export default class DynamicUploaderForm extends React.Component {
	static propTypes = {
		addTicketAttachmentURL: PropTypes.string.isRequired,
		generateTokenURL: PropTypes.string.isRequired,
		portletNamespace: PropTypes.string.isRequired,
		uploadURL: PropTypes.string.isRequired
	}

	formRef = React.createRef();

	state = {
		comment: '',
		file: {
			fileName: '',
			fileSize: '',
			type: ''
		},
		message: {
			content: '',
			type: ''
		},
		token: '',
		toolbar: {
			paused: false,
			progress: 0,
			visible: false
		}
	}

	componentDidMount() {
		const {portletNamespace, uploadURL} = this.props;

		const resumable = new Resumable(
			{
				chunkSize: 25 * 1024 * 1024,
				headers: {
					'Content-Type': 'multipart/form-data;charset=utf-8'
				},
				maxFiles: 1,
				maxFileSize: 35 * 1024 * 1024 * 1024,
				method: 'octet',
				query: (resumableFile, resumableChunk) => {
					const queryParam = {
						token: this.state.token
					};

					return queryParam;
				},
				simultaneousUploads: 10,
				target: uploadURL,
				testChunks: true,
				throttleProgressCallbacks: 1
			}
		);

		if (!resumable.support) {
			this.setState(
				{
					message: {
						content: Liferay.Language.get('invalid-browser'),
						type: 'error'
					}
				}
			);
		}
		else {
			resumable.assignBrowse(document.getElementById(`${portletNamespace}selectButton`));
			resumable.assignDrop(document.getElementById(`${portletNamespace}uploadArea`));

			resumable.on(
				'fileAdded',
				(file) => {
					this.generateToken(resumable, file)
						.then(
							response => {
								this.setState(
									{
										message: {
											content: Liferay.Language.get('loading'),
											type: 'pending'
										},
										token: response.data.token,
										toolbar: {
											visible: true
										}
									}
								);

								resumable.upload();
							}
						)
						.catch(
							err => {
								this.setState(
									{
										message: {
											content: err.message,
											type: 'error'
										}
									}
								);
							}
						);
				}
			);

			resumable.on(
				'fileError',
				(file, message) => {
					const response = handleFileError(file, message);

					this.setState(
						{
							message: {
								content: response.message,
								type: 'error'
							},
							toolbar: {
								visible: true
							}
						}
					);
				}
			);

			resumable.on(
				'fileProgress',
				(file) => {
					this.setState(
						{
							toolbar: {
								progress: getFileProgress(file),
								visible: true
							}
						}
					);
				}
			);

			resumable.on(
				'fileSuccess',
				(file, message) => {
					const resumableFile = handleFileSuccess(file, message);

					this.setState(
						(prevState) => (
							{
								file: {
									...prevState.file,
									fileName: file.fileName,
									fileSize: file.size,
									type: file.file.type
								},
								fileObj: file,
								message: {
									content: resumableFile.message,
									type: 'success'
								},
								toolbar: {
									visible: false
								}
							}
						)
					);
				}
			);

			this.setState(
				{
					resumable
				}
			);
		}
	}

	generateToken = () => {
		return axios.get(this.props.generateTokenURL);
	}

	handleClear = () => {
		this.state.resumable.cancel();

		this.setState(
			{
				comment: '',
				file: '',
				fileObj: '',
				toolbar: {
					visible: false
				}
			}
		);
	}

	handleClearAccepted = () => {
		this.state.resumable.removeFile(this.state.fileObj);

		this.setState(
			(prevState) => (
				{
					file: {
						...prevState.file,
						fileName: '',
						fileSize: '',
						type: ''
					},
					fileObj: ''
				}
			)
		);
	}

	handlePause = () => {
		this.state.resumable.pause();

		this.setState(
			{
				toolbar: {
					paused: true,
					visible: true
				}
			}
		);
	}

	handlePlay = () => {
		this.state.resumable.upload();

		this.setState(
			{
				toolbar: {
					paused: false,
					visible: true
				}
			}
		);
	}

	handleSubmit = (event) => {
		event.preventDefault();

		if (this.validateForm()) {
			this.setState(
				{
					submitting: true
				}
			);

			this.updateActionUrl();

			this.formRef.current.submit();
		}
		else {
			this.setState(
				{
					message: {
						content: Liferay.Language.get('error-validating-file-attachment-please-reupload-file'),
						type: 'error'
					}
				}
			);
		}
	}

	handleUpdateComment = (event) => {
		this.setState({
			comment: event.currentTarget.value
		});
	}

	updateActionUrl = () => {
		const {addTicketAttachmentURL, portletNamespace} = this.props;

		const formFields = Object.keys(this.state.file);

		let actionUrl = addTicketAttachmentURL;

		formFields.forEach(
			key => {
				actionUrl += `&${portletNamespace}${key}=${this.state.file[key]}`;
			}
		);

		actionUrl += `&${portletNamespace}comment=${this.state.comment}`;

		this.formRef.current.action = actionUrl;
	}

	validateForm = () => {
		const {
			fileName,
			fileSize,
			type
		} = this.state.file;

		return (fileName && fileSize && type);
	}

	render() {
		const {portletNamespace} = this.props;

		return (
			<form
				method="post"
				onSubmit={this.handleSubmit}
				ref={this.formRef}
			>
				<div className="row">
					<div className="col-md-12">
						<div className="form-group" id={`${portletNamespace}uploadContainer`}>
							<label className="control-label">
								{Liferay.Language.get('attachment')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<div
								className="form-control upload-area"
								id={`${portletNamespace}uploadArea`}
							>
								<input
									className="attachment"
									id={`${portletNamespace}selectButton`}
									name={`${portletNamespace}attachment`}
									type="file"
								/>

								<div className="upload-area-label">
									<svg className="lexicon-icon lexicon-icon-paperclip">
										<use xlinkHref="#paperclip" />
									</svg>
									{Liferay.Language.get('add-file-or-drop-file-here')}
								</div>
							</div>

							<div className="toolbar" id={`${portletNamespace}toolbar`}>
								{this.state.file.fileName && (
									<div className="attachment" onClick={this.handleClearAccepted}>
										<svg className="lexicon-icon lexicon-icon-paperclip">
											<use xlinkHref="#paperclip" />
										</svg>

										<span className="attachment-name">{this.state.file.fileName}</span>

										<svg className="lexicon-icon lexicon-icon-times">
											<use xlinkHref="#times" />
										</svg>
									</div>
								)}

								{this.state.toolbar.visible && (
									<div className="progress-bar-container">
										<div
											className="progress-bar form-control"
											id={`${portletNamespace}progressBar`}
										>
											<div
												className="progress"
												id={`${portletNamespace}progress`}
												style={{width: this.state.toolbar.progress + '%'}}
											>
											</div>
										</div>

										{!this.state.toolbar.paused && (
											<div className="btn form-control toolbar-control-container" onClick={this.handlePause}>
												<svg className="lexicon-icon lexicon-icon-pause">
													<use xlinkHref="#pause" />
												</svg>
											</div>
										)}

										{this.state.toolbar.paused && (
											<div className="btn form-control toolbar-control-container" onClick={this.handlePlay}>
												<svg className="lexicon-icon lexicon-icon-play">
													<use xlinkHref="#play" />
												</svg>
											</div>
										)}
									</div>
								)}
							</div>
						</div>

						{this.state.message.type === 'error' && (
							<div className="alert alert-danger" role="alert">
								{this.state.message.content}
							</div>
						)}
					</div>

					<div className="col-md-12">
						<div className="form-group">
							<label className="control-label">
								{Liferay.Language.get('leave-a-comment')}
							</label>

							<textarea
								className="form-control"
								id={`${portletNamespace}comment`}
								name={`${portletNamespace}comment`}
								onChange={this.handleUpdateComment}
								value={this.state.comment}
							/>
						</div>
					</div>
				</div>

				<div className="btn-row">
					<Button
						disabled={this.state.submitting}
						type="submit"
					>
						{Liferay.Language.get('submit')}
					</Button>

					<Button
						display="outline"
						onClick={this.handleClear}
						type="button"
					>
						{Liferay.Language.get('clear')}
					</Button>
				</div>
			</form>
		);
	}
}