import { useEffect, useState } from "react";
import { Chart, registerables } from "chart.js";
import {useSelector} from "react-redux";

Chart.register(...registerables);

export default function Home() {
    const usuario = useSelector((state) => state.auth.usuario);
    const saldo = useSelector((state) => state.auth.salario);
    const transacoes = [];

    const [chartData, setChartData] = useState(null);
    const [chartInstance, setChartInstance] = useState(null);

    useEffect(() => {

    })

//     useEffect(() => {
//         if (usuario && transacoes.length > 0) {
//             const salarioUsuario = usuario.salario || 0; 
//             const totalGastos = transacoes.reduce((total, transacao) => {
//                 return total + Number(transacao.valor); 
//             }, 0);
            
//             const saldoAtual = salarioUsuario - totalGastos;
    
//             const data = {
//                 labels: ["Salário", "Gastos", "Saldo"],
//                 datasets: [
//                     {
//                         label: "Financeiro",
//                         data: [salarioUsuario, totalGastos, saldoAtual], 
//                         backgroundColor: ["#4CAF50", "#FF6F61", "#2196F3"],
//                     },
//                 ],
//             };
    
//             setChartData(data);
//         }
//     }, [usuario, transacoes]);
    

//     useEffect(() => {
//         if (chartData && !chartInstance) {
//             const canvasElement = document.getElementById("financeChart");
//             if (canvasElement) {
//                 const ctx = canvasElement.getContext("2d");
//                 const newChart = new Chart(ctx, {
//                     type: "bar",
//                     data: chartData,
//                     options: {
//                         responsive: true,
//                         plugins: {
//                             legend: { display: true, position: "top" },
//                         },
//                     },
//                 });
//                 setChartInstance(newChart);
//             }
//         }

//         return () => {
//             if (chartInstance) {
//                 chartInstance.destroy();
//                 setChartInstance(null);
//             }
//         };
//     }, [chartData, chartInstance]);

//     return (
//         <div>
//             <h1>Gráfico Financeiro</h1>
//             <canvas id="financeChart"></canvas>
//         </div>
//     );
 }