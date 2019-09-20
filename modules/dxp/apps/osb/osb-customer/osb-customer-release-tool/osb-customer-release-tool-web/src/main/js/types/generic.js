import PropTypes from 'prop-types';

export const errorType = PropTypes.shape(
	{
		error: PropTypes.shape(
			{
				message: PropTypes.string,
				name: PropTypes.string
			}
		)
	}
);

export const filtersJSONObjectType = PropTypes.arrayOf(
	PropTypes.shape(
		{
			fixPacks: PropTypes.arrayOf(
				PropTypes.shape(
					{
						name: PropTypes.string,
						version: PropTypes.string
					}
				)
			),
			name: PropTypes.string,
			product: PropTypes.string,
			version: PropTypes.string
		}
	)
);