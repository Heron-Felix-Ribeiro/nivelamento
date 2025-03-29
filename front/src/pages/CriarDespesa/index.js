import React, { useEffect, useState } from "react";
import Formulario from "../../components/Formulario";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { useUsuarioContext } from "../../contexts/Usuario";


export default function CriarDespesa() {
    const { usuario } = useUsuarioContext(); 
    const [cadastro, setCadastro] = useState({
        usuarioId: usuario.id,
        despesa: ""
    });
    const navigate = useNavigate();

    const cadastroSubmit = async () => {
       
        try {
            await axios.post("http://localhost:3001/despesa", cadastro);
            navigate("/despesas");
            alert("Tipo de Despesa criada com sucesso");
        } catch (error) {
            alert("Não foi possível cadastrar o tipo de despesa");
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
            <h1 className="text-center">Criar Despesa</h1>
            <div className="container mt-5 bg-dark pb-5">
                <Formulario
                    campos={[
                        { 
                            name: "despesa", 
                            label: "Tipo de Despesa", 
                            type: "text", 
                            required: true 
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
};