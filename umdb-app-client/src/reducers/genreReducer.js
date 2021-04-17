import _ from 'lodash';
import {
    FETCH_GENRES,
    FETCH_GENRE,
    CREATE_GENRE,
    EDIT_GENRE,
    DELETE_GENRE
} from '../actions/genre/types'

const genreReducer = (state = {}, action) => {
    switch(action.type) {
        case FETCH_GENRES:
            return {...state, ..._.mapKeys(action.payload, 'id') }
        case FETCH_GENRE:
            console.log(action.payload.id)
            return { ...state, [action.payload.id]: action.payload };
        case CREATE_GENRE:
            return { ...state, [action.payload.id]: action.payload };
        case EDIT_GENRE:
            return { ...state, [action.payload.id]: action.payload };
        case DELETE_GENRE:
            return _.omit(state, action.payload);
        default:
            return state;
    }
}

export default genreReducer