import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080",  
});

api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("token");
        console.log("Token:", token);
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
            console.log("Token enviado no cabeçalho:", config.headers.Authorization);
        }
        else {
            console.log("Nenhum token encontrado no localStorage.");
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default api;