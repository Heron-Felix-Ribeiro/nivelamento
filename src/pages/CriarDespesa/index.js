import React, { useState } from "react";
import Formulario from "../../components/Formulario";


export default function CriarDespesa() {
    const [resultadoConsulta, setResultadoConsulta] = useState("");

    const criar = async (valor) => {
        if (!valor.trim()) {
            alert("Por favor, insira um valor válido.");
            return;
        }

        try {
            const response = await fetch(`https://viacep.com.br/ws/${valor}/json/`);
            const data = await response.json();

            if (data.erro) {
                alert("Não foi possível criar o tipo de despesa");
                setResultadoConsulta(null);
            } else {
                alert(`Resultado: ${data.logradouro || "Informação não disponível"}, ${data.bairro || "Informação não disponível"}`);
                setResultadoConsulta(data);
            }
        } catch (error) {
            alert("Erro de comunicação com o sistema");
        }
    };

    return (
        <div className="container bg-dark text-light">
            <h1 className="text-center">Criar Despesa</h1>
            <div className="container mt-5 bg-dark pb-5">
                <Formulario
                    campos={[
                        { name: "tipo", label: "Tipo de Despesa", type: "text", required: true },
                    ]}
                    aoEnviar={(formData) => {
                        criar(formData.tipo);
                    }}
                    botaoTexto="Criar"
                />
            </div>
        </div>
    );
}
