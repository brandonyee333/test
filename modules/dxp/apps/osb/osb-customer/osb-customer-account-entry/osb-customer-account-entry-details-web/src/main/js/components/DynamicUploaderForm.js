import PropTypes from 'prop-types';
import React from 'react';

import axios from 'axios';

import Resumable from '../third-party/resumable.nocsf';
import {
	getFileProgress,
	handleFileError,
	handleFileSuccess
} from '../helpers/resumable-util';

import Alert from './Alert';
import Button from './Button';

export default class DynamicUploaderForm extends React.Component {
	static propTypes = {
		addTicketAttachmentURL: PropTypes.string.isRequired,
		generateTokenURL: PropTypes.string.isRequired,
		uploadURL: PropTypes.string.isRequired
	};

	formRef = React.createRef();
	selectButtonRef = React.createRef();
	uploadAreaRef = React.createRef();

	state = {
		comment: '',
		file: {
			fileName: '',
			fileSize: ''
		},
		message: {
			content: '',
			type: ''
		},
		namespace: '',
		resumable: {},
		token: '',
		toolbar: {
			paused: false,
			progress: 0,
			visible: false
		}
	};

	componentDidMount() {
		const {uploadURL} = this.props;

		const {namespace} = window.AccountDetailsConstants;

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
				simultaneousUploads: 1,
				target: uploadURL,
				testChunks: true,
				throttleProgressCallbacks: 1
			}
		);

		if (!resumable.support) {
			this.setState(
				{
					message: {
						content: Liferay.Language.get('this-browser-is-not-supported'),
						type: 'error'
					}
				}
			);
		}
		else {
			resumable.assignBrowse(this.selectButtonRef.current);
			resumable.assignDrop(this.uploadAreaRef.current);

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
									fileSize: file.size
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
					namespace,
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
						fileSize: ''
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
		this.setState(
			{
				comment: event.currentTarget.value
			}
		);
	}

	updateActionUrl = () => {
		const {addTicketAttachmentURL} = this.props;

		const {namespace} = this.state;

		const formFields = Object.keys(this.state.file);

		let actionUrl = addTicketAttachmentURL;

		formFields.forEach(
			key => {
				actionUrl += `&${namespace}${key}=${this.state.file[key]}`;
			}
		);

		actionUrl += `&${namespace}comment=${this.state.comment}`;

		this.formRef.current.action = actionUrl;
	}

	validateForm = () => {
		const {
			fileName,
			fileSize
		} = this.state.file;

		return (fileName && fileSize);
	}

	render() {
		const {namespace} = this.state;

		return (
			<form method="post" onSubmit={this.handleSubmit} ref={this.formRef}>
				<div className="row">
					<div className="col-md-12">
						<div className="form-group" id={`${namespace}uploadContainer`}>
							<label className="control-label">
								{Liferay.Language.get('attachment')}

								<svg className="lexicon-icon lexicon-icon-asterisk">
									<use xlinkHref="#asterisk" />
								</svg>
							</label>

							<div className="form-control upload-area" id={`${namespace}uploadArea`} ref={this.uploadAreaRef}>
								<input className="attachment" id={`${namespace}selectButton`} name={`${namespace}attachment`} ref={this.selectButtonRef} type="file" />

								<div className="upload-area-label">
									<svg className="lexicon-icon lexicon-icon-paperclip">
										<use xlinkHref="#paperclip" />
									</svg>

									{Liferay.Language.get('add-file-or-drop-file-here')}
								</div>
							</div>

							<div className="toolbar" id={`${namespace}toolbar`}>
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
										<div className="progress-bar form-control" id={`${namespace}progressBar`}>
											<div className="progress" id={`${namespace}progress`} style={{width: this.state.toolbar.progress + '%'}}></div>
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
							<Alert type="danger">
								{this.state.message.content}
							</Alert>
						)}
					</div>

					<div className="col-md-12">
						<div className="form-group">
							<label className="control-label">
								{Liferay.Language.get('leave-a-comment')}
							</label>

							<textarea className="form-control" id={`${namespace}comment`} name={`${namespace}comment`} onChange={this.handleUpdateComment} value={this.state.comment} />
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