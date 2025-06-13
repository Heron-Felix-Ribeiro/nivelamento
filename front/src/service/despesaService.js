import api from "./api";

const cadastro = async (cadastroRequest) => {

    const res = await api.post("/tipo_despesa", cadastroRequest)

    return res

}

const listar = async (cnpj) => {

    const res = await api.get(`/tipo_despesa/listar/${cnpj}`)

    return res

}

const listarUm = async (id) => {

    const res = await api.get(`/tipo_despesa/listarUm/${id}`)

    return res

}

const editar = async (id, despesaRequest) => {

    const res = await api.put(`/tipo_despesa/${id}`, despesaRequest)

    return res
}

const deletar = async (id) => {

    const res = await api.delete(`/tipo_despesa/deletar/${id}`)

    return res

}

export const despesaService = {cadastro, listar, listarUm ,editar, deletar };