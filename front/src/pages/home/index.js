import {useEffect, useState} from "react";
import {Chart, registerables} from "chart.js";
import {useSelector} from "react-redux";
import {transacaoService} from "../../service/transacaoService";
import HomeChart from "../../components/homeChart";

export default function Home() {
    const usuario = useSelector((state) => state.auth.usuario);
    const id = useSelector((state) => state.auth.id);
    const {total, setTotal} = useState(null);
    const salario = useSelector((state) => state.auth.salario);

    useEffect(() => {
        transacaoService.totalTransacoes(id)
            .then((response) => {
                const totalTransacoes = response.data;
                setTotal(totalTransacoes);
            })
            .catch((error) => {
                alert(error.message);
            });
    })

    return(
        <div>
            <h1 className="text-center"></h1>
            <HomeChart
                usuario={usuario}
                gastos={total}
                salario={salario}
            />
        </div>
    )

}