import axios from "axios";

import { getToken } from "./auth";

const api = axios.create({
  baseURL: "https://umdb-server.herokuapp.com/api"
});

api.interceptors.request.use(async config => {
  const token = getToken();
  if (token) {
    config.headers.Authorization = `JWT ${token}`;
  }
  return config;
});

export default api;