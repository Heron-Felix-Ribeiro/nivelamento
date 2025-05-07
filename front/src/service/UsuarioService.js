import api from "./api";

const cadastro = async (cadastroRequest) => {

    const res = await api.post("/usuario", cadastroRequest)
    
    return res.data

}

export const usuarioService = {cadastro};