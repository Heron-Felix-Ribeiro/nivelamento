import axios from "axios";
import { useEffect, useState } from "react";
import Formulario from "../../components/formulario";
import { useUsuarioContext } from "../../contexts/Usuario";
import { useNavigate, useParams } from "react-router-dom";
import {despesaService} from "../../service/despesaService";
import {useSelector} from "react-redux";

export default function AtualizarDespesa() {
    const usuario  = useSelector((state) => state.auth.id);
    const [cadastro, setCadastro] = useState({
        usuarioId: usuario,
        despesa: ""
    });
    const navigate = useNavigate();
    const { id } = useParams();

    const carregarDespesa = async () => {
        try {
            console.log(id)
            const response = await despesaService.listarUm(id);
            setCadastro(response.data);
        } catch (error) {
            alert("Não foi possível carregar a despesa");
        }
    };

    const atualizarSubmit = async () => {
        try {
            await despesaService.editar(id, cadastro);
            navigate("/despesas");
            alert("Despesa atualizada com sucesso");
        } catch (error) {
            alert("Não foi possível atualizar a despesa");
        }
    };

    const handleMudarCampo = (campo, valor) => {
        setCadastro((prevCadastro) => ({
            ...prevCadastro,
            [campo]: valor
        }));
    };

    useEffect(() => {
        carregarDespesa(); 
    }, []);

    return (
        <div className="container bg-dark text-light">
            <h1 className="text-center">Atualizar Tipo de Despesa</h1>
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