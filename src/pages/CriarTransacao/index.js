import axios from "axios";
import { useState } from "react";
import Formulario from "../../components/Formulario";

export default function CriarTransacao() {
    const [resultadoConsulta, setResultadoConsulta] = useState("");

    const criar = async (valor) => {

        if (!valor.trim()) {
            alert("Por favor, insira um valor válido.");
            return;
        }

        try {
            const response = await axios.get(`https://viacep.com.br/ws/${valor}/json/`);
            const data = response.data;

            if (data.erro) {
                alert("Não foi possível cadastrar");
                setResultadoConsulta(null);
            } else {
                alert(`Resultado: ${data.logradouro || "Não disponível"}, ${data.bairro || "Não disponível"}`);
                setResultadoConsulta(data);
            }
        } catch (error) {
            alert("Erro de comunicação com o sistema");
        }
    };

    const aoEnviar = (formData) => {

        criar(formData.valor);
    };

    return (
        <div className="container bg-dark text-light">
            <h1 className="text-center">Criar Transação</h1>
            <div className="container mt-5 bg-dark pb-5">
                <Formulario
                    campos={[
                        { name: "valor", label: "Valor", type: "number", required: true },
                        { name: "parcela", label: "Parcela", type: "text", required: true },
                        { name: "local", label: "Local", type: "text", required: true },
                        { name: "tipo", label: "Tipo Despesa", type: "text", required: false }
                    ]}
                    aoEnviar={aoEnviar}
                    botaoTexto="Criar"
                />
            </div>
        </div>
    );
}
