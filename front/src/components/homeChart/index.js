import { useRef, useEffect } from "react";
import { Chart, ArcElement, Legend, Tooltip, registerables } from "chart.js";

Chart.register(ArcElement, Tooltip, Legend, ...registerables);

export default function HomeChart({ gastos, salario }) {
    const chartGastos = useRef(null);

    useEffect(() => {
        if (chartGastos.current) {
            const ctx = chartGastos.current.getContext("2d");

            if (Chart.getChart(ctx)) {
                Chart.getChart(ctx).destroy();
            }

            new Chart(ctx, {
                type: "pie",
                data: {
                    labels: ["Gastos", "Disponível"],
                    datasets: [
                        { data: [gastos, salario - gastos], backgroundColor: ["#FF6384", "#36A2EB"] }
                    ]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: { position: 'top' },
                        tooltip: {
                            callbacks: {
                                label: context => `${context.label}: R$ ${context.raw.toFixed(2)}`
                            }
                        }
                    }
                }
            });
        }
    }, [gastos, salario]);

    return (
        <div className={"container mt-3"}>
                <div className={"col-md-6"}>
                    <canvas ref={chartGastos}></canvas>
                </div>
        </div>
    );
}
