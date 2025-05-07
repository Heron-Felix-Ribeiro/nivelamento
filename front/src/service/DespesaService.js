import api from "./api";

const cadastro = async (cadastroRequest) => {

    const res = await api.post("/tipo_despesa", cadastroRequest)

    return res.data

}

const listar = async (id) => {

    const res = await api.get(`/tipo_despesa/listar/${id}`)

    return res.data

}

const listarUm = async (id) => {

    const res = await api.get(`/tipo_despesa/listarUm/${id}`)

    return res.data

}

const editar = async (id, despesaRequest) => {

    const res = await api.put(`/tipo_despesa/${id}`, despesaRequest)

    return res.data
}

const deletar = async (id) => {

    const res = await api.delete(`/tipo_despesa/deletar/${id}`)

    return res.data

}

export const despesaService = {cadastro, listar, listarUm ,editar, deletar };