import api from "./api"
import { setCredentials } from './auth'

const createUser = (name, email, password) => {
    api.post('/open/user', {
        name,
        email,
        password
    }).then((data) => {
        setCredentials(data)
    }).catch((error) => {
        return false
    })
}

export {
    createUser
}