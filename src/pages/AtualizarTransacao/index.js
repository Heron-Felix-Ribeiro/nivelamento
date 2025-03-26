import axios from "axios";
import { useEffect, useState } from "react";
import Formulario from "../../components/Formulario";
import { useUsuarioContext } from "../../contexts/Usuario";
import { useNavigate, useParams } from "react-router-dom";

export default function AtualizarTransacao() {
    const { usuario } = useUsuarioContext();
    const [cadastro, setCadastro] = useState({
        usuarioId: usuario.id,
        valor: "",
        parcelas: "",
        tipoDespesa: "",
        estabelecimento: ""
    });
    const [tiposDespesa, setTiposDespesa] = useState([]);
    const navigate = useNavigate();
    const { id } = useParams();

    const carregarTransacao = async () => {
        try {
            const response = await axios.get(`http://localhost:3001/transacao/${String(id)}`);
            console.log("Resposta da API:", response.data);
            setCadastro(response.data); 
        } catch (error) {
            alert("Não foi possível carregar a transação");
        }
    };

    const carregarTiposDespesa = async () => {
        try {
            const response = await axios.get(`http://localhost:3001/despesa?usuarioId=${usuario.id}`);
            setTiposDespesa(response.data);
        } catch (error) {
            alert("Não foi possível carregar os tipos de despesa");
        }
    };

    const atualizarSubmit = async () => {
        try {
            await axios.put(`http://localhost:3001/transacao/${id}`, cadastro);
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
                            name: "parcelas",
                            label: "Parcela",
                            type: "select",
                            options: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
                            required: true
                        },
                        {
                            name: "estabelecimento",
                            label: "Estabelecimento",
                            type: "text",
                            required: true
                        },
                        {
                            name: "tipoDespesa",
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
            </div>
        </div>
    );
}