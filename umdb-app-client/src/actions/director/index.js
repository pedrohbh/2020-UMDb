import api from '../services/api'
import history from '../history';

import {
    FETCH_DIRECTORS,
    FETCH_DIRECTOR,
    CREATE_DIRECTOR,
    DELETE_DIRECTOR,
    EDIT_DIRECTOR
} from './types'

export const fetchDirectors = () => async dispatch => {
    const response = await api.get('/open/director')
    dispatch({ type: FETCH_DIRECTORS, payload: response.data })
}

export const fetchDirector = id => async dispatch => {
    const response = await api.get(`open/director/${id}`);
    dispatch({ type: FETCH_DIRECTOR, payload: response.data });
};

export const createDirector = formValues => async dispatch => {
    const response = await api.post('/admin/director', formValues);
    dispatch({ type: CREATE_DIRECTOR, payload: response.data });
    history.push('/admin/directors')
};

export const editDirector = (id, formValues) => async dispatch => {
    const response = await api.put(`admin/director/${id}`, formValues);
    dispatch({ type: EDIT_DIRECTOR, payload: response.data });
};

export const deleteDirector = id => async dispatch => {
    await api.delete(`admin/director/${id}`);
    dispatch({ type: DELETE_DIRECTOR, payload: id });
};
