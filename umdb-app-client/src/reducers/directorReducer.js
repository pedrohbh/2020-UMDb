import _ from 'lodash';
import {
    FETCH_DIRECTORS,
    FETCH_DIRECTOR,
    CREATE_DIRECTOR,
    EDIT_DIRECTOR,
    DELETE_DIRECTOR
} from '../actions/director/types'

const directorReducer = (state = {}, action) => {
    switch(action.type) {
        case FETCH_DIRECTORS:
            return {...state, ..._.mapKeys(action.payload, 'id') }
        case FETCH_DIRECTOR:
            return { ...state, [action.payload.id]: action.payload };
        case CREATE_DIRECTOR:
            return { ...state, [action.payload.id]: action.payload };
        case EDIT_DIRECTOR:
            return { ...state, [action.payload.id]: action.payload };
        case DELETE_DIRECTOR:
            return _.omit(state, action.payload);
        default:
            return state;
    }
}

export default directorReducer