import PropTypes from 'prop-types';

export const artifactVersionFiltersType = PropTypes.arrayOf(
	PropTypes.shape({
		label: PropTypes.string,
		value: PropTypes.number
	})
);

export const artifactVersionJSONObjectType = PropTypes.shape({
	results: PropTypes.arrayOf(
		PropTypes.shape({
			fromRepositoryURL: PropTypes.string,
			fromVersion: PropTypes.string,
			group: PropTypes.string,
			name: PropTypes.string,
			toRepositoryURL: PropTypes.string,
			toVersion: PropTypes.string
		})
	),
	total: PropTypes.number
});