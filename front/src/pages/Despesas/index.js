import { useEffect, useState } from "react";
import Tabela from "../../components/Tabela";
import { useNavigate } from "react-router-dom";
import BotaoExcluir from "../../components/BotaoDelete";
import {useSelector} from "react-redux";
import {despesaService} from "../../service/DespesaService";


export default function Despesas() {
    const usuario = useSelector((state => state.auth.id));
    const [dados, setDados] = useState([]);
    const navigate = useNavigate();
    console.log("ID do usuário:", usuario?.id);

    useEffect(() => {
        despesaService.listar(usuario)
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
    }, [usuario]);

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