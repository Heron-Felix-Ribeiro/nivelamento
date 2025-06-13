import {useSelector} from "react-redux";
import {useEffect, useState} from "react";
import {empresaService} from "../../service/empresaService";
import {usuarioService} from "../../service/usuarioService";
import Tabela from "../../components/tabela";
import BotaoExcluir from "../../components/botaoDelete";

export default function Inativos() {
    const cnpj = useSelector((state) => state.auth.cnpj);
    const [empresas, setEmpresas] = useState([])
    const [usuarios, setUsuarios] = useState([]);

    const handleAtivarEmpresa = async (id) => {
        try {
            await empresaService.mudarStatus(id);
            setEmpresas((prevEmpresas) =>
                prevEmpresas.filter((empresa) => empresa.id !== id)
            );
        } catch (error) {
            console.error("Erro ao ativar empresa:", error);
        }
    };

    const handleAtivarUsuario = async (id) => {
        try {
            await usuarioService.mudarStatus(id);
            setUsuarios((prevUsuarios) =>
                prevUsuarios.filter((usuario) => usuario.id !== id)
            );
        } catch (error) {
            console.error("Erro ao ativar usuário:", error);
        }
    };

    useEffect(() => {
        const fetchInativos = async () => {
            try {
                const empresasInativas = await empresaService.listarInativo();
                const empresasFormatadas = empresasInativas.data.map(item => ({
                    id: item.id,
                    nome: item.nome,
                }))
                setEmpresas(empresasFormatadas);

                const usuariosInativos = await usuarioService.listarInativo();
                const usuariosFormatados = usuariosInativos.data.map(item => ({
                    id: item.id,
                    nome: item.usuario,
                    empresa: item.empresa
                }))
                setUsuarios(usuariosFormatados);
            } catch (error) {
                console.error("Erro ao buscar dados inativos:", error);
            }
        };
        fetchInativos();
    }, []);



    return (
        <div className="container mt-5">
            <h1 className="text-center mb-4">Empresas e Usuários Inativos</h1>
            <div className="mb-5">
                <h2>Empresas Inativas</h2>
                <Tabela
                    colunas={[
                        {key: "nome", label: "Nome"},
                        {key: "ações", label: "Ações"},
                    ]}
                    dados={empresas}
                    renderAcoes={(item) => (
                        <>
                            <button className="btn btn-primary"
                                    onClick={() => handleAtivarEmpresa(item.id)}
                            >
                                Ativar
                            </button>
                        </>
                    )}
                />
            </div>
            <div>
                <h2>Usuários Inativos</h2>
                <Tabela
                    colunas={[
                        {key: "nome", label: "Nome"},
                        {key: "empresa", label: "Empresa"},
                        {key: "ações", label: "Ações"}
                    ]}
                    dados={usuarios}
                    renderAcoes={(item) => (
                        <>
                            <button className="btn btn-primary"
                                    onClick={() => handleAtivarUsuario(item.id)}
                            >
                                Ativar
                            </button>
                        </>
                    )}
                />
            </div>
        </div>
    );
}