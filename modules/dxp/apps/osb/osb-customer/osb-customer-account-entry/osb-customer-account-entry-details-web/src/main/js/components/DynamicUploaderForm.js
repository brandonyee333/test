import React from 'react';
import PropTypes from "prop-types";

import axios from 'axios';
import {Formik} from 'formik';

import Button from './Button';

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
		fileName: '',
		fileSize: '',
		type: ''
	}

	handleAcceptAttachment = (event) => {
		const {portletNamespace} = this.props;

		const file = event.currentTarget.files[0];

		const props = this.refs.dynamicUploaderFormikForm;

		props.setFieldValue(`${portletNamespace}fileName`, file.name);
		props.setFieldValue(`${portletNamespace}fileSize`, file.size);
		props.setFieldValue(`${portletNamespace}type`, file.type);

		this.setState(
			{
				fileName: file.name,
				fileSize: file.size,
				type: file.type
			}
		);
	}

	handleCheckAttachment = () => {
		let fileName = '';

		if (this.state.fileName) {
			fileName = this.state.fileName;
		}

		return fileName;
	}

	isRequired = message => value => (!!value ? undefined : message);

	handleClear = () => {
		this.setState(
			{
				comment: '',
				fileName: '',
				fileSize: '',
				type: ''
			}
		)

		this.refs.dynamicUploaderFormikForm.handleReset();
	}

	handleClearAccepted = () => {
		this.setState(
			{
				fileName: '',
				fileSize: '',
				type: ''
			}
		);
	}

	handleSubmit = () => {
		this.formRef.current.submit();
	}

	updateActionUrl = (values) => {
		let actionUrl = addTicketAttachmentURL;

		const valueKeys = Object.keys(values);

		valueKeys.map(key => {
			actionUrl += `&${key}=${values[key]}`;
		});

		this.formRef.current.action = actionUrl;
	}

	render() {
		const intialValuesList = [
			'comment',
			'fileName',
			'fileSize',
			'type',
		];

		const {addTicketAttachmentURL, portletNamespace} = this.props;

		const initialValues = {};

		intialValuesList.map(value => {
			initialValues[this.props.portletNamespace + value] = this.props[value] ? this.props[value] : '';
		})

		const checkAttachment = this.handleCheckAttachment();

		return (
			<Formik
				initialValues={initialValues}
				onSubmit={(values) => {
					this.updateActionUrl(values);

					this.handleSubmit();
				}}
				ref="dynamicUploaderFormikForm"
				render={(props) => (
					<form
						action={addTicketAttachmentURL}
						method="post"
						onSubmit={props.handleSubmit}
						ref={this.formRef}
					>
						<div className="row">
							<div className="col-md-12">
								<input
									id={`${portletNamespace}fileName`}
									name={`${portletNamespace}fileName`}
									type="hidden"
									defaultValue={this.state.fileName}
								/>

								<input
									id={`${portletNamespace}fileSize`}
									name={`${portletNamespace}fileSize`}
									type="hidden"
									defaultValue={this.state.fileSize}
								/>

								<input
									id={`${portletNamespace}type`}
									name={`${portletNamespace}type`}
									type="hidden"
									defaultValue={this.state.type}
								/>

								<div className="form-group">
									<label className="control-label" htmlFor={`${portletNamespace}attachment`}>
										{Liferay.Language.get('attachment')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<div className="form-control dropzone">
										<input
											className="attachment"
											id={`${portletNamespace}attachment`}
											name={`${portletNamespace}attachment`}
											onChange={this.handleAcceptAttachment}
											required={true}
											type="file"
										/>

										<div className="dropzone-label">
											<svg className="lexicon-icon lexicon-icon-paperclip">
												<use xlinkHref="#paperclip" />
											</svg>
											Add file or drop files here
										</div>
									</div>

									{checkAttachment && (
										<div className="attachment" key={this.state.fileName} onClick={this.handleClearAccepted}>
											<svg className="lexicon-icon lexicon-icon-paperclip">
												<use xlinkHref="#paperclip" />
											</svg>

											<span className="attachment-name">{this.state.fileName}</span>

											<svg className="lexicon-icon lexicon-icon-times">
												<use xlinkHref="#times" />
											</svg>
										</div>
									)}
								</div>

								{props.touched.attachment && props.errors.attachment && (
									<div className="alert alert-danger" role="alert">
										{props.errors.attachment}
									</div>
								)}
							</div>

							<div className="col-md-12">
								<div className="form-group">
									<label className="control-label" htmlFor={`${portletNamespace}comment`}>
										{Liferay.Language.get('leave-a-comment')}
									</label>

									<textarea
										className="form-control"
										id={`${portletNamespace}comment`}
										name={`${portletNamespace}comment`}
										onChange={(event) => {
											props.setFieldValue(`${portletNamespace}comment`, this.state.comment);

											this.setState(
												{
													comment: event.currentTarget.value
												}
											)
										}}
										value={this.state.comment}
									/>
								</div>
							</div>
						</div>

						<div className="btn-row">
							<Button
								disabled={props.isSubmitting}
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
				)}
			/>
		)
	}
}