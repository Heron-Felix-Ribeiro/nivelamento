import axios from "axios";
import { useEffect, useState } from "react"
import Tabela from "../../components/Tabela";


export default function Despesas() {
    const [dados, setDados] = useState ([]);

    useEffect(() => {
        fetch("https://viacep.com.br/ws/01001000/json/")
            .then(response => response.json())
            .then(data => {
                
                const despesasFormatadas = [
                    {
                        Valor: "R$ 100,00", 
                        Parcelas: "1x",
                        "Tipo Despesa": data.logradouro || "Outro"
                    }
                ];
                setDados(despesasFormatadas);
            })
            .catch(error => {
                console.error("Erro ao buscar dados:", error);
            });
    }, []);

    return (
       <div>
        <h1 className="md-12 mt-3 text-center fw-bold">Lista de Despesas</h1>
        <Tabela 
                colunas={["Valor", "Parcelas", "Tipo Despesa"]}
                dados={dados}
            />
       </div>
    )
}