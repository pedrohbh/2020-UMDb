import api from "./api"

const getActors = () => {
    api.get('/open/actor')
    .then(({data}) => {
        return data
    }).catch((error) => {
        console.error(error)
    })
}

export {
    getActors
}