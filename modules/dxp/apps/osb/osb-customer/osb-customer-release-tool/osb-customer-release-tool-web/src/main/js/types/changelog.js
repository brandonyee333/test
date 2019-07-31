import PropTypes from 'prop-types';

export const jiraComponentsType = PropTypes.arrayOf(
	PropTypes.shape({
		name: PropTypes.string,
		value: PropTypes.string
	})
);

export const jiraIssueJSONObjectType = PropTypes.shape({
	results: PropTypes.arrayOf(
		PropTypes.shape({
			components: PropTypes.arrayOf(PropTypes.string),
			description: PropTypes.string,
			key: PropTypes.string,
			release: PropTypes.string,
			summary: PropTypes.string,
			url: PropTypes.string
		})
	),
	total: PropTypes.number
});
