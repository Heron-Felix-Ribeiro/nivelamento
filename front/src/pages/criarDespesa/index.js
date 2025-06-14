import React, { useEffect, useState } from "react";
import Formulario from "../../components/formulario";
import { useNavigate } from "react-router-dom";

import { useUsuarioContext } from "../../contexts/Usuario";
import axios from "axios";
import {despesaService} from "../../service/despesaService";
import {useSelector} from "react-redux";

export default function CriarDespesa() {
    const cnpj = useSelector((state) => state.auth.cnpj);
    const [cadastro, setCadastro] = useState({
        usuario: useSelector((state) => state.info.usuario),
        despesa: "",
        cnpj: cnpj
    });
    const navigate = useNavigate();

    const cadastroSubmit = async () => {
        try {
            await despesaService.cadastro(cadastro);
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
                <div className="d-flex justify-content-center">
                    <button type="button" className="btn btn-primary btn-lg mt-3" onClick={cadastroSubmit}>
                        Enviar
                    </button>
                </div>
            </div>
        </div>
    );
};