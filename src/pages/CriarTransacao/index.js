import axios from "axios";
import { useState } from "react";
import Formulario from "../../components/Formulario";
import { useUsuarioContext } from "../../contexts/Usuario";
import { useNavigate } from "react-router-dom";

export default function CriarTransacao() {
    const { usuario } = useUsuarioContext();
    const [cadastro, setCadastro] = useState({
        usuarioId: usuario.id,
        valor: "",
        parcelas: "",
        tipoDespesa: "",
        estabelecimento: ""
    })
    const navigate = useNavigate();

    const cadastroSubmit = async () => {

        try {
            await axios.post("http://localhost:3001/transacao", cadastro);
            navigate("/transacoes");
            alert("Transação criada com sucesso");
        } catch (error) {
            alert("Não foi possível cadastrar a transação")
        }

    }

    const handleMudarCampo = (campo, valor) => {
        setCadastro((prevCadastro) => ({
            ...prevCadastro,
            [campo]: valor
        }));
    };

    return (
        <div className="container bg-dark text-light">
            <h1 className="text-center">Criar Transação</h1>
            <div className="container mt-5 bg-dark pb-5">
                <Formulario
                    campos={[
                        {
                            name: "valor",
                            label: "Valor",
                            type: "number",
                            required: true
                        },
                        { 
                            name: "parcela", 
                            label: "Parcela", 
                            type: "select",
                            options: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"], 
                            required: true 
                        },
                        { 
                            name: "estabelecimento", 
                            label: "Estabelecimento", 
                            type: "text", 
                            required: true },
                        { 
                            name: "tipo", 
                            label: "Tipo Despesa", 
                            type: "text", 
                            required: false 
                        }
                    ]}
                    valores={cadastro}
                    botaoTexto="Cadastrar"
                    aoMudarCampo={handleMudarCampo}
                    aoEnviar={cadastroSubmit}
                />
            </div>
        </div>
    );
}
