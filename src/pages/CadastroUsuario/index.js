import { useState } from "react";
import Formulario from "../../components/Formulario";

export default function cadastrarUsuario() {

    const [usuarioInformado, setUsuario] = useState = ("");  
    const [cep, setCep] = useState = (""); 
    

    try{
        const responseAxios = await axios.post ("http://localhost:3001/");

           
        
    } catch (error) {
        alert("Não foi possível cadastrar o usuário")
    }

    return (
        <div>
            <h1>Cadastro Usuário</h1>
            <div>
                <Formulario
                    campos={[
                        { name: "usuario", label: "Usuario", type: "text", required: true },
                        { name: "idade", label: "Idade", type: "text", required: true },
                        { name: "cep", label: "CEP", type: "text", required: true },
                        { name: "estado", label: "Estado", type: "text", required: true },
                        { name: "cidade", label: "Cidade", type: "text", required: true },
                        { name: "bairro", label: "Bairro", type: "text", required: true },
                        { name: "rua", label: "Rua", type: "text", required: true },
                        { name: "numero", label: "Número", type: "text", required: false }
                    ]}
                />
            </div>
        </div>
    );
}