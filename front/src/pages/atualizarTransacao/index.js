import axios from "axios";
import { useEffect, useState } from "react";
import Formulario from "../../components/formulario";
import { useUsuarioContext } from "../../contexts/Usuario";
import { data, useNavigate, useParams } from "react-router-dom";
import {useSelector} from "react-redux";
import {transacaoService} from "../../service/transacaoService";
import {despesaService} from "../../service/despesaService";

export default function AtualizarTransacao() {
    const cnpj = useSelector((state) => state.auth.cnpj);
    const [cadastro, setCadastro] = useState({
        usuario: "",
        valor: "",
        despesa: "",
        estabelecimento: ""
    });
    const [tiposDespesa, setTiposDespesa] = useState([]);
    const navigate = useNavigate();
    const { id } = useParams();

    const carregarTransacao = async () => {
        try {
            const response = await transacaoService.listarUm(id);
            setCadastro(response.data);
        } catch (error) {
            alert("Não foi possível carregar a transação");
        }
    };

    const carregarTiposDespesa = async () => {
        try {
            const response = await despesaService.listar(cnpj);
            setTiposDespesa(response.data);
        } catch (error) {
            alert("Não foi possível carregar os tipos de despesa");
        }
    };

    const atualizarSubmit = async () => {
        try {
            await transacaoService.editar(id, cadastro);
            navigate("/transacoes");
            alert("Transação atualizada com sucesso");
        } catch (error) {
            alert("Não foi possível atualizar a transação");
        }
    };

    const handleMudarCampo = (campo, valor) => {
        setCadastro((prevCadastro) => ({
            ...prevCadastro,
            [campo]: valor
        }));
    };

    useEffect(() => {
        carregarTransacao(); 
        carregarTiposDespesa(); 
    }, []);

    return (
        <div className="container bg-dark text-light">
            <h1 className="text-center">Atualizar Transação</h1>
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
                            label: "Estabelecimento",
                            type: "text",
                            required: true
                        },
                        {
                            name: "despesa",
                            label: "Tipo Despesa",
                            type: "select",
                            options: tiposDespesa.map((despesa) => despesa.despesa),
                            required: false
                        }
                    ]}
                    valores={cadastro}
                    botaoTexto="Atualizar"
                    aoMudarCampo={handleMudarCampo}
                    aoEnviar={atualizarSubmit}
                />
                <div className="d-flex justify-content-center">
                    <button className="btn btn-success btn-lg" onClick={atualizarSubmit}>
                        Enviar
                    </button>
                </div>
            </div>
        </div>
    );
}