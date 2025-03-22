import axios from "axios"
import Tabela from "../../components/Tabela"
import { useEffect, useState } from "react"


export default function Transacoes() {
    const [dados, setDados] = useState([]);

    useEffect(() => {
        axios.get("https://viacep.com.br/ws/01001000/json/")
            .then(response => {
                const cepData = response.data;
                const novoDado = [
                    {
                        Valor: "N/A",
                        Parcelas: "N/A",
                        Estabelecimento: cepData.logradouro || "Não informado",
                        Data: cepData.bairro || "Não informado"
                    }
                ];
                setDados(novoDado);
            })
            .catch(error => console.error("Erro ao carregar dados do ViaCEP:", error));
    }, []);
    

    return (
        <div>
            <h1 className="md-12 mt-3 text-center fw-bold"> Página de transações</h1>
            <Tabela
                colunas={["Valor", "Parcelas", "Estabelecimento", "Data"]}
                dados={dados}
            />
        </div>
    )
}