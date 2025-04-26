import axios from "axios";
import { useEffect, useState } from "react";
import Formulario from "../../components/Formulario";
import { useUsuarioContext } from "../../contexts/Usuario";
import { useNavigate, useParams } from "react-router-dom";

export default function AtualizarDespesa() {
    const { usuario } = useUsuarioContext(); 
    const [cadastro, setCadastro] = useState({
        usuarioId: usuario.id,
        despesa: ""
    });
    const navigate = useNavigate();
    const { id } = useParams();

    const carregarDespesa = async () => {
        try {
            const response = await axios.get(`/tipo_despesa/listarUm/${id}`);
            console.log("Resposta da API:", response.data); 
            setCadastro(response.data);
        } catch (error) {
            console.error("Erro ao carregar a despesa:", error);
            alert("Não foi possível carregar a despesa");
        }
    };

    const atualizarSubmit = async () => {
        try {
            await axios.put(`http://localhost:8080/tipo_despesa/${id}`, cadastro);
            navigate("/despesas");
            alert("Despesa atualizada com sucesso");
        } catch (error) {
            console.error("Erro ao atualizar a despesa:", error);
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
            </div>
        </div>
    );
}