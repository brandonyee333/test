import React from 'react';

import {Formik} from 'formik';
import * as yup from 'yup';

import Button from './Button';

export default class AddAccountEnvironmentForm extends React.Component {
	addEnvironmentFormRef = React.createRef();

	state = {
		envLFRIndex: null,
		envLFRVersion: null,
		patchLevelFile: null,
		portalExtFile: null,
		productEntryIdIndex: null
	};

	handleFileChange = event => {
		const {files} = event.target;
		const name = event.target.name.replace(
			[`${this.props.portletNamespace}`], '');

		this.setState({
			[`${name}File`]: files.length > 0 ? files[0].name : null
		});

		this.refs.formikInstanceRef.handleChange(event);
	};

	handleSelectChange = event => {
		const {options} = event.target;
		const {value} = options[options.selectedIndex];
		const fieldNameIndex = options[options.selectedIndex].id.replace(
			/\D+/g,
			''
		);

		const name = event.target.name.replace(
			[`${this.props.portletNamespace}`], '');

		const envLFRVersion = (name == 'envLFR') ? value : null;

		this.setState({
			[`${name}Index`]: fieldNameIndex,
			envLFRVersion
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
			environmentConfiguration,
			touched,
			values
		} = this.props;

		const {
			envLFRIndex,
			envLFRVersion,
			patchLevelFile,
			portalExtFile,
			productEntryIdIndex
		} = this.state;

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
			[`${portletNamespace}productEntryId`]: ''
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
			[`${portletNamespace}productEntryId`]: requiredSchema
		});

		const {products} = environmentConfiguration;
		const {envLFRVersions} = environmentConfiguration;

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
						<div className="row">
							<div className="col-md-12">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${portletNamespace}accountEnvironmentName`}
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
										htmlFor={`${portletNamespace}accountEnvironmentProduct`}
									>
										{Liferay.Language.get('product')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										id={`${portletNamespace}accountEnvironmentProduct`}
										name={`${portletNamespace}productEntryId`}
										onBlur={props.handleBlur}
										onChange={this.handleSelectChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{products.map((product, index) => (
											<option
												key={'product-' + index}
												id={'product-' + index}
												label={product.displayName}
												value={product.productEntryId}
											/>
										))}
									</select>
								</div>

								{props.touched[`${portletNamespace}productEntryId`] &&
									props.errors[`${portletNamespace}productEntryId`] && (
										<div className="alert alert-danger" role="alert">
											{props.errors[`${portletNamespace}productEntryId`]}
										</div>
									)}
							</div>

							<div className="col-md-3">
								<div className="form-group">
									<label
										className="control-label"
										htmlFor={`${portletNamespace}envLFR`}
									>
										{Liferay.Language.get('liferay-version')}

										<svg className="lexicon-icon lexicon-icon-asterisk">
											<use xlinkHref="#asterisk" />
										</svg>
									</label>

									<select
										className="form-control"
										disabled={productEntryIdIndex == null}
										id={`${portletNamespace}envLFR`}
										name={`${portletNamespace}envLFR`}
										onBlur={props.handleBlur}
										onChange={this.handleSelectChange}
									>
										<option value="" label={Liferay.Language.get('select')} />

										{productEntryIdIndex
											? products[productEntryIdIndex].envLFR.map(
													(version, index) => (
														<option
															key={'envLFR-' + index}
															id={'envLFR-' + index}
															label={version.name}
															value={version.value}
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
									<label
										className="control-label"
										htmlFor={`${portletNamespace}envOS`}
									>
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

										{envLFRIndex && envLFRVersion
											? envLFRVersions[
													envLFRIndex
											  ][envLFRVersion].envOS.map((envOS, index) => (
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
									<label
										className="control-label"
										htmlFor={`${portletNamespace}envJVM`}
									>
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

										{envLFRIndex && envLFRVersion
											? envLFRVersions[
													envLFRIndex
											  ][envLFRVersion].envJVM.map((envJVM, index) => (
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
									<label
										className="control-label"
										htmlFor={`${portletNamespace}envAS`}
									>
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

										{envLFRIndex && envLFRVersion
											? envLFRVersions[
													envLFRIndex
											  ][envLFRVersion].envAS.map((envAS, index) => (
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
									<label
										className="control-label"
										htmlFor={`${portletNamespace}envDB`}
									>
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

										{envLFRIndex && envLFRVersion
											? envLFRVersions[
													envLFRIndex
											  ][envLFRVersion].envDB.map((envDB, index) => (
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
									<label
										className="control-label"
										htmlFor={`${portletNamespace}envBrowser`}
									>
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

										{envLFRIndex && envLFRVersion
											? envLFRVersions[
													envLFRIndex
											  ][envLFRVersion].envBrowser.map((envBrowser, index) => (
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

							{envLFRIndex &&
							 envLFRVersion &&
							 envLFRVersions[
									envLFRIndex
							 ][envLFRVersion].envCS ? (
								<div className="col-md-6">
									<div className="form-group">
										<label
											className="control-label"
											htmlFor={`${portletNamespace}envCS`}
										>
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

											{envLFRVersions[
													envLFRIndex
											 ][envLFRVersion].envCS.map((envCS, index) => (
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

							{envLFRIndex &&
							 envLFRVersion &&
							 productEntryIdIndex &&
							 'enterpriseSearch' in products[productEntryIdIndex] ? (
								<div className="col-md-12">
									<div className="form-group">
										<label
											className="control-label"
											htmlFor={`${portletNamespace}envSearch`}
										>
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
											{envLFRVersions[envLFRIndex][
													envLFRVersion
											 ].envSearch[products[productEntryIdIndex].enterpriseSearch
												? 0 : 1
											 ][products[productEntryIdIndex].enterpriseSearch
										 		? 'enterprise' : 'standard'
											 ].map((envSearch, index) => (
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
									<label
										className="control-label"
										htmlFor={`${portletNamespace}portalExt`}
									>
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
									<label
										className="control-label"
										htmlFor={`${portletNamespace}patchLevel`}
									>
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