import React from 'react';

import {Formik} from 'formik';
import * as yup from 'yup';

import Button from './Button';

export default class AddAccountEnvironmentForm extends React.Component {
	addEnvironmentFormRef = React.createRef();

	state = {
		[`${this.props.portletNamespace}envLFRIndex`]: null,
		[`${this.props.portletNamespace}patchLevelFile`]: null,
		[`${this.props.portletNamespace}portalExtFile`]: null,
		[`${this.props.portletNamespace}productIndex`]: null
	};

	handleFileChange = event => {
		const {files} = event.target;
		const {name} = event.target;

		this.setState({
			[`${name}File`]: files.length > 0 ? files[0].name : null
		});

		this.refs.formikInstanceRef.handleChange(event);
	};

	handleSelectChange = event => {
		const {options} = event.target;
		const fieldNameIndex = options[options.selectedIndex].id.replace(
			/\D+/g,
			''
		);

		const {name} = event.target;

		this.setState({
			[`${name}Index`]: fieldNameIndex
		});

		this.refs.formikInstanceRef.handleChange(event);
	};

	handleSubmit = () => this.addEnvironmentFormRef.current.submit();

	render() {
		const {
			addEnvironmentURL,
			errors,
			handleBlur,
			handleChange,
			handleCloseModal,
			handleSubmit,
			isSubmitting,
			portletNamespace,
			productEntries,
			touched,
			values
		} = this.props;

		const envLFRIndex = this.state[`${portletNamespace}envLFRIndex`];
		const patchLevelFile = this.state[`${portletNamespace}patchLevelFile`];
		const portalExtFile = this.state[`${portletNamespace}portalExtFile`];
		const productIndex = this.state[`${portletNamespace}productIndex`];

		const initialValues = {
			[`${portletNamespace}envAS`]: '',
			[`${portletNamespace}envBrowser`]: '',
			[`${portletNamespace}envCS`]: '',
			[`${portletNamespace}envDB`]: '',
			[`${portletNamespace}envJVM`]: '',
			[`${portletNamespace}envLFR`]: '',
			[`${portletNamespace}envOS`]: '',
			[`${portletNamespace}envSearch`]: '',
			[`${portletNamespace}name`]: '',
			[`${portletNamespace}patchLevel`]: '',
			[`${portletNamespace}portalExt`]: '',
			[`${portletNamespace}product`]: ''
		};

		const requiredSchema = yup
			.string()
			.required(Liferay.Language.get('this-field-is-required'));

		const validationSchema = yup.object().shape({
			[`${portletNamespace}envAS`]: requiredSchema,
			[`${portletNamespace}envDB`]: requiredSchema,
			[`${portletNamespace}envJVM`]: requiredSchema,
			[`${portletNamespace}envLFR`]: requiredSchema,
			[`${portletNamespace}envOS`]: requiredSchema,
			[`${portletNamespace}name`]: requiredSchema,
			[`${portletNamespace}patchLevel`]: requiredSchema,
			[`${portletNamespace}portalExt`]: requiredSchema,
			[`${portletNamespace}product`]: requiredSchema
		});

		return (
			<Formik
				initialValues={initialValues}
				validationSchema={validationSchema}
				onSubmit={this.handleSubmit}
				ref="formikInstanceRef"
				render={props => (
					<form
						action={addEnvironmentURL}
						encType="multipart/form-data"
						method="post"
						onSubmit={props.handleSubmit}
						ref={this.addEnvironmentFormRef}
					>
						<input
							name={`${portletNamespace}productEntryId`}
							type="hidden"
							value={
								productIndex ? productEntries[productIndex].productEntryId : ''
							}
						/>

						<div className="row">
							<div className="col-md-12">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor="accountEnvironmentName"
									>
										{Liferay.Language.get('name')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<input
										className="form-control"
										id={`${portletNamespace}accountEnvironmentName`}
										name={`${portletNamespace}name`}
										onBlur={props.handleBlur}
										onChange={props.handleChange}
										type="text"
										value={props.values.name}
									/>
								</div>

								{props.touched[`${portletNamespace}name`] &&
									props.errors[`${portletNamespace}name`] && (
										<div className="alert alert-danger" role="alert">
											{props.errors[`${portletNamespace}name`]}
										</div>
									)}
							</div>

							<div className="col-md-9">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor="accountEnvironmentProduct"
									>
										{Liferay.Language.get('product')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										id={`${portletNamespace}accountEnvironmentProduct`}
										name={`${portletNamespace}product`}
										onBlur={props.handleBlur}
										onChange={this.handleSelectChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{productEntries.map((productEntry, index) => (
											<option
												key={'product-' + index}
												id={'product-' + index}
												label={productEntry.displayName}
												value={productEntry.displayName}
											/>
										))}
									</select>
								</div>

								{props.touched[`${portletNamespace}product`] &&
									props.errors[`${portletNamespace}product`] && (
										<div className="alert alert-danger" role="alert">
											{props.errors[`${portletNamespace}product`]}
										</div>
									)}
							</div>

							<div className="col-md-3">
								<div className="form-group">
									<label className="control-label" htmlFor="envLFR">
										{Liferay.Language.get('liferay-version')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={productIndex == null}
										id={`${portletNamespace}envLFR`}
										name={`${portletNamespace}envLFR`}
										onBlur={props.handleBlur}
										onChange={this.handleSelectChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{productIndex
											? productEntries[productIndex].envListTypes.map(
													(listType, index) => (
														<option
															key={'envLFR-' + index}
															id={'envLFR-' + index}
															label={listType.envLFR[0].name}
															value={listType.envLFR[0].value}
														/>
													)
											  )
											: null}
									</select>
								</div>

								{props.touched[`${portletNamespace}envLFR`] &&
									props.errors[`${portletNamespace}envLFR`] && (
										<div className="alert alert-danger" role="alert">
											{props.errors[`${portletNamespace}envLFR`]}
										</div>
									)}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label className="control-label" htmlFor="envOS">
										{Liferay.Language.get('operating-system')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={envLFRIndex == null}
										id={`${portletNamespace}envOS`}
										name={`${portletNamespace}envOS`}
										onBlur={props.handleBlur}
										onChange={props.handleChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{productIndex && envLFRIndex
											? productEntries[productIndex].envListTypes[
													envLFRIndex
											  ].envOS.map((envOS, index) => (
													<option
														key={'envOS-' + index}
														id={'envOS-' + index}
														label={envOS.name}
														value={envOS.value}
													/>
											  ))
											: null}
									</select>
								</div>

								{props.touched[`${portletNamespace}envOS`] &&
									props.errors[`${portletNamespace}envOS`] && (
										<div className="alert alert-danger" role="alert">
											{props.errors[`${portletNamespace}envOS`]}
										</div>
									)}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label className="control-label" htmlFor="envJVM">
										{Liferay.Language.get('java-version')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={envLFRIndex == null}
										id={`${portletNamespace}envJVM`}
										name={`${portletNamespace}envJVM`}
										onBlur={props.handleBlur}
										onChange={props.handleChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{productIndex && envLFRIndex
											? productEntries[productIndex].envListTypes[
													envLFRIndex
											  ].envJVM.map((envJVM, index) => (
													<option
														key={'envJVM-' + index}
														id={'envJVM-' + index}
														label={envJVM.name}
														value={envJVM.value}
													/>
											  ))
											: null}
									</select>
								</div>

								{props.touched[`${portletNamespace}envJVM`] &&
									props.errors[`${portletNamespace}envJVM`] && (
										<div className="alert alert-danger" role="alert">
											{props.errors[`${portletNamespace}envJVM`]}
										</div>
									)}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label className="control-label" htmlFor="envAS">
										{Liferay.Language.get('application-server')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={envLFRIndex == null}
										id={`${portletNamespace}envAS`}
										name={`${portletNamespace}envAS`}
										onBlur={props.handleBlur}
										onChange={props.handleChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{productIndex && envLFRIndex
											? productEntries[productIndex].envListTypes[
													envLFRIndex
											  ].envAS.map((envAS, index) => (
													<option
														key={'envAS-' + index}
														id={'envAS-' + index}
														label={envAS.name}
														value={envAS.value}
													/>
											  ))
											: null}
									</select>
								</div>

								{props.touched[`${portletNamespace}envAS`] &&
									props.errors[`${portletNamespace}envAS`] && (
										<div className="alert alert-danger" role="alert">
											{props.errors[`${portletNamespace}envAS`]}
										</div>
									)}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label className="control-label" htmlFor="envDB">
										{Liferay.Language.get('database')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={envLFRIndex == null}
										id={`${portletNamespace}envDB`}
										name={`${portletNamespace}envDB`}
										onBlur={props.handleBlur}
										onChange={props.handleChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{productIndex && envLFRIndex
											? productEntries[productIndex].envListTypes[
													envLFRIndex
											  ].envDB.map((envDB, index) => (
													<option
														key={'envDB-' + index}
														id={'envDB-' + index}
														label={envDB.name}
														value={envDB.value}
													/>
											  ))
											: null}
									</select>
								</div>

								{props.touched[`${portletNamespace}envDB`] &&
									props.errors[`${portletNamespace}envDB`] && (
										<div className="alert alert-danger" role="alert">
											{props.errors[`${portletNamespace}envDB`]}
										</div>
									)}
							</div>

							<div className="col-md-6">
								<div className="form-group">
									<label className="control-label" htmlFor="envBrowser">
										{Liferay.Language.get('browser')}
									</label>

									<select
										className="form-control"
										disabled={envLFRIndex == null}
										id={`${portletNamespace}envBrowser`}
										name={`${portletNamespace}envBrowser`}
										onBlur={props.handleBlur}
										onChange={props.handleChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{productIndex && envLFRIndex
											? productEntries[productIndex].envListTypes[
													envLFRIndex
											  ].envBrowser.map((envBrowser, index) => (
													<option
														key={'envBrowser-' + index}
														id={'envBrowser-' + index}
														label={envBrowser.name}
														value={envBrowser.value}
													/>
											  ))
											: null}
									</select>
								</div>

								{props.touched[`${portletNamespace}envBrowser`] &&
									props.errors[`${portletNamespace}envBrowser`] && (
										<div className="alert alert-danger" role="alert">
											{props.errors[`${portletNamespace}envBrowser`]}
										</div>
									)}
							</div>

							{productIndex &&
							envLFRIndex &&
							productEntries[productIndex].envListTypes[envLFRIndex].envCS ? (
								<div className="col-md-6">
									<div className="form-group">
										<label className="control-label" htmlFor="envCS">
											{Liferay.Language.get('cloud-services')}
										</label>

										<select
											className="form-control"
											disabled={envLFRIndex == null}
											id={`${portletNamespace}envCS`}
											name={`${portletNamespace}envCS`}
											onBlur={props.handleBlur}
											onChange={props.handleChange}
										>
											<option value="" label={Liferay.Language.get('select')} />

											{productEntries[productIndex].envListTypes[
												envLFRIndex
											].envCS.map((envCS, index) => (
												<option
													key={'envCS-' + index}
													id={'envCS-' + index}
													label={envCS.name}
													value={envCS.value}
												/>
											))}
										</select>
									</div>

									{props.touched[`${portletNamespace}envCS`] &&
										props.errors[`${portletNamespace}envCS`] && (
											<div className="alert alert-danger" role="alert">
												{props.errors[`${portletNamespace}envCS`]}
											</div>
										)}
								</div>
							) : null}

							{productIndex &&
							envLFRIndex &&
							productEntries[productIndex].envListTypes[envLFRIndex]
								.envSearch ? (
								<div className="col-md-12">
									<div className="form-group">
										<label className="control-label" htmlFor="envSearch">
											{Liferay.Language.get('search')}
										</label>

										<select
											className="form-control"
											disabled={envLFRIndex == null}
											id={`${portletNamespace}envSearch`}
											multiple={true}
											name={`${portletNamespace}envSearch`}
											onBlur={props.handleBlur}
											onChange={props.handleChange}
										>
											{productEntries[productIndex].envListTypes[
												envLFRIndex
											].envSearch.map((envSearch, index) => (
												<option
													key={'envSearch-' + index}
													id={'envSearch-' + index}
													label={envSearch.name}
													value={envSearch.value}
												/>
											))}
										</select>
									</div>

									{props.touched[`${portletNamespace}envSearch`] &&
										props.errors[`${portletNamespace}envSearch`] && (
											<div className="alert alert-danger" role="alert">
												{props.errors[`${portletNamespace}envSearch`]}
											</div>
										)}
								</div>
							) : null}

							<div className="col-md-12">
								<div className="form-group">
									<label className="control-label" htmlFor="portalExt">
										{Liferay.Language.get('portal-ext')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<div className="upload-dropzone">
										<input
											className="form-control"
											id={`${portletNamespace}portalExt`}
											name={`${portletNamespace}portalExt`}
											onBlur={props.handleBlur}
											onChange={this.handleFileChange}
											type="file"
										/>

										<svg className="lexicon-icon lexicon-icon-paperclip">
											<use xlinkHref="#paperclip" />
										</svg>

										<span>
											{Liferay.Language.get('add-file-or-drop-files-here')}
										</span>
									</div>

									{portalExtFile ? (
										<div className="file-list">
											<ul className="attachment-pool">
												<li className="attachment">
													<svg className="lexicon-icon lexicon-icon-paperclip">
														<use xlinkHref="#paperclip" />
													</svg>

													{portalExtFile}
												</li>
											</ul>
										</div>
									) : null}
								</div>

								{props.touched[`${portletNamespace}portalExt`] &&
									props.errors[`${portletNamespace}portalExt`] && (
										<div className="alert alert-danger" role="alert">
											{props.errors[`${portletNamespace}portalExt`]}
										</div>
									)}
							</div>

							<div className="col-md-12">
								<div className="form-group">
									<label className="control-label" htmlFor="patchLevel">
										{Liferay.Language.get('patch-info')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<div className="upload-dropzone">
										<input
											className="form-control"
											id={`${portletNamespace}patchLevel`}
											name={`${portletNamespace}patchLevel`}
											onBlur={props.handleBlur}
											onChange={this.handleFileChange}
											type="file"
										/>

										<svg className="lexicon-icon lexicon-icon-paperclip">
											<use xlinkHref="#paperclip" />
										</svg>

										<span>
											{Liferay.Language.get('add-file-or-drop-files-here')}
										</span>
									</div>

									{patchLevelFile ? (
										<div className="file-list">
											<ul className="attachment-pool">
												<li className="attachment">
													<svg className="lexicon-icon lexicon-icon-paperclip">
														<use xlinkHref="#paperclip" />
													</svg>

													{patchLevelFile}
												</li>
											</ul>
										</div>
									) : null}
								</div>

								{props.touched[`${portletNamespace}patchLevel`] &&
									props.errors[`${portletNamespace}patchLevel`] && (
										<div className="alert alert-danger" role="alert">
											{props.errors[`${portletNamespace}patchLevel`]}
										</div>
									)}
							</div>
						</div>

						<div className="btn-row">
							<Button
								display="outline"
								onClick={handleCloseModal}
								type="button"
							>
								{Liferay.Language.get('cancel')}
							</Button>

							<Button disabled={props.isSubmitting} type="submit">
								{Liferay.Language.get('save')}
							</Button>
						</div>
					</form>
				)}
			/>
		);
	}
}