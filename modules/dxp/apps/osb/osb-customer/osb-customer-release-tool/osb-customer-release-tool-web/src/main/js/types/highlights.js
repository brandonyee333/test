import PropTypes from 'prop-types';

export const filtersJSONObject = PropTypes.arrayOf(
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

export const fixPackJSONObject = PropTypes.shape(
	{
		results: PropTypes.arrayOf(
			PropTypes.shape(
				{
					content: PropTypes.string,
					releaseDate: PropTypes.string,
					resourcePrimKey: PropTypes.string,
					title: PropTypes.string
				}
			)
		),
		total: PropTypes.number
	}
);