import api from '../../services/api'
import history from '../../history';

import {
    FETCH_GENRES,
    FETCH_GENRE,
    CREATE_GENRE,
    DELETE_GENRE,
    EDIT_GENRE
} from './types'

export const fetchGenres = () => async dispatch => {
    const response = await api.get('/open/genre')
    dispatch({ type: FETCH_GENRES, payload: response.data })
}

export const fetchGenre = id => async dispatch => {
    const response = await api.get(`open/genre/${id}`);
    dispatch({ type: FETCH_GENRE, payload: response.data });
};

export const createGenre = formValues => async dispatch => {
    const response = await api.post('/admin/genre', formValues);
    dispatch({ type: CREATE_GENRE, payload: response.data });
    history.push('/admin/genres')
};

export const editGenre = (id, formValues) => async dispatch => {
    const response = await api.put(`admin/genre/${id}`, formValues);
    dispatch({ type: EDIT_GENRE, payload: response.data });
};

export const deleteGenre = id => async dispatch => {
    await api.delete(`admin/genre/${id}`);
    dispatch({ type: DELETE_GENRE, payload: id });
};
