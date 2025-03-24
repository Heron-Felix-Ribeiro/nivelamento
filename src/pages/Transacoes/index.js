import axios from "axios"
import Tabela from "../../components/Tabela"
import { useEffect, useState } from "react"
import { useUsuarioContext } from "../../contexts/Usuario"


export default function Transacoes() {
    const { usuario } = useUsuarioContext();
    const [dados, setDados] = useState([]);

    useEffect(() =>{
        axios.get(`http://localhost:3001/transacao?usuarioId=${usuario.id}`)
        .then(response => {
            const dadosFormatados = response.data.map(item => ({
                valor: item.valor,
                parcelas: item.parcelas,
                estabelecimento: item.estabelecimento, 
                tipoDespesa: item.tipoDespesa
            }))
            setDados(dadosFormatados);
        });
    }, [usuario]);

    return (
        <div>
            <h1 className="md-12 mt-3 text-center fw-bold"> Página de transações</h1>
            <Tabela
                colunas={["valor", "parcelas", "estabelecimento", "tipoDespesa"]}
                dados={dados}
            />
        </div>
    )
}