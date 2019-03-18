import PropTypes from 'prop-types';

export const error = PropTypes.shape(
	{
		error: PropTypes.shape(
			{
				message: PropTypes.string,
				name: PropTypes.string
			}
		)
	}
);