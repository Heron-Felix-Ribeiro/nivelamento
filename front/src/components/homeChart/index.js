import React, {useEffect, useRef, useState} from "react";
import { useSelector } from "react-redux";
import {ArcElement, Chart, Legend, Tooltip} from "chart.js";

Chart.register(ArcElement, Tooltip, Legend);

export default function HomeChart({usuario, gastos, salario}) {
    const chartRef = useRef(null);

    useEffect(() => {
        if (chartRef.current) {
            const ctx = chartRef.current.getContext("2d");
            new Chart(ctx, {
                type: "pie",
                data: {
                    labels: ["Gastos", "Disponível"],
                    datasets: [
                        {
                            data: [gastos, salario - gastos],
                            backgroundColor: ["#FF6384", "#36A2EB"],
                        },
                    ],
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        tooltip: {
                            callbacks: {
                                label: (context) => {
                                    const valor = context.raw;
                                    return `${context.label}: R$ ${valor.toFixed(2)}`;
                                },
                            },
                        },
                    },
                },
            });
        }
    }, [salario, gastos]);

    return (
        <div>
            <h2>Seu resumo financeiro {usuario}</h2>
            <canvas ref={chartRef}></canvas>
        </div>
    )
}