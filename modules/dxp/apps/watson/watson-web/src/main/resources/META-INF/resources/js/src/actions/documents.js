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
	add as addDocument,
	create as createDocument,
	destroy as destroyDocument,
	edit as editDocument,
	fetchMetrics as fetchDocumentMetrics,
	fetchTranslation as fetchDocumentTranslation,
	importModel as importDocuments,
	index as indexDocuments,
	requestTranslation as requestDocumentTranslation,
	search as searchDocuments,
	update as updateDocument,
	updateDataManually as updateDocumentsDataManually,
	updateFormData as updateDocumentsFormData,
	view as viewDocument
};