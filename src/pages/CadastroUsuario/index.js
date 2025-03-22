import { useState } from "react";
import axios from "axios";
import Formulario from "../../components/Formulario";
import { useNavigate } from "react-router-dom";


export default function CadastroUsuario() {
    const [cadastro, setCadastro] = useState({
        usuario: "",
        idade: "",
        cep: "",
        estado: "",
        cidade: "",
        bairro: "",
        rua: "",
        numero: ""
    })
    const navigate = useNavigate; 

    const cadastroSubmit = async () => {

        try{

            const responseAxios = axios.post("http://localhost:3001/usuarios", cadastro); 
            navigate("/login")
            alert("Conta criada com sucesso")
        } catch(error){
            alert("Não foi possível cadastrar o seu usuário")
        }
        
    } 

    const cepChange = (cep) => {

        if (cep.length === 8) {

            try {

                const response = axios.get(`https://viacep.com.br/ws/${cep}/json/`)
                    .then(response => {
                        const endereco = response.data;
                        const cadastro = [
                            {
                                ...cadastro,
                                estado: endereco.estado,
                                cidade: endereco.localidade,
                                bairro: endereco.bairro,
                                rua: endereco.rua
                            }
                        ];
                        setCadastro(cadastro);
                    })

            } catch (error) {

            }

        }

    }

    return (
        <div className="container bg-dark text-light">
            <h1 className="text-center">Cadastro Usuário</h1>
            <div>
                <Formulario
                    campos={[
                        { name: "usuario", label: "Usuario", type: "text", required: true },
                        { name: "idade", label: "Idade", type: "text", required: true },
                        { name: "cep", label: "CEP", type: "text", required: true, onChange: (e) => cepChange(e.target.value) },
                        { name: "estado", label: "Estado", type: "text", required: true },
                        { name: "cidade", label: "Cidade", type: "text", required: true },
                        { name: "bairro", label: "Bairro", type: "text", required: true },
                        { name: "rua", label: "Rua", type: "text", required: true },
                        { name: "numero", label: "Número", type: "text", required: false }
                    ]}
                    botaoTexto="Cadastrar"
                    aoEnviar={cadastroSubmit}
                />
            </div>
        </div>
    );
}