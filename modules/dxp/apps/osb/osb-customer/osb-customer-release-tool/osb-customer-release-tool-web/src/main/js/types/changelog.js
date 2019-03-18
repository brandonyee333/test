import PropTypes from 'prop-types';

export const jiraIssueJSONObject = PropTypes.shape(
	{
		results: PropTypes.arrayOf(
			PropTypes.shape(
				{
					components: PropTypes.arrayOf(PropTypes.string),
					description: PropTypes.string,
					key: PropTypes.string,
					product: PropTypes.string,
					release: PropTypes.string,
					summary: PropTypes.string,
					url: PropTypes.string
				}
			)
		),
		total: PropTypes.number
	}
);