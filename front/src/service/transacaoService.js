import api from "./api";

const cadastro = async (cadastroRequest) => {

    const res = await api.post("/transacao", cadastroRequest)

    return res.data;

}

const listar = async (id) => {

    const res = await api.get(`/transacao/listar/${id}`)

    return res.data;

}

const listarUm = async (id) => {

    const res = await api.get(`/transacao/listarUm/${id}`)

    return res.data;

}

const totalTransacoes = async (id) => {

    const res = await api.get(`/transacao/total/${id}`)

    return res.data;

}

const editar = async (id, transacaoRequest) => {

    const res = await api.put(`/transacao/${id}`, transacaoRequest)

    return res.data;
}

const deletar = async (id) => {

    const res = await api.delete(`/transacao/deletar/${id}`)

    return res.data;
}

export const transacaoService = {cadastro, listar, listarUm , totalTransacoes,editar, deletar };
