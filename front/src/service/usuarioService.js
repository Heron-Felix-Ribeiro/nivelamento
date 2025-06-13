import api from "./api";

const cadastro = async (cadastroRequest) => {
    const res = await api.post("/usuario", cadastroRequest)
    return res
}

const listar = async () => {
    const res = await api.get("/usuario/listar");
    return res;
}

const listarUm = async (id) => {
    const res = await api.get(`/usuario/listarUm/${id}`);
    return res;
}

const listarInativo = async () => {
    const res = await api.get("/usuario/listarInativo");
    return res;
}

const quantidade = async () => {
    const res = await api.get("/usuario/quantidade");
    return res;
}

const editar = async (id, atualizacaoRequest) => {
    const  res = await api.put(`/usuario/${id}`, atualizacaoRequest);
    return res;
}

const mudarStatus = async (id) => {
    const res = await api.post(`/usuario/mudarStatus/${id}`);
    return res;
}

export const usuarioService = {cadastro, listar, listarUm,listarInativo, quantidade, editar, mudarStatus};