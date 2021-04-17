import api from '../../services/api'
import history from '../../history';

import {
    CREATE_REVIEW,
} from './types'


export const createReview = (formValues, movieId) => async dispatch => {
    const response = await api.post(`/close/movie/${movieId}/review`, formValues);
    dispatch({ type: CREATE_REVIEW, payload: response.data });
    history.push(`/admin/movie/${movieId}`)
};

