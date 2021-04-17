import { combineReducers } from 'redux'
import { reducer as formReducer } from 'redux-form';
import actorReducer from './actorReducer'
import directorReducer from "./directorReducer";
import genreReducer from "./genreReducer";
import movieReducer from "./movieReducer";
import reviewReducer from "./reviewReducer";

export default combineReducers({
    actors: actorReducer,
    directors: directorReducer,
    genres: genreReducer,
    movies: movieReducer,
    review: reviewReducer,
    form: formReducer,
})