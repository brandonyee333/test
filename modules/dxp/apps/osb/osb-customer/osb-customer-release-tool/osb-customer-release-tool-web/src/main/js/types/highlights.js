import PropTypes from 'prop-types';

export const fixPackFieldsType = PropTypes.arrayOf(
	PropTypes.shape(
		{
			label: PropTypes.string,
			value: PropTypes.string
		}
	)
);

export const fixPackJSONObjectType = PropTypes.shape(
	{
		results: PropTypes.arrayOf(
			PropTypes.shape(
				{
					content: PropTypes.string,
					fieldsUsed: PropTypes.arrayOf(
						PropTypes.string
					),
					releaseDate: PropTypes.string,
					resourcePrimKey: PropTypes.string,
					title: PropTypes.string
				}
			)
		),
		total: PropTypes.number
	}
);