import axios from "axios";
import React, {useEffect, useState} from "react";
import Formulario from "../../components/formulario";
import {useUsuarioContext} from "../../contexts/Usuario";
import {data, useNavigate} from "react-router-dom";
import {transacaoService} from "../../service/transacaoService";
import {useSelector} from "react-redux";
import {despesaService} from "../../service/despesaService";

export default function CriarTransacao() {

    const [cadastro, setCadastro] = useState({
        valor: "",
        despesa: "",
        descricao: "",
        cnpj: useSelector(state => state.auth.cnpj),
        responsavel: useSelector(state => state.info.usuario)
    });
    const [tiposDespesa, setTipoDespesa] = useState([])
    const navigate = useNavigate();

    const cadastroSubmit = async () => {

        try {
            await transacaoService.cadastro(cadastro);
            navigate("/transacoes");
            alert("Transação criada com sucesso");
        } catch (error) {
            alert("Não foi possível cadastrar a transação")
        }

    }

    const carregarTiposDespesa = async () => {
        try {
            const respose = await despesaService.listar(cadastro.cnpj);
            setTipoDespesa(respose.data);
        } catch (error) {
            alert("Não foi possível carregar os tipos de despesa");
        }
    }

    useEffect(() => {
        carregarTiposDespesa();
    }, []);

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
                            name: "estabelecimento",
                            label: "Prestador de Serviço",
                            type: "text",
                            required: true
                        },
                        {
                            name: "despesa",
                            label: "Tipo Despesa",
                            type: "select",
                            options: tiposDespesa.map(despesa => despesa.despesa),
                            required: false
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
}
