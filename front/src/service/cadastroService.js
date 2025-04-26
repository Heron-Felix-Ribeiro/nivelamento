import api from "./api";

const cadastro = async () => {

    const res = await api.post("usuario", cadastroRequest)
    
    return res.data

}

export const cadastroService = (cadastro); 