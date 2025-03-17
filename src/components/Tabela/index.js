import { useState } from "react"

export default function Tabela({colunas, dados}) {

    return (
        <div className="text-white">
            <table className="table table-striped table-dark">
                <thead>
                    <tr>
                        {colunas.map((coluna) => (
                            <th key={coluna} className="px-4 py-2 text-left">
                                {coluna}
                            </th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    {dados.length > 0 ? (
                        dados.map((linha, index) => (
                            <tr key={index}>
                                {colunas.map((coluna) => (
                                    
                                    <td key={coluna} className="px-4 py-2">
                                        {linha[coluna] || "-"}
                                    </td>
                                
                                ))}
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan={colunas.length} className="text-center py-4">
                                Sem dados registrados
                            </td>
                        </tr>
                    )}
                </tbody>
            </table>
        </div>
    )
}