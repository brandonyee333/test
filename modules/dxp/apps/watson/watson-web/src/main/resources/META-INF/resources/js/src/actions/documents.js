import createBaseActions from './crud';
import createSupplementalActions from './supplemental';

export const NAME = 'DOCUMENTS';

const controller = 'documents';

const base = createBaseActions(
	{
		controller,
		name: NAME
	}
);

const supplemental = createSupplementalActions(
	{
		controller,
		name: NAME
	}
);

const actionTypes = {
	...base.actionTypes,
	...supplemental.actionTypes
};

const {
	add,
	create,
	destroy,
	edit,
	index,
	search,
	update,
	view
} = base.actions;

const {
	fetchMetrics,
	fetchTranslation,
	importModel,
	requestTranslation,
	updateDataManually,
	updateFormData
} = supplemental.actions;

export {
	actionTypes,
	add as addDocuments,
	create as createDocuments,
	destroy as destroyDocuments,
	edit as editDocuments,
	fetchMetrics as fetchDocumentMetrics,
	fetchTranslation as fetchDocumentsTranslation,
	importModel as importDocuments,
	index as indexDocuments,
	requestTranslation as requestDocumentsTranslation,
	search as searchDocuments,
	update as updateDocuments,
	updateDataManually as updateDocumentsDataManually,
	updateFormData as updateDocumentsFormData,
	view as viewDocuments
};