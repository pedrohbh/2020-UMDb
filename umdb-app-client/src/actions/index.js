import api from '../services/api'
import history from '../history';

import {
    FETCH_ACTORS,
    FETCH_ACTOR,
    CREATE_ACTOR,
    DELETE_ACTOR,
    EDIT_ACTOR
} from './types'

export const fetchActors = () => async dispatch => {
    const response = await api.get('/open/actor')
    dispatch({ type: FETCH_ACTORS, payload: response.data })
}

export const fetchActor = id => async dispatch => {
    const response = await api.get(`open/actor/${id}`);
    dispatch({ type: FETCH_ACTOR, payload: response.data });
};

export const createActor = formValues => async dispatch => {
    const response = await api.post('/admin/actor', formValues);
    dispatch({ type: CREATE_ACTOR, payload: response.data });
    history.push('/admin/actors')
};

export const editActor = (id, formValues) => async dispatch => {
    const response = await api.put(`admin/actor/${id}`, formValues);
    dispatch({ type: EDIT_ACTOR, payload: response.data });
};

export const deleteActor = id => async dispatch => {
    await api.delete(`admin/actor/${id}`);
    dispatch({ type: DELETE_ACTOR, payload: id });
};
