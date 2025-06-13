import {useEffect, useRef, useState} from "react";
import {Chart, registerables} from "chart.js";
import {empresaService} from "../../service/empresaService";
import {usuarioService} from "../../service/usuarioService";
import Tabela from "../../components/tabela";
import BotaoExcluir from "../../components/botaoDelete";
import {useNavigate} from "react-router-dom";

Chart.register(...registerables);

export default function Dashboard() {
    const [empresas, setEmpresas] = useState([]);
    const [usuarios, setUsuarios] = useState([]);
    const [qtdEmpresas, setQtdEmpresas] = useState();
    const [qtdUsuarios, setQtdUsuarios] = useState();
    const chartRef = useRef();
    const navigate = useNavigate();

    useEffect(() => {
        empresaService.listar()
            .then((response) => {
                const dadosFormatados = response.data.map((item) => ({
                    id: item.id,
                    nome: item.nome,
                }));
                console.log(response);
                setEmpresas(dadosFormatados);
            })
            .catch((error) => console.error("Erro ao buscar empresas:", error));

        usuarioService.listar()
            .then((response) => {
                const dadosFormatados = response.data.map((item) => ({
                    id: item.id,
                    nome: item.usuario,
                    empresa: item.empresa,
                }));
                setUsuarios(dadosFormatados);
            })
            .catch((error) => console.error("Erro ao buscar usuários:", error));

        empresaService.quantidade()
            .then((response) => setQtdEmpresas(response.data))
            .catch((error) => console.error("Erro ao buscar quantidade de empresas:", error));

        usuarioService.quantidade()
            .then((response) => setQtdUsuarios(response.data))
            .catch((error) => console.error("Erro ao buscar quantidade de usuários:", error));

    }, []);

    const handleEditarEmpresa = (id) => {
        navigate(`/atualizar_empresa/${id}`);
    };

    const handleExcluirEmpresa = async (id) => {
        try {
            await empresaService.mudarStatus(id);
            setEmpresas((prevEmpresas) => prevEmpresas.filter((empresa) => empresa.id !== id));
        } catch (error) {
            console.error("Erro ao excluir empresa:", error);
        }
    };

    const handleEditarUsuario = (id) => {
        navigate(`/atualizar_usuario/${id}`);
    };

    const handleExcluirUsuario = async (id) => {
        try {
            await usuarioService.mudarStatus(id);
            setUsuarios((prevUsuarios) => prevUsuarios.filter((usuario) => usuario.id !== id));
        } catch (error) {
            console.error("Erro ao excluir usuário:", error);
        }
    };

    useEffect(() => {
        if (chartRef.current) {
            const existingChart = Chart.getChart(chartRef.current);
            if (existingChart) {
                existingChart.destroy();
            }

            if (qtdEmpresas > 0 || qtdUsuarios > 0) {
                const ctx = chartRef.current.getContext("2d");
                new Chart(ctx, {
                    type: "bar",
                    data: {
                        labels: ["Empresas", "Usuários"],
                        datasets: [
                            {
                                label: "Quantidade",
                                data: [qtdEmpresas, qtdUsuarios],
                                backgroundColor: ["#4CAF50", "#2196F3"],
                            },
                        ],
                    },
                    options: {
                        responsive: true,
                        plugins: {
                            legend: {
                                display: true,
                            },
                        },
                    },
                });
            }
        }
    }, [qtdEmpresas, qtdUsuarios]);

    return (
        <div>
            <div>
                <div className="d-flex justify-content-around">
                    <div>
                        <h3>Quantidade de Empresas e Usuários</h3>
                        <canvas ref={chartRef}></canvas>
                    </div>
                </div>
                <div>
                    <div>
                        <Tabela
                            colunas={[
                                {key: "nome", label: "Nome"},
                                {key: "ações", label: "Ações"}
                            ]}
                            dados={empresas}
                            renderAcoes={(item) => (
                                <>
                                    <button className="btn btn-primary me-2"
                                            onClick={() => handleEditarEmpresa(item.id)}
                                    >
                                        Editar
                                    </button>
                                    <BotaoExcluir id={item.id} aoExcluir={handleExcluirEmpresa}/>
                                </>
                            )}
                        />
                    </div>
                    <div>
                        <Tabela
                            colunas={[
                                {key: "nome", label: "Nome"},
                                {key: "empresa", label: "Empresa"},
                                {key: "ações", label: "Ações"}
                            ]}
                            dados={usuarios}
                            renderAcoes={(item) => (
                                <>
                                    <button className="btn btn-primary me-2"
                                            onClick={() => handleEditarUsuario(item.id)}
                                    >
                                        Editar
                                    </button>
                                    <BotaoExcluir id={item.id} aoExcluir={handleExcluirUsuario}/>
                                </>
                            )}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
}