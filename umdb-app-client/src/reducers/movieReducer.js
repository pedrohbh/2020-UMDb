import _ from 'lodash';
import {
    FETCH_MOVIES,
    FETCH_MOVIE,
    CREATE_MOVIE,
    EDIT_MOVIE,
    DELETE_MOVIE
} from '../actions/movie/types'

const movieReducer = (state = {}, action) => {
    switch(action.type) {
        case FETCH_MOVIES:
            return {...state, ..._.mapKeys(action.payload, 'id') }
        case FETCH_MOVIE:
            console.log(action.payload.id)
            return { ...state, [action.payload.id]: action.payload };
        case CREATE_MOVIE:
            return { ...state, [action.payload.id]: action.payload };
        case EDIT_MOVIE:
            return { ...state, [action.payload.id]: action.payload };
        case DELETE_MOVIE:
            return _.omit(state, action.payload);
        default:
            return state;
    }
}

export default movieReducer