import api from "./api";

const cadastro = async (cadastroRequest) => {

    const res = await api.post("/transacao", cadastroRequest)

    return res;

}

const listar = async (cnpj) => {

    const res = await api.get(`/transacao/listar/${cnpj}`)

    return res;

}

const listarUm = async (id) => {

    const res = await api.get(`/transacao/listarUm/${id}`)

    return res;

}

const totalTransacoes = async (cnpj) => {

    const res = await api.get(`/transacao/total/${cnpj}`)

    return res;

}

const maioresTranscoes = async (cnpj) => {

    const res = await api.get(`/transacao/maior/${cnpj}`)

    return res;
}

const editar = async (id, transacaoRequest) => {

    const res = await api.put(`/transacao/${id}`, transacaoRequest)

    return res;
}

const deletar = async (id) => {

    const res = await api.delete(`/transacao/deletar/${id}`)

    return res;
}

export const transacaoService = {cadastro, listar, listarUm, maioresTranscoes , totalTransacoes,editar, deletar };
