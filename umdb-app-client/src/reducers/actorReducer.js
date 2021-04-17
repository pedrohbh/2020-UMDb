import _ from 'lodash';
import {
    FETCH_ACTORS,
    FETCH_ACTOR,
    CREATE_ACTOR,
    EDIT_ACTOR,
    DELETE_ACTOR
} from '../actions/actor/types'

const actorReducer = (state = {}, action) => {
    switch(action.type) {
        case FETCH_ACTORS:
            return {...state, ..._.mapKeys(action.payload, 'id') }
        case FETCH_ACTOR:
            console.log(action.payload.id)
            return { ...state, [action.payload.id]: action.payload };
        case CREATE_ACTOR:
            return { ...state, [action.payload.id]: action.payload };
        case EDIT_ACTOR:
            return { ...state, [action.payload.id]: action.payload };
        case DELETE_ACTOR:
            return _.omit(state, action.payload);
        default:
            return state;
    }
}

export default actorReducer