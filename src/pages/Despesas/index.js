

export default function Despesas() {

    return (
        <div className="container bg-dark text-light">
            <h1 className="md-12 mt-3 text-center fw-bold"> Lista de despesas </h1>
            <table> 
                <thead>
                    <tr>
                        <th>Valor</th>
                        <th>Parcelas</th>
                        <th>Tipo de Despesa</th>
                    </tr>
                </thead>
            </table>
        </div>

    )
}