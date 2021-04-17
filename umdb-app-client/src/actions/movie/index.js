import api from '../../services/api'
import history from '../../history';

import {
    FETCH_MOVIES,
    FETCH_MOVIE,
    CREATE_MOVIE,
    DELETE_MOVIE,
    EDIT_MOVIE
} from './types'

export const fetchMovies = () => async dispatch => {
    const response = await api.get('/open/movie')
    dispatch({ type: FETCH_MOVIES, payload: response.data })
}

export const fetchMovie = id => async dispatch => {
    const response = await api.get(`open/movie/${id}`);
    dispatch({ type: FETCH_MOVIE, payload: response.data });
};

export const createMovie = (formValues, config) => async dispatch => {
    const response = await api.post('/admin/movie', formValues, config);
    dispatch({ type: CREATE_MOVIE, payload: response.data });
    history.push('/admin/movies')
};

export const editMovie = (id, formValues) => async dispatch => {
    const response = await api.put(`admin/movie/${id}`, formValues);
    dispatch({ type: EDIT_MOVIE, payload: response.data });
};

export const deleteMovie = id => async dispatch => {
    await api.delete(`admin/movie/${id}`);
    dispatch({ type: DELETE_MOVIE, payload: id });
};
