import { useState } from "react";
import axios from "axios";
import Formulario from "../../components/Formulario";
import { useNavigate } from "react-router-dom";
import {usuarioService} from "../../service/UsuarioService";



export default function CadastroUsuario() {
    const [cadastro, setCadastro] = useState({
        usuario: "",
        idade: "",
        cep: "",
        estado: "",
        cidade: "",
        bairro: "",
        rua: "",
        numero: "",
        salario: "",
        senha: ""
    })
    const navigate = useNavigate();

    const cadastroSubmit = async () => {
        try {
            await usuarioService.cadastro( cadastro);
            navigate("/login");
            alert("Conta criada com sucesso");
        } catch (error) {
            alert("Não foi possível cadastrar o seu usuário");
        }

    }

    const handleMudarCampo = (field, value) => {
        setCadastro((prevCadastro) => ({
            ...prevCadastro,
            [field]: value
        }));
    };


    const cepChange = async (cep) => {
        if (cep.length === 8) {
            try {
                const response = await axios.get(`https://viacep.com.br/ws/${cep}/json/`);
                const endereco = response.data;
                setCadastro((prevCadastro) => ({
                    ...prevCadastro,
                    cep: cep,
                    estado: endereco.uf,
                    cidade: endereco.localidade,
                    bairro: endereco.bairro,
                    rua: endereco.logradouro
                }));
            } catch (error) {
                console.error("Erro ao buscar CEP:", error);
            }
        } else {
            setCadastro((prevCadastro) => ({
                ...prevCadastro,
                cep: cep
            }));
        }
    
    };

    return (
        <div className="container bg-dark text-light">
            <h1 className="text-center">Cadastro Usuário</h1>
            <div>
                <Formulario
                    campos={[
                        { name: "usuario", label: "Usuario", type: "text", required: true },
                        { name: "idade", label: "Idade", type: "text", required: true },
                        { name: "cep", label: "CEP", type: "text", required: true },
                        { name: "estado", label: "Estado", type: "text", required: true, readOnly: true },
                        { name: "cidade", label: "Cidade", type: "text", required: true, readOnly: true },
                        { name: "bairro", label: "Bairro", type: "text", required: true, readOnly: true },
                        { name: "rua", label: "Rua", type: "text", required: true, readOnly: true },
                        { name: "numero", label: "Número", type: "text", required: false },
                        { name: "salario", label: "Salário", type: "number", required: true},
                        { name: "senha", label: "Senha", type: "password", required: true }
                    ]}                
                    valores={cadastro}
                    aoMudarCampo={(field, value) => {
                        handleMudarCampo(field, value);
                        if (field === "cep") {
                            cepChange(value); 
                        }
                    }}
                    botaoTexto="Cadastrar"
                    aoEnviar={cadastroSubmit}
                />
            </div>
        </div>
    );
}