const TOKEN_KEY = '@token'
const USER_ROLE_KEY = '@userRole'
const ADMIN_VALUE = 'admin'

const isAuthenticated = () => localStorage.getItem(TOKEN_KEY) !== null

const isAdmin = () => localStorage.getItem(USER_ROLE_KEY) === ADMIN_VALUE

const getToken = () => localStorage.getItem(TOKEN_KEY)

const login = token => {
  localStorage.setItem(TOKEN_KEY, token)
};

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
    isAdmin
}