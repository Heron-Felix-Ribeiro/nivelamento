import api from "./api";

const cadastro = async (cadastroRequest) => {
    const res = await api.post("/empresa", cadastroRequest);
    return res;
}

const listar = async () => {
    const res = await api.get("/empresa/listarTodos");
    return res;
}

const listarInativo = async () => {
    const res= await api.get("/empresa/listarInativo");
    return res;
}

const listarUm = async (id) => {
    const res = await api.get(`/empresa/listarUm/${id}`);
    return res;
}

const quantidade = async () => {
    const res = await api.get("/empresa/quantidade");
    return res;
}

const editar = async (id, cadastroRequest) => {
    const res = await api.put(`/empresa/editar/${id}`, cadastroRequest);
    return res;
}

const mudarStatus = async (id) => {
    const res = await api.post(`/empresa/mudarStatus/${id}`);
    return res;
}

export const empresaService = { cadastro, listar,listarInativo, listarUm, quantidade, editar, mudarStatus};