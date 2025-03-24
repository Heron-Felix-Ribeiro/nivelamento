import axios from "axios";
import { useEffect, useState } from "react";
import Tabela from "../../components/Tabela";
import { useUsuarioContext } from "../../contexts/Usuario";

export default function Despesas() {
    const { usuario } = useUsuarioContext();
    const [dados, setDados] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:3001/despesa?usuarioId=${usuario.id}`)
            .then(response => {
                const dadosFormatados = response.data.map(item => ({
                    despesa: item.despesa
                }));
                setDados(dadosFormatados);
            })
            .catch(error => {
                console.error("Não foi possível carregar tipos de despesa:", error);
            });
    }, [usuario]);

    return (
        <div>
            <h1 className="md-12 mt-3 text-center fw-bold">Lista de Despesas</h1>
            <Tabela 
                colunas={["despesa"]}
                dados={dados}
            />
        </div>
    );
}