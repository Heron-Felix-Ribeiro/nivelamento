export default function Tabela({ colunas, dados, renderAcoes }) {
    return (
        <div className="text-white">
            <table className="table table-striped table-dark">
                <thead>
                    <tr>
                        {colunas.map((coluna) => (
                            <th key={coluna.key} className="px-4 py-2 text-left">
                                {coluna.label}
                            </th>
                        ))}
                    </tr>
                </thead>
                <tbody>
                    {dados.length > 0 ? (
                        dados.map((linha, index) => (
                            <tr key={index}>
                                {colunas.map((coluna) => (
                                    <td key={coluna.key} className="px-4 py-2">
                                        {coluna.key === "ações" && renderAcoes
                                            ? renderAcoes(linha)
                                            : linha[coluna.key] || "-"}
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
    );
}