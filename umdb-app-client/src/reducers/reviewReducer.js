import {
    CREATE_REVIEW,
} from '../actions/review/types'

const reviewReducer = (state = {}, action) => {
    switch (action.type){
        case CREATE_REVIEW:
            return { ...state, [action.payload.id]: action.payload };
        default:
            return state
    }
}
export default reviewReducer