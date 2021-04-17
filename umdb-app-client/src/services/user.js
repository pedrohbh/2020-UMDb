import api from "./api"

const createUser = (name, email, password) => {
    return api.post('/open/user', {
        name,
        email,
        password
    })
}

export {
    createUser
}