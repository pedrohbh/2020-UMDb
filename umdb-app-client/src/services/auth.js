import api from "./api"

const TOKEN_KEY = '@token'
const USER_ROLE_KEY = '@userRole'
const USER_NAME_KEY = '@userName'
const ADMIN_VALUE = 'ROLE_ADMIN'

const isAuthenticated = () => localStorage.getItem(TOKEN_KEY) !== null

const isAdmin = () => localStorage.getItem(USER_ROLE_KEY) === ADMIN_VALUE

const getToken = () => localStorage.getItem(TOKEN_KEY)

const login = (email, password) => {
  api.post('/open/login/', {
    email,
    password
  }).then(({data}) => {
    setCredentials(data)
    window.location.href = '/'
  }).catch((error) => {
    return false;
  })
};

const setCredentials = ({name, token, roles}) => {
  const role = roles[0]
  localStorage.setItem(TOKEN_KEY, token)
  localStorage.setItem(USER_ROLE_KEY, role)
  localStorage.setItem(USER_NAME_KEY, name)
}

const logout = () => {
  localStorage.removeItem(TOKEN_KEY)
  localStorage.removeItem(USER_ROLE_KEY)
  window.location.href = '/'
};

export {
    isAuthenticated,
    getToken,
    login,
    logout,
    isAdmin,
    setCredentials
}