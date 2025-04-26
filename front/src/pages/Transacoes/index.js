import axios from "axios"
import Tabela from "../../components/Tabela"
import { useEffect, useState } from "react"
import { useUsuarioContext } from "../../contexts/Usuario"
import { useNavigate } from "react-router-dom";
import BotaoExcluir from "../../components/BotaoDelete";


export default function Transacoes() {
    const { usuario } = useUsuarioContext();
    const [dados, setDados] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`/transacao/listar/${usuario.id}`)
            .then(response => {
                const dadosFormatados = response.data.map(item => ({
                    id: item.id,
                    valor: item.valor,
                    estabelecimento: item.estabelecimento,
                    despesa: item.despesa

                }))
                console.log(dadosFormatados)
                setDados(dadosFormatados);
            });
    }, [usuario]);

    const handleEditar = (id) => {
        navigate(`/atualizar_transacao/${id}`);
    };

    const handleExcluir = (id) => {
        setDados((dadosAnteriores) =>
            dadosAnteriores.filter((item) => item.id !== id)
        );

        try {
            axios.delete(`http://localhost:8080/transacao/deletar/${id}`);
        } catch (error) {
            alert("Não foi possível deletar o registro")
        }
    };


    return (
        <div>
            <h1 className="md-12 mt-3 text-center fw-bold"> Página de transações</h1>
            <Tabela
                colunas={[
                    { key: "valor", label: "Valor" },
                    { key: "estabelecimento", label: "Estabelecimento" },
                    { key: "despesa", label: "Despesa" },
                    { key: "ações", label: "Ações" }
                ]}
                dados={dados}
                renderAcoes={(item) => (
                    <>
                        <button
                            className="btn btn-primary me-2"
                            onClick={() => handleEditar(item.id)}
                        >
                            Editar
                        </button>
                        <BotaoExcluir id={item.id} aoExcluir={handleExcluir} />
                    </>
                )}
            />
        </div>
    )
}