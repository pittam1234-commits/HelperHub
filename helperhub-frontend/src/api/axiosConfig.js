import axios from "axios";

const api = axios.create({
  baseURL: "https://helperhub.onrender.com",
  headers: {
    "Content-Type": "application/json"
  },
  timeout: 120000
});

api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");

    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default api;