import {useEffect, useState} from "react";
import {Chart, registerables} from "chart.js";
import {useSelector} from "react-redux";
import {transacaoService} from "../../service/transacaoService";
import HomeChart from "../../components/homeChart";
import BotaoExcluir from "../../components/botaoDelete";
import Tabela from "../../components/tabela";

export default function Home() {
    const usuario = useSelector((state) => state.info.usuario);
    const cnpj = useSelector((state) => state.auth.cnpj);
    const [total, setTotal] = useState(null);
    const salario = useSelector((state) => state.info.verba);
    const [dadosTabela, setDadosTabela] = useState([]);

    useEffect(() => {
        transacaoService.totalTransacoes(cnpj)
            .then((response) => {
                const totalTransacoes = response.data;
                setTotal(totalTransacoes);
            })
            .catch((error) => {
                alert(error.message);
            });
    })

    useEffect(() => {
        if (!cnpj) return;

        transacaoService.totalTransacoes(cnpj)
            .then((response) => setTotal(response.data))
            .catch((error) => alert(error.message));
    }, [cnpj]);

    useEffect(() => {
        if (!cnpj) return;

        transacaoService.maioresTranscoes(cnpj)
            .then((response) => {
                setDadosTabela(response.data);
            })
            .catch((error) => {
                console.error("Erro ao buscar maiores transações:", error);
            });
    })

    return (
        <div className="container container-fluid rounded-3 ">
            <div className="text-center fw-bold">
                <h1>Bem-vindo, {usuario}</h1>
            </div>
            <div className="container-fluid">
                <div className="d-flex justify-content-between flex-wrap">
                    <div
                        className="bg-dark text-light rounded p-4 m-2 d-flex flex-column justify-content-center align-items-center"
                        style={{width: "300px"}}>
                        <h3 className="fw-bold "> Saldo: {salario - total} </h3>
                        {total !== null && <HomeChart gastos={total} salario={salario}/>}
                    </div>
                    <div className="bg-dark text-light rounded p-4 m-2" style={{width: "300px"}}>
                        <h3>Maiores Transações</h3>
                        <Tabela
                            colunas={[
                                {key: "valor", label: "Valor"},
                                {key: "estabelecimento", label: "Prestador de Serviço"},
                            ]}
                            dados={dadosTabela}
                        />
                    </div>
                </div>
            </div>
        </div>
    )

}