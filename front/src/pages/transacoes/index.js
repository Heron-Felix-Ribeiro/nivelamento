import Tabela from "../../components/tabela"
import { useEffect, useState } from "react"
import { useNavigate } from "react-router-dom";
import BotaoExcluir from "../../components/botaoDelete";
import {transacaoService} from "../../service/transacaoService";
import {useSelector} from "react-redux";


export default function Transacoes() {
    const usuario = useSelector((state) => state.auth.id);
    const [dados, setDados] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        transacaoService.listar(usuario)
            .then(response => {
                const dadosFormatados = response.data.map(item => ({
                    id: item.id,
                    valor: item.valor,
                    estabelecimento: item.estabelecimento,
                    despesa: item.despesa
                }))
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
            transacaoService.deletar(id);
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