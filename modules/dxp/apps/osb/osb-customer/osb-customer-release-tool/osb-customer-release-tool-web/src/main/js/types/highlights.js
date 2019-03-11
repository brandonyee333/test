import PropTypes from 'prop-types';

export const fixPackJSONObject = PropTypes.shape(
	{
		total: PropTypes.number,
		results: PropTypes.arrayOf(
			PropTypes.shape(
				{
				content: PropTypes.string,
				releaseDate: PropTypes.string,
				resourcePrimKey: PropTypes.string,
				title: PropTypes.string
			}
			)
		)
	}
)

export const error = PropTypes.shape(
	{
		error: PropTypes.shape(
			{
				message: PropTypes.string,
				name: PropTypes.string,
			}
		)
	}
)