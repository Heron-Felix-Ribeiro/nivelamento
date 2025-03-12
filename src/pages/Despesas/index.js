

export default function Despesas() {

    return (
        <div className="container mt-4">
            <h1 className="md-12 mt-3 text-center fw-bold"> Lista de despesas </h1>
            <table className="table table-striped table-bordered">
                <thead className="table-dark">
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