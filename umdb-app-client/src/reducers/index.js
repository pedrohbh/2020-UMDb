import { combineReducers } from 'redux'
import { reducer as formReducer } from 'redux-form';
import actorReducer from './actorReducer'

export default combineReducers({
    actors: actorReducer,
    form: formReducer,
})