import React from 'react';

import {withFormik} from 'formik';
import * as yup from 'yup';

import Button from './Button';

const FormFields = (
	{
		dirty,
		errors,
		handleBlur,
		handleChange,
		handleReset,
		handleSubmit,
		isSubmitting,
		touched,
		values
	}
) => (
	<form onSubmit={handleSubmit}>
		<div className="row">
			<div className="col-md-12">
				<div className="form-group">
					<label className="control-label" htmlFor="accountEnvironmentName">
						{Liferay.Language.get('name')}

						<svg className="lexicon-icon lexicon-icon-asterisk">
							<use xlinkHref="#asterisk" />
						</svg>
					</label>

					<input className="form-control" id="accountEnvironmentName" name="name" onBlur={handleBlur} onChange={handleChange} type="text" value={values.name} />
				</div>

				{touched.name && errors.name && (
					<div className="alert alert-danger" role="alert">
						{errors.name}
					</div>
				)}
			</div>

			<div className="col-md-9">
				<div className="form-group">
					<label className="control-label" htmlFor="accountEnvironmentProduct">
						{Liferay.Language.get('product')}

						<svg className="lexicon-icon lexicon-icon-asterisk">
							<use xlinkHref="#asterisk" />
						</svg>
					</label>

					<select className="form-control" id="accountEnvironmentProduct" name="product" onBlur={handleBlur} onChange={handleChange}>
						<option value="" />
					</select>
				</div>

				{touched.product && errors.product && (
					<div className="alert alert-danger" role="alert">
						{errors.product}
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

					<select className="form-control" id="envLFR" name="envLFR" onBlur={handleBlur} onChange={handleChange}>
						<option value="" />
					</select>
				</div>

				{touched.envLFR && errors.envLFR && (
					<div className="alert alert-danger" role="alert">
						{errors.envLFR}
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

					<select className="form-control" id="envOS" name="envOS" onBlur={handleBlur} onChange={handleChange}>
						<option value="" />
					</select>
				</div>

				{touched.envOS && errors.envOS && (
					<div className="alert alert-danger" role="alert">
						{errors.envOS}
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

					<select className="form-control" id="envJVM" name="envJVM" onBlur={handleBlur} onChange={handleChange}>
						<option value="" />
					</select>
				</div>

				{touched.envJVM && errors.envJVM && (
					<div className="alert alert-danger" role="alert">
						{errors.envJVM}
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

					<select className="form-control" id="envAS" name="envAS" onBlur={handleBlur} onChange={handleChange}>
						<option value="" />
					</select>
				</div>

				{touched.envAS && errors.envAS && (
					<div className="alert alert-danger" role="alert">
						{errors.envAS}
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

					<select className="form-control" id="envDB" name="envDB" onBlur={handleBlur} onChange={handleChange}>
						<option value="" />
					</select>
				</div>

				{touched.envDB && errors.envDB && (
					<div className="alert alert-danger" role="alert">
						{errors.envDB}
					</div>
				)}
			</div>

			<div className="col-md-12">
				<div className="form-group">
					<label className="control-label" htmlFor="portalExt">
						{Liferay.Language.get('portal-ext')}

						<svg className="lexicon-icon lexicon-icon-asterisk">
							<use xlinkHref="#asterisk" />
						</svg>
					</label>

					<input className="form-control" id="portalExt" multiple="true" name="portalExt" onBlur={handleBlur} onChange={handleChange} type="file" />
				</div>

				{touched.portalExt && errors.portalExt && (
					<div className="alert alert-danger" role="alert">
						{errors.portalExt}
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

					<input className="form-control" id="patchLevel" multiple="true" name="patchLevel" onBlur={handleBlur} onChange={handleChange} type="file" />
				</div>

				{touched.patchLevel && errors.patchLevel && (
					<div className="alert alert-danger" role="alert">
						{errors.patchLevel}
					</div>
				)}
			</div>
		</div>

		<div className="btn-row">
			<Button
				display="outline"
				onClick={handleReset}
				type="button"
			>
				{Liferay.Language.get('cancel')}
			</Button>

			<Button
				disabled={isSubmitting}
				type="submit"
			>
				{Liferay.Language.get('save')}
			</Button>
		</div>
	</form>
);

const requiredSchema = yup.string().required(
	Liferay.Language.get('this-field-is-required')
);

export const AddAccountEnvironmentForm = withFormik(
	{
		displayName: 'addAccountEnvironment',
		handleSubmit: '',
		mapPropsToValues: () => (
			{
				envAS: '',
				envDB: '',
				envJVM: '',
				envLFR: '',
				envOS: '',
				name: '',
				patchLevel: '',
				portalExt: '',
				product: ''
			}
		),
		validationSchema: yup.object().shape(
			{
				envAS: requiredSchema,
				envDB: requiredSchema,
				envJVM: requiredSchema,
				envLFR: requiredSchema,
				envOS: requiredSchema,
				name: requiredSchema,
				patchLevel: requiredSchema,
				portalExt: requiredSchema,
				product: requiredSchema
			}
		)
	}
)(FormFields);