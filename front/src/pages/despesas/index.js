import { useEffect, useState } from "react";
import Tabela from "../../components/tabela";
import { useNavigate } from "react-router-dom";
import BotaoExcluir from "../../components/botaoDelete";
import {useSelector} from "react-redux";
import {despesaService} from "../../service/despesaService";


export default function Despesas() {
    const cnpj = useSelector((state) => state.auth.cnpj);
    const [dados, setDados] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        despesaService.listar(cnpj)
            .then(response => {
                const dadosFormatados = response.data.map(item => ({
                    id: item.id,
                    despesa: item.despesa
                }));
                setDados(dadosFormatados);
            })
            .catch(error => {
                console.error("Não foi possível carregar tipos de despesa:", error);
            });
    }, [cnpj]);

    const handleEditar = (id) => {
        navigate(`/atualizar_despesa/${id}`);
    }

    const handleExcluir = (id) => {
        setDados((dadosAnteriores) =>
            dadosAnteriores.filter((item) => item.id !== id)
        );
        try {
            despesaService.deletar(id);
        } catch (error) {
            alert("Não foi possível deletar o registro")
        }
    };

    return (
        <div>
            <h1 className="md-12 mt-3 text-center fw-bold">Lista de Despesas</h1>
            <Tabela
                colunas={[
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
    );
}